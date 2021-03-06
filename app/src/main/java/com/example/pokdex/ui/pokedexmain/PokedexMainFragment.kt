package com.example.pokdex.ui.pokedexmain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.View.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.models.pokemonlist.Pokemon
import com.example.domain.util.ErrorEntity
import com.example.pokdex.R
import com.example.pokdex.adapters.PokedexPagingAdapter
import com.example.pokdex.databinding.FragmentPokedexMainBinding
import com.example.pokdex.util.LoadState
import com.example.pokdex.util.selectErrorMessageFromErrorEntity
import org.koin.androidx.viewmodel.ext.android.viewModel


class PokedexMainFragment : Fragment(R.layout.fragment_pokedex_main), PokedexPagingAdapter.IAdapter {

    private val viewModel: PokedexMainViewModel by viewModel()

    private var _binding: FragmentPokedexMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PokedexPagingAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPokedexMainBinding.bind(view)

        setupLoadStateHandler()

        setupRecyclerView()

    }

    private fun setupRecyclerView() {

        adapter = PokedexPagingAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(context, 3)
        }

        viewModel.pokemonListLiveData.observe(viewLifecycleOwner) { pagingData ->
            adapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        }

    }

    private fun setupLoadStateHandler() {
        viewModel.loadStateObservable.observe(viewLifecycleOwner) { loadState ->
            if (loadState is LoadState.ERROR) {
                showErrorMessage(loadState.error)
            }
        }
    }

    private fun showErrorMessage(error: ErrorEntity) {
        binding.apply {
            recyclerView.visibility = INVISIBLE
            errorMessage.text = context?.let { selectErrorMessageFromErrorEntity(it,error) }
            errorMessage.visibility = VISIBLE
        }
    }

    override fun onItemClick(pokemon: Pokemon) {
        val action = PokedexMainFragmentDirections.actionPokedexMainToPokemonDetails(
            pokemonName = pokemon.name,
            isSavedPokemon = false
        )
        view?.findNavController()?.navigate(action)
    }   

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
