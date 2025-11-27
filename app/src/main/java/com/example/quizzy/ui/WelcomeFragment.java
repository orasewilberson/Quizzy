package com.example.quizzy.ui;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizzy.R;
import com.example.quizzy.databinding.FragmentWelcomeBinding;


public class WelcomeFragment extends Fragment {

    private FragmentWelcomeBinding binding;

    public WelcomeFragment() {
        // Constructeur vide obligatoire pour les fragments
    }

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Charger le layout du fragment
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //verifie si la saisie de l'utilisateur n'est pas vide et dans ce cas autorise le clic
        binding.playerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //activer le bouton si l'user commence a taper
                boolean isEmpty = s.toString().isEmpty();
                binding.playButton.setEnabled(!isEmpty);
                // Changer la couleur selon l'état
                if (isEmpty) {
                    binding.playButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
                } else {
                    binding.playButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.primary)));
                }

            }
        });

        //detecter le click sur le bouton 'let's play' pour afficher les categories
        binding.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer le nom entré par l'utilisateur
                String playerName = binding.playerName.getText().toString();

                // Naviguer vers le CategoryFragment pour afficher les categories
                Bundle bundle = new Bundle();
                bundle.putString("playerName", playerName);

                //renitialise le champ EditText
                binding.playerName.setText("");
                // Naviguer vers CategoryFragment en passant le nom
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_categoryFragment, bundle);

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        binding.playButton.setEnabled(false);
    }


}