package co.in.dreamguys.feedback.user.network;

import co.in.dreamguys.feedback.user.response.NotesDeleteListResponse;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Dhanraaj on 6/15/2017.
 */

public class NotesDeleteListAPI {
    private static final NotesDeleteListAPI ourInstance = new NotesDeleteListAPI();

    public static NotesDeleteListAPI getInstance() {
        return ourInstance;
    }

    private NotesDeleteListAPI() {
    }

    public void Callresponse(String NotesId, Callback<NotesDeleteListResponse.UserNotesDeleteListResponse> mCallback) {
        ReviewDetails mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(ReviewDetails.class);
        mGitapi.mVendor(NotesId, mCallback);
    }

    public interface ReviewDetails {
        @FormUrlEncoded
        @POST("/deleteNotes")
        public void mVendor(@Field("notesId") String NotesId,Callback<NotesDeleteListResponse.UserNotesDeleteListResponse> response);
    }
}
