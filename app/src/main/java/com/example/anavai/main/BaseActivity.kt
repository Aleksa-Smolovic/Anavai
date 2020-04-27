package com.example.anavai.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.anavai.fragments.MenuFragment
import com.example.anavai.fragments.ProfileFragment
import com.example.anavai.fragments.ShareFragment
import com.example.anavai.R
import kotlinx.android.synthetic.main.activity_base.*

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        val navController:NavController = findNavController(R.id.nav_host_fragment_container)
        bottom_navigation_view.setupWithNavController(navController)

        supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
                when(f){
                    is MenuFragment -> showBottomNav()
                    is ShareFragment -> showBottomNav()
                    is ProfileFragment -> showBottomNav()
                    else -> hideBottomNav()
                }
            }
        }, true)
    }

    private fun showBottomNav() {
        bottom_navigation_view.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        bottom_navigation_view.visibility = View.GONE
    }


}
