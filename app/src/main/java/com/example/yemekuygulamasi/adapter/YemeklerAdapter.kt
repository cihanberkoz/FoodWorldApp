package com.example.yemekuygulamasi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.databinding.CardTasarimAnasayfaBinding
import com.example.yemekuygulamasi.databinding.FragmentAnasayfaBinding
import com.example.yemekuygulamasi.entity.Yemekler
import com.example.yemekuygulamasi.fragment.AnasayfaFragmentDirections
import com.example.yemekuygulamasi.viewmodel.AnasayfaFragmentViewModel
import com.squareup.picasso.Picasso

class YemeklerAdapter(var mContext: Context, var yemeklerListesi: List<Yemekler>, var viewModel: AnasayfaFragmentViewModel) :
    RecyclerView.Adapter<YemeklerAdapter.CardTasarimAnasayfaTutucu>() {

    inner class CardTasarimAnasayfaTutucu(tasarim: CardTasarimAnasayfaBinding) : RecyclerView.ViewHolder(tasarim.root) {
        val tasarim: CardTasarimAnasayfaBinding = tasarim
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimAnasayfaTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim: CardTasarimAnasayfaBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_tasarim_anasayfa, parent, false)
        return CardTasarimAnasayfaTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimAnasayfaTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim

        t.yemekNesnesi = yemek

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Picasso.get().load(url).into(t.imageViewYemekResim)

        t.satirCardAnasayfa.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecis(yemek = yemek)
            Navigation.findNavController(it).navigate(gecis)
        }
    }

    override fun getItemCount() = yemeklerListesi.size
}