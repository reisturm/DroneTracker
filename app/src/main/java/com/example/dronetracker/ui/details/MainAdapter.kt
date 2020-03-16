package com.example.dronetracker.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dronetracker.R
import kotlinx.android.synthetic.main.detail_row.view.*

class MainAdapter(val datafeed: List<DroneData>): RecyclerView.Adapter<CustomViewHolder>() {
    val guidList = listOf<String>()
    val latList = listOf<String>()
    val longList = listOf<String>()
    // number of items
    override fun getItemCount(): Int {
        return datafeed.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.detail_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val gufi = datafeed.get(position).MessageAolFlightPlan.gufi
        val lat = datafeed.get(position).MessageAolFlightPlan.lla[0]
        val long = datafeed.get(position).MessageAolFlightPlan.lla[1]

        holder.view.textView_GUFI?.text = gufi
        holder.view.textView_Lat?.text = lat.toString()
        holder.view.textView_long?.text = long.toString()

    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}