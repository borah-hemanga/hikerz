package hikerz.com.hikerz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import hikerz.com.hikerz.fragments.FragmentInteractionListener;
import hikerz.com.hikerz.fragments.MatchMapView;
import hikerz.com.hikerz.fragments.TrailViewFragment;

public class TrailView extends AppCompatActivity implements OnMapReadyCallback, FragmentInteractionListener {
    String mLocation;
    Double[][] contourPath = {{36.5554175,-118.7490034},{36.5553288,-118.7489515},{36.5552745,-118.7488559},{36.5550300,-118.7489388},{36.5549147,-118.7489316},{36.5548001,-118.7489196},{36.5547046,-118.7488681},{36.5547046,-118.7488681},{36.5546267,-118.7486844},{36.5546216,-118.7485642},{36.5545411,-118.7485100},{36.5544476,-118.7484117},{36.5543503,-118.7483583},{36.5542408,-118.7482494},{36.5541239,-118.7482293},{36.5540317,-118.7481811},{36.5539010,-118.7481688},{36.5538118,-118.7482180},{36.5537433,-118.7483077},{36.5536539,-118.7483505},{36.5535837,-118.7482705},{36.5534848,-118.7482042},{36.5534085,-118.7481391},{36.5532494,-118.7480961},{36.5531581,-118.7480248},{36.5531368,-118.7479051},{36.5531726,-118.7477728},{36.5532376,-118.7476543},{36.5533508,-118.7476356},{36.5534469,-118.7476122},{36.5534475,-118.7474864},{36.5534806,-118.7473738},{36.5535569,-118.7472912},{36.5536509,-118.7472370},{36.5537754,-118.7471762},{36.5538923,-118.7471020},{36.5539881,-118.7470523},{36.5541027,-118.7470044},{36.5542070,-118.7469983},{36.5543180,-118.7470116},{36.5544064,-118.7470535},{36.5544949,-118.7470046},{36.5545864,-118.7469985},{36.5546896,-118.7469936},{36.5547863,-118.7470365},{36.5548383,-118.7471314},{36.5549420,-118.7471271},{36.5549574,-118.7470123},{36.5549821,-118.7468661},{36.5550323,-118.7467477},{36.5550653,-118.7466312},{36.5550609,-118.7465098},{36.5550163,-118.7463818},{36.5549852,-118.7462745},{36.5549629,-118.7461545},{36.5549237,-118.7460431},{36.5549100,-118.7459130},{36.5549190,-118.7457932},{36.5549325,-118.7456672},{36.5549767,-118.7455511},{36.5550167,-118.7454322},{36.5550743,-118.7453458},{36.5551569,-118.7452841},{36.5552650,-118.7452448},{36.5553607,-118.7452366},{36.5554421,-118.7451859},{36.5555309,-118.7451250},{36.5556166,-118.7450617},{36.5556997,-118.7449613},{36.5557833,-118.7448823},{36.5558232,-118.7447753},{36.5558185,-118.7446597},{36.5558495,-118.7445487},{36.5559245,-118.7444696},{36.5559801,-118.7443731},{36.5560522,-118.7442937},{36.5561177,-118.7442010},{36.5561635,-118.7440973},{36.5562097,-118.7439942},{36.5562497,-118.7438788},{36.5563200,-118.7437988},{36.5564018,-118.7437303},{36.5564897,-118.7436973},{36.5565716,-118.7436439},{36.5566593,-118.7436056},{36.5567680,-118.7436386},{36.5568630,-118.7436629},{36.5569651,-118.7436116},{36.5570592,-118.7435941},{36.5571582,-118.7435951},{36.5572525,-118.7435737},{36.5573427,-118.7435678},{36.5574401,-118.7435716},{36.5575368,-118.7435645},{36.5576306,-118.7435711},{36.5577640,-118.7435884},{36.5578617,-118.7435944},{36.5579619,-118.7435863},{36.5580568,-118.7436149},{36.5581570,-118.7436358},{36.5582483,-118.7436350},{36.5583512,-118.7436399},{36.5584573,-118.7436471},{36.5585621,-118.7436523},{36.5587625,-118.7435729},{36.5588546,-118.7435423},{36.5589515,-118.7435762},{36.5590429,-118.7435902},{36.5591371,-118.7435825},{36.5591895,-118.7434353},{36.5592713,-118.7433255},{36.5593639,-118.7432656},{36.5594558,-118.7432964},{36.5596374,-118.7433777},{36.5597469,-118.7434028},{36.5598370,-118.7433757},{36.5599336,-118.7433296},{36.5596980,-118.7432505},{36.5597906,-118.7432905},{36.5599284,-118.7433582},{36.5600299,-118.7433919},{36.5601383,-118.7434014},{36.5602295,-118.7434170},{36.5602333,-118.7425352},{36.5604067,-118.7425648},{36.5604169,-118.7426864},{36.5604169,-118.7426864},{36.5605077,-118.7426700},{36.5605777,-118.7425738},{36.5606146,-118.7424536},{36.5606341,-118.7423283},{36.5607268,-118.7422212},{36.5608122,-118.7421764},{36.5608335,-118.7420139},{36.5608532,-118.7417158},{36.5609662,-118.7415809},{36.5610582,-118.7415091},{36.5611538,-118.7413871},{36.5612746,-118.7413429},{36.5613898,-118.7412703},{36.5614306,-118.7411624},{36.5614814,-118.7410668},{36.5615251,-118.7409682},{36.5615757,-118.7408758},{36.5615126,-118.7407853},{36.5614170,-118.7407129},{36.5612994,-118.7406161},{36.5612092,-118.7405998},{36.5611173,-118.7405689},{36.5610288,-118.7406053},{36.5609453,-118.7405093},{36.5609490,-118.7403452},{36.5609135,-118.7402343},{36.5608643,-118.7401266},{36.5608228,-118.7400164},{36.5607825,-118.7398960},{36.5607045,-118.7398246},{36.5606241,-118.7397557},{36.5605328,-118.7397971},{36.5604364,-118.7398118},{36.5604364,-118.7398118},{36.5603913,-118.7396677},{36.5603992,-118.7395403},{36.5604316,-118.7394123},{36.5603808,-118.7392874},{36.5603113,-118.7391976},{36.5602073,-118.7391578},{36.5600399,-118.7391258},{36.5600008,-118.7389869},{36.5599671,-118.7388392},{36.5599215,-118.7387167},{36.5598604,-118.7386332},{36.5597550,-118.7386026},{36.5596476,-118.7386357},{36.5595870,-118.7387264},{36.5595024,-118.7387863},{36.5594086,-118.7387996},{36.5593169,-118.7387991},{36.5592189,-118.7388469},{36.5591286,-118.7388518},{36.5591269,-118.7390954},{36.5590699,-118.7390066},{36.5590511,-118.7388469},{36.5590503,-118.7387316},{36.5590759,-118.7386012},{36.5591620,-118.7385134},{36.5592625,-118.7384781},{36.5593782,-118.7384488},{36.5595062,-118.7384067},{36.5596042,-118.7383765},{36.5596907,-118.7383447},{36.5597831,-118.7383147},{36.5598681,-118.7382601},{36.5599737,-118.7382504},{36.5600774,-118.7382424},{36.5601699,-118.7382621},{36.5602613,-118.7381386},{36.5603500,-118.7380473},{36.5604455,-118.7379501},{36.5605277,-118.7378302},{36.5606300,-118.7378027},{36.5606382,-118.7376822},{36.5606517,-118.7375706},{36.5607048,-118.7374652},{36.5607716,-118.7373847},{36.5608072,-118.7372761},{36.5608288,-118.7371656},{36.5608912,-118.7370794},{36.5609235,-118.7369673},{36.5608862,-118.7368549},{36.5608172,-118.7367669},{36.5608032,-118.7366209},{36.5607137,-118.7365693},{36.5606289,-118.7365198},{36.5605369,-118.7364964},{36.5604877,-118.7363879},{36.5605207,-118.7362697},{36.5605574,-118.7361574},{36.5606373,-118.7360958},{36.5607078,-118.7360234},{36.5607872,-118.7359620},{36.5608746,-118.7359261},{36.5609724,-118.7359460},{36.5610653,-118.7359430},{36.5611630,-118.7359829},{36.5612610,-118.7359994},{36.5613590,-118.7360194},{36.5614504,-118.7360524},{36.5615206,-118.7361390},{36.5615982,-118.7362427},{36.5616854,-118.7362897},{36.5617774,-118.7363125},{36.5618871,-118.7363339},{36.5619797,-118.7363463},{36.5620716,-118.7363882},{36.5621632,-118.7364067},{36.5622640,-118.7364332},{36.5623410,-118.7365035},{36.5624353,-118.7365278},{36.5625336,-118.7365415},{36.5626210,-118.7365775},{36.5627141,-118.7366288},{36.5628052,-118.7366529},{36.5628970,-118.7366702},{36.5629876,-118.7366688},{36.5630781,-118.7366995},{36.5631737,-118.7366844},{36.5632656,-118.7366515},{36.5633577,-118.7366951},{36.5634488,-118.7366633},{36.5635501,-118.7366603},{36.5636520,-118.7366445},{36.5637439,-118.7366488},{36.5638501,-118.7366486},{36.5639505,-118.7366533},{36.5640555,-118.7366596},{36.5641375,-118.7367096},{36.5642265,-118.7366894},{36.5643050,-118.7366348},{36.5643881,-118.7365651},{36.5644642,-118.7364892},{36.5645547,-118.7364854},{36.5646443,-118.7365522},{36.5646667,-118.7363445},{36.5647144,-118.7364478},{36.5647849,-118.7365712},{36.5648343,-118.7366731},{36.5648240,-118.7368004},{36.5648698,-118.7369167},{36.5649446,-118.7370212},{36.5650377,-118.7371168},{36.5651220,-118.7371791},{36.5652199,-118.7372062},{36.5652853,-118.7372833},{36.5653575,-118.7373518},{36.5654116,-118.7374548},{36.5654785,-118.7375314},{36.5655635,-118.7375768},{36.5656628,-118.7376062},{36.5657533,-118.7376422},{36.5658481,-118.7376379},{36.5659370,-118.7376121},{36.5660255,-118.7375888},{36.5661277,-118.7375257},{36.5662234,-118.7375095},{36.5663107,-118.7374750},{36.5664020,-118.7374973},{36.5664755,-118.7374305},{36.5665534,-118.7373531},{36.5666368,-118.7373010},{36.5667093,-118.7372285},{36.5668021,-118.7372326},{36.5668933,-118.7372225},{36.5669832,-118.7371604},{36.5670648,-118.7372218},{36.5671598,-118.7372073},{36.5672497,-118.7372465},{36.5673475,-118.7372704},{36.5674665,-118.7372913},{36.5675654,-118.7373324},{36.5676475,-118.7374274},{36.5677181,-118.7375131},{36.5678020,-118.7375874},{36.5678869,-118.7376638},{36.5679394,-118.7377623},{36.5679978,-118.7378602},{36.5680508,-118.7379544},{36.5681354,-118.7380139},{36.5682322,-118.7380585},{36.5683214,-118.7381024},{36.5684043,-118.7381612},{36.5684825,-118.7382363},{36.5685650,-118.7382809},{36.5686583,-118.7382992},{36.5687719,-118.7383391},{36.5688442,-118.7384153},{36.5689394,-118.7384232},{36.5690334,-118.7384387},{36.5691260,-118.7384236},{36.5692218,-118.7384387},{36.5693118,-118.7384410},{36.5694016,-118.7384973},{36.5695340,-118.7385399},{36.5696382,-118.7385408},{36.5697339,-118.7384147},{36.5698317,-118.7384132},{36.5699263,-118.7384539},{36.5700198,-118.7384290},{36.5700078,-118.7385409},{36.5703078,-118.7385614},{36.5702634,-118.7386764},{36.5701723,-118.7387325},{36.5700946,-118.7388067},{36.5700196,-118.7388746},{36.5699728,-118.7391034},{36.5699364,-118.7392134},{36.5698519,-118.7393051},{36.5697925,-118.7393907},{36.5698460,-118.7394816},{36.5697849,-118.7395692},{36.5697286,-118.7396723},{36.5696543,-118.7397391},{36.5695208,-118.7397795},{36.5693732,-118.7398096},{36.5692488,-118.7400382},{36.5692160,-118.7401622},{36.5691813,-118.7402681},{36.5691312,-118.7403612},{36.5690838,-118.7404706},{36.5690535,-118.7406183},{36.5689897,-118.7407019},{36.5689106,-118.7407732},{36.5688224,-118.7408361},{36.5686938,-118.7408978},{36.5686025,-118.7408868},{36.5685067,-118.7408966},{36.5684116,-118.7409090},{36.5683470,-118.7410079},{36.5682797,-118.7410909},{36.5682249,-118.7411924},{36.5681722,-118.7413011},{36.5681200,-118.7414071},{36.5680508,-118.7414791},{36.5679825,-118.7415518},{36.5678936,-118.7416000},{36.5678003,-118.7416276},{36.5677242,-118.7417584},{36.5676238,-118.7417583},{36.5675253,-118.7417376},{36.5674269,-118.7417437},{36.5673426,-118.7418821},{36.5672394,-118.7418971},{36.5671050,-118.7419450},{36.5670144,-118.7419596},{36.5669268,-118.7420103},{36.5668357,-118.7420331},{36.5667595,-118.7421129},{36.5666832,-118.7421730},{36.5666024,-118.7423401},{36.5665039,-118.7423710},{36.5664139,-118.7424264},{36.5664108,-118.7425385},{36.5663686,-118.7426393},{36.5663133,-118.7427308},{36.5662474,-118.7428246},{36.5662246,-118.7429620},{36.5662456,-118.7430872},{36.5663381,-118.7431651},{36.5664733,-118.7432086},{36.5665658,-118.7432531},{36.5666547,-118.7432947},{36.5667078,-118.7433863},{36.5667470,-118.7434944},{36.5668173,-118.7435850},{36.5668584,-118.7436954},{36.5668918,-118.7438085},{36.5669490,-118.7439106},{36.5670036,-118.7439995},{36.5670717,-118.7440855},{36.5671385,-118.7441630},{36.5671717,-118.7442819},{36.5672364,-118.7443732},{36.5672788,-118.7444776},{36.5673257,-118.7445736},{36.5673332,-118.7446905},{36.5673152,-118.7448180},{36.5673450,-118.7449307},{36.5673724,-118.7450377},{36.5674297,-118.7451313},{36.5674520,-118.7452417},{36.5675211,-118.7453537},{36.5676125,-118.7454753},{36.5676839,-118.7455543},{36.5677787,-118.7455528},{36.5678835,-118.7455459},{36.5679673,-118.7454682},{36.5680464,-118.7454007},{36.5681377,-118.7453444},{36.5682300,-118.7453200},{36.5683098,-118.7452587},{36.5683806,-118.7451812},{36.5684555,-118.7450563},{36.5685109,-118.7449624},{36.5685880,-118.7448941},{36.5686845,-118.7448930},{36.5687640,-118.7449565},{36.5687264,-118.7450723},{36.5687507,-118.7451828},{36.5688545,-118.7452076},{36.5689494,-118.7452311},{36.5690380,-118.7452893},{36.5691309,-118.7454337},{36.5692276,-118.7454304},{36.5692947,-118.7452878},{36.5693828,-118.7453097},{36.5694701,-118.7453514},{36.5695646,-118.7453806},{36.5696558,-118.7453657},{36.5697512,-118.7453834},{36.5698452,-118.7454233},{36.5699234,-118.7454796},{36.5700310,-118.7455069},{36.5701230,-118.7455181},{36.5701475,-118.7456409},{36.5702339,-118.7457018},{36.5703289,-118.7457100},{36.5704322,-118.7457285},{36.5705391,-118.7457227},{36.5706218,-118.7457683},{36.5707058,-118.7458244},{36.5706855,-118.7459355},{36.5706672,-118.7460856},{36.5706994,-118.7462277},{36.5707650,-118.7463586},{36.5707757,-118.7464724},{36.5707153,-118.7466542},{36.5706423,-118.7467445},{36.5705567,-118.7468062},{36.5704623,-118.7468275},{36.5703788,-118.7469037},{36.5703716,-118.7470315},{36.5704169,-118.7471390},{36.5704888,-118.7472253},{36.5705751,-118.7472792},{36.5706463,-118.7473587},{36.5707166,-118.7474287},{36.5707746,-118.7475157},{36.5708653,-118.7475556},{36.5710790,-118.7476002},{36.5711849,-118.7475982},{36.5712851,-118.7475999},{36.5713803,-118.7476067},{36.5714760,-118.7476081},{36.5715722,-118.7475818},{36.5716639,-118.7475300},{36.5717548,-118.7475042},{36.5718545,-118.7475077},{36.5719435,-118.7475358},{36.5720303,-118.7475811},{36.5721232,-118.7476186},{36.5722132,-118.7476050},{36.5723050,-118.7475994},{36.5723698,-118.7476870},{36.5724292,-118.7477895},{36.5725154,-118.7478770},{36.5725820,-118.7479672},{36.5726625,-118.7480229},{36.5727370,-118.7480983},{36.5728038,-118.7481765},{36.5728971,-118.7482139},{36.5729012,-118.7483367},{36.5729141,-118.7484628},{36.5731927,-118.7489352},{36.5732756,-118.7489833},{36.5733647,-118.7490683},{36.5734321,-118.7491666},{36.5734830,-118.7492589},{36.5734530,-118.7493671},{36.5734187,-118.7494750},{36.5734383,-118.7495974},{36.5734763,-118.7497017},{36.5735341,-118.7498020},{36.5735802,-118.7499116},{36.5736511,-118.7500033},{36.5735510,-118.7499613},{36.5734700,-118.7499094},{36.5733801,-118.7499434},{36.5733206,-118.7500414},{36.5733106,-118.7501816},{36.5732763,-118.7500730},{36.5732363,-118.7499711},{36.5731526,-118.7498755},{36.5730978,-118.7497852},{36.5732132,-118.7498068},{36.5731103,-118.7499214},{36.5730041,-118.7498290},{36.5729452,-118.7496883},{36.5728638,-118.7495853},{36.5727688,-118.7494671},{36.5726979,-118.7493959},{36.5726059,-118.7493306},{36.5725160,-118.7492820},{36.5724377,-118.7492241},{36.5723710,-118.7491141},{36.5722990,-118.7489850},{36.5721979,-118.7489279},{36.5721218,-118.7488589},{36.5720617,-118.7487703},{36.5719795,-118.7486982},{36.5718914,-118.7486273},{36.5717958,-118.7485461},{36.5717038,-118.7485147},{36.5716034,-118.7485159},{36.5715068,-118.7485257},{36.5714038,-118.7485189},{36.5713106,-118.7485585},{36.5712186,-118.7485429},{36.5711849,-118.7486574},{36.5710955,-118.7487077},{36.5710107,-118.7487605},{36.5708976,-118.7487935},{36.5707686,-118.7489245},{36.5706794,-118.7490005},{36.5706062,-118.7490734},{36.5705257,-118.7491351},{36.5705202,-118.7492726},{36.5705096,-118.7493899},{36.5704939,-118.7495175},{36.5704649,-118.7496402},{36.5704083,-118.7497344},{36.5703271,-118.7498038},{36.5702703,-118.7499010},{36.5701903,-118.7499889},{36.5701251,-118.7500728},{36.5700220,-118.7500701},{36.5699076,-118.7500702},{36.5698073,-118.7500435},{36.5697129,-118.7500335},{36.5696220,-118.7500022},{36.5695308,-118.7499569},{36.5694415,-118.7499026},{36.5693394,-118.7498660},{36.5692449,-118.7498652},{36.5691537,-118.7498619},{36.5690605,-118.7498720},{36.5689693,-118.7498870},{36.5688772,-118.7498947},{36.5687738,-118.7498473},{36.5687002,-118.7497794},{36.5685789,-118.7498151},{36.5684926,-118.7499004},{36.5684085,-118.7499524},{36.5683250,-118.7500174},{36.5682369,-118.7500445},{36.5681337,-118.7500044},{36.5680819,-118.7499101},{36.5680250,-118.7497863},{36.5679873,-118.7496680},{36.5679681,-118.7495474},{36.5679285,-118.7494323},{36.5678799,-118.7493361},{36.5678598,-118.7492227},{36.5678116,-118.7490965},{36.5677706,-118.7489970},{36.5677272,-118.7488766},{36.5677099,-118.7487650},{36.5676802,-118.7486518},{36.5676320,-118.7485482},{36.5676260,-118.7484342},{36.5675303,-118.7484118},{36.5674194,-118.7483812},{36.5673285,-118.7483721},{36.5672336,-118.7484063},{36.5671822,-118.7485049},{36.5670867,-118.7485125},{36.5668932,-118.7487204},{36.5667978,-118.7486294},{36.5667269,-118.7485409},{36.5666841,-118.7484309},{36.5666125,-118.7483579},{36.5665228,-118.7483388},{36.5663432,-118.7482641},{36.5662807,-118.7483715},{36.5662635,-118.7484825},{36.5662296,-118.7486192},{36.5661934,-118.7487382},{36.5661205,-118.7488337},{36.5660748,-118.7489455},{36.5660772,-118.7490682},{36.5660232,-118.7491803},{36.5659572,-118.7492887},{36.5658903,-118.7493728},{36.5658066,-118.7494465},{36.5657087,-118.7495079},{36.5656216,-118.7495626},{36.5655308,-118.7495949},{36.5656225,-118.7496269},{36.5656659,-118.7497675},{36.5656537,-118.7498845},{36.5656473,-118.7500870},{36.5656643,-118.7502077},{36.5656420,-118.7503265},{36.5656420,-118.7503265},{36.5655508,-118.7504519},{36.5655570,-118.7505697},{36.5655721,-118.7506877},{36.5655661,-118.7508078},{36.5655569,-118.7509217},{36.5655583,-118.7510433},{36.5655138,-118.7511506},{36.5654666,-118.7512700},{36.5654993,-118.7513873},{36.5655445,-118.7514967},{36.5655133,-118.7516038},{36.5655425,-118.7517116},{36.5656339,-118.7517177},{36.5654567,-118.7517753},{36.5653824,-118.7518398},{36.5653274,-118.7519592},{36.5653021,-118.7520755},{36.5652027,-118.7521891},{36.5651677,-118.7523016},{36.5651623,-118.7524237},{36.5650833,-118.7524911},{36.5649962,-118.7525483},{36.5649468,-118.7526457},{36.5648542,-118.7527087},{36.5647616,-118.7526861},{36.5646540,-118.7527066},{36.5645221,-118.7527441},{36.5644240,-118.7527871},{36.5643477,-118.7528640},{36.5642776,-118.7529396},{36.5642107,-118.7530147},{36.5641173,-118.7530507},{36.5640271,-118.7530474},{36.5639399,-118.7530746},{36.5638787,-118.7531635},{36.5637741,-118.7531642},{36.5636803,-118.7531737},{36.5635867,-118.7531735},{36.5635526,-118.7530693},{36.5634980,-118.7529657},{36.5634116,-118.7529143},{36.5633656,-118.7528085},{36.5633421,-118.7526651},{36.5632831,-118.7525733},{36.5631953,-118.7526148},{36.5631067,-118.7526373},{36.5630133,-118.7526657},{36.5629181,-118.7526827},{36.5628082,-118.7527061},{36.5627127,-118.7527161},{36.5626214,-118.7527249},{36.5625256,-118.7527124},{36.5624273,-118.7527287},{36.5623302,-118.7526870},{36.5622124,-118.7527145},{36.5621049,-118.7527541},{36.5620062,-118.7526930},{36.5619300,-118.7525736},{36.5618601,-118.7524340},{36.5617968,-118.7523373},{36.5617095,-118.7522742},{36.5616272,-118.7522253},{36.5615576,-118.7521487},{36.5614931,-118.7520590},{36.5614549,-118.7519287},{36.5613783,-118.7518558},{36.5612974,-118.7517757},{36.5612181,-118.7517155},{36.5611001,-118.7517205},{36.5610102,-118.7517293},{36.5609289,-118.7517881},{36.5608426,-118.7518396},{36.5608002,-118.7517219},{36.5607257,-118.7516461},{36.5606436,-118.7515946},{36.5605165,-118.7515759},{36.5604240,-118.7515769},{36.5603322,-118.7515210},{36.5602592,-118.7514530},{36.5601704,-118.7514201},{36.5600812,-118.7513037},{36.5600467,-118.7511942},{36.5599850,-118.7511057},{36.5599752,-118.7509911},{36.5599951,-118.7508766},{36.5599105,-118.7507907},{36.5598193,-118.7507691},{36.5597799,-118.7508763},{36.5598157,-118.7509913},{36.5598965,-118.7510474},{36.5599546,-118.7511450},{36.5599374,-118.7512713},{36.5599036,-118.7513930},{36.5599047,-118.7515511},{36.5598631,-118.7516689},{36.5598155,-118.7518323},{36.5597623,-118.7519317},{36.5596990,-118.7521058},{36.5596363,-118.7522354},{36.5595848,-118.7523633},{36.5596050,-118.7524987},{36.5596405,-118.7526240},{36.5596777,-118.7527329},{36.5596917,-118.7528784},{36.5597512,-118.7529626},{36.5598309,-118.7530168},{36.5599227,-118.7530516},{36.5599695,-118.7531557},{36.5599607,-118.7532721},{36.5599592,-118.7533854},{36.5599353,-118.7535111},{36.5598946,-118.7536438},{36.5597790,-118.7537044},{36.5597127,-118.7538118},{36.5596111,-118.7538978},{36.5595284,-118.7539546},{36.5594165,-118.7539902},{36.5593110,-118.7540103},{36.5592141,-118.7540086},{36.5591374,-118.7540683},{36.5590771,-118.7541735},{36.5589131,-118.7544396},{36.5588438,-118.7545449},{36.5587373,-118.7545773},{36.5586891,-118.7544697},{36.5586185,-118.7543842},{36.5585596,-118.7542321},{36.5585375,-118.7541142},{36.5584536,-118.7541625},{36.5584333,-118.7542841},{36.5585619,-118.7544624},{36.5586426,-118.7545624},{36.5587359,-118.7545502},{36.5588137,-118.7544850},{36.5588708,-118.7543870},{36.5589354,-118.7543061},{36.5590314,-118.7543262},{36.5591272,-118.7543175},{36.5592373,-118.7543183},{36.5593260,-118.7542851},{36.5593989,-118.7542000},{36.5594567,-118.7541141},{36.5595477,-118.7540578},{36.5596301,-118.7539878},{36.5596889,-118.7538960},{36.5597212,-118.7537904},{36.5597760,-118.7536724},{36.5598582,-118.7535974},{36.5598895,-118.7534632},{36.5598438,-118.7533636},{36.5598660,-118.7532476},{36.5598253,-118.7531445},{36.5598051,-118.7530254},{36.5597957,-118.7529101},{36.5597141,-118.7528316},{36.5596519,-118.7527387},{36.5595971,-118.7526216},{36.5596064,-118.7525101},{36.5595653,-118.7524074},{36.5595651,-118.7522922},{36.5597391,-118.7521097},{36.5597455,-118.7519979},{36.5596965,-118.7518914},{36.5596914,-118.7517750},{36.5597509,-118.7516582},{36.5597600,-118.7515301},{36.5597640,-118.7513604},{36.5597192,-118.7512535},{36.5597175,-118.7511330},{36.5597706,-118.7510045},{36.5597229,-118.7508319},{36.5597838,-118.7507176},{36.5598537,-118.7506004},{36.5597883,-118.7505074},{36.5597092,-118.7504454},{36.5596240,-118.7503692},{36.5595728,-118.7502739},{36.5594719,-118.7502917},{36.5593886,-118.7503517},{36.5593029,-118.7504122},{36.5592523,-118.7505244},{36.5592059,-118.7506322},{36.5592492,-118.7507840},{36.5592607,-118.7508990},{36.5591898,-118.7509855},{36.5590903,-118.7509812},{36.5589969,-118.7509469},{36.5589526,-118.7508436},{36.5588647,-118.7507565},{36.5588480,-118.7506262},{36.5588747,-118.7505153},{36.5588827,-118.7503772},{36.5588966,-118.7502651},{36.5588857,-118.7501435},{36.5588312,-118.7500456},{36.5588510,-118.7499327},{36.5587732,-118.7498739},{36.5586946,-118.7498095},{36.5586164,-118.7497364},{36.5585374,-118.7496271},{36.5585121,-118.7495070},{36.5585277,-118.7493638},{36.5585756,-118.7492422},{36.5586549,-118.7491613},{36.5587144,-118.7490680},{36.5587770,-118.7489709},{36.5586946,-118.7490812},{36.5586362,-118.7491663},{36.5585161,-118.7490880},{36.5584115,-118.7490476},{36.5583118,-118.7490047},{36.5582419,-118.7489303},{36.5581384,-118.7488349},{36.5580521,-118.7487776},{36.5579522,-118.7487746},{36.5578578,-118.7487689},{36.5576820,-118.7487789},{36.5576054,-118.7487115},{36.5574993,-118.7486564},{36.5574017,-118.7486476},{36.5573048,-118.7486550},{36.5571985,-118.7486277},{36.5571087,-118.7485888},{36.5570167,-118.7486083},{36.5569276,-118.7486349},{36.5568313,-118.7486090},{36.5567333,-118.7485550},{36.5566806,-118.7484594},{36.5565792,-118.7484985},{36.5564889,-118.7484986},{36.5563881,-118.7484624},{36.5562853,-118.7484807},{36.5561915,-118.7484749},{36.5560853,-118.7485359},{36.5559828,-118.7485936},{36.5558890,-118.7486566},{36.5557908,-118.7486897},{36.5556975,-118.7486797},{36.5555910,-118.7487059},{36.5554967,-118.7486925},{36.5553848,-118.7487110},{36.5552787,-118.7487658},{36.5551814,-118.7488200},{36.5551814,-118.7488200},{36.5549562,-118.7488629},{36.5548656,-118.7489034},{36.5548250,-118.7489345},{36.5548031,-118.7489399}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        mLocation = intent.getStringExtra("LOCATION");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Map Saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng locationPos = new LatLng(36.5564018,-118.7437303);
        PolylineOptions polylineOptions = new PolylineOptions();
        ArrayList<LatLng> points = new ArrayList<LatLng>();
        for (int i = 0; i < contourPath.length; i++)
        {
            points.add(new LatLng(contourPath[i][0],contourPath[i][1]));
        }
        polylineOptions.color(Color.GRAY);
        polylineOptions.addAll(points);
        googleMap.addPolyline(polylineOptions);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(locationPos)      // Sets the center of the map to Mountain View
                .zoom(15)                   // Sets the zoom
                .build();                   // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    @Override
    public void onFragmentReady(Fragment fragment){
        if (fragment instanceof TrailViewFragment){
            MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.trailMap);
            mapFragment.getMapAsync(this);
        }

    }

}
