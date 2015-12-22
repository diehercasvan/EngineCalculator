package Class;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
    private RelativeLayout relativeLayout;


    public DialogResultsFour() {

        this.view = null;
        this.textViews = new TextView[2];
        this.imageViews = new ImageView[3];
        this.dialog = null;
        this.svg=null;
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
            relativeLayout=(RelativeLayout)view.findViewById(R.id.containerDialog);

            try {
                svg = SVGParser.getSVGFromResource(getResources(), Integer.parseInt(sAnswer.get(2)));

            } catch (Exception e) {
                e.printStackTrace();

            }
            imageViews[2].setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            imageViews[2].setImageDrawable(svg.createPictureDrawable());

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
                    OBJ_screen_email = new Screen_Email(relativeLayout);
                    OBJ_screen_email.createScreen();
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
