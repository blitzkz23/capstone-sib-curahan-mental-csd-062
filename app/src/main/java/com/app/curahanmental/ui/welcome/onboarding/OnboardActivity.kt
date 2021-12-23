package com.app.curahanmental.ui.welcome.onboarding

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.app.curahanmental.R
import com.app.curahanmental.databinding.ActivityOnboardBinding
import com.app.curahanmental.ui.auth.login.LoginActivity
import com.app.curahanmental.ui.main.MainActivity


class OnboardActivity : AppCompatActivity() {
	private val binding: ActivityOnboardBinding by lazy {
		ActivityOnboardBinding.inflate(
			layoutInflater
		)
	}
	private lateinit var onboardAdapter: OnboardAdapter
	private lateinit var onboardDots: LinearLayout
	private var state = "true"

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		showButton(false)
		setOnboard()
		setInitialOnboardDots()
		setCurrentOnboardDots(0)

	}

	override fun onResume() {
		super.onResume()
		val sharedPreferences =
			getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
		if (!sharedPreferences.getBoolean(state, false)) {
			sharedPreferences.edit().apply {
				putBoolean(state, true)
				apply()
			}
		} else {
			startActivity(Intent(this@OnboardActivity, MainActivity::class.java))
			finish()
		}
	}

	@SuppressLint("UseCompatLoadingForDrawables")
	private fun setOnboard() {
		onboardAdapter = OnboardAdapter(
			listOf(
				OnboardModel(
					getDrawable(R.drawable.curahan_onboarding1),
					getString(R.string.header_onboarding1),
					getString(R.string.overview_onboarding1)
				),
				OnboardModel(
					getDrawable(R.drawable.curahan_onboarding2),
					getString(R.string.header_onboarding2),
					getString(R.string.overview_onboarding2)
				),
				OnboardModel(
					getDrawable(R.drawable.curahan_onboarding3),
					getString(R.string.header_onboarding3),
					getString(R.string.overview_onboarding3)
				),
			)
		)
		val viewPager = binding.onboardViewPager
		viewPager.adapter = onboardAdapter
		viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
			override fun onPageSelected(position: Int) {
				super.onPageSelected(position)
				setCurrentOnboardDots(position)
			}
		})
		binding.onboardTvSkip.setOnClickListener {
			startActivity(Intent(this@OnboardActivity, LoginActivity::class.java))
			finish()
		}
		binding.onboardBtnStart.setOnClickListener {
			startActivity(Intent(this@OnboardActivity, LoginActivity::class.java))
			finish()
		}

	}

	private fun setInitialOnboardDots() {
		onboardDots = binding.onboardDots
		val identifier = arrayOfNulls<ImageView>(onboardAdapter.itemCount)
		val layoutParameter: LinearLayout.LayoutParams =
			LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
		layoutParameter.setMargins(16, 0, 0, 0)

		for (i in identifier.indices) {
			identifier[i] = ImageView(applicationContext)
			identifier[i]?.let {
				it.setImageDrawable(
					ContextCompat.getDrawable(
						applicationContext,
						R.drawable.onboard_dots_inactive
					)
				)
				it.layoutParams = layoutParameter
				onboardDots.addView(it)
			}
		}
	}

	fun setCurrentOnboardDots(position: Int) {
		val counts = onboardDots.childCount
		for (i in 0 until counts) {
			val imageView = onboardDots.getChildAt(i) as ImageView
			if (i == position && i != 2) {
				imageView.setImageDrawable(
					ContextCompat.getDrawable(
						applicationContext,
						R.drawable.onboard_dots_active
					)
				)
				showButton(false)
			} else if (i == position && i == 2) {
				imageView.setImageDrawable(
					ContextCompat.getDrawable(
						applicationContext,
						R.drawable.onboard_dots_active
					)
				)
				showButton(true)
			} else {
				imageView.setImageDrawable(
					ContextCompat.getDrawable(
						applicationContext,
						R.drawable.onboard_dots_inactive
					)
				)
			}
		}
	}

	private fun showButton(state: Boolean) {
		if (state) {
			binding.onboardBtnStart.visibility = VISIBLE
			binding.onboardTvSkip.visibility = GONE
		} else {
			binding.onboardBtnStart.visibility = GONE
			binding.onboardTvSkip.visibility = VISIBLE
		}
	}

}