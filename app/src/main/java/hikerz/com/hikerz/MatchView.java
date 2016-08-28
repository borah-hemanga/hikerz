package hikerz.com.hikerz;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import hikerz.com.hikerz.fragments.FragmentInteractionListener;
import hikerz.com.hikerz.fragments.MatchListViewFragment;
import hikerz.com.hikerz.fragments.MatchMapView;

public class MatchView extends AppCompatActivity implements FragmentInteractionListener, OnMapReadyCallback{

    String data= "[{\"Location\":\"Millard Canyon Falls\",\"Latitude\":38.9355199,\"Longitude\":-112.2324347,\"Difficulty\":1},\n" +
            "{\"Location\":\"Will Thrall Peak\",\"Latitude\":34.3847207,\"Longitude\":-117.9028421,\"Difficulty\":0},\n" +
            "{\"Location\":\"Cooper Canyon Falls\",\"Latitude\":34.3608678,\"Longitude\":-117.902441,\"Difficulty\":0},\n" +
            "{\"Location\":\"Hermit Falls\",\"Latitude\":34.1912347,\"Longitude\":-118.0170987,\"Difficulty\":1},\n" +
            "{\"Location\":\"Fish Canyon Falls\",\"Latitude\":34.1690043,\"Longitude\":-117.9261276,\"Difficulty\":4},\n" +
            "{\"Location\":\"South Mount Hawkins\",\"Latitude\":34.3113909,\"Longitude\":-117.8103382,\"Difficulty\":3},\n" +
            "{\"Location\":\"Smith Mountain\",\"Latitude\":36.127442,\"Longitude\":-118.2248053,\"Difficulty\":0},\n" +
            "{\"Location\":\"Mount Islip\",\"Latitude\":34.3450005,\"Longitude\":-117.8397841,\"Difficulty\":3},\n" +
            "{\"Location\":\"Mount Baden-Powell\",\"Latitude\":34.3586117,\"Longitude\":-117.764504,\"Difficulty\":3},\n" +
            "{\"Location\":\"Bighorn Peak\",\"Latitude\":44.25441,\"Longitude\":-107.1072842,\"Difficulty\":2},\n" +
            "{\"Location\":\"Mount Wilson\",\"Latitude\":37.8391607,\"Longitude\":-107.9914581,\"Difficulty\":1},\n" +
            "{\"Location\":\"Mount Markham\",\"Latitude\":34.2369477,\"Longitude\":-118.0989587,\"Difficulty\":0},\n" +
            "{\"Location\":\"Waterman Mountain Loop\",\"Latitude\":32.3906599,\"Longitude\":-111.427113,\"Difficulty\":3},\n" +
            "{\"Location\":\"Kratka Ridge\",\"Latitude\":34.3466666,\"Longitude\":-117.8997222,\"Difficulty\":4},\n" +
            "{\"Location\":\"Sunset Peak\",\"Latitude\":44.8560267,\"Longitude\":-112.1466529,\"Difficulty\":3},\n" +
            "{\"Location\":\"The Devil&#8217;s Chair\",\"Latitude\":44.2777942,\"Longitude\":-73.2178253,\"Difficulty\":1},\n" +
            "{\"Location\":\"Iron Mountain (#2)\",\"Latitude\":45.8402212,\"Longitude\":-88.0876547,\"Difficulty\":1},\n" +
            "{\"Location\":\"Echo Mountain and Inspiration Point\",\"Latitude\":34.2215285,\"Longitude\":-118.1093724,\"Difficulty\":0},\n" +
            "{\"Location\":\"Mount Hillyer\",\"Latitude\":34.3494435,\"Longitude\":-118.015901,\"Difficulty\":1},\n" +
            "{\"Location\":\"Mount Gleason\",\"Latitude\":34.3766644,\"Longitude\":-118.177296,\"Difficulty\":4},\n" +
            "{\"Location\":\"Strawberry Peak\",\"Latitude\":34.2836126,\"Longitude\":-118.1203487,\"Difficulty\":0},\n" +
            "{\"Location\":\"Pacifico Mountain\",\"Latitude\":34.3822201,\"Longitude\":-118.0347909,\"Difficulty\":3},\n" +
            "{\"Location\":\"Icehouse Canyon\",\"Latitude\":37.4458496,\"Longitude\":-103.4796644,\"Difficulty\":2},\n" +
            "{\"Location\":\"Fox Mountain\",\"Latitude\":37.5061194,\"Longitude\":-106.7439309,\"Difficulty\":0},\n" +
            "{\"Location\":\"Josephine Peak\",\"Latitude\":47.2199271,\"Longitude\":-114.4934552,\"Difficulty\":0},\n" +
            "{\"Location\":\"San Gabriel Peak\",\"Latitude\":34.2433363,\"Longitude\":-118.0984032,\"Difficulty\":1},\n" +
            "{\"Location\":\"Mount Lawlor\",\"Latitude\":34.2708353,\"Longitude\":-118.1039592,\"Difficulty\":1},\n" +
            "{\"Location\":\"Upper Big Sycamore Canyon\",\"Latitude\":33.975699,\"Longitude\":-117.3179816,\"Difficulty\":3},\n" +
            "{\"Location\":\"Nicholas Flat\",\"Latitude\":34.0665662,\"Longitude\":-118.9126083,\"Difficulty\":3},\n" +
            "{\"Location\":\"Wildwood Regional Park\",\"Latitude\":40.831339,\"Longitude\":-74.357468,\"Difficulty\":1},\n" +
            "{\"Location\":\"Rocky Oaks Park\",\"Latitude\":44.2949554,\"Longitude\":-111.4566608,\"Difficulty\":3},\n" +
            "{\"Location\":\"Paramount Ranch\",\"Latitude\":34.1155816,\"Longitude\":-118.7562884,\"Difficulty\":4},\n" +
            "{\"Location\":\"Las Virgenes Canyon\",\"Latitude\":34.1722273,\"Longitude\":-118.7023111,\"Difficulty\":2},\n" +
            "{\"Location\":\"Victory head Loop\",\"Latitude\":44.0762472,\"Longitude\":-86.3746629,\"Difficulty\":3},\n" +
            "{\"Location\":\"Cheeseboro and Palo Comado Canyons\",\"Latitude\":34.1837788,\"Longitude\":-118.7372922,\"Difficulty\":1},\n" +
            "{\"Location\":\"King Gillette Ranch\",\"Latitude\":34.1018989,\"Longitude\":-118.704936,\"Difficulty\":2},\n" +
            "{\"Location\":\"Corral Canyon\",\"Latitude\":34.0336184,\"Longitude\":-118.7342532,\"Difficulty\":1},\n" +
            "{\"Location\":\"Escondido Falls\",\"Latitude\":34.0259721,\"Longitude\":-118.7797592,\"Difficulty\":3},\n" +
            "{\"Location\":\"Trebek Open Space\",\"Latitude\":34.1104851,\"Longitude\":-118.3589785,\"Difficulty\":0},\n" +
            "{\"Location\":\"Murphy Ranch\",\"Latitude\":34.072382,\"Longitude\":-118.5138881,\"Difficulty\":2},\n" +
            "{\"Location\":\"Towsley Canyon\",\"Latitude\":34.3605542,\"Longitude\":-118.5548086,\"Difficulty\":1},\n" +
            "{\"Location\":\"Charmlee Wilderness Park\",\"Latitude\":34.0618131,\"Longitude\":-118.8770557,\"Difficulty\":1},\n" +
            "{\"Location\":\"Malibu Creek\",\"Latitude\":34.0625028,\"Longitude\":-118.6997438,\"Difficulty\":3},\n" +
            "{\"Location\":\"Fryman Canyon\",\"Latitude\":34.1426344,\"Longitude\":-118.3936808,\"Difficulty\":2},\n" +
            "{\"Location\":\"Mugu Peak\",\"Latitude\":34.0925068,\"Longitude\":-119.0548238,\"Difficulty\":1},\n" +
            "{\"Location\":\"Old Boney \",\"Latitude\":35.5392428,\"Longitude\":-82.0576413,\"Difficulty\":2},\n" +
            "{\"Location\":\"La Jolla Canyon\",\"Latitude\":33.0097838,\"Longitude\":-109.4639644,\"Difficulty\":0},\n" +
            "{\"Location\":\"Solstice Canyon\",\"Latitude\":34.0484269,\"Longitude\":-118.7576968,\"Difficulty\":4},\n" +
            "{\"Location\":\"Runyon Canyon\",\"Latitude\":34.1106805,\"Longitude\":-118.3503784,\"Difficulty\":4},\n" +
            "{\"Location\":\"Grotto \",\"Latitude\":47.737412,\"Longitude\":-121.4238318,\"Difficulty\":2},\n" +
            "{\"Location\":\"Engelmann Oak Loop\",\"Latitude\":33.5845893,\"Longitude\":-117.2243587,\"Difficulty\":4},\n" +
            "{\"Location\":\"Vista Hermosa Natural Park\",\"Latitude\":34.0619569,\"Longitude\":-118.2579459,\"Difficulty\":1},\n" +
            "{\"Location\":\"Double Peak\",\"Latitude\":33.1094846,\"Longitude\":-117.1775343,\"Difficulty\":4},\n" +
            "{\"Location\":\"Saddleback Butte State Park\",\"Latitude\":34.6895546,\"Longitude\":-117.8236985,\"Difficulty\":4},\n" +
            "{\"Location\":\"Baldwin Hills Scenic Overlook\",\"Latitude\":34.0175826,\"Longitude\":-118.3840379,\"Difficulty\":1},\n" +
            "{\"Location\":\"Franklin Canyon\",\"Latitude\":36.2691161,\"Longitude\":-118.968989,\"Difficulty\":3},\n" +
            "{\"Location\":\"Ernest E. Debs Regional Park\",\"Latitude\":34.096562,\"Longitude\":-118.1956408,\"Difficulty\":1},\n" +
            "{\"Location\":\"Angel&#8217;s Point\",\"Latitude\":33.8288321,\"Longitude\":-85.8372015,\"Difficulty\":0},\n" +
            "{\"Location\":\"Elysian Park West Loop\",\"Latitude\":44.1995343,\"Longitude\":-93.6781788,\"Difficulty\":0},\n" +
            "{\"Location\":\"Vasquez Rocks\",\"Latitude\":34.4855487,\"Longitude\":-118.3173013,\"Difficulty\":0},\n" +
            "{\"Location\":\"Antelope Valley California Poppy Reserve\",\"Latitude\":34.7349045,\"Longitude\":-118.3960096,\"Difficulty\":3},\n" +
            "{\"Location\":\"Eaton Canyon\",\"Latitude\":34.16224,\"Longitude\":-118.085,\"Difficulty\":0},\n" +
            "{\"Location\":\"Beaudry Loop in the Verdugo Mountains\",\"Latitude\":34.187829,\"Longitude\":-118.2523225,\"Difficulty\":4},\n" +
            "{\"Location\":\"Fryman Canyon\",\"Latitude\":34.1426344,\"Longitude\":-118.3936808,\"Difficulty\":4},\n" +
            "{\"Location\":\"Runyon Canyon\",\"Latitude\":34.1106805,\"Longitude\":-118.3503784,\"Difficulty\":4},\n" +
            "{\"Location\":\"Taco Peak\",\"Latitude\":33.8555532,\"Longitude\":-112.1378733,\"Difficulty\":4},\n" +
            "{\"Location\":\"Amir&#8217;s Garden\",\"Latitude\":37.980267,\"Longitude\":-100.850223,\"Difficulty\":1},\n" +
            "{\"Location\":\"Western Canyon\",\"Latitude\":34.9082274,\"Longitude\":-101.8844273,\"Difficulty\":1},\n" +
            "{\"Location\":\"Glendale Peak\",\"Latitude\":35.6576751,\"Longitude\":-78.7349524,\"Difficulty\":2},\n" +
            "{\"Location\":\"Firebreak  to Griffith Observatory\",\"Latitude\":34.1145321,\"Longitude\":-118.3071022,\"Difficulty\":1},\n" +
            "{\"Location\":\"East Griffith Observatory \",\"Latitude\":34.1184341,\"Longitude\":-118.3003935,\"Difficulty\":2},\n" +
            "{\"Location\":\"West Griffith Observatory \",\"Latitude\":34.1184341,\"Longitude\":-118.3003935,\"Difficulty\":2},\n" +
            "{\"Location\":\"Beacon Hill in Griffith Park\",\"Latitude\":34.1184525,\"Longitude\":-118.2737583,\"Difficulty\":1},\n" +
            "{\"Location\":\"Hollywood Reservoir\",\"Latitude\":34.1197689,\"Longitude\":-118.3308532,\"Difficulty\":4},\n" +
            "{\"Location\":\"Mount Hollywood\",\"Latitude\":34.1280637,\"Longitude\":-118.3011874,\"Difficulty\":3},\n" +
            "{\"Location\":\"Del Mar Mesa Preserve\",\"Latitude\":32.9512588,\"Longitude\":-117.1625037,\"Difficulty\":4},\n" +
            "{\"Location\":\"Indianhead Mountain\",\"Latitude\":49.6644444,\"Longitude\":-125.2497222,\"Difficulty\":4},\n" +
            "{\"Location\":\"Gonzales Canyon\",\"Latitude\":36.9166933,\"Longitude\":-105.0627784,\"Difficulty\":1},\n" +
            "{\"Location\":\"Barker Valley\",\"Latitude\":32.8473958,\"Longitude\":-85.2048894,\"Difficulty\":2},\n" +
            "{\"Location\":\"Lake Calavera\",\"Latitude\":33.1711358,\"Longitude\":-117.2839609,\"Difficulty\":3},\n" +
            "{\"Location\":\"Canyon Sin Nombre\",\"Latitude\":32.8491627,\"Longitude\":-116.1538663,\"Difficulty\":4},\n" +
            "{\"Location\":\"Viejas Mountain\",\"Latitude\":32.8611624,\"Longitude\":-116.7258546,\"Difficulty\":0},\n" +
            "{\"Location\":\"Black Mountain\",\"Latitude\":35.6178951,\"Longitude\":-82.3212302,\"Difficulty\":2},\n" +
            "{\"Location\":\"Boucher Hill Loop\",\"Latitude\":44.6132876,\"Longitude\":-87.6931983,\"Difficulty\":0},\n" +
            "{\"Location\":\"Corte Madera Mountain\",\"Latitude\":32.7575535,\"Longitude\":-116.5911294,\"Difficulty\":3},\n" +
            "{\"Location\":\"Engelmann Oak Loop\",\"Latitude\":33.5845893,\"Longitude\":-117.2243587,\"Difficulty\":3},\n" +
            "{\"Location\":\"Boden Canyon\",\"Latitude\":33.0922646,\"Longitude\":-116.8966937,\"Difficulty\":0},\n" +
            "{\"Location\":\"Whale Peak\",\"Latitude\":33.0292139,\"Longitude\":-116.3161234,\"Difficulty\":2},\n" +
            "{\"Location\":\"Mountain Palm Springs\",\"Latitude\":32.8642201,\"Longitude\":-116.233348,\"Difficulty\":4},\n" +
            "{\"Location\":\"Pinyon Ridge\",\"Latitude\":33.1780555,\"Longitude\":-116.4552777,\"Difficulty\":0},\n" +
            "{\"Location\":\"Split Mountain\",\"Latitude\":37.0202851,\"Longitude\":-118.4218804,\"Difficulty\":1},\n" +
            "{\"Location\":\"Stanley Peak\",\"Latitude\":51.1699999,\"Longitude\":-116.0533333,\"Difficulty\":1},\n" +
            "{\"Location\":\"El Cajon Mountain\",\"Latitude\":32.9147711,\"Longitude\":-116.8197465,\"Difficulty\":0},\n" +
            "{\"Location\":\"Bow Willow and Rockhouse Canyons\",\"Latitude\":32.842349,\"Longitude\":-116.225824,\"Difficulty\":1},\n" +
            "{\"Location\":\"Guajome County Park\",\"Latitude\":33.2448343,\"Longitude\":-117.2750294,\"Difficulty\":0},\n" +
            "{\"Location\":\"San Onofre State Beach\",\"Latitude\":33.3761505,\"Longitude\":-117.5693128,\"Difficulty\":2},\n" +
            "{\"Location\":\"Hot Springs Mountain\",\"Latitude\":33.3150362,\"Longitude\":-116.5797401,\"Difficulty\":4},\n" +
            "{\"Location\":\"Bernardo Mountain and Lake Hodges\",\"Latitude\":33.0639309,\"Longitude\":-117.088088,\"Difficulty\":4},\n" +
            "{\"Location\":\"Wooded Hill\",\"Latitude\":33.7623059,\"Longitude\":-89.8231168,\"Difficulty\":1},\n" +
            "{\"Location\":\"Guatay Mountain\",\"Latitude\":32.8433842,\"Longitude\":-116.5741849,\"Difficulty\":0},\n" +
            "{\"Location\":\"Double Peak\",\"Latitude\":33.1094846,\"Longitude\":-117.1775343,\"Difficulty\":3},\n" +
            "{\"Location\":\"Eagle Rock\",\"Latitude\":34.1322469,\"Longitude\":-118.2117257,\"Difficulty\":2},\n" +
            "{\"Location\":\"Stonewall Peak\",\"Latitude\":32.960881,\"Longitude\":-116.5714077,\"Difficulty\":0},\n" +
            "{\"Location\":\"Mount Gower\",\"Latitude\":33.0164351,\"Longitude\":-116.7625233,\"Difficulty\":0},\n" +
            "{\"Location\":\"Observatory \",\"Latitude\":33.6998598,\"Longitude\":-117.9175784,\"Difficulty\":3},\n" +
            "{\"Location\":\"Iron Mountain\",\"Latitude\":45.8202334,\"Longitude\":-88.0659603,\"Difficulty\":3},\n" +
            "{\"Location\":\"Mount Woodson\",\"Latitude\":33.0086557,\"Longitude\":-116.9705855,\"Difficulty\":0},\n" +
            "{\"Location\":\"Cuyamaca Peak\",\"Latitude\":32.9467149,\"Longitude\":-116.6064084,\"Difficulty\":4},\n" +
            "{\"Location\":\"Clevenger Canyon\",\"Latitude\":33.0892091,\"Longitude\":-116.9019716,\"Difficulty\":0},\n" +
            "{\"Location\":\"Torrey Pines Reserve Extension\",\"Latitude\":32.8434461,\"Longitude\":-117.2716076,\"Difficulty\":4},\n" +
            "{\"Location\":\"Los Peñasquitos Canyon\",\"Latitude\":32.902824,\"Longitude\":-117.2228136,\"Difficulty\":2},\n" +
            "{\"Location\":\"Batiquitos Lagoon\",\"Latitude\":33.0892947,\"Longitude\":-117.2925942,\"Difficulty\":4},\n" +
            "{\"Location\":\"Cedar Creek Falls\",\"Latitude\":32.9894915,\"Longitude\":-116.7303002,\"Difficulty\":4},\n" +
            "{\"Location\":\"Volcan Mountain\",\"Latitude\":41.2080186,\"Longitude\":-107.0078278,\"Difficulty\":1},\n" +
            "{\"Location\":\"Big Laguna \",\"Latitude\":32.9056145,\"Longitude\":-96.9360125,\"Difficulty\":1},\n" +
            "{\"Location\":\"Santa Margarita River\",\"Latitude\":33.2319793,\"Longitude\":-117.4161509,\"Difficulty\":2},\n" +
            "{\"Location\":\"Kanaka Flat\",\"Latitude\":39.4709223,\"Longitude\":-120.8382609,\"Difficulty\":0},\n" +
            "{\"Location\":\"Daley Ranch\",\"Latitude\":33.1812741,\"Longitude\":-117.0617553,\"Difficulty\":2},\n" +
            "{\"Location\":\"Torrey Pines State Natural Reserve\",\"Latitude\":32.9216491,\"Longitude\":-117.2535292,\"Difficulty\":3},\n" +
            "{\"Location\":\"Palomar Mountain State Park\",\"Latitude\":33.31691,\"Longitude\":-116.8779263,\"Difficulty\":3},\n" +
            "{\"Location\":\"Borrego Palm Canyon\",\"Latitude\":33.2717045,\"Longitude\":-116.4075131,\"Difficulty\":1},\n" +
            "{\"Location\":\"San Mateo Canyon\",\"Latitude\":35.3430887,\"Longitude\":-107.6625554,\"Difficulty\":1},\n" +
            "{\"Location\":\"Santiago Peak via Trabuco Canyon\",\"Latitude\":33.6812817,\"Longitude\":-117.6250603,\"Difficulty\":2},\n" +
            "{\"Location\":\"Sitton Peak\",\"Latitude\":33.5875232,\"Longitude\":-117.446154,\"Difficulty\":1},\n" +
            "{\"Location\":\"Los Piños Peak\",\"Latitude\":33.6641876,\"Longitude\":-117.4711553,\"Difficulty\":1},\n" +
            "{\"Location\":\"Indianhead Mountain\",\"Latitude\":49.6644444,\"Longitude\":-125.2497222,\"Difficulty\":3},\n" +
            "{\"Location\":\"Canyon Sin Nombre\",\"Latitude\":32.8491627,\"Longitude\":-116.1538663,\"Difficulty\":3},\n" +
            "{\"Location\":\"Whale Peak\",\"Latitude\":33.0292139,\"Longitude\":-116.3161234,\"Difficulty\":2},\n" +
            "{\"Location\":\"Mountain Palm Springs\",\"Latitude\":32.8642201,\"Longitude\":-116.233348,\"Difficulty\":2},\n" +
            "{\"Location\":\"Pinyon Ridge\",\"Latitude\":33.1780555,\"Longitude\":-116.4552777,\"Difficulty\":3},\n" +
            "{\"Location\":\"Split Mountain\",\"Latitude\":37.0202851,\"Longitude\":-118.4218804,\"Difficulty\":4},\n" +
            "{\"Location\":\"Bow Willow and Rockhouse Canyons\",\"Latitude\":32.842349,\"Longitude\":-116.225824,\"Difficulty\":2},\n" +
            "{\"Location\":\"Big Laguna \",\"Latitude\":32.9056145,\"Longitude\":-96.9360125,\"Difficulty\":3},\n" +
            "{\"Location\":\"Borrego Palm Canyon\",\"Latitude\":33.2717045,\"Longitude\":-116.4075131,\"Difficulty\":1},\n" +
            "{\"Location\":\"Tahquitz Peak\",\"Latitude\":33.7558539,\"Longitude\":-116.6761288,\"Difficulty\":2},\n" +
            "{\"Location\":\"Bertha Peak\",\"Latitude\":34.2830617,\"Longitude\":-116.899199,\"Difficulty\":0},\n" +
            "{\"Location\":\"Willow Hole\",\"Latitude\":45.7435806,\"Longitude\":-122.8207096,\"Difficulty\":0},\n" +
            "{\"Location\":\"Pine City \",\"Latitude\":45.8229105,\"Longitude\":-92.9703435,\"Difficulty\":0},\n" +
            "{\"Location\":\"Cholla Cactus Garden\",\"Latitude\":33.9247516,\"Longitude\":-115.929411,\"Difficulty\":1},\n" +
            "{\"Location\":\"Lost Palms Oasis\",\"Latitude\":33.7125679,\"Longitude\":-115.7623865,\"Difficulty\":1},\n" +
            "{\"Location\":\"Hidden Valley\",\"Latitude\":43.1348136,\"Longitude\":-77.712564,\"Difficulty\":4},\n" +
            "{\"Location\":\"The Maze\",\"Latitude\":40.6123033,\"Longitude\":-77.1955405,\"Difficulty\":2},\n" +
            "{\"Location\":\"Barker Dam Loop\",\"Latitude\":34.0211639,\"Longitude\":-116.1522132,\"Difficulty\":4},\n" +
            "{\"Location\":\"Ryan Mountain\",\"Latitude\":33.9858889,\"Longitude\":-116.1347357,\"Difficulty\":0},\n" +
            "{\"Location\":\"Inspiration Peak\",\"Latitude\":46.1360721,\"Longitude\":-95.5736544,\"Difficulty\":2},\n" +
            "{\"Location\":\"Queen Mountain\",\"Latitude\":48.8780022,\"Longitude\":-116.2149213,\"Difficulty\":4},\n" +
            "{\"Location\":\"Ashford Canyon\",\"Latitude\":35.9477351,\"Longitude\":-116.6819844,\"Difficulty\":3},\n" +
            "{\"Location\":\"Darwin Falls\",\"Latitude\":36.3207418,\"Longitude\":-117.5244607,\"Difficulty\":2},\n" +
            "{\"Location\":\"Salt Creek\",\"Latitude\":40.3750494,\"Longitude\":-123.1320648,\"Difficulty\":0},\n" +
            "{\"Location\":\"Badwater Basin\",\"Latitude\":36.250278,\"Longitude\":-116.825833,\"Difficulty\":3},\n" +
            "{\"Location\":\"Limekiln State Park in Big Sur\",\"Latitude\":36.0495805,\"Longitude\":-121.5898093,\"Difficulty\":1},\n" +
            "{\"Location\":\"Pfeiffer Big Sur State Park\",\"Latitude\":36.2479341,\"Longitude\":-121.7812662,\"Difficulty\":4},\n" +
            "{\"Location\":\"Andrew Molera State Park in Big Sur\",\"Latitude\":36.2884189,\"Longitude\":-121.844272,\"Difficulty\":0},\n" +
            "{\"Location\":\"Point Lobos State Reserve in Big Sur\",\"Latitude\":36.5159123,\"Longitude\":-121.9422821,\"Difficulty\":4},\n" +
            "{\"Location\":\"Junipero Serra Peak\",\"Latitude\":36.1455185,\"Longitude\":-121.4193777,\"Difficulty\":1},\n" +
            "{\"Location\":\"Duck Lake\",\"Latitude\":43.3390724,\"Longitude\":-86.3921491,\"Difficulty\":4},\n" +
            "{\"Location\":\"Thousand Island Lake\",\"Latitude\":37.7204427,\"Longitude\":-119.1833985,\"Difficulty\":4},\n" +
            "{\"Location\":\"Fossil Falls\",\"Latitude\":35.969946,\"Longitude\":-117.9089637,\"Difficulty\":4},\n" +
            "{\"Location\":\"Jordan Hot Springs\",\"Latitude\":36.2291067,\"Longitude\":-118.3034189,\"Difficulty\":4},\n" +
            "{\"Location\":\"Rae Lakes Loop\",\"Latitude\":32.071268,\"Longitude\":-82.8923825,\"Difficulty\":2},\n" +
            "{\"Location\":\"Parker Lake\",\"Latitude\":37.6867211,\"Longitude\":-90.0776139,\"Difficulty\":4},\n" +
            "{\"Location\":\"Convict Lake\",\"Latitude\":37.5898951,\"Longitude\":-118.8582239,\"Difficulty\":1},\n" +
            "{\"Location\":\"Rock Creek Lake\",\"Latitude\":40.9668572,\"Longitude\":-96.4990611,\"Difficulty\":1},\n" +
            "{\"Location\":\"North Fork of Big Pine Creek\",\"Latitude\":37.1250058,\"Longitude\":-118.5013703,\"Difficulty\":0},\n" +
            "{\"Location\":\"McGee Creek Canyon\",\"Latitude\":34.4104662,\"Longitude\":-83.1211269,\"Difficulty\":3},\n" +
            "{\"Location\":\"Sherwin Lakes\",\"Latitude\":37.6107696,\"Longitude\":-118.9451316,\"Difficulty\":3},\n" +
            "{\"Location\":\"Treasure Lakes\",\"Latitude\":37.3877177,\"Longitude\":-118.7653478,\"Difficulty\":2},\n" +
            "{\"Location\":\"North Lake Road Fall Foliage\",\"Latitude\":29.8149266,\"Longitude\":-95.2030418,\"Difficulty\":2},\n" +
            "{\"Location\":\"Mount Whitney\",\"Latitude\":36.5784991,\"Longitude\":-118.29226,\"Difficulty\":4},\n" +
            "{\"Location\":\"Devil&#8217;s Postpile\",\"Latitude\":37.6243807,\"Longitude\":-119.0818007,\"Difficulty\":4},\n" +
            "{\"Location\":\"Junipero Serra Peak\",\"Latitude\":36.1455185,\"Longitude\":-121.4193777,\"Difficulty\":1},\n" +
            "{\"Location\":\"Sespe Creek\",\"Latitude\":34.5517403,\"Longitude\":-118.9462491,\"Difficulty\":1},\n" +
            "{\"Location\":\"Yosemite National Park &#8211; Chilnualna Falls\",\"Latitude\":37.5478584,\"Longitude\":-119.6431818,\"Difficulty\":3},\n" +
            "{\"Location\":\"Cathedral Lakes\",\"Latitude\":37.843523,\"Longitude\":-119.4209251,\"Difficulty\":0},\n" +
            "{\"Location\":\"Sugar Pine \",\"Latitude\":37.4405372,\"Longitude\":-119.6364523,\"Difficulty\":3},\n" +
            "{\"Location\":\"Soldiers Loop\",\"Latitude\":38.6913409,\"Longitude\":-90.7328924,\"Difficulty\":1},\n" +
            "{\"Location\":\"Moro Rock in Sequoia National Park\",\"Latitude\":36.5486611,\"Longitude\":-118.7665649,\"Difficulty\":3},\n" +
            "{\"Location\":\"Big Basin Redwoods State Park\",\"Latitude\":37.372485,\"Longitude\":-122.221188,\"Difficulty\":1},\n" +
            "{\"Location\":\"Mount Whitney\",\"Latitude\":36.5784991,\"Longitude\":-118.29226,\"Difficulty\":2},\n" +
            "{\"Location\":\"Zion National Park\",\"Latitude\":37.2982022,\"Longitude\":-113.0263005,\"Difficulty\":3},\n" +
            "{\"Location\":\"Syncline Loop \",\"Latitude\":30.5333563,\"Longitude\":-98.4034103,\"Difficulty\":2},\n" +
            "{\"Location\":\"Dead Horse Point State Park\",\"Latitude\":38.5010992,\"Longitude\":-109.7375474,\"Difficulty\":3},\n" +
            "{\"Location\":\"Newspaper Rock State Historic Monument\",\"Latitude\":37.988329,\"Longitude\":-109.5184791,\"Difficulty\":1},\n" +
            "{\"Location\":\"Mesa Arch \",\"Latitude\":34.6553344,\"Longitude\":-106.8176538,\"Difficulty\":2},\n" +
            "{\"Location\":\"Fiery Furnace\",\"Latitude\":38.7435404,\"Longitude\":-109.5615864,\"Difficulty\":2},\n" +
            "{\"Location\":\"Delicate Arch\",\"Latitude\":38.7436297,\"Longitude\":-109.4993124,\"Difficulty\":1},\n" +
            "{\"Location\":\"Devils Garden\",\"Latitude\":43.615678,\"Longitude\":-122.1947596,\"Difficulty\":1},\n" +
            "{\"Location\":\"Windows Loop and Double Arch\",\"Latitude\":38.6915718,\"Longitude\":-109.5403037,\"Difficulty\":2},\n" +
            "{\"Location\":\"Millard Canyon Falls\",\"Latitude\":38.9355199,\"Longitude\":-112.2324347,\"Difficulty\":1},\n" +
            "{\"Location\":\"Will Thrall Peak\",\"Latitude\":34.3847207,\"Longitude\":-117.9028421,\"Difficulty\":0},\n" +
            "{\"Location\":\"Cooper Canyon Falls\",\"Latitude\":34.3608678,\"Longitude\":-117.902441,\"Difficulty\":0},\n" +
            "{\"Location\":\"Hermit Falls\",\"Latitude\":34.1912347,\"Longitude\":-118.0170987,\"Difficulty\":2},\n" +
            "{\"Location\":\"Fish Canyon Falls\",\"Latitude\":34.1690043,\"Longitude\":-117.9261276,\"Difficulty\":0},\n" +
            "{\"Location\":\"South Mount Hawkins\",\"Latitude\":34.3113909,\"Longitude\":-117.8103382,\"Difficulty\":3},\n" +
            "{\"Location\":\"Smith Mountain\",\"Latitude\":36.127442,\"Longitude\":-118.2248053,\"Difficulty\":1},\n" +
            "{\"Location\":\"Mount Islip\",\"Latitude\":34.3450005,\"Longitude\":-117.8397841,\"Difficulty\":0},\n" +
            "{\"Location\":\"Mount Baden-Powell\",\"Latitude\":34.3586117,\"Longitude\":-117.764504,\"Difficulty\":0},\n" +
            "{\"Location\":\"Bighorn Peak\",\"Latitude\":44.25441,\"Longitude\":-107.1072842,\"Difficulty\":2},\n" +
            "{\"Location\":\"Mount Wilson\",\"Latitude\":37.8391607,\"Longitude\":-107.9914581,\"Difficulty\":0},\n" +
            "{\"Location\":\"Mount Markham\",\"Latitude\":34.2369477,\"Longitude\":-118.0989587,\"Difficulty\":0},\n" +
            "{\"Location\":\"Waterman Mountain Loop\",\"Latitude\":32.3906599,\"Longitude\":-111.427113,\"Difficulty\":3},\n" +
            "{\"Location\":\"Kratka Ridge\",\"Latitude\":34.3466666,\"Longitude\":-117.8997222,\"Difficulty\":2},\n" +
            "{\"Location\":\"Sunset Peak\",\"Latitude\":44.8560267,\"Longitude\":-112.1466529,\"Difficulty\":2},\n" +
            "{\"Location\":\"The Devil&#8217;s Chair\",\"Latitude\":44.2777942,\"Longitude\":-73.2178253,\"Difficulty\":1},\n" +
            "{\"Location\":\"Iron Mountain (#2)\",\"Latitude\":45.8402212,\"Longitude\":-88.0876547,\"Difficulty\":0},\n" +
            "{\"Location\":\"Echo Mountain and Inspiration Point\",\"Latitude\":34.2215285,\"Longitude\":-118.1093724,\"Difficulty\":1},\n" +
            "{\"Location\":\"Mount Hillyer\",\"Latitude\":34.3494435,\"Longitude\":-118.015901,\"Difficulty\":3},\n" +
            "{\"Location\":\"Mount Gleason\",\"Latitude\":34.3766644,\"Longitude\":-118.177296,\"Difficulty\":0},\n" +
            "{\"Location\":\"Strawberry Peak\",\"Latitude\":34.2836126,\"Longitude\":-118.1203487,\"Difficulty\":3},\n" +
            "{\"Location\":\"Pacifico Mountain\",\"Latitude\":34.3822201,\"Longitude\":-118.0347909,\"Difficulty\":0},\n" +
            "{\"Location\":\"Icehouse Canyon\",\"Latitude\":37.4458496,\"Longitude\":-103.4796644,\"Difficulty\":3},\n" +
            "{\"Location\":\"Fox Mountain\",\"Latitude\":37.5061194,\"Longitude\":-106.7439309,\"Difficulty\":2},\n" +
            "{\"Location\":\"Josephine Peak\",\"Latitude\":47.2199271,\"Longitude\":-114.4934552,\"Difficulty\":1},\n" +
            "{\"Location\":\"San Gabriel Peak\",\"Latitude\":34.2433363,\"Longitude\":-118.0984032,\"Difficulty\":1},\n" +
            "{\"Location\":\"Mount Lawlor\",\"Latitude\":34.2708353,\"Longitude\":-118.1039592,\"Difficulty\":3},\n" +
            "{\"Location\":\"Upper Big Sycamore Canyon\",\"Latitude\":33.975699,\"Longitude\":-117.3179816,\"Difficulty\":0},\n" +
            "{\"Location\":\"Nicholas Flat\",\"Latitude\":34.0665662,\"Longitude\":-118.9126083,\"Difficulty\":1},\n" +
            "{\"Location\":\"Wildwood Regional Park\",\"Latitude\":40.831339,\"Longitude\":-74.357468,\"Difficulty\":3},\n" +
            "{\"Location\":\"Rocky Oaks Park\",\"Latitude\":44.2949554,\"Longitude\":-111.4566608,\"Difficulty\":1},\n" +
            "{\"Location\":\"Paramount Ranch\",\"Latitude\":34.1155816,\"Longitude\":-118.7562884,\"Difficulty\":2},\n" +
            "{\"Location\":\"Las Virgenes Canyon\",\"Latitude\":34.1722273,\"Longitude\":-118.7023111,\"Difficulty\":0},\n" +
            "{\"Location\":\"Victory head Loop\",\"Latitude\":44.0762472,\"Longitude\":-86.3746629,\"Difficulty\":2},\n" +
            "{\"Location\":\"Cheeseboro and Palo Comado Canyons\",\"Latitude\":34.1837788,\"Longitude\":-118.7372922,\"Difficulty\":1},\n" +
            "{\"Location\":\"King Gillette Ranch\",\"Latitude\":34.1018989,\"Longitude\":-118.704936,\"Difficulty\":2},\n" +
            "{\"Location\":\"Corral Canyon\",\"Latitude\":34.0336184,\"Longitude\":-118.7342532,\"Difficulty\":3},\n" +
            "{\"Location\":\"Escondido Falls\",\"Latitude\":34.0259721,\"Longitude\":-118.7797592,\"Difficulty\":2},\n" +
            "{\"Location\":\"Trebek Open Space\",\"Latitude\":34.1104851,\"Longitude\":-118.3589785,\"Difficulty\":1},\n" +
            "{\"Location\":\"Murphy Ranch\",\"Latitude\":34.072382,\"Longitude\":-118.5138881,\"Difficulty\":1},\n" +
            "{\"Location\":\"Towsley Canyon\",\"Latitude\":34.3605542,\"Longitude\":-118.5548086,\"Difficulty\":3},\n" +
            "{\"Location\":\"Charmlee Wilderness Park\",\"Latitude\":34.0618131,\"Longitude\":-118.8770557,\"Difficulty\":3},\n" +
            "{\"Location\":\"Malibu Creek\",\"Latitude\":34.0625028,\"Longitude\":-118.6997438,\"Difficulty\":1},\n" +
            "{\"Location\":\"Fryman Canyon\",\"Latitude\":34.1426344,\"Longitude\":-118.3936808,\"Difficulty\":1},\n" +
            "{\"Location\":\"Mugu Peak\",\"Latitude\":34.0925068,\"Longitude\":-119.0548238,\"Difficulty\":3},\n" +
            "{\"Location\":\"Old Boney \",\"Latitude\":35.5392428,\"Longitude\":-82.0576413,\"Difficulty\":0},\n" +
            "{\"Location\":\"La Jolla Canyon\",\"Latitude\":33.0097838,\"Longitude\":-109.4639644,\"Difficulty\":1},\n" +
            "{\"Location\":\"Solstice Canyon\",\"Latitude\":34.0484269,\"Longitude\":-118.7576968,\"Difficulty\":2},\n" +
            "{\"Location\":\"Runyon Canyon\",\"Latitude\":34.1106805,\"Longitude\":-118.3503784,\"Difficulty\":1},\n" +
            "{\"Location\":\"Grotto \",\"Latitude\":47.737412,\"Longitude\":-121.4238318,\"Difficulty\":3},\n" +
            "{\"Location\":\"Engelmann Oak Loop\",\"Latitude\":33.5845893,\"Longitude\":-117.2243587,\"Difficulty\":3},\n" +
            "{\"Location\":\"Vista Hermosa Natural Park\",\"Latitude\":34.0619569,\"Longitude\":-118.2579459,\"Difficulty\":1},\n" +
            "{\"Location\":\"Double Peak\",\"Latitude\":33.1094846,\"Longitude\":-117.1775343,\"Difficulty\":1},\n" +
            "{\"Location\":\"Saddleback Butte State Park\",\"Latitude\":34.6895546,\"Longitude\":-117.8236985,\"Difficulty\":2},\n" +
            "{\"Location\":\"Baldwin Hills Scenic Overlook\",\"Latitude\":34.0175826,\"Longitude\":-118.3840379,\"Difficulty\":1},\n" +
            "{\"Location\":\"Franklin Canyon\",\"Latitude\":36.2691161,\"Longitude\":-118.968989,\"Difficulty\":2},\n" +
            "{\"Location\":\"Ernest E. Debs Regional Park\",\"Latitude\":34.096562,\"Longitude\":-118.1956408,\"Difficulty\":1},\n" +
            "{\"Location\":\"Angel&#8217;s Point\",\"Latitude\":33.8288321,\"Longitude\":-85.8372015,\"Difficulty\":1},\n" +
            "{\"Location\":\"Elysian Park West Loop\",\"Latitude\":44.1995343,\"Longitude\":-93.6781788,\"Difficulty\":1},\n" +
            "{\"Location\":\"Vasquez Rocks\",\"Latitude\":34.4855487,\"Longitude\":-118.3173013,\"Difficulty\":2},\n" +
            "{\"Location\":\"Antelope Valley California Poppy Reserve\",\"Latitude\":34.7349045,\"Longitude\":-118.3960096,\"Difficulty\":2},\n" +
            "{\"Location\":\"Eaton Canyon\",\"Latitude\":34.16224,\"Longitude\":-118.085,\"Difficulty\":2},\n" +
            "{\"Location\":\"Beaudry Loop in the Verdugo Mountains\",\"Latitude\":34.187829,\"Longitude\":-118.2523225,\"Difficulty\":1},\n" +
            "{\"Location\":\"Fryman Canyon\",\"Latitude\":34.1426344,\"Longitude\":-118.3936808,\"Difficulty\":3},\n" +
            "{\"Location\":\"Runyon Canyon\",\"Latitude\":34.1106805,\"Longitude\":-118.3503784,\"Difficulty\":0},\n" +
            "{\"Location\":\"Taco Peak\",\"Latitude\":33.8555532,\"Longitude\":-112.1378733,\"Difficulty\":3},\n" +
            "{\"Location\":\"Amir&#8217;s Garden\",\"Latitude\":37.980267,\"Longitude\":-100.850223,\"Difficulty\":3},\n" +
            "{\"Location\":\"Western Canyon\",\"Latitude\":34.9082274,\"Longitude\":-101.8844273,\"Difficulty\":0},\n" +
            "{\"Location\":\"Glendale Peak\",\"Latitude\":35.6576751,\"Longitude\":-78.7349524,\"Difficulty\":3},\n" +
            "{\"Location\":\"Firebreak  to Griffith Observatory\",\"Latitude\":34.1145321,\"Longitude\":-118.3071022,\"Difficulty\":0},\n" +
            "{\"Location\":\"East Griffith Observatory \",\"Latitude\":34.1184341,\"Longitude\":-118.3003935,\"Difficulty\":2},\n" +
            "{\"Location\":\"West Griffith Observatory \",\"Latitude\":34.1184341,\"Longitude\":-118.3003935,\"Difficulty\":1},\n" +
            "{\"Location\":\"Beacon Hill in Griffith Park\",\"Latitude\":34.1184525,\"Longitude\":-118.2737583,\"Difficulty\":2},\n" +
            "{\"Location\":\"Hollywood Reservoir\",\"Latitude\":34.1197689,\"Longitude\":-118.3308532,\"Difficulty\":0},\n" +
            "{\"Location\":\"Mount Hollywood\",\"Latitude\":34.1280637,\"Longitude\":-118.3011874,\"Difficulty\":3},\n" +
            "{\"Location\":\"Del Mar Mesa Preserve\",\"Latitude\":32.9512588,\"Longitude\":-117.1625037,\"Difficulty\":3},\n" +
            "{\"Location\":\"Indianhead Mountain\",\"Latitude\":49.6644444,\"Longitude\":-125.2497222,\"Difficulty\":3},\n" +
            "{\"Location\":\"Gonzales Canyon\",\"Latitude\":36.9166933,\"Longitude\":-105.0627784,\"Difficulty\":1},\n" +
            "{\"Location\":\"Barker Valley\",\"Latitude\":32.8473958,\"Longitude\":-85.2048894,\"Difficulty\":1},\n" +
            "{\"Location\":\"Lake Calavera\",\"Latitude\":33.1711358,\"Longitude\":-117.2839609,\"Difficulty\":1},\n" +
            "{\"Location\":\"Canyon Sin Nombre\",\"Latitude\":32.8491627,\"Longitude\":-116.1538663,\"Difficulty\":1},\n" +
            "{\"Location\":\"Viejas Mountain\",\"Latitude\":32.8611624,\"Longitude\":-116.7258546,\"Difficulty\":0},\n" +
            "{\"Location\":\"Black Mountain\",\"Latitude\":35.6178951,\"Longitude\":-82.3212302,\"Difficulty\":1},\n" +
            "{\"Location\":\"Boucher Hill Loop\",\"Latitude\":44.6132876,\"Longitude\":-87.6931983,\"Difficulty\":1},\n" +
            "{\"Location\":\"Corte Madera Mountain\",\"Latitude\":32.7575535,\"Longitude\":-116.5911294,\"Difficulty\":1},\n" +
            "{\"Location\":\"Engelmann Oak Loop\",\"Latitude\":33.5845893,\"Longitude\":-117.2243587,\"Difficulty\":3},\n" +
            "{\"Location\":\"Boden Canyon\",\"Latitude\":33.0922646,\"Longitude\":-116.8966937,\"Difficulty\":0},\n" +
            "{\"Location\":\"Whale Peak\",\"Latitude\":33.0292139,\"Longitude\":-116.3161234,\"Difficulty\":0},\n" +
            "{\"Location\":\"Mountain Palm Springs\",\"Latitude\":32.8642201,\"Longitude\":-116.233348,\"Difficulty\":0},\n" +
            "{\"Location\":\"Pinyon Ridge\",\"Latitude\":33.1780555,\"Longitude\":-116.4552777,\"Difficulty\":2},\n" +
            "{\"Location\":\"Split Mountain\",\"Latitude\":37.0202851,\"Longitude\":-118.4218804,\"Difficulty\":0},\n" +
            "{\"Location\":\"Stanley Peak\",\"Latitude\":51.1699999,\"Longitude\":-116.0533333,\"Difficulty\":0},\n" +
            "{\"Location\":\"El Cajon Mountain\",\"Latitude\":32.9147711,\"Longitude\":-116.8197465,\"Difficulty\":1},\n" +
            "{\"Location\":\"Bow Willow and Rockhouse Canyons\",\"Latitude\":32.842349,\"Longitude\":-116.225824,\"Difficulty\":2},\n" +
            "{\"Location\":\"Guajome County Park\",\"Latitude\":33.2448343,\"Longitude\":-117.2750294,\"Difficulty\":0},\n" +
            "{\"Location\":\"San Onofre State Beach\",\"Latitude\":33.3761505,\"Longitude\":-117.5693128,\"Difficulty\":3},\n" +
            "{\"Location\":\"Hot Springs Mountain\",\"Latitude\":33.3150362,\"Longitude\":-116.5797401,\"Difficulty\":3},\n" +
            "{\"Location\":\"Bernardo Mountain and Lake Hodges\",\"Latitude\":33.0639309,\"Longitude\":-117.088088,\"Difficulty\":3},\n" +
            "{\"Location\":\"Wooded Hill\",\"Latitude\":33.7623059,\"Longitude\":-89.8231168,\"Difficulty\":2},\n" +
            "{\"Location\":\"Guatay Mountain\",\"Latitude\":32.8433842,\"Longitude\":-116.5741849,\"Difficulty\":1},\n" +
            "{\"Location\":\"Double Peak\",\"Latitude\":33.1094846,\"Longitude\":-117.1775343,\"Difficulty\":1},\n" +
            "{\"Location\":\"Eagle Rock\",\"Latitude\":34.1322469,\"Longitude\":-118.2117257,\"Difficulty\":1},\n" +
            "{\"Location\":\"Stonewall Peak\",\"Latitude\":32.960881,\"Longitude\":-116.5714077,\"Difficulty\":0},\n" +
            "{\"Location\":\"Mount Gower\",\"Latitude\":33.0164351,\"Longitude\":-116.7625233,\"Difficulty\":0},\n" +
            "{\"Location\":\"Observatory \",\"Latitude\":33.6998598,\"Longitude\":-117.9175784,\"Difficulty\":3},\n" +
            "{\"Location\":\"Iron Mountain\",\"Latitude\":45.8202334,\"Longitude\":-88.0659603,\"Difficulty\":1},\n" +
            "{\"Location\":\"Mount Woodson\",\"Latitude\":33.0086557,\"Longitude\":-116.9705855,\"Difficulty\":3},\n" +
            "{\"Location\":\"Cuyamaca Peak\",\"Latitude\":32.9467149,\"Longitude\":-116.6064084,\"Difficulty\":2},\n" +
            "{\"Location\":\"Clevenger Canyon\",\"Latitude\":33.0892091,\"Longitude\":-116.9019716,\"Difficulty\":2},\n" +
            "{\"Location\":\"Torrey Pines Reserve Extension\",\"Latitude\":32.8434461,\"Longitude\":-117.2716076,\"Difficulty\":2},\n" +
            "{\"Location\":\"Los Peñasquitos Canyon\",\"Latitude\":32.902824,\"Longitude\":-117.2228136,\"Difficulty\":2},\n" +
            "{\"Location\":\"Batiquitos Lagoon\",\"Latitude\":33.0892947,\"Longitude\":-117.2925942,\"Difficulty\":2},\n" +
            "{\"Location\":\"Cedar Creek Falls\",\"Latitude\":32.9894915,\"Longitude\":-116.7303002,\"Difficulty\":1},\n" +
            "{\"Location\":\"Volcan Mountain\",\"Latitude\":41.2080186,\"Longitude\":-107.0078278,\"Difficulty\":2},\n" +
            "{\"Location\":\"Big Laguna \",\"Latitude\":32.9056145,\"Longitude\":-96.9360125,\"Difficulty\":3},\n" +
            "{\"Location\":\"Santa Margarita River\",\"Latitude\":33.2319793,\"Longitude\":-117.4161509,\"Difficulty\":3},\n" +
            "{\"Location\":\"Kanaka Flat\",\"Latitude\":39.4709223,\"Longitude\":-120.8382609,\"Difficulty\":0},\n" +
            "{\"Location\":\"Daley Ranch\",\"Latitude\":33.1812741,\"Longitude\":-117.0617553,\"Difficulty\":2},\n" +
            "{\"Location\":\"Torrey Pines State Natural Reserve\",\"Latitude\":32.9216491,\"Longitude\":-117.2535292,\"Difficulty\":2},\n" +
            "{\"Location\":\"Palomar Mountain State Park\",\"Latitude\":33.31691,\"Longitude\":-116.8779263,\"Difficulty\":1},\n" +
            "{\"Location\":\"Borrego Palm Canyon\",\"Latitude\":33.2717045,\"Longitude\":-116.4075131,\"Difficulty\":2},\n" +
            "{\"Location\":\"San Mateo Canyon\",\"Latitude\":35.3430887,\"Longitude\":-107.6625554,\"Difficulty\":2},\n" +
            "{\"Location\":\"Santiago Peak via Trabuco Canyon\",\"Latitude\":33.6812817,\"Longitude\":-117.6250603,\"Difficulty\":3},\n" +
            "{\"Location\":\"Sitton Peak\",\"Latitude\":33.5875232,\"Longitude\":-117.446154,\"Difficulty\":1},\n" +
            "{\"Location\":\"Los Piños Peak\",\"Latitude\":33.6641876,\"Longitude\":-117.4711553,\"Difficulty\":1},\n" +
            "{\"Location\":\"Indianhead Mountain\",\"Latitude\":49.6644444,\"Longitude\":-125.2497222,\"Difficulty\":0},\n" +
            "{\"Location\":\"Canyon Sin Nombre\",\"Latitude\":32.8491627,\"Longitude\":-116.1538663,\"Difficulty\":3},\n" +
            "{\"Location\":\"Whale Peak\",\"Latitude\":33.0292139,\"Longitude\":-116.3161234,\"Difficulty\":2},\n" +
            "{\"Location\":\"Mountain Palm Springs\",\"Latitude\":32.8642201,\"Longitude\":-116.233348,\"Difficulty\":0},\n" +
            "{\"Location\":\"Pinyon Ridge\",\"Latitude\":33.1780555,\"Longitude\":-116.4552777,\"Difficulty\":0},\n" +
            "{\"Location\":\"Split Mountain\",\"Latitude\":37.0202851,\"Longitude\":-118.4218804,\"Difficulty\":3},\n" +
            "{\"Location\":\"Bow Willow and Rockhouse Canyons\",\"Latitude\":32.842349,\"Longitude\":-116.225824,\"Difficulty\":1},\n" +
            "{\"Location\":\"Big Laguna \",\"Latitude\":32.9056145,\"Longitude\":-96.9360125,\"Difficulty\":3},\n" +
            "{\"Location\":\"Borrego Palm Canyon\",\"Latitude\":33.2717045,\"Longitude\":-116.4075131,\"Difficulty\":2},\n" +
            "{\"Location\":\"Tahquitz Peak\",\"Latitude\":33.7558539,\"Longitude\":-116.6761288,\"Difficulty\":3},\n" +
            "{\"Location\":\"Bertha Peak\",\"Latitude\":34.2830617,\"Longitude\":-116.899199,\"Difficulty\":2},\n" +
            "{\"Location\":\"Willow Hole\",\"Latitude\":45.7435806,\"Longitude\":-122.8207096,\"Difficulty\":1},\n" +
            "{\"Location\":\"Pine City \",\"Latitude\":45.8229105,\"Longitude\":-92.9703435,\"Difficulty\":2},\n" +
            "{\"Location\":\"Cholla Cactus Garden\",\"Latitude\":33.9247516,\"Longitude\":-115.929411,\"Difficulty\":1},\n" +
            "{\"Location\":\"Lost Palms Oasis\",\"Latitude\":33.7125679,\"Longitude\":-115.7623865,\"Difficulty\":2},\n" +
            "{\"Location\":\"Hidden Valley\",\"Latitude\":43.1348136,\"Longitude\":-77.712564,\"Difficulty\":3},\n" +
            "{\"Location\":\"The Maze\",\"Latitude\":40.6123033,\"Longitude\":-77.1955405,\"Difficulty\":0},\n" +
            "{\"Location\":\"Barker Dam Loop\",\"Latitude\":34.0211639,\"Longitude\":-116.1522132,\"Difficulty\":3},\n" +
            "{\"Location\":\"Ryan Mountain\",\"Latitude\":33.9858889,\"Longitude\":-116.1347357,\"Difficulty\":1},\n" +
            "{\"Location\":\"Inspiration Peak\",\"Latitude\":46.1360721,\"Longitude\":-95.5736544,\"Difficulty\":1},\n" +
            "{\"Location\":\"Queen Mountain\",\"Latitude\":48.8780022,\"Longitude\":-116.2149213,\"Difficulty\":2},\n" +
            "{\"Location\":\"Ashford Canyon\",\"Latitude\":35.9477351,\"Longitude\":-116.6819844,\"Difficulty\":2},\n" +
            "{\"Location\":\"Darwin Falls\",\"Latitude\":36.3207418,\"Longitude\":-117.5244607,\"Difficulty\":0},\n" +
            "{\"Location\":\"Salt Creek\",\"Latitude\":40.3750494,\"Longitude\":-123.1320648,\"Difficulty\":2},\n" +
            "{\"Location\":\"Badwater Basin\",\"Latitude\":36.250278,\"Longitude\":-116.825833,\"Difficulty\":3},\n" +
            "{\"Location\":\"Limekiln State Park in Big Sur\",\"Latitude\":36.0495805,\"Longitude\":-121.5898093,\"Difficulty\":2},\n" +
            "{\"Location\":\"Pfeiffer Big Sur State Park\",\"Latitude\":36.2479341,\"Longitude\":-121.7812662,\"Difficulty\":0},\n" +
            "{\"Location\":\"Andrew Molera State Park in Big Sur\",\"Latitude\":36.2884189,\"Longitude\":-121.844272,\"Difficulty\":2},\n" +
            "{\"Location\":\"Point Lobos State Reserve in Big Sur\",\"Latitude\":36.5159123,\"Longitude\":-121.9422821,\"Difficulty\":1},\n" +
            "{\"Location\":\"Junipero Serra Peak\",\"Latitude\":36.1455185,\"Longitude\":-121.4193777,\"Difficulty\":2},\n" +
            "{\"Location\":\"Duck Lake\",\"Latitude\":43.3390724,\"Longitude\":-86.3921491,\"Difficulty\":3},\n" +
            "{\"Location\":\"Thousand Island Lake\",\"Latitude\":37.7204427,\"Longitude\":-119.1833985,\"Difficulty\":3},\n" +
            "{\"Location\":\"Fossil Falls\",\"Latitude\":35.969946,\"Longitude\":-117.9089637,\"Difficulty\":2},\n" +
            "{\"Location\":\"Jordan Hot Springs\",\"Latitude\":36.2291067,\"Longitude\":-118.3034189,\"Difficulty\":3},\n" +
            "{\"Location\":\"Rae Lakes Loop\",\"Latitude\":32.071268,\"Longitude\":-82.8923825,\"Difficulty\":2},\n" +
            "{\"Location\":\"Parker Lake\",\"Latitude\":37.6867211,\"Longitude\":-90.0776139,\"Difficulty\":3},\n" +
            "{\"Location\":\"Convict Lake\",\"Latitude\":37.5898951,\"Longitude\":-118.8582239,\"Difficulty\":2},\n" +
            "{\"Location\":\"Rock Creek Lake\",\"Latitude\":40.9668572,\"Longitude\":-96.4990611,\"Difficulty\":2},\n" +
            "{\"Location\":\"North Fork of Big Pine Creek\",\"Latitude\":37.1250058,\"Longitude\":-118.5013703,\"Difficulty\":0},\n" +
            "{\"Location\":\"McGee Creek Canyon\",\"Latitude\":34.4104662,\"Longitude\":-83.1211269,\"Difficulty\":0},\n" +
            "{\"Location\":\"Sherwin Lakes\",\"Latitude\":37.6107696,\"Longitude\":-118.9451316,\"Difficulty\":0},\n" +
            "{\"Location\":\"Treasure Lakes\",\"Latitude\":37.3877177,\"Longitude\":-118.7653478,\"Difficulty\":1},\n" +
            "{\"Location\":\"North Lake Road Fall Foliage\",\"Latitude\":29.8149266,\"Longitude\":-95.2030418,\"Difficulty\":3},\n" +
            "{\"Location\":\"Mount Whitney\",\"Latitude\":36.5784991,\"Longitude\":-118.29226,\"Difficulty\":2},\n" +
            "{\"Location\":\"Devil&#8217;s Postpile\",\"Latitude\":37.6243807,\"Longitude\":-119.0818007,\"Difficulty\":1},\n" +
            "{\"Location\":\"Junipero Serra Peak\",\"Latitude\":36.1455185,\"Longitude\":-121.4193777,\"Difficulty\":2},\n" +
            "{\"Location\":\"Sespe Creek\",\"Latitude\":34.5517403,\"Longitude\":-118.9462491,\"Difficulty\":1},\n" +
            "{\"Location\":\"Yosemite National Park &#8211; Chilnualna Falls\",\"Latitude\":37.5478584,\"Longitude\":-119.6431818,\"Difficulty\":2},\n" +
            "{\"Location\":\"Cathedral Lakes\",\"Latitude\":37.843523,\"Longitude\":-119.4209251,\"Difficulty\":2},\n" +
            "{\"Location\":\"Sugar Pine \",\"Latitude\":37.4405372,\"Longitude\":-119.6364523,\"Difficulty\":1},\n" +
            "{\"Location\":\"Soldiers Loop\",\"Latitude\":38.6913409,\"Longitude\":-90.7328924,\"Difficulty\":3},\n" +
            "{\"Location\":\"Moro Rock in Sequoia National Park\",\"Latitude\":36.5486611,\"Longitude\":-118.7665649,\"Difficulty\":1},\n" +
            "{\"Location\":\"Forests of the Sequoias\",\"Latitude\":37.372485,\"Longitude\":-122.221188,\"Difficulty\":1},\n" +
            "{\"Location\":\"Mount Whitney\",\"Latitude\":36.5784991,\"Longitude\":-118.29226,\"Difficulty\":2},\n" +
            "{\"Location\":\"Zion National Park\",\"Latitude\":37.2982022,\"Longitude\":-113.0263005,\"Difficulty\":0},\n" +
            "{\"Location\":\"Syncline Loop \",\"Latitude\":30.5333563,\"Longitude\":-98.4034103,\"Difficulty\":0},\n" +
            "{\"Location\":\"Dead Horse Point State Park\",\"Latitude\":38.5010992,\"Longitude\":-109.7375474,\"Difficulty\":2},\n" +
            "{\"Location\":\"Newspaper Rock State Historic Monument\",\"Latitude\":37.988329,\"Longitude\":-109.5184791,\"Difficulty\":3},\n" +
            "{\"Location\":\"Mesa Arch \",\"Latitude\":34.6553344,\"Longitude\":-106.8176538,\"Difficulty\":0},\n" +
            "{\"Location\":\"Fiery Furnace\",\"Latitude\":38.7435404,\"Longitude\":-109.5615864,\"Difficulty\":3},\n" +
            "{\"Location\":\"Delicate Arch\",\"Latitude\":38.7436297,\"Longitude\":-109.4993124,\"Difficulty\":1},\n" +
            "{\"Location\":\"Devils Garden\",\"Latitude\":43.615678,\"Longitude\":-122.1947596,\"Difficulty\":0},\n" +
            "{\"Location\":\"Windows Loop and Double Arch\",\"Latitude\":38.6915718,\"Longitude\":-109.5403037,\"Difficulty\":2}]";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private GoogleMap mGoogleMap;
    private ViewPager mViewPager;
    private int[] tabIcons = {
            R.drawable.ic_map_marker,
            R.drawable.ic_list
    };

    private AutoCompleteTextView actv;
    ArrayAdapter<String> adapter;
    String mLocation;
    boolean mUseDefault = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_view);
        Intent intent = getIntent();
        mLocation = intent.getStringExtra("LOCATION");
        mGoogleMap = null;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(MatchView.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {  // protection
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
        /*TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);*/
        addOptions();
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

    String[] COUNTRIES = new String[] {
            "#popular", "#friends", "#yosemite", "#bayarea", "Big Basin", "Big Sur", "#favorites",
            "#myhikes", "#myfriends"
    };

    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

    private void addOptions() {
        actv = (AutoCompleteTextView) findViewById(R.id.searchFieldMatches);
        adapter = new ArrayAdapter<String>(this,
                R.layout.list_view_row, COUNTRIES);
        actv.setAdapter(adapter);

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mUseDefault = false;
                refreshMap(adapter.getItem(position).toString());
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
                        mUseDefault = false;
                        refreshMap(actv.getText().toString());
                        Log.i("Textual", actv.getText().toString());
                        hideKeyboard();
                        return true;
                    }
                }
                return false;
            }
        });
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
            return 1;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        boolean addFilter = false;
        if (mLocation.indexOf('#') >= 0)
        {
            addFilter = true;
            mLocation = mLocation.substring(mLocation.indexOf('#') + 1, mLocation.length());
        }

        JSONArray arr = null;
        mGoogleMap = googleMap;
        final Context context = this;
        try {
            arr = new JSONArray(data);
            for (int i = 0; i < arr.length(); i++) {
                if (addFilter)
                {
                    if (i % 2 == 0)
                        continue;
                }
                JSONObject obj = arr.getJSONObject(i);
                String location = obj.getString("Location");
                double latitude = obj.getDouble("Latitude");
                double longitude = obj.getDouble("Longitude");
                int difficulty = obj.getInt("Difficulty");
                float bdes = BitmapDescriptorFactory.HUE_AZURE;
                switch (difficulty) {
                    case 0:
                        bdes = BitmapDescriptorFactory.HUE_BLUE;
                        break;
                    case 1:
                        bdes = BitmapDescriptorFactory.HUE_YELLOW;
                        break;
                    case 2:
                        bdes = BitmapDescriptorFactory.HUE_ORANGE;
                        break;
                    case 3:
                        bdes = BitmapDescriptorFactory.HUE_RED;
                        break;
                }

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitude, longitude))
                        .title(location)
                        .icon(BitmapDescriptorFactory.defaultMarker(bdes)));
                ;
                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
                                                       @Override
                                                       public boolean onMarkerClick(Marker marker) {
                                                           String location = marker.getTitle();
                                                           Intent intent = new Intent(context, TrailView.class);
                                                           intent.putExtra("LOCATION", location);
                                                           startActivity(intent);
                                                           return false;
                                                       }
                                                   }
                );

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        changeLocation(mLocation, true);
    }

    void refreshMap(String location)
    {
        mLocation = location;
        mGoogleMap.clear();
        onMapReady(mGoogleMap);
    }

    void changeLocation(String location, boolean useDefault)
    {
        if (mGoogleMap == null)
        {
            return;
        }
        double lat = 37.774, lng = -122.419;
        Geocoder gc = new Geocoder(this);
        List<Address> list = null;
        try {
            list = gc.getFromLocationName(location, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (list != null && list.size() > 0) {
            Address address = list.get(0);
            lat = address.getLatitude();
            lng = address.getLongitude();
            LatLng locationPos = new LatLng(lat, lng);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(locationPos)      // Sets the center of the map to Mountain View
                    .zoom(8)                   // Sets the zoom
                    .build();                   // Creates a CameraPosition from the builder
            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        else
        {
            if(useDefault){
                LatLng locationPos = new LatLng(lat, lng);
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(locationPos)      // Sets the center of the map to Mountain View
                        .zoom(8)                   // Sets the zoom
                        .build();                   // Creates a CameraPosition from the builder
                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
            Toast.makeText(getApplicationContext(), "Could not find a match",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFragmentReady(Fragment fragment){
        if (fragment instanceof MatchMapView){
            MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

    }
}
