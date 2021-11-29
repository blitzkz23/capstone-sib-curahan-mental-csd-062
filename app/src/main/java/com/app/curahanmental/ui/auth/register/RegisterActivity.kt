package com.app.curahanmental.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
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

		// Check if the user are already authenticated
//		val currentUser = auth.currentUser
//		if (currentUser != null) {
//			Toast.makeText(this, "Kamu sudah login", Toast.LENGTH_SHORT).show()
//		}

		registerViewModel = ViewModelProvider(
			this,
			ViewModelProvider.NewInstanceFactory()
		)[RegisterViewModel::class.java]
		registerViewModel.authRes.observe(this@RegisterActivity) {
			if (it.isSuccessful) {
				startActivity(Intent(this@RegisterActivity, LoginActivity::class.java).also {
					Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
				})
				finish()
			} else {
				Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
			}
		}
		registerActivityBinding.registerButton.setOnClickListener {
			registerUser()
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
				registerEdFirstname.error = getString(R.string.error_firstname)
			}
			if (lastName.isEmpty()) {
				registerEdLastname.error = getString(R.string.error_lastname)
			}

			if (email.isEmpty()) {
				registerEdEmail.error = getString(R.string.error_email)
			} else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
				registerEdEmail.error = getString(R.string.error_email2)
			} else if (password.isEmpty()) {
				registerEdPassword.error = getString(R.string.error_password)
			} else if (password.length < 6) {
				registerEdPassword.error = getString(R.string.error_password2)
			} else {
				registerViewModel.signUpUser(firstName, lastName, email, password)
				Toast.makeText(
					this@RegisterActivity,
					"Akun telah berhasil dibuat.",
					Toast.LENGTH_SHORT
				).show()
				finish()
			}

			registerToLoginFlow.setOnClickListener {
				startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
				finish()
			}
		}
	}

}