package DTO;



/**
 * Created by DIEGO CASALLAS on 15/12/2015.
 */
public class DTO_Data {
    private String sAge;
    private String sSystolicPressure;
    private String sCholesterolHDL;
    private String sCholesterolTotal;
    private String sWaist;
    private String sTriglycerides;
    private String sGlucose;
    private String sSystolic;
    private String sDiastolic;
    private String sHeight;
    private String sWeight;
    private String sSex;

    private boolean bHeartSound;
    private boolean bAcuteMyocardial;
    private boolean bNoSinusRhythm;
    private boolean bContractions;
    private boolean bAge70;
    private boolean bOperation;
    private boolean bBatCondition;
    private boolean bSurgery;
    private boolean bValvular;
    private boolean bHypertensionTreatment;
    private boolean bSmokes;
    private boolean bDiabetic;
    public DTO_Data(){


    }
    public DTO_Data(String sAge, boolean bSmokes, boolean bDiabetic, boolean bHypertensionTreatment, String sSex, String sCholesterolHDL, String sCholesterolTotal, String sSystolicPressure) {
        this.sAge = sAge;
        this.bSmokes = bSmokes;
        this.bDiabetic = bDiabetic;
        this.bHypertensionTreatment = bHypertensionTreatment;
        this.sSex = sSex;
        this.sCholesterolHDL = sCholesterolHDL;
        this.sCholesterolTotal = sCholesterolTotal;
        this.sSystolicPressure = sSystolicPressure;


    }

    public DTO_Data(String sAge, String sSystolicPressure, String sCholesterolHDL, String sCholesterolTotal, String sSex, boolean bHypertensionTreatment, boolean bSmokes) {
        this.sAge = sAge;
        this.sSystolicPressure = sSystolicPressure;
        this.sCholesterolHDL = sCholesterolHDL;
        this.sCholesterolTotal = sCholesterolTotal;
        this.sSex = sSex;
        this.bHypertensionTreatment = bHypertensionTreatment;
        this.bSmokes = bSmokes;
    }

    public DTO_Data(String  sSex, boolean bHypertensionTreatment, String sGlucose, String sDiastolic, String sSystolic, String sCholesterolHDL, String sWaist, String sTriglycerides) {
        this.sSex = sSex;
        this.bHypertensionTreatment = bHypertensionTreatment;
        this.sGlucose = sGlucose;
        this.sDiastolic = sDiastolic;
        this.sSystolic = sSystolic;
        this.sCholesterolHDL = sCholesterolHDL;
        this.sWaist = sWaist;
        this.sTriglycerides = sTriglycerides;
    }

    public DTO_Data(String sHeight, String sWeight) {
        this.sHeight = sHeight;
        this.sWeight = sWeight;
    }

    public DTO_Data(boolean bHeartSound, boolean bAcuteMyocardial, boolean bNoSinusRhythm, boolean bContractions, boolean bAge70, boolean bOperation, boolean bBatCondition, boolean bSurgery, boolean bValvular) {
        this.bHeartSound = bHeartSound;
        this.bAcuteMyocardial = bAcuteMyocardial;
        this.bNoSinusRhythm = bNoSinusRhythm;
        this.bContractions = bContractions;
        this.bAge70 = bAge70;
        this.bOperation = bOperation;
        this.bBatCondition = bBatCondition;
        this.bSurgery = bSurgery;
        this.bValvular = bValvular;


    }

    public String getsAge() {
        return sAge;
    }

    public void setsAge(String sAge) {
        this.sAge = sAge;
    }

    public String getsSystolicPressure() {
        return sSystolicPressure;
    }

    public void setsSystolicPressure(String sSystolicPressure) {
        this.sSystolicPressure = sSystolicPressure;
    }

    public String getsCholesterolHDL() {
        return sCholesterolHDL;
    }

    public void setsCholesterolHDL(String sCholesterolHDL) {
        this.sCholesterolHDL = sCholesterolHDL;
    }

    public String getsCholesterolTotal() {
        return sCholesterolTotal;
    }

    public void setsCholesterolTotal(String sCholesterolTotal) {
        this.sCholesterolTotal = sCholesterolTotal;
    }

    public String getsWaist() {
        return sWaist;
    }

    public void setsWaist(String sWaist) {
        this.sWaist = sWaist;
    }

    public String getsTriglycerides() {
        return sTriglycerides;
    }

    public void setsTriglycerides(String sTriglycerides) {
        this.sTriglycerides = sTriglycerides;
    }

    public String getsGlucose() {
        return sGlucose;
    }

    public void setsGlucose(String sGlucose) {
        this.sGlucose = sGlucose;
    }

    public String getsSystolic() {
        return sSystolic;
    }

    public void setsSystolic(String sSystolic) {
        this.sSystolic = sSystolic;
    }

    public String getsDiastolic() {
        return sDiastolic;
    }

    public void setsDiastolic(String sDiastolic) {
        this.sDiastolic = sDiastolic;
    }

    public String getsHeight() {
        return sHeight;
    }

    public void setsHeight(String sHeight) {
        this.sHeight = sHeight;
    }

    public String getsWeight() {
        return sWeight;
    }

    public void setsWeight(String sWeight) {
        this.sWeight = sWeight;
    }

    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex;
    }

    public boolean isbHeartSound() {
        return bHeartSound;
    }

    public void setbHeartSound(boolean bHeartSound) {
        this.bHeartSound = bHeartSound;
    }

    public boolean isbAcuteMyocardial() {
        return bAcuteMyocardial;
    }

    public void setbAcuteMyocardial(boolean bAcuteMyocardial) {
        this.bAcuteMyocardial = bAcuteMyocardial;
    }

    public boolean isbNoSinusRhythm() {
        return bNoSinusRhythm;
    }

    public void setbNoSinusRhythm(boolean bNoSinusRhythm) {
        this.bNoSinusRhythm = bNoSinusRhythm;
    }

    public boolean isbContractions() {
        return bContractions;
    }

    public void setbContractions(boolean bContractions) {
        this.bContractions = bContractions;
    }

    public boolean isbAge70() {
        return bAge70;
    }

    public void setbAge70(boolean bAge70) {
        this.bAge70 = bAge70;
    }

    public boolean isbOperation() {
        return bOperation;
    }

    public void setbOperation(boolean bOperation) {
        this.bOperation = bOperation;
    }

    public boolean isbBatCondition() {
        return bBatCondition;
    }

    public void setbBatCondition(boolean bBatCondition) {
        this.bBatCondition = bBatCondition;
    }

    public boolean isbSurgery() {
        return bSurgery;
    }

    public void setbSurgery(boolean bSurgery) {
        this.bSurgery = bSurgery;
    }

    public boolean isbValvular() {
        return bValvular;
    }

    public void setbValvular(boolean bValvular) {
        this.bValvular = bValvular;
    }

    public boolean isbHypertensionTreatment() {
        return bHypertensionTreatment;
    }

    public void setbHypertensionTreatment(boolean bHypertensionTreatment) {
        this.bHypertensionTreatment = bHypertensionTreatment;
    }

    public boolean isbSmokes() {
        return bSmokes;
    }

    public void setbSmokes(boolean bSmokes) {
        this.bSmokes = bSmokes;
    }

    public boolean isbDiabetic() {
        return bDiabetic;
    }

    public void setbDiabetic(boolean bDiabetic) {
        this.bDiabetic = bDiabetic;
    }
}
