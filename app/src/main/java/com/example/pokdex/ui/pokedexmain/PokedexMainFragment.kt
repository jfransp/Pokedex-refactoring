package com.example.pokdex.ui.pokedexmain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokdex.R
import com.example.pokdex.adapters.PokedexPagingAdapter
import com.example.pokdex.data.models.pokemonlist.Pokemon
import com.example.pokdex.databinding.FragmentPokedexMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexMainFragment : Fragment(R.layout.fragment_pokedex_main), PokedexPagingAdapter.IAdapter {

    private val viewModel: PokedexMainViewModel by viewModels()

    private var _binding: FragmentPokedexMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PokedexPagingAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPokedexMainBinding.bind(view)

        setupRecyclerView()

    }

    private fun setupRecyclerView() {

        adapter = PokedexPagingAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(context, 3)
        }

        viewModel.pokemonListFlow.observe(viewLifecycleOwner) { pagingData ->
            adapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        }


    }

    override fun onItemClick(pokemon: Pokemon) {
        val action = PokedexMainFragmentDirections.actionPokedexMainToPokemonDetails(pokemon)
        view?.findNavController()?.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
