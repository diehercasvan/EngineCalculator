package Class;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.edibca.enginecalculator.R;
import java.util.ArrayList;

/**
 * Created by DIEGO CASALLAS on 07/12/2015.
 */
public class DialogResults extends DialogFragment implements View.OnClickListener {
    private View view;
    private ProgressBar[] progressBars;
    private TextView[] textViews;
    private ImageView[] imageViews;
    private EditText editTextObservation;
    private ArrayList<String> listSeadPDF;
    private Dialog dialog;
    public  static ArrayList<Double> sAnswer= new ArrayList<>();
    private Screen_Email OBJ_screen_email;




    public DialogResults() {

        this.view = null;
        this.progressBars = new ProgressBar[3];
        this.textViews = new TextView[5];
        this.imageViews = new ImageView[2];
        this.dialog = null;
        this.OBJ_screen_email=null;
        this.listSeadPDF=new ArrayList<>();
        this.editTextObservation=null;


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.view_results_two, container, false);

        loadView();


        return view;
    }

    public void loadView() {
        progressBars[0] = (ProgressBar) view.findViewById(R.id.progressBar1);
        progressBars[1] = (ProgressBar) view.findViewById(R.id.progressBar2);
        progressBars[2] = (ProgressBar) view.findViewById(R.id.progressBar3);

        textViews[0] = (TextView) view.findViewById(R.id.textViewResult1);
        textViews[1] = (TextView) view.findViewById(R.id.textViewResult2);
        textViews[2] = (TextView) view.findViewById(R.id.textViewResult3);
        textViews[3] = (TextView) view.findViewById(R.id.textViewResultYears);
        textViews[4] = (TextView) view.findViewById(R.id.textViewReference);

        imageViews[0] = (ImageView) view.findViewById(R.id.Btn_Previous);
        imageViews[1] = (ImageView) view.findViewById(R.id.Btn_Share);
        imageViews[0].setOnClickListener(this);
        imageViews[1].setOnClickListener(this);

        textViews[0].setText(getResources().getString(R.string.message_risk) + " " + sAnswer.get(0) + "%");
        textViews[1].setText(getResources().getString(R.string.message_risk1) + " " + sAnswer.get(1) + "%");
        textViews[2].setText(getResources().getString(R.string.message_risk2) + " " + sAnswer.get(2) + "%");
        textViews[3].setText("" + new Double(sAnswer.get(3)).intValue());

        progressBars[0].setProgress(new Double(sAnswer.get(0)).intValue());
        progressBars[1].setProgress(new Double(sAnswer.get(1)).intValue());
        progressBars[2].setProgress(new Double(sAnswer.get(2)).intValue());

        editTextObservation=(EditText)view.findViewById(R.id.editTextObservation);
    }
    public String  loadDataPDF(){
        String sDataPDF="";


        listSeadPDF.add(getResources().getString(R.string.title_item1));//Title
        listSeadPDF.add(getResources().getString(R.string.message_risk));
        listSeadPDF.add(sAnswer.get(0) + "%");
        listSeadPDF.add(getResources().getString(R.string.message_risk1));
        listSeadPDF.add(sAnswer.get(1) + "%");
        listSeadPDF.add(getResources().getString(R.string.message_risk2));
        listSeadPDF.add(sAnswer.get(2) + "%");
        listSeadPDF.add(getResources().getString(R.string.message_risk3));
        listSeadPDF.add("" + new Double(sAnswer.get(3)).intValue());
        listSeadPDF.add(getResources().getString(R.string.observations));
        listSeadPDF.add(editTextObservation.getText().toString());



        sDataPDF="</head><body><div id=\"container\"><div id=\"header\"><h2>"+listSeadPDF.get(0)+"</h2>\n" +
                "  </div><div id=\"containerGeneral\"><div class=\"separator\"></div><div class=\"infoRight\">\n" +
                "    <p>"+listSeadPDF.get(2)+"</p>\n" +
                "  </div><div class=\"infoLeft\">\n" +
                "    <p>"+listSeadPDF.get(1)+"</p>\n" +
                "  </div><div class=\"separator\"></div><div class=\"infoRight\">\n" +
                "    <p>"+listSeadPDF.get(4)+"</p></div><div class=\"infoLeft\">\n" +
                "    <p>"+listSeadPDF.get(3)+"</p></div><div class=\"separator\"></div><div class=\"infoRight\">\n" +
                "    <p>"+listSeadPDF.get(6)+"</p></div><div class=\"infoLeft\">\n" +
                "    <p>"+listSeadPDF.get(5)+"</p></div><div class=\"separator\"></div><div class=\"infoRight\">\n" +
                "    <p>"+listSeadPDF.get(8)+"</p></div><div class=\"infoLeft\" >\n" +
                "    <p>"+listSeadPDF.get(7)+"</p></div></div><div class=\"separator\"></div><div id=\"observations\">\n" +
                " \t<h4>"+listSeadPDF.get(9)+"</h4>\n" +
                "    <p>"+listSeadPDF.get(10)+"</p></div></div></body></html>\n";

        return  sDataPDF;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Btn_Previous:
                dialog.dismiss();
                break;
            case R.id.Btn_Share:
                try {
                    OBJ_screen_email = new Screen_Email(loadDataPDF());
                    OBJ_screen_email.createPDF();
                }
                catch (Exception e){
                    General.printToast(R.string.messages4);
                }

                break;
        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }
}
