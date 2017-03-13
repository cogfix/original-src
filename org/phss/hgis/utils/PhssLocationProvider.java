package org.phss.hgis.utils;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import java.util.List;

public class PhssLocationProvider extends Activity {
    Handler delayed;
    private String langi;
    private String lati;
    protected Location location;
    PhssLocationListener locationListener;
    protected LocationManager locationManager;
    private OnLocationReceivedListener locationReceivedListener;
    protected Context phssContext;

    public interface OnLocationReceivedListener {
        void onLocationReceived(Location location);
    }

    private class PhssLocationListener implements LocationListener {
        private PhssLocationListener() {
        }

        public void onLocationChanged(Location location) {
            PhssLocationProvider.this.lati = String.format("%1s", new Object[]{Double.valueOf(location.getLatitude())});
            PhssLocationProvider.this.langi = String.format("%1s", new Object[]{Double.valueOf(location.getLongitude())});
            Toast.makeText(PhssLocationProvider.this.phssContext, "Successfully get location", 1).show();
            PhssLocationProvider.this.locationReceivedListener.onLocationReceived(location);
        }

        public void onStatusChanged(String s, int i, Bundle b) {
        }

        public void onProviderDisabled(String s) {
            Toast.makeText(PhssLocationProvider.this.phssContext, "GPS turned off", 1).show();
        }

        public void onProviderEnabled(String s) {
            Toast.makeText(PhssLocationProvider.this.phssContext, "GPS turned on", 1).show();
        }
    }

    public PhssLocationProvider(Context context) {
        this.lati = "";
        this.langi = "";
        this.locationReceivedListener = null;
        this.delayed = new Handler();
        this.phssContext = context;
    }

    public void setLocationMgr(LocationManager locationMgr) {
        this.locationManager = locationMgr;
    }

    public void stopLocationSearch() {
        this.locationManager.removeUpdates(this.locationListener);
    }

    public void getCurrentLocation(LocationManager locationMgr) {
        this.locationListener = new PhssLocationListener();
        this.locationManager = locationMgr;
        this.locationManager.requestLocationUpdates("gps", 0, 0.0f, this.locationListener);
    }

    public String getLati() {
        return this.lati;
    }

    public String getLongi() {
        return this.langi;
    }

    public boolean isGpsInstalled() {
        if (this.locationManager == null) {
        }
        List<String> providers = this.locationManager.getAllProviders();
        if (providers == null) {
        }
        boolean isGps = providers.contains("gps");
        if (!isGps) {
            Toast.makeText(this.phssContext, "GPS is not installed", 1).show();
        }
        return isGps;
    }

    public boolean isGpsOn() {
        try {
            boolean isGps = this.locationManager.isProviderEnabled("gps");
            if (!isGps) {
                Toast.makeText(this.phssContext, "GPS is not enbled", 1).show();
            }
            return isGps;
        } catch (Exception e) {
            Toast.makeText(this.phssContext, "GPS is not enbled", 1).show();
            return false;
        }
    }

    public void setOnLocationReceivedListener(OnLocationReceivedListener onLocationReceivedListener) {
        this.locationReceivedListener = onLocationReceivedListener;
    }
}
