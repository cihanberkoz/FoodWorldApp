package com.example.yemekuygulamasi.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.yemekuygulamasi.entity.*
import com.example.yemekuygulamasi.retrofit.ApiUtils
import com.example.yemekuygulamasi.retrofit.YemeklerDAOInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository {
    val yemeklerListesi:MutableLiveData<List<Yemekler>>
    val sepetYemeklerListesi:MutableLiveData<List<SepetYemekler>>
    var ydao:YemeklerDAOInterface

    init {
        ydao = ApiUtils.getYemeklerDaoInterface()
        yemeklerListesi = MutableLiveData()
        sepetYemeklerListesi = MutableLiveData()
    }

    fun yemekleriGetir() : MutableLiveData<List<Yemekler>> {
        return yemeklerListesi
    }

    fun sepetYemekleriGetir(kullanici_adi: String) : MutableLiveData<List<SepetYemekler>> {
        return sepetYemeklerListesi
    }

    fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
        ydao.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi).enqueue(object : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
                val basari = response.body().success
                val mesaj = response.body().message
                Log.e("Sepet ekle","$basari - $mesaj")
            }

            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })
    }

    fun yemekSil(sepet_yemek_id:Int,kullanici_adi: String){
        val kullanici_adi = "cbo"
        ydao.sepetYemekSil(sepet_yemek_id,kullanici_adi).enqueue(object : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
               val basari = response.body().success
                val mesaj = response.body().message
                Log.e("Sepet sil","$basari - $mesaj")
                sepetTumYemekleriAl(kullanici_adi)
            }

            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })
    }

    fun tumYemekleriAl(){
        ydao.tumYemekler().enqueue(object : Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler
                yemeklerListesi.value = liste
            }

            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })
    }

    fun sepetTumYemekleriAl(kullanici_adi:String){
        val kullanici_adi = "cbo"
        ydao.sepetYemekler(kullanici_adi).enqueue(object : Callback<SepetYemeklerCevap>{
            override fun onResponse(call: Call<SepetYemeklerCevap>, response: Response<SepetYemeklerCevap>) {
                val liste = response.body().sepetYemekler
                sepetYemeklerListesi.value = liste
            }

            override fun onFailure(call: Call<SepetYemeklerCevap>?, t: Throwable?) {}
        })
    }

}