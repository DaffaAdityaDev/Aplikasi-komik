package com.example.aplikasi_komik

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView

class komik_detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_komik_detail)

        val komik = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("EXTRA_KOMIK", Komik::class.java)
        } else {
            intent.getParcelableExtra<Komik>("EXTRA_KOMIK")
        }

        showingHeroDetail(komik)
        showingRecommendation()
        showingDetail(komik)

    }

    private fun showingHeroDetail(komik: Komik?) {
        val komikTitle = komik?.title
        val komikImage = komik?.photo

        val ivImage = findViewById<ImageView>(R.id.iv_gambarKomik)
        val tvTitle = findViewById<TextView>(R.id.tv_judulKomik_detail)

        ivImage.setImageResource(komikImage!!)

        tvTitle.text = komikTitle
    }

    private fun showingRecommendation() {
        val ivRecommendImage = findViewById<ImageView>(R.id.iv_recommend_image)
        val ivRecommendImage2 = findViewById<ImageView>(R.id.iv_recommend_image2)
        val ivRecommendImage3 = findViewById<ImageView>(R.id.iv_recommend_image3)

        val dataImageKomikJepang = resources.obtainTypedArray(R.array.data_photo_komik_jepang)
        val dataImageKomikAmerika = resources.obtainTypedArray(R.array.data_photo_komik_amerika)

        for (i in 0..2) {
            val image = if (i % 2 == 0) {
                dataImageKomikAmerika.getResourceId(i, -1)
            } else {
                dataImageKomikJepang.getResourceId(i, -1)
            }

            when (i) {
                0 -> ivRecommendImage.setImageResource(image)
                1 -> ivRecommendImage2.setImageResource(image)
                2 -> ivRecommendImage3.setImageResource(image)
            }
        }

    }

    private fun showingDetail(komik: Komik?) {
        val komikData = komik?.description
        val tvDetail = findViewById<TextView>(R.id.tv_detail_komik)
        tvDetail.text = komikData
        tvDetail.movementMethod = ScrollingMovementMethod()
    }

}