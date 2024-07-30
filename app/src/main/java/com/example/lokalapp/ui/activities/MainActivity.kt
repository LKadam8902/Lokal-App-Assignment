package com.example.lokalapp.ui.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.lokalapp.R
import com.example.lokalapp.ui.fragments.HomeFragment
import com.example.lokalapp.utils.NetworkHelper
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val splashScreenId = findViewById<View>(R.id.splash_screen)
        val activityScreen = findViewById<View>(R.id.main_activity_screen)
        Handler(Looper.getMainLooper()).postDelayed({
            splashScreenId.visibility = View.GONE
            activityScreen.visibility = View.VISIBLE
        }, 5000)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btm_nav)
        val navController = findNavController(R.id.hostFragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

       if(NetworkHelper.isNetworkConnected(this)){
        val home = HomeFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.hostFragment, home)
            commit()
        }}else{
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show()
       }


    }
}