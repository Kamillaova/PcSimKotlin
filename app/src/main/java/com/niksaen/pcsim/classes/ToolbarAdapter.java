package com.niksaen.pcsim.classes;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.niksaen.pcsim.MainActivity;
import com.niksaen.pcsim.R;
import com.niksaen.pcsim.program.Program;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ToolbarAdapter  extends  RecyclerView.Adapter<ToolbarAdapter.ViewHolder> {

    Context context;
    MainActivity activity;
    public ToolbarAdapter(MainActivity activity){
        this.context = activity.getBaseContext();
        this.activity = activity;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_toolbar,null);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        if(activity.programArrayList.get(position).status == 0) {
            holder.app_icon.setImageResource(Program.programIcon.get(activity.programArrayList.get(position).Title));
            holder.app_name.setTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/pixelFont.ttf"));
            holder.app_name.setText(activity.words.get(activity.programArrayList.get(position).Title));
            holder.app_name.setTextColor(activity.styleSave.ToolbarTextColor);
            holder.itemView.setOnClickListener(v -> {activity.programArrayList.get(position).rollUpProgram(0);});
        }else{
            holder.app_icon.setVisibility(View.GONE);
            holder.app_name.setVisibility(View.GONE);
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setPadding(0,0,0,0);
        }
    }

    @Override
    public int getItemCount() {
        return activity.programArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView app_icon;
        public final TextView app_name;

        ViewHolder(View view) {
            super(view);
            app_icon = view.findViewById(R.id.app_icon);
            app_name = view.findViewById(R.id.app_name);
        }
    }
}
