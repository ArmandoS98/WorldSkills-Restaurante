package com.aesc.restaurantews.ui.fragments.pedidos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aesc.restaurantews.R

class PedidosFragment : Fragment() {

    companion object {
        fun newInstance() = PedidosFragment()
    }

    private lateinit var viewModel: PedidosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pedidos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PedidosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}