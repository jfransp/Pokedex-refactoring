package com.example.pokdex.ui.mypokemons

import MyPokemonsRecyclerViewAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.util.ErrorEntity
import com.example.pokdex.R
import com.example.pokdex.databinding.FragmentMyPokemonsBinding
import com.example.pokdex.util.LoadState
import com.example.pokdex.util.selectErrorMessageFromErrorEntity
import com.google.android.material.snackbar.Snackbar
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

        setupLoadStateHandler()

        setupRecyclerView()

        viewModel.pokemonListLiveData.observe(viewLifecycleOwner) { savedPokemonList ->
            if (savedPokemonList.isEmpty()) {
                binding.emptyRvMessage.visibility = View.VISIBLE
            } else {
                binding.emptyRvMessage.visibility = View.GONE
            }
        }

        viewModel.fetchData()

    }

    private fun setupRecyclerView() {

        adapter = MyPokemonsRecyclerViewAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                val pokemon = adapter.currentList[position]
                viewModel.deletePokemon(pokemon.name)
                view?.let {
                    Snackbar.make(it, "Pokemon deleted", Snackbar.LENGTH_LONG).apply {
                        setAction("Undo") {
                            viewModel.undoDeletion(pokemon)
                        }
                        show()
                    }
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerView)
        }


        viewModel.pokemonListLiveData.observe(viewLifecycleOwner) { savedPokemonList ->
            adapter.submitList(savedPokemonList)
        }
    }

    override fun onItemClick(pokemon: PokemonDetails) {
        val action = MyPokemonsFragmentDirections.actionMyPokemonsFragmentToPokemonDetails(
            pokemonName = pokemon.name,
            isSavedPokemon = true
        )
        view?.findNavController()?.navigate(action)
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
            recyclerView.visibility = View.INVISIBLE
            errorMessage.text = context?.let { selectErrorMessageFromErrorEntity(it, error) }
            errorMessage.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
