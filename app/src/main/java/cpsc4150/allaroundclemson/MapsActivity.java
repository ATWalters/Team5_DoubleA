package cpsc4150.allaroundclemson;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private final int REQUEST_LOCATION_PERMISSIONS = 0;

    private GoogleMap mMap;

    private FusedLocationProviderClient mClient;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private float mZoomLevel = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Creating location request
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        //Create location callback
        mLocationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult){
                if(locationResult != null){
                    for(Location location : locationResult.getLocations()){
                        updateMap(location);
                    }
                }
            }
        };

        mClient = LocationServices.getFusedLocationProviderClient(this);
    }

    private void updateMap(Location location){
        //Get user's current location
        LatLng myLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        //Place a marker at the current location
        MarkerOptions myMarker = new MarkerOptions()
                .title("You are here!")
                .position(myLatLng);

        //Remove previous marker
        mMap.clear();

        //Add new marker
        mMap.addMarker(myMarker);

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(myLatLng, mZoomLevel);
        mMap.moveCamera(update);

    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Used in making sure the GoogleMap was working correctly before going ahead with
        //adding all of our functionality

        // Add a marker in Clemson and move the camera
        //LatLng clemson = new LatLng(34.6793, -82.8351);
        //mMap.addMarker(new MarkerOptions().position(clemson).title("Marker in Clemson"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(clemson, 19));

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener(){
            @Override
            public void onCameraMove(){
                CameraPosition cameraPosition = mMap.getCameraPosition();
                mZoomLevel = cameraPosition.zoom;
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mClient.removeLocationUpdates(mLocationCallback);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onResume() {
        super.onResume();

        if (hasLocationPermission()) {
            mClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);
        }
    }

    private boolean hasLocationPermission() {

        // Request fine location permission if not already granted
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this ,
                    new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                    REQUEST_LOCATION_PERMISSIONS);

            return false;
        }

        return true;
    }
}