package co.in.dreamguys.feedback.user.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.in.dreamguys.feedback.user.R;
import co.in.dreamguys.feedback.user.network.NotesDeleteListAPI;
import co.in.dreamguys.feedback.user.response.NotesDeleteListResponse;
import co.in.dreamguys.feedback.user.response.NotesViewListResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder mHolder;
        final String NotesId;
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
        mHolder.inputDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                NotesDeleteListAPI.getInstance().Callresponse(data.getNotes_id(), new Callback<NotesDeleteListResponse.UserNotesDeleteListResponse>() {
                                    @Override
                                    public void success(NotesDeleteListResponse.UserNotesDeleteListResponse userNotesDeleteListResponse, Response response) {

                                    }

                                    @Override
                                    public void failure(RetrofitError error) {

                                    }
                                });
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.dismiss();
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
        /*mHolder.inputDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Are you sure you want to Delete?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                NotesDeleteListAPI.getInstance().Callresponse(((NotesViewListResponse.Datum) getItem(position)).getNotes_id(), new Callback<NotesDeleteListResponse.UserNotesDeleteListResponse>() {
                                    @Override
                                    public void success(NotesDeleteListResponse.UserNotesDeleteListResponse userNotesDeleteListResponse, Response response) {
                                        if (userNotesDeleteListResponse.getStatus().equalsIgnoreCase("Y")) {
                                            ((Activity) mContext).finish();
                                            *//*Intent intent = new Intent(mContext, ViewFeedbackList.class);
                                            mContext.startActivity(intent);*//*
                                            notifyDataSetChanged();
                                        }
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {

                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }

        });*/

        return convertView;
    }

    private class ViewHolder {
        TextView inputNotesName, inputNotesDesc;
        ImageView inputDelete;
    }
}
