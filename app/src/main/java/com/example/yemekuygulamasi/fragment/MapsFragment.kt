package com.example.yemekuygulamasi.fragment

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.databinding.FragmentMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private lateinit var tasarim:FragmentMapsBinding

    private val callback = OnMapReadyCallback { googleMap ->
        val konum = LatLng(40.908074, 29.217028)
        googleMap.addMarker(MarkerOptions().position(konum).title("Home").icon(BitmapDescriptorFactory.fromResource(R.drawable.home)))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(konum,13f))

        val konum2 = LatLng(40.9006212, 29.2385721)
        googleMap.addMarker(MarkerOptions().position(konum2).title("Restaurant").icon(BitmapDescriptorFactory.fromResource(R.drawable.courier_pin)))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(konum,13f))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_maps, container, false)
        tasarim.mapsToolbarBaslik = "Siparişim"

        return tasarim.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}