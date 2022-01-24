package com.app.curahanmental.ui.auth.register

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.curahanmental.R
import com.app.curahanmental.databinding.ActivityRegisterBinding
import com.app.curahanmental.ui.auth.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

	private val registerActivityBinding: ActivityRegisterBinding by lazy {
		ActivityRegisterBinding.inflate(
			layoutInflater
		)
	}
	private lateinit var registerViewModel: RegisterViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(registerActivityBinding.root)

		registerViewModel = ViewModelProvider(
			this,
			ViewModelProvider.NewInstanceFactory()
		)[RegisterViewModel::class.java]
		showProgressBar(false)

		registerActivityBinding.registerButton.setOnClickListener {
			registerUser()
			registerViewModel.authRes.observe(this@RegisterActivity) {
				if (it.isSuccessful) {
					Toast.makeText(
						this@RegisterActivity,
						getString(R.string.account_created),
						Toast.LENGTH_SHORT
					).show()
					startActivity(Intent(this@RegisterActivity, LoginActivity::class.java).also { intent ->
						intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
					})
					finish()
				} else {
					Log.e(ContentValues.TAG, "createUserWithEmail:failure", it.exception)
					Toast.makeText(this, getString(R.string.account_existed), Toast.LENGTH_SHORT).show()
					showProgressBar(false)
				}
			}
		}
		registerActivityBinding.registerToLoginFlow.setOnClickListener {
			startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
			finish()
		}
	}

	private fun registerUser() {
		registerActivityBinding.apply {
			val firstName = registerEdFirstname.text.toString().trim()
			val lastName = registerEdLastname.text.toString().trim()
			val email = registerEdEmail.text.toString().trim()
			val password = registerEdPassword.text.toString().trim()

			// Validate user form
			if (firstName.isEmpty()) {
				textInputLayout.requestFocus()
				registerEdFirstname.error = getString(R.string.error_firstname)
			}
			if (lastName.isEmpty()) {
				textInputLayout1.requestFocus()
				registerEdLastname.error = getString(R.string.error_lastname)
			}

			if (email.isEmpty()) {
				textInputLayout2.requestFocus()
				registerEdEmail.error = getString(R.string.error_email)
			} else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
				textInputLayout2.requestFocus()
				registerEdEmail.error = getString(R.string.error_email2)
			}

			when {
				password.isEmpty() -> {
					textInputLayout3.requestFocus()
					registerEdPassword.error = getString(R.string.error_password)
				}
				password.length < 6 -> {
					textInputLayout3.requestFocus()
					registerEdPassword.error = getString(R.string.error_password2)
				}
				else -> {
					registerViewModel.signUpUser(firstName, lastName, email, password)
					showProgressBar(true)
				}
			}
		}
	}

	private fun showProgressBar(loading: Boolean){
		registerActivityBinding.progressBar.visibility = if (loading) VISIBLE else GONE
	}
}