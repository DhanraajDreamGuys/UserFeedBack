package co.in.dreamguys.feedback.user.network;

import co.in.dreamguys.feedback.user.response.CategoryListResponse;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Dhanraaj on 6/15/2017.
 */

public class CategoryProgressListAPI {
    private static final CategoryProgressListAPI ourInstance = new CategoryProgressListAPI();

    public static CategoryProgressListAPI getInstance() {
        return ourInstance;
    }

    private CategoryProgressListAPI() {
    }

    public void Callresponse(String restaurantId, Callback<CategoryListResponse.UserCategoryListResponse> mCallback) {
        ReviewDetails mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(ReviewDetails.class);
        mGitapi.mVendor(restaurantId, mCallback);
    }

    public interface ReviewDetails {
        @FormUrlEncoded
        @POST("/getChartdetails")
        void mVendor(@Field("restaurant_id") String restaurantId, Callback<CategoryListResponse.UserCategoryListResponse> response);
    }
}
