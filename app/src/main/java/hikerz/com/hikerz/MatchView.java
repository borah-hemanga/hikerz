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

import java.io.IOException;
import java.util.List;

import hikerz.com.hikerz.fragments.FragmentInteractionListener;
import hikerz.com.hikerz.fragments.MatchListViewFragment;
import hikerz.com.hikerz.fragments.MatchMapView;

public class MatchView extends AppCompatActivity implements FragmentInteractionListener, OnMapReadyCallback{


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
        LatLng location = new LatLng(lat, lng);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(location)      // Sets the center of the map to Mountain View
                .zoom(7)                   // Sets the zoom
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
