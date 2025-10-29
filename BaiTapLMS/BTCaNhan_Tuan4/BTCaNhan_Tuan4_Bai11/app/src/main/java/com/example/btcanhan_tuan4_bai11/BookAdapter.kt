package com.example.btcanhan_tuan4_bai11

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView



class BookAdapter(val activity: Activity, val lstBook: List<BookData>) : ArrayAdapter<BookData>(activity, R.layout.list_layout, lstBook) {
    override fun getCount(): Int {
        return lstBook.size
    }

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = activity.layoutInflater
        val rowView = layoutInflater.inflate(R.layout.list_layout, null, true)
        val images = rowView.findViewById<ImageView>(R.id.images)
        val txtBookName = rowView.findViewById<TextView>(R.id.txtBookName)


        val bookData = lstBook[position]
        images.setImageResource(bookData.image)
        txtBookName.text = bookData.bookName

        return rowView


    }


}


