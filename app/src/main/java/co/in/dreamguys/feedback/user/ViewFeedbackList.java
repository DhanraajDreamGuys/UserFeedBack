package co.in.dreamguys.feedback.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import co.in.dreamguys.feedback.user.adapter.NotesViewListAdapter;
import co.in.dreamguys.feedback.user.helper.Constants;
import co.in.dreamguys.feedback.user.helper.SessionHandler;
import co.in.dreamguys.feedback.user.network.CategoryChartListAPI;
import co.in.dreamguys.feedback.user.network.NotesViewListAPI;
import co.in.dreamguys.feedback.user.response.SurveyListChartResponse;
import co.in.dreamguys.feedback.user.response.NotesViewListResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by user5 on 12-07-2017.
 */

public class ViewFeedbackList extends AppCompatActivity implements View.OnClickListener {

    private LineChart lineChart;
    ListView NotesList;
    LineDataSet lineDataSet;
    List<SurveyListChartResponse.Datum> arrayChartList = new ArrayList<>();
    List<NotesViewListResponse.Datum> arrayNotesList = new ArrayList<>();
    final ArrayList<Entry> entries = new ArrayList<Entry>();
    ArrayList<String> xVals = new ArrayList<>();
    ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
    NotesViewListAdapter aCategoryViewListAdapter;
    ProgressDialog mProgressDialog;
    ImageView inputAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category);
        lineChart = (LineChart) findViewById(R.id.linechart);
        NotesList = (ListView) findViewById(R.id.input_notes);
        inputAdd = (ImageView) findViewById(R.id.input_add);
        mProgressDialog = new ProgressDialog(ViewFeedbackList.this);
        mProgressDialog.setMessage("Loading...Please wait...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        inputAdd.setVisibility(View.VISIBLE);


        NotesViewListAPI.getInstance().Callresponse(new Callback<NotesViewListResponse.UserNotesViewListResponse>() {
            @Override
            public void success(NotesViewListResponse.UserNotesViewListResponse userNotesViewListResponse, Response response) {
                if (userNotesViewListResponse.getStatus().equalsIgnoreCase("Y")) {
                    if (userNotesViewListResponse.getData() == null) {
                        mProgressDialog.dismiss();
                        Toast.makeText(ViewFeedbackList.this, "No data", Toast.LENGTH_SHORT).show();
                    } else {
                        mProgressDialog.dismiss();
                        arrayNotesList = userNotesViewListResponse.getData();
                        aCategoryViewListAdapter = new NotesViewListAdapter(ViewFeedbackList.this, arrayNotesList);
                        NotesList.setAdapter(aCategoryViewListAdapter);
                        aCategoryViewListAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("TAG", "Error");
            }
        });

        CategoryChartListAPI.getInstance().Callresponse(SessionHandler.getInstance().get(ViewFeedbackList.this, Constants.RESTAURANT_ID),
                SessionHandler.getInstance().get(ViewFeedbackList.this, Constants.CATEGORYNAME), new Callback<SurveyListChartResponse.UserSurveyListChartResponse>() {
                    @Override
                    public void success(SurveyListChartResponse.UserSurveyListChartResponse userListChartResponse, Response response) {
                        mProgressDialog.show();
                        arrayChartList = userListChartResponse.getData();
                        for (int i = 0; i < arrayChartList.size(); i++) {
                            String data = arrayChartList.get(i).getCount();
                            xVals.add(arrayChartList.get(i).getNotes_date());
                            entries.add(new Entry(Integer.parseInt(data), i));
                        }
                        lineDataSet = new LineDataSet(entries, "Values");
                        lineDataSet.setFillAlpha(110);
                        lineDataSet.setColor(getResources().getColor(R.color.colorPrimary));
                        lineDataSet.setCircleColor(Color.WHITE);
                        lineDataSet.setLineWidth(1f);
                        lineDataSet.setCircleRadius(3f);
                        lineDataSet.setDrawCircleHole(false);
                        lineDataSet.setValueTextColor(Color.WHITE);
                        lineDataSet.setValueTextSize(9f);
                        lineDataSet.setDrawFilled(true);

                        dataSets.add(lineDataSet);
                        LineData linedata = new LineData(xVals, dataSets);
                        lineChart.setData(linedata);
                        lineChart.getAxisRight().setEnabled(false);
                        lineChart.getAxisLeft().setEnabled(false);
/*                        lineChart.setVisibleXRange(100,200);*/
                        XAxis xAxis = lineChart.getXAxis();
                        xAxis.setTextSize(10f);
                        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                        xAxis.setSpaceBetweenLabels(10);
                        xAxis.setTextColor(Color.WHITE);
                        mProgressDialog.dismiss();
                        Legend l = lineChart.getLegend();
                        l.setForm(Legend.LegendForm.LINE);
                        l.setFormSize(10f);
                        l.setTextColor(Color.WHITE);
                        l.setMaxSizePercent(0.95f);
                        l.setYEntrySpace(40f);


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(ViewFeedbackList.this, "No data", Toast.LENGTH_SHORT).show();
                    }
                });

        inputAdd.setOnClickListener(this);
        /*lineChart.setOnChartGestureListener(this);
        lineChart.setOnChartValueSelectedListener(this);*/

    }


    public void notesList() {


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.input_add) {
            Intent addNote = new Intent(ViewFeedbackList.this, AddNote.class);
            startActivity(addNote);
            finish();
        }
    }
}
