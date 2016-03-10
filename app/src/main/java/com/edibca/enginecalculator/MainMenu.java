package com.edibca.enginecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainMenu extends Activity implements View.OnClickListener{
    private ImageView[] imageViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        loadView();
    }
    public void  loadView(){
        imageViews=new ImageView[5];

        imageViews[0] = (ImageView) findViewById(R.id.btnItem_0);
        imageViews[1] = (ImageView) findViewById(R.id.btnItem_1);
        imageViews[2] = (ImageView) findViewById(R.id.btnItem_2);
        imageViews[3] = (ImageView) findViewById(R.id.btnItem_3);
        imageViews[4] = (ImageView) findViewById(R.id.btnItem_4);



        for(int i=0;i<imageViews.length;i++){
            imageViews[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int selection=0;
        switch (v.getId())
        {

            case R.id.btnItem_0:
                selection=0;
                break;
            case R.id.btnItem_1:
                selection=1;
                break;
            case R.id.btnItem_2:
                selection=2;
                break;
            case R.id.btnItem_3:
                selection=3;
                break;
            case R.id.btnItem_4:
                selection=4;
                break;



        }

        loadIntent(selection);

    }
    public void  loadIntent(int iData){

        Intent intent=new Intent(this,MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("selectMenu",iData);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        finish();
    }
}
