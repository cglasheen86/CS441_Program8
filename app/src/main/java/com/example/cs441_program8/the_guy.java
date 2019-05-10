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

public class the_guy {
    float x, y;
    float width, height;
    Bitmap bitmap1;
    Drawable d;
    int frameLength = 100;
    int lastChange = 0;
    int curFrame = 0;
    int numFrames = 2;
    boolean go = true;

    public the_guy(Context context){
        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.test_boy) ;

    }
    public void update(String direction)
    {
        if(direction == "up") y--;
        else if(direction == "down") y++;
        else if(direction == "right") x++;
        else if(direction == "left") x--;

    }



    public void draw(Canvas canvas, Paint paint)
    {
        //canvas.drawBitmap(test, 256,256,null);
        //canvas.drawCircle(x, y, diameter, paint);
        canvas.drawBitmap(bitmap1, x, y, null);
        //d = new BitmapDrawable(bitmap1);
        //canvas.drawBitmap(test, 256,256,null);
    }

    public boolean onTouchEvent(MotionEvent e, Context c){
        //if(e.getAction() == MotionEvent.ACTION_DOWN) {

        //}
        return true;
    }
}
