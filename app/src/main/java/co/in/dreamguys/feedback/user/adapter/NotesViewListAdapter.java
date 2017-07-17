package co.in.dreamguys.feedback.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.in.dreamguys.feedback.user.R;
import co.in.dreamguys.feedback.user.response.NotesViewListResponse;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by user5 on 17-07-2017.
 */

public class NotesViewListAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater layoutInflater;
    private List<NotesViewListResponse.Datum> Notes;

    public NotesViewListAdapter(Context mContext, List<NotesViewListResponse.Datum> Notes) {
        this.mContext = mContext;
        this.Notes = Notes;
        layoutInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override

    public int getCount() {
        return Notes.size();
    }

    @Override
    public Object getItem(int position) {
        return Notes.get(position);
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
            convertView = layoutInflater.inflate(R.layout.adapter_add_note, null);
            mHolder.inputNotesName = (TextView) convertView.findViewById(R.id.input_Name);
            mHolder.inputNotesDesc = (TextView) convertView.findViewById(R.id.input_Desc);
            mHolder.inputDelete = (ImageView) convertView.findViewById(R.id.input_delete);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        final NotesViewListResponse.Datum data = (NotesViewListResponse.Datum) getItem(position);
        mHolder.inputNotesName.setText(data.getCategory_name());
        mHolder.inputNotesDesc.setText(data.getNotes());


        return convertView;
    }

    private class ViewHolder {
        TextView inputNotesName, inputNotesDesc;
        ImageView inputDelete;
    }
}
