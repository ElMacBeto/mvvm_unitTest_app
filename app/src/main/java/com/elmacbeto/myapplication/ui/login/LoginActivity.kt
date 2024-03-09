package com.elmacbeto.myapplication.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import com.elmacbeto.myapplication.R
import com.elmacbeto.myapplication.databinding.ActivityLoginBinding
import com.elmacbeto.myapplication.sis.utils.Constants
import com.elmacbeto.myapplication.sis.utils.customComponents.alerts.LoaderNBEXWidget

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loader by lazy { LoaderNBEXWidget() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStartNavigation()
    }

    private fun setStartNavigation() {
        val myNavHostFragment = supportFragmentManager
            .findFragmentById(binding.fragmentContainer.id) as NavHostFragment

        val inflater = myNavHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.login_navigation)

        when (intent.getIntExtra(Constants.NAVIGATION_STATE_EXTRA, 0)) {
            Constants.LoginState.LOGOUT.value -> graph.setStartDestination(R.id.logoutFragment)

            Constants.LoginState.LOGIN_WITH_GOOGLE.value -> graph.setStartDestination(R.id.loginWithGoogleFragment)

            Constants.LoginState.LOGIN_WITH_FINGERPRINT.value -> graph.setStartDestination(R.id.loginWithFingerPrintFragment)

            Constants.LoginState.LOGIN_WITH_PASSWORD.value -> graph.setStartDestination(R.id.loginWithPasswordFragment)

            else -> graph.setStartDestination(R.id.logoutFragment)
        }

        myNavHostFragment.navController.graph = graph
    }

    fun showLoader() {
        try {
            val loaderDialog = supportFragmentManager.findFragmentByTag("Loader")
            val isShowing = loader.dialog?.isShowing ?: false

            if (loaderDialog != null && loaderDialog.isAdded) return

            if (!loader.isAdded && !loader.isVisible && !isShowing) {
                loader.show(supportFragmentManager, "Loader")
                supportFragmentManager.executePendingTransactions()
            }
        } catch (e: Exception) {
            Log.e("Loader error", e.message.toString())
        }
    }

    fun dismissLoader() {
        if (loader.isAdded) {
            if (loader.isResumed) {
                loader.dismiss()
            } else {
                loader.dismissAllowingStateLoss()
            }
        }
    }

}