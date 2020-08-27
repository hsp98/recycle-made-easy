package com.technocrats.recycle.made.easy

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jakewharton.threetenabp.AndroidThreeTen
import com.technocrats.recycle.made.easy.ui.calendar.CalendarFragment
import com.technocrats.recycle.made.easy.ui.categories.CategoriesFragment
import com.technocrats.recycle.made.easy.ui.donate.DonateFragment
import com.technocrats.recycle.made.easy.ui.scan.ScanFragment
import com.technocrats.recycle.made.easy.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

//    public var jsonObject = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_categories, R.id.navigation_calendar, R.id.navigation_scan, R.id.navigation_donate, R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        AndroidThreeTen.init(this)


        val jsonfile: String = applicationContext.assets.open("vish.json").bufferedReader().use {it.readText()}

        var jsonObject = JSONObject(jsonfile)

//        Toast.makeText(this,jsonObject.toString(),Toast.LENGTH_SHORT).show()

//        navView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.navigation_donate -> {
//                    findNavController(R.id.nav_host_fragment)
//                        .navigate(R.id.navigation_donate)
//
//                }
//                R.id.navigation_categories -> {
//                    findNavController(R.id.nav_host_fragment)
//                        .navigate(R.id.navigation_categories)
//
//                }
//                R.id.navigation_calendar -> {
//                    findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_calendar)
//                }
//                R.id.navigation_scan -> {
//                    findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_scan)
//                }
//                R.id.navigation_settings -> {
//                    findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_settings)
//                }
//            }
//            true
//        }

    }

}
