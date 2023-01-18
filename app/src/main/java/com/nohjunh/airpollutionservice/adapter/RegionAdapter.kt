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

class RegionAdapter(val context: Context, val regionSet: ArrayList<String>) :
    RecyclerView.Adapter<RegionAdapter.ViewHolder>() {

    private val checksStarRegionList = ArrayList<String>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val regionName : TextView = view.findViewById(R.id.regionName)
        val starBtn : ImageView = view.findViewById(R.id.starBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.region_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.regionName.text = regionSet[position]

        holder.starBtn.setOnClickListener {

            if (checksStarRegionList.contains(regionSet[position])) {
                checksStarRegionList.remove(regionSet[position])
                holder.starBtn.load(R.drawable.unstar)
            } else {
                checksStarRegionList.add(regionSet[position])
                holder.starBtn.load(R.drawable.star)
            }

        }
    }

    override fun getItemCount(): Int {
        return regionSet.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}