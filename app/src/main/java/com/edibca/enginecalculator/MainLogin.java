package com.edibca.enginecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

import DataBaseHelper.BusinessLogic;
import DataBaseHelper.DtoUser;
import Class.*;


/**
 * Created by DIEGO CASALLAS on 11/03/2016.
 */
public class MainLogin extends Activity implements View.OnClickListener {

    private DtoUser dtoUser;
    private BusinessLogic businessLogic;
    private EditText editText;
    private Button button;
    private ImageView imageLogo;
    private SvgCreate svgCreate;
    private  String sUser;
    private ArrayList<DtoUser> arrayList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        General ObjGenral = new General(this, this, Environment.getExternalStorageDirectory().getAbsolutePath());
        imageLogo = (ImageView)findViewById(R.id.ImgLogo);
        svgCreate = new SvgCreate(imageLogo, General.iIDLogo);
        svgCreate.builderSVG();

        editText = (EditText) findViewById(R.id.editTextPassword);
        button = (Button) findViewById(R.id.btnLogin);
        button.setOnClickListener(this);
        dtoUser=new DtoUser();
        businessLogic=new BusinessLogic(this);
        dtoUser.setsName(General.PASSWORD);

        arrayList=new ArrayList<>();
        /* try {
            if(businessLogic.deleteUserBl(dtoUser)!=-1){
                button.setEnabled(false);
                editText.setEnabled(false);
                editText.setText("");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
       try {
            arrayList=businessLogic.consultUserBl(dtoUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(arrayList.size()==1){
            startInten();
        }



    }

    @Override
    public void onClick(View v) {
        sUser=editText.getText().toString().trim().toLowerCase();

      if(sUser.equals(General.PASSWORD)){
          try {
              insertUser(sUser);
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }
        else{
          Toast.makeText(this,getResources().getString(R.string.dialogErrorPassword),Toast.LENGTH_LONG).show();
      }
    }

    public void insertUser(String sName) throws SQLException {
        dtoUser=new DtoUser();
        dtoUser.setsName(sName);

        if(businessLogic.insertUserBl(dtoUser)!=-1){
            button.setEnabled(false);
            editText.setEnabled(false);
            editText.setText("");
            startInten();

        }

    }
    public void  startInten(){

        Intent intent=new Intent(this,MainMenu.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);


    }
}
