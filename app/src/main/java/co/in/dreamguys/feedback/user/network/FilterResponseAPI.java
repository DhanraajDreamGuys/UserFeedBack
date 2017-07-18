package co.in.dreamguys.feedback.user.network;

import co.in.dreamguys.feedback.user.response.FilterResponse;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Dhanraaj on 6/15/2017.
 */

public class FilterResponseAPI {
    private static final FilterResponseAPI ourInstance = new FilterResponseAPI();

    public static FilterResponseAPI getInstance() {
        return ourInstance;
    }

    private FilterResponseAPI() {
    }

    public void Callresponse(String restaurantId, String Gender, String Skintone, String Age, Callback<FilterResponse.UserFilterResponse> mCallback) {
        ReviewDetails mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(ReviewDetails.class);
        mGitapi.mVendor(restaurantId, Gender, Age, Skintone, mCallback);
    }

    public interface ReviewDetails {
        @FormUrlEncoded
        @POST("/getSureyfilter")
        public void mVendor(@Field("restaurantId") String restaurantId, @Field("gender") String Gender, @Field("skin_tone") String Skintone,
                            @Field("Age") String Age, Callback<FilterResponse.UserFilterResponse> response);
    }
}
