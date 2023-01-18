package com.nohjunh.airpollutionservice.view.preview

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import coil.Coil
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.nohjunh.airpollutionservice.R
import com.nohjunh.airpollutionservice.databinding.FragmentPreview1Binding
import com.nohjunh.airpollutionservice.view.region.RegionActivity
import kotlinx.coroutines.*

class PreviewFragment1 : Fragment() {

    private var _binding : FragmentPreview1Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPreview1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ///////// coil GIF 확장 라이브러리 /////////////
        val imageLoader = context?.let {
            ImageLoader.Builder(it)
                .components {
                    if (SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()
        }
        if (imageLoader != null) {
            Coil.setImageLoader(imageLoader)
        }
        binding.menuImg.load(R.drawable.upmenu) {
            crossfade(true) // fade in 애니메이션
        }
        ///////////////////////////////////////////

        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            Navigation.findNavController(view).navigate(R.id.action_previewFragment1_to_previewFragment2)
        }

        binding.region.setOnClickListener {
            val intent = Intent(requireContext(), RegionActivity::class.java)
            startActivity(intent)
        }
    }
}
