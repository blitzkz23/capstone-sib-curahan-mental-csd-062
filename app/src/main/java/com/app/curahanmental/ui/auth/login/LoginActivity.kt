package com.app.curahanmental.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.curahanmental.R
import com.app.curahanmental.databinding.ActivityLoginBinding
import com.app.curahanmental.ui.auth.register.RegisterActivity
import com.app.curahanmental.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {
	private val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
	private lateinit var loginViewModel: LoginViewModel


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		loginViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[LoginViewModel::class.java]

		loginViewModel.authRes.observe(this){
			if (it.isSuccessful){
				showProgressBar(true)
				startActivity(Intent(this@LoginActivity, MainActivity::class.java).also { intent ->
					intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
				})
				finish()
			}else{
				Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
			}
		}

		binding.signinBtn.setOnClickListener{
			val email = binding.signinEdEmail.text.toString().trim()
			val pass = binding.signinEdPassword.text.toString().trim()

			if (email.isEmpty()){
				binding.apply {
					signinEdEmail.error = getString(R.string.error_email)
					textInputLayout.requestFocus()
				}
				return@setOnClickListener
			}
			if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
				binding.apply {
					signinEdEmail.error = getString(R.string.error_email2)
					textInputLayout.requestFocus()
				}
				return@setOnClickListener
			}
			if (pass.isEmpty()){
				binding.apply {
					signinEdPassword.error = getString(R.string.error_password)
					textInputLayout1.requestFocus()
				}
				return@setOnClickListener
			}
			if (pass.length < 6){
				binding.apply {
					signinEdPassword.error = getString(R.string.error_password2)
					textInputLayout1.requestFocus()
				}
				return@setOnClickListener
			}

			loginViewModel.signInUser(email, pass)
		}

		binding.signinToRegisterFlow.setOnClickListener{
			startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
		}
	}

	private fun showProgressBar(loading: Boolean){
		binding.progressBar.visibility = if (loading) VISIBLE else GONE
	}
}