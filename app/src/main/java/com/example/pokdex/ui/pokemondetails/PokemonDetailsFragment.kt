package com.example.pokdex.ui.pokemondetails

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokdex.R
import com.example.pokdex.data.models.pokemondetails.PokemonDetails
import com.example.pokdex.data.models.pokemondetails.Type
import com.example.pokdex.databinding.FragmentPokemonDetailsBinding
import com.example.pokdex.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment(R.layout.fragment_pokemon_details) {

    private val args: PokemonDetailsFragmentArgs by navArgs()

    private val viewModel: PokemonDetailsViewModel by viewModels()

    private var _binding: FragmentPokemonDetailsBinding? = null
    private val binding get() = _binding!!


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPokemonDetailsBinding.bind(view)

        viewModel.pokemonLiveData.observe(viewLifecycleOwner, { pokemonDetails ->
            populateScreenData(pokemonDetails)
        })

        viewModel.fetchData()

        setupBackButton()

        setupSavePokemonButton()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun populateScreenData(data: PokemonDetails) {

        //Separating data
        val imageUrl = getImageUrlFromId(data.id)
        val pokemonIdString = "#${data.id}"
        val capitalizedName = data.name.capitalizeUtil()
        val types = data.types
        val pokemonWeight = (data.weight.toFloat() / 10).toString() + "Kg"
        val pokemonHeight = (data.height.toFloat() / 10).toString() + "M"

        //Function for setting up type views
        setupTypes(types, binding)

        binding.apply {

            //Set pokemon image with glide and header background color with palette
            Glide.with(this@PokemonDetailsFragment)
                .load(imageUrl)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_pokeball_black)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        getColorFromImage(resource!!) { color ->
                            DrawableCompat.setTint(headerBackground.background, color.toArgb())
                            activity?.window?.statusBarColor = color.toArgb()
                        }
                        return false
                    }
                })
                .into(pokemonImage)

            //Sets general data
            pokemonId.text = pokemonIdString

            pokemonName.text = capitalizedName

            weightValue.text = pokemonWeight

            heightValue.text = pokemonHeight

            //Sets stats progress views
            setupProgressViews(data)
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupTypes(types:List<Type>, binding: FragmentPokemonDetailsBinding) {
        when (types.size) {
            1 -> {
                binding.apply {
                    typeSlotOne.apply {
                        visibility = View.VISIBLE
                        fragmentTypesColorSetter(1, context = context, types[0], binding)
                        text = types[0].type.name
                    }
                }
            }
            2 -> {
                binding.apply {
                    typeSlotOne.apply {
                        visibility = View.VISIBLE
                        fragmentTypesColorSetter(1, context = context, types[0], binding)
                        text = types[0].type.name
                    }
                    typeSlotTwo.apply {
                        visibility = View.VISIBLE
                        fragmentTypesColorSetter(2, context = context, types[1], binding)
                        text = types[1].type.name
                    }

                }
            }
        }
    }

    private fun setupProgressViews(data: PokemonDetails) {
        val hpValue = data.stats[0].base_stat
        val atkValue = data.stats[1].base_stat
        val defValue = data.stats[2].base_stat
        val spdValue = data.stats[5].base_stat

        binding.apply {
            hpProgressView.progress = hpValue.toFloat()
            hpProgressView.labelText = hpValue.toString()

            atkProgressView.progress = atkValue.toFloat()
            atkProgressView.labelText = atkValue.toString()

            defProgressView.progress = defValue.toFloat()
            defProgressView.labelText = defValue.toString()

            spdProgressView.progress = spdValue.toFloat()
            spdProgressView.labelText = spdValue.toString()

        }
    }

    private fun setupSavePokemonButton() {
        binding.savePokemonButton.setOnClickListener {
            viewModel.pokemonLiveData.value?.let { pokemon ->
                viewModel.savePokemon(pokemon)
                context?.toast("${pokemon.name} saved!")
            }
        }
    }

    private fun setupBackButton() {
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
