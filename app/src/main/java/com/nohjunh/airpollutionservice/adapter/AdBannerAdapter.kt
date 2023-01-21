package com.nohjunh.airpollutionservice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nohjunh.airpollutionservice.R

class AdBannerAdapter(private val imageDataSet: Array<Int>):
    RecyclerView.Adapter<AdBannerAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // 초기화
        var imageView : ImageView = view.findViewById(R.id.Banner_image_View)
    }

    // 화면 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.banner_item_layout, parent, false)
        return ViewHolder(view)
    }

    // 데이터 설정
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.load(imageDataSet[position])
    }

    // 개수 가져오기
    override fun getItemCount(): Int {
        return imageDataSet.size
    }
}