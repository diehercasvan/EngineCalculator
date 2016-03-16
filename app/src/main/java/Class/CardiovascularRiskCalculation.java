package Class;

/**
 * Created by DIEGO  CASALLAS on 16/12/2015.
 */

import com.edibca.enginecalculator.R;
import java.util.ArrayList;
import DTO.*;

public class CardiovascularRiskCalculation {

    private int iSex;
    private double dAge;
    private double dSystolicPressure;
    private int iSmoker;
    private int iDiabetic;
    private double dHDLCholesterol;
    private double dTotalCholesterol;
    private double dCoef;
    private double dCoefOptimum;
    private double dCoefNormal;
    private double dRisk;
    private double dOptimumRisk;
    private double dRiskNormal;
    private boolean bTreatment;

    private ArrayList<Double> lisDataCalculate;

    public CardiovascularRiskCalculation(DTO_Data dto_data) {

        this.iSex = (dto_data.getsSex().equals(General.ACTIVITY.getResources().getString(R.string.male))) ? 1 : 0;
        this.dAge = Math.log(Integer.parseInt(dto_data.getsAge()));
        this.dSystolicPressure = Math.log(Integer.parseInt(dto_data.getsSystolicPressure()));
        this.iSmoker = (dto_data.isbSmokes()) ? 1 : 0;
        this.iDiabetic = (dto_data.isbDiabetic()) ? 1 : 0;
        this.bTreatment = dto_data.isbHypertensionTreatment();
        this.dHDLCholesterol = Math.log(Integer.parseInt(dto_data.getsCholesterolHDL()));
        this.dTotalCholesterol = Math.log(Integer.parseInt(dto_data.getsCholesterolTotal()));
        this.lisDataCalculate = null;

    }

    public ArrayList<Double> calculate() {
        try {
            lisDataCalculate = new ArrayList<>();
            //Male
            if (iSex == 1) {
                //treatment
                if (bTreatment) {
                    dCoef = (dAge * 3.06117) + (dSystolicPressure * 1.99881) + (dTotalCholesterol * 1.1237) + (dHDLCholesterol * -0.93263) + (iSmoker * 0.65451) + (iDiabetic * 0.57367);
                }
                //No treatment
                else {
                    dCoef = (dAge * 3.06117) + (dSystolicPressure * 1.93303) + (dTotalCholesterol * 1.1237) + (dHDLCholesterol * -0.93263) + (iSmoker * 0.65451) + (iDiabetic * 0.57367);
                }
                dCoefOptimum = (dAge * 3.06117) + (Math.log(110) * 1.93303) + (Math.log(160) * 1.1237) + (Math.log(60) * -0.93263);
                dCoefNormal = (dAge * 3.06117) + (Math.log(125) * 1.93303) + (Math.log(180) * 1.1237) + (Math.log(45) * -0.93263);

                dRisk = 1 - Math.pow(0.88936, Math.exp(dCoef - 23.9802));
                dOptimumRisk = 1 - Math.pow(0.88936, Math.exp(dCoefOptimum - 23.9802));
                dRiskNormal = 1 - Math.pow(0.88936, Math.exp(dCoefNormal - 23.9802));
            }
            //Famele
            else {
                //treatment
                if (bTreatment) {
                    dCoef = (dAge * 2.32888) + (dSystolicPressure * 2.82263) + (dTotalCholesterol * 1.20904) + (dHDLCholesterol * -0.70833) + (iSmoker * 0.52873)
                            + (iDiabetic * 0.69154);
                }
                //No treatment
                else {
                    dCoef = (dAge * 2.32888) + (dSystolicPressure * 2.76157) + (dTotalCholesterol * 1.20904) + (dHDLCholesterol * -0.70833) + (iSmoker * 0.52873)
                            + (iDiabetic * 0.69154);
                }
                dCoefOptimum = (dAge * 2.32888) + (Math.log(110) * 2.76157) + (Math.log(160) * 1.20904) + (Math.log(60) * -0.70833);
                dCoefNormal = (dAge * 2.32888) + (Math.log(125) * 2.76157) + (Math.log(180) * 1.20904) + (Math.log(45) * -0.70833);

                dRisk = 1 - Math.pow(0.95012, Math.exp(dCoef - 26.1931));
                dOptimumRisk = 1 - Math.pow(0.95012, Math.exp(dCoefOptimum - 26.1931));
                dRiskNormal = 1 - Math.pow(0.95012, Math.exp(dCoefNormal - 26.1931));
            }



            lisDataCalculate.add(Math.round(dRisk * 10000.0) / 100.0);
            lisDataCalculate.add(Math.round(dRiskNormal * 10000.0) / 100.0);
            lisDataCalculate.add(Math.round(dOptimumRisk * 10000.0) / 100.0);



        } catch (Exception e) {
            General.printToast(R.string.messages3);
        }
        return lisDataCalculate;

    }

}
