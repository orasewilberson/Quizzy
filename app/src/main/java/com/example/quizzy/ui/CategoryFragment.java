package com.example.quizzy.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quizzy.R;
import com.example.quizzy.databinding.FragmentCategoryBinding;
import com.example.quizzy.ui.adapter.CategoryAdapter;
import com.example.quizzy.viewModel.ScoreViewModel;
import java.util.Arrays;
import java.util.List;

public class CategoryFragment extends Fragment {
    private FragmentCategoryBinding binding;
    private ScoreViewModel scoreViewModel;
    private String playerName;


    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Charger le ScoreViewModel
        scoreViewModel = new ViewModelProvider(requireActivity()).get(ScoreViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);  // Indique que ce fragment a un menu
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        List<String> categories = Arrays.asList("Chimie", "Biologie", "Mathematiques", "Culture generale", "Divertissement et Sport", "Programmation");

        CategoryAdapter adapter = new CategoryAdapter(categories, category -> {
            // Récupérer le PlayerName
            Bundle args = getArguments();
            playerName = (args != null) ? args.getString("playerName", "") : "";

            if (category != null) {
               Bundle bundle = new Bundle();
                bundle.putString("selected_category", category);
                bundle.putString("playerName", playerName);

                try {
                    navController.navigate(R.id.action_categoryFragment_to_quizFragment, bundle);
                    Log.d("CategoryFragment", "Navigation effectuée avec succès !");
                } catch (Exception e) {
                    Log.e("CategoryFragment", "Erreur lors de la navigation", e);
                }
            } else {
                Log.w("CategoryFragment", "Aucune catégorie sélectionnée");
            }
        });

        binding.recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewCategories.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_category, menu);
        // Trouver l'élément du menu
        MenuItem themeItem = menu.findItem(R.id.action_toggle_theme);

        // Vérifier le mode actuel et changer l'icône
        boolean isDarkMode = (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES);
        themeItem.setIcon(isDarkMode ? R.drawable.ic_light_mode : R.drawable.ic_dark_mode);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(requireView());
        Bundle args = getArguments();
         playerName = args != null ? args.getString("playerName", "") : "";

        if (item.getItemId() == R.id.action_history_user) {
            if (playerName == null || playerName.isEmpty()) {
                Toast.makeText(requireContext(), "Nom du joueur non trouvé", Toast.LENGTH_SHORT).show();
                return false;
            }

            // Afficher les scores de l'utilisateur connecté
            Bundle bundle = new Bundle();
            bundle.putBoolean("showUserScores", true);
            bundle.putString("playerName", playerName); // Passer playerName
            navController.navigate(R.id.action_categoryFragment_to_scoreFragment, bundle);
            return true;
        } else if (item.getItemId() == R.id.action_history_all) {
            // Afficher les scores de tous les joueurs
            Bundle bundle = new Bundle();
            bundle.putBoolean("showUserScores", false);
            navController.navigate(R.id.action_categoryFragment_to_scoreFragment, bundle);
            return true;
        }  else if (item.getItemId() == R.id.action_toggle_theme) {
            toggleTheme();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void toggleTheme() {
        SharedPreferences prefs = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        boolean isDarkMode = prefs.getBoolean("dark_mode", false);

        // Basculer entre les modes immédiatement
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        // Sauvegarder le choix de l'utilisateur
        prefs.edit().putBoolean("dark_mode", !isDarkMode).apply();
    }


}
