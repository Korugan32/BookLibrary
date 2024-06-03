package com.korugan.booklibrary.presentation.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import com.korugan.booklibrary.presentation.theme.BookLibraryTheme
import com.korugan.booklibrary.util.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookLibraryTheme {
                val launcher = rememberLauncherForActivityResult(
                    ActivityResultContracts.RequestPermission()
                ) { isGranted ->
                    if (isGranted) {
                        Toast.makeText(this, "İzin verildi", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "İzin reddedildi", Toast.LENGTH_SHORT).show()
                    }
                }
                if (!hasInternetPermission()) {
                    launcher.launch(Manifest.permission.INTERNET)
                }
                Navigation()
            }
        }
    }

    private fun hasInternetPermission(): Boolean {
        return applicationContext.checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
    }
}