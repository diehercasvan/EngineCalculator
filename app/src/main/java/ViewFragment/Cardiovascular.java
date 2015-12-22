package ViewFragment;

/**
 * Created by DIEGO CASALLAS on 03/12/2015.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
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
import android.widget.Switch;
import com.edibca.enginecalculator.R;
import com.larvalabs.svgandroid.SVG;
import java.util.ArrayList;
import Class.*;
import DTO.DTO_Data;


public class Cardiovascular extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    private View view;
    private RadioGroup GroupSex;
    private Button btnCalculator;
    private FloatingActionButton btnFab;
    private EditText[] editTexts;
    private Switch[] switches;
    private int iSelectionRadio;
    private ImageView imageLogo;
    private SVG svg;
    private DTO_Data objDTo;
    private CardiovascularRiskCalculation cardiovascularRiskCalculation;
    private SvgCreate svgCreate;

    public Cardiovascular() {

        this.view = null;
        this.GroupSex = null;
        this.btnCalculator = null;
        this.btnFab = null;
        this.editTexts = new EditText[4];
        this.switches = new Switch[3];
        this.imageLogo = null;
        this.svg = null;
        this.objDTo = null;
        this.cardiovascularRiskCalculation = null;
        this.svgCreate=null;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cardiovascular, container, false);


        loadView();
        return view;
    }

    public void loadView() {


        imageLogo= (ImageView) view.findViewById(R.id.ImgLogo);
        svgCreate=new SvgCreate(imageLogo, General.iIDLogo);
        svgCreate.builderSVG();

        GroupSex = (RadioGroup) view.findViewById(R.id.GroupSex);
        GroupSex.setOnCheckedChangeListener(this);
        editTexts[0] = (EditText) view.findViewById(R.id.EdiAge);
        editTexts[1] = (EditText) view.findViewById(R.id.EdiSystolicPressure);
        editTexts[2] = (EditText) view.findViewById(R.id.EdiCholesterolHDL);
        editTexts[3] = (EditText) view.findViewById(R.id.EdiCholesterolTotal);
        btnCalculator = (Button) view.findViewById(R.id.BtnCalculator);
        btnFab = (FloatingActionButton) view.findViewById(R.id.BtnFabReload);
        btnFab.setOnClickListener(this);
        btnCalculator.setOnClickListener(this);

        switches[0] = (Switch) view.findViewById(R.id.switchHypertension);
        switches[1] = (Switch) view.findViewById(R.id.switchSmoke);
        switches[2] = (Switch) view.findViewById(R.id.switchDiabetes);

        if (switches[0] != null) {
            switches[0].setOnCheckedChangeListener(this);

        }
        if (switches[1] != null) {
            switches[1].setOnCheckedChangeListener(this);

        }
        if (switches[2] != null) {
            switches[2].setOnCheckedChangeListener(this);

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.BtnCalculator:
                if (validateBoxText()) {
                    loadDataCalculator();

                } else {

                    General.printSnackbar(R.string.messages1, v);
                }
                break;
            case R.id.BtnFabReload:
                clearView();
                break;
        }
    }

    public boolean validateBoxText() {

        boolean bValidateBoxText = false;
        if (General.validateTypeString(editTexts)) {
            if (General.validateEmpty(editTexts)) {
                bValidateBoxText = true;
            }
        }
        return bValidateBoxText;

    }

    public void clearView() {

        editTexts[0].setText("");
        editTexts[1].setText("");
        editTexts[2].setText("");
        editTexts[3].setText("");
        switches[0].setChecked(false);
        switches[1].setChecked(false);
        switches[2].setChecked(false);
    }

    public void loadDataCalculator() {

        iSelectionRadio = GroupSex.getCheckedRadioButtonId();
        if (iSelectionRadio == -1) {
            General.printToast(R.string.messages1);

        } else {

            RadioButton radioButton = (RadioButton) view.findViewById(iSelectionRadio);
            objDTo = new DTO_Data(editTexts[0].getText().toString(), switches[1].isChecked(), switches[2].isChecked(), switches[0].isChecked(), radioButton.getText().toString(), editTexts[2].getText().toString(), editTexts[3].getText().toString(), editTexts[1].getText().toString());
            loadDialog();

        }


    }

    public void loadDialog() {

        ArrayList<Double> listResult;
        cardiovascularRiskCalculation = new CardiovascularRiskCalculation(objDTo);

        listResult = cardiovascularRiskCalculation.calculate();
        double riskVal = listResult.get(0);
        DialogResults dialogPerson = new DialogResults();
        listResult.add(calcHeartAge(riskVal, editTexts[0].getText().toString()));
        dialogPerson.list = listResult;
        FragmentManager fragmentManager = getFragmentManager();
        dialogPerson.show(fragmentManager, "Dialogo Personalizado");

    }

    private double calcHeartAge(double riskVal, String gender) {
        double loAge = 10;  // no real minimum bound, but 10 is a practical one
        double hiAge = 86;          // 85 is max
        double testAge = 10;
        ArrayList<Double> testRisk =new ArrayList<>();
        CardiovascularRiskCalculation testRiesgoCardio;


        // threshold should be < half of the desired accuracy (.5 in this case)
        while ((hiAge - loAge) > .2) {
            testAge = (hiAge + loAge) / 2.0;

            objDTo.setsAge(String.valueOf((int) Math.round(testAge)));
            testRiesgoCardio = new CardiovascularRiskCalculation(objDTo);
            testRisk = testRiesgoCardio.calculate();

            if (testRisk.get(2) < riskVal) {
                loAge = testAge;
            } else if (testRisk.get(2) > riskVal) {
                hiAge = testAge;
            } else {
                hiAge = testAge;
                loAge = testAge;
            }

        }

        return testAge;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
