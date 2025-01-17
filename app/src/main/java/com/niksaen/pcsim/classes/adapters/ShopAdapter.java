package com.niksaen.pcsim.classes.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.pcsim.R;
import com.niksaen.pcsim.classes.AssetFile;
import com.niksaen.pcsim.save.Settings;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

import dev.syorito_hatsuki.pcsim.init.InitFont;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private final ArrayList<String> strings;
    private final Context context;
    private final String type;
    private final Typeface font;

    public String getType(){
        return this.type;
    }
    public String getItem(int position){ return this.strings.get(position); }

    public ShopAdapter(Context context, ArrayList<String> strings,String type){
        this.strings = strings;
        this.context = context;
        this.type = type;
        font = InitFont.INSTANCE.getPixelFont(context);
        getLanguage();
    }

    private HashMap<String,String> words;
    private void getLanguage(){
        TypeToken<HashMap<String,String>> typeToken = new TypeToken<HashMap<String,String>>(){};
        words = new Gson().fromJson(new AssetFile(context).getText("language/"+new Settings(context).Language+".json"),typeToken.getType());
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shop,null));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ShopAdapter.ViewHolder holder, int position) {
        setStyle(holder);
        holder.text.setTypeface(font,Typeface.BOLD);
        if(type != "icon") {
            holder.text.setText(strings.get(position));
            holder.icon.setImageDrawable(new AssetFile(context).getImage("pc_component/images/" + type + "/" + strings.get(position) + ".png"));
        }else{
            holder.text.setText(words.get(getItem(position)));
            holder.icon.setImageDrawable(new AssetFile(context).getImage("icons/shop/" +new Settings(context).Theme+ "/" + strings.get(position) + ".png"));
        }
    }

    private void setStyle(ShopAdapter.ViewHolder holder){
        if(new Settings(context).Theme == "Dark"){
            holder.text.setTextColor(Color.WHITE);
        }
        else {
            holder.text.setTextColor(Color.BLACK);
        }
    }
    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text;
        public ViewHolder(View item){
            super(item);
            icon = item.findViewById(R.id.icon);
            text = item.findViewById(R.id.text);
        }
    }
}
