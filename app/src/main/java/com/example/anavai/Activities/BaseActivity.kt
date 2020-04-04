package com.example.anavai.Activities

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewAnimationUtils
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.anavai.Fragments.MenuFragment
import com.example.anavai.Fragments.ProfileFragment
import com.example.anavai.Fragments.ShareFragment
import com.example.anavai.Fragments.SingleMediaFragment
import com.example.anavai.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_base.*
import kotlin.math.hypot

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        Handler().postDelayed({
            removeCover()
        }, 500)

        val navController:NavController = findNavController(R.id.nav_host_fragment_container)
        bottom_navigation_view.setupWithNavController(navController)
    }

    private fun removeCover() {
        val startRadius: Float =
            hypot((basic_cover.width).toDouble(), (basic_cover.height).toDouble()).toFloat()

        val cx: Float = (basic_cover.x + basic_cover.width / 2)
        val cy: Float = (basic_cover.y + basic_cover.height / 2)

        val expandAnimation: Animator = ViewAnimationUtils.createCircularReveal(
            basic_cover,
            (cx).toInt(),
            (cy).toInt(),
            startRadius,
            0f
        )

        expandAnimation.duration = 800
        expandAnimation.start()
        expandAnimation.doOnEnd {
            basic_cover.visibility = View.GONE
        }
    }

//    private val bottomNavigationListener =
//        BottomNavigationView.OnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.menuFragment -> {
//                    replaceFragment(MenuFragment())
//                    return@OnNavigationItemSelectedListener true
//                }
//                R.id.shareFragment -> {
//                    replaceFragment(ShareFragment())
//                    return@OnNavigationItemSelectedListener true
//                }
//                R.id.profileFragment -> {
//                    replaceFragment(ProfileFragment())
//                    return@OnNavigationItemSelectedListener true
//                }
//            }
//            false
//        }


}
