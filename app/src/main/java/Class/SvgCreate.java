package Class;


import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import com.edibca.enginecalculator.R;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

/**
 * Created by DIEGO CASALLAS  on 21/12/2015.
 */
public class SvgCreate {
    private ImageView[] imageViews;
    private int[] iIdSVGs;
    private ImageView imageView;
    private int iIdSVG;
    private SVG svg;
    private Resources resources = General.ACTIVITY.getResources();

    public SvgCreate(ImageView[] imageViews, int[] ints) {

        this.imageViews = imageViews;
        this.iIdSVGs = ints;
        this.svg = null;
    }

    public SvgCreate(ImageView imageView, int i) {

        this.imageView = imageView;
        this.iIdSVG = i;
        this.svg = null;
    }

    public void builderSVGs() {

        try {
            for (int i = 0; i < imageViews.length; i++) {

                svg = SVGParser.getSVGFromResource(resources, iIdSVGs[i]);
                imageViews[i].setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                imageViews[i].setImageDrawable(svg.createPictureDrawable());

            }


        } catch (Exception e) {

        }

    }

    public void builderSVG() {

        try {
            svg = SVGParser.getSVGFromResource(resources, iIdSVG);
            imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            imageView.setImageDrawable(svg.createPictureDrawable());

        } catch (Exception e) {
            General.printToast(R.string.messages6);
        }

    }


}
