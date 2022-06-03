package com.example.yemekuygulamasi.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.databinding.CardTasarimSepetBinding
import com.example.yemekuygulamasi.databinding.FragmentAnasayfaBinding
import com.example.yemekuygulamasi.entity.SepetYemekler
import com.example.yemekuygulamasi.viewmodel.SepetFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class SepetYemeklerAdapter(var mContext: Context, var sepetYemeklerListesi: List<SepetYemekler>, var viewModel: SepetFragmentViewModel) :
    RecyclerView.Adapter<SepetYemeklerAdapter.CardTasarimSepetTutucu>() {
    inner class CardTasarimSepetTutucu(tasarim: CardTasarimSepetBinding) : RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: CardTasarimSepetBinding = tasarim
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimSepetTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim: CardTasarimSepetBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_tasarim_sepet, parent, false)
        return CardTasarimSepetTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimSepetTutucu, position: Int) {
        val sepetYemek = sepetYemeklerListesi[position]
        val t = holder.tasarim

        t.sepetYemeklerNesnesi = sepetYemek

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"
        Picasso.get().load(url).resize(128, 128).into(t.imageViewSepetYemek)

        t.imageViewSilResim.setOnClickListener {
            Snackbar.make(it, "${sepetYemek.yemek_adi} Sepetten Çıkarılsın Mı?", Snackbar.LENGTH_LONG)
                .setBackgroundTint(Color.RED)
                .setTextColor(Color.WHITE)
                .setAction("Evet") { viewModel.sil(sepetYemek.sepet_yemek_id, sepetYemek.kullanici_adi) }.show()
        }
    }

    override fun getItemCount() = sepetYemeklerListesi.size
}