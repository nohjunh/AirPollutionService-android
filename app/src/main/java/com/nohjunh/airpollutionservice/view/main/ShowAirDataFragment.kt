package com.nohjunh.airpollutionservice.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nohjunh.airpollutionservice.R
import com.nohjunh.airpollutionservice.adapter.RegionAdapter
import com.nohjunh.airpollutionservice.adapter.ShowAirDataRVAdapter
import com.nohjunh.airpollutionservice.database.entity.CityAirPollutionEntity
import com.nohjunh.airpollutionservice.databinding.ActivityRegionBinding
import com.nohjunh.airpollutionservice.databinding.FragmentShowAirDataBinding
import com.nohjunh.airpollutionservice.viewModel.AirPollutionViewModel
import com.nohjunh.airpollutionservice.viewModel.MainViewModel
import com.nohjunh.airpollutionservice.viewModel.RegionViewModel
import timber.log.Timber

class ShowAirDataFragment : Fragment() {

    private var _binding : FragmentShowAirDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MainViewModel by activityViewModels()

    private val selectedCityList = ArrayList<CityAirPollutionEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShowAirDataBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCityAirPollutionData()
        viewModel.selectedCityAirList.observe(viewLifecycleOwner, Observer {

            selectedCityList.clear()

            for (city in it) {
                selectedCityList.add(city)
            }

            setSelectedCityListRV()
        })

    }

    private fun setSelectedCityListRV() {
        val selectedRVAdapter = ShowAirDataRVAdapter(requireContext(), selectedCityList)
        binding.selectedCityRV.adapter = selectedRVAdapter
        binding.selectedCityRV.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}