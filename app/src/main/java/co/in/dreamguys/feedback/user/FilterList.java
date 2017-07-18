package co.in.dreamguys.feedback.user;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import co.in.dreamguys.feedback.user.helper.Constants;
import co.in.dreamguys.feedback.user.helper.SessionHandler;
import co.in.dreamguys.feedback.user.network.FilterResponseAPI;
import co.in.dreamguys.feedback.user.network.GetSkinToneAPI;
import co.in.dreamguys.feedback.user.response.FilterResponse;
import co.in.dreamguys.feedback.user.response.SkinToneResponse;
import co.in.dreamguys.feedback.user.response.SurveyListResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FilterList extends AppCompatActivity implements View.OnClickListener {

    Toolbar mToolbar;
    ListView ASurveyOverview;

    TextView inputSkinTone, inputDate, inputTitle;
    RadioGroup inputRadioGroup;
    Button inputFilter, inputReset;
    EditText inputAge;
    SimpleDateFormat dateFormatter;
    DatePickerDialog inputDatePickerDialog;
    List<SkinToneResponse.Datum> arraySkinTone = new ArrayList<>();
    List<FilterResponse.Datum> arrayFilterList = new ArrayList<>();
    List<SurveyListResponse.UserSurveyListResponse> arraySurveyList = new ArrayList<>();
    private AlertDialog mSkinToneDialog;
    SkinToneAdapter skinToneAdapter;
    ListView inputSkinToneList;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_search);
        initWidgets();
        setDateTimeField();


        GetSkinToneAPI.getInstance().Callresponse(SessionHandler.getInstance().get(FilterList.this, Constants.RESTAURANT_ID), new Callback<SkinToneResponse.UserSkinToneResponse>() {
            @Override
            public void success(SkinToneResponse.UserSkinToneResponse userSkinToneResponse, Response response) {
                if (userSkinToneResponse.getStatus().equalsIgnoreCase("Y")) {
                    arraySkinTone = userSkinToneResponse.getData();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }


    public void initWidgets() {
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        inputSkinTone = (TextView) findViewById(R.id.input_skinTone);
        inputAge = (EditText) findViewById(R.id.input_Age);
        inputDate = (TextView) findViewById(R.id.input_date);
        inputRadioGroup = (RadioGroup) findViewById(R.id.input_radio_group);
        inputFilter = (Button) findViewById(R.id.input_Btn_filter);
        inputReset = (Button) findViewById(R.id.input_Btn_reset);
        inputRadioGroup.clearCheck();
        inputRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton value = (RadioButton) group.findViewById(checkedId);
                if (null != value && checkedId > -1) {
                    String user = (String) value.getText();
                    if (user.equalsIgnoreCase("male")) {
                        gender = "m";
                    } else {
                        gender = "f";
                    }
                }
            }
        });
        inputSkinTone.setOnClickListener(this);
        inputDate.setOnClickListener(this);
        inputFilter.setOnClickListener(this);
        inputReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.input_skinTone) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(FilterList.this);
            View mShowAddProjectView = getLayoutInflater().inflate(R.layout.activity_list_survey, null);
            mBuilder.setView(mShowAddProjectView);
            mToolbar = (Toolbar) mShowAddProjectView.findViewById(R.id.ATTB_toolbar);
            mToolbar.setVisibility(View.GONE);
            inputSkinToneList = (ListView) mShowAddProjectView.findViewById(R.id.list_categories);
            inputSkinToneList.setBackground(getResources().getDrawable(R.color.colorWhite));
            skinToneAdapter = new SkinToneAdapter(FilterList.this, arraySkinTone);
            inputSkinToneList.setAdapter(skinToneAdapter);
            mSkinToneDialog = mBuilder.create();
            mSkinToneDialog.show();
        } else if (v.getId() == R.id.input_date) {
    /*        inputDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());*/
            inputDatePickerDialog.show();
        } else if (v.getId() == R.id.input_Btn_filter) {
            FilterResponseAPI.getInstance().Callresponse(SessionHandler.getInstance().get(FilterList.this, Constants.RESTAURANT_ID), gender,
                    inputAge.getText().toString(), inputSkinTone.getText().toString(), new Callback<FilterResponse.UserFilterResponse>() {
                        @Override
                        public void success(FilterResponse.UserFilterResponse userFilterResponse, Response response) {
                            if (userFilterResponse.getStatus().equalsIgnoreCase("Y")) {
                                if (userFilterResponse.getData() == null) {
                                    Toast.makeText(FilterList.this, "No data", Toast.LENGTH_SHORT).show();
                                } else {
                                    arrayFilterList = userFilterResponse.getData();
                                    Constants.ArrayFilter = arrayFilterList;
                                    Intent filterData = new Intent(FilterList.this, MainActivity.class);
                                    startActivity(filterData);
                                    finish();
                                    Log.i("TAG", arraySurveyList.toString());
                                }
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
        } else if (v.getId() == R.id.input_Btn_reset) {
            inputRadioGroup.clearCheck();
            inputSkinTone.setText("");
            inputDate.setText("");
            inputAge.getText().clear();
        }
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


    public class SkinToneAdapter extends BaseAdapter {
        LayoutInflater inflater;
        Context mContext;
        private List<SkinToneResponse.Datum> SkinTone;

        public SkinToneAdapter(Context mContext, List<SkinToneResponse.Datum> SkinTone) {
            this.mContext = mContext;
            this.SkinTone = SkinTone;
            inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return SkinTone.size();
        }

        @Override
        public Object getItem(int position) {
            return SkinTone.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder mHolder;
            if (convertView == null) {
                mHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.activity_list_adapter, null);
                mHolder.inputName = (TextView) convertView.findViewById(R.id.input_name);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) convertView.getTag();
            }
            final SkinToneResponse.Datum data = (SkinToneResponse.Datum) getItem(position);
            mHolder.inputName.setText(data.getSkin_tone());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inputSkinTone.setText(((SkinToneResponse.Datum) getItem(position)).getSkin_tone());
                    mSkinToneDialog.dismiss();
                }
            });

            return convertView;
        }


        class ViewHolder {
            TextView inputName;
        }

    }


}
