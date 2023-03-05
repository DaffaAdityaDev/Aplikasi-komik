package com.example.aplikasi_komik

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Komik_Data_List (
    var judulKomik: MutableList<String>,
    var deskripsiKomik: MutableList<String>,
    var imagesKomik: MutableList<Int>,
) : Parcelable