package hikerz.com.hikerz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class Hikerz extends AppCompatActivity {
    private AutoCompleteTextView actv;
    ArrayAdapter<String> adapter;

    String[] COUNTRIES = new String[] {
            "#popular", "#friends", "#yosemite", "#bayarea", "Big Basin", "Big Sur", "#favorites",
            "#myhikes", "#myfriends"
    };

    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

    private void addOptions() {

        actv = (AutoCompleteTextView) findViewById(R.id.searchField);
        adapter = new ArrayAdapter<String>(this,
                R.layout.list_view_row, COUNTRIES);
        actv.setAdapter(adapter);

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                findMatches(adapter.getItem(position).toString());
                hideKeyboard();
            }
        });

        actv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (actv.getRight() - actv.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) - 25) {
                        findMatches(actv.getText().toString());
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public void findMatches(String location)
    {
        Intent intent = new Intent(this, MatchView.class);
        intent.putExtra("LOCATION", location);
        startActivity(intent);
    }
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hikerz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        addOptions();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hikerz, menu);
        return true;
    }
}
