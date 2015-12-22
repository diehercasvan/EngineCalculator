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
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.edibca.enginecalculator.R;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import java.util.ArrayList;

/**
 * Created by DIEGO CASALLAS on 07/12/2015.
 */
public class DialogResultsFour extends DialogFragment implements View.OnClickListener {
    private View view;
    private TextView []textViews;
    private ImageView[] imageViews;
    private Dialog dialog;
    private SVG svg;
    public static ArrayList<String> sAnswer =new ArrayList<>();
    private Screen_Email OBJ_screen_email;
    private EditText editTextObservation;
    private ArrayList<String> listSeadPDF;


    public DialogResultsFour() {

        this.view = null;
        this.textViews = new TextView[2];
        this.imageViews = new ImageView[3];
        this.dialog = null;
        this.svg=null;
        this.listSeadPDF=new ArrayList<>();
        this.editTextObservation=null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view_results_five, container, false);
        loadView();
        return view;
    }

    public void loadView() {
        try {

            textViews[0] = (TextView) view.findViewById(R.id.textAnswer);
            textViews[1] = (TextView) view.findViewById(R.id.textViewIMC);

            imageViews[0] = (ImageView) view.findViewById(R.id.Btn_Previous);
            imageViews[1] = (ImageView) view.findViewById(R.id.Btn_Share);
            imageViews[2] = (ImageView) view.findViewById(R.id.imageView);

            imageViews[0].setOnClickListener(this);
            imageViews[1].setOnClickListener(this);


            textViews[0].setText(sAnswer.get(0));
            textViews[1].setText(sAnswer.get(1));


            try {
                svg = SVGParser.getSVGFromResource(getResources(),Integer.parseInt(sAnswer.get(2)) );

            } catch (Exception e) {
                e.printStackTrace();

            }
            imageViews[2].setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            imageViews[2].setImageDrawable(svg.createPictureDrawable());
            editTextObservation=(EditText)view.findViewById(R.id.editTextObservation);

        } catch (Exception e) {
            General.printToast(R.string.messages3);
        }


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
    public String  loadDataPDF(){
        String sDataPDF="";


        listSeadPDF.add(getResources().getString(R.string.title_item4));//Title
        listSeadPDF.add(sAnswer.get(0));
        listSeadPDF.add(sAnswer.get(1));
        listSeadPDF.add(getResources().getString(R.string.observations));
        listSeadPDF.add(editTextObservation.getText().toString());



        sDataPDF="</head>\n" +
                "\n" +
                "<body>\n" +
                "<div  id=\"container\">\n" +
                "  <div id=\"header\">\n" +
                "    <h2>"+listSeadPDF.get(0)+"</h2>\n" +
                "  </div>\n" +
                "  <div id=\"containerGeneral\">\n" +
                "    <div class=\"separator\"></div>\n" +
                "    <div class=\"infoRight\">\n" +
                "      <p>"+listSeadPDF.get(2)+"</p>\n" +
                "    </div>\n" +
                "    <div class=\"infoLeft\">\n" +
                "      <p>"+listSeadPDF.get(1)+"</p>\n" +
                "    </div>\n" +
                "    <div class=\"separator\"></div>\n" +
                "  </div>\n" +
                "  <div class=\"separator\"></div>\n" +
                "  <div id=\"observations\">\n" +
                "    <h4>"+listSeadPDF.get(3)+"</h4>\n" +
                "    <p> "+listSeadPDF.get(4)+"</p>\n" +
                "  </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

        return  sDataPDF;

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }
}
