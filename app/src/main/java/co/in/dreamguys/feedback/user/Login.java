package co.in.dreamguys.feedback.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import co.in.dreamguys.feedback.user.helper.Constants;
import co.in.dreamguys.feedback.user.helper.CustomProgressDialog;
import co.in.dreamguys.feedback.user.helper.SessionHandler;
import co.in.dreamguys.feedback.user.helper.Utils;
import co.in.dreamguys.feedback.user.network.LoginAPI;
import co.in.dreamguys.feedback.user.response.LoginResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by user5 on 06-07-2017.
 */

public class Login extends AppCompatActivity {

    Button buttonLogin;
    EditText editUsername, editPassword;
    TextView textForgotpassword;
    CustomProgressDialog mCustomProgressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCustomProgressDialog = new CustomProgressDialog(this);
        Constants.ArrayFilter.clear();
        initWidgets();


    }

    private void initWidgets() {
        buttonLogin = (Button) findViewById(R.id.button_login);
        editUsername = (EditText) findViewById(R.id.edit_username);
        editPassword = (EditText) findViewById(R.id.edit_password);
        textForgotpassword = (TextView) findViewById(R.id.text_forgot_password);
    }

    public void loginButton(final View view) {
        if (editUsername.getText().toString().isEmpty() && editPassword.getText().toString().isEmpty()) {
            editUsername.setError(getString(R.string.err_username));
            editUsername.requestFocus();
            editPassword.setError(getString(R.string.err_password));
            editPassword.requestFocus();
        } else if (editUsername.getText().toString().isEmpty()) {
            editUsername.setError(getString(R.string.err_username));
            editUsername.requestFocus();
        } else if (editPassword.getText().toString().isEmpty()) {
            editPassword.setError(getString(R.string.err_password));
            editPassword.requestFocus();
        } else if (!Utils.isNetworkAvailable(this)) {
            Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
        } else {
            mCustomProgressDialog.showDialog();
            LoginAPI.getInstance().Callresponse(editUsername.getText().toString(), editPassword.getText().toString(), new Callback<LoginResponse.OwnerLoginResponse>() {
                @Override
                public void success(LoginResponse.OwnerLoginResponse ownerLoginResponse, Response response) {
                    if (ownerLoginResponse.getStatus().equalsIgnoreCase("Y")) {
                        startActivity(new Intent(Login.this, MainActivity.class));
                        SessionHandler.getInstance().save(Login.this, Constants.RESTAURANT_ID, ownerLoginResponse.getData().get(0).getRest_id());
                        finish();
                    } else {
                        Snackbar.make(view, ownerLoginResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                    mCustomProgressDialog.dismiss();
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.i("TAG", error.getMessage());
                    mCustomProgressDialog.dismiss();
                }
            });
        }
    }
}
