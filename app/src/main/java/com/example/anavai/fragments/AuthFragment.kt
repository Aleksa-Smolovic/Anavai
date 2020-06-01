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
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.anavai.R
import com.example.anavai.databinding.FragmentAuthBinding
import com.example.anavai.interfaces.LoginListener
import com.example.anavai.utils.toast
import com.example.anavai.view_models.LoginViewModel
import kotlinx.android.synthetic.main.fragment_auth.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.hypot

/**
 * A simple [Fragment] subclass.
 */
class AuthFragment : Fragment(), LoginListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val loginViewModel: LoginViewModel by viewModel()
//        using databinding with viewmodel
        val binding: FragmentAuthBinding = FragmentAuthBinding.inflate(inflater, container, false)
        loginViewModel.loginListener = this
        binding.loginViewmodel = loginViewModel
        return binding.root
//        instead of this
//        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        no_acc.setOnClickListener {
            it.findNavController().navigate(R.id.navigate_Auth_to_Register)
        }
    }

    private fun startAnimation() {
        val rotateLeft: Animation = AnimationUtils.loadAnimation(
            context,
            R.anim.rotate_left
        )
        val rotateRight: Animation = AnimationUtils.loadAnimation(
            context,
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

    override fun onStarted() {
        log_in_btn.isEnabled = false
        context?.toast("Started")
        splash_animation.cancelAnimation()
        splash_animation.visibility = View.GONE
        splash_animation_error.visibility = View.GONE
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            context?.toast(it)
        })
        splash_animation.visibility = View.VISIBLE
        splash_animation.playAnimation()
        startAnimation()
    }

    override fun onFailure(message: String) {
        splash_animation_error.visibility = View.VISIBLE
        splash_animation_error.playAnimation()
        context?.toast(message)
        log_in_btn.isEnabled = true
    }

}
