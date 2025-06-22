package com.pavan.fly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private  boolean isMute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main)   ;
        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
        TextView highscoreTxt = findViewById(R.id.highScoreTxt);
        SharedPreferences prefs = getSharedPreferences("game",MODE_PRIVATE);
        highscoreTxt.setText("HighScore : "+prefs.getInt("highscore",0));
        isMute = prefs.getBoolean("isMute",false);

        ImageView volumeCtrl = findViewById(R.id.volume_control);
        if(isMute){
            volumeCtrl.setImageResource(R.drawable.volume_off);
        }else{
            volumeCtrl.setImageResource(R.drawable.volume_up);
        }
        volumeCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMute = !isMute;
                if(isMute){
                    volumeCtrl.setImageResource(R.drawable.volume_off);
                }else{
                    volumeCtrl.setImageResource(R.drawable.volume_up);
                }
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isMute",isMute);
                editor.apply();
            }
        });
    }
}