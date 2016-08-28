package utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by hborah on 5/29/16.
 */
public class UtilPhotoStore {
    public static String saveToInternalStorage(Context applicationCtx, Bitmap bitmapImage, String fileName){
        ContextWrapper cw = new ContextWrapper(applicationCtx);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir(PrimaryConstants.IMAGE_STORE_DIR, Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                Log.e("UtilPhotoStore", "Could not close file");
            }
        }
        return directory.getAbsolutePath();
    }

    public static Bitmap getImageFromStorage(Context applicationCtx, String fileName)
    {
        try {
            ContextWrapper cw = new ContextWrapper(applicationCtx);
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir(PrimaryConstants.IMAGE_STORE_DIR, Context.MODE_PRIVATE);

            File f = new File(directory, fileName);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            return b;
        }
        catch (FileNotFoundException e)
        {
            Log.e("UtilPhotoStore", e.toString());
            return null;
        }
    }

    public static boolean imageExists(Context applicationCtx, String fileName) {
        try {
            ContextWrapper cw = new ContextWrapper(applicationCtx);
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir(PrimaryConstants.IMAGE_STORE_DIR, Context.MODE_PRIVATE);

            File f = new File(directory, fileName);
            return f.exists();
        } catch (Exception e){
            return false;
        }
    }

    public static boolean deleteImage(Context applicationCtx, String fileName) {
        try {
            ContextWrapper cw = new ContextWrapper(applicationCtx);
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir(PrimaryConstants.IMAGE_STORE_DIR, Context.MODE_PRIVATE);

            File f = new File(directory, fileName);
            f.delete();
            return f.exists() == false;
        } catch (Exception e) {
            return false;
        }
    }
}
