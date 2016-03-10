package Class;

/**
 * Created by DIEGO  CASALLAS on 16/12/2015.
 */

import com.edibca.enginecalculator.R;
import DTO.DTO_Data;

public class MetabolicSyndromeCalculation {

    private int iSex;
    private int iWaist;
    private int iHDLCholesterol;
    private boolean bTreatment;
    private double bTriglycerides;
    private int iSystolic;
    private int iDiastolic;
    private double dGlucose;
    private int iNumer;
    private boolean bSyndrome;
    private boolean bValidate;


    public MetabolicSyndromeCalculation(DTO_Data dto_data) {
        this.bValidate = false;
        this.iSex = (dto_data.getsSex().equals(General.ACTIVITY.getResources().getString(R.string.male))) ? 1 : 0;
        this.iWaist = Integer.parseInt(dto_data.getsWaist());
        if ((iSex == 1 && iWaist >= 95) || (iSex == 0 && iWaist >= 90)) {
            bValidate = true;
        }
        if (bValidate) {
            this.bTriglycerides = Double.parseDouble(dto_data.getsTriglycerides());
            this.iHDLCholesterol = Integer.parseInt(dto_data.getsCholesterolHDL());

            this.bTreatment = dto_data.isbHypertensionTreatment();
            if(!this.bTreatment){
                this.iSystolic = Integer.parseInt(dto_data.getsSystolic());
                this.iDiastolic = Integer.parseInt(dto_data.getsDiastolic());
            }
            this.dGlucose = Double.parseDouble(dto_data.getsGlucose());
            this.iNumer = 0;
            this.bSyndrome = false;
        }
        else {
            bValidate=true;
        }

    }

    public boolean calculate() {
        try {

            if (bValidate) {
                //Male

                if (iSex == 1) {
                    if (iWaist >= 95) {
                        iNumer++;
                    }
                    if (bTriglycerides >= 150) {
                        iNumer++;
                    }
                    if (iHDLCholesterol < 40) {
                        iNumer++;
                    }
                    if (bTreatment) {
                        iNumer++;
                    } else {
                        if (iSystolic >= 130) {
                            iNumer++;
                        }
                        if (iDiastolic >= 85) {
                            iNumer++;
                        }
                    }
                    if (dGlucose >= 100) {
                        iNumer++;
                    }
                }
                //Famele
                else {
                    if (iWaist >= 90) {
                        iNumer++;
                    }
                    if (bTriglycerides >= 150) {
                        iNumer++;
                    }
                    if (iHDLCholesterol < 50) {
                        iNumer++;
                    }
                    if (bTreatment) {
                        iNumer++;
                    } else {
                        if (iSystolic >= 130) {
                            iNumer++;
                        }
                        if (iDiastolic >= 85) {
                            iNumer++;
                        }
                    }
                    if (dGlucose >= 100) {
                        iNumer++;
                    }
                }
                if (iNumer >= 3) bSyndrome = true;
            }
        } catch (Exception e) {
            General.printToast(R.string.messages3);
        }
        return bSyndrome;

    }

}