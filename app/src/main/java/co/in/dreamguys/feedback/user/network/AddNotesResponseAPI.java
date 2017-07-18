package co.in.dreamguys.feedback.user.network;

import co.in.dreamguys.feedback.user.response.AddNotesResponse;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Dhanraaj on 6/15/2017.
 */

public class AddNotesResponseAPI {
    private static final AddNotesResponseAPI ourInstance = new AddNotesResponseAPI();

    public static AddNotesResponseAPI getInstance() {
        return ourInstance;
    }

    private AddNotesResponseAPI() {
    }

    public void Callresponse(String restaurantId, String CategoryId, String Notes, String NotesDate, Callback<AddNotesResponse.UserAddNotesResponse> mCallback) {
        ReviewDetails mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(ReviewDetails.class);
        mGitapi.mVendor(restaurantId, CategoryId, Notes, NotesDate, mCallback);
    }

    public interface ReviewDetails {
        @FormUrlEncoded
        @POST("/notes")
        public void mVendor(@Field("restaurantId") String restaurantId, @Field("notes_cat_id") String CategoryId, @Field("notes") String Notes,
                            @Field("notes_date") String NotesDate, Callback<AddNotesResponse.UserAddNotesResponse> response);
    }
}
