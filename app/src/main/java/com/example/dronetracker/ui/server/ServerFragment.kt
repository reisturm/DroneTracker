package com.example.dronetracker.ui.server

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dronetracker.R

class ServerFragment : Fragment() {

    private lateinit var serverViewModel: ServerViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        serverViewModel =
                ViewModelProviders.of(this).get(ServerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_server, container, false)
        val textView: TextView = root.findViewById(R.id.text_login)
        serverViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
