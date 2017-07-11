package co.in.dreamguys.feedback.user.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Dhanraaj on 5/4/2017.
 */
public class Utils {
    private static int TYPE_WIFI = 1;
    private static int TYPE_MOBILE = 2;
    private static int TYPE_NOT_CONNECTED = 0;

    public static boolean isNetworkAvailable(final Context context) {
        int conn = getConnectivityStatus(context);
        boolean status = false;
        if (conn == TYPE_WIFI) {
            status = true;
        } else if (conn == TYPE_MOBILE) {
            status = true;
        } else if (conn == TYPE_NOT_CONNECTED) {
            status = false;
        }
        return status;
    }

    private static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

}
