package com.openclassrooms.tajmahal.ui.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListViewHolder> {
    private List<Review> listReview;
    public ReviewListAdapter() {
        listReview = new ArrayList<>();
    }

    public void updateList(List<Review> newList) {
        //Méthode NotifyDataSetChanged : entraine un rafraichissement complet de la liste coté ui
        //this.listReview = listReview;
        //notifyDataSetChanged(); // privilégier le diffUtil pour regénérer à l'écran uniquement les éléments de la liste qui ont changé
        // Méthode DiffUtil : entraine un rafraichissement partiel de la liste côté UI
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ReviewDiffCallback(listReview, newList));
        listReview.clear();
        listReview.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }
    @NonNull
    @Override
    public ReviewListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_review_item, parent, false);
        return new ReviewListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewListViewHolder holder, int position) {
        holder.setReview(listReview.get(position));
    }

    @Override
    public int getItemCount() {
        return listReview.size();
    }
}
