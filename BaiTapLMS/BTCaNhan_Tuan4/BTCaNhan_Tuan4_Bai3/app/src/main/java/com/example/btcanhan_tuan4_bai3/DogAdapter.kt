package com.example.btcanhan_tuan4_bai3

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class DogAdapter(val activity: Activity, val lst: List<DogData>) : ArrayAdapter<DogData>(activity, R.layout.item_layout, lst)  {
    override fun getCount(): Int {
        return lst.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.item_layout, null)

        val img = view.findViewById<ImageView>(R.id.images)

        img.setImageResource(lst[position].img)

        return view

    }

}