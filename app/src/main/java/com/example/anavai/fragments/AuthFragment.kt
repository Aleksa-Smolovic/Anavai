package com.example.anavai.fragments

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.biometric.BiometricManager
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.anavai.R
import kotlinx.android.synthetic.main.fragment_auth.*
import kotlin.math.hypot

/**
 * A simple [Fragment] subclass.
 */
class AuthFragment : Fragment() {

    lateinit var biometricManager: BiometricManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        biometricManager = BiometricManager.from(view.context)
        checkBiometricStatus(biometricManager)

        log_in_btn.setOnClickListener {
            log_in_btn.isEnabled = false
            startAnimation()
        }

        no_acc.setOnClickListener{
            it.findNavController().navigate(R.id.navigate_Auth_to_Register)
        }
    }

    private fun startAnimation() {
        val rotateLeft: Animation = AnimationUtils.loadAnimation(context,
            R.anim.rotate_left
        )
        val rotateRight: Animation = AnimationUtils.loadAnimation(context,
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
                findNavController().navigate(R.id.navigate_Auth_to_Menu)
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
