package co.in.dreamguys.feedback.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.in.dreamguys.feedback.user.network.CategoryListAPI;
import co.in.dreamguys.feedback.user.response.CategoryListResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by user5 on 17-07-2017.
 */

public class AddNote extends AppCompatActivity implements View.OnClickListener {


    TextView inputCategory, inputDate;
    Spinner inputList;
    EditText inputComments;
    List<CategoryListResponse.Datum> arrayCategoryList = new ArrayList<>();
    List<String> CategoryList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initWidgets();


        CategoryListAPI.getInstance().Callresponse(new Callback<CategoryListResponse.UserCategoryListResponse>() {
            @Override
            public void success(CategoryListResponse.UserCategoryListResponse userCategoryListResponse, Response response) {
                if (userCategoryListResponse.getStatus().equalsIgnoreCase("Y")) {
                    arrayCategoryList = userCategoryListResponse.getData();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    public void initWidgets() {
        inputCategory = (TextView) findViewById(R.id.input_categoryList);
        inputDate = (TextView) findViewById(R.id.input_date);
        inputComments = (EditText) findViewById(R.id.input_comments);
    }

    @Override
    public void onClick(View v) {

    }
}
