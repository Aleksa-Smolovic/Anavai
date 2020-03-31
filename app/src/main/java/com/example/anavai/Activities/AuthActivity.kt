package com.example.anavai.Activities

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.core.animation.doOnEnd
import com.example.anavai.R
import kotlinx.android.synthetic.main.activity_auth.*
import kotlin.math.hypot

class AuthActivity : AppCompatActivity() {

    lateinit var biometricManager: BiometricManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        biometricManager = BiometricManager.from(this)
        checkBiometricStatus(biometricManager)

        front_rectangle.setOnClickListener {
            startAnimation()
        }
    }

    private fun startAnimation() {
        val rotateLeft: Animation = AnimationUtils.loadAnimation(this,
                R.anim.rotate_left
        )
        val rotateRight: Animation = AnimationUtils.loadAnimation(this,
                R.anim.rotate_right
        )

        val finalRadius: Float =
                hypot((main_root.width).toDouble(), (main_root.height).toDouble()).toFloat()

        val cx: Float = (front_rectangle.x + front_rectangle.width / 2)
        val cy: Float = (front_rectangle.y + front_rectangle.height / 2)

        val expandAnimation: Animator = ViewAnimationUtils.createCircularReveal(
                cover_rectangle,
                (cx).toInt(),
                (cy).toInt(),
                0f,
                finalRadius
        )

        front_rectangle.startAnimation(rotateLeft)
        back_rectangle.startAnimation(rotateRight)
        Handler().postDelayed({
            cover_rectangle.visibility = View.VISIBLE
            expandAnimation.duration = 800
            expandAnimation.start()
            expandAnimation.doOnEnd {
                startActivity(Intent(this@AuthActivity, BaseActivity::class.java))
            }
        }, 2000)
    }

    private fun checkBiometricStatus(biometricManager: BiometricManager) {
        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                System.out.println("Moze")
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                System.out.println("Ne moze")
        }
    }

}