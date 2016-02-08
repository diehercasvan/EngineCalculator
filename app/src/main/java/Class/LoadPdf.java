package Class;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Sistemas2 on 8/02/2016.
 */
public class LoadPdf {

    private InputStream inputStream;
    private OutputStream outputStream;
    private File file;
    private String sUri;
    private AssetManager assetManager;
    private Activity activity;
    private String sNameFile;

    public LoadPdf(String sUri, String sNameFile) {
        this.sUri = sUri;
        this.inputStream = null;
        this.outputStream = null;
        this.file = null;
        this.activity = General.ACTIVITY;
        this.sUri = sUri;
        this.sNameFile = sNameFile;

    }

    public void CopyReadAssets() {
        assetManager = activity.getAssets();

        file = new File(activity.getFilesDir(), sNameFile);
        try {
            inputStream = assetManager.open(sNameFile);
            outputStream = activity.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyFile(inputStream, outputStream);
            inputStream.close();
            inputStream = null;
            outputStream.flush();
            outputStream.close();
            outputStream = null;
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }


        //Toast.makeText(activity, "Is Uri :" + sUri, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(sUri), "application/pdf");
        activity.startActivity(intent);
        Context ctx = activity.getApplicationContext();
        File folder = ctx.getDir("NewFolder", Context.MODE_PRIVATE);
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}
