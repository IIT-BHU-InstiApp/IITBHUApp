package com.example.anant.iitbhuvaranasi;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
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

/*
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
*/
//import org.kashiyatra.ky18.R;
//import org.kashiyatra.ky18.utils.PermissionUtils;

public class IITBHUMapFragment extends Fragment implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private static final String TAG = com.google.android.gms.maps.MapFragment.class.getSimpleName();
    final LatLng C_V_RAMAN = new LatLng(25.265912276191887, 82.9862916469574);
    final LatLng MORVI = new LatLng(25.265077860079725, 82.98618972301483);
    final LatLng DHANRAJGIRI = new LatLng(25.26392325158243, 82.98608243465424);
    final LatLng RAMANUJAN = new LatLng(25.263578807425237, 82.9848861694336);
    final LatLng ASN_BOSE = new LatLng(25.263442970024144, 82.98393130302429);
    final LatLng VISVESWARAYYA = new LatLng(25.262744375275034, 82.98392057418823);
    final LatLng GANDHI_SMRITI_MAHILA = new LatLng(25.260580646589776, 82.98429071903229);
    final LatLng RAJPUTANA = new LatLng(25.262385373632068, 82.98617899417877);
    final LatLng ARYABHATTA = new LatLng(25.26464124471217, 82.98425316810608);
    final LatLng RAJPUTANA_GROUND = new LatLng(25.26219131824797, 82.98728942871094);
    final LatLng LIMBDI = new LatLng(25.261301085198394, 82.9863291978836);
    final LatLng SC_DE = new LatLng(25.260148866519867, 82.98673689365387);
    final LatLng VIVEKANANDA = new LatLng(25.259178568626197, 82.9871016740799);
    final LatLng VISHVAKARMA = new LatLng(25.25782984167578, 82.98563718795776);
    final LatLng IIT_GUEST_HOUSE = new LatLng(25.259610352146275, 82.98519730567932);
    final LatLng GRT_APARTMENTS = new LatLng(25.258513910094607, 82.9848164319992);
    final LatLng SALUJA = new LatLng(25.270190994281233, 82.98426389694214);
    final LatLng LIMBDI_GIRLS = new LatLng(25.260766214518878, 82.98562176525593);

    final LatLng REGISTRATION_DESK = new LatLng(25.261651600080054, 82.98654980957508);
    final LatLng SWATANTRATA_BHAVAN = new LatLng(25.26073589298122, 82.99452066421509);

    //Lecture Theatres
    final LatLng G11 = new LatLng(25.26123559095607, 82.99245402216911);
    final LatLng G14 = new LatLng(25.26172800976422, 82.99058854579926);
    final LatLng LT3 = new LatLng(25.25884866557616, 82.99267530441284);
    final LatLng LT1 = new LatLng(25.260275004676558, 82.99107670783997);

    final LatLng ADV_GROUNDS_EVENT = new LatLng(25.258717674410665, 82.99015402793884);
    final LatLng ADV_GROUNDS_PRONITE = new LatLng(25.258281036171866, 82.99061268568039);
    final LatLng ATM_HG1 = new LatLng(25.2622883459788, 82.9817345738411);
    final LatLng ATM_HG2 = new LatLng(25.26173468044865, 82.98157699406147);
    final LatLng ATM_VT = new LatLng(25.26537378738049, 82.98967659473419);
    final LatLng ATM_eCorner = new LatLng(25.26386261007635, 82.9949739575386);


    //Departments
    final LatLng ARCHITECHTURE = new LatLng(25.261633, 82.991648);
    final LatLng CERAMIC = new LatLng(25.259783, 82.992806);
    final LatLng CHEMICAL = new LatLng(25.259578, 82.993618);
    final LatLng CIVIL = new LatLng(25.263186, 82.991962);
    final LatLng CSE = new LatLng(25.262514, 82.993421);
    final LatLng ELECTRICAL = new LatLng(25.261367, 82.992037);
    final LatLng ELECTRONICS = new LatLng(25.262856, 82.990626);
    final LatLng MECHANICAL = new LatLng(25.261777, 82.991742);
    final LatLng METALLURGICAL = new LatLng(25.268978, 82.992557);
    final LatLng MINING = new LatLng(25.269711, 82.992847);
    final LatLng PHARMACEUTICAL = new LatLng(25.258771, 82.993556);

    final LatLng CHEMISTRY = new LatLng(25.261048, 82.991786);
    final LatLng MATHEMATICAL_SCIENCES = new LatLng(25.261873, 82.993501);
    final LatLng PHYSICS = new LatLng(25.259545, 82.992941);

    final LatLng BIOCHEMICAL = new LatLng(25.258507, 82.994231);
    final LatLng BIOMEDICAL = new LatLng(25.261720, 82.994487);
    final LatLng MATERIALS_SCIENCE = new LatLng(25.259609, 82.991511);

    final LatLng HUMANISTIC_STUDIES = new LatLng(25.261603, 82.990653);

    FloatingActionMenu filterFAM;
    FloatingActionButton filterHostel, filterAtm, filterDepartment, filterLT, filterRegDesk;

    BitmapDescriptor registrationDeskMarker;
    BitmapDescriptor hostelMarker;
    BitmapDescriptor departmentMarker;
    BitmapDescriptor ltMarker;
    BitmapDescriptor atmMarker;

    private boolean mPermissionDenied = false;
    private GoogleMap mMap;

    public IITBHUMapFragment() {
        // Required empty public constructor
    }

    public static com.google.android.gms.maps.MapFragment newInstance() {
        com.google.android.gms.maps.MapFragment fragment = new com.google.android.gms.maps.MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        MapsInitializer.initialize(getContext());

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager()
                        .findFragmentById(R.id.map);


        registrationDeskMarker = BitmapDescriptorFactory.fromResource(R.drawable.baseline_place_black_24);
        hostelMarker = BitmapDescriptorFactory.fromResource(R.drawable.baseline_place_black_24);
        departmentMarker = BitmapDescriptorFactory.fromResource(R.drawable.baseline_menu_book_black_24);
        ltMarker = BitmapDescriptorFactory.fromResource(R.drawable.baseline_place_black_24);
        atmMarker = BitmapDescriptorFactory.fromResource(R.drawable.baseline_atm_black_24);

        mapFragment.getMapAsync(this);

        filterFAM = view.findViewById(R.id.filter_menu);
        filterAtm = view.findViewById(R.id.filter_atm);
        filterDepartment = view.findViewById(R.id.filter_department);
        filterHostel = view.findViewById(R.id.filter_hostel);
        filterLT = view.findViewById(R.id.filter_pronite);
        //filterRegDesk = view.findViewById(R.id.filter_reg_desk);

        createCustomAnimation();

        filterFAM.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filterFAM.isOpened()) {
                    markRegistrationDesk();
                    markHostels();
                    markDepartments();
                    markAtms();
                    markLT();
                }
                filterFAM.toggle(true);
            }
        });
        filterAtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                markAtms();
                filterFAM.close(true);
            }
        });
        /*filterRegDesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                markRegistrationDesk();
                filterFAM.close(true);
            }
        });*/
        filterLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                markLT();
                filterFAM.close(true);
            }
        });
        filterHostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                markHostels();
                filterFAM.close(true);
            }
        });
        filterDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
                markDepartments();
                filterFAM.close(true);
            }
        });
        return view;
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready for use.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getContext(), R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();

        markRegistrationDesk();
        markHostels();
        markDepartments();
        markAtms();
        markLT();

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(REGISTRATION_DESK)
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
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing
            // TODO : Error is coming from this line(closed)
            PermissionUtils.requestPermission(/*(AppCompatActivity)*/this.getActivity() , LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(getActivity(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
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
        //PermissionUtils.PermissionDeniedDialog
        //      .newInstance(true).show((new android.app.FragmentManager())getChildFragmentManager(), "dialog");
        Toast.makeText(this.getContext(),"Permission Missing",Toast.LENGTH_LONG).show();
    }

    private void markRegistrationDesk() {
        mMap.addMarker(new MarkerOptions().position(REGISTRATION_DESK).title("KY Registration Desk").icon(registrationDeskMarker));
    }

    private void markDepartments() {
        mMap.addMarker(new MarkerOptions().position(ARCHITECHTURE).title("Department of Architecture, Planning and Design").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(CERAMIC).title("Department of Ceramic Engineering").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(CHEMICAL).title("Department of Chemical Engineering & Technology").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(CIVIL).title("Department of Civil Engineering").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(CSE).title("Department of Computer Science and Engineering").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(ELECTRICAL).title("Department of Electrical Engineering").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(ELECTRONICS).title("Department of Electronics Engineering").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(MECHANICAL).title("Department of Mechanical Engineering").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(METALLURGICAL).title("Department of Metallurgical Engineering").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(MINING).title("Department of Mining Engineering").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(PHARMACEUTICAL).title("Department of Pharmaceutical Engineering and Technology").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(CHEMISTRY).title("Department of Chemistry").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(MATHEMATICAL_SCIENCES).title("Department of Mathematical Sciences").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(PHYSICS).title("Department of Physics").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(BIOCHEMICAL).title("School of Biochemical Engineering").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(BIOMEDICAL).title("School of Biomedical Engineering").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(MATERIALS_SCIENCE).title("School of Materials Science and Technology").icon(departmentMarker));
        mMap.addMarker(new MarkerOptions().position(HUMANISTIC_STUDIES).title("Department of Humanistic Studies").icon(departmentMarker));
    }

    private void markHostels() {
        mMap.addMarker(new MarkerOptions().position(C_V_RAMAN).title("C V Raman Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(ARYABHATTA).title("Aryabhatta Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(MORVI).title("Morvi Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(DHANRAJGIRI).title("Dhanrajgiri Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(RAJPUTANA).title("Rajputana Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(RAMANUJAN).title("Ramanujan Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(ASN_BOSE).title("ASN Bose Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(VISVESWARAYYA).title("Visveswarayya Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(GANDHI_SMRITI_MAHILA).title("Gandhi Smriti Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(LIMBDI).title("Limbdi Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(SC_DE).title("SC De Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(VIVEKANANDA).title("Vivekananda Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(VISHVAKARMA).title("Vishvakarma Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(GANDHI_SMRITI_MAHILA).title("Gandhi Smriti Mahila Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(IIT_GUEST_HOUSE).title("IIT Guest House").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(SALUJA).title("Saluja Girls Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(LIMBDI_GIRLS).title("Limbdi Girls Hostel").icon(hostelMarker));
        mMap.addMarker(new MarkerOptions().position(GRT_APARTMENTS).title("GRT Apartments").icon(hostelMarker));
    }

    private void markAtms() {
        mMap.addMarker(new MarkerOptions().position(ATM_eCorner).title("SBI eCorner").icon(atmMarker));
        mMap.addMarker(new MarkerOptions().position(ATM_HG1).title("ICICI ATM").icon(atmMarker));
        mMap.addMarker(new MarkerOptions().position(ATM_HG2).title("SBI ATM").icon(atmMarker));
        mMap.addMarker(new MarkerOptions().position(ATM_VT).title("Bank of Baroda ATM").icon(atmMarker));
    }

    private void markLT() {
        mMap.addMarker(new MarkerOptions().position(LT1).title("Lecture Theater 1").icon(ltMarker));
        mMap.addMarker(new MarkerOptions().position(LT3).title("Lecture Theater 3").icon(ltMarker));
        mMap.addMarker(new MarkerOptions().position(G11).title("G-11").icon(ltMarker));
        mMap.addMarker(new MarkerOptions().position(G14).title("G-14").icon(ltMarker));
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

}
