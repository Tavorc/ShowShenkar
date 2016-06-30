package il.ac.shenkar.showshenkar.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import il.ac.shenkar.showshenkar.R;
import il.ac.shenkar.showshenkar.utils.PermissionUtils;

public class MapActivity extends ShenkarActivity implements ActivityCompat.OnRequestPermissionsResultCallback,OnMapReadyCallback,GoogleMap.OnMyLocationButtonClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap depatmentMap;

    private GoogleApiClient mGoogleApiClient;

    private Location mLastLocation;

    private Long mDepartmentId;

    private static final LatLng PERNIK = new LatLng(32.09006641826965 , 34.80311807245016);

    private static final LatLng MITSHLE = new LatLng(32.09005278383782, 34.80274926871061);

    private static final LatLng INTERIOR_DESIGN = new LatLng(32.09030615669672, 34.803183311601877);

    private static final LatLng SHENKAR = new LatLng(32.09039421212218, 34.8030037432909);

    private static final LatLng ELIT = new LatLng(32.08264557800064, 34.80440218001604);

    private final List<BitmapDescriptor> mImages = new ArrayList<BitmapDescriptor>();

    private GroundOverlay mGroundOverlay;

    private GroundOverlay mGroundOverlay2;

    private int mCurrentEntry = 0;

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Set department id
        mDepartmentId = getIntent().getLongExtra("id", 0);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.department_map);
        mapFragment.getMapAsync(this);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        depatmentMap = googleMap;

        depatmentMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();

        // Register a listener to respond to clicks on GroundOverlays.
        //depatmentMap.setOnGroundOverlayClickListener(this);

        depatmentMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SHENKAR, 18));

        mImages.clear();
        mImages.add(BitmapDescriptorFactory.fromResource(R.mipmap.overview));
        mImages.add(BitmapDescriptorFactory.fromResource(R.mipmap.elit));

        // Add a large overlay at Newark on top of the smaller overlay.
        mGroundOverlay = depatmentMap.addGroundOverlay(new GroundOverlayOptions()
                .image(mImages.get(0))
                .position(SHENKAR, 190f, 150f));

        mGroundOverlay2 = depatmentMap.addGroundOverlay(new GroundOverlayOptions()
                .image(mImages.get(1))
                .position(ELIT, 190f, 150f));

        // Set marker by department id
        // ----- need to change everything to hebrew ------
        if(mDepartmentId == 5085604337418240L){ // design-MDes-curriculum
            depatmentMap.addMarker(new MarkerOptions().position(PERNIK).title("Pernik").snippet("Design MDes curriculum")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }else if(mDepartmentId == 5085604337418240L){ // admission-requirements-engineering
            depatmentMap.addMarker(new MarkerOptions().position(MITSHLE).title("Mitchell").snippet("Admission requirements engineering"));
        }else if(mDepartmentId == 5644406560391168L){ // design-visual-communication-contact
            depatmentMap.addMarker(new MarkerOptions().position(MITSHLE).title("Mitchell").snippet("Design visual communication"));
        }else if(mDepartmentId == 5649391675244544L){ // design-interior-building-and-environment-registaration
            depatmentMap.addMarker(new MarkerOptions().position(INTERIOR_DESIGN).title("Interior Design").snippet("Design interior building and environment registaration"));
        }else if(mDepartmentId == 5654313976201216L){ // design-jewelry-department
            depatmentMap.addMarker(new MarkerOptions().position(PERNIK).title("Pernik").snippet("Design jewelry department")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }else if(mDepartmentId == 5659313586569216L){ // design-textile-department
            depatmentMap.addMarker(new MarkerOptions().position(PERNIK).title("Pernik").snippet("Design textile department")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }else if(mDepartmentId == 5682617542246400L){ // multi-disciplinary-art-school
            depatmentMap.addMarker(new MarkerOptions().position(PERNIK).title("Pernik").snippet("Multi disciplinary art school")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }else if(mDepartmentId == 5707702298738688L){ // engineering-software-department
            depatmentMap.addMarker(new MarkerOptions().position(PERNIK).title("Pernik").snippet("Engineering software department")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }else if(mDepartmentId == 5722646637445120L){ // design-fashion-department
            depatmentMap.addMarker(new MarkerOptions().position(PERNIK).title("Pernik").snippet("Design fashion department")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }else{
            depatmentMap.addMarker(new MarkerOptions().position(PERNIK).title("Pernik").snippet("Pernik")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }

    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            System.out.println("Error");
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            //mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            //mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
            System.out.println("mLastLocation.getLatitude()) ---------> "+ mLastLocation.getLatitude());
            System.out.println("mLastLocation.getLongitude()) ---------> "+ mLastLocation.getLongitude());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection problem", Toast.LENGTH_LONG).show();

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

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (depatmentMap != null) {
            // Access to the location has been granted to the app.
            depatmentMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        //Toast.makeText(this, "Welcome to ShowShenkar Map", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
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
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }
}

