package co.in.dreamguys.feedback.user;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import co.in.dreamguys.feedback.user.helper.Constants;
import co.in.dreamguys.feedback.user.helper.SessionHandler;
import co.in.dreamguys.feedback.user.network.AddNotesResponseAPI;
import co.in.dreamguys.feedback.user.network.CategoryListAPI;
import co.in.dreamguys.feedback.user.response.AddNotesResponse;
import co.in.dreamguys.feedback.user.response.NotesCategoryListResponse;
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
    Button inputAdd;
    Toolbar mToolbar;
    NotesCategoryListAdapter aNotesCategoryListAdapter;
    List<NotesCategoryListResponse.Datum> arrayCategoryList = new ArrayList<>();
    List<String> CategoryList = new ArrayList<>();
    private AlertDialog mCategoryDialog;
    ListView inputCategoryList;
    SimpleDateFormat dateFormatter;
    DatePickerDialog inputDatePickerDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initWidgets();
        setDateTimeField();

        CategoryListAPI.getInstance().Callresponse(new Callback<NotesCategoryListResponse.UserNotesCategoryListResponse>() {
            @Override
            public void success(NotesCategoryListResponse.UserNotesCategoryListResponse userCategoryListResponse, Response response) {
                if (userCategoryListResponse.getStatus().equalsIgnoreCase("Y")) {
                    arrayCategoryList = userCategoryListResponse.getData();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        inputCategory.setOnClickListener(this);
        inputDate.setOnClickListener(this);
        inputAdd.setOnClickListener(this);

    }


    public void initWidgets() {
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        inputCategory = (TextView) findViewById(R.id.input_categoryList);
        inputDate = (TextView) findViewById(R.id.input_Date);
        inputComments = (EditText) findViewById(R.id.input_comments);
        inputAdd = (Button) findViewById(R.id.input_Btn_Add);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.input_categoryList) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddNote.this);
            View mShowAddProjectView = getLayoutInflater().inflate(R.layout.activity_list_survey, null);
            mBuilder.setView(mShowAddProjectView);
            mToolbar = (Toolbar) mShowAddProjectView.findViewById(R.id.ATTB_toolbar);
            mToolbar.setVisibility(View.GONE);
            inputCategoryList = (ListView) mShowAddProjectView.findViewById(R.id.list_categories);
            inputCategoryList.setBackground(getResources().getDrawable(R.color.colorWhite));
            aNotesCategoryListAdapter = new NotesCategoryListAdapter(AddNote.this, arrayCategoryList);
            inputCategoryList.setAdapter(aNotesCategoryListAdapter);
            mCategoryDialog = mBuilder.create();
            mCategoryDialog.show();
        } else if (v.getId() == R.id.input_Date) {
            inputDatePickerDialog.show();
        } else if (v.getId() == R.id.input_Btn_Add) {
            AddNotesResponseAPI.getInstance().Callresponse(SessionHandler.getInstance().get(AddNote.this, Constants.RESTAURANT_ID),
                    inputCategory.getText().toString(), inputDate.getText().toString(), inputComments.getText().toString(), new Callback<AddNotesResponse.UserAddNotesResponse>() {
                        @Override
                        public void success(AddNotesResponse.UserAddNotesResponse userAddNotesResponse, Response response) {
                            if (userAddNotesResponse.getStatus().equalsIgnoreCase("Y")) {
                                Intent surveyList = new Intent(AddNote.this, ViewFeedbackList.class);
                                startActivity(surveyList);
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
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

    public class NotesCategoryListAdapter extends BaseAdapter {
        LayoutInflater inflater;
        Context mContext;
        private List<NotesCategoryListResponse.Datum> SkinTone;

        public NotesCategoryListAdapter(Context mContext, List<NotesCategoryListResponse.Datum> SkinTone) {
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
            final NotesCategoryListResponse.Datum data = (NotesCategoryListResponse.Datum) getItem(position);
            mHolder.inputName.setText(data.getCategory_name());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inputCategory.setText(((NotesCategoryListResponse.Datum) getItem(position)).getCategory_name());
                    Constants.CATEGORYID = ((NotesCategoryListResponse.Datum) getItem(position)).getId();
                    mCategoryDialog.dismiss();
                }
            });
            return convertView;
        }


        class ViewHolder {
            TextView inputName;
        }

    }
}
