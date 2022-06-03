package com.example.yemekuygulamasi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.adapter.SepetYemeklerAdapter
import com.example.yemekuygulamasi.databinding.FragmentSepetBinding
import com.example.yemekuygulamasi.entity.SepetYemekler
import com.example.yemekuygulamasi.viewmodel.SepetFragmentViewModel

class SepetFragment : Fragment() {

    private lateinit var tasarim: FragmentSepetBinding
    private lateinit var viewModel: SepetFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        tasarim = FragmentSepetBinding.inflate(inflater, container, false)

        tasarim.sepetToolbarBaslik = "Sepetim"

        tasarim.rvSepet.layoutManager = LinearLayoutManager(requireContext())

        tasarim.ivGeri.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner) {
            var toplam = 0

            it.forEach { sepetYemekler ->
                toplam += (sepetYemekler.yemek_fiyat * sepetYemekler.yemek_siparis_adet)
            }

            tasarim.tvToplam.text = "Toplam Tutar: " + toplam.toString() + "₺"

            val adapter = SepetYemeklerAdapter(requireContext(), it, viewModel)
            tasarim.rvSepet.adapter = adapter
        }

        tasarim.btnMapsGit.setOnClickListener {
                val gecis = SepetFragmentDirections.mapsGecis()
                Navigation.findNavController(it).navigate(gecis)
        }

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SepetFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepetYemekleriYukle("cbo")
    }
}