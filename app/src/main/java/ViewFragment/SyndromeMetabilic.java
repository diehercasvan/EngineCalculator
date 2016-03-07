package ViewFragment;

/**
 * Created by DIEGO CASALLAS on 03/12/2015.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.edibca.enginecalculator.R;

import Class.*;
import DTO.DTO_Data;


public class SyndromeMetabilic extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    private View view;
    private RadioGroup GroupSex;
    private SeekBar seekBarWaist;
    private int iWaist = 0;
    private FloatingActionButton btnFab;
    private EditText[] editTexts;
    private Button btnCalculator;
    private Switch switchHypertension;
    private int selectedId;
    private ImageView imageLogo;
    private int iSelectionRadio;
    private DTO_Data objDTo;
    private static int iValidateButton;
    private MetabolicSyndromeCalculation metabolicSyndromeCalculation;
    private SvgCreate svgCreate;
    private RelativeLayout[] relativeLayouts;


    public SyndromeMetabilic() {

        this.GroupSex = null;
        this.btnCalculator = null;
        this.seekBarWaist = null;
        this.editTexts = new EditText[6];
        this.btnFab = null;
        this.btnCalculator = null;
        this.switchHypertension = null;
        this.selectedId = 0;
        this.imageLogo = null;
        this.metabolicSyndromeCalculation = null;
        this.svgCreate = null;
        this.relativeLayouts = new RelativeLayout[2];
        this.view = null;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_syndrome_metabolic, container, false);
        loadView();
        clearView();
        return view;
    }

    public void loadView() {
        relativeLayouts[0] = (RelativeLayout) view.findViewById(R.id.contenButton);
        relativeLayouts[1] = (RelativeLayout) view.findViewById(R.id.contenButtonStart);
        imageLogo = (ImageView) view.findViewById(R.id.ImgLogo);
        svgCreate = new SvgCreate(imageLogo, General.iIDLogo);
        svgCreate.builderSVG();
        GroupSex = (RadioGroup) view.findViewById(R.id.GroupSex);
        GroupSex.setOnCheckedChangeListener(this);
        editTexts[0] = (EditText) view.findViewById(R.id.EdiTrigliceridos);
        editTexts[1] = (EditText) view.findViewById(R.id.EdiHDL_cholesterol);
        editTexts[2] = (EditText) view.findViewById(R.id.EdiGlucosaPressure);
        editTexts[3] = (EditText) view.findViewById(R.id.EdiSystolicPressure);
        editTexts[4] = (EditText) view.findViewById(R.id.EdiDiastolicPressure);
        editTexts[5] = (EditText) view.findViewById(R.id.EdiWaist);
        editTexts[5].setOnClickListener(this);

        seekBarWaist = (SeekBar) view.findViewById(R.id.seekBarWaist);
        seekBarWaist.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                iWaist = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(iWaist>0){
                    editTexts[5].setVisibility(View.VISIBLE);
                }
                else{
                    editTexts[5].setVisibility(View.GONE);
                }
                editTexts[5].setText(String.valueOf(iWaist));
                validateButtons(0);



            }
        });


        btnFab = (FloatingActionButton) view.findViewById(R.id.BtnFabReload);
        btnFab.setOnClickListener(this);

        btnCalculator = (Button) view.findViewById(R.id.BtnCalculator);
        btnCalculator.setOnClickListener(this);

        switchHypertension = (Switch) view.findViewById(R.id.switchHypertension);

        if (switchHypertension != null) {
            switchHypertension.setOnCheckedChangeListener(this);

        }


    }

    public void validateButtons(int data) {
        selectedId = GroupSex.getCheckedRadioButtonId();
        if (data == 0) {
            if (selectedId == -1) {
                General.printToast(R.string.messages1);
                clearView();

            } else {

                if (selectedId == R.id.RadioMale) {

                    if (iWaist >= 94) {

                        viewButton(0);
                    } else {

                        viewButton(3);
                    }

                } else {

                    if (iWaist >= 90) {
                        viewButton(0);

                    } else {

                        viewButton(3);
                    }
                }
            }
        }

    }

    public void viewButton(int iType) {

        if (iType == 0) {

            relativeLayouts[1].setVisibility(View.VISIBLE);
            iValidateButton = 2;

        } else if (iType == 1) {

            relativeLayouts[0].setVisibility(View.VISIBLE);
            iValidateButton = 2;
        } else if (iType == 2) {
            relativeLayouts[0].setVisibility(View.GONE);
            iValidateButton = 3;

        } else {

            relativeLayouts[1].setVisibility(View.GONE);

            iValidateButton = 1;
        }
    }

    @Override
    public void onClick(View v) {
        selectedId = GroupSex.getCheckedRadioButtonId();
        switch (v.getId()) {

            case R.id.BtnCalculator:
                if (editTexts[5].getText().toString().equals("0")) {
                    General.printSnackbar(R.string.messages1, getView());
                } else {
                    loadDataCalculator();
                }

                break;
            case R.id.BtnFabReload:
                clearView();
                break;
            case R.id.EdiWaist:
                String dataText = editTexts[5].getText().toString();
                try {
                    if (iWaist > 0) {
                        if (dataText.substring(0, 1).equals("0")) {
                            dataText = dataText.substring(1, dataText.length());
                            editTexts[5].setText(dataText);
                        }
                        int ediProgress = Integer.parseInt(dataText);
                        if (ediProgress <= 300) {
                            seekBarWaist.setProgress(Integer.parseInt(dataText));
                            iWaist = ediProgress;
                            validateButtons(0);
                        } else {
                            editTexts[5].setText("300");
                            iWaist = 300;
                            validateButtons(0);
                            seekBarWaist.setProgress(iWaist);
                        }

                    }
                    else{
                        editTexts[5].setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    clearView();
                }


                break;
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        validateButtons(0);
    }

    public void clearView() {

        if (relativeLayouts[1].getVisibility() == View.VISIBLE) {
            editTexts[0].setText("");
            editTexts[1].setText("");
            editTexts[2].setText("");
            editTexts[3].setText("");
            editTexts[4].setText("");
            editTexts[5].setText("0");
            iWaist = 0;
            seekBarWaist.setProgress(iWaist);
            switchHypertension.setChecked(false);
            editTexts[5].setVisibility(View.GONE);
        } else {
            iWaist = 0;
            editTexts[5].setText("0");
            seekBarWaist.setProgress(0);
            switchHypertension.setChecked(false);
            editTexts[5].setVisibility(View.GONE);
        }
        viewButton(3);

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            viewButton(2);
        } else {
            viewButton(1);
        }

    }

    public boolean validateBoxText(EditText[] texts) {

        boolean bValidateBoxText = false;
        if (General.validateTypeString(texts)) {
            if (General.validateEmpty(texts)) {
                bValidateBoxText = true;
            }
        }
        return bValidateBoxText;

    }

    public void loadDataCalculator() {
        boolean bValidateSend = false;
        iSelectionRadio = GroupSex.getCheckedRadioButtonId();
        if (iSelectionRadio == -1) {
            General.printToast(R.string.messages1);
        } else {
            RadioButton radioButton = (RadioButton) view.findViewById(iSelectionRadio);
            EditText[] editTexts1 = null;
            if (iValidateButton == 1) {
                radioButton.getText();
            } else if (iValidateButton == 2) {
                radioButton.getText();
                editTexts1 = editTexts;
            } else if (iValidateButton == 3) {
                radioButton.getText();
                editTexts1 = new EditText[3];
                editTexts1[0] = editTexts[0];
                editTexts1[1] = editTexts[1];
                editTexts1[2] = editTexts[2];
            }
            if (iValidateButton == 2) {

                if (validateBoxText(editTexts1)) {
                    objDTo = new DTO_Data(radioButton.getText().toString(), switchHypertension.isChecked(), editTexts[2].getText().toString(), editTexts[4].getText().toString(), editTexts[3].getText().toString(), editTexts[1].getText().toString(), String.valueOf(iWaist), editTexts[0].getText().toString());
                    bValidateSend = true;


                } else {

                    General.printSnackbar(R.string.messages1, getView());
                    bValidateSend = false;
                }
            } else if (iValidateButton == 3) {
                if (validateBoxText(editTexts1)) {
                    objDTo = new DTO_Data();
                    objDTo.setsSex(radioButton.getText().toString());
                    objDTo.setbHypertensionTreatment(switchHypertension.isChecked());
                    objDTo.setsGlucose(editTexts[2].getText().toString());
                    objDTo.setsCholesterolHDL(editTexts[1].getText().toString());
                    objDTo.setsWaist(String.valueOf(iWaist));
                    objDTo.setsTriglycerides(editTexts[0].getText().toString());
                    bValidateSend = true;
                } else {
                    General.printSnackbar(R.string.messages1, getView());
                    bValidateSend = false;
                }
            } else {

                objDTo = new DTO_Data();
                objDTo.setsSex(radioButton.getText().toString());
                objDTo.setsWaist(String.valueOf(iWaist));
                bValidateSend = true;

            }
            if (bValidateSend) {
                loadDialog();
            }
        }


    }

    public void loadDialog() {
        metabolicSyndromeCalculation = new MetabolicSyndromeCalculation(objDTo);

        DialogResultsThree dialogPerson = new DialogResultsThree();
        dialogPerson.bValidate = metabolicSyndromeCalculation.calculate();
        FragmentManager fragmentManager = getFragmentManager();
        dialogPerson.show(fragmentManager, "Dialogo Personalizado");
    }
}
