package com.ufps.movil2021_1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterMedicine (var contex: Context?, val dataSet: ArrayList<MedicineEntity>, recurso : Int) :
    RecyclerView.Adapter<AdapterMedicine.MedicineViewHolder>() {
    class MedicineViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val titulo: TextView
        val descripcion: TextView
        val sintomas: TextView
        val imagen: ImageView
        init {
            titulo = view.findViewById(R.id.titulo)
            descripcion = view.findViewById(R.id.descripcion)
            sintomas = view.findViewById(R.id.sintomas)
            imagen= view.findViewById(R.id.imagen)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_medicine,parent,false)
        return MedicineViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.titulo.text = dataSet[position].titulo
        holder.descripcion.text = dataSet[position].descripcion
        holder.sintomas.text = dataSet[position].sintomas
        Picasso.get().load(dataSet[position].imagen).into(holder.imagen)
    }

    override fun getItemCount() = dataSet.size
}