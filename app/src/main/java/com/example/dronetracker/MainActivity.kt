package com.example.dronetracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dronetracker.ui.details.DroneData
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_server, R.id.navigation_map, R.id.navigation_details))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        fetchJson()
    }

    fun fetchJson() {
        println("Attemping to Fetch JSON")
        val url = "http://10.0.2.2:3000/"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()

                val gson = GsonBuilder().create()

                val datafeed: List<DroneData> = gson.fromJson(body, Array<DroneData>::class.java).toList()


                var hashMap : HashMap<String, MutableList<MutableList<LatLng>>> = HashMap()

                var datafeedSize = datafeed.size-1;
                var operationVolumesSize = 0;
                var coordinatesArray = 0;
                var ListFlightGeographyPolygon: MutableList<MutableList<LatLng>> = ArrayList(ArrayList())
                var flightGeographyPolygon: MutableList<LatLng> = ArrayList()


                for(datafeedIndex in 0..datafeedSize){
                    ListFlightGeographyPolygon.clear();
                    var gufi = datafeed[datafeedIndex].MessageAolFlightPlan.gufi;
                    //Log.i("status", gufi);
                    operationVolumesSize = datafeed[datafeedIndex].MessageAolFlightPlan.operation_volumes.size-1;

                    for(operationVolumesIndex in 0..operationVolumesSize){
                        coordinatesArray = datafeed[datafeedIndex].MessageAolFlightPlan.operation_volumes[operationVolumesIndex].flight_geography.coordinates[0].size-1;
                        flightGeographyPolygon.clear()

                        for(coordinatesArrayIndex in 0..coordinatesArray){
                            var coordinates=datafeed[datafeedIndex].MessageAolFlightPlan.
                                operation_volumes[operationVolumesIndex]
                                .flight_geography.coordinates[0][coordinatesArrayIndex];
                            Log.i("coordinates", coordinates.toString());

                            var lat = coordinates[1].toDouble();
                            Log.i("lat", lat.toString());

                            var long = coordinates[0].toDouble();
                            Log.i("long", long.toString());

                            var latLng = LatLng(lat,long);
                            Log.i("latLng", latLng.toString());

                            flightGeographyPolygon.add(latLng);
                            //Log.i("flightGeographyPolygon[coordinatesArrayIndex]",flightGeographyPolygon[coordinatesArrayIndex].toString());
                        }

                        //Log.i("status", "coodinatesArrayDone");
                        ListFlightGeographyPolygon.add(flightGeographyPolygon);
                        //flightGeographyPolygon.clear()
                        //Log.i("status2",flightGeographyPolygon[0].toString())

                    }
                    //Log.i("status", "operationVolumesDones");
                    //Log.i("status",ListFlightGeographyPolygon.size.toString())
                    //Log.i("status",ListFlightGeographyPolygon[0].toString())

                    /*for(i in 0..ListFlightGeographyPolygon.size-1){
                        Log.i("status3",ListFlightGeographyPolygon[i].toString());
                    }*/

                    hashMap.put(gufi,ListFlightGeographyPolygon);

                }

                //Log.i("hashMapSize", hashMap.size.toString());
                //Log.i("97b3418f-e482-4786-a2a8-422ce9a5c208", hashMap.get("97b3418f-e482-4786-a2a8-422ce9a5c208").toString());
                /*for(key in hashMap.keys){
                    Log.i("hashMap", "Element at key $key : ${hashMap[key]}");
                }*/







            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }
}

class DroneData(val MessageAolFlightPlan: MessageAOLFlightPlan)

class MessageAOLFlightPlan(val callsign: String, val gufi: String, val state: String, val operation_volumes: List<OperationVolume>, val controller_location: ControllerLocation, val gcs_location: GCSLocation, val metadata: MetaData, val lla: List<Float>)

class OperationVolume(val ordinal: Int, val near_structure: Boolean, val effective_time_begin: String, effective_time_end: String, val min_altitude: AltitudeObj, val max_altitude: AltitudeObj, val beyond_visual_line_of_sight: Boolean, val volume_type: String, val flight_geography: FGObject)

class AltitudeObj(val altitude_value: Int, val vertical_reference: String, val units_of_measure: String, val source: String)

class FGObject(val type: String, val coordinates: List<List<List<Float>>>)

class ControllerLocation(val coordinates: List<Float>, type: String)

class GCSLocation(val coordinates: List<Float>, type: String)

class MetaData(val data_collection: Boolean, val scenario: String, val test_card: String, val call_sign: String, val test_type: String, val source: String, val event_id: String, val location: String, val setting: String, val free_text: String, val modified: Boolean, val test_run: String)
