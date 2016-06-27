package il.ac.shenkar.showshenkar.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import il.ac.shenkar.showshenkar.R;
import il.ac.shenkar.showshenkar.utils.CurrentLocationListener;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap depatmentMap;
    private final List<BitmapDescriptor> mImages = new ArrayList<BitmapDescriptor>();
    GroundOverlay mGroundOverlay;
    private GroundOverlay mGroundOverlayRotated;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.department_map);
        mapFragment.getMapAsync(this);

        //get Your Current Location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        CurrentLocationListener locationListener = new CurrentLocationListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        depatmentMap = googleMap;

        // Add a marker in Shenkar and move the camera
        LatLng Shenkar = new LatLng(-32, 121);
        depatmentMap.addMarker(new MarkerOptions().position(Shenkar).title("Shenkar"));
        depatmentMap.moveCamera(CameraUpdateFactory.newLatLng(Shenkar));
        //depatmentMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Shenkar, 4));

        //mImages.clear();
        //mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.building));

        // Add a large overlay at Newark on top of the smaller overlay.
        //mGroundOverlay = depatmentMap.addGroundOverlay(new GroundOverlayOptions()
//                .image(mImages.get(0))
//                .position(Shenkar, 800f, 600f));
//
//        depatmentMap.setContentDescription("Google Map with ground overlay.");

    }
}

