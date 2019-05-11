package com.example.cs441_program8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
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
        myView = new MyView(this);
        setContentView(myView);


    }

    class MyView extends SurfaceView implements Runnable{
        Thread gameThread = null;
        SurfaceHolder ourHolder;
        volatile boolean playing;
        boolean paused = false;
        Canvas canvas;
        Paint paint;
        Bitmap bitmap;
        int height, width;
        int heightI = 1000;
        int widthI = 1000;
        int leftBound, topBound = 250;
        //final ImageView img = (ImageView) findViewById(R.id.the_guy);

        //img.setFrame(2,2,2,2);
        float x,y=100;
        Bitmap background = BitmapFactory.decodeResource(this.getResources(), R.drawable.test_boy) ;
        float startX, startY, endX, endY = 100;
        boolean drawLine = false;

        Matrix m = new Matrix();
        Rect rect = new Rect(100,100,300,300);

        the_guy guy;
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
            touched = false;

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
                paint.setColor(Color.GREEN);
                //canvas.drawLine(0,0,300,300, paint);
                //canvas.drawCircle(x,y,100,paint);
                guy.width = width;
                guy.height = height;
                guy.draw(canvas, paint);


                //canvas.drawLine(500,500,700,700,paint);
                //canvas.drawCircle(cx, cy,50,paint);






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

            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                int xtuch = (int) motionEvent.getX();
                int ytuch = (int) motionEvent.getY();
                //if (xtuch > hey.x - 30 && xtuch < hey.x + hey.bitmap1.getWidth() +30 && ytuch > hey.y -30 && ytuch < hey.y + hey.bitmap1.getHeight() +30) {
                //    hey.dy = -50;
                //}
                startX = xtuch;
                startY = ytuch;
                drawLine = false;
                //bonk.onTouchEvent(motionEvent, this.getContext());



            }
            else if(motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_HOVER_MOVE) {
                int xtuch = (int) motionEvent.getX();
                int ytuch = (int) motionEvent.getY();
                endX = xtuch;
                endY = ytuch;
                drawLine = true;
                go = true;
            }
            else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                //bonk.onTouchEvent(motionEvent, this.getContext());
                /*if(teeest){
                    hey.curFrame = 0;
                }
                else{
                    hey.curFrame = 1;
                }*/
                //teeest = !teeest;
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
