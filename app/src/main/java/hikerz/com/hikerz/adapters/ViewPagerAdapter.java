package hikerz.com.hikerz.adapters;

/**
 * Created by hborah on 5/21/16.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hborah.smiteme.R;
import com.example.hborah.utils.UtilPhotoStore;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<String> mResources;

    public ViewPagerAdapter(Context mContext, ArrayList<String> mResources) {
        this.mContext = mContext;
        this.mResources = mResources;
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);
        Bitmap bmp = UtilPhotoStore.getImageFromStorage(mContext, mResources.get(position));
        if (bmp == null) {
            // TODO - Fix this case (it will probably not occur if the rest of the code is good)
            // But there is always a change for a bug
            Toast.makeText(mContext, "Could not pick up photo!", Toast.LENGTH_SHORT).show();
            return null;
        }
        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        imageView.setImageBitmap(bmp);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}