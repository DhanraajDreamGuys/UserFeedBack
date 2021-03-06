package co.in.dreamguys.feedback.user.network;

import co.in.dreamguys.feedback.user.response.NotesCategoryListResponse;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Dhanraaj on 6/15/2017.
 */

public class CategoryListAPI {
    private static final CategoryListAPI ourInstance = new CategoryListAPI();

    public static CategoryListAPI getInstance() {
        return ourInstance;
    }

    private CategoryListAPI() {
    }

    public void Callresponse(Callback<NotesCategoryListResponse.UserNotesCategoryListResponse> mCallback) {
        ReviewDetails mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(ReviewDetails.class);
        mGitapi.mVendor(mCallback);
    }

    public interface ReviewDetails {
        @GET("/categorylist")
        public void mVendor(Callback<NotesCategoryListResponse.UserNotesCategoryListResponse> response);
    }
}
