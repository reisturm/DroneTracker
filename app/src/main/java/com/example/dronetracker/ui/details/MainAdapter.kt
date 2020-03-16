package com.example.dronetracker.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dronetracker.R

class MainAdapter: RecyclerView.Adapter<CustomViewHolder>() {
    val guidList = listOf<String>()
    val latList = listOf<String>()
    val longList = listOf<String>()
    // number of items
    override fun getItemCount(): Int {
        return 10
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.detail_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}