package com.example.pokdex.ui.mypokemons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.pokdex.R
import com.example.pokdex.databinding.FragmentMyPokemonsBinding

class MyPokemonsFragment : Fragment(R.layout.fragment_my_pokemons) {

    private var _binding: FragmentMyPokemonsBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMyPokemonsBinding.bind(view)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}