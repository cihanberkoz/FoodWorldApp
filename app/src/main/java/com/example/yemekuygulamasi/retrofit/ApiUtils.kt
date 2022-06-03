package com.example.yemekuygulamasi.retrofit

class ApiUtils {
    companion object{
        val BASE_URL =  "http://kasimadalan.pe.hu/"

        fun getYemeklerDaoInterface() : YemeklerDAOInterface {
            return RetrofitClient.getClient(BASE_URL).create(YemeklerDAOInterface::class.java)
        }
    }
}