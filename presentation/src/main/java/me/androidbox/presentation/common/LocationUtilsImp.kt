package me.androidbox.presentation.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationUtilsImp(private val activity: Activity) : LocationUtils {

    private var fusedLocationProviderClient: FusedLocationProviderClient? = null

    private companion object {
        const val permissionRequestCode = 1000
    }

    override fun isLocationServicesEnabled(): Boolean {
        return (activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager)
            .isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    override fun getLocationCoordinates(locationResultStatus: (LocationStatus) -> Unit) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

        if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),
                permissionRequestCode)
        }
        else {
            fusedLocationProviderClient?.lastLocation?.addOnSuccessListener(activity) { location ->
                if (location != null) {
                    locationResultStatus(LocationStatus.Success(location.latitude, location.longitude))
                }
                else {
                    locationResultStatus(LocationStatus.Failure("There are no location coordinates, try opening google maps and trying again"))
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun requestPermissionResults(locationResultStatus: (LocationStatus) -> Unit, requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            permissionRequestCode -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fusedLocationProviderClient?.lastLocation?.addOnSuccessListener(activity) { location ->
                        if(location != null) {
                            locationResultStatus(LocationStatus.Success(location.latitude, location.longitude))
                        }
                    }
                }
                else {
                    locationResultStatus(LocationStatus.Failure("Permissions has been denied"))
                }
            }
        }
    }

    sealed class LocationStatus {
        class Success(val latitude: Double, val longitude: Double) : LocationStatus()
        class Failure(val errorMessage: String) : LocationStatus()
    }
}
