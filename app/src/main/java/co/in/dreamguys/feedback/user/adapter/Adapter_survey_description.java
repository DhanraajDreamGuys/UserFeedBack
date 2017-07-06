package co.in.dreamguys.feedback.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;

import co.in.dreamguys.feedback.user.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by user5 on 06-07-2017.
 */

public class Adapter_survey_description extends BaseAdapter {


    Context mContext;
    private final JSONArray survey;
    LayoutInflater layoutInflater;


    public Adapter_survey_description(Context mContext, JSONArray survey) {
        this.mContext = mContext;
        this.survey = survey;
        layoutInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return survey.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adapter_survey_description, null);
            mHolder.inputCategory = (TextView) convertView.findViewById(R.id.input_category);
            mHolder.inputProgress = (ProgressBar) convertView.findViewById(R.id.progressBar);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        mHolder.inputProgress.setProgress(10);
        return convertView;
    }


    class ViewHolder {
        TextView inputCategory;
        ProgressBar inputProgress;

    }

}
