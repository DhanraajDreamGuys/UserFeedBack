package co.in.dreamguys.feedback.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import co.in.dreamguys.feedback.user.helper.CustomProgressDialog;
import co.in.dreamguys.feedback.user.helper.SessionHandler;
import co.in.dreamguys.feedback.user.interfaces.SurveyListChanged;
import co.in.dreamguys.feedback.user.network.CategoryChartListAPI;
import co.in.dreamguys.feedback.user.network.NotesViewListAPI;
import co.in.dreamguys.feedback.user.response.NotesViewListResponse;
import co.in.dreamguys.feedback.user.response.SurveyListChartResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by user5 on 12-07-2017.
 */

public class ViewFeedbackList extends AppCompatActivity implements View.OnClickListener, SurveyListChanged {

    private LineChart lineChart;
    ListView NotesList;
    Toolbar mToolbar;
    LineDataSet lineDataSet;
    LinearLayout inputLayoutNotes;
    List<SurveyListChartResponse.Datum> arrayChartList = new ArrayList<>();
    List<NotesViewListResponse.Datum> arrayNotesList = new ArrayList<>();
    final ArrayList<Entry> entries = new ArrayList<Entry>();
    ArrayList<String> xVals = new ArrayList<>();
    ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
    NotesViewListAdapter aCategoryViewListAdapter;
    CustomProgressDialog mCustomProgressDialog;
    ImageView inputAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category);
        initWidgets();
        mCustomProgressDialog.showDialog();
        /*if (arrayNotesList.size() > 0) {
            NotesList.setAdapter(null);
            aCategoryViewListAdapter = new NotesViewListAdapter(ViewFeedbackList.this, arrayNotesList);
            NotesList.setAdapter(aCategoryViewListAdapter);
            aCategoryViewListAdapter.notifyDataSetChanged();
        } else {

            Log.i("TAG", "No Data");
        }*/
        NotesViewListAPI.getInstance().Callresponse(new Callback<NotesViewListResponse.UserNotesViewListResponse>() {

            @Override
            public void success(NotesViewListResponse.UserNotesViewListResponse userNotesViewListResponse, Response response) {
                if (userNotesViewListResponse.getStatus().equalsIgnoreCase("Y")) {
                    if (userNotesViewListResponse.getData() == null) {
                        Toast.makeText(ViewFeedbackList.this, "No data", Toast.LENGTH_SHORT).show();
                    } else {
                        arrayNotesList = userNotesViewListResponse.getData();
                        aCategoryViewListAdapter = new NotesViewListAdapter(ViewFeedbackList.this, arrayNotesList);
                        NotesList.setAdapter(aCategoryViewListAdapter);
                        aCategoryViewListAdapter.notifyDataSetChanged();
                        mCustomProgressDialog.dismiss();
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
                       /* mProgressDialog.show();*/
                        arrayChartList = userListChartResponse.getData();
                        for (int i = 0; i < arrayChartList.size(); i++) {
                            String data = arrayChartList.get(i).getCount();
                            xVals.add(arrayChartList.get(i).getNotes_date());
                            entries.add(new Entry(Integer.parseInt(data), i));
                        }

                        setData();
                        mCustomProgressDialog.dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mCustomProgressDialog.dismiss();
                        Toast.makeText(ViewFeedbackList.this, "No data", Toast.LENGTH_SHORT).show();
                    }
                });

        inputAdd.setOnClickListener(this);
        /*lineChart.setOnChartGestureListener(this);
        lineChart.setOnChartValueSelectedListener(this);*/

    }

    public void initWidgets() {
        mToolbar = (Toolbar) findViewById(R.id.ATTB_toolbar);
        mToolbar.setTitle(SessionHandler.getInstance().get(ViewFeedbackList.this, Constants.CATEGORYANSWER));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        mToolbar.setNavigationIcon(getDrawable(R.drawable.ic_back));
        mCustomProgressDialog = new CustomProgressDialog(this);
        setSupportActionBar(mToolbar);

        lineChart = (LineChart) findViewById(R.id.linechart);
        Legend l = lineChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);

        NotesList = (ListView) findViewById(R.id.input_notes);
        inputAdd = (ImageView) findViewById(R.id.input_add);
        inputLayoutNotes = (LinearLayout) findViewById(R.id.input_layout_notes);
        inputLayoutNotes.setVisibility(View.INVISIBLE);
        inputAdd.setVisibility(View.VISIBLE);
    }

    public void setData() {

        lineDataSet = new LineDataSet(entries, "Values");
        lineDataSet.setFillAlpha(110);
        lineDataSet.setColor(getResources().getColor(R.color.colorPrimary));
        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(5f);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setDrawVerticalHighlightIndicator(false);
        lineDataSet.setDrawVerticalHighlightIndicator(false);

        dataSets.add(lineDataSet);
        LineData linedata = new LineData(xVals, dataSets);
        lineChart.setData(linedata);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.setHorizontalScrollBarEnabled(true);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setClickable(false);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getXAxis().setAvoidFirstLastClipping(true);
        lineChart.getXAxis().removeAllLimitLines();
        lineChart.setDrawGridBackground(false);
        lineChart.setDrawBorders(false);


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextSize(4f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setSpaceBetweenLabels(1);
        xAxis.setXOffset(-5);
        xAxis.setDrawGridLines(false);
        Legend l = lineChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setForm(Legend.LegendForm.LINE);

        l.setTextSize(10f);
        l.setTextColor(Color.WHITE);
        l.setMaxSizePercent(0.10f);
        l.setXEntrySpace(3f);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        l.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);

    }


    @Override
    public void surveyNotify() {
        NotesList.setAdapter(null);
        List<NotesViewListResponse.Datum> arrayNotes = new ArrayList<>();
        if (arrayNotes.size() > 0) {
            aCategoryViewListAdapter = new NotesViewListAdapter(this, arrayNotes);
            NotesList.setAdapter(aCategoryViewListAdapter);
        } else {
            Toast.makeText(this, "No Project Found.", Toast.LENGTH_SHORT).show();
        }
        aCategoryViewListAdapter.notifyDataSetChanged();
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.input_add) {
            Intent addNote = new Intent(ViewFeedbackList.this, AddNote.class);
            startActivity(addNote);
        }
    }
}
