package com.example.yemekuygulamasi.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.yemekuygulamasi.databinding.FragmentSplashFragmentBinding


class SplashFragment : Fragment() {

    private lateinit var tasarim: FragmentSplashFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = FragmentSplashFragmentBinding.inflate(inflater, container, false)

        val timer = object: CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long){}

            override fun onFinish() {
                val gecis = SplashFragmentDirections.anasayfaGecis()
                Navigation.findNavController(tasarim.root).navigate(gecis)
            }
        }
        timer.start()

        return tasarim.root
    }
}