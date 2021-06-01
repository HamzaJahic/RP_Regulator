package com.example.rpregulator.view

import android.os.Bundle
import android.view.Menu
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aghajari.zoomhelper.ZoomHelper
import com.bumptech.glide.Glide
import com.example.rpregulator.R
import com.example.rpregulator.databinding.ActivityMainBinding
import com.example.rpregulator.utils.GlobalConstants.Companion.USER_ID
import com.example.rpregulator.viewmodel.MainActivityViewModel

private lateinit var binding: ActivityMainBinding
private lateinit var navController: NavController
private lateinit var appBarConfiguration: AppBarConfiguration
private lateinit var mainActivityViewModel: MainActivityViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_hostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        mainActivityViewModel = MainActivityViewModel(this)
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment,
                R.id.statusFragment,
                R.id.paperworkFragment,
                R.id.notesFragment,
                R.id.bestiaryFragment,
                R.id.settingsFragment,
                R.id.adminTabFragment
            ),
            binding.drawerLayout
        )

        setSupportActionBar(binding.toolbar)


        mainActivityViewModel.imgUri.observe(this, {
            val imgView = binding.navView.getHeaderView(0).findViewById<ImageView>(R.id.imgUser)
            Glide.with(this).load(it).into(imgView)
        })

        if (USER_ID.value == "Damir") {
            binding.navView.menu.removeItem(R.id.mainFragment)
            binding.navView.menu.removeItem(R.id.statusFragment)
            binding.navView.menu.removeItem(R.id.paperworkFragment)
            navGraph.startDestination = R.id.adminTabFragment
            navController.graph = navGraph
        } else {
            binding.navView.menu.removeItem(R.id.adminTabFragment)
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return ZoomHelper.getInstance().dispatchTouchEvent(ev!!, this) || super.dispatchTouchEvent(
            ev
        )
    }
}