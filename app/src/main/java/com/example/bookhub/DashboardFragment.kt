package com.example.bookhub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class DashboardFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerView=view.findViewById(R.id.recyclerDashboard)
        layoutManager=LinearLayoutManager(activity)


        return view
    }

}