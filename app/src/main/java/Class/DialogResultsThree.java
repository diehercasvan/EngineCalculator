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

/**
 * Created by DIEGO CASALLAS on 07/12/2015.
 */
public class DialogResultsThree extends DialogFragment implements View.OnClickListener {
    private View view;
    private TextView textViews;
    private ImageView[] imageViews;
    private Dialog dialog;
    public static boolean bValidate =false;
    private Screen_Email OBJ_screen_email;
    private RelativeLayout relativeLayout;


    public DialogResultsThree() {

        this.view = null;
        this.textViews = null;
        this.imageViews = new ImageView[2];
        this.dialog = null;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view_results_four, container, false);
        loadView();
        return view;
    }

    public void loadView() {
        try {
            String sAnswer="";
            textViews = (TextView) view.findViewById(R.id.textViewResult1);
            imageViews[0] = (ImageView) view.findViewById(R.id.Btn_Previous);
            imageViews[1] = (ImageView) view.findViewById(R.id.Btn_Share);
            imageViews[0].setOnClickListener(this);
            imageViews[1].setOnClickListener(this);
            relativeLayout=(RelativeLayout)view.findViewById(R.id.containerDialog);
            if(bValidate){
                sAnswer=getActivity().getResources().getString(R.string.yes);
            }
            else{
                sAnswer=getActivity().getResources().getString(R.string.no);
            }
            textViews.setText(sAnswer);

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
