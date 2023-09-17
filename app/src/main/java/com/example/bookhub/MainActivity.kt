package com.example.bookhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var navigationView: NavigationView
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var drawerLayout: DrawerLayout
    lateinit var frameLayout: FrameLayout
    lateinit var toolbar: Toolbar
    var previousMenuItem: MenuItem?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView=findViewById(R.id.navigationView)
        coordinatorLayout=findViewById(R.id.coordinatorLayout)
        drawerLayout=findViewById(R.id.drawerLayout)
        frameLayout=findViewById(R.id.frameLayout)
        toolbar=findViewById(R.id.toolbar)
        setUpToolbar()

        openDashboard()

        val actionBarDrawerToggle=ActionBarDrawerToggle(this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            if(previousMenuItem!=null){
                previousMenuItem?.isChecked=false
            }

            it.isCheckable=true
            it.isChecked=true
            previousMenuItem=it

            when(it.itemId){
                R.id.dashboard->{
                    openDashboard()
                    drawerLayout.closeDrawers()
                }
                R.id.favourites->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,FavouritesFragment())
                        .commit()

                    supportActionBar?.title="Favourites"
                    drawerLayout.closeDrawers()
                }
                R.id.profile->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,ProfileFragment())
                        .commit()
                    supportActionBar?.title="Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.aboutApp->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,AboutAppFragment())//.addToBackStack("About App")
                        .commit()

                    supportActionBar?.title="About App"
                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    fun openDashboard(){
        val fragment=DashboardFragment()
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
        transaction.commit()

        supportActionBar?.title="Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)
    }

    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="BookHub"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id= item.itemId

        if(id== androidx.appcompat.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val frag=supportFragmentManager.findFragmentById(R.id.frameLayout)

        when(frag) {
            !is DashboardFragment -> openDashboard()

            else ->super.onBackPressed()
        }
    }
}