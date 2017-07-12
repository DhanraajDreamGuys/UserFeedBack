package co.in.dreamguys.feedback.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import co.in.dreamguys.feedback.user.helper.Constants;
import co.in.dreamguys.feedback.user.helper.SessionHandler;
import co.in.dreamguys.feedback.user.network.CategoryChartListAPI;
import co.in.dreamguys.feedback.user.response.CategoryListChartResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by user5 on 12-07-2017.
 */

public class CategoryViewList extends AppCompatActivity {

    private LineChart lineChart;
    LineDataSet lineDataSet;
    List<CategoryListChartResponse.Datum> arrayChartList = new ArrayList<>();
    final ArrayList<Entry> entries = new ArrayList<Entry>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category);


        CategoryChartListAPI.getInstance().Callresponse(SessionHandler.getInstance().get(CategoryViewList.this, Constants.RESTAURANT_ID),
                SessionHandler.getInstance().get(CategoryViewList.this, Constants.CATEGORYNAME), new Callback<CategoryListChartResponse.UserListChartResponse>() {
                    @Override
                    public void success(CategoryListChartResponse.UserListChartResponse userListChartResponse, Response response) {

                        arrayChartList = userListChartResponse.getData();
                        for (int i = 0; i < arrayChartList.size(); i++) {
                            String data = arrayChartList.get(i).getCount();
                            entries.add(new Entry(Integer.parseInt(data), i));
                        }
                        lineDataSet = new LineDataSet(entries, "Values");

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });

    }


    private void initWidgets() {
        lineChart = (LineChart) findViewById(R.id.linechart);
    }


}
