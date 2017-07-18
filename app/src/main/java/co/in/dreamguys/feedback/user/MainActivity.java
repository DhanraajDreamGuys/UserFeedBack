package co.in.dreamguys.feedback.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.in.dreamguys.feedback.user.adapter.CategoryProgressListAdapter;
import co.in.dreamguys.feedback.user.adapter.FilterListAdapter;
import co.in.dreamguys.feedback.user.helper.Constants;
import co.in.dreamguys.feedback.user.network.CategoryProgressListAPI;
import co.in.dreamguys.feedback.user.response.FilterResponse;
import co.in.dreamguys.feedback.user.response.SurveyListResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Prasad on 7/11/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    ListView listCategories;
    Toolbar mToolbar;
    ImageView inputFilter;
    CategoryProgressListAdapter aCategoryProgressListAdapter;
    FilterListAdapter aFilterListAdapter;
    LinearLayout inputSurveyOverviewLayout;
    List<SurveyListResponse.Datum> arrayCategoryList = new ArrayList<>();
    String restaurant_id = "1";
    List<FilterResponse.Datum> arrayFilter = new ArrayList<>();
    private SwipeRefreshLayout inputLayoutSwipeRefresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_progress);
        intiWidgets();
        arrayFilter = Constants.ArrayFilter;

        inputLayoutSwipeRefresh.setOnRefreshListener(this);
        inputLayoutSwipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                inputLayoutSwipeRefresh.setRefreshing(true);
                SurveyResults();
            }
        });

        if (arrayFilter.size() > 0) {
            aFilterListAdapter = new FilterListAdapter(MainActivity.this, arrayFilter);
            listCategories.setAdapter(aFilterListAdapter);
            aFilterListAdapter.notifyDataSetChanged();
        } else {
            SurveyResults();
        }
        inputFilter.setOnClickListener(this);

    }

    public void SurveyResults() {
        CategoryProgressListAPI.getInstance().Callresponse(restaurant_id, new Callback<SurveyListResponse.UserSurveyListResponse>() {
            @Override
            public void success(SurveyListResponse.UserSurveyListResponse userCategoryListResponse, Response response) {
                if (userCategoryListResponse.getStatus().equalsIgnoreCase("Y")) {
                    arrayCategoryList = userCategoryListResponse.getData();
                    if (arrayCategoryList.size() > 0) {
                        inputLayoutSwipeRefresh.setRefreshing(false);
                        aCategoryProgressListAdapter = new CategoryProgressListAdapter(MainActivity.this, arrayCategoryList);
                        listCategories.setAdapter(aCategoryProgressListAdapter);
                    } else {
                        Log.i("TAG", "empty list");
                    }
                    Log.i("TAG", arrayCategoryList.toString());
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void intiWidgets() {
        mToolbar = (Toolbar) findViewById(R.id.ATTB_toolbar);
        mToolbar.setTitle(getString(R.string.surveyOverview));
        mToolbar.setTitleTextColor(Color.WHITE);
        inputFilter = (ImageView) findViewById(R.id.input_filter);
        inputFilter.setVisibility(View.VISIBLE);
        listCategories = (ListView) findViewById(R.id.list_categories);
        inputLayoutSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.input_layout_swipe_refresh);
       /* listCategories.setBackground(getResources().getDrawable(R.color.colorBlack));*/
       /* inputSurveyOverviewLayout = (LinearLayout) findViewById(R.id.input_layout_survey_overview);*/
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.input_filter) {
            Intent filterActivity = new Intent(MainActivity.this, FilterList.class);
            startActivity(filterActivity);
            Constants.ArrayFilter.clear();
        }
    }

    @Override
    public void onRefresh() {
        SurveyResults();
    }
}
