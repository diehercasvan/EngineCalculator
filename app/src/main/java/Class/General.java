package Class;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;
import com.edibca.enginecalculator.R;
import java.io.File;
import java.util.regex.Pattern;

/**
 * Created by DIEGO CASALLAS on 03/12/2015.
 */
public class General {

    public static Activity ACTIVITY;
    public static Context CONTEXT;
    public static Animation animation;
    public static String ROUTE;
    public static String NAME_FOLDER_MAIL;
    public static String[] MAIL_CONTENT;
    public static int iIDLogo;
    public static String CONTENT_HTML;
    public static String CONTENT_CSS;
    public static final String PASSWORD="creative";

    public General(Activity activity, Context context,String route) {

        this.ACTIVITY = activity;
        this.CONTEXT = context;
        this.ROUTE=route;
        this.MAIL_CONTENT= new String []{"","Atlas Information",""};
        this.NAME_FOLDER_MAIL="Pictures";
        this.iIDLogo=R.raw.logos;
        this.CONTENT_HTML="<!DOCTYPE html><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>Calculator PDF</title>";
        this.CONTENT_CSS="<style type=\"text/css\">html, body {text-align: center;}#container{width: auto;height: auto;margin: auto;background: transparent !important;text-align: center;padding: 10px;color:#333;}#container h2 {margin: 0;padding: 0;color:white;}#header {width: auto;height:50px;box-shadow: 0px 0px 10px #8c0000;background: #8c0000;margin: auto;}.infoRight {width: 340px;height:50px;background:#CCC;float: right;border: 5px solid #FFF;font-weight:bold;}.infoLeft {width: 340px;height: 50px;background:#CCC;float: left;border: 5px solid #FFF;}.separator {width: auto;height: 30px;}#observations {width: auto;height: auto;background:#FFF;float: left;border: 3px solid #CCC;}#observations p{text-align:left;padding:5px;}#observations h4{text-align:left;padding:5px;}</style>";


    }

    public static final void printSnackbar(int iText, View view) {

        Snackbar snackbar = Snackbar.make(view, iText, Snackbar.LENGTH_SHORT);
        StyleSnackbar.alert(snackbar).show();

    }

    public static final void printToast(int iText) {

        Toast.makeText(ACTIVITY, iText, Toast.LENGTH_LONG).show();
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static final boolean validateEmpty(EditText[] editTexts) {

        boolean bValidateData = true;
        boolean bReturn = true;
        int i = 0;

        while (bValidateData) {

            if (editTexts.length == i) {
                bValidateData = false;
                bReturn = true;
            } else {

                if (editTexts[i].getText().equals("") || editTexts[i].getText().length() <= 1) {
                    bValidateData = false;
                    bReturn = false;
                }
            }
            i++;

        }
        return bReturn;

    }

    public static final boolean validateTypeString(EditText[] editTexts) {


        boolean bValidateData = true;
        boolean bReturn = true;
        int i = 0;

        while (bValidateData) {


            if (editTexts.length == i) {
                bValidateData = false;
                bReturn = true;
            } else {

                if (!Pattern.matches("[0-9]+", editTexts[i].getText())) {
                    bValidateData = false;
                    bReturn = false;
                }
            }
            i++;

        }

        return bReturn;

    }

    public static final Animation animation(int iSelection) {
        switch (iSelection) {
            case 0:
                animation = AnimationUtils.loadAnimation(ACTIVITY, R.anim.fadein);
                break;
            case 1:
                animation = AnimationUtils.loadAnimation(ACTIVITY, R.anim.fadeout);
                break;
        }

        return animation;
    }
}
