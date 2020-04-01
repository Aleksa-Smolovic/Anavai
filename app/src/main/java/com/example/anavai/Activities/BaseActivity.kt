package com.example.anavai.Activities

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewAnimationUtils
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
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

        replaceFragment(SingleMediaFragment())
        bottom_navigation_view.setOnNavigationItemSelectedListener(bottomNavigationListener)
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

    private val bottomNavigationListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu -> {
                    replaceFragment(MenuFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.share -> {
                    replaceFragment(ShareFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.base_fragment_container, fragment)
        fragmentTransaction.commit()
    }
}
