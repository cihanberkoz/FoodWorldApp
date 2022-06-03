package com.example.yemekuygulamasi.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.databinding.FragmentYemekDetayBinding
import com.example.yemekuygulamasi.viewmodel.YemekDetayFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class YemekDetayFragment : Fragment() {

    private lateinit var tasarim: FragmentYemekDetayBinding
    private lateinit var viewModel: YemekDetayFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: YemekDetayFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_yemek_detay, container, false)

        tasarim.yemekDetayToolbarBaslik = "FoodWorld"

        val bundle: YemekDetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek
        tasarim.yemekNesnesi = gelenYemek

        val kullanici_adi = "cbo"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Picasso.get().load(url).resize(512, 512).into(tasarim.imageViewYemekResimDetay)

        tasarim.buttonSepeteEkle.setOnClickListener {
            sepeteEkle(gelenYemek.yemek_adi,
                gelenYemek.yemek_resim_adi,
                gelenYemek.yemek_fiyat,
                tasarim.tasarimAdetSayaci.tvSayac.text.toString().toInt(),
                kullanici_adi)
            Snackbar.make(it, "${gelenYemek.yemek_adi} Sepete Eklendi", Snackbar.LENGTH_LONG).setBackgroundTint(
                Color.WHITE).setTextColor(Color.RED).show()
        }

        tasarim.ivGeri.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

        tasarim.ivSepeteGit.setOnClickListener {
            val gecis = YemekDetayFragmentDirections.detaydanSepeteGecis()
            Navigation.findNavController(it).navigate(gecis)
        }

        tasarim.tasarimAdetSayaci.tvArti.setOnClickListener {
            tasarim.tasarimAdetSayaci.tvSayac.text = (tasarim.tasarimAdetSayaci.tvSayac.text.toString().toInt() + 1).toString()
        }

        tasarim.tasarimAdetSayaci.tvEksi.setOnClickListener {
            if (tasarim.tasarimAdetSayaci.tvSayac.text.toString().toInt() == 1) {
                return@setOnClickListener
            }
            tasarim.tasarimAdetSayaci.tvSayac.text = (tasarim.tasarimAdetSayaci.tvSayac.text.toString().toInt() - 1).toString()
        }

        return tasarim.root
    }

    private fun sepeteEkle(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String) {
        viewModel.ekle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }
}