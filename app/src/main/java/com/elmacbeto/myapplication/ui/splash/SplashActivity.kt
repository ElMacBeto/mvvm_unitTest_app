package com.elmacbeto.myapplication.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.elmacbeto.myapplication.databinding.ActivitySplashBinding
import com.elmacbeto.myapplication.sis.utils.Constants
import com.elmacbeto.myapplication.sis.utils.Constants.NAVIGATION_STATE_EXTRA
import com.elmacbeto.myapplication.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()
    private lateinit var binding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            setLoginStartView()
        }, 3000)
    }

    private fun setLoginStartView() {
        val intent = Intent(this, LoginActivity::class.java)
        when (viewModel.checksLoginState()) {
            Constants.LoginState.LOGOUT.value ->
                intent.putExtra(NAVIGATION_STATE_EXTRA, Constants.LoginState.LOGOUT.value)

            Constants.LoginState.LOGOUT.value ->
                intent.putExtra(NAVIGATION_STATE_EXTRA, Constants.LoginState.LOGIN_WITH_GOOGLE.value)

            Constants.LoginState.LOGOUT.value ->
                intent.putExtra(NAVIGATION_STATE_EXTRA, Constants.LoginState.LOGIN_WITH_FINGERPRINT.value)

            Constants.LoginState.LOGOUT.value ->
                intent.putExtra(NAVIGATION_STATE_EXTRA, Constants.LoginState.LOGIN_WITH_PASSWORD.value)

            else -> intent.putExtra(NAVIGATION_STATE_EXTRA, Constants.LoginState.LOGOUT.value)
        }

        startActivity(intent)
        finish()
    }
}
