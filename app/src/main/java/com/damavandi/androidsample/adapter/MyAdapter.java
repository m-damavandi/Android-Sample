package com.damavandi.androidsample.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.damavandi.androidsample.R;
import com.damavandi.androidsample.network.modeles.ShowModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by arch on 1/2/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<ShowModel> showModelList;
    private Context context;
    private int displayWidth;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public MyViewHolder(View view) {
            super(view);
            LinearLayout.LayoutParams params = new
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(displayWidth*3/4));
            image = (ImageView) view.findViewById(R.id.image_view);
            image.setLayoutParams(params);

        }
    }

    public MyAdapter(List<ShowModel> showModelList, Context context) {
        this.showModelList = showModelList;
        this.context = context;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);
        displayWidth = displaymetrics.widthPixels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_row_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(context)
                .load(showModelList.get(position).getImage().getOriginal())
                .resize(displayWidth/2,displayWidth*3/4)
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return showModelList.size();
    }
}