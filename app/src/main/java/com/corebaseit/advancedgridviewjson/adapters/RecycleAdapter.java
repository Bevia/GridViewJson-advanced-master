package com.corebaseit.advancedgridviewjson.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.corebaseit.advancedgridviewjson.R;
import com.corebaseit.advancedgridviewjson.models.ElsPoetesJsonDataModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vbevia on 14/05/16.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    LayoutInflater inflater;
    private Context context;
    private List<ElsPoetesJsonDataModel> poetsList;
    private static final String enContextoImage_1 = "pictures/Poet-";

    /**
     * get listener ready for fragment-------> START
     */
    // Define listener member variable...
    private static OnItemClickListener listener;

    // Define the listener interface...
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener...
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * get listener ready for fragment-------> END
     */

    public RecycleAdapter(FragmentActivity context, ArrayList<ElsPoetesJsonDataModel> modelJsonPoets) {
        this.context = context;
        this.poetsList = modelJsonPoets;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = inflater.inflate(R.layout.list_row_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final ElsPoetesJsonDataModel flower = poetsList.get(position);

        holder.bio.setText(Html.fromHtml(flower.getBio()));
        holder.name.setText(Html.fromHtml(flower.getName()));

        //progressView.setVisibility(View.VISIBLE);
        Picasso.with(context)
                .load("file:///android_asset/"
                        + enContextoImage_1 + flower.getNumber()
                        + "-Menu@2x.jpg")
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                       //you can set your progress indicator to stop here ;)
                    }

                    @Override
                    public void onError() {
                        //notify whatever error ;)
                    }
                });
    }

    @Override
    public int getItemCount() {
        return poetsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView bio;
        TextView name;

        public MyViewHolder(final View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image);

            bio = (TextView) itemView.findViewById(R.id.txv_row1);
            ((TextView) itemView.findViewById(R.id.txv_row1)).setTypeface(Typeface.
                    createFromAsset(itemView.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            name = (TextView) itemView.findViewById(R.id.txv_row2);
            ((TextView) itemView.findViewById(R.id.txv_row1)).setTypeface(Typeface.
                    createFromAsset(itemView.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }
}

