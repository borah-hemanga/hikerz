package hikerz.com.hikerz;


import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import hikerz.com.hikerz.fragments.FragmentInteractionListener;
import hikerz.com.hikerz.fragments.MatchListViewFragment;
import hikerz.com.hikerz.fragments.MatchMapView;

public class MatchView extends AppCompatActivity implements FragmentInteractionListener, OnMapReadyCallback{

    String data= "[     {        \"Location\":\"Millard Canyon Falls\",      \"Latitude\":38.9355199,      \"Longitude\":-112.2324347   },   {        \"Location\":\"Will Thrall Peak\",      \"Latitude\":34.3847207,      \"Longitude\":-117.9028421   },   {        \"Location\":\"Cooper Canyon Falls\",      \"Latitude\":34.3608678,      \"Longitude\":-117.902441   },   {        \"Location\":\"Hermit Falls\",      \"Latitude\":34.1912347,      \"Longitude\":-118.0170987   },   {        \"Location\":\"Fish Canyon Falls\",      \"Latitude\":34.1690043,      \"Longitude\":-117.9261276   },   {        \"Location\":\"South Mount Hawkins\",      \"Latitude\":34.3113909,      \"Longitude\":-117.8103382   },   {        \"Location\":\"Smith Mountain\",      \"Latitude\":36.127442,      \"Longitude\":-118.2248053   },   {        \"Location\":\"Mount Islip\",      \"Latitude\":34.3450005,      \"Longitude\":-117.8397841   },   {        \"Location\":\"Mount Baden-Powell\",      \"Latitude\":34.3586117,      \"Longitude\":-117.764504   },   {        \"Location\":\"Bighorn Peak\",      \"Latitude\":44.25441,      \"Longitude\":-107.1072842   },   {        \"Location\":\"Mount Wilson\",      \"Latitude\":37.8391607,      \"Longitude\":-107.9914581   },   {        \"Location\":\"Mount Markham\",      \"Latitude\":34.2369477,      \"Longitude\":-118.0989587   },   {        \"Location\":\"Waterman Mountain Loop\",      \"Latitude\":32.3906599,      \"Longitude\":-111.427113   },   {        \"Location\":\"Kratka Ridge\",      \"Latitude\":34.3466666,      \"Longitude\":-117.8997222   },   {        \"Location\":\"Sunset Peak\",      \"Latitude\":44.8560267,      \"Longitude\":-112.1466529   },   {        \"Location\":\"The Devil&#8217;s Chair\",      \"Latitude\":44.2777942,      \"Longitude\":-73.2178253   },   {        \"Location\":\"Iron Mountain (#2)\",      \"Latitude\":45.8402212,      \"Longitude\":-88.0876547   },   {        \"Location\":\"Echo Mountain and Inspiration Point\",      \"Latitude\":34.2215285,      \"Longitude\":-118.1093724   },   {        \"Location\":\"Mount Hillyer\",      \"Latitude\":34.3494435,      \"Longitude\":-118.015901   },   {        \"Location\":\"Mount Gleason\",      \"Latitude\":34.3766644,      \"Longitude\":-118.177296   },   {        \"Location\":\"Strawberry Peak\",      \"Latitude\":34.2836126,      \"Longitude\":-118.1203487   },   {        \"Location\":\"Pacifico Mountain\",      \"Latitude\":34.3822201,      \"Longitude\":-118.0347909   },   {        \"Location\":\"Icehouse Canyon\",      \"Latitude\":37.4458496,      \"Longitude\":-103.4796644   },   {        \"Location\":\"Fox Mountain\",      \"Latitude\":37.5061194,      \"Longitude\":-106.7439309   },   {        \"Location\":\"Josephine Peak\",      \"Latitude\":47.2199271,      \"Longitude\":-114.4934552   },   {        \"Location\":\"San Gabriel Peak\",      \"Latitude\":34.2433363,      \"Longitude\":-118.0984032   },   {        \"Location\":\"Mount Lawlor\",      \"Latitude\":34.2708353,      \"Longitude\":-118.1039592   },   {        \"Location\":\"Upper Big Sycamore Canyon\",      \"Latitude\":33.975699,      \"Longitude\":-117.3179816   },   {        \"Location\":\"Nicholas Flat\",      \"Latitude\":34.0665662,      \"Longitude\":-118.9126083   },   {        \"Location\":\"Wildwood Regional Park\",      \"Latitude\":40.831339,      \"Longitude\":-74.357468   },   {        \"Location\":\"Rocky Oaks Park\",      \"Latitude\":44.2949554,      \"Longitude\":-111.4566608   },   {        \"Location\":\"Paramount Ranch\",      \"Latitude\":34.1155816,      \"Longitude\":-118.7562884   },   {        \"Location\":\"Las Virgenes Canyon\",      \"Latitude\":34.1722273,      \"Longitude\":-118.7023111   },   {        \"Location\":\"Victory head Loop\",      \"Latitude\":44.0762472,      \"Longitude\":-86.3746629   },   {        \"Location\":\"Cheeseboro and Palo Comado Canyons\",      \"Latitude\":34.1837788,      \"Longitude\":-118.7372922   },   {        \"Location\":\"King Gillette Ranch\",      \"Latitude\":34.1018989,      \"Longitude\":-118.704936   },   {        \"Location\":\"Corral Canyon\",      \"Latitude\":34.0336184,      \"Longitude\":-118.7342532   },   {        \"Location\":\"Escondido Falls\",      \"Latitude\":34.0259721,      \"Longitude\":-118.7797592   },   {        \"Location\":\"Trebek Open Space\",      \"Latitude\":34.1104851,      \"Longitude\":-118.3589785   },   {        \"Location\":\"Murphy Ranch\",      \"Latitude\":34.072382,      \"Longitude\":-118.5138881   },   {        \"Location\":\"Towsley Canyon\",      \"Latitude\":34.3605542,      \"Longitude\":-118.5548086   },   {        \"Location\":\"Charmlee Wilderness Park\",      \"Latitude\":34.0618131,      \"Longitude\":-118.8770557   },   {        \"Location\":\"Malibu Creek\",      \"Latitude\":34.0625028,      \"Longitude\":-118.6997438   },   {        \"Location\":\"Fryman Canyon\",      \"Latitude\":34.1426344,      \"Longitude\":-118.3936808   },   {        \"Location\":\"Mugu Peak\",      \"Latitude\":34.0925068,      \"Longitude\":-119.0548238   },   {        \"Location\":\"Old Boney \",      \"Latitude\":35.5392428,      \"Longitude\":-82.0576413   },   {        \"Location\":\"La Jolla Canyon\",      \"Latitude\":33.0097838,      \"Longitude\":-109.4639644   },   {        \"Location\":\"Solstice Canyon\",      \"Latitude\":34.0484269,      \"Longitude\":-118.7576968   },   {        \"Location\":\"Runyon Canyon\",      \"Latitude\":34.1106805,      \"Longitude\":-118.3503784   },   {        \"Location\":\"Grotto \",      \"Latitude\":47.737412,      \"Longitude\":-121.4238318   },   {        \"Location\":\"Engelmann Oak Loop\",      \"Latitude\":33.5845893,      \"Longitude\":-117.2243587   },   {        \"Location\":\"Vista Hermosa Natural Park\",      \"Latitude\":34.0619569,      \"Longitude\":-118.2579459   },   {        \"Location\":\"Double Peak\",      \"Latitude\":33.1094846,      \"Longitude\":-117.1775343   },   {        \"Location\":\"Saddleback Butte State Park\",      \"Latitude\":34.6895546,      \"Longitude\":-117.8236985   },   {        \"Location\":\"Baldwin Hills Scenic Overlook\",      \"Latitude\":34.0175826,      \"Longitude\":-118.3840379   },   {        \"Location\":\"Franklin Canyon\",      \"Latitude\":36.2691161,      \"Longitude\":-118.968989   },   {        \"Location\":\"Ernest E. Debs Regional Park\",      \"Latitude\":34.096562,      \"Longitude\":-118.1956408   },   {        \"Location\":\"Angel&#8217;s Point\",      \"Latitude\":33.8288321,      \"Longitude\":-85.8372015   },   {        \"Location\":\"Elysian Park West Loop\",      \"Latitude\":44.1995343,      \"Longitude\":-93.6781788   },   {        \"Location\":\"Vasquez Rocks\",      \"Latitude\":34.4855487,      \"Longitude\":-118.3173013   },   {        \"Location\":\"Antelope Valley California Poppy Reserve\",      \"Latitude\":34.7349045,      \"Longitude\":-118.3960096   },   {        \"Location\":\"Eaton Canyon\",      \"Latitude\":34.16224,      \"Longitude\":-118.085   },   {        \"Location\":\"Beaudry Loop in the Verdugo Mountains\",      \"Latitude\":34.187829,      \"Longitude\":-118.2523225   },   {        \"Location\":\"Fryman Canyon\",      \"Latitude\":34.1426344,      \"Longitude\":-118.3936808   },   {        \"Location\":\"Runyon Canyon\",      \"Latitude\":34.1106805,      \"Longitude\":-118.3503784   },   {        \"Location\":\"Taco Peak\",      \"Latitude\":33.8555532,      \"Longitude\":-112.1378733   },   {        \"Location\":\"Amir&#8217;s Garden\",      \"Latitude\":37.980267,      \"Longitude\":-100.850223   },   {        \"Location\":\"Western Canyon\",      \"Latitude\":34.9082274,      \"Longitude\":-101.8844273   },   {        \"Location\":\"Glendale Peak\",      \"Latitude\":35.6576751,      \"Longitude\":-78.7349524   },   {        \"Location\":\"Firebreak  to Griffith Observatory\",      \"Latitude\":34.1145321,      \"Longitude\":-118.3071022   },   {        \"Location\":\"East Griffith Observatory \",      \"Latitude\":34.1184341,      \"Longitude\":-118.3003935   },   {        \"Location\":\"West Griffith Observatory \",      \"Latitude\":34.1184341,      \"Longitude\":-118.3003935   },   {        \"Location\":\"Beacon Hill in Griffith Park\",      \"Latitude\":34.1184525,      \"Longitude\":-118.2737583   },   {        \"Location\":\"Hollywood Reservoir\",      \"Latitude\":34.1197689,      \"Longitude\":-118.3308532   },   {        \"Location\":\"Mount Hollywood\",      \"Latitude\":34.1280637,      \"Longitude\":-118.3011874   },   {        \"Location\":\"Del Mar Mesa Preserve\",      \"Latitude\":32.9512588,      \"Longitude\":-117.1625037   },   {        \"Location\":\"Indianhead Mountain\",      \"Latitude\":49.6644444,      \"Longitude\":-125.2497222   },   {        \"Location\":\"Gonzales Canyon\",      \"Latitude\":36.9166933,      \"Longitude\":-105.0627784   },   {        \"Location\":\"Barker Valley\",      \"Latitude\":32.8473958,      \"Longitude\":-85.2048894   },   {        \"Location\":\"Lake Calavera\",      \"Latitude\":33.1711358,      \"Longitude\":-117.2839609   },   {        \"Location\":\"Canyon Sin Nombre\",      \"Latitude\":32.8491627,      \"Longitude\":-116.1538663   },   {        \"Location\":\"Viejas Mountain\",      \"Latitude\":32.8611624,      \"Longitude\":-116.7258546   },   {        \"Location\":\"Black Mountain\",      \"Latitude\":35.6178951,      \"Longitude\":-82.3212302   },   {        \"Location\":\"Boucher Hill Loop\",      \"Latitude\":44.6132876,      \"Longitude\":-87.6931983   },   {        \"Location\":\"Corte Madera Mountain\",      \"Latitude\":32.7575535,      \"Longitude\":-116.5911294   },   {        \"Location\":\"Engelmann Oak Loop\",      \"Latitude\":33.5845893,      \"Longitude\":-117.2243587   },   {        \"Location\":\"Boden Canyon\",      \"Latitude\":33.0922646,      \"Longitude\":-116.8966937   },   {        \"Location\":\"Whale Peak\",      \"Latitude\":33.0292139,      \"Longitude\":-116.3161234   },   {        \"Location\":\"Mountain Palm Springs\",      \"Latitude\":32.8642201,      \"Longitude\":-116.233348   },   {        \"Location\":\"Pinyon Ridge\",      \"Latitude\":33.1780555,      \"Longitude\":-116.4552777   },   {        \"Location\":\"Split Mountain\",      \"Latitude\":37.0202851,      \"Longitude\":-118.4218804   },   {        \"Location\":\"Stanley Peak\",      \"Latitude\":51.1699999,      \"Longitude\":-116.0533333   },   {        \"Location\":\"El Cajon Mountain\",      \"Latitude\":32.9147711,      \"Longitude\":-116.8197465   },   {        \"Location\":\"Bow Willow and Rockhouse Canyons\",      \"Latitude\":32.842349,      \"Longitude\":-116.225824   },   {        \"Location\":\"Guajome County Park\",      \"Latitude\":33.2448343,      \"Longitude\":-117.2750294   },   {        \"Location\":\"San Onofre State Beach\",      \"Latitude\":33.3761505,      \"Longitude\":-117.5693128   },   {        \"Location\":\"Hot Springs Mountain\",      \"Latitude\":33.3150362,      \"Longitude\":-116.5797401   },   {        \"Location\":\"Bernardo Mountain and Lake Hodges\",      \"Latitude\":33.0639309,      \"Longitude\":-117.088088   },   {        \"Location\":\"Wooded Hill\",      \"Latitude\":33.7623059,      \"Longitude\":-89.8231168   },   {        \"Location\":\"Guatay Mountain\",      \"Latitude\":32.8433842,      \"Longitude\":-116.5741849   },   {        \"Location\":\"Double Peak\",      \"Latitude\":33.1094846,      \"Longitude\":-117.1775343   },   {        \"Location\":\"Eagle Rock\",      \"Latitude\":34.1322469,      \"Longitude\":-118.2117257   },   {        \"Location\":\"Stonewall Peak\",      \"Latitude\":32.960881,      \"Longitude\":-116.5714077   },   {        \"Location\":\"Mount Gower\",      \"Latitude\":33.0164351,      \"Longitude\":-116.7625233   },   {        \"Location\":\"Observatory \",      \"Latitude\":33.6998598,      \"Longitude\":-117.9175784   },   {        \"Location\":\"Iron Mountain\",      \"Latitude\":45.8202334,      \"Longitude\":-88.0659603   },   {        \"Location\":\"Mount Woodson\",      \"Latitude\":33.0086557,      \"Longitude\":-116.9705855   },   {        \"Location\":\"Cuyamaca Peak\",      \"Latitude\":32.9467149,      \"Longitude\":-116.6064084   },   {        \"Location\":\"Clevenger Canyon\",      \"Latitude\":33.0892091,      \"Longitude\":-116.9019716   },   {        \"Location\":\"Torrey Pines Reserve Extension\",      \"Latitude\":32.8434461,      \"Longitude\":-117.2716076   },   {        \"Location\":\"Los Peñasquitos Canyon\",      \"Latitude\":32.902824,      \"Longitude\":-117.2228136   },   {        \"Location\":\"Batiquitos Lagoon\",      \"Latitude\":33.0892947,      \"Longitude\":-117.2925942   },   {        \"Location\":\"Cedar Creek Falls\",      \"Latitude\":32.9894915,      \"Longitude\":-116.7303002   },   {        \"Location\":\"Volcan Mountain\",      \"Latitude\":41.2080186,      \"Longitude\":-107.0078278   },   {        \"Location\":\"Big Laguna \",      \"Latitude\":32.9056145,      \"Longitude\":-96.9360125   },   {        \"Location\":\"Santa Margarita River\",      \"Latitude\":33.2319793,      \"Longitude\":-117.4161509   },   {        \"Location\":\"Kanaka Flat\",      \"Latitude\":39.4709223,      \"Longitude\":-120.8382609   },   {        \"Location\":\"Daley Ranch\",      \"Latitude\":33.1812741,      \"Longitude\":-117.0617553   },   {        \"Location\":\"Torrey Pines State Natural Reserve\",      \"Latitude\":32.9216491,      \"Longitude\":-117.2535292   },   {        \"Location\":\"Palomar Mountain State Park\",      \"Latitude\":33.31691,      \"Longitude\":-116.8779263   },   {        \"Location\":\"Borrego Palm Canyon\",      \"Latitude\":33.2717045,      \"Longitude\":-116.4075131   },   {        \"Location\":\"San Mateo Canyon\",      \"Latitude\":35.3430887,      \"Longitude\":-107.6625554   },   {        \"Location\":\"Santiago Peak via Trabuco Canyon\",      \"Latitude\":33.6812817,      \"Longitude\":-117.6250603   },   {        \"Location\":\"Sitton Peak\",      \"Latitude\":33.5875232,      \"Longitude\":-117.446154   },   {        \"Location\":\"Los Piños Peak\",      \"Latitude\":33.6641876,      \"Longitude\":-117.4711553   },   {        \"Location\":\"Indianhead Mountain\",      \"Latitude\":49.6644444,      \"Longitude\":-125.2497222   },   {        \"Location\":\"Canyon Sin Nombre\",      \"Latitude\":32.8491627,      \"Longitude\":-116.1538663   },   {        \"Location\":\"Whale Peak\",      \"Latitude\":33.0292139,      \"Longitude\":-116.3161234   },   {        \"Location\":\"Mountain Palm Springs\",      \"Latitude\":32.8642201,      \"Longitude\":-116.233348   },   {        \"Location\":\"Pinyon Ridge\",      \"Latitude\":33.1780555,      \"Longitude\":-116.4552777   },   {        \"Location\":\"Split Mountain\",      \"Latitude\":37.0202851,      \"Longitude\":-118.4218804   },   {        \"Location\":\"Bow Willow and Rockhouse Canyons\",      \"Latitude\":32.842349,      \"Longitude\":-116.225824   },   {        \"Location\":\"Big Laguna \",      \"Latitude\":32.9056145,      \"Longitude\":-96.9360125   },   {        \"Location\":\"Borrego Palm Canyon\",      \"Latitude\":33.2717045,      \"Longitude\":-116.4075131   },   {        \"Location\":\"Tahquitz Peak\",      \"Latitude\":33.7558539,      \"Longitude\":-116.6761288   },   {        \"Location\":\"Bertha Peak\",      \"Latitude\":34.2830617,      \"Longitude\":-116.899199   },   {        \"Location\":\"Willow Hole\",      \"Latitude\":45.7435806,      \"Longitude\":-122.8207096   },   {        \"Location\":\"Pine City \",      \"Latitude\":45.8229105,      \"Longitude\":-92.9703435   },   {        \"Location\":\"Cholla Cactus Garden\",      \"Latitude\":33.9247516,      \"Longitude\":-115.929411   },   {        \"Location\":\"Lost Palms Oasis\",      \"Latitude\":33.7125679,      \"Longitude\":-115.7623865   },   {        \"Location\":\"Hidden Valley\",      \"Latitude\":43.1348136,      \"Longitude\":-77.712564   },   {        \"Location\":\"The Maze\",      \"Latitude\":40.6123033,      \"Longitude\":-77.1955405   },   {        \"Location\":\"Barker Dam Loop\",      \"Latitude\":34.0211639,      \"Longitude\":-116.1522132   },   {        \"Location\":\"Ryan Mountain\",      \"Latitude\":33.9858889,      \"Longitude\":-116.1347357   },   {        \"Location\":\"Inspiration Peak\",      \"Latitude\":46.1360721,      \"Longitude\":-95.5736544   },   {        \"Location\":\"Queen Mountain\",      \"Latitude\":48.8780022,      \"Longitude\":-116.2149213   },   {        \"Location\":\"Ashford Canyon\",      \"Latitude\":35.9477351,      \"Longitude\":-116.6819844   },   {        \"Location\":\"Darwin Falls\",      \"Latitude\":36.3207418,      \"Longitude\":-117.5244607   },   {        \"Location\":\"Salt Creek\",      \"Latitude\":40.3750494,      \"Longitude\":-123.1320648   },   {        \"Location\":\"Badwater Basin\",      \"Latitude\":36.250278,      \"Longitude\":-116.825833   },   {        \"Location\":\"Limekiln State Park in Big Sur\",      \"Latitude\":36.0495805,      \"Longitude\":-121.5898093   },   {        \"Location\":\"Pfeiffer Big Sur State Park\",      \"Latitude\":36.2479341,      \"Longitude\":-121.7812662   },   {        \"Location\":\"Andrew Molera State Park in Big Sur\",      \"Latitude\":36.2884189,      \"Longitude\":-121.844272   },   {        \"Location\":\"Point Lobos State Reserve in Big Sur\",      \"Latitude\":36.5159123,      \"Longitude\":-121.9422821   },   {        \"Location\":\"Junipero Serra Peak\",      \"Latitude\":36.1455185,      \"Longitude\":-121.4193777   },   {        \"Location\":\"Duck Lake\",      \"Latitude\":43.3390724,      \"Longitude\":-86.3921491   },   {        \"Location\":\"Thousand Island Lake\",      \"Latitude\":37.7204427,      \"Longitude\":-119.1833985   },   {        \"Location\":\"Fossil Falls\",      \"Latitude\":35.969946,      \"Longitude\":-117.9089637   },   {        \"Location\":\"Jordan Hot Springs\",      \"Latitude\":36.2291067,      \"Longitude\":-118.3034189   },   {        \"Location\":\"Rae Lakes Loop\",      \"Latitude\":32.071268,      \"Longitude\":-82.8923825   },   {        \"Location\":\"Parker Lake\",      \"Latitude\":37.6867211,      \"Longitude\":-90.0776139   },   {        \"Location\":\"Convict Lake\",      \"Latitude\":37.5898951,      \"Longitude\":-118.8582239   },   {        \"Location\":\"Rock Creek Lake\",      \"Latitude\":40.9668572,      \"Longitude\":-96.4990611   },   {        \"Location\":\"North Fork of Big Pine Creek\",      \"Latitude\":37.1250058,      \"Longitude\":-118.5013703   },   {        \"Location\":\"McGee Creek Canyon\",      \"Latitude\":34.4104662,      \"Longitude\":-83.1211269   },   {        \"Location\":\"Sherwin Lakes\",      \"Latitude\":37.6107696,      \"Longitude\":-118.9451316   },   {        \"Location\":\"Treasure Lakes\",      \"Latitude\":37.3877177,      \"Longitude\":-118.7653478   },   {        \"Location\":\"North Lake Road Fall Foliage\",      \"Latitude\":29.8149266,      \"Longitude\":-95.2030418   },   {        \"Location\":\"Mount Whitney\",      \"Latitude\":36.5784991,      \"Longitude\":-118.29226   },   {        \"Location\":\"Devil&#8217;s Postpile\",      \"Latitude\":37.6243807,      \"Longitude\":-119.0818007   },   {        \"Location\":\"Junipero Serra Peak\",      \"Latitude\":36.1455185,      \"Longitude\":-121.4193777   },   {        \"Location\":\"Sespe Creek\",      \"Latitude\":34.5517403,      \"Longitude\":-118.9462491   },   {        \"Location\":\"Yosemite National Park &#8211; Chilnualna Falls\",      \"Latitude\":37.5478584,      \"Longitude\":-119.6431818   },   {        \"Location\":\"Cathedral Lakes\",      \"Latitude\":37.843523,      \"Longitude\":-119.4209251   },   {        \"Location\":\"Sugar Pine \",      \"Latitude\":37.4405372,      \"Longitude\":-119.6364523   },   {        \"Location\":\"Soldiers Loop\",      \"Latitude\":38.6913409,      \"Longitude\":-90.7328924   },   {        \"Location\":\"Moro Rock in Sequoia National Park\",      \"Latitude\":36.5486611,      \"Longitude\":-118.7665649   },   {        \"Location\":\"Forests of the Sequoias\",      \"Latitude\":37.372485,      \"Longitude\":-122.221188   },   {        \"Location\":\"Mount Whitney\",      \"Latitude\":36.5784991,      \"Longitude\":-118.29226   },   {        \"Location\":\"Zion National Park\",      \"Latitude\":37.2982022,      \"Longitude\":-113.0263005   },   {        \"Location\":\"Syncline Loop \",      \"Latitude\":30.5333563,      \"Longitude\":-98.4034103   },   {        \"Location\":\"Dead Horse Point State Park\",      \"Latitude\":38.5010992,      \"Longitude\":-109.7375474   },   {        \"Location\":\"Newspaper Rock State Historic Monument\",      \"Latitude\":37.988329,      \"Longitude\":-109.5184791   },   {        \"Location\":\"Mesa Arch \",      \"Latitude\":34.6553344,      \"Longitude\":-106.8176538   },   {        \"Location\":\"Fiery Furnace\",      \"Latitude\":38.7435404,      \"Longitude\":-109.5615864   },   {        \"Location\":\"Delicate Arch\",      \"Latitude\":38.7436297,      \"Longitude\":-109.4993124   },   {        \"Location\":\"Devils Garden\",      \"Latitude\":43.615678,      \"Longitude\":-122.1947596   },   {        \"Location\":\"Windows Loop and Double Arch\",      \"Latitude\":38.6915718,      \"Longitude\":-109.5403037   }]";
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private int[] tabIcons = {
            R.drawable.ic_map_marker,
            R.drawable.ic_list
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(R.string.title_activity_match_view);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_match_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Fragment getFragment(int sectionNumber) {
        if (sectionNumber == 1) {
            MatchListViewFragment fragment = new MatchListViewFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        } else {
            MatchMapView fragment = new MatchMapView();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return getFragment(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            /*switch (position) {
                case 0:
                    return "Map";
                case 1:
                    return "List";
            }*/
            return null;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double lat = 37.774,lng = -122.419;
        /*GPSTracker locationListener = new GPSTracker(this);
        String addresses[] = {"Millard Canyon Falls", "Will Thrall Peak", "Cooper Canyon Falls", "Hermit Falls", "Monrovia Canyon Falls", "Fish Canyon Falls", "South Mount Hawkins", "Mount Lowe from Red Box", "Jones Peak via Bailey Canyon", "Smith Mountain", "Lewis Falls on Soldier Creek", "Placerita Canyon Firebreak ", "Mount Islip", "Throop Peak and Mount Hawkins", "Mount Baden-Powell", "Bighorn Peak", "Mount Wilson", "East Fork of the San Gabriel River to the Bridge to Nowhere", "Mount Markham", "Waterman Mountain Loop", "Kratka Ridge", "Mount Akawie", "Winston Ridge and Winston Peak", "Mount Lukens via Haines Canyon", "Shortcut Canyon to West Fork Camp", "Santa Anita Canyon and Sturtevant Falls", "Sunset Peak", "The Devil&#8217;s Chair", "Iron Mountain (#2)", "Mount San Antonio (Mount Baldy) and Mount Harwood", "Echo Mountain and Inspiration Point", "Mount Hillyer", "Mount Wilson from Chantry Flats", "Mount Gleason", "Strawberry Peak", "Pacifico Mountain", "Icehouse Canyon", "Mount Mooney and Vetter Mountain", "Fox Mountain", "Switzer Falls and Bear Canyon", "Mount Lukens via Stone Canyon", "Burkhart  to Burkhart Saddle", "Josephine Peak", "Condor Peak and  Canyon Falls", "San Gabriel Peak", "Thunder Mountain and Telegraph Peak via Three Tees ", "Mount Lawlor", "Timber Mountain via Icehouse Canyon", "Cucamonga Peak via Icehouse Canyon", "Upper Big Sycamore Canyon", "Nicholas Flat", "Wildwood Regional Park", "Simi Peak and China Flat", "Rocky Oaks Park", "Paramount Ranch", "to the Cave of Munits", "Satwiwa to Tri-Peaks", "Las Virgenes Canyon", "Victory head Loop", "Cheeseboro and Palo Comado Canyons", "King Gillette Ranch", "Corral Canyon", "Escondido Falls", "Red Rock Canyon to Calabasas Peak", "Trebek Open Space", "Temescal Canyon to Skull Rock", "Franklin Canyon &#8211; Reservoir Loop", "Franklin Canyon &#8211; Hastain Loop", "Murphy Ranch", "Towsley Canyon", "Castro Crest and Bulldog Road", "Los Liones  to Parker Mesa in Topanga State Park", "Charmlee Wilderness Park", "Malibu Creek", "Fryman Canyon", "Mugu Peak", "Old Boney ", "La Jolla Canyon", "Eagle Rock and Temescal Peak", "Solstice Canyon", "Runyon Canyon", "Grotto ", "Mishe Mokwa  to Sandstone Peak", "Engelmann Oak Loop", "Vista Hermosa Natural Park", "Monrovia Canyon Falls", "Double Peak", "Vital Link  in Wildwood Canyon to Verdugo Peak", "Cowles Mountain and Pyles Peak", "Portuguese Bend Reserve to Sacred Cove", "Wild Flower Hill at the Theodore Payne Foundation", "Saddleback Butte State Park", "Baldwin Hills Scenic Overlook", "Kenneth Hahn Community Loop ", "Franklin Canyon", "Ernest E. Debs Regional Park", "Angel&#8217;s Point", "Elysian Park West Loop", "Corralitas Rail Path", "Vasquez Rocks", "Antelope Valley California Poppy Reserve", "Eaton Canyon", "Beaudry Loop in the Verdugo Mountains", "Fryman Canyon", "Runyon Canyon", "Taco Peak", "Amir&#8217;s Garden", "to Berlin Forest", "Western Canyon", "Glendale Peak", "Firebreak  to Griffith Observatory", "East Griffith Observatory ", "West Griffith Observatory ", "Griffith Park&#8217;s Northside Loop", "Beacon Hill in Griffith Park", "Hollywood Reservoir", " Wisdom Tree and Cahuenga Peak", "Griffith Park &#8211; Bee Rock and the Old L.A. Zoo", "Mount Hollywood", "Mount Lee to the Hollywood Sign", "Del Mar Mesa Preserve", "Indianhead Mountain", "Oakoasis Preserve", "Gonzales Canyon", "Rabbit Peak via Villager Peak", "Barker Valley", "Lake Calavera", "Canyon Sin Nombre", "Viejas Mountain", "Black Mountain", "Boucher Hill Loop", "Thunder Spring and Chimney Flat Loop", "Corte Madera Mountain", "West Butte Borrego Mountain and The Slot", "Culp Valley Loop", "Engelmann Oak Loop", "Boden Canyon", "Lower Doane Valley and French Valley", "Whale Peak", "Mountain Palm Springs", "Pinyon Ridge", "Split Mountain", "Stanley Peak", "El Cajon Mountain", "Bow Willow and Rockhouse Canyons", "Guajome County Park", "San Onofre State Beach", "Hot Springs Mountain", "Bernardo Mountain and Lake Hodges", "Wooded Hill", "Monument Peak via Desert View Picnic Area", "Guatay Mountain", "Kwaay Paay, Mission Dam, and Kumeyaay Lake", "Piedras Pintadas and Bernardo Bay", "Double Peak", "Eagle Rock", "Stonewall Peak", "Mount Gower", "Observatory ", "Bayside  at Cabrillo National Monument", "Cowles Mountain and Pyles Peak", "East Mesa in Cuyamaca Rancho State Park", "Lake Cuyamaca and the Stonewall Mine", "Iron Mountain", "Mount Woodson", "Cuyamaca Peak", "Clevenger Canyon", "Torrey Pines Reserve Extension", "Los Peñasquitos Canyon", "Batiquitos Lagoon", "Cedar Creek Falls", "Volcan Mountain", "Big Laguna ", "Santa Margarita River", "Kanaka Flat", "Daley Ranch", "Torrey Pines State Natural Reserve", "Palomar Mountain State Park", "Borrego Palm Canyon", "Ghost Mountain and Pictograph ", "Anza-Borrego State Park: Calcite Mine", "Anza-Borrego Desert State Park: Hellhole Canyon", "New and Old San Juan s Loop", "Trabuco Canyon / Horsethief Loop", "San Mateo Canyon", "Santiago Peak via Trabuco Canyon", "Sitton Peak", "Los Piños Peak", "Aliso and Wood Canyons", "Bell Canyon in Caspers Wilderness Park", "Indianhead Mountain", "Rabbit Peak via Villager Peak", "Canyon Sin Nombre", "West Butte Borrego Mountain and The Slot", "Culp Valley Loop", "Whale Peak", "Mountain Palm Springs", "Pinyon Ridge", "Split Mountain", "Bow Willow and Rockhouse Canyons", "Big Laguna ", "Borrego Palm Canyon", "Ghost Mountain and Pictograph ", "Anza-Borrego State Park: Calcite Mine", "Anza-Borrego Desert State Park: Hellhole Canyon", "San Gorgonio Mountain via the Vivian Creek ", "Tahquitz Peak", "and Backpack San Bernardino Peak", "and Backpack Mount San Jacinto State Park", "Mount San Jacinto via Mountain Station", "San Gorgonio Mountain via South Fork ", "Bertha Peak", "Snowshoeing Pine Knot ", "Bighorn Peak and Ontario Peak", "Thunder Mountain and Telegraph Peak via Three Tees ", "Dry Lake Backpacking &#8211; San Gorgonio Wilderness", "Timber Mountain via Icehouse Canyon", "Cucamonga Peak via Icehouse Canyon", "Johnny Lang Canyon in Joshua Tree National Park", "Willow Hole", "Arch Rock to Grand Tank", "Pine City ", "Lost Horse Mine in Joshua Tree National Park", "Cholla Cactus Garden", "Lost Palms Oasis", " Wonderland Ranch and the Wall Street Mill", "Hidden Valley", "The Maze", "Barker Dam Loop", "Ryan Mountain", "Inspiration Peak", "Queen Mountain", "Hungry Bill&#8217;s Ranch", "Ashford Canyon", "Golden Canyon and Gower Gulch to Zabriskie Point", "Darwin Falls", "Salt Creek", "Badwater Basin", "Limekiln State Park in Big Sur", "Ewoldsen  in Julia Pfeiffer Burns State Park", "Pfeiffer Big Sur State Park", "Andrew Molera State Park in Big Sur", "Point Lobos State Reserve in Big Sur", "McWay Falls at Julia Pfeiffer Burns State Park", "Junipero Serra Peak", "Big Pine Lakes and Palisade Glacier via Sam Mack Meadow", "Scodie Mountain via Northeast Ridge", "Duck Lake", "To The Tuttle Creek Ashram", "South Lake to Long Lake", "Thousand Island Lake", "Fossil Falls", "Jordan Hot Springs", "Rae Lakes Loop", "Parker Lake", "Convict Lake", "Rock Creek Lake", "North Fork of Big Pine Creek", "McGee Creek Canyon", "Sherwin Lakes", "Treasure Lakes", "North Lake Road Fall Foliage", "Sabrina Basin to Emerald Lakes", "Mount Whitney", "Devil&#8217;s Postpile", "McWay Falls at Julia Pfeiffer Burns State Park", "Junipero Serra Peak", "Sespe Creek", "Backpacking Matilija Creek", "Bear Gulch in Pinnacles National Park", "Condor Gulch in Pinnacles National Park", "High Peaks Loop in Pinnacles National Park", "Backpacking Yosemite&#8217;s Pohono ", "Yosemite National Park &#8211; Chilnualna Falls", "Yosemite National Park &#8211; Bridalveil Falls", "Cathedral Lakes", "North Dome and Indian Rock &#8211; Yosemite National Park", "The Panorama Loop &#8211; Yosemite National Park", "Sugar Pine ", "Soldiers Loop", "Alta Peak and Alta Meadow in Sequoia National Park", "Monarch Lake and Sawtooth Pass in Sequoia National Park", "Eagle Lake in Sequoia National Park", "White Chief Bowl in Sequoia National Park", "Cold Springs Nature  in Sequoia National Park", "Round Meadow and Beetle Rock in Sequoia National Park", "Congress  in Sequoia National Park", "East Fork Grove in Sequoia National Park", "Moro Rock in Sequoia National Park", "Tokopah Falls in Sequoia National Park", " of the Sequoias", "Mount Whitney", "Zion National Park", "Syncline Loop ", "Chesler Park Loop in Canyonlands", "Dead Horse Point State Park", "Cave Spring in Canyonlands", "Roadside Ruin in Canyonlands", "Lathrop  in Canyonlands", "Newspaper Rock State Historic Monument", "Mesa Arch ", "Upheaval Dome Overlook in Canyonlands", "Grand View Point in Canyonlands National Park", "Fiery Furnace", "Delicate Arch", "Devils Garden", "Windows Loop and Double Arch", "Balanced Rock in Arches National Park"};
        for (String a : addresses) {
            try {

                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(lat, lng))
                            .title(a));
                }catch(Exception e){
                e.printStackTrace();
            }
        }
*/

        JSONArray arr = null;
        try {
            arr = new JSONArray(data);
            for (int i = 0; i < arr.length(); i++)
            {
                JSONObject obj = arr.getJSONObject(i);
                String location = obj.getString("Location");
                double latitude = obj.getDouble("Latitude");
                double longitude = obj.getDouble("Longitude");
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitude, longitude))
                        .title(location));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        LatLng location = new LatLng(lat, lng);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(location)      // Sets the center of the map to Mountain View
                .zoom(10)                   // Sets the zoom
                .build();                   // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onFragmentReady(Fragment fragment){
        if (fragment instanceof MatchMapView){
            MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

    }
}
