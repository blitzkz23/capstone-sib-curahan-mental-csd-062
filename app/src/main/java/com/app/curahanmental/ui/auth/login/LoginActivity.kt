package com.app.curahanmental.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.curahanmental.ui.main.MainActivity
import com.app.curahanmental.databinding.ActivityLoginBinding
import com.app.curahanmental.ui.auth.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
	private val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
	private lateinit var loginViewModel: LoginViewModel


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		loginViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[LoginViewModel::class.java]
		loginViewModel.authRes.observe(this){
			if (it.isSuccessful){
				startActivity(Intent(this@LoginActivity, MainActivity::class.java).also {
					Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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
				Toast.makeText(this, "Silahkan Isi E-mail Anda", Toast.LENGTH_SHORT).show()
				binding.textInputLayout.requestFocus()
				return@setOnClickListener
			}
			if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
				Toast.makeText(this, "Alamat E-mail Tidak Sesuai", Toast.LENGTH_SHORT).show()
				binding.textInputLayout.requestFocus()
				return@setOnClickListener
			}
			if (pass.isEmpty() || pass.length < 6){
				Toast.makeText(this, "Password Anda Tidak Sesuai", Toast.LENGTH_SHORT).show()
				binding.textInputLayout1.requestFocus()
				return@setOnClickListener
			}

			loginViewModel.signInUser(email, pass)
		}

		binding.signinToRegisterFlow.setOnClickListener{
			startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
		}
	}
}