package com.example.test_android.domain

sealed class QRCodeTabItem (val title: String) {
    object MyCode : TabItem("My Code")
    object ScanCode: TabItem("Scan Code")

}

val qrCodeTabItems = listOf(
    QRCodeTabItem.MyCode,
    QRCodeTabItem.ScanCode,
)