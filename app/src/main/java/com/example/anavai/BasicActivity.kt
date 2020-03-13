package com.example.anavai

import android.animation.Animator
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewAnimationUtils
import androidx.core.animation.doOnEnd
import kotlinx.android.synthetic.main.activity_basic.*
import kotlinx.android.synthetic.main.activity_main.*

class BasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)

        Handler().postDelayed({
            removeCover()
        }, 500)
    }

    private fun removeCover() {
        if (!basic_cover.isAttachedToWindow){
            removeCover()
        }
        val startRadius: Float =
            Math.hypot((basic_cover.width).toDouble(), (basic_cover.height).toDouble()).toFloat()

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

}
