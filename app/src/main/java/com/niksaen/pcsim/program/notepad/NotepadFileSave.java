package com.niksaen.pcsim.program.notepad;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.pcsim.MainActivity;
import com.niksaen.pcsim.R;
import com.niksaen.pcsim.classes.AssetFile;
import com.niksaen.pcsim.classes.FileUtil;
import com.niksaen.pcsim.classes.PortableView;
import com.niksaen.pcsim.program.Program;
import com.niksaen.pcsim.program.fileManager.FileManagerListViewAdapter;
import com.niksaen.pcsim.save.Language;
import com.niksaen.pcsim.save.StyleSave;

import java.util.ArrayList;
import java.util.HashMap;

public class NotepadFileSave extends Program {

    Context context;
    View fileSaveWindow;
    Typeface font;
    StyleSave styleSave;
    MainActivity mainActivity;

    public NotepadFileSave(MainActivity activity){
        super(activity);
        this.Title = "Saving a file";
        this.context = activity.getBaseContext();
        mainActivity = activity;

        fileSaveWindow = LayoutInflater.from(context).inflate(R.layout.program_for_save_file,null);
        font = Typeface.createFromAsset(context.getAssets(),"fonts/pixelFont.ttf");
        styleSave = new StyleSave(context);
    }

    String buffPath = "",buffPath2 = "";

    TextView title;
    Button saveButton,pageDown;
    EditText fileName;
    ListView listView;
    Button fullscreen,close;

    private void initView(){
        title = fileSaveWindow.findViewById(R.id.title);
        saveButton = fileSaveWindow.findViewById(R.id.button4);
        fileName = fileSaveWindow.findViewById(R.id.fileName);
        listView = fileSaveWindow.findViewById(R.id.listView);
        pageDown = fileSaveWindow.findViewById(R.id.pageDown);
        fullscreen = fileSaveWindow.findViewById(R.id.fullscreenMode);
        close = fileSaveWindow.findViewById(R.id.close);
    }

    FileManagerListViewAdapter listViewAdapter;
    ArrayList<String> folders;
    HashMap<String,String> words;
    private void style(){
        fileName.setTypeface(font,Typeface.BOLD);
        title.setTypeface(font,Typeface.BOLD);
        title.setTextColor(styleSave.TitleColor);
        saveButton.setTypeface(font,Typeface.BOLD);
        saveButton.setBackgroundColor(styleSave.ThemeColor2);
        saveButton.setTextColor(styleSave.TextButtonColor);
        fileSaveWindow.setBackgroundColor(styleSave.ColorWindow);
        close.setBackgroundResource(styleSave.CloseButtonImageRes);
        fullscreen.setBackgroundResource(styleSave.FullScreenMode2ImageRes);
        fileName.setTextColor(styleSave.TextColor);
        fileName.setBackgroundColor(styleSave.ThemeColor2);
        fileName.setHintTextColor(styleSave.TextColor);
        pageDown.setBackgroundResource(styleSave.ArrowButtonImage);

        //adapter settings
        folders = new ArrayList<>();
        FileUtil.listDir("//storage/emulated/0/",folders);
        listViewAdapter = new FileManagerListViewAdapter(context,R.layout.item_textview,folders);
        listViewAdapter.ColorBackground = styleSave.ThemeColor1;
        listViewAdapter.ColorText = styleSave.TextColor;
        listView.setAdapter(listViewAdapter);
        listView.setBackgroundColor(styleSave.ThemeColor1);

        //set text
        words = new Gson().fromJson(new AssetFile(context).getText("language/"+ Language.getLanguage(context)+".json"),new TypeToken<HashMap<String,String>>(){}.getType());
        saveButton.setText(words.get("Save"));
        title.setText(words.get("Saving a file"));
        fileName.setHint(words.get("Enter file name")+":");
    }

    View buff;
    public void openProgram(final String textFile){
        if(status == -1) {
            super.openProgram();
            initView();
            style();

            listView.setOnItemClickListener((parent, view, position, id) -> {
                if (FileUtil.isDirectory(folders.get(position))) {
                    buffPath = folders.get(position);
                    buffPath2 = folders.get(position) + "/";
                    FileUtil.listDir(folders.get(position), folders);
                    ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                } else if (folders.get(position).endsWith(".txt")) {
                    if (buff != null) {
                        buff.setBackgroundColor(styleSave.ThemeColor1);
                    }
                    view.setBackgroundColor(styleSave.ThemeColor2);
                    buff = view;
                    buffPath = folders.get(position);
                    fileName.setText(buffPath.substring(buffPath.lastIndexOf("/") + 1).replace(".txt", ""));
                }
            });

            saveButton.setOnClickListener(v -> {
                if (FileUtil.isDirectory(buffPath)) {
                    if (fileName.getText() != null) {
                        FileUtil.writeFile(buffPath + fileName.getText().toString() + ".txt", textFile);
                        fileName.setText("");
                        FileUtil.listDir(buffPath, folders);
                        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                        closeProgram(1);
                    }
                } else if (buffPath.endsWith(".txt")) {
                    if (fileName.getText() != null) {
                        FileUtil.writeFile(buffPath, textFile);
                        FileUtil.listDir(buffPath, folders);
                        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                        closeProgram(1);
                    }
                }
            });

            pageDown.setOnClickListener(v -> {
                if (buffPath2.contains("/storage/emulated/0/") && buffPath2 != "/storage/emulated/0/") {
                    v.setVisibility(View.VISIBLE);
                    buffPath2 = buffPath2.substring(0, buffPath2.lastIndexOf("/"));
                    FileUtil.listDir(buffPath2, folders);
                    ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                }
            });

            final int[] button2ClickCount = {0};

            fullscreen.setOnClickListener(v -> {
                if (button2ClickCount[0] == 0) {
                    fullscreen.setBackgroundResource(R.drawable.button_2_1_color17);
                    fileSaveWindow.setScaleX(0.7f);
                    fileSaveWindow.setScaleY(0.7f);
                    fileSaveWindow.setX(0f);
                    fileSaveWindow.setY(0f);
                    PortableView portableView = new PortableView(fileSaveWindow);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        fileSaveWindow.setZ(0f);
                    }
                    button2ClickCount[0]++;
                } else {
                    fullscreen.setBackgroundResource(R.drawable.button_2_2_color17);
                    fileSaveWindow.setScaleX(1);
                    fileSaveWindow.setScaleY(1);
                    fileSaveWindow.setX(0);
                    fileSaveWindow.setY(0);
                    fileSaveWindow.setOnTouchListener(null);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        fileSaveWindow.setZ(10f);
                    }
                    button2ClickCount[0] = 0;
                }
            });
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closeProgram(1);
                }
            });
            if (fileSaveWindow.getParent() == null) {
                mainActivity.layout.addView(fileSaveWindow, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            } else {
                fileSaveWindow.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void closeProgram(int mode){
        super.closeProgram(mode);
        fileSaveWindow.setVisibility(View.GONE);
    }
}
