package com.example.anavai.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.anavai.R
import com.example.anavai.databinding.FragmentAuthBinding
import com.example.anavai.databinding.FragmentProfileBinding
import com.example.anavai.interfaces.LogoutListener
import com.example.anavai.models.User
import com.example.anavai.utils.loadImage
import com.example.anavai.view_models.ReviewViewModel
import com.example.anavai.view_models.UserViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(), LogoutListener {

    private val userViewModel: UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProfileBinding =
            FragmentProfileBinding.inflate(inflater, container, false)
        userViewModel.logoutListener = this
        binding.profileViewmodel = userViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUserInfo()
    }

    private fun loadUserInfo() {
        GlobalScope.launch(Dispatchers.Main) {
            val user: User = userViewModel.getUserInfo()
            profile_image.loadImage(user.image)
            profile_full_name.text = user.fullName
            profile_email.text = user.email
        }
    }

    override fun onSuccess() {
        findNavController().navigate(R.id.navigate_ProfileFragment_to_AuthFragment)
    }

}