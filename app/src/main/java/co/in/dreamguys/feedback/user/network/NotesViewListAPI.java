package co.in.dreamguys.feedback.user.network;

import co.in.dreamguys.feedback.user.response.NotesViewListResponse;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Dhanraaj on 6/15/2017.
 */

public class NotesViewListAPI {
    private static final NotesViewListAPI ourInstance = new NotesViewListAPI();

    public static NotesViewListAPI getInstance() {
        return ourInstance;
    }

    private NotesViewListAPI() {
    }

    public void Callresponse(Callback<NotesViewListResponse.UserNotesViewListResponse> mCallback) {
        ReviewDetails mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(ReviewDetails.class);
        mGitapi.mVendor(mCallback);
    }

    public interface ReviewDetails {
        @GET("/notesList")
        public void mVendor(Callback<NotesViewListResponse.UserNotesViewListResponse> response);
    }
}
