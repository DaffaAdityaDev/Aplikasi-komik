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
        val komikPosition = komik!!.position

        val ivImage = findViewById<ImageView>(R.id.iv_gambarKomik)
        val tvTitle = findViewById<TextView>(R.id.tv_judulKomik_detail)
        val tvKomikCapter = findViewById<TextView>(R.id.tv_total_capter)
        val tvRating = findViewById<TextView>(R.id.tv_rating)
        val tvYear = findViewById<TextView>(R.id.tv_year)
        val tvdata_genre = findViewById<TextView>(R.id.tv_category)

        val dataTotalCapterJepang = resources.getStringArray(R.array.data_total_capter_komik_jepang)
        val dataRatingJepang = resources.getStringArray(R.array.data_rating_jepang)
        val dataYearJepang = resources.getStringArray(R.array.data_year_jepang)
        val dataCategoryJepang = resources.getStringArray(R.array.data_genre_jepang)

        val dataTotalCapterAmerika = resources.getStringArray(R.array.data_total_capter_komik_amerika)
        val dataRatingAmerika = resources.getStringArray(R.array.data_rating_amerika)
        val dataYearAmerika = resources.getStringArray(R.array.data_year_amerika)
        val dataCategoryAmerika = resources.getStringArray(R.array.data_genre_amerika)

        ivImage.setImageResource(komikImage!!)

        val target = komikPosition / 2
        tvTitle.text = komikTitle

        if(komikPosition % 2 == 0) {
            tvKomikCapter.text = "Total Capter:" + dataTotalCapterAmerika[target]
            tvRating.text = "rating:" + dataRatingAmerika[target]
            tvYear.text = dataYearAmerika[target]
            tvdata_genre.text = dataCategoryAmerika[target]
        } else {
            tvKomikCapter.text = "Total Capter:" + dataTotalCapterJepang[target]
            tvRating.text = "rating:" + dataRatingJepang[target]
            tvYear.text = dataYearJepang[target]
            tvdata_genre.text = dataCategoryJepang[target]
        }

    }

    private fun showingRecommendation() {
        val ivRecommendImage = findViewById<ImageView>(R.id.iv_recommend_image)
        val ivRecommendImage2 = findViewById<ImageView>(R.id.iv_recommend_image2)
        val ivRecommendImage3 = findViewById<ImageView>(R.id.iv_recommend_image3)

        val tvRecommend = findViewById<TextView>(R.id.tv_recommend)
        val tvRecommend2 = findViewById<TextView>(R.id.tv_recommend2)
        val tvRecommend3 = findViewById<TextView>(R.id.tv_recommend3)

        val dataImageKomikJepang = resources.obtainTypedArray(R.array.data_photo_komik_jepang)
        val dataImageKomikAmerika = resources.obtainTypedArray(R.array.data_photo_komik_amerika)

        val dataTitleKomikJepang = resources.getStringArray(R.array.komik_jepang)
        val dataTitleKomikAmerika = resources.getStringArray(R.array.komik_amerika)

        for (i in 0..2) {

            val image = if (i % 2 == 0) {
                dataImageKomikAmerika.getResourceId(i, -1)
            } else {
                dataImageKomikJepang.getResourceId(i, -1)
            }

            when (i) {
                0 -> {
                    ivRecommendImage.setImageResource(image)
                    tvRecommend.text = dataTitleKomikAmerika[i]
                }
                1 -> {
                    ivRecommendImage2.setImageResource(image)
                    tvRecommend2.text = dataTitleKomikJepang[i]
                }
                2 -> {
                    ivRecommendImage3.setImageResource(image)
                    tvRecommend3.text = dataTitleKomikAmerika[i]
                }
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