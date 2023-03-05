package com.example.aplikasi_komik

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Komik(
    var title: String,
    var description: String,
    var photo: Int,
) : Parcelable
