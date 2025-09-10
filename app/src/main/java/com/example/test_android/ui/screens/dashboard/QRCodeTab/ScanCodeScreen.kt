package com.example.test_android.ui.screens.dashboard.QRCodeTab


import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


@Composable
fun RequestCameraPermission(onGranted: () -> Unit) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onGranted()
        } else {
            Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.CAMERA)
    }
}



@Composable
fun QRScannerScreen(
    onResult: (String) -> Unit // 👈 define callback properly
) {
    val launcher = rememberLauncherForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            onResult(result.contents) // 👈 this is the missing `onResult`
        }
    }

    // Request camera permission before scanning
    RequestCameraPermission {
        val options = ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            setPrompt("Scan a QR Code")
            setCameraId(0) // back camera
            setBeepEnabled(true)
            setBarcodeImageEnabled(true)
        }
        launcher.launch(options)
    }
}

@Composable
fun ScanCodeScreen(Controller: NavController)  {
    val context = LocalContext.current

    QRScannerScreen { scannedText ->
        // handle scanned QR code here
        println("QR Code: $scannedText")
        Toast.makeText(context, "Scanded value is $scannedText", Toast.LENGTH_SHORT).show()
    }
}