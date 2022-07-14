package com.example.cecs453
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
//import java.util.concurrent.TimeUnit

/**
 * Created by harshitdwivedi on 14/03/18.
 */
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkCurrentUser()
    }

    private fun checkCurrentUser() {
        // [START check_current_user]
        val user = Firebase.auth.currentUser
        if (user != null) {
            // User is signed in
            println("Authenticated")
        } else {
            // No user is signed in
            println("Unauthenticated")
        }
        // [END check_current_user]
    }

    /*
    private fun getUserProfile() {
        // [START get_user_profile]
        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }
        // [END get_user_profile]
    }

    private fun getProviderData() {
        // [START get_provider_data]
        val user = Firebase.auth.currentUser
        user?.let {
            for (profile in it.providerData) {
                // Id of the provider (ex: google.com)
                val providerId = profile.providerId

                // UID specific to the provider
                val uid = profile.uid

                // Name, email address, and profile photo Url
                val name = profile.displayName
                val email = profile.email
                val photoUrl = profile.photoUrl
            }
        }
        // [END get_provider_data]
    }

    private fun updateProfile() {
        // [START update_profile]
        val user = Firebase.auth.currentUser

        val profileUpdates = userProfileChangeRequest {
            displayName = "Jane Q. User"
            photoUri = Uri.parse("https://example.com/jane-q-user/profile.jpg")
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User profile updated.")
                }
            }
        // [END update_profile]
    }

    private fun updateEmail() {
        // [START update_email]
        val user = Firebase.auth.currentUser

        user!!.updateEmail("user@example.com")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User email address updated.")
                }
            }
        // [END update_email]
    }

    private fun updatePassword() {
        // [START update_password]
        val user = Firebase.auth.currentUser
        val newPassword = "SOME-SECURE-PASSWORD"

        user!!.updatePassword(newPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User password updated.")
                }
            }
        // [END update_password]
    }

    private fun sendPasswordReset() {
        // [START send_password_reset]
        val emailAddress = "user@example.com"

        Firebase.auth.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
            }
        // [END send_password_reset]
    }

    private fun deleteUser() {
        // [START delete_user]
        val user = Firebase.auth.currentUser!!

        user.delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User account deleted.")
                }
            }
        // [END delete_user]
    }


    private fun unlink(providerId: String) {

        // [START auth_unlink]
        Firebase.auth.currentUser!!.unlink(providerId)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Auth provider unlinked from account
                    // ...
                }
            }
        // [END auth_unlink]
    }

    private fun getEmailCredentials() {
        val email = ""
        val password = ""
        // [START auth_email_cred]
        val credential = EmailAuthProvider.getCredential(email, password)
        // [END auth_email_cred]
    }

    private fun signOut() {
        // [START auth_sign_out]
        Firebase.auth.signOut()
        // [END auth_sign_out]
    }

    private fun updateUI(user: FirebaseUser?) {
        // No-op
    }
        */
}