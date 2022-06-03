package com.example.yemekuygulamasi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekuygulamasi.entity.Yemekler
import com.example.yemekuygulamasi.repo.YemeklerDaoRepository

class AnasayfaFragmentViewModel : ViewModel() {
    val krepo = YemeklerDaoRepository()
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init {
        yemekleriYukle()
        yemeklerListesi = krepo.yemekleriGetir()
    }

    fun yemekleriYukle(){
        krepo.tumYemekleriAl()
    }


}