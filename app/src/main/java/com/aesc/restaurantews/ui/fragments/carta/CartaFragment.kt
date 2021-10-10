package com.aesc.restaurantews.ui.fragments.carta

import android.graphics.drawable.ClipDrawable.VERTICAL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aesc.restaurantews.Util.Utils
import com.aesc.restaurantews.databinding.CartaFragmentBinding
import com.aesc.restaurantews.provider.services.models.Dato
import com.aesc.restaurantews.ui.adapters.CategoriasAdapter
import com.aesc.visaappk.provider.services.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.carta_fragment.*

class CartaFragment : Fragment() {
    lateinit var viewModels: MainViewModel
    private lateinit var cartaViewModel: CartaViewModel
    private var _binding: CartaFragmentBinding? = null
    private lateinit var adapter: CategoriasAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartaViewModel = ViewModelProvider(this).get(CartaViewModel::class.java)
        _binding = CartaFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModels = ViewModelProvider(this).get(MainViewModel::class.java)
        logic()
    }

    private fun logic() {
        var status = false
        viewModels.responseCategorias.observe(viewLifecycleOwner, {
            if (!status) {
                Utils.logsUtils("SUCCESS $it")
                recyclerviewInit(it.datos)
            }
        })

        viewModels.errorMessage.observe(viewLifecycleOwner, {
            if (!status) {
                Utils.logsUtils("ERROR $it")
            }
        })

        viewModels.loading.observe(viewLifecycleOwner, {
            status = it
            if (it) {
                Utils.logsUtils("SHOW")
            } else {
                Utils.logsUtils("HIDE")
            }
        })
        viewModels.categorias()
    }

    private fun recyclerviewInit(datos: List<Dato>) {
        adapter = CategoriasAdapter()
        adapter.setCategories(datos)
        recyclerview.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        recyclerview.adapter = adapter
    }

}
