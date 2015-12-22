package ViewFragment;

/**
 * Created by DIEGO CASALLAS on 03/12/2015.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import com.edibca.enginecalculator.R;
import Class.*;
import DTO.DTO_Data;


public class SyndromeMetabilic extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    private View view;
    private RadioGroup GroupSex;
    private TextView textWaist;
    private SeekBar seekBarWaist;
    private int iWaist;
    private FloatingActionButton btnFab;
    private EditText[] editTexts;
    private Button btnCalculator;
    private Switch switchHypertension;
    private int selectedId;
    private TableRow[] tableRows;
    private ImageView imageLogo;
    private int iSelectionRadio;
    private DTO_Data objDTo;
    private static int iValidateButton;
    private MetabolicSyndromeCalculation metabolicSyndromeCalculation;
    private SvgCreate svgCreate;


    public SyndromeMetabilic() {

        this.GroupSex = null;
        this.btnCalculator = null;
        this.textWaist = null;
        this.seekBarWaist = null;
        this.iWaist = 0;
        this.editTexts = new EditText[5];
        this.btnFab = null;
        this.btnCalculator = null;
        this.switchHypertension = null;
        this.tableRows = new TableRow[6];
        this.selectedId = 0;
        this.imageLogo = null;
        this.metabolicSyndromeCalculation = null;
        this.svgCreate=null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_syndrome_metabolic, container, false);

        loadView();

        return view;
    }

    public void loadView() {
        imageLogo= (ImageView) view.findViewById(R.id.ImgLogo);
        svgCreate=new SvgCreate(imageLogo, General.iIDLogo);
        svgCreate.builderSVG();

        GroupSex = (RadioGroup) view.findViewById(R.id.GroupSex);
        GroupSex.setOnCheckedChangeListener(this);

        textWaist = (TextView) view.findViewById(R.id.textWaist);
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
                textWaist.setText(String.valueOf(iWaist) + " cm");
                validateButtons(0);


            }
        });
        editTexts[0] = (EditText) view.findViewById(R.id.EdiTrigliceridos);
        editTexts[1] = (EditText) view.findViewById(R.id.EdiHDL_cholesterol);
        editTexts[2] = (EditText) view.findViewById(R.id.EdiGlucosaPressure);
        editTexts[3] = (EditText) view.findViewById(R.id.EdiSystolicPressure);
        editTexts[4] = (EditText) view.findViewById(R.id.EdiDiastolicPressure);

        btnFab = (FloatingActionButton) view.findViewById(R.id.BtnFabReload);
        btnFab.setOnClickListener(this);

        btnCalculator = (Button) view.findViewById(R.id.BtnCalculator);
        btnCalculator.setOnClickListener(this);

        switchHypertension = (Switch) view.findViewById(R.id.switchHypertension);

        if (switchHypertension != null) {
            switchHypertension.setOnCheckedChangeListener(this);

        }
        tableRows[0] = (TableRow) view.findViewById(R.id.Row0);
        tableRows[1] = (TableRow) view.findViewById(R.id.Row1);
        tableRows[2] = (TableRow) view.findViewById(R.id.Row2);
        tableRows[3] = (TableRow) view.findViewById(R.id.Row3);
        tableRows[4] = (TableRow) view.findViewById(R.id.Row4);
        tableRows[5] = (TableRow) view.findViewById(R.id.Row5);


    }

    public void validateButtons(int data) {
        selectedId = GroupSex.getCheckedRadioButtonId();
        if (data == 0) {
            if (selectedId == -1) {
                General.printToast(R.string.messages1);
                clearView();

            } else {

                if (selectedId == R.id.RadioMale) {

                    if (iWaist >= 90) {

                        viewButton(0);
                    } else {

                        viewButton(3);
                    }

                } else {

                    if (iWaist >= 80) {
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

            tableRows[0].setVisibility(View.VISIBLE);
            tableRows[1].setVisibility(View.VISIBLE);
            tableRows[2].setVisibility(View.VISIBLE);
            tableRows[3].setVisibility(View.VISIBLE);
            tableRows[4].setVisibility(View.VISIBLE);
            tableRows[5].setVisibility(View.VISIBLE);
            iValidateButton = 2;

        } else if (iType == 1) {

            tableRows[4].setVisibility(View.VISIBLE);
            tableRows[5].setVisibility(View.VISIBLE);
            iValidateButton = 2;
        } else if (iType == 2) {
            tableRows[4].setVisibility(View.GONE);
            tableRows[5].setVisibility(View.GONE);
            iValidateButton = 3;


        } else {

            tableRows[0].setVisibility(View.GONE);
            tableRows[1].setVisibility(View.GONE);
            tableRows[2].setVisibility(View.GONE);
            tableRows[3].setVisibility(View.GONE);
            tableRows[4].setVisibility(View.GONE);
            tableRows[5].setVisibility(View.GONE);

            iValidateButton = 1;
        }
    }

    @Override
    public void onClick(View v) {
        selectedId = GroupSex.getCheckedRadioButtonId();
        switch (v.getId()) {

            case R.id.BtnCalculator:

                loadDataCalculator();


                break;
            case R.id.BtnFabReload:
                clearView();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        validateButtons(0);
    }

    public void clearView() {
        if (tableRows[5].getVisibility() == View.VISIBLE) {
            editTexts[0].setText("");
            editTexts[1].setText("");
            editTexts[2].setText("");
            editTexts[3].setText("");
            editTexts[4].setText("");
            textWaist.setText("0 cm");
            iWaist = 0;
            seekBarWaist.setProgress(iWaist);
            switchHypertension.setChecked(false);
        }
        if (tableRows[5].getVisibility() == View.VISIBLE) {
            editTexts[0].setText("");
            editTexts[1].setText("");
            editTexts[2].setText("");
            textWaist.setText("0 cm");
            iWaist = 0;
            seekBarWaist.setProgress(iWaist);
            switchHypertension.setChecked(false);
        } else {
            iWaist = 0;
            textWaist.setText("0 cm");
            seekBarWaist.setProgress(0);
            switchHypertension.setChecked(false);

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
                    bValidateSend=true;


                } else {

                    General.printSnackbar(R.string.messages1, getView());
                    bValidateSend=false;
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
                    bValidateSend=true;
                } else {
                    General.printSnackbar(R.string.messages1, getView());
                    bValidateSend=false;
                }
            } else {

                objDTo = new DTO_Data();
                objDTo.setsSex(radioButton.getText().toString());
                objDTo.setsWaist(String.valueOf(iWaist));
                bValidateSend=true;

            }
            if (bValidateSend) {
                loadDialog();
            }
        }


    }

    public void loadDialog() {
        metabolicSyndromeCalculation = new MetabolicSyndromeCalculation(objDTo);

        DialogResultsThree dialogPerson = new DialogResultsThree();
        dialogPerson.bValidate=metabolicSyndromeCalculation.calculate();
        FragmentManager fragmentManager = getFragmentManager();
        dialogPerson.show(fragmentManager, "Dialogo Personalizado");
    }
}
