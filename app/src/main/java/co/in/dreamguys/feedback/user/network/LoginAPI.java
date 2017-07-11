package co.in.dreamguys.feedback.user.network;

import co.in.dreamguys.feedback.user.response.LoginResponse;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Dhanraaj on 6/15/2017.
 */

public class LoginAPI {
    private static final LoginAPI ourInstance = new LoginAPI();

    public static LoginAPI getInstance() {
        return ourInstance;
    }

    private LoginAPI() {
    }

    public void Callresponse(String restaurantUsername, String restaurantPassword, Callback<LoginResponse.OwnerLoginResponse> mCallback) {
        ReviewDetails mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(ReviewDetails.class);
        mGitapi.mVendor(restaurantUsername, restaurantPassword, mCallback);
    }

    public interface ReviewDetails {
        @FormUrlEncoded
        @POST("/ownerLogin")
        public void mVendor(@Field("restaurantUsername") String restaurantUsername, @Field("restaurantPassword") String restaurantPassword,
                            Callback<LoginResponse.OwnerLoginResponse> response);
    }
}
