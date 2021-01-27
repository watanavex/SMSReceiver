package tech.watanave.smsreceiver

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        Log.w("", "requestPermission $result")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermission.launch(Manifest.permission.RECEIVE_SMS)
    }
}