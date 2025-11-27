package com.example.quizzy.ui.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quizzy.R;
import com.example.quizzy.data.model.Score;
import com.example.quizzy.viewModel.ScoreViewModel;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {
    private List<Score> scores;
    private ScoreViewModel viewModel;
    private Context context;
    private boolean showDeleteButton;

    public ScoreAdapter(Context context){
        this.context = context;
    }

    public void setShowDeleteButton(boolean show) {
        this.showDeleteButton = show;
    }

    public void setScores(List<Score> scores) {
        if (scores != null) {
            this.scores = scores;
        }
        notifyDataSetChanged();
    }
    public void setViewModel(ScoreViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Score score = scores.get(position);
        holder.playerName.setText("Nom Joueur : " + score.getPlayerName());
        holder.category.setText("categorie : " + score.getCategory());
        holder.score.setText("Score : " + score.getScore());

        // Afficher ou cacher le bouton en fonction de "showDeleteButton"
        holder.btnDelete.setVisibility(showDeleteButton ? View.VISIBLE : View.GONE);

        //supprimer un score
        holder.btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Supprimer")
                    .setMessage("Voulez-vous vraiment supprimer cet score ?")
                    .setPositiveButton("Oui", (dialog, which) -> {
                        new Thread(() -> {
                            if (viewModel != null) {
                                    viewModel.delete(score);
                                    ((Activity) context).runOnUiThread(() -> {
                                        scores.remove(position);
                                        notifyItemRemoved(position);
                                        Toast.makeText(context, "Score supprimé", Toast.LENGTH_SHORT).show();
                                    });
                            } else {
                                Log.e("ItemAdapter", "ViewModel non initialisé !");
                            }

                        }).start();
                    })
                    .setNegativeButton("Non", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return scores != null ? scores.size() : 0;
    }


    static class ScoreViewHolder extends RecyclerView.ViewHolder {
        TextView playerName, category, score;
        ImageView btnDelete;

        ScoreViewHolder(View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
            category = itemView.findViewById(R.id.categoryQuestion);
            score = itemView.findViewById(R.id.playerScore);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
