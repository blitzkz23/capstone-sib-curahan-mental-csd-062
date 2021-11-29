package com.app.curahanmental.ui.auth.register

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.curahanmental.MainActivity
import com.app.curahanmental.R
import com.app.curahanmental.data.source.local.entity.UserEntity
import com.app.curahanmental.databinding.ActivityRegisterBinding
import com.app.curahanmental.ui.auth.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

	private var registerActivityBinding: ActivityRegisterBinding? = null
	private lateinit var auth: FirebaseAuth
	private lateinit var database: DatabaseReference

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		registerActivityBinding = ActivityRegisterBinding.inflate(layoutInflater)
		setContentView(registerActivityBinding?.root)

		// Auth and database initialization
		auth = Firebase.auth
		database = Firebase.database.reference

		// Check if the user are already authenticated
//		val currentUser = auth.currentUser
//		if (currentUser != null) {
//			Toast.makeText(this, "Kamu sudah login", Toast.LENGTH_SHORT).show()
//		}

		registerActivityBinding?.registerButton?.setOnClickListener {
			registerUser()
		}
	}

	private fun registerUser() {
		registerActivityBinding?.apply {
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
				auth.createUserWithEmailAndPassword(email, password)
					.addOnCompleteListener { task ->
						if (task.isSuccessful) {
							// Sign in success, update UI with the signed-in user's information
							Log.d(TAG, "signInWithEmail:success")
							val user = UserEntity(
								firstName = firstName,
								lastName = lastName,
								email = email,
								password = password
							)
							auth.currentUser?.let { database.child("users").child(it.uid).setValue(user) }
							startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
							finish()
						} else {
							// If sign in fails, display a message to the user.
							Log.w(TAG, "signInWithEmail:failure", task.exception)
							Toast.makeText(
								baseContext, "Authentication failed.",
								Toast.LENGTH_SHORT
							).show()
						}
					}
			}
			registerToLoginFlow.setOnClickListener {
				startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
				finish()
			}
		}
	}

}