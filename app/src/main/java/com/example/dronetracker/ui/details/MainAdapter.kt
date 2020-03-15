package com.example.dronetracker.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dronetracker.R

class MainAdapter: RecyclerView.Adapter<CustomViewHolder>() {

    // number of items
    override fun getItemCount(): Int {
        return 5
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

    }
}

class CustomViewHolder(v: View): RecyclerView.ViewHolder(v) {

}