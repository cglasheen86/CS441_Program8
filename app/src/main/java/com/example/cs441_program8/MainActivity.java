package com.example.cs441_program8;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   MyView myView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ConstraintLayout parent = (ConstraintLayout)findViewById(R.id.constraintLayout);
        myView = new MyView(this);
        //parent.addView(myView,0);
        setContentView(myView);


    }

    class MyView extends SurfaceView implements Runnable{
        Button butt = findViewById(R.id.button6);
        Thread gameThread = null;
        SurfaceHolder ourHolder;
        volatile boolean playing;
        boolean paused = false;
        Canvas canvas;
        Paint paint;
        Bitmap up, down, left, right, action;
        int height, width;
        int heightI = 1000;
        int widthI = 1000;
        int leftBound, topBound = 250;
        //final ImageView img = (ImageView) findViewById(R.id.the_guy);
        int flag = 0;
        float actionX;
        float actionY;

        String heldX = "";
        String heldY = "";

        //img.setFrame(2,2,2,2);
        float x,y=100;
        Bitmap background = BitmapFactory.decodeResource(this.getResources(), R.drawable.area) ;
        float startX, startY, endX, endY = 100;
        boolean drawLine = false;

        Matrix m = new Matrix();
        Rect rect = new Rect(100,100,300,300);

        the_guy guy;
        bad_guy bad;
        boolean go  = false;
        boolean teeest = false;

        float startXTest, startYTest =0;
        float endXTest, endYTest = 300;
        float cx=520;
        float cy=1000;
        boolean touched = false;
        boolean touched2 = false;
        TextView score = new TextView(getContext());
        TextView hiscore = new TextView(getContext());
        int scoreint = 0;
        int hiscoreint = 0;



        private long thisTimeFrame;
        public MyView(Context context){
            super(context);
            ourHolder = getHolder();
            paint = new Paint();
            //paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(20);
            paint.setStyle(Paint.Style.STROKE);
        }
        @Override
        public void run(){
            guy = new the_guy(this.getContext(), 400, 400);
            guy.x =100;
            guy.y =100;

            bad = new bad_guy(this.getContext(), 400, 400);
            bad.x = 400;
            bad.y = 400;

            //actionX = canvas.getWidth()/2;
            //actionY = canvas.getHeight() - 400;

            action = BitmapFactory.decodeResource(getResources(), R.drawable.center_button);
            up = BitmapFactory.decodeResource(getResources(), R.drawable.up_arrow);
            down = BitmapFactory.decodeResource(getResources(), R.drawable.down_arrow);
            left = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow);
            right = BitmapFactory.decodeResource(getResources(), R.drawable.right_arrow);
            while(playing){
                update();
                draw();
                try{
                    Thread.sleep(1);
                }catch(InterruptedException e){

                }
            }
        }
        public void update(){
            //touched = false;
            //touched2=false;
            //if(touched2){
            //cx--;
            //}
            //else cy--;
            if( ((guy.x > bad.x && guy.x <= bad.x + bad.bitmap1.getWidth()) || ((guy.x+guy.bitmap1.getWidth() > bad.x && guy.x+guy.bitmap1.getWidth() <= bad.x + bad.bitmap1.getWidth()) )) && ((guy.y > bad.y && guy.y <= bad.y + bad.bitmap1.getHeight() ) || (guy.y+guy.bitmap1.getHeight() > bad.y && guy.y+guy.bitmap1.getHeight() <= bad.y + bad.bitmap1.getHeight() ))) flag = 1;
            else if(flag == 1) flag = 0;
            if(flag == 1){
                Intent intent = new Intent(MainActivity.this, TitleActivity.class);
                intent.putExtra("x-coor", guy.x);
                intent.putExtra("y-coor", guy.y);
                startActivity(new Intent(MainActivity.this, TitleActivity.class));
            }
            if(heldY != "")guy.update(heldY);
            if(heldX != "")guy.update(heldX);


            //if(go)guy.update("up");
            //if(!guy.go){
            //    go = false;
            //    guy.go=true;
            //}
        }

        public void draw(){
            if(ourHolder.getSurface().isValid()){
                canvas = ourHolder.lockCanvas();
                width = canvas.getWidth();
                height = canvas.getHeight();
                int ex = 0;
                int why = 0;
                //while(why < height){
                    //while(ex < width){
                        //canvas.drawBitmap(background,ex,why,null);
                        //ex+=background.getWidth();
                    //}
                    ex=0;
                    //why+=background.getHeight();
                //}
                //canvas.drawBitmap(background,0,0,null);
                if(flag == 0)canvas.drawColor(Color.WHITE);
                else canvas.drawColor(Color.RED);
                canvas.drawBitmap(background, 0,0, null);
                //canvas.drawLine(0,0,300,300, paint);
                //canvas.drawCircle(x,y,100,paint);
                guy.width = width;
                guy.height = height;
                guy.draw(canvas, paint);
                bad.draw(canvas, paint);
                //canvas.drawLine(500,500,700,700,paint);
                //canvas.drawCircle(cx, cy,50,paint);
                actionX = canvas.getWidth()/2 - action.getWidth()/2;
                actionY = canvas.getHeight() - 400;
                canvas.drawBitmap(action, actionX, actionY, null);
                canvas.drawBitmap(up, actionX, actionY - 200, null);
                canvas.drawBitmap(down, actionX, actionY + 200, null);
                canvas.drawBitmap(left, actionX - 200, actionY, null);
                canvas.drawBitmap(right, actionX + 200, actionY, null);




                ourHolder.unlockCanvasAndPost(canvas);
            }
        }
        public void pause(){
            playing = false;
            try{
                gameThread.join();
            }catch(InterruptedException e){
                Log.e("ERror:", "joining thread, dummy");
            }
        }
        public void resume(){
            playing =true;
            gameThread = new Thread(this);
            gameThread.start();
        }


        @Override
        public boolean onTouchEvent(MotionEvent motionEvent){
            int xtuch = (int) motionEvent.getX();
            int ytuch = (int) motionEvent.getY();

            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                if (xtuch >= actionX && xtuch < actionX + action.getWidth() && ytuch >= actionY && ytuch < actionY + action.getHeight()) {
                    if(guy.x == 100) guy.x = 400;
                    else guy.x = 100;
                }
                if (xtuch >= actionX && xtuch < actionX + action.getWidth() && ytuch >= actionY -200 && ytuch < actionY -200 + action.getHeight()) {
                    heldY = "up";
                }
                if (xtuch >= actionX && xtuch < actionX + action.getWidth() && ytuch >= actionY + 200 && ytuch < actionY + 200 + action.getHeight()) {
                    heldY = "down";
                }

                if (xtuch >= actionX -200 && xtuch < actionX -200 + action.getWidth() && ytuch >= actionY && ytuch < actionY + action.getHeight()) {
                    heldX = "left";
                }

                if (xtuch >= actionX +200 && xtuch < actionX + 200 + action.getWidth() && ytuch >= actionY && ytuch < actionY + action.getHeight()) {
                    heldX = "right";
                }
            }


            if(motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                if (xtuch >= actionX && xtuch < actionX + action.getWidth() && ytuch >= actionY && ytuch < actionY + action.getHeight()) {
                    if(guy.x == 100) guy.x = 400;
                    else guy.x = 100;
                }

                if (xtuch >= actionX && xtuch < actionX + action.getWidth() && ytuch >= actionY -200 && ytuch < actionY -200 + action.getHeight()) {
                    //guy.update("up");
                    heldY = "up";
                }

                else if (xtuch >= actionX && xtuch < actionX + action.getWidth() && ytuch >= actionY + 200 && ytuch < actionY + 200 + action.getHeight()) {
                    //guy.update("down");
                    heldY = "down";
                }

                else heldY = "";

                if (xtuch >= actionX -200 && xtuch < actionX - 200 + action.getWidth() && ytuch >= actionY && ytuch < actionY + action.getHeight()) {
                    heldX = "left";
                }

                else if (xtuch >= actionX + 200&& xtuch < actionX + 200 + action.getWidth() && ytuch >= actionY && ytuch < actionY + action.getHeight()) {
                    heldX = "right";
                }

                else heldX = "";
            }


            if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                heldX = "";
                heldY = "";
            }
            return true;
        }
    }
    public void stop(){
    }
    @Override
    protected void onResume(){
        super.onResume();
        myView.resume();
    }
    @Override
    protected void onPause(){
        super.onPause();
        myView.pause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
