package co.in.dreamguys.feedback.user;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar mToolbar;
    ListView ASurveyOverview;
    ImageView inputFilter;
    TextView inputSkinTone, inputAge, inputDate, inputTitle;
    RadioGroup inputRadioGroup;
    SimpleDateFormat dateFormatter;
    DatePickerDialog inputDatePickerDialog;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_overview);
        initWidgets();
        setDateTimeField();
    }


    public void initWidgets() {
        mToolbar = (Toolbar) findViewById(R.id.ATTB_toolbar);
        mToolbar.setTitle(getString(R.string.surveyOverview));
        mToolbar.setTitleTextColor(Color.WHITE);

        inputFilter = (ImageView) findViewById(R.id.input_filter);
        inputFilter.setVisibility(View.VISIBLE);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        inputFilter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.input_filter) {
            showFilter();
        } else if (v.getId() == R.id.input_date) {
    /*        inputDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());*/
            inputDatePickerDialog.show();
        }
    }

    private void showFilter() {
        AlertDialog.Builder filter = new AlertDialog.Builder(this);
        View filterLayout = getLayoutInflater().inflate(R.layout.activity_filter_search, null);
        filter.setView(filterLayout);
        inputTitle = new TextView(this);
        inputTitle.setText(getString(R.string.filteryoursearch));
        inputTitle.setPadding(10, 30, 10, 10);
        inputTitle.setGravity(Gravity.CENTER);
        inputTitle.setTextColor(Color.BLACK);
        inputTitle.setTextSize(20);
        filter.setCustomTitle(inputTitle);
        inputSkinTone = (TextView) filterLayout.findViewById(R.id.input_skinTone);
        inputAge = (TextView) filterLayout.findViewById(R.id.input_Age);
        inputDate = (TextView) filterLayout.findViewById(R.id.input_date);
        inputRadioGroup = (RadioGroup) filterLayout.findViewById(R.id.input_radio_group);
        inputRadioGroup.clearCheck();
        inputRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton value = (RadioButton) group.findViewById(checkedId);
                if (null != value && checkedId > -1) {
                    Toast.makeText(MainActivity.this, value.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        inputDate.setOnClickListener(MainActivity.this);
        dialog = filter.create();
        dialog.show();
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        inputDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                inputDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

}
