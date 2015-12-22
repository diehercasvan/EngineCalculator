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
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import com.edibca.enginecalculator.R;
import DTO.DTO_Data;
import Class.*;

public class CardiacRisk extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    private View view;
    private Button btnCalculator;
    private FloatingActionButton btnFab;
    private Switch[] switches;
    private ImageView imageLogo;
    private DTO_Data objDTo;
    private PostoperativeRisk postoperativeRisk;
    private SvgCreate svgCreate;

    public CardiacRisk() {

        this.view = null;
        this.btnCalculator = null;
        this.btnFab = null;
        this.switches = new Switch[9];
        this.imageLogo = null;
        this.postoperativeRisk=null;
        this.svgCreate=null;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cardiac_risk, container, false);


        loadView();
        return view;
    }

    public void loadView() {

        imageLogo= (ImageView) view.findViewById(R.id.ImgLogo);
        svgCreate=new SvgCreate(imageLogo, General.iIDLogo);
        svgCreate.builderSVG();

        btnCalculator = (Button) view.findViewById(R.id.BtnCalculator);
        btnFab = (FloatingActionButton) view.findViewById(R.id.BtnFabReload);
        btnFab.setOnClickListener(this);
        btnCalculator.setOnClickListener(this);

        switches[0] = (Switch) view.findViewById(R.id.switch0);
        switches[1] = (Switch) view.findViewById(R.id.switch1);
        switches[2] = (Switch) view.findViewById(R.id.switch2);
        switches[3] = (Switch) view.findViewById(R.id.switch3);
        switches[4] = (Switch) view.findViewById(R.id.switch4);
        switches[5] = (Switch) view.findViewById(R.id.switch5);
        switches[6] = (Switch) view.findViewById(R.id.switch6);
        switches[7] = (Switch) view.findViewById(R.id.switch7);
        switches[8] = (Switch) view.findViewById(R.id.switch8);

        if (switches[0] != null) {
            switches[0].setOnCheckedChangeListener(this);

        }
        if (switches[1] != null) {
            switches[1].setOnCheckedChangeListener(this);

        }
        if (switches[2] != null) {
            switches[2].setOnCheckedChangeListener(this);

        }
        if (switches[3] != null) {
            switches[3].setOnCheckedChangeListener(this);

        }
        if (switches[4] != null) {
            switches[4].setOnCheckedChangeListener(this);

        }
        if (switches[5] != null) {
            switches[5].setOnCheckedChangeListener(this);

        }
        if (switches[6] != null) {
            switches[6].setOnCheckedChangeListener(this);

        }
        if (switches[7] != null) {
            switches[7].setOnCheckedChangeListener(this);

        }
        if (switches[8] != null) {
            switches[8].setOnCheckedChangeListener(this);

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.BtnCalculator:
                loadDataCalculator();
                break;
            case R.id.BtnFabReload:
                clearView();
                break;
        }
    }

    public void clearView() {


        switches[0].setChecked(false);
        switches[1].setChecked(false);
        switches[2].setChecked(false);
        switches[3].setChecked(false);
        switches[4].setChecked(false);
        switches[5].setChecked(false);
        switches[6].setChecked(false);
        switches[7].setChecked(false);
        switches[8].setChecked(false);
    }

    public void loadDialog() {

        DialogResultsFive dialogPerson = new DialogResultsFive();
        dialogPerson.sAnswer=postoperativeRisk.calcularRiesgo();
        FragmentManager fragmentManager = getFragmentManager();
        dialogPerson.show(fragmentManager, "Dialogo Personalizado");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
    public void loadDataCalculator() {

        objDTo = new DTO_Data( switches[0].isChecked(),switches[1].isChecked(),switches[2].isChecked(),switches[3].isChecked(),switches[4].isChecked(),switches[5].isChecked(),switches[6].isChecked(),switches[7].isChecked(),switches[8].isChecked());
        postoperativeRisk=new PostoperativeRisk(objDTo);
        loadDialog();

    }
}
