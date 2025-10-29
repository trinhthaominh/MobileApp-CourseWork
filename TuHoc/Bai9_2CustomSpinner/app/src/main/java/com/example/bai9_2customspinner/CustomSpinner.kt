package com.example.bai9_2customspinner

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomSpinner(val activity: Activity, val lst2: List<OutData>) : ArrayAdapter<OutData>(activity, R.layout.list_monan, lst2) {
    override fun getCount(): Int {
        return lst2.size
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

    private fun initView(position: Int,
                         convertView: View?,
                         parent: ViewGroup): View {
        val context = activity.layoutInflater
        val rowView = context.inflate(R.layout.list_monan, parent, false)
        val img = rowView.findViewById<ImageView>(R.id.images)
        val txtMonAn = rowView.findViewById<TextView>(R.id.txtMonAn)

        img.setImageResource(lst2[position].img)
        txtMonAn.text = lst2[position].mieuta
        return rowView

    }
}