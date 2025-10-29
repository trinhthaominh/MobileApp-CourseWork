package com.example.bai8_2lvnangcao

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(val activity: Activity, val list: List<OutData>) : ArrayAdapter<OutData>(activity, R.layout.list_item, list) {
//    Alt + Insert

    override fun getCount(): Int {
        return list.size // vẽ lên view hết tat cả các dòng
    }

    @SuppressLint("ViewHolder")
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val context = activity.layoutInflater
        val row = context.inflate(R.layout.list_item, parent, false)

        val image = row.findViewById<ImageView>(R.id.images)
        val title = row.findViewById<TextView>(R.id.title)
        val desc = row.findViewById<TextView>(R.id.desc)

        image.setImageResource(list[position].image)
        desc.text = list[position].desc
        title.text = list[position].title

        return row
    }
}