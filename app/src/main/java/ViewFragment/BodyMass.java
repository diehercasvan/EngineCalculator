package ViewFragment;

/**
 * Created by DIEGO CASALLAS on 03/12/2015.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.edibca.enginecalculator.R;

import DTO.DTO_Data;
import Class.*;


public class BodyMass extends Fragment implements View.OnClickListener {

    private View view;
    private Button btnCalculator;
    private FloatingActionButton btnFab;
    private EditText[] editTexts;
    private int iStature;
    private int iWeight;
    private ImageView imageLogo;
    private DTO_Data objDTo;
    private IMC imc;
    private SvgCreate svgCreate;


    public BodyMass() {
        this.view = null;
        this.btnCalculator = null;
        this.btnFab = null;
        this.iStature = 0;
        this.iWeight = 0;
        this.imageLogo = null;
        this.objDTo = null;
        this.imc = null;
        this.editTexts = new EditText[2];
        this.svgCreate = null;


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_body_mass, container, false);

        loadView();
        return view;
    }

    public void loadView() {


        imageLogo = (ImageView) view.findViewById(R.id.ImgLogo);
        svgCreate = new SvgCreate(imageLogo, General.iIDLogo);
        svgCreate.builderSVG();
        btnCalculator = (Button) view.findViewById(R.id.BtnCalculator);
        btnCalculator.setOnClickListener(this);
        btnFab = (FloatingActionButton) view.findViewById(R.id.BtnFabReload);
        btnFab.setOnClickListener(this);
        editTexts[0] = (EditText) view.findViewById(R.id.editTextStature);
        editTexts[1] = (EditText) view.findViewById(R.id.editTextWeight);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.BtnFabReload:
                clearView();
                break;
            case R.id.BtnCalculator:
                if (validateBoxText()) {
                    loadDataCalculator();

                } else {

                    General.printSnackbar(R.string.messages1, v);
                }

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
    }

    public void loadDialog() {

        DialogResultsFour dialogPerson = new DialogResultsFour();

            dialogPerson.sAnswer = imc.answer();
            FragmentManager fragmentManager = getFragmentManager();
            dialogPerson.show(fragmentManager, "Dialogo Personalizado");

    }

    public void loadDataCalculator() {


        objDTo = new DTO_Data(editTexts[0].getText().toString(), editTexts[1].getText().toString());
        imc = new IMC(objDTo);
        loadDialog();

    }
}
