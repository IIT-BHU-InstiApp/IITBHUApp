package com.example.anant.iitbhuvaranasi;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

import static com.example.anant.iitbhuvaranasi.Feedfragment_notifcation_Activity.location2345;


public class IITBHUMapActivity extends AppCompatActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

    //Nested class used for storing map data
    private class Place {
        private String name;
        private LatLng point;

        Place(String nameOfPlace, LatLng latLngOfPlace) {
            name = nameOfPlace;
            point = latLngOfPlace;
        }

        String getName() {
            return name;
        }

        LatLng getPoint() {
            return point;
        }
    }

    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int LOCATION_SETTINGS_REQUEST = 1;

    private static final String TAG = com.google.android.gms.maps.MapFragment.class.getSimpleName();

    final Map<String, Place> hostelLocations = new HashMap<String, Place>() {{
        put("C_V_RAMAN", new Place("C V Raman Hostel", new LatLng(25.265912276191887, 82.9862916469574)));
        put("MORVI", new Place("Morvi Hostel", new LatLng(25.265077860079725, 82.98618972301483)));
        put("DHANRAJGIRI", new Place("Dhanrajgiri Hostel", new LatLng(25.26392325158243, 82.98608243465424)));
        put("RAMANUJAN", new Place("Ramanujan Hostel", new LatLng(25.263578807425237, 82.9848861694336)));
        put("ASN_BOSE", new Place("ASN Bose Hostel", new LatLng(25.263442970024144, 82.98393130302429)));
        put("VISVESWARAYYA", new Place("Vishveshwarya Hostel", new LatLng(25.262744375275034, 82.98392057418823)));
        put("GANDHI_SMRITI_MAHILA", new Place("Gandhi Smriti Hostel", new LatLng(25.260580646589776, 82.98429071903229)));
        put("RAJPUTANA", new Place("Rajputana Hostel", new LatLng(25.262385373632068, 82.98617899417877)));
        put("ARYABHATTA", new Place("Aryabhatta Hostel", new LatLng(25.26464124471217, 82.98425316810608)));
        put("LIMBDI", new Place("Limbdi Hostel", new LatLng(25.261301085198394, 82.9863291978836)));
        put("SC_DE", new Place("SC Dey Hostel", new LatLng(25.260148866519867, 82.98673689365387)));
        put("VIVEKANANDA", new Place("Vivekananda Hostel", new LatLng(25.259178568626197, 82.9871016740799)));
        put("VISHVAKARMA", new Place("Vishvakarma Hostel", new LatLng(25.25782984167578, 82.98563718795776)));
        put("IIT_GUEST_HOUSE", new Place("IIT Guest House", new LatLng(25.259610352146275, 82.98519730567932)));
        put("GRT_APARTMENTS", new Place("GRT Apartments", new LatLng(25.258513910094607, 82.9848164319992)));
        put("SALUJA", new Place("Saluja Hostel", new LatLng(25.270190994281233, 82.98426389694214)));
        put("LIMBDI_GIRLS", new Place("Limbdi Girls Hostel", new LatLng(25.260766214518878, 82.98562176525593)));
        put("IIT_GIRLS", new Place("IIT Girls Hostel", new LatLng(25.261187, 82.983791)));
    }};

    final Map<String, Place> departmentLocations = new HashMap<String, Place>() {{
        //Departments
        put("ARCHITECHTURE", new Place("Department of Architecture, Planning and Design", new LatLng(25.261633, 82.991648)));
        put("CERAMIC", new Place("Department of Ceramic Engineering", new LatLng(25.259783, 82.992806)));
        put("CHEMICAL", new Place("Department of Chemical Engineering & Technology", new LatLng(25.259578, 82.993618)));
        put("CIVIL", new Place("Department of Civil Engineering", new LatLng(25.263186, 82.991962)));
        put("CSE", new Place("Department of Computer Science and Engineering", new LatLng(25.262514, 82.993421)));
        put("ELECTRICAL", new Place("Department of Electrical Engineering", new LatLng(25.261367, 82.992037)));
        put("ELECTRONICS", new Place("Department of Electronics Engineering", new LatLng(25.262856, 82.990626)));
        put("MECHANICAL", new Place("Department of Mechanical Engineering", new LatLng(25.261777, 82.991742)));
        put("METALLURGICAL", new Place("Department of Metallurgical Engineering", new LatLng(25.268978, 82.992557)));
        put("MINING", new Place("Department of Mining Engineering", new LatLng(25.269711, 82.992847)));
        put("PHARMACEUTICAL", new Place("Department of Pharmaceutical Engineering and Technology", new LatLng(25.258771, 82.993556)));
        put("CHEMISTRY", new Place("Department of Chemistry", new LatLng(25.261048, 82.991786)));
        put("MATHEMATICAL_SCIENCES", new Place("Department of Mathematical Sciences", new LatLng(25.261873, 82.993501)));
        put("PHYSICS", new Place("Department of Physics", new LatLng(25.259545, 82.992941)));
        put("BIOCHEMICAL", new Place("School of Biochemical Engineering", new LatLng(25.258507, 82.994231)));
        put("BIOMEDICAL", new Place("School of Biomedical Engineering", new LatLng(25.261720, 82.994487)));
        put("MATERIALS_SCIENCE", new Place("School of Materials Science and Technology", new LatLng(25.259609, 82.991511)));
        put("HUMANISTIC_STUDIES", new Place("Department of Humanistic Studies", new LatLng(25.261603, 82.990653)));
    }};

    final Map<String, Place> ltLocations = new HashMap<String, Place>() {{
        //Lecture Theatres
        put("G11", new Place("G-11", new LatLng(25.26123559095607, 82.99245402216911)));
        put("G14", new Place("G-14", new LatLng(25.26172800976422, 82.99058854579926)));
        put("LT3", new Place("Lecture Theatre 3", new LatLng(25.25884866557616, 82.99267530441284)));
        put("LT1", new Place("Lecture Theatre 1", new LatLng(25.260275004676558, 82.99107670783997)));
    }};

    final Map<String, Place> atmLocations = new HashMap<String, Place>() {{
        put("ATM_HG1", new Place("ICICI ATM", new LatLng(25.2622883459788, 82.9817345738411)));
        put("ATM_HG2", new Place("SBI ATM", new LatLng(25.26173468044865, 82.98157699406147)));
        put("ATM_VT", new Place("Bank of Baroda ATM", new LatLng(25.26537378738049, 82.98967659473419)));
        put("ATM_eCorner", new Place("SBI eCorner", new LatLng(25.26386261007635, 82.9949739575386)));
    }};

    final Map<String, Place> templeLocations = new HashMap<String, Place>() {{
        put("VT", new Place("Vishwanath Temple", new LatLng(25.266083, 82.987908)));
    }};

    final Map<String, Place> cafeLocations = new HashMap<String, Place>() {{
        put("DG", new Place("DG Corner", new LatLng(25.263215, 82.986463)));
        put("LC", new Place("Limbdi Corner", new LatLng(25.260667, 82.986883)));
        put("CCD", new Place("Café Coffee Day", new LatLng(25.258257, 82.986542)));
    }};

    final Map<String, Place> hospitalLocations = new HashMap<String, Place>() {{
        put("SUNDERLAL_HOSPITAL", new Place("Sir Sunderlal Hospital", new LatLng(25.276436, 82.999643)));
        put("HEALTH_CENTER", new Place("Student's Health Center", new LatLng(25.270028, 82.988653)));
    }};

    final Map<String, Place> petrolLocations = new HashMap<String, Place>() {{
        put("BHU_PETROL", new Place("BHU Petrol Pump", new LatLng(25.278200, 82.996613)));
    }};

    final Map<String, Place> groundLocations = new HashMap<String, Place>() {{
        put("RAJPUTANA_GROUND", new Place("Rajputana Grounds", new LatLng(25.262191, 82.987291)));
        put("GYMKHANAA_GROUND", new Place("Gymkhana Grounds", new LatLng(25.260313, 82.988470)));
        put("ADV_GROUND", new Place("ADV Grounds", new LatLng(25.258717674410665, 82.99015402793884)));
    }};

    final Map<String, Place> otherLocations = new HashMap<String, Place>() {{
        put("SWATANTRATA_BHAVAN", new Place("Swatantrata Bhavan", new LatLng(25.26073589298122, 82.99452066421509)));
        put("HG", new Place("Hyderabad Gate", new LatLng(25.262944, 82.982306)));
        put("GTAC", new Place("GTAC", new LatLng(25.259717, 82.984984)));
    }};

    static Map<String,Place> used;

    private String location = null;
    String location23 = location2345;


    FloatingActionMenu filterFAM;
    FloatingActionButton filterHostel, filterOther, filterDepartment, filterLT;

    BitmapDescriptor hostelMarker;
    BitmapDescriptor departmentMarker;
    BitmapDescriptor ltMarker;
    BitmapDescriptor atmMarker;
    BitmapDescriptor templeMarker;
    BitmapDescriptor cafeMarker;
    BitmapDescriptor hospitalMarker;
    BitmapDescriptor petrolMarker;
    BitmapDescriptor groundMarker;
    BitmapDescriptor otherMarker;


    private boolean mPermissionDenied;
    private GoogleMap mMap;

    public IITBHUMapActivity() {
        // Required empty public constructor
        location=location23;
    }

    public IITBHUMapActivity(String locationOfEvent) {
        location = locationOfEvent;
    }


    public static com.google.android.gms.maps.MapFragment newInstance() {
        com.google.android.gms.maps.MapFragment fragment = new com.google.android.gms.maps.MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_i_t_b_h_u_map);

        if (!isServicesOK()) {
            Toast.makeText(this,"Google Play Services version not compatible",Toast.LENGTH_LONG).show();
            finish();
        }

        enableMyLocation();

        MapsInitializer.initialize(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        hostelMarker = BitmapDescriptorFactory.fromResource(R.drawable.baseline_place_black_24);
        departmentMarker = BitmapDescriptorFactory.fromResource(R.drawable.baseline_menu_book_black_24);
        ltMarker = BitmapDescriptorFactory.fromResource(R.drawable.baseline_place_black_24);
        atmMarker = BitmapDescriptorFactory.fromResource(R.drawable.baseline_atm_black_24);
        templeMarker = BitmapDescriptorFactory.fromResource(R.drawable.temple_icon);
        cafeMarker = BitmapDescriptorFactory.fromResource(R.drawable.sharp_local_cafe_black_24);
        hospitalMarker = BitmapDescriptorFactory.fromResource(R.drawable.sharp_local_hospital_black_24);
        petrolMarker = BitmapDescriptorFactory.fromResource(R.drawable.baseline_local_gas_station_black_24);
        groundMarker = BitmapDescriptorFactory.fromResource(R.drawable.baseline_place_black_24);
        otherMarker = BitmapDescriptorFactory.fromResource(R.drawable.baseline_place_black_24);

        mapFragment.getMapAsync(this);

        filterFAM = findViewById(R.id.filter_menu);
        filterOther = findViewById(R.id.filter_atm);
        filterDepartment = findViewById(R.id.filter_department);
        filterHostel = findViewById(R.id.filter_hostel);
        filterLT = findViewById(R.id.filter_pronite);

        createCustomAnimation();


        filterFAM.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filterFAM.isOpened()) {
                    markHostels();
                    markDepartments();
                    markLT();
                    markAtms();
                    markTemples();
                    markCafe();
                    markHospitals();
                    markPetrol();
                    markGround();
                    markOther();
                }
                filterFAM.toggle(true);
            }
        });
        filterOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                displayAlertDialog("Others",view);
                filterFAM.close(true);
            }
        });
        filterLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                displayAlertDialog("Lecture Halls",view);
                filterFAM.close(true);
            }
        });
        filterHostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                displayAlertDialog("Hostels",view);
                filterFAM.close(true);
            }
        });
        filterDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                displayAlertDialog("Departments",view);
                filterFAM.close(true);
            }
        });
    }

    private static final int ERROR_DIALOG_REQUEST = 9001;

    //Checks play services are working
    public boolean isServicesOK(){
        boolean isAvailable = false;
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            isAvailable = true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return isAvailable;
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready for use.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng targetLocation = cafeLocations.get("LC").getPoint();

        mMap = googleMap;
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        //if map could not load,control would fallback to HomeActivity
        if (mMap == null)
        {
            Toast.makeText(this,"There was error in loading the map",Toast.LENGTH_LONG).show();
            finish();
        }

        enableMyLocationButton();

        markHostels();
        markDepartments();
        markLT();
        markAtms();
        markTemples();
        markCafe();
        markHospitals();
        markPetrol();
        markGround();
        markOther();

        /*
            if backend provides name of place as " " or "Not_Given"
            then only map will be opened without any InfoWindow
         */
        location = location2345;


        if (location == null || location.trim().isEmpty() || location.trim().equalsIgnoreCase("nolocation")
                || location.trim().equalsIgnoreCase("not_given") || location.trim().equalsIgnoreCase("no_location")) {
        } else {
            if (hostelLocations.containsKey(location)) {
                mMap.addMarker(new MarkerOptions().position(hostelLocations.get(location).getPoint()).title(hostelLocations.get(location).getName())
                        .icon(hostelMarker)).showInfoWindow();
                targetLocation = hostelLocations.get(location).getPoint();
            } else if (departmentLocations.containsKey(location)) {
                mMap.addMarker(new MarkerOptions().position(departmentLocations.get(location).getPoint()).title(departmentLocations.get(location).getName())
                        .icon(departmentMarker)).showInfoWindow();
                targetLocation = departmentLocations.get(location).getPoint();
            } else if (ltLocations.containsKey(location)) {
                mMap.addMarker(new MarkerOptions().position(ltLocations.get(location).getPoint()).title(ltLocations.get(location).getName())
                        .icon(ltMarker)).showInfoWindow();
                targetLocation = ltLocations.get(location).getPoint();
            } else if (atmLocations.containsKey(location)) {
                mMap.addMarker(new MarkerOptions().position(atmLocations.get(location).getPoint()).title(atmLocations.get(location).getName())
                        .icon(atmMarker)).showInfoWindow();
                targetLocation = atmLocations.get(location).getPoint();
            } else if (templeLocations.containsKey(location)) {
                mMap.addMarker(new MarkerOptions().position(templeLocations.get(location).getPoint()).title(templeLocations.get(location).getName())
                        .icon(templeMarker)).showInfoWindow();
                targetLocation = templeLocations.get(location).getPoint();
            } else if (cafeLocations.containsKey(location)) {
                mMap.addMarker(new MarkerOptions().position(cafeLocations.get(location).getPoint()).title(cafeLocations.get(location).getName())
                        .icon(cafeMarker)).showInfoWindow();
                targetLocation = cafeLocations.get(location).getPoint();
            } else if (hospitalLocations.containsKey(location)) {
                mMap.addMarker(new MarkerOptions().position(hospitalLocations.get(location).getPoint()).title(hospitalLocations.get(location).getName())
                        .icon(hospitalMarker)).showInfoWindow();
                targetLocation = hospitalLocations.get(location).getPoint();
            } else if (petrolLocations.containsKey(location)) {
                mMap.addMarker(new MarkerOptions().position(petrolLocations.get(location).getPoint()).title(petrolLocations.get(location).getName())
                        .icon(petrolMarker)).showInfoWindow();
                targetLocation = petrolLocations.get(location).getPoint();
            } else if (groundLocations.containsKey(location)) {
                mMap.addMarker(new MarkerOptions().position(groundLocations.get(location).getPoint()).title(groundLocations.get(location).getName())
                        .icon(groundMarker)).showInfoWindow();
                targetLocation = groundLocations.get(location).getPoint();
            } else if (otherLocations.containsKey(location)) {
                mMap.addMarker(new MarkerOptions().position(otherLocations.get(location).getPoint()).title(otherLocations.get(location).getName())
                        .icon(otherMarker)).showInfoWindow();
                targetLocation = otherLocations.get(location).getPoint();
            }
        }
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(targetLocation)
                .zoom(17)
                .tilt(67.5f)
                .bearing(0)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {

        String permissions[] = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};

        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
            && (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)) {
                // Permission is granted
                mPermissionDenied = false;
        }
        else {
            PermissionUtils.requestPermission(this ,permissions ,
                    LOCATION_PERMISSION_REQUEST_CODE, true);
        }

    }

    private void enableMyLocationButton() {
        //If permission is denied then control will not pass through this if statement
        if (!mPermissionDenied) {
            if ((ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.setOnMyLocationButtonClickListener(this);
            }
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        displayLocationSettingsRequest();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    //Displays Location Settings Request to enable GPS/other means to get location
    //gets triggered when MyLocationButton is clicked
    private void displayLocationSettingsRequest()
    {
        LocationRequest mLocationRequest = LocationRequest.create()
                                            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                            .setInterval(10*1000)
                                            .setFastestInterval(1*1000);

        LocationSettingsRequest.Builder settingsBuilder = new LocationSettingsRequest.Builder()
                                                                .addLocationRequest(mLocationRequest);
        settingsBuilder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(this)
                                                .checkLocationSettings(settingsBuilder.build());


        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response =
                            task.getResult(ApiException.class);
                } catch (ApiException ex) {
                    switch (ex.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                ResolvableApiException resolvableApiException =
                                        (ResolvableApiException) ex;
                                resolvableApiException
                                        .startResolutionForResult(IITBHUMapActivity.this,
                                                LOCATION_SETTINGS_REQUEST);
                            } catch (IntentSender.SendIntentException e) {

                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                            break;
                    }
                }
            }
        });

    }

    //Callback for the result from requesting permissions.
    //This method is invoked for every call on requestPermissions(android.app.Activity, String[], int)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})) {
            // Enable the my location layer if the permission has been granted.
            mPermissionDenied = false;
            enableMyLocationButton();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getFragmentManager(), "dialog");
    }

    private void markDepartments() {
        for (String key : departmentLocations.keySet())
            mMap.addMarker(new MarkerOptions().position(departmentLocations.get(key).getPoint()).title(departmentLocations.get(key).getName()).icon(departmentMarker));
    }

    private void markHostels() {
        for (String key : hostelLocations.keySet())
            mMap.addMarker(new MarkerOptions().position(hostelLocations.get(key).getPoint()).title(hostelLocations.get(key).getName()).icon(hostelMarker));
    }

    private void markAtms() {
        for (String key : atmLocations.keySet())
            mMap.addMarker(new MarkerOptions().position(atmLocations.get(key).getPoint()).title(atmLocations.get(key).getName()).icon(atmMarker));
    }

    private void markLT() {
        for (String key : ltLocations.keySet())
            mMap.addMarker(new MarkerOptions().position(ltLocations.get(key).getPoint()).title(ltLocations.get(key).getName()).icon(ltMarker));
    }

    private void markTemples(){
        for (String key : templeLocations.keySet())
            mMap.addMarker(new MarkerOptions().position(templeLocations.get(key).getPoint()).title(templeLocations.get(key).getName()).icon(templeMarker));
    }

    private void markPetrol(){
        for (String key : petrolLocations.keySet())
            mMap.addMarker(new MarkerOptions().position(petrolLocations.get(key).getPoint()).title(petrolLocations.get(key).getName()).icon(petrolMarker));
    }

    private void markCafe(){
        for (String key : cafeLocations.keySet())
            mMap.addMarker(new MarkerOptions().position(cafeLocations.get(key).getPoint()).title(cafeLocations.get(key).getName()).icon(cafeMarker));
    }

    private void markHospitals(){
        for (String key : hospitalLocations.keySet())
            mMap.addMarker(new MarkerOptions().position(hospitalLocations.get(key).getPoint()).title(hospitalLocations.get(key).getName()).icon(hospitalMarker));
    }

    private void markGround() {
        for (String key : groundLocations.keySet())
            mMap.addMarker(new MarkerOptions().position(groundLocations.get(key).getPoint()).title(groundLocations.get(key).getName()).icon(groundMarker));
    }

    private void markOther(){
        for (String key : otherLocations.keySet())
            mMap.addMarker(new MarkerOptions().position(otherLocations.get(key).getPoint()).title(otherLocations.get(key).getName()).icon(otherMarker));
    }

    private void createCustomAnimation() {
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator scaleOutX = ObjectAnimator.ofFloat(filterFAM.getMenuIconView(), "scaleX", 1.0f, 0.2f);
        ObjectAnimator scaleOutY = ObjectAnimator.ofFloat(filterFAM.getMenuIconView(), "scaleY", 1.0f, 0.2f);

        ObjectAnimator scaleInX = ObjectAnimator.ofFloat(filterFAM.getMenuIconView(), "scaleX", 0.2f, 1.0f);
        ObjectAnimator scaleInY = ObjectAnimator.ofFloat(filterFAM.getMenuIconView(), "scaleY", 0.2f, 1.0f);

        scaleOutX.setDuration(50);
        scaleOutY.setDuration(50);

        scaleInX.setDuration(150);
        scaleInY.setDuration(150);

        scaleInX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                filterFAM.getMenuIconView().setImageResource(filterFAM.isOpened()
                        ? R.drawable.baseline_place_white_24dp : R.drawable.baseline_place_white_24dp);
            }
        });

        set.play(scaleOutX).with(scaleOutY);
        set.play(scaleInX).with(scaleInY).after(scaleOutX);
        set.setInterpolator(new OvershootInterpolator(2));

        filterFAM.setIconToggleAnimatorSet(set);
    }

    private void displayAlertDialog(String name,View view) {
        String options[] = {"Display All","Select from List"};
        AlertDialog.Builder builder = new AlertDialog.Builder(IITBHUMapActivity.this);
        builder.setTitle("Display "+name);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0: {
                        if (name.equals("Hostels"))
                            markHostels();
                        else if (name.equals("Departments"))
                            markDepartments();
                        else if (name.equals("Lecture Halls"))
                            markLT();
                        else if (name.equals("Others")) {
                            markAtms();
                            markTemples();
                            markCafe();
                            markHospitals();
                            markPetrol();
                            markGround();
                            markOther();
                        }
                    }
                    break;
                    case 1:
                        if (name.equals("Hostels"))
                            used=hostelLocations;
                        else if (name.equals("Departments"))
                            used=departmentLocations;
                        else if (name.equals("Lecture Halls"))
                            used=ltLocations;
                        else if (name.equals("Others")) {
                            used = new HashMap<String, Place>();
                            used.clear();
                            used.putAll(atmLocations);
                            used.putAll(templeLocations);
                            used.putAll(cafeLocations);
                            used.putAll(hospitalLocations);
                            used.putAll(petrolLocations);
                            used.putAll(groundLocations);
                            used.putAll(otherLocations);
                        }
                        registerForContextMenu(view);
                        view.showContextMenu();
                    default:
                        dialog.cancel();
                }

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        this.getMenuInflater().inflate(R.menu.context_menu, menu);
        int i=0;
        for (String key : used.keySet()) {
            menu.add(1,i,i,used.get(key).getName());
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String name = item.getTitle().toString();
        for (String key : used.keySet())
        {
            if (name.equals(used.get(key).getName())){
                location2345=key;
                onMapReady(mMap);
            }
        }
        return true;
    }
}