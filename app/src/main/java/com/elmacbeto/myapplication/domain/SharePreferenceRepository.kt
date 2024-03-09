package com.elmacbeto.myapplication.domain

import com.elmacbeto.myapplication.sis.di.ModuleSharePreference
import javax.inject.Inject

class SharePreferenceRepository @Inject constructor(
    private val sharedPreferences: ModuleSharePreference
) {

    fun getLoginState() = sharedPreferences.getLoginState()

}