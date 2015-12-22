package ViewFragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import Class.*;
import com.edibca.enginecalculator.R;

/**
 * Created by DIEGO CASALLAS on 18/12/2015.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView[] imageViews;
    private MyCallback myCallback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            myCallback = (MyCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ " must implement MyCallback");
        }
    }

    public HomeFragment() {

        this.view = null;
        this.imageViews = new ImageView[5];
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        loadView();
        return view;
    }

    public void loadView() {
        try {
            imageViews[0] = (ImageView) view.findViewById(R.id.btnItem_0);
            imageViews[1] = (ImageView) view.findViewById(R.id.btnItem_1);
            imageViews[2] = (ImageView) view.findViewById(R.id.btnItem_2);
            imageViews[3] = (ImageView) view.findViewById(R.id.btnItem_3);
            imageViews[4] = (ImageView) view.findViewById(R.id.btnItem_4);

            imageViews[0].setOnClickListener(this);
            imageViews[1].setOnClickListener(this);
            imageViews[2].setOnClickListener(this);
            imageViews[3].setOnClickListener(this);
            imageViews[4].setOnClickListener(this);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error _:" + e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onClick(View v) {
        int iSelectionView = 0;
        switch (v.getId()) {
            case R.id.btnItem_0:
                iSelectionView = 0;
                break;
            case R.id.btnItem_1:
                iSelectionView = 1;
                break;
            case R.id.btnItem_2:
                iSelectionView = 2;
                break;
            case R.id.btnItem_3:
                iSelectionView = 3;
                break;
            case R.id.btnItem_4:
                iSelectionView = 4;
                break;
        }
       myCallback.onclickMenu(iSelectionView);
        selectionTouhc();
    }
    public void selectionTouhc(){

        for(int i=0;i<imageViews.length;i++){
            imageViews[i].setEnabled(false);
        }

    }

}
