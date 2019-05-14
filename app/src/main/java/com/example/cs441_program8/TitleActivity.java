package com.example.cs441_program8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int xtuch = (int) motionEvent.getX();
        int ytuch = (int) motionEvent.getY();

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            Intent intent = new Intent(TitleActivity.this, MainActivity.class);
            intent.putExtra("x-coor", getIntent().getExtras().getString("x-coor"));
            intent.putExtra("y-coor", getIntent().getExtras().getString("y-coor"));
        }
        return true;
    }
}
