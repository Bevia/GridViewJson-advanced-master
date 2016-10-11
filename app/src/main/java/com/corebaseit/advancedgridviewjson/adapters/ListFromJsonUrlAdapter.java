package com.corebaseit.advancedgridviewjson.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.corebaseit.advancedgridviewjson.resttasks.ListFromJsonUrlTask;
import com.corebaseit.advancedgridviewjson.R;
import com.corebaseit.advancedgridviewjson.models.ListFromJsonUrlModel;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vincentbevia on 06/10/16.
 */

public class ListFromJsonUrlAdapter extends RecyclerView.Adapter<ListFromJsonUrlAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<ListFromJsonUrlModel> paymentHistoryModel = Collections.emptyList();
    private static RelativeLayout clickable_view;

    public ListFromJsonUrlAdapter(Context context, List<ListFromJsonUrlModel> paymentHistoryModel) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.paymentHistoryModel = (paymentHistoryModel == null ?
                new ArrayList<ListFromJsonUrlModel>() : paymentHistoryModel);
    }

    @Override
    public ListFromJsonUrlAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.payment_history_item_list, parent, false);

        return new ListFromJsonUrlAdapter.ViewHolder(itemView);
    }

    // Bind data
    @Override
    public void onBindViewHolder(ListFromJsonUrlAdapter.ViewHolder viewHolder, final int position) {

        final ListFromJsonUrlModel currentPosition = paymentHistoryModel.get(position);

        viewHolder.type.setText(currentPosition.getType());
        viewHolder.name.setText(currentPosition.getName());
        viewHolder.type.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));

        /*int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        clickable_view.setBackgroundResource(backgroundResource);*/

        //progressView.setVisibility(View.VISIBLE);
        Glide.with(context)
                .load(currentPosition.getImage())
                .fitCenter()
                .into(viewHolder.imageView);

        viewHolder.setClickListener(new ListFromJsonUrlAdapter.ViewHolder.ClickListener() {
            @Override
            public void onClick(View v, int pos, boolean isLongClick) {
                if (isLongClick) {
                    // View v at position pos is long-clicked.
                    Toast.makeText(context, "isLongClick " + position, Toast.LENGTH_SHORT).show();

                } else {
                    // View v at position pos is clicked.
                    Toast.makeText(context, "normalClick " + position + " ip: "
                            + currentPosition.getId(), Toast.LENGTH_SHORT).show();

                    URL url = getUrl();
                    ListFromJsonUrlTask task = new ListFromJsonUrlTask(url);
                    task.setCallable(context);
                    task.execute(currentPosition.getId());
                }
            }

            @Nullable
            private URL getUrl() {
                URL url = null;
                try {
                    url = new URL("http://geoip.xcounter.org/api/ip/85.248.227.165/json");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return url;
            }
        });
    }

    @Override
    public int getItemCount() {
        return paymentHistoryModel.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener,
            View.OnLongClickListener {

        private ListFromJsonUrlAdapter.ViewHolder.ClickListener clickListener;

        @BindView(R.id.gateway_logo)
        ImageView imageView;
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.name)
        TextView name;

        // create constructor to get widget reference
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            //Need this to make it clickable:
            clickable_view = (RelativeLayout) itemView.findViewById(R.id.clickable_view);
            clickable_view.setOnClickListener(this);
            clickable_view.setOnLongClickListener(this);

        }

        /* Interface for handling clicks - both normal and long ones. */
        public interface ClickListener {
            void onClick(View v, int position, boolean isLongClick);
        }

        public void setClickListener(ListFromJsonUrlAdapter.ViewHolder.ClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View v) {
            // If not long clicked, pass last variable as false.
            clickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            // If long clicked, passed last variable as true.
            clickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }
}