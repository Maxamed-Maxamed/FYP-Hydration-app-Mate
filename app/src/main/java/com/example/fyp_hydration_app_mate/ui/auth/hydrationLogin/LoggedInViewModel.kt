package com.example.fyp_hydration_app_mate.ui.auth.hydrationLogin



import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.fyp_hydration_app_mate.helpers.FirebaseAuthManager
import com.google.firebase.auth.FirebaseUser



class LoggedInViewModel(app: Application) : AndroidViewModel(app) {


    var firebaseAuthManager : FirebaseAuthManager = FirebaseAuthManager(app)
    var liveFirebaseUser : MutableLiveData<FirebaseUser> = firebaseAuthManager.liveFirebaseUser
    var loggedOut : MutableLiveData<Boolean> = firebaseAuthManager.loggedOut




    fun logOut() {
        firebaseAuthManager.logOut()
    }
}