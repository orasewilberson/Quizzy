package com.example.quizzy.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quizzy.R;
import com.example.quizzy.databinding.FragmentScoreBinding;
import com.example.quizzy.ui.adapter.ScoreAdapter;
import com.example.quizzy.viewModel.ScoreViewModel;

public class ScoreFragment extends Fragment {
    private ScoreViewModel scoreViewModel;
    private ScoreAdapter adapter;
    private FragmentScoreBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // Active le menu

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentScoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewScores);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ScoreAdapter(requireContext());
        recyclerView.setAdapter(adapter);

        // Récupérer les arguments
        Bundle args = getArguments();
        boolean showUserScores = args != null && args.getBoolean("showUserScores", false);
        String playerName = args != null ? args.getString("playerName", "") : "";

        scoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);
        adapter.setViewModel(scoreViewModel);

        if (showUserScores && playerName != null && !playerName.isEmpty()) {
            // Afficher les scores de l'utilisateur connecté
            scoreViewModel.getAllScoresUser(playerName).observe(getViewLifecycleOwner(), scoresUser -> {
                if (scoresUser != null && !scoresUser.isEmpty()) {
                    adapter.setScores(scoresUser);
                    adapter.setShowDeleteButton(true); // Rendre le bouton visible
                } else {
                    adapter.setShowDeleteButton(false); // Rendre le bouton visible
                    Toast.makeText(requireContext(), "Aucun score trouvé pour " + playerName, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Afficher tous les scores
            scoreViewModel.getAllScores().observe(getViewLifecycleOwner(), scores -> {
                if (scores != null && !scores.isEmpty()) {
                    adapter.setScores(scores);
                } else {
                    Toast.makeText(requireContext(), "Aucun score trouvé", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}