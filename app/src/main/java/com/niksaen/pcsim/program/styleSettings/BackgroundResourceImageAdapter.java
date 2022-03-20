package com.niksaen.pcsim.program.styleSettings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.niksaen.pcsim.R;

public class BackgroundResourceImageAdapter extends RecyclerView.Adapter<BackgroundResourceImageAdapter.ViewHolder> {

    private final int[] imageId = {
            R.drawable.background1,R.drawable.background2,R.drawable.background3,R.drawable.background4,R.drawable.background5,
            R.drawable.background6,R.drawable.background7,R.drawable.background8,R.drawable.background9,R.drawable.background10,
    };

    private final View testBackground;
    private final LayoutInflater layoutInflater;
    public BackgroundResourceImageAdapter(Context context, View testBackground){
        layoutInflater = LayoutInflater.from(context);
        this.testBackground = testBackground;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_image,viewGroup,false);
        return new ViewHolder(view);
    }

    public int currentImageId;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.imageView.setImageResource(imageId[i]);
        viewHolder.imageView.setOnClickListener(v -> {
            currentImageId = imageId[i];
            testBackground.setBackgroundResource(imageId[i]);
        });
    }

    @Override
    public int getItemCount() {
        return imageId.length;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}

