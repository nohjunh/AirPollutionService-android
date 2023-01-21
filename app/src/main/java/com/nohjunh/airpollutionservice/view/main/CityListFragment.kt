package com.nohjunh.airpollutionservice.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nohjunh.airpollutionservice.R
import com.nohjunh.airpollutionservice.adapter.CityListRVAdapter
import com.nohjunh.airpollutionservice.database.entity.CityAirPollutionEntity
import com.nohjunh.airpollutionservice.databinding.FragmentCityListBinding
import com.nohjunh.airpollutionservice.viewModel.MainViewModel

class CityListFragment : Fragment() {

    val citys = arrayOf<String>("서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주", "세종")

    private var _binding : FragmentCityListBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MainViewModel by activityViewModels()

    private val selectedCityList = ArrayList<CityAirPollutionEntity>()
    private val unSelectedCityList = ArrayList<CityAirPollutionEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCityAirPollutionData()
        viewModel.selectedCityAirList.observe(viewLifecycleOwner, Observer {

            var temp = ArrayList<String>()
            selectedCityList.clear()
            unSelectedCityList.clear()

            for (city in it) {
                if(temp.contains(city.sidoName)) {
                    continue
                } else {
                    selectedCityList.add(city)
                    temp.add(city.sidoName)
                }
            }
            for (city in citys) {
                if (!temp.contains(city)) {
                    val value = CityAirPollutionEntity(0, "$city","temp", "temp", 0, "temp", 0, false)
                    unSelectedCityList.add(value)
                }
            }
            setSelectedCityListRV()
        })

    }

    private fun setSelectedCityListRV() {
        val selectedRVAdapter = CityListRVAdapter(requireContext(), selectedCityList)
        binding.selectedCityRV.adapter = selectedRVAdapter
        binding.selectedCityRV.layoutManager = LinearLayoutManager(requireContext())

        selectedRVAdapter.itemClick = object : CityListRVAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                viewModel.deleteSelectedCityListData(selectedCityList[position].sidoName)
            }
        }

        val unSelectedRVAdapter = CityListRVAdapter(requireContext(), unSelectedCityList)
        binding.unSelectedCityRV.adapter = unSelectedRVAdapter
        binding.unSelectedCityRV.layoutManager = LinearLayoutManager(requireContext())

        unSelectedRVAdapter.itemClick = object : CityListRVAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                viewModel.insertSelectedCityListData(unSelectedCityList[position].sidoName)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}