package com.example.yemekuygulamasi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekuygulamasi.entity.SepetYemekler
import com.example.yemekuygulamasi.repo.YemeklerDaoRepository

class SepetFragmentViewModel : ViewModel() {
    val krepo = YemeklerDaoRepository()
    var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>()

    init {
        sepetYemekleriYukle("cihan")
        sepetYemeklerListesi = krepo.sepetYemekleriGetir("cihan")
    }

    fun sil(sepet_yemek_id:Int,kullanici_adi:String){
        krepo.yemekSil(sepet_yemek_id, kullanici_adi)
    }

    fun sepetYemekleriYukle(kullanici_adi: String){
        krepo.sepetTumYemekleriAl(kullanici_adi)
    }
}