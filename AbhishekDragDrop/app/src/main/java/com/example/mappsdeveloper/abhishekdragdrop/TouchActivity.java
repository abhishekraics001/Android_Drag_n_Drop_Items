package com.example.mappsdeveloper.abhishekdragdrop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TouchActivity extends Activity {

    private ViewGroup mainLayout;
    private ImageView image1 , image2 ,  image3;
    private int xDelta;
    private int yDelta;
    boolean checkItems = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mainLayout = (RelativeLayout) findViewById(R.id.main);
            image1 = (ImageView) findViewById(R.id.image1);
            image2 = (ImageView) findViewById(R.id.imageView2);
            image3 = (ImageView) findViewById(R.id.imageView3);

            image1.setOnTouchListener(onTouchListener( "FirstImage") );
            image2.setOnTouchListener(onTouchListener( "SecondImage"));
            image3.setOnTouchListener(onTouchListener("ThirdImage") );
    }

    private OnTouchListener onTouchListener(final String msg)
    {
        return new OnTouchListener()
        {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event)
            {
                if(checkItems)
                {
                    return false;
                }
                else
                {
                    final int x = (int) event.getRawX();
                    final int y = (int) event.getRawY();

                    switch (event.getAction() & MotionEvent.ACTION_MASK)
                    {
                        case MotionEvent.ACTION_DOWN:
                            RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                            xDelta = x - lParams.leftMargin;
                            yDelta = y - lParams.topMargin;
                            Log.d("Point ACTION_DOWN", "x " + x + "  -- y -- " + y + "---lParams.leftMargin--" + lParams.leftMargin + "--lParams.topMargin --" + lParams.topMargin + "--xDelta--" + xDelta + "--yDelta---" + yDelta);
                            break;
                        case MotionEvent.ACTION_UP:
                              Log.d("Point ACTION_UP", "x " + x + "  -- xDelta -- " + xDelta + "---y--" + y + "--yDelta --" + yDelta);
                            if (x > 200 && y > 170 && x < 704 && y < 1064) {
                                Log.d("Point ACTION_UP", "x " + x + "  -- xDelta -- " + xDelta + "---y--" + y + "--yDelta --" + yDelta);
                                if (msg.equalsIgnoreCase("SecondImage")) {
                                    Toast.makeText(TouchActivity.this, "Congrats!!!!!!!!! Item drop to basket -- " + msg, Toast.LENGTH_SHORT).show();
                                    checkItems = true;
                                } else {
                                    Toast.makeText(TouchActivity.this, "Sorry!!!!! , Wrong Item drop to basket -- " + msg, Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(TouchActivity.this, "Item Not drop to basket , Please dropt it in center of basket-- " + msg, Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case MotionEvent.ACTION_MOVE:
                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                            layoutParams.leftMargin = x - xDelta;
                            layoutParams.topMargin = y - yDelta;
                            layoutParams.rightMargin = 0;
                            layoutParams.bottomMargin = 0;
                            view.setLayoutParams(layoutParams);
                            Log.d("Point ACTION_MOVE", "x " + x + "  -- xDelta -- " + xDelta + "---y--" + y + "--yDelta --" + yDelta + "---x - xDelta--" + layoutParams.leftMargin + "---y - yDelta--" + layoutParams.topMargin);
                            break;
                    }
                }
                mainLayout.invalidate();
                return true;
            }
        };
    }
}
