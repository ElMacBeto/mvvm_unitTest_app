package com.elmacbeto.myapplication.ui.splash

import androidx.lifecycle.ViewModel
import com.elmacbeto.myapplication.domain.SharePreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sharedRepository: SharePreferenceRepository
):ViewModel() {

    fun checksLoginState() = sharedRepository.getLoginState()

}