package com.example.anavai

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    lateinit var biometricManager: BiometricManager
    lateinit var frontLayout: LinearLayout
    lateinit var backLayout: LinearLayout
    lateinit var parentLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        biometricManager = BiometricManager.from(this)
        checkBiometricStatus(biometricManager)

        parentLayout = findViewById(R.id.main_fragment_container)
        frontLayout = findViewById(R.id.front_rectangle)
        backLayout = findViewById(R.id.back_rectangle)


        frontLayout.setOnClickListener {
            startAnimation()
        }
    }

    fun startAnimation(){
        var rotateLeft: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate_left)
        var rotateRight: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate_right)

        val cx: Int = parentLayout.left + parentLayout.right
        val cy: Int = parentLayout.top + parentLayout.bottom

        var finalRadius: Int = Math.max(parentLayout.width, parentLayout.height)

        var expandAnimation: Animator = ViewAnimationUtils.createCircularReveal(frontLayout, cx, cy, 0f, finalRadius.toFloat())
        frontLayout.startAnimation(rotateLeft)
        backLayout.startAnimation(rotateRight)
        Handler().postDelayed({
            expandAnimation.start()
        }, 2000)
    }

    fun checkBiometricStatus(biometricManager: BiometricManager){
        when(biometricManager.canAuthenticate()){
            BiometricManager.BIOMETRIC_SUCCESS->
                System.out.println("Moze")
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE->
                System.out.println("Ne moze")
        }
    }

}
