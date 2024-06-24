package com.openclassrooms.tajmahal.ui.restaurant;

import androidx.recyclerview.widget.DiffUtil;

import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.List;

public class ReviewDiffCallback extends DiffUtil.Callback{
    private final List<Review> oldList;
    private final List<Review> newList;
    public ReviewDiffCallback(List<Review> oldList, List<Review> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        // Compare the unique identifiers of the items
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        // Compare the contents of the items
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}