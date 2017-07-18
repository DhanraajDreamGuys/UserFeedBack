package co.in.dreamguys.feedback.user.network;

import co.in.dreamguys.feedback.user.response.NotesCategoryListResponse;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Dhanraaj on 6/15/2017.
 */

public class NotesCategoryListAPI {
    private static final NotesCategoryListAPI ourInstance = new NotesCategoryListAPI();

    public static NotesCategoryListAPI getInstance() {
        return ourInstance;
    }

    private NotesCategoryListAPI() {
    }

    public void Callresponse(Callback<NotesCategoryListResponse.UserNotesCategoryListResponse> mCallback) {
        ReviewDetails mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(ReviewDetails.class);
        mGitapi.mVendor(mCallback);
    }

    public interface ReviewDetails {
        @GET("/notesCategorylist")
        public void mVendor(Callback<NotesCategoryListResponse.UserNotesCategoryListResponse> response);
    }
}
