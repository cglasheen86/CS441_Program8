package com.example.cs441_program8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class bad_guy {
    float x, y;
    float width, height, center;
    Bitmap bitmap1;
    Drawable d;
    ImageView butt;
    int frameLength = 100;
    int lastChange = 0;
    int curFrame = 0;
    int numFrames = 2;
    boolean go = true;

    public bad_guy(Context context, float centerX, float screenHeight){
        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.evil_thing) ;
        //up = BitmapFactory.decodeResource(context.getResources(), R.drawable.up_arrow);
        //down = BitmapFactory.decodeResource(context.getResources(), R.drawable.down_arrow);
        //left = BitmapFactory.decodeResource(context.getResources(), R.drawable.left_arrow);
        //right = BitmapFactory.decodeResource(context.getResources(), R.drawable.right_arrow);
        //action = BitmapFactory.decodeResource(context.getResources(), R.drawable.center_button);
        //center = centerX;
        //height = screenHeight;
        //butt =new ImageView(context);
        //butt.setImageBitmap(up);
    }
    public void update(String direction)
    {
        if(direction == "up") y-=5;
        else if(direction == "down") y+=5;
        else if(direction == "right") x+=5;
        else if(direction == "left") x-=5;

    }



    public void draw(Canvas canvas, Paint paint)
    {
        //canvas.drawBitmap(test, 256,256,null);
        //canvas.drawCircle(x, y, diameter, paint);
        canvas.drawBitmap(bitmap1, x, y, null);
        //canvas.drawBitmap(action, canvas.getWidth()/2, canvas.getHeight() - 400, null);
        //canvas.drawBitmap(up, canvas.getWidth()/2, canvas.getHeight() - action.getHeight(), null);
        //canvas.drawBitmap(down, canvas.getWidth()/2, canvas.getHeight() - 200, null);
        //canvas.drawBitmap(left, canvas.getWidth()/2 - 200, canvas.getHeight() - 400, null);
        //canvas.drawBitmap(right, canvas.getWidth()/2 + 200, canvas.getHeight() - 400, null);

        //d = new BitmapDrawable(bitmap1);
        //canvas.drawBitmap(test, 256,256,null);
    }

    public boolean onTouchEvent(MotionEvent e, Context c){
        if(e.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
}
