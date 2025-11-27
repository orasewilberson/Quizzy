package com.example.quizzy.ui;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quizzy.R;
import com.example.quizzy.data.dao.ScoreDao;
import com.example.quizzy.data.model.Question;
import com.example.quizzy.data.model.Score;
import com.example.quizzy.databinding.FragmentQuizBinding;
import com.example.quizzy.viewModel.QuizViewModel;
import com.example.quizzy.viewModel.QuizViewModelFactory;
import com.example.quizzy.viewModel.ScoreViewModel;

import java.util.Arrays;
import java.util.List;


public class QuizFragment extends Fragment {
    private QuizViewModel viewModel;
    private FragmentQuizBinding binding;
    private Question currentQuestion;  // Variable pour stocker la question actuelle
    private View rootView;


    MutableLiveData<Integer> score = new MutableLiveData<Integer>(0);

    public static QuizFragment newInstance() {
        return new QuizFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Charger le layout du fragment
        binding = FragmentQuizBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;

        // Récupérer la catégorie sélectionnée
        Bundle args = getArguments();
        String category = (args != null) ? args.getString("selected_category", "") : "";

        if (!category.isEmpty()) {
            // Initialiser le ViewModel après avoir récupéré la catégorie
            QuizViewModelFactory factory = new QuizViewModelFactory(requireActivity().getApplication(), category);
            viewModel = new ViewModelProvider(this, factory).get(QuizViewModel.class);

            // Observer les questions
            viewModel.getCurrentQuestion().observe(getViewLifecycleOwner(), question -> {
                if (question != null) {
                    displayQuestion(question); // Afficher la première question
                } else {
                    Log.e("QuizFragment", "Aucune question reçue !");
                }
            });
        } else {
            Log.e("QuizFragment", "Aucune catégorie reçue !");
        }

        // Gestion des clics des boutons
        View.OnClickListener answerClickListener = v -> {
            if (currentQuestion == null) {
                Log.e("QuizFragment", "Erreur : Aucune question disponible !");
                return;
            }

            Button selectedButton = (Button) v;
            String selectedAnswer = selectedButton.getText().toString();
            checkAnswer(selectedButton, selectedAnswer, currentQuestion.getCorrectAnswer());  // Utiliser la question actuelle
        };

        binding.answer1.setOnClickListener(answerClickListener);
        binding.answer2.setOnClickListener(answerClickListener);
        binding.answer3.setOnClickListener(answerClickListener);
        binding.answer4.setOnClickListener(answerClickListener);

        // Observer si c'est la dernière question et mettre à jour le texte du bouton "Next"
        viewModel.getIsLastQuestion().observe(getViewLifecycleOwner(), isLast -> {
            if (isLast) {
                binding.next.setText("Terminé");  // Dernière question
            } else {
                binding.next.setText("Suivant");  // Question suivante
            }
        });


        // Gestion du bouton "Next"
        binding.next.setOnClickListener(v -> {
            Boolean isLast = viewModel.getIsLastQuestion().getValue();

            if (isLast != null && isLast) {
                Log.e("islast", "derniere question" + isLast);

                displayResultDialog(); // Afficher le score final et terminer le quiz
            } else {
                viewModel.nextQuestion(); // Passer à la prochaine question
                resetQuestion();
                binding.next.setVisibility(View.INVISIBLE); // Cacher le bouton jusqu'à une nouvelle réponse
            }
        });


    }

    // afficher les questions
    private void displayQuestion(Question question) {
        this.currentQuestion = question;  // Assigner la question actuelle
        List<String> shuffledAnswers = question.getShuffledAnswers(); // Mélange les réponses avant affichage

        binding.question.setText(question.getQuestionText());
        binding.answer1.setText(shuffledAnswers.get(0));
        binding.answer2.setText(shuffledAnswers.get(1));
        binding.answer3.setText(shuffledAnswers.get(2));
        binding.answer4.setText(shuffledAnswers.get(3));
    }

    // Vérifie si la réponse est correcte
    private void checkAnswer(Button button, String selectedAnswer, String correctAnswer) {
        if (selectedAnswer.equals(correctAnswer)) {

            Integer currentScore = score.getValue(); // Récupère le score actuel
            if (currentScore != null) {
                score.setValue(currentScore + 1); // Incrémente le score si la réponse est correcte
            }
            button.setBackgroundResource(R.drawable.correct_answer);
            binding.validityText.setText("Bonne reponse ! 💪");
            button.announceForAccessibility("Bonne reponse !");
        } else {
            button.setBackgroundResource(R.drawable.wrong_answer);
            binding.validityText.setText("Mauvaise reponse 😢");
            button.announceForAccessibility("Mauvaise reponse !");
        }
        binding.validityText.setVisibility(View.VISIBLE);
        binding.next.setVisibility(View.VISIBLE);
        enableAllAnswers(false, button);

    }


    // Active ou désactive les boutons de réponse
    private void enableAllAnswers(boolean enable, Button selectedButton) {
        List<Button> allAnswers = Arrays.asList(binding.answer1, binding.answer2, binding.answer3, binding.answer4);
        for (Button answer : allAnswers) {
            if (enable) {
                answer.setEnabled(true);
            } else {
                answer.setEnabled(false);
                // Ne change pas la couleur du bouton sélectionné
                if (answer != selectedButton) {
                    answer.setBackgroundResource(R.drawable.rounded_button); // Restaurer le style original
                }
            }
        }
    }

    // Réinitialise l'affichage pour une nouvelle question
    private void resetQuestion() {
        List<Button> allAnswers = Arrays.asList(binding.answer1, binding.answer2, binding.answer3, binding.answer4);
        for (Button answer : allAnswers) {
            answer.setBackgroundResource(R.drawable.rounded_button); // Remettre le fond initial
            answer.setEnabled(true);  // Réactive les boutons de réponse
        }
        binding.validityText.setVisibility(View.INVISIBLE); // Cache le texte de validité

    }

    // Affiche la boîte de dialogue avec le score final
    private void displayResultDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Bundle args = getArguments();
        String category = (args != null) ? args.getString("selected_category", "") : "";
        String playerName = (args != null) ? args.getString("playerName", "") : "";

        int finalScore = score.getValue() != null ? score.getValue() : 0;
        builder.setTitle("Terminé !");
        builder.setMessage("votre score final est " + finalScore);
        // Bouton pour quitter et revenir à l'écran d'accueil
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                saveScoreToDatabase(playerName, category, finalScore);
                goToCategoryFragment();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void goToCategoryFragment() {
        // Récupérer le nom du joueur puis l'envoiye vers CategoryFragment
        Bundle args = getArguments();
        String playerName = (args != null) ? args.getString("playerName", "") : "";

        Bundle bundle = new Bundle();
        bundle.putString("playerName",playerName);
        NavController navController = Navigation.findNavController(rootView);
        navController.navigate(R.id.action_quizFragment_to_categoryFragment, args);

    }

    // Méthode pour enregistrer le score dans la base de données
    private void saveScoreToDatabase(String playerName, String category, int score) {
        ScoreViewModel scoreViewModel = new ScoreViewModel(new Application());
            scoreViewModel.insert(new Score(playerName, category, score));

    }
}



