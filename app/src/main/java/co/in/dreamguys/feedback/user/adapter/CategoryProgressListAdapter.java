package co.in.dreamguys.feedback.user.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import co.in.dreamguys.feedback.user.R;
import co.in.dreamguys.feedback.user.model.DAOCategory;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by user5 on 06-07-2017.
 */

public class CategoryProgressListAdapter extends BaseAdapter {


    Context mContext;
    private List<DAOCategory> survey;
    LayoutInflater layoutInflater;


    public CategoryProgressListAdapter(Context mContext, List<DAOCategory> survey) {
        this.mContext = mContext;
        this.survey = survey;
        layoutInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return survey.size();
    }

    @Override
    public DAOCategory getItem(int position) {
        return survey.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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

        DAOCategory data = getItem(position);
        mHolder.inputCategory.setText(data.getCategoryName());
        mHolder.inputProgress.setProgress(Integer.parseInt(data.getIntPercentageValue()));


//        int colorValue = Color.parseColor(data.getIntColorValue());
//        mHolder.inputProgress.setBackgroundColor(colorValue);
        return convertView;
    }


    private class ViewHolder {
        TextView inputCategory;
        ProgressBar inputProgress;

    }

}
