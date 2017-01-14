package com.corebaseit.advancedgridviewjson.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.corebaseit.advancedgridviewjson.ListDetailActivity;
import com.corebaseit.advancedgridviewjson.R;
import com.corebaseit.advancedgridviewjson.models.ElsPoetesJsonDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vbevia on 14/05/16.
 */
public class ListRecycleAdapter extends RecyclerView.Adapter<ListRecycleAdapter.MyViewHolder> implements
        View.OnClickListener,
        View.OnLongClickListener {

    LayoutInflater inflater;
    private Context context;
    private List<ElsPoetesJsonDataModel> poetsList;
    private static final String enContextoImage_1 = "pictures/Poet-";

    public ListRecycleAdapter(FragmentActivity context, ArrayList<ElsPoetesJsonDataModel> modelJsonPoets) {
        this.context = context;
        this.poetsList = (modelJsonPoets == null ? new ArrayList<ElsPoetesJsonDataModel>() : modelJsonPoets);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = inflater.inflate(R.layout.list_row_item, parent, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final ElsPoetesJsonDataModel flower = poetsList.get(position);

        holder.bio.setText(Html.fromHtml(flower.getBio()));
        holder.name.setText(Html.fromHtml(flower.getName()));

        Glide.with(context)
                .load("file:///android_asset/"
                        + enContextoImage_1 + flower.getNumber()
                        + "-Menu@2x.jpg")
                .into(holder.imageView);

        holder.relativeItemView.setTag(position);
        holder.relativeItemView.setOnClickListener(this);
        holder.relativeItemView.setOnLongClickListener(this);
    }

    @Override
    public int getItemCount() {
        return poetsList.size();
    }

    @Override
    public void onClick(View view) {

        Integer id = (Integer) view.getTag();
        final ElsPoetesJsonDataModel flower = poetsList.get(id);

        Intent i = new Intent(context, ListDetailActivity.class);
        i.putExtra("imagePath", String.valueOf(flower.getNumber()));
        i.putExtra("textBelowImage", flower.getName());
        i.putExtra("textBio", flower.getBioExtended());

        context.startActivity(i);

        Log.d("LONGCLICK", "short click... " + flower.getBio());
    }

    @Override
    public boolean onLongClick(View view) {

        Log.d("LONGCLICK", "clicking long...");

        return false;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView bio;
        TextView name;
        RelativeLayout relativeItemView;

        public MyViewHolder(final View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image);
            relativeItemView = (RelativeLayout) itemView.findViewById(R.id.relativeItemView);

            bio = (TextView) itemView.findViewById(R.id.txv_row1);
            ((TextView) itemView.findViewById(R.id.txv_row1)).setTypeface(Typeface.
                    createFromAsset(itemView.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));

            name = (TextView) itemView.findViewById(R.id.txv_row2);
            ((TextView) itemView.findViewById(R.id.txv_row1)).setTypeface(Typeface.
                    createFromAsset(itemView.getContext().getAssets(), "fonts/Quattrocento Regular.ttf"));
        }
    }
}

