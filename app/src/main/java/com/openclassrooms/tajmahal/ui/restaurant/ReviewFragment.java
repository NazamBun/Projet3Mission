package com.openclassrooms.tajmahal.ui.restaurant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.databinding.FragmentReviewBinding;
import com.openclassrooms.tajmahal.domain.model.User;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewFragment extends Fragment {
    private ReviewListAdapter reviewListAdapter;
    private DetailsViewModel detailsViewModel;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters
    private FragmentReviewBinding binding;

    public ReviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment ReviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewFragment newInstance() {
        ReviewFragment fragment = new ReviewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_review, container, false);
        binding = FragmentReviewBinding.inflate(inflater, container, false);
        reviewListAdapter = new ReviewListAdapter();
        binding.fragmentReviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.fragmentReviewRecyclerView.setAdapter(reviewListAdapter);
        setupViewModel();
        updateUI();
        setupAddReview();
        setupAvatar();

        //BackStack
        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        return binding.getRoot();
    }

    private void setupAvatar() {
        // Appeler le detailViewModel getUser()
        LiveData<User> user = detailsViewModel.getUser();
        user.observe(getViewLifecycleOwner(), user1 -> {
            //avec glide mettre en place l'avatar dans l'ui
            Glide.with(binding.ivNewReviewAvatar.getContext())
                    .load(user1.getPictureUrl())
                    .centerCrop()
                    .into(binding.ivNewReviewAvatar);
            binding.tvNewReviewName.setText(user1.getUserName());
        });

    }

    private void setupAddReview() {
        binding.buttonValidate.setOnClickListener(
                arg -> {
                    Log.d("reviewFragment", "click sur le bouton valider");
                    String comment = String.valueOf(Objects.requireNonNull(binding.tilNewReviewComent.getEditText()).getText());
                    Log.d("reviewFragment", "Comment: "+comment);
                    float rating = binding.rbNewReviewRate.getRating();
                    Log.d("reviewFragment", "rating: "+ rating);
                    // Appeler le detailViewModel getUser()
                    String avatar = "https://xsgames.co/randomusers/assets/avatars/female/1.jpg";
                    String userName = "Manon Garcia";
                    detailsViewModel.addReview(comment, (int) rating, avatar, userName);
                    updateUI();
                }
        );

    }

    private void updateUI() {

        detailsViewModel.getReviews().observe(getViewLifecycleOwner(), reviews -> {
            Log.d("reviewFragment", reviews.size()+ "");
            reviewListAdapter.updateList(reviews);
        });
    }

    /**
     * Initializes the ViewModel for this fragment.
     */
    private void setupViewModel() {
        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
    }



}