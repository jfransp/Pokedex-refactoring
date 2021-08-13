package com.example.pokdex.ui.mypokemons

import MyPokemonsRecyclerViewAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokdex.R
import com.example.pokdex.data.models.pokemondetails.PokemonDetails
import com.example.pokdex.databinding.FragmentMyPokemonsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPokemonsFragment : Fragment(R.layout.fragment_my_pokemons), MyPokemonsRecyclerViewAdapter.IAdapter {

    private val viewModel: MyPokemonsViewModel by viewModels()

    private var _binding: FragmentMyPokemonsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MyPokemonsRecyclerViewAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMyPokemonsBinding.bind(view)

        setupRecyclerView()

    }

    private fun setupRecyclerView() {

        adapter = MyPokemonsRecyclerViewAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
        }

        viewModel.pokemonLiveData.observe(viewLifecycleOwner) { savedPokemonList ->
            adapter.submitList(savedPokemonList)
        }
    }

    override fun onItemClick(pokemon: PokemonDetails) {
        val action = MyPokemonsFragmentDirections.actionMyPokemonsFragmentToPokemonDetails(
            pokemonId = pokemon.id,
            isFetchFromRemote = false
        )
        view?.findNavController()?.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
