package tare.app.test.openinapptest.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import tare.app.test.openinapptest.R
import tare.app.test.openinapptest.databinding.ActivitySplashBinding
import tare.app.test.openinapptest.ui.home.HomeActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivitySplashBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setStatusBarColor(R.color.white,root)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        Handler(Looper.getMainLooper()).postDelayed(
            {startActivity(Intent(this, HomeActivity::class.java));finish()},
            3000
        )
    }

    private fun setStatusBarColor(@ColorRes resId: Int, view: View): Window = window.apply {
        WindowCompat.setDecorFitsSystemWindows(this, true)
        WindowInsetsControllerCompat(this, view).show(WindowInsetsCompat.Type.statusBars())
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = ContextCompat.getColor(this@SplashActivity, resId)
    }
}