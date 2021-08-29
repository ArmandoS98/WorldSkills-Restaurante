package com.aesc.restaurantews.ui.carta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aesc.restaurantews.databinding.CartaFragmentBinding

class CartaFragment : Fragment() {

    private lateinit var cartaViewModel: CartaViewModel
    private var _binding: CartaFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartaViewModel = ViewModelProvider(this).get(CartaViewModel::class.java)
        _binding = CartaFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = binding.tvTest
        cartaViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
    }
}