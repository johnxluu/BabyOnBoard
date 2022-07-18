package com.example.cecs453
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * Created by harshitdwivedi on 14/03/18.
 */
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val email:String = "exit21sb@gmail.com"
    private val password:String = ""
    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = Firebase.auth
        // [END initialize_auth]
        setContentView(R.layout.activity_main)
    }

    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser == null) {
            signIn(email, password)
        }
    }
    // [END on_start_check_user]
    private fun checkCurrentUser():Boolean{
        // [START check_current_user]
        val user = Firebase.auth.currentUser
        return user!=null
    }
    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(baseContext, "Empty email or password",
                Toast.LENGTH_SHORT).show()
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    println(user.toString())
                    Toast.makeText(baseContext, "Authentication Succeed.",
                        Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        // [END sign_in_with_email]
    }
}