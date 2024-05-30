package com.openclassrooms.tajmahal;

import org.junit.Test;

import static org.junit.Assert.*;

import androidx.core.widget.TextViewCompat;

import com.openclassrooms.tajmahal.data.service.RestaurantApi;
import com.openclassrooms.tajmahal.data.service.RestaurantFakeApi;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void countReview() {
        RestaurantApi api = new RestaurantFakeApi();
        int reviewsCount = api.getReviews().size();
        assertEquals(5, reviewsCount);
    }

    @Test
    public void addReview() {
        RestaurantApi api = new RestaurantFakeApi();
        api.addReview("commentaire", 4, "", "Julie");
        assertEquals(6, api.getReviews().size());
    }

    @Test
    public void addEmptyCommentReview() {
        RestaurantApi api = new RestaurantFakeApi();
        api.addReview("", 0, "", "");
        assertEquals(5, api.getReviews().size());
    }

    @Test
    public void addEmptyRatingReview() {
        RestaurantApi api = new RestaurantFakeApi();
        api.addReview("commentaire", null, "", "");
        assertEquals(5, api.getReviews().size());
    }

}