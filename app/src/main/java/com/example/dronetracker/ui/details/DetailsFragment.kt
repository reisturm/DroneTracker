package com.example.dronetracker.ui.details

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dronetracker.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_details.*
import okhttp3.*
import java.io.IOException

class DetailsFragment : Fragment() {

    private lateinit var detailsViewModel: DetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_details, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_main.layoutManager = LinearLayoutManager(context)
//        recyclerView_main.adapter = MainAdapter()

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

                activity?.runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(datafeed)
                }
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

