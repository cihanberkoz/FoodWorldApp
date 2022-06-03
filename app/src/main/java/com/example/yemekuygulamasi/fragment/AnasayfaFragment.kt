package com.example.yemekuygulamasi.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.adapter.YemeklerAdapter
import com.example.yemekuygulamasi.databinding.FragmentAnasayfaBinding
import com.example.yemekuygulamasi.viewmodel.AnasayfaFragmentViewModel

class AnasayfaFragment : Fragment() {

    private lateinit var tasarim: FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false)
        tasarim.anasayfaFragment = this

        tasarim.anasayfaToolbarBaslik = "FoodWorld"


        viewModel.yemeklerListesi.observe(viewLifecycleOwner) {
            val adapter = YemeklerAdapter(requireContext(), it, viewModel)
            tasarim.yemeklerAdapter = adapter
        }

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnasayfaFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun sepetTikla(v: View) {
        val gecis = AnasayfaFragmentDirections.sepetGecis()
        Navigation.findNavController(v).navigate(gecis)
    }
}