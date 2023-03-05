package com.example.aplikasi_komik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var judulKomik = mutableListOf<String>()
    private var deskripsiKomik = mutableListOf<String>()
    private var imagesKomik = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postToList()
        showKomik()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about -> {
                val about = Intent(this, About::class.java)
                startActivity(about)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showKomik() {
        var dataKomikList = Komik_Data_List(judulKomik, deskripsiKomik, imagesKomik)
        val rvkomik = findViewById<RecyclerView>(R.id.rvKomik)
        rvkomik.layoutManager = LinearLayoutManager(this)
        rvkomik.adapter = recyclerAdapterKomik(this, dataKomikList)
    }

    private fun addToList(username: String, username_data: String, images: Int) {
        this.judulKomik.add(username)
        this.deskripsiKomik.add(username_data)
        this.imagesKomik.add(images)
    }

    private fun postToList() {
        val dataKomikJepang = resources.getStringArray(R.array.komik_jepang)
        val dataKomikAmerika = resources.getStringArray(R.array.komik_amerika)

        val dataImageKomikJepang = resources.obtainTypedArray(R.array.data_photo_komik_jepang)
        val dataImageKomikAmerika = resources.obtainTypedArray(R.array.data_photo_komik_amerika)

        val dataDetailKomikJepang = resources.getStringArray(R.array.data_detail_komik_jepang)
        val dataDetailKomikAmerika = resources.getStringArray(R.array.data_detail_komik_amerika)

        val totalKomik : Int = ((dataKomikJepang.size) + (dataKomikAmerika.size))

        for (i in 0 until totalKomik) {
            val target = i / 2
            if(i % 2 == 0)
                addToList(dataKomikAmerika[target], dataDetailKomikAmerika[target], dataImageKomikAmerika.getResourceId(target, -1))
            else
                addToList(dataKomikJepang[target], dataDetailKomikJepang[target], dataImageKomikJepang.getResourceId(target, -1))

        }
    }
}