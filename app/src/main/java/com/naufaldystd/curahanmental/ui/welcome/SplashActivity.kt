package com.naufaldystd.curahanmental.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.naufaldystd.curahanmental.databinding.ActivitySplashBinding
import com.naufaldystd.curahanmental.ui.welcome.onboarding.OnboardActivity
import com.naufaldystd.curahanmental.utils.Constants.SPLASH_TIMER
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

	private var activitySplashBinding: ActivitySplashBinding? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
		setContentView(activitySplashBinding?.root)

		CoroutineScope(Dispatchers.Default).launch {
			delay(SPLASH_TIMER)
			withContext(Dispatchers.Main) {
				val intent = Intent(this@SplashActivity , OnboardActivity::class.java)
				startActivity(intent)
				finish()
			}
		}
	}
}