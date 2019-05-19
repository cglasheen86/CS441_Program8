package com.example.cs441_program8;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TitleActivity extends AppCompatActivity {

    Button fightButton;
    Button magicButton;
    Button itemsButton;
    Button runButton;
    TextView playerAction;
    TextView monsterAction;
    TextView healthView;
    int monstHealth = 50;
    int health;
    float x, y;
    boolean done = false;
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
        healthView = findViewById(R.id.healthView);
        health = getIntent().getExtras().getInt("health");
        healthView.setText("Health: " + health);
        x = getIntent().getExtras().getFloat("xcoor");
        y = getIntent().getExtras().getFloat("ycoor");

        fightButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!done) {
                    playerAction.setText("You did damage");
                    monsterAction.setText("Monster did 10 damage");
                    health -= 10;
                    monstHealth -= 10;
                    if (monstHealth <= 0){
                        done = true;
                        monsterAction.setText("You won!");
                        playerAction.setText("");
                        ImageView img = findViewById(R.id.imageView);
                        img.setVisibility(View.INVISIBLE);
                    }
                    healthUpdate();
                }
            }
        });
        magicButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!done){playerAction.setText("Magic Missile!");}
            }
        });
        itemsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!done)playerAction.setText("No items...");
            }
        });
        runButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!done) {
                    playerAction.setText("Got away safely!");
                    Intent intent = new Intent(TitleActivity.this, MainActivity.class);

                    intent.putExtra("xcoor", x);
                    intent.putExtra("ycoor", y);
                    intent.putExtra("health", health);
                    intent.putExtra("done", true);
                    startActivity(intent);
                }
            }
        });
    }

    public void healthUpdate(){
        healthView.setText("Health: " + health);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int xtuch = (int) motionEvent.getX();
        int ytuch = (int) motionEvent.getY();

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            if(done) {
                Intent intent = new Intent(TitleActivity.this, MainActivity.class);

                intent.putExtra("xcoor", x);
                intent.putExtra("ycoor", y);
                intent.putExtra("health", health);
                intent.putExtra("done", true);
                startActivity(intent);
            }
        }
        return true;
    }
}
