package com.example.tournament;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.Player;

public class AdapterTeam extends RecyclerView.Adapter<AdapterTeam.ViewHolder> {
    MyApplication app;
    private OnItemClickListener listener;
    public AdapterTeam(MyApplication app,OnItemClickListener listener){
        this.app = app;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_rowlayout, parent, false);
        AdapterTeam.ViewHolder viewHolder = new AdapterTeam.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Player tmp = app.getMyTeam().getPlayerAtPos(position);
        holder.txtHeader.setText(tmp.getNickname());
        holder.txtFirstName.setText(tmp.getFirstName());
        holder.txtLastName.setText(tmp.getLastName());
        //TODO image...
        /*
        if (position % 2 == 1){
            //holder.background.setBackgroundColor(Color.BLUE);
            holder.txtLastName.setTextColor(Color.WHITE);
            holder.txtFirstName.setTextColor(Color.WHITE);
            holder.txtHeader.setTextColor(Color.WHITE);
        }
        else{
            holder.background.setBackgroundColor(Color.WHITE);
            holder.txtLastName.setTextColor(Color.BLUE);
            holder.txtFirstName.setTextColor(Color.BLUE);
            holder.txtHeader.setTextColor(Color.BLUE);
        }*/
    }

    @Override
    public int getItemCount() {
        return app.myTeam.getSize();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFirstName;
        public TextView txtLastName;
        public ImageView iv;
        public View background;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHeader = (TextView) itemView.findViewById(R.id.firstLine);
            txtFirstName = (TextView) itemView.findViewById(R.id.secondLine);
            txtLastName = (TextView) itemView.findViewById(R.id.thirdLine);
            iv = (ImageView) itemView.findViewById(R.id.rowicon);
            background = itemView.findViewById(R.id.mylayoutrow);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        listener.onItemLongClick(itemView, position);
                    }
                    return false;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        listener.onItemClick(itemView, position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
        void onItemLongClick(View itemView, int position);
    }
}
