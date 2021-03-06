/*
 Copyright 2017 Google Inc. All Rights Reserved.
 <p>
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 <p>
 http://www.apache.org/licenses/LICENSE-2.0
 <p>
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/

package com.google.android.gms.location.sample.basiclocationsample

//import android.location.Location

//Location.distanceBetween
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Color
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.sample.basiclocationsample.BuildConfig.APPLICATION_ID
import com.google.android.gms.location.sample.basiclocationsample.Repeater.createRepeaterList
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Demonstrates use of the Location API to retrieve the last known location for a device.
 */
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 34

    /**
     * Provides the entry point to the Fused Location Provider API.
     */
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var latitudeText: TextView
    private lateinit var longitudeText: TextView
    private lateinit var datetimeStamp: TextView
    private lateinit var distanceText: TextView
    private var resume = 0
    private var destroy = 0
    private var stop = 0
    private var pause = 0
    private var start = 0
    private var create = 0
    private var restart = 0

    private val startLat = -37.892   // TODO - set radial centre
    private val startLong = 144.775    // set these values into an array
    private val vk3rglLat = -37.8865371   // TODO - set radial centre
    private val vk3rglLong = 144.2694973    // set these values into an array  Mt. Anakie
    // such that the elements can be used sequentially that can be used to compare with radial centre

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        latitudeText = findViewById(R.id.latitude_text)
        longitudeText = findViewById(R.id.longitude_text)
        datetimeStamp = findViewById(R.id.time_stamp_text)
        distanceText = findViewById(R.id.distance_text)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val array = arrayOf(1, 2, 3, 4)
//	array[x] = array[x] * 2 // no actual calls to get() and set() generated
        for (x in array) { // no iterator created
            print(x)
        }
        print(array[2])
        val javaObj = createRepeaterList()   // TODO in onCreate can initialise the list
        println(javaObj.size)
/*        val latitudes = doubleArrayOf(startLat, 1.2, 1.3)
        val longitudes = doubleArrayOf(startLong, 1.2, 1.3)*/
        Log.w("ZZZZ: ", "1 onCreate $create")
        create++
    }

    override fun onStart() {
        super.onStart()
        Log.w("ZZZZ: ", "2 onStart $start")
        if (!checkPermissions()) {
            requestPermissions()
        } else {
            getLastLocation()
        }
        start++
    }

    override fun onResume() {
        super.onResume()
        Log.w("ZZZZ: ", "3 onResume $resume")
/*        if (!checkPermissions()) {
            requestPermissions()
        } else {
            getLastLocation()
        }*/
        resume++
    }

    override fun onPause() {
        super.onPause()
        Log.w("ZZZZ: ", "4 onPause $pause")
/*        if (!checkPermissions()) {
            requestPermissions()
        } else {
            getLastLocation()
        }*/
        pause++
    }

    override fun onStop() {
        super.onStop()
        Log.w("ZZZZ: ", "5 onStop $stop")
/*        if (!checkPermissions()) {
            requestPermissions()
        } else {
            getLastLocation()
        }*/
        stop++
    }

    override fun onRestart() {
        super.onRestart()
        Log.w("ZZZZ: ", "7 onRestart $restart")
/*        if (!checkPermissions()) {
            requestPermissions()
        } else {
            getLastLocation()
        }*/
        restart++
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("ZZZZ: ", "6 onDestroy $destroy")
/*        if (!checkPermissions()) {
            requestPermissions()
        } else {
            getLastLocation()
        }*/
        destroy++
    }

    /**
     * Provides a simple way of getting a device's location and is well suited for
     * applications that do not require a fine-grained location and that do not need location
     * updates. Gets the best and most recent location currently available, which may be null
     * in rare cases when a location is not available.
     *
     * Note: this method should be called after location permission has been granted.
     */
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        val latitudes = doubleArrayOf(1.1, startLat, vk3rglLat, 1.3)
        val longitudes = doubleArrayOf(1.1, startLong, vk3rglLong, 1.3)
        val repeaterStrings = arrayOf("1", "2", "3")
        fusedLocationClient.lastLocation
            .addOnCompleteListener { taskLocation ->
                if (taskLocation.isSuccessful && taskLocation.result != null) {
// https://developers.google.com/android/reference/com/google/android/gms/location/LocationResult
                    val location = taskLocation.result
                    val current = LocalDateTime.now()    // was .now()
                    val formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")
                    val formatted = current.format(formatter)
                    // TODO make the following two lines display evenly
                    latitudeText.text = resources
                        .getString(R.string.latitude_label, location?.latitude)
                    longitudeText.text = this.resources
                        .getString(R.string.longitude_label, location?.longitude)
                    //        datetimeStamp.text = "Date Time Stamp : $current"
                    datetimeStamp.text = "Date Time Stamp : $formatted"
                    val ans1 = floatArrayOf(1.1f, 2.2f)
                    location?.latitude?.let {
                        Location.distanceBetween(
                            //TODO compare current location with radial centre
                            latitudes[2],  //    Latitudes[0],// TODO was startLat,
                            longitudes[2], //    startLong,
                            location.latitude,
                            location.longitude,
                            ans1
                        )
                    }
                    distanceText.text =
                        "Distance : " + ans1[0] + " " + repeaterStrings[1] + " metres \nDistance : " + ans1[0] + " " + repeaterStrings[2] + " metres\n"
                    // TODO Log.w("ZZZZ:", distanceText.text as String)
                    Log.w("ZZZZ: ", "Distance = ")
                    showSnackbar(R.string.permission_rationale, android.R.string.ok)  // TODO fix
                } else {
                    Log.w(TAG, "getLastLocation:exception", taskLocation.exception)
                    showSnackbar(R.string.no_location_detected)
                }
            }
    }

    /**
     * Shows a [Snackbar].
     *
     * @param snackStrId The id for the string resource for the Snackbar text.
     * @param actionStrId The text of the action item.
     * @param listener The listener associated with the Snackbar action.
     */
    private fun showSnackbar(
        snackStrId: Int,
        actionStrId: Int = 0,
        listener: View.OnClickListener? = null
    ) {
        val snackbar = Snackbar.make(
            findViewById(android.R.id.content), getString(snackStrId),
            LENGTH_LONG
        )  // TODO was LENGTH_INDEFINITE
        if (actionStrId != 0 && listener != null) {
            snackbar.setAction(getString(actionStrId), listener)
        }
        snackbar.setBackgroundTint(Color.GREEN)
        snackbar.setTextColor(Color.RED)
        snackbar.show()
        Log.i(TAG, "after Snackbar")
    }

    /**
     * Return the current state of the permissions needed.
     */
    private fun checkPermissions() =
        ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(
            this, arrayOf(ACCESS_COARSE_LOCATION),
            REQUEST_PERMISSIONS_REQUEST_CODE
        )
    }

    private fun requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_COARSE_LOCATION)) {
            // Provide an additional rationale to the user. This would happen if the user denied the
            // request previously, but didn't check the "Don't ask again" checkbox.
            Log.w("ZZZZ:", "Displaying permission rationale to provide additional context.")
            showSnackbar(R.string.permission_rationale, android.R.string.ok) {
                // Request permission
                startLocationPermissionRequest()
            }

        } else {
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            Log.w("ZZZZ:", "Requesting permission")
            startLocationPermissionRequest()
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.i(TAG, "onRequestPermissionResult")
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                grantResults.isEmpty() -> Log.i(TAG, "User interaction was cancelled.")

                // Permission granted.
                (grantResults[0] == PERMISSION_GRANTED) -> getLastLocation()

                // Permission denied.

                // Notify the user via a SnackBar that they have rejected a core permission for the
                // app, which makes the Activity useless. In a real app, core permissions would
                // typically be best requested during a welcome-screen flow.

                // Additionally, it is important to remember that a permission might have been
                // rejected without asking the user for permission (device policy or "Never ask
                // again" prompts). Therefore, a user interface affordance is typically implemented
                // when permissions are denied. Otherwise, your app could appear unresponsive to
                // touches or interactions which have required permissions.
                else -> {
                    showSnackbar(
                        R.string.permission_denied_explanation, R.string.settings
                    ) {
                        // Build intent that displays the App settings screen.
                        val intent = Intent().apply {
                            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            data = Uri.fromParts("package", APPLICATION_ID, null)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        startActivity(intent)
                    }
                }
            }
        }
    }

}
