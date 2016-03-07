package ViewFragment;
import android.app.Activity;
import android.app.Fragment;
import Class.*;


/**
 * Created by DIEGO CASALLAS on 03/12/2015.
 */
public class ContainerFragment {

    private Fragment fragment;
    private int iSelectionFragment;
    private Activity activity;
    //Object fragment
    private Cardiovascular cardiovascular;
    private CoronaryRisk coronaryRisk;
    private SyndromeMetabilic syndromeMetabilic;
    private BodyMass bodyMass;
    private CardiacRisk cardiacRisk;


    public ContainerFragment(int iSelection){
        this.iSelectionFragment=iSelection;
        this.cardiovascular=null;
        this.coronaryRisk=null;
        this.syndromeMetabilic=null;
        this.bodyMass=null;
        this.cardiacRisk=null;
        activity= General.ACTIVITY;

    }
    public  Fragment selectionFragment(){

        switch(iSelectionFragment)
        {
            case 0:
                fragment=cardiovascular=new Cardiovascular();
                break;
            case 1:
                fragment=coronaryRisk=new CoronaryRisk();
                break;
            case 2:
                fragment=syndromeMetabilic=new SyndromeMetabilic();
                break;
            case 3:
                fragment=bodyMass=new BodyMass();
                break;
            case 4:
                fragment=cardiacRisk=new CardiacRisk();
                break;

        }

        return fragment;


    }
}

