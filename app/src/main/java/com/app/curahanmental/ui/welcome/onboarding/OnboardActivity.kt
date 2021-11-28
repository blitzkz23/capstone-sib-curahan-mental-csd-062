package com.app.curahanmental.ui.welcome.onboarding

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.app.curahanmental.MainActivity
import com.app.curahanmental.R
import com.app.curahanmental.databinding.ActivityOnboardBinding
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.core.content.ContextCompat


class OnboardActivity : AppCompatActivity() {
    private lateinit var binding  : ActivityOnboardBinding
    private lateinit var onboardAdapter: OnboardAdapter
    private lateinit var onboardDots: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showButton(false)
        setOnboard()
        setInitialOnboardDots()
        setCurrentOnboardDots(0)

    }

    override fun onResume() {
        super.onResume()
        //TODO: set sharedPreferences to save user state
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
                    getString(R.string.header_onboarding1),
                    getString(R.string.overview_onboarding1)
                ),
                OnboardModel(
                    getDrawable(R.drawable.curahan_onboarding3),
                    getString(R.string.header_onboarding1),
                    getString(R.string.overview_onboarding1)
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
            val intent = Intent(this@OnboardActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.onboardBtnStart.setOnClickListener{
            val intent = Intent(this@OnboardActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun setInitialOnboardDots(){
        onboardDots = binding.onboardDots
        val identifier = arrayOfNulls<ImageView>(onboardAdapter.itemCount)
        val layoutParameter: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParameter.setMargins(16, 0, 0, 0)

        for(i in identifier.indices){
            identifier[i] = ImageView(applicationContext)
            identifier[i]?.let {
                it.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.onboard_dots_inactive))
                it.layoutParams = layoutParameter
                onboardDots.addView(it)
            }
        }
    }

     fun setCurrentOnboardDots(position: Int){
        val counts = onboardDots.childCount
        for (i in 0 until counts){
            val imageView = onboardDots.getChildAt(i) as ImageView
            if(i == position && i != 2){
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.onboard_dots_active))
                showButton(false)
            }else if(i == position && i == 2){
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.onboard_dots_active))
                showButton(true)
            }
            else{
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.onboard_dots_inactive))
            }
        }
    }

    private fun showButton(state: Boolean){
        binding.onboardBtnStart.visibility = if (state) VISIBLE else GONE
    }

}