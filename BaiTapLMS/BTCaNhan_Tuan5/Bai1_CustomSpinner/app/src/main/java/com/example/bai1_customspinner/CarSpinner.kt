package com.example.bai1_customspinner

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CarSpinner(val activity: Activity, val list: List<Car>) :
    ArrayAdapter<Car>(activity, R.layout.list_cacloaixe, list ) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        return initView(position, convertView, parent)

    }

    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View? {
        return initView(position, convertView, parent)
    }

    private fun initView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val context = activity.layoutInflater
        val rowView = context.inflate(R.layout.list_cacloaixe, parent, false)
        val img = rowView.findViewById<ImageView>(R.id.images)
        val txtName = rowView.findViewById<TextView>(R.id.txtName)
        txtName.text = list[position].name
        img.setImageResource(list[position].img)

        return rowView


    }
}