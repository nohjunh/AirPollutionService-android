package com.nohjunh.airpollutionservice.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.nohjunh.airpollutionservice.R
import com.nohjunh.airpollutionservice.adapter.AdBannerAdapter
import com.nohjunh.airpollutionservice.adapter.CityListRVAdapter
import com.nohjunh.airpollutionservice.database.entity.CityAirPollutionEntity
import com.nohjunh.airpollutionservice.databinding.FragmentCityListBinding
import com.nohjunh.airpollutionservice.viewModel.MainViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

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


        /* Banner 배너 관련 코드 진행부 */

        var viewPager2 = binding.advertisingBanner

        // 데이터
        var Banners : Array<Int> =
            arrayOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3)

        // Adapter 초기화 (Banners DataSet 삽입)
        var adBannerAdapter = AdBannerAdapter(Banners)

        // Adapter 적용
        viewPager2.adapter = adBannerAdapter

        // 가로방향
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // 배너 몇 페이지인지 출력
        var currentPosition = 0
        var txtCurrentBanner = binding.txtCurrentBanner
        txtCurrentBanner.text = getString(R.string.viewpager2_banner, 1, Banners.size)
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            //사용자가 스크롤 했을때 position 수정
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                txtCurrentBanner.text = getString(
                    R.string.viewpager2_banner,
                    position + 1,
                    Banners.size
                )
                if (currentPosition > 2) currentPosition = 0
            }
        })

        // 페이지 변경하기
        fun setPage() {
            if (currentPosition > 2) currentPosition = 0
            viewPager2.setCurrentItem(currentPosition, true)
            currentPosition += 1
        }

        // 2초마다 페이지 자동 슬라이드
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(1800)
                setPage()
            }
        }

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