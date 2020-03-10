package com.example.dronetracker.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.dronetracker.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapViewModel: MapViewModel
    private lateinit var myview: View
    private lateinit var mapView: MapView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mapViewModel = ViewModelProviders.of(this).get(MapViewModel::class.java)
        myview = inflater.inflate(R.layout.fragment_map, container, false)
        //val textView: TextView = root.findViewById(R.id.text_dashboard)
        //mapViewModel.text.observe(viewLifecycleOwner, Observer { textView.text = it }



        return myview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = myview.findViewById(R.id.map)

        if (mapView != null)
        {
            mapView.onCreate(null)
            mapView.onResume()
            mapView.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val marker = LatLng(37.3354550, -121.8850220)

        googleMap.addMarker(
            MarkerOptions().position(marker)
                .title("Marker in San Jose")
        )

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
    }
}
