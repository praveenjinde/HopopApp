package com.hopop.hopop.source;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hopop.hopop.database.FromRoute;
import com.hopop.hopop.ply.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by praveen on 6/4/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DataObjectHolder> {
    private List<FromRoute> fromRoutes;
    private Context context;
    static ItemClickListenr itemClickListenr;


    public RecyclerAdapter(List<FromRoute> fromRoutes,Context context){
        this.fromRoutes = fromRoutes;
        this.context = context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.route_item, viewGroup, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder dataObjectHolder, int position) {

        dataObjectHolder.tv_entity_name.setText(fromRoutes.get(position).getStopLocation());

    }

    @Override
    public int getItemCount() {
        return fromRoutes.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener{
        @Bind(R.id.tv_entity_name)
        TextView tv_entity_name;
        public DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Log.i(getClass().getSimpleName(), "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListenr.onItemClick(getAdapterPosition(),view);
        }
    }

    public void setOnItemClickListener(ItemClickListenr itemClickListenr) {
        this.itemClickListenr = itemClickListenr;
    }
    public interface ItemClickListenr {
         void onItemClick(int position, View v);
    }
}
