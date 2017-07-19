package co.in.dreamguys.feedback.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import co.in.dreamguys.feedback.user.R;
import co.in.dreamguys.feedback.user.ViewFeedbackList;
import co.in.dreamguys.feedback.user.helper.Constants;
import co.in.dreamguys.feedback.user.helper.SessionHandler;
import co.in.dreamguys.feedback.user.response.FilterResponse;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by user5 on 18-07-2017.
 */

public class FilterListAdapter extends BaseAdapter {
    Context mContext;
    private List<FilterResponse.Datum> Filter;
    LayoutInflater layoutInflater;

    public FilterListAdapter(Context mContext, List<FilterResponse.Datum> Filter) {
        this.mContext = mContext;
        this.Filter = Filter;
        layoutInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Filter.size();
    }

    @Override
    public Object getItem(int position) {
        return Filter.get(position);
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
            mHolder.inputPercentage = (TextView) convertView.findViewById(R.id.input_percentage);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        final FilterResponse.Datum data = (FilterResponse.Datum) getItem(position);
        int progress = (int) Math.round(Double.valueOf(data.getPercentage()));
        mHolder.inputCategory.setText(data.getAnswer());
        mHolder.inputProgress.setProgress(progress);
        mHolder.inputPercentage.setText(String.valueOf(progress) + "%");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewCategory = new Intent(mContext, ViewFeedbackList.class);
                SessionHandler.getInstance().save(mContext, Constants.CATEGORYNAME, data.getAnswer());
                mContext.startActivity(viewCategory);
               /* ((Activity) mContext).finish();*/
            }
        });
        return convertView;
    }

    private class ViewHolder {
        TextView inputCategory, inputPercentage;
        ProgressBar inputProgress;

    }
}
