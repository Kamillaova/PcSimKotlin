package com.niksaen.pcsim.program;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.pcsim.R;
import com.niksaen.pcsim.classes.AssetFile;
import com.niksaen.pcsim.classes.PortableView;
import com.niksaen.pcsim.save.Language;
import com.niksaen.pcsim.save.PcParametersSave;
import com.niksaen.pcsim.save.StyleSave;

import java.util.HashMap;

public class Browser {
    View mainWindow;
    PcParametersSave pcParametersSave;
    StyleSave styleSave;
    Context context;

    ConstraintLayout layout;

    Typeface font;
    String titleText;

    public Browser(Context context, PcParametersSave pcParametersSave,ConstraintLayout layout){
        this.context = context;
        this.layout = layout;
        this.pcParametersSave = pcParametersSave;
        styleSave = new StyleSave(context);
        mainWindow = LayoutInflater.from(context).inflate(R.layout.program_browser,null);
        font = Typeface.createFromAsset(context.getAssets(), "fonts/pixelFont.ttf");
        style();
    }
    int i=0;
    public void openProgram(){
        WebView webView = mainWindow.findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://google.com/");
        webView.getSettings().setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                webView.clearCache(true);
                super.onPageFinished(view, url);
            }
        });
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);


        close.setOnClickListener(v -> closeProgram());
        fullscreenMode.setOnClickListener(v -> {
            if(i==0){
                mainWindow.setScaleX(0.6f);
                mainWindow.setScaleY(0.6f);
                PortableView portableView = new PortableView(mainWindow);
                v.setBackgroundResource(fullscreenMode1);
                i++;
            }else{
                mainWindow.setScaleX(1);
                mainWindow.setScaleY(1);
                mainWindow.setX(0);
                mainWindow.setY(0);
                mainWindow.setOnTouchListener(null);
                v.setBackgroundResource(fullscreenMode2);
                i=0;
            }
        });
        layout.addView(mainWindow, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    TextView title;
    Button close,fullscreenMode,rollUp;
    int fullscreenMode1,fullscreenMode2;
    private void style(){
        languageSettings();
        title=mainWindow.findViewById(R.id.title);
        close = mainWindow.findViewById(R.id.close);
        fullscreenMode = mainWindow.findViewById(R.id.fullscreenMode);
        rollUp = mainWindow.findViewById(R.id.roll_up);

        title.setTypeface(font,Typeface.BOLD);
        title.setText(titleText);
        title.setTextColor(styleSave.TitleColor);

        mainWindow.setBackgroundColor(styleSave.ColorWindow);

        close.setBackgroundResource(styleSave.CloseButtonImageRes);

        fullscreenMode1 = styleSave.FullScreenMode1ImageRes;
        fullscreenMode2 = styleSave.FullScreenMode2ImageRes;
        fullscreenMode.setBackgroundResource(fullscreenMode2);

        rollUp.setBackgroundResource(styleSave.RollUpButtonImageRes);
    }
    public void closeProgram(){
        mainWindow.setVisibility(View.GONE);
        mainWindow = null;
    }

    private void languageSettings(){
        TypeToken<HashMap<String,String>> typeToken = new TypeToken<HashMap<String,String>>(){};
        HashMap<String,String> buff = new HashMap<>();
        buff = new Gson().fromJson(new AssetFile(context).getText("language/"+Language.getLanguage(context)+".json"),typeToken.getType());
        titleText = buff.get("Browser");
    }
}
