package Class;

import android.app.Activity;
import com.edibca.enginecalculator.R;
import java.util.ArrayList;
import DTO.DTO_Data;

/**
 * Created by DIEGO CASALLAS on 18/12/2015.
 */
public class PostoperativeRisk {

    private boolean bHeartSound;
    private boolean bAcuteMyocardial;
    private boolean bNoSinusRhythm;
    private boolean bContractions;
    private boolean bAge70;
    private boolean bOperation;
    private boolean bBatCondition;
    private boolean bSurgery;
    private boolean bValvular;
    private Activity activity;

    public PostoperativeRisk(DTO_Data dto_data) {


        this.bHeartSound = dto_data.isbHeartSound();
        this.bAcuteMyocardial = dto_data.isbHeartSound();
        this.bNoSinusRhythm = dto_data.isbHeartSound();
        this.bContractions = dto_data.isbHeartSound();
        this.bAge70 = dto_data.isbHeartSound();
        this.bOperation = dto_data.isbHeartSound();
        this.bBatCondition = dto_data.isbHeartSound();
        this.bSurgery = dto_data.isbHeartSound();
        this.bValvular = dto_data.isbHeartSound();
        this.activity=General.ACTIVITY;
    }

    public ArrayList<String> calcularRiesgo() {
        int iScore = 0;
        ArrayList<String> arrayList=new ArrayList<>();
        String[] dResult =activity.getResources().getStringArray(R.array.Postoperative);

        if (bAge70) iScore += 5;
        if (this.bAcuteMyocardial) iScore += 10;
        if (this.bHeartSound) iScore += 11;
        if (this.bValvular) iScore += 3;
        if (this.bNoSinusRhythm) iScore += 7;
        if (this.bContractions) iScore += 7;
        if (this.bBatCondition) iScore += 3;
        if (this.bSurgery) iScore += 3;
        if (this.bOperation) iScore += 4;

        if (iScore <= 5) {
            arrayList.add("1%");
            arrayList.add("0.7%");
            arrayList.add("0.2%");
            arrayList.add(dResult[0]);
        } else if (iScore >= 6 && iScore <= 12) {
            arrayList.add("2%");
            arrayList.add("5%");
            arrayList.add("2%");
            arrayList.add(dResult[1]);
        } else if (iScore >= 13 && iScore <= 25) {
            arrayList.add("3%");
            arrayList.add("11%");
            arrayList.add("2%");
            arrayList.add(dResult[2]);
        } else if (iScore >= 26) {
            arrayList.add("4%");
            arrayList.add("22%");
            arrayList.add("56%");
            arrayList.add(dResult[3]);
        }

        return arrayList;
    }


}
