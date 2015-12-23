package Class;

/**
 * Created by DIEGO  CASALLAS on 16/12/2015.
 */

import com.edibca.enginecalculator.R;
import java.util.ArrayList;
import DTO.DTO_Data;

public class ECRiskCalculation {

    private int iSex;
    private int iAge;
    private int iSystolicPressure;
    private boolean bSmoker;
    private int iHDLCholesterol;
    private double dTotalCholesterol;
    private boolean bTreatment;
    private ArrayList<Double> lisDataCalculate;

    public ECRiskCalculation(DTO_Data dto_data) {

        this.iSex = (dto_data.getsSex().equals(General.ACTIVITY.getResources().getString(R.string.male))) ? 1 : 0;
        this.iAge = Integer.parseInt(dto_data.getsAge());
        this.iSystolicPressure = Integer.parseInt(dto_data.getsSystolicPressure());
        this.bSmoker = dto_data.isbSmokes();
        this.bTreatment = dto_data.isbHypertensionTreatment();
        this.iHDLCholesterol = Integer.parseInt(dto_data.getsCholesterolHDL());
        this.dTotalCholesterol = Math.log(Integer.parseInt(dto_data.getsCholesterolTotal()));
        this.lisDataCalculate = null;

    }

    public ArrayList<Double> calculate() {
        try {
            lisDataCalculate = new ArrayList<>();
            double dRisk = 0;
            int score = 0;

            //m
            if (iSex == 1) {
                //Edad
                if (iAge >= 20 && iAge <= 34) score += -9;
                else if (iAge >= 35 && iAge <= 39) score += -4;
                else if (iAge >= 40 && iAge <= 44) score += 0;
                else if (iAge >= 45 && iAge <= 49) score += 3;
                else if (iAge >= 50 && iAge <= 54) score += 6;
                else if (iAge >= 55 && iAge <= 59) score += 8;
                else if (iAge >= 60 && iAge <= 64) score += 10;
                else if (iAge >= 65 && iAge <= 69) score += 11;
                else if (iAge >= 70 && iAge <= 74) score += 12;
                else if (iAge >= 75 && iAge <= 79) score += 13;

                //Colesterol total vs. Edad y Fumador/No fumador
                if (iAge >= 20 && iAge <= 39) {
                    if (dTotalCholesterol < 160) {
                        score += 0;
                    } else if (dTotalCholesterol >= 160 && dTotalCholesterol <= 199) {
                        score += 4;
                    } else if (dTotalCholesterol >= 200 && dTotalCholesterol <= 239) {
                        score += 7;
                    } else if (dTotalCholesterol >= 240 && dTotalCholesterol <= 279) {
                        score += 9;
                    } else if (dTotalCholesterol >= 280) {
                        score += 11;
                    }
                    if (bSmoker) score += 8;
                } else if (iAge >= 40 && iAge <= 49) {
                    if (dTotalCholesterol < 160) {
                        score += 0;
                    } else if (dTotalCholesterol >= 160 && dTotalCholesterol <= 199) {
                        score += 3;
                    } else if (dTotalCholesterol >= 200 && dTotalCholesterol <= 239) {
                        score += 5;
                    } else if (dTotalCholesterol >= 240 && dTotalCholesterol <= 279) {
                        score += 6;
                    } else if (dTotalCholesterol >= 280) {
                        score += 8;
                    }
                    if (bSmoker) score += 5;
                } else if (iAge >= 50 && iAge <= 59) {
                    if (dTotalCholesterol < 160) {
                        score += 0;
                    } else if (dTotalCholesterol >= 160 && dTotalCholesterol <= 199) {
                        score += 2;
                    } else if (dTotalCholesterol >= 200 && dTotalCholesterol <= 239) {
                        score += 3;
                    } else if (dTotalCholesterol >= 240 && dTotalCholesterol <= 279) {
                        score += 4;
                    } else if (dTotalCholesterol >= 280) {
                        score += 5;
                    }
                    if (bSmoker) score += 3;
                } else if (iAge >= 60 && iAge <= 69) {
                    if (dTotalCholesterol < 160) {
                        score += 0;
                    } else if (dTotalCholesterol >= 160 && dTotalCholesterol <= 199) {
                        score += 1;
                    } else if (dTotalCholesterol >= 200 && dTotalCholesterol <= 239) {
                        score += 1;
                    } else if (dTotalCholesterol >= 240 && dTotalCholesterol <= 279) {
                        score += 2;
                    } else if (dTotalCholesterol >= 280) {
                        score += 3;
                    }
                    if (bSmoker) score += 1;
                } else if (iAge >= 70 && iAge <= 79) {
                    if (dTotalCholesterol < 160) {
                        score += 0;
                    } else if (dTotalCholesterol >= 160 && dTotalCholesterol <= 199) {
                        score += 0;
                    } else if (dTotalCholesterol >= 200 && dTotalCholesterol <= 239) {
                        score += 0;
                    } else if (dTotalCholesterol >= 240 && dTotalCholesterol <= 279) {
                        score += 1;
                    } else if (dTotalCholesterol >= 280) {
                        score += 1;
                    }
                    if (bSmoker) score += 1;
                }

                //HDL
                if (iHDLCholesterol>= 60) score += -1;
                else if (iHDLCholesterol >= 50 && iHDLCholesterol <= 59) score += 0;
                else if (iHDLCholesterol >= 40 && iHDLCholesterol <= 49) score += 1;
                else if (iHDLCholesterol < 40) score += 2;

                //Presión sistólica vs. Tratamiento
                if (iSystolicPressure < 120) {
                    if (this.bTreatment) {
                        score += 0;
                    } else {
                        score += 0;
                    }
                } else if (iSystolicPressure >= 120 && iSystolicPressure <= 129) {
                    if (this.bTreatment) {
                        score += 0;
                    } else {
                        score += 1;
                    }
                } else if (iSystolicPressure >= 130 && iSystolicPressure <= 139) {
                    if (this.bTreatment) {
                        score += 1;
                    } else {
                        score += 2;
                    }
                } else if (iSystolicPressure >= 140 && iSystolicPressure <= 159) {
                    if (this.bTreatment) {
                        score += 1;
                    } else {
                        score += 2;
                    }
                } else if (iSystolicPressure >= 160) {
                    if (this.bTreatment) {
                        score += 2;
                    } else {
                        score += 3;
                    }
                }

                //Riesgo según score
                if (score < 0) dRisk = 0;//	< 1
                if (score == 0) dRisk = 1;
                if (score == 1) dRisk = 1;
                if (score == 2) dRisk = 1;
                if (score == 3) dRisk = 1;
                if (score == 4) dRisk = 1;
                if (score == 5) dRisk = 2;
                if (score == 6) dRisk = 2;
                if (score == 7) dRisk = 3;
                if (score == 8) dRisk = 4;
                if (score == 9) dRisk = 5;
                if (score == 10) dRisk = 6;
                if (score == 11) dRisk = 8;
                if (score == 12) dRisk = 10;
                if (score == 13) dRisk = 12;
                if (score == 14) dRisk = 16;
                if (score == 15) dRisk = 20;
                if (score == 16) dRisk = 25;
                if (score >= 17) dRisk = 30;//≥ 30
            }

            //Mujeres
            else {
                //Edad
                if (iAge >= 20 && iAge <= 34) score += -7;
                else if (iAge >= 35 && iAge <= 39) score += -3;
                else if (iAge >= 40 && iAge <= 44) score += 0;
                else if (iAge >= 45 && iAge <= 49) score += 3;
                else if (iAge >= 50 && iAge <= 54) score += 6;
                else if (iAge >= 55 && iAge <= 59) score += 8;
                else if (iAge >= 60 && iAge <= 64) score += 10;
                else if (iAge >= 65 && iAge <= 69) score += 12;
                else if (iAge >= 70 && iAge <= 74) score += 14;
                else if (iAge >= 75 && iAge <= 79) score += 16;

                //Colesterol total vs. Edad y Fumador/No fumador
                if (iAge >= 20 && iAge <= 39) {
                    if (dTotalCholesterol < 160) {
                        score += 0;
                    } else if (dTotalCholesterol >= 160 && dTotalCholesterol <= 199) {
                        score += 4;
                    } else if (dTotalCholesterol >= 200 && dTotalCholesterol <= 239) {
                        score += 8;
                    } else if (dTotalCholesterol >= 240 && dTotalCholesterol <= 279) {
                        score += 11;
                    } else if (dTotalCholesterol >= 280) {
                        score += 13;
                    }
                    if (bSmoker) score += 9;
                } else if (iAge >= 40 && iAge <= 49) {
                    if (dTotalCholesterol < 160) {
                        score += 0;
                    } else if (dTotalCholesterol >= 160 && dTotalCholesterol <= 199) {
                        score += 3;
                    } else if (dTotalCholesterol >= 200 && dTotalCholesterol <= 239) {
                        score += 6;
                    } else if (dTotalCholesterol >= 240 && dTotalCholesterol <= 279) {
                        score += 8;
                    } else if (dTotalCholesterol >= 280) {
                        score += 10;
                    }
                    if (bSmoker) score += 7;
                } else if (iAge >= 50 && iAge <= 59) {
                    if (dTotalCholesterol < 160) {
                        score += 0;
                    } else if (dTotalCholesterol >= 160 && dTotalCholesterol <= 199) {
                        score += 2;
                    } else if (dTotalCholesterol >= 200 && dTotalCholesterol <= 239) {
                        score += 4;
                    } else if (dTotalCholesterol >= 240 && dTotalCholesterol <= 279) {
                        score += 5;
                    } else if (dTotalCholesterol >= 280) {
                        score += 7;
                    }
                    if (bSmoker) score += 4;
                } else if (iAge >= 60 && iAge <= 69) {
                    if (dTotalCholesterol < 160) {
                        score += 0;
                    } else if (dTotalCholesterol >= 160 && dTotalCholesterol <= 199) {
                        score += 1;
                    } else if (dTotalCholesterol >= 200 && dTotalCholesterol <= 239) {
                        score += 2;
                    } else if (dTotalCholesterol >= 240 && dTotalCholesterol <= 279) {
                        score += 3;
                    } else if (dTotalCholesterol >= 280) {
                        score += 4;
                    }
                    if (bSmoker) score += 2;
                } else if (iAge >= 70 && iAge <= 79) {
                    if (dTotalCholesterol < 160) {
                        score += 0;
                    } else if (dTotalCholesterol >= 160 && dTotalCholesterol <= 199) {
                        score += 1;
                    } else if (dTotalCholesterol >= 200 && dTotalCholesterol <= 239) {
                        score += 1;
                    } else if (dTotalCholesterol >= 240 && dTotalCholesterol <= 279) {
                        score += 2;
                    } else if (dTotalCholesterol >= 280) {
                        score += 2;
                    }
                    if (bSmoker) score += 1;
                }

                //HDL
                if (iHDLCholesterol>= 60) score += -1;
                else if (iHDLCholesterol >= 50 && iHDLCholesterol <= 59) score += 0;
                else if (iHDLCholesterol >= 40 && iHDLCholesterol <= 49) score += 1;
                else if (iHDLCholesterol < 40) score += 2;

                //Presión sistólica vs. Tratamiento
                if (iSystolicPressure < 120) {
                    if (this.bTreatment) {
                        score += 0;
                    } else {
                        score += 0;
                    }
                } else if (iSystolicPressure >= 120 && iSystolicPressure <= 129) {
                    if (this.bTreatment) {
                        score += 1;
                    } else {
                        score += 3;
                    }
                } else if (iSystolicPressure >= 130 && iSystolicPressure <= 139) {
                    if (this.bTreatment) {
                        score += 2;
                    } else {
                        score += 4;
                    }
                } else if (iSystolicPressure >= 140 && iSystolicPressure <= 159) {
                    if (this.bTreatment) {
                        score += 3;
                    } else {
                        score += 5;
                    }
                } else if (iSystolicPressure >= 160) {
                    if (this.bTreatment) {
                        score += 4;
                    } else {
                        score += 6;
                    }
                }

                //Riesgo según score
                if (score < 9) dRisk = 0;//	< 1
                if (score == 9) dRisk = 1;
                if (score == 10) dRisk = 1;
                if (score == 11) dRisk = 1;
                if (score == 12) dRisk = 1;
                if (score == 13) dRisk = 2;
                if (score == 14) dRisk = 2;
                if (score == 15) dRisk = 3;
                if (score == 16) dRisk = 4;
                if (score == 17) dRisk = 5;
                if (score == 18) dRisk = 6;
                if (score == 19) dRisk = 8;
                if (score == 20) dRisk = 11;
                if (score == 21) dRisk = 14;
                if (score == 22) dRisk = 17;
                if (score == 23) dRisk = 22;
                if (score == 24) dRisk = 27;
                if (score >= 25) dRisk = 30;//≥ 30
            }

            lisDataCalculate.add(dRisk);
        } catch (Exception e) {
            General.printToast(R.string.messages3);
        }
        return lisDataCalculate;

    }

}
