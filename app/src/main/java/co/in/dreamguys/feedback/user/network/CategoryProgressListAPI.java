package co.in.dreamguys.feedback.user.network;

import co.in.dreamguys.feedback.user.response.SurveyListResponse;
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

    public void Callresponse(String restaurantId, Callback<SurveyListResponse.UserSurveyListResponse> mCallback) {
        ReviewDetails mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(ReviewDetails.class);
        mGitapi.mVendor(restaurantId, mCallback);
    }

    public interface ReviewDetails {
        @FormUrlEncoded
        @POST("/getSurveyrating")
        void mVendor(@Field("restaurant_id") String restaurantId, Callback<SurveyListResponse.UserSurveyListResponse> response);
    }
}
