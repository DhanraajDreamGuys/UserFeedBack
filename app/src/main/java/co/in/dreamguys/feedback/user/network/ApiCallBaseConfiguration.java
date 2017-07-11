package co.in.dreamguys.feedback.user.network;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by tech on 8/13/2015.
 */
public class ApiCallBaseConfiguration {
    private static ApiCallBaseConfiguration ourInstance;
    RestAdapter ApiBuilder;

    /*BASE URL place it here  -  live url*/
//    public String domainName = "https://www.dreamguys.co.in/display/reviewofsystems/v1";
    public String domainName = "https://dreamguys.co.in/display/reviewofsystems/V1";
//    public String domainName = "http://tolkbestilling.dk/tolkApi/web/index.php/v1";

    public static ApiCallBaseConfiguration getInstance() {
        if (ourInstance == null) {
            synchronized (ApiCallBaseConfiguration.class) {
                if (ourInstance == null)
                    ourInstance = new ApiCallBaseConfiguration();
            }
        }
        ourInstance.config();
        return ourInstance;
    }

    private ApiCallBaseConfiguration() {
    }

    private void config() {

        OkHttpClient mOkHttp = new OkHttpClient();
        mOkHttp.setReadTimeout(150, TimeUnit.SECONDS);
        mOkHttp.setConnectTimeout(150, TimeUnit.SECONDS);
        ApiBuilder = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(mOkHttp))
                .setEndpoint(domainName)
                .build();
    }

    public RestAdapter getApiBuilder() {
        return ApiBuilder;
    }

    public void setApiBuilder(RestAdapter apiBuilder) {
        ApiBuilder = apiBuilder;
    }
}
