package com.niksaen.pcsim.program.taskManager;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.niksaen.pcsim.MainActivity;
import com.niksaen.pcsim.R;
import com.niksaen.pcsim.program.Program;

public class TaskManager extends Program {
    public TaskManager(MainActivity activity) {
        super(activity);
        Title = "Task Manager";
    }

    @Override
    public void initProgram() {
        initView();
        update();
        style();
        super.initProgram();
    }
    private TextView programName,useRam,useStream,useVideoMemory;
    private ListView programList;
    private LinearLayout main;
    private void initView(){
        mainWindow = LayoutInflater.from(activity.getBaseContext()).inflate(R.layout.program_taskmanager,null);
        titleTextView = mainWindow.findViewById(R.id.title);
        buttonClose = mainWindow.findViewById(R.id.close);
        buttonFullscreenMode = mainWindow.findViewById(R.id.fullscreenMode);
        buttonRollUp = mainWindow.findViewById(R.id.roll_up);
        programList = mainWindow.findViewById(R.id.app_list);
        main = mainWindow.findViewById(R.id.main);
        programName = mainWindow.findViewById(R.id.program_name);
        useRam = mainWindow.findViewById(R.id.ram_use);
        useVideoMemory = mainWindow.findViewById(R.id.video_memory_use);
        useStream = mainWindow.findViewById(R.id.stream_use);
    }
    private void style(){
        programName.setTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/pixelFont.ttf"));
        programName.setText(activity.words.get("Application"));
        programName.setTextColor(activity.styleSave.TextColor);

        useRam.setTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/pixelFont.ttf"));
        useRam.setText(activity.words.get("Memory"));
        useRam.setTextColor(activity.styleSave.TextColor);

        useStream.setTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/pixelFont.ttf"));
        useStream.setText(activity.words.get("Streams"));
        useStream.setTextColor(activity.styleSave.TextColor);

        useVideoMemory.setTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/pixelFont.ttf"));
        useVideoMemory.setText(activity.words.get("Video memory"));
        useVideoMemory.setTextColor(activity.styleSave.TextColor);

        mainWindow.setBackgroundColor(activity.styleSave.ColorWindow);
        main.setBackgroundColor(activity.styleSave.ThemeColor1);
        programList.setAdapter(adapter);
    }

    private TaskManagerAdapter adapter;
    public void update(){
        adapter = new TaskManagerAdapter(activity.getBaseContext(),0, activity.programArrayList);
        adapter.BackgroundColor = activity.styleSave.ThemeColor1;
        adapter.TextColor = activity.styleSave.TextColor;
        if(programList != null){
            programList.setAdapter(adapter);
        }
    }
}
