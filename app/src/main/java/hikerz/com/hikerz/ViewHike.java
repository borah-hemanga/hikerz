package hikerz.com.hikerz;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

import hikerz.com.hikerz.adapters.ViewPagerAdapter;
import utils.RoundImage;
import utils.UtilPhotoStore;

public class ViewHike extends AppCompatActivity {
    private FrameLayout container;
    private ViewPagerAdapter mAdapter;
    private ViewPager intro_images;
    Map<Long, String> orderedPhotos = new TreeMap<>();
    private ArrayList<Integer> mImageResources = new ArrayList<>();
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ArrayList<ImageView> dots;
    protected View view;
    FriendListAdapter mFriendListAdapter;
    ListView mFriendListView;
    static final String months[] = {"AAA", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    ArrayList<FriendListItem> mFriendsList;
    boolean selected = false;

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

            mAdapter = new ViewPagerAdapter(getApplicationContext(), new ArrayList<Integer>() {{
                add(R.drawable.my_img_empty);
            }});
        } else {
            mAdapter = new ViewPagerAdapter(getApplicationContext(), mImageResources);
        }
        intro_images.setAdapter(mAdapter);

        setUiPageViewController();
        intro_images.setCurrentItem(currPosition);
    }

    private void setUiPageViewController() {
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
    }

    private void hardcodePhotos() {
        mImageResources = new ArrayList<Integer>() {{
            add(R.drawable.my_img_0);
            add(R.drawable.my_img_1);
            add(R.drawable.my_img_2);
            add(R.drawable.my_img_5);
            add(R.drawable.my_img_4);
        }
        };
    }

    void setPeopleArr_3() {
        mFriendsList = new ArrayList<FriendListItem>() {{
            add(new FriendListItem("Rita (15)", BitmapFactory.decodeResource(getResources(), R.drawable.img_rita), "Lets Bike!!!"));
            add(new FriendListItem("Anna (22)", BitmapFactory.decodeResource(getResources(), R.drawable.img_anna), "Lemme show you how to run"));
            add(new FriendListItem("Carter (200)", BitmapFactory.decodeResource(getResources(), R.drawable.img_dude), "Hear hear!!!"));
        }
        };
    }

    void setPeopleArr_2() {
        selected = true;
        mFriendsList = new ArrayList<FriendListItem>() {{
            add(new FriendListItem("Kelly (9)", BitmapFactory.decodeResource(getResources(), R.drawable.img_anita), "Join me at Bay Bridge"));
            add(new FriendListItem("Sammy (30)", BitmapFactory.decodeResource(getResources(), R.drawable.my_img_empty), "A women's right awareness marathon"));
            add(new FriendListItem("Anna (18)", BitmapFactory.decodeResource(getResources(), R.drawable.img_anna), "Weekly Hike"));
            add(new FriendListItem("Jess (22)", BitmapFactory.decodeResource(getResources(), R.drawable.img_jessica), "Dog friendly hike"));
            add(new FriendListItem("Julie (19)", BitmapFactory.decodeResource(getResources(), R.drawable.img_julie), "A race against time"));
        }
        };
    }

    void setPeopleArr_1() {
        mFriendsList = new ArrayList<FriendListItem>() {{
            add(new FriendListItem("Carter (12)", BitmapFactory.decodeResource(getResources(), R.drawable.img_dude), "Long hike, lunch later"));
            add(new FriendListItem("John (8)", BitmapFactory.decodeResource(getResources(), R.drawable.my_img_empty), "Casual hike"));
            add(new FriendListItem("Kelly (11)", BitmapFactory.decodeResource(getResources(), R.drawable.img_rita), "Run for fun"));
        }
        };
    }

    private void setPeople() {
        mFriendListAdapter = new FriendListAdapter(getApplicationContext(), R.layout.friends_list_item, getLayoutInflater(), mFriendsList);
        mFriendListView = (ListView) findViewById(R.id.FriendsList);
        mFriendListView.setAdapter(mFriendListAdapter);
        mFriendListView.setVisibility(View.VISIBLE);
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
            if (!selected) {
                setPeopleArr_2();
            } else {
                setPeopleArr_3();
            }

            setPeople();
        }
    };

    private int year, month, day;
    private Calendar calendar;
    TextView dateView;

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    private void setDatePicker() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        dateView = (TextView)findViewById(R.id.timePicker);
        showDate(year, month+1, day);
    }

    private void showDate(int year, int month, int day) {
        StringBuilder s = new StringBuilder()
                .append(months[month]).append(" ").append(day).append(", ").append(year);
        dateView.setText(s);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hike);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        hardcodePhotos();
        setProfilePhotos(0);
        setPeopleArr_1();
        setPeople();
        setDatePicker();
    }

    class FriendListItem {
        public FriendListItem(String displayName, Bitmap image, String message) {
            this.image = image;
            this.displayName = displayName;
            this.message = message;
        }

        public String displayName;
        public Bitmap image;
        public String message;
    }

    class FriendListAdapter extends ArrayAdapter<FriendListItem> {
        Context mContext;
        int layoutResourceId;

        public void setData(ArrayList<FriendListItem> data) {
            this.data = data;
        }

        ArrayList<FriendListItem> data = null;
        LayoutInflater m_layoutInflater;

        public FriendListAdapter(Context mContext, int layoutResourceId, LayoutInflater layoutInflater, ArrayList<FriendListItem> data) {
            super(mContext, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.mContext = mContext;
            this.data = data;
            this.m_layoutInflater = layoutInflater;
        }

        public FriendListItem getItem(int position){
            return data.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItem = m_layoutInflater.inflate(layoutResourceId, parent, false);
            ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.FriendIcon);
            TextView textViewName = (TextView) listItem.findViewById(R.id.FriendName);
            TextView textViewlatestChat = (TextView) listItem.findViewById(R.id.FriendLatestChat);
            FriendListItem friendItem = data.get(position);
            if(friendItem.image != null) {
                RoundImage roundedImage = new RoundImage(Bitmap.createScaledBitmap(friendItem.image, 120, 120, false));
                imageViewIcon.setImageDrawable(roundedImage);
            }

            textViewName.setText(friendItem.displayName);
            textViewlatestChat.setText(friendItem.message);
            return listItem;
        }
    }
}
