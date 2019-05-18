package com.example.cs441_program8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TitleActivity extends AppCompatActivity {

    Button fightButton;
    Button magicButton;
    Button itemsButton;
    Button runButton;
    TextView playerAction;
    TextView monsterAction;
    float x, y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        playerAction = findViewById(R.id.youAct);
        monsterAction = findViewById(R.id.monstAct);
        fightButton = findViewById(R.id.fight);
        itemsButton = findViewById(R.id.item);
        magicButton = findViewById(R.id.magic);
        runButton = findViewById(R.id.run);
        x = getIntent().getExtras().getFloat("xcoor");
        y = getIntent().getExtras().getFloat("ycoor");

        fightButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playerAction.setText("You bit and scratched!");
            }
        });
        magicButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playerAction.setText("Magic Missile!");
            }
        });
        itemsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playerAction.setText("No items...");
            }
        });
        runButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                playerAction.setText("Got away safely!");
                Intent intent = new Intent(TitleActivity.this, MainActivity.class);

                intent.putExtra("xcoor", x);
                intent.putExtra("ycoor", y);
                intent.putExtra("done", true);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int xtuch = (int) motionEvent.getX();
        int ytuch = (int) motionEvent.getY();

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
}
