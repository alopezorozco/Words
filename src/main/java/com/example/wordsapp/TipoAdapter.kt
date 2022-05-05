package com.example.wordsapp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class TipoAdapter (context: Context) :
    RecyclerView.Adapter<TipoAdapter.TipoViewHolder>() {

    class TipoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipoViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TipoViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}