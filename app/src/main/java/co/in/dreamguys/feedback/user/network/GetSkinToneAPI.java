package co.in.dreamguys.feedback.user.network;

import co.in.dreamguys.feedback.user.response.SkinToneResponse;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Dhanraaj on 6/15/2017.
 */

public class GetSkinToneAPI {
    private static final GetSkinToneAPI ourInstance = new GetSkinToneAPI();

    public static GetSkinToneAPI getInstance() {
        return ourInstance;
    }

    private GetSkinToneAPI() {
    }

    public void Callresponse(String restaurantId, Callback<SkinToneResponse.UserSkinToneResponse> mCallback) {
        ReviewDetails mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(ReviewDetails.class);
        mGitapi.mVendor(restaurantId, mCallback);
    }

    public interface ReviewDetails {
        @FormUrlEncoded
        @POST("/getSkintone")
        public void mVendor(@Field("restaurantId") String restaurantId, Callback<SkinToneResponse.UserSkinToneResponse> response);
    }
}
