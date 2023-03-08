package com.example.aplikasi_komik

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class recyclerAdapterKomik(
    private val activity : MainActivity,
    private var dataKomikList: Komik_Data_List) :
RecyclerView.Adapter<recyclerAdapterKomik.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemJudulKomik: TextView = itemView.findViewById(R.id.tv_judulKomik)
        val itemDeskripsiKomik: TextView = itemView.findViewById(R.id.tv_deskripsiKomik)
        val itemGambarKomik: ImageView = itemView.findViewById(R.id.tv_gambarKomik)

        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition

                val komikData = Intent(activity, komik_detail::class.java)
                val komik = Komik(dataKomikList.judulKomik[position],
                    dataKomikList.deskripsiKomik[position],
                    dataKomikList.imagesKomik[position],
                    position)

                komikData.putExtra("EXTRA_KOMIK", komik)

                activity.startActivity(komikData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.list_komik, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title: String = dataKomikList.judulKomik[position]
        val detail: String = dataKomikList.deskripsiKomik[position]
        val image: Int = dataKomikList.imagesKomik[position]

        holder.itemJudulKomik.text = title
        holder.itemDeskripsiKomik.text = detail
        holder.itemGambarKomik.setImageResource(image)
    }

    override fun getItemCount(): Int {
        return dataKomikList.judulKomik.size
    }



}