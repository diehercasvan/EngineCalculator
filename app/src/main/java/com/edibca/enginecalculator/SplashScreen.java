package com.edibca.enginecalculator;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class SplashScreen extends Activity {


    private RelativeLayout relativeLayout;
    private boolean bValidate = true;
    private Dialog dialog;


    public SplashScreen() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);
            relativeLayout = (RelativeLayout) findViewById(R.id.containerSplash);

            final GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onDown(MotionEvent e) {
                    Log.i("Movimiento", "Right to Down");
                     //loadNewActivity(2);
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                    Log.i("Movimiento", "onFling has been called!");
                    final int SWIPE_MIN_DISTANCE = 120;
                    final int SWIPE_MAX_OFF_PATH = 250;
                    final int SWIPE_THRESHOLD_VELOCITY = 300;
                    try {
                        if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                            return false;
                        if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                            Log.i("Movimiento", "Right to Left");
                            loadNewActivity(0);

                        } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                            Log.i("Movimiento", "Left to Right");
                            loadNewActivity(1);
                        }
                    } catch (Exception e) {
                        // nothing
                    }
                    return super.onFling(e1, e2, velocityX, velocityY);
                }
            });
            relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return gestureDetector.onTouchEvent(event);
                }
            });
        } catch (Exception e) {
            dialog();
        }
    }

    public void loadNewActivity(int iAnimation) {
        if (bValidate) {
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
            if (iAnimation == 0) {
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            } else if (iAnimation == 1){
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }else{
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }

            relativeLayout.setEnabled(false);
        }
        bValidate = false;

    }

    public void dialog() {

        dialog = new Dialog(this);

        dialog.setContentView(R.layout.custom);
        dialog.setTitle(getResources().getString(R.string.item6));
        dialog.setCancelable(false);
        Button dialogOK = (Button) dialog.findViewById(R.id.btn_yes);
        Button dialogCancel = (Button) dialog.findViewById(R.id.btn_no);
        // if button is clicked, close the custom dialog
        dialogOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reloadActivity(1);



            }
        });
        dialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reloadActivity(2);
            }
        });


        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public  void  reloadActivity(int iData ){

        if (iData==1) {
            finish();
            onDestroy();
            startActivity(getIntent());
        } else  {
            finish();
            onDestroy();
        }
    }



}
