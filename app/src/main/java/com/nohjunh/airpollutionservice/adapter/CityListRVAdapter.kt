package com.nohjunh.airpollutionservice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nohjunh.airpollutionservice.R
import com.nohjunh.airpollutionservice.database.entity.CityAirPollutionEntity

class CityListRVAdapter(val context : Context, val dataSet : List<CityAirPollutionEntity>)
    : RecyclerView.Adapter<CityListRVAdapter.ViewHolder>() {

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val regionName : TextView = view.findViewById(R.id.regionName)
        val starBtn : ImageView = view.findViewById(R.id.starBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.citylist_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.regionName.text = dataSet[position].sidoName
        val selected = dataSet[position].selected
        if(selected) {
            holder.starBtn.load(R.drawable.star)
        } else {
            holder.starBtn.load(R.drawable.unstar)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}