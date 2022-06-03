package com.example.yemekuygulamasi.viewmodel

import androidx.lifecycle.ViewModel
import com.example.yemekuygulamasi.repo.YemeklerDaoRepository

class YemekDetayFragmentViewModel : ViewModel() {
    val krepo = YemeklerDaoRepository()

    fun ekle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
        krepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }
}