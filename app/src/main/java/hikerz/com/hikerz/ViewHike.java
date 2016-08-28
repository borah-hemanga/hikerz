package hikerz.com.hikerz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import hikerz.com.hikerz.adapters.ViewPagerAdapter;
import utils.UtilPhotoStore;

public class ViewHike extends AppCompatActivity {
    private FrameLayout container;
    private ViewPagerAdapter mAdapter;
    private ViewPager intro_images;
    Map<Long, String> orderedPhotos = new TreeMap<>();
    private int currPosition;
    private ArrayList<String> mImageResources = new ArrayList<>();
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ArrayList<ImageView> dots;
    protected View view;

    public void setProfilePhotos(int currPosition) {
        container = (FrameLayout) findViewById(R.id.swipeEditPhotoContainer);
        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.page_swipe_viewpager, container);

        intro_images = (ViewPager) findViewById(R.id.pager_introduction);
        if (mImageResources == null || mImageResources.size() == 0) {
            //Image image = new Image(view.findViewById(R.drawable.my_img_empty));
            final String emptyProfilePic = "my_img_empty.jpg";
            if (!UtilPhotoStore.imageExists(getApplicationContext(), emptyProfilePic)) {
                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.my_img_empty);
                UtilPhotoStore.saveToInternalStorage(getApplicationContext(), bm, emptyProfilePic);
            }

            mAdapter = new ViewPagerAdapter(getApplicationContext(), new ArrayList<String>() {{
                add(emptyProfilePic);
            }});
        } else {
            mAdapter = new ViewPagerAdapter(getApplicationContext(), mImageResources);
        }
        intro_images.setAdapter(mAdapter);
        if (currPosition <= 0) {
            currPosition = 0;
        } else if (currPosition >= orderedPhotos.size()) {
            currPosition = orderedPhotos.size() - 1;
        }

        setUiPageViewController(currPosition);
        intro_images.setCurrentItem(currPosition);
        this.currPosition = currPosition;
    }

    private void setUiPageViewController(int selectedPosition) {
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        dotsCount = mAdapter.getCount();
        dots = new ArrayList<ImageView>();

        for (int i = 0; i < dotsCount; i++) {
            dots.add(new ImageView(view.getContext()));
            dots.get(i).setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots.get(i), params);
        }

//            dots.get(selectedPosition).setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hike);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
