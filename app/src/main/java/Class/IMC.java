package Class;

/**
 * Created by DIEGO CASALLAS on 16/12/2015.
 */

import android.app.Activity;
import com.edibca.enginecalculator.*;
import java.util.ArrayList;

import DTO.*;

public class IMC {
    private int iWeight;
    private double dStature;
    private Activity activity;


    public IMC(DTO_Data dto_data) {

        this.iWeight = Integer.parseInt(dto_data.getsWeight());
        this.dStature = Double.parseDouble(dto_data.getsHeight());
        this.activity = General.ACTIVITY;


    }

    public double calculate() {
        Double m = dStature / 100;
        return iWeight / Math.pow(m, 2);
    }

    public ArrayList<String> answer() {
        ArrayList<String> list = new ArrayList<>();
        String[] strings = activity.getResources().getStringArray(R.array.degradeObesity);
        String sID_SVG = "";
        String sAnswer = "";

        double imc = calculate();
        if (imc < 18.5) {
            sAnswer = strings[0];
            sID_SVG = "" + R.raw.man1;
        } else if (imc >= 18.5 && imc <= 24.9) {

            sAnswer = strings[1];
            sID_SVG = "" + R.raw.man2;
        } else if (imc >= 25 && imc <= 29.9) {
            sAnswer = strings[2];
            sID_SVG = "" + R.raw.man3;
        } else if (imc >= 30 && imc <= 34.9) {
            sAnswer = strings[3];
            sID_SVG = "" + R.raw.man4;
        } else if (imc >= 35 && imc <= 39.9) {
            sAnswer = strings[4];
            sID_SVG = "" + R.raw.man4;
        } else if (imc >= 40) {
            sAnswer = strings[5];
            sID_SVG = "" + R.raw.man4;
        }

        list.add(sAnswer);
        list.add(activity.getResources().getString(R.string.message_risk6) + " " + Math.rint(imc * 100) / 100 + " kg/m2");
        list.add(sID_SVG);
        return list;
    }
}
