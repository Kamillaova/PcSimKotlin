package com.niksaen.pcsim.program.notepad;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.niksaen.pcsim.R;

import dev.syorito_hatsuki.pcsim.init.InitFont;

public class NotepadSpinnerAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] objects;
    private final LayoutInflater layoutInflater;
    private final Typeface font;
    private final int weight = Typeface.BOLD;
    private final int resource;
    private String text;

    public NotepadSpinnerAdapter(@NonNull Context context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;
        layoutInflater=LayoutInflater.from(context);
        font = InitFont.INSTANCE.getPixelFont(context);
    }

    public NotepadSpinnerAdapter(@NonNull Context context, int resource, @NonNull String[] objects,String text) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;
        layoutInflater=LayoutInflater.from(context);
        font = Typeface.createFromAsset(context.getAssets(), "font/pixel.ttf");
        this.text = text;
    }

    @Override
    public int getCount(){ return objects.length; }

    @Override
    public int getPosition(@Nullable String item) { return super.getPosition(item); }

    @Nullable
    @Override
    public String getItem(int position) { return super.getItem(position); }

    public int TextColor = Color.parseColor("#000000");
    public int BackgroundColor;

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView = (TextView) layoutInflater.inflate(R.layout.item_textview,null);
        textView.setTypeface(font,weight);
        textView.setText(objects[position]);
        textView.setTextColor(TextColor);
        textView.setBackgroundColor(BackgroundColor);
        return textView;
    }


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView = (TextView) layoutInflater.inflate(R.layout.item_textview,null);
        textView.setTypeface(font,weight);
        textView.setTextColor(TextColor);
        textView.setBackgroundColor(Color.TRANSPARENT);
        if(text == null) {
            textView.setText(objects[position]);
        }
        else{
            textView.setText(text);
        }
        return textView;
    }

    public void setTextColor(String s) {
    }
}
