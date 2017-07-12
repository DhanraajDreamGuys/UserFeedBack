package co.in.dreamguys.feedback.user.network;

import co.in.dreamguys.feedback.user.response.CategoryListChartResponse;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Dhanraaj on 6/15/2017.
 */

public class CategoryChartListAPI {
    private static final CategoryChartListAPI ourInstance = new CategoryChartListAPI();

    public static CategoryChartListAPI getInstance() {
        return ourInstance;
    }

    private CategoryChartListAPI() {
    }

    public void Callresponse(String restaurantId, String color, Callback<CategoryListChartResponse.UserListChartResponse> mCallback) {
        ReviewDetails mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(ReviewDetails.class);
        mGitapi.mVendor(restaurantId, color, mCallback);
    }

    public interface ReviewDetails {
        @FormUrlEncoded
        @POST("/lineChart")
        public void mVendor(@Field("restaurantId") String restaurantId, @Field("color") String color,
                            Callback<CategoryListChartResponse.UserListChartResponse> response);
    }
}
