package co.in.dreamguys.feedback.user.helper;

import android.content.Context;

/**
 * Created by user5 on 19-05-2017.
 */

public class SessionHandler {
    private static SessionHandler ourInstance = new SessionHandler();

    public static SessionHandler getInstance() {
        return ourInstance;
    }

    private SessionHandler() {
    }

    public void save(Context mContext, String key, String value) {
        mContext.getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE).edit().putString(key, value).commit();
    }

    public void remove(Context mContext, String key) {
        mContext.getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE).edit().remove(key).commit();
    }

    public void removeAll(Context mContext) {
        mContext.getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE).edit().clear().commit();
    }

    public String get(Context mContext, String key) {
        return mContext.getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE).getString(key, null);
    }


}
