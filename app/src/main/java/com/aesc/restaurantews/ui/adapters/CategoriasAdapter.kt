package com.aesc.restaurantews.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.aesc.restaurantews.R
import com.aesc.restaurantews.Util.Utils
import com.aesc.restaurantews.extensions.loadByURL
import com.aesc.restaurantews.provider.services.models.Dato
import kotlinx.android.synthetic.main.item_categorias.view.*

class CategoriasAdapter : RecyclerView.Adapter<CategoriasAdapter.ViewHolder>() {

    private var datos: List<Dato> = listOf()

    fun ViewGroup.inflate(@LayoutRes layoutResID: Int, attachRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutResID, this, attachRoot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.item_categorias, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Utils.logsUtils("img ${datos[position].url_imagen}")
        holder.itemView.imgCategoria.loadByURL(datos[position].url_imagen!!)
        holder.itemView.tvNombre.text = datos[position].nombre
    }

    override fun getItemCount() = datos.size

    fun setCategories(datos: List<Dato>) {
        this.datos = datos
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            //Le hagamos click a una cardview
            val item: Dato = datos[adapterPosition]
            Utils.logsUtils("Clicked -> $item")
        }
    }
}