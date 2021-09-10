package com.example.pokdex.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.DrawableCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.domain.models.pokemonlist.Pokemon
import com.example.pokdex.R
import com.example.pokdex.databinding.PokemonListItemBinding
import com.example.pokdex.util.capitalizeUtil
import com.example.pokdex.util.getColorFromImage
import com.example.pokdex.util.getImageUrlFromUrl

class PokedexPagingAdapter(
    private val listener: IAdapter
) : PagingDataAdapter<Pokemon, PokedexPagingAdapter.PokemonViewHolder>(
    POKEMON_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding =
            PokemonListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PokemonViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class PokemonViewHolder(private val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val pokemon = getItem(position)
                    if (pokemon != null) {
                        listener.onItemClick(pokemon)
                    }
                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(pokemon: Pokemon) {

            //Gets image url from pokemon url
            val drawableUrl = getImageUrlFromUrl(pokemon.url)

            binding.apply {
                //Loads image
                Glide.with(itemView)
                    .load(drawableUrl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_pokeball_black)
                        //Sets the background gradient color when image is loaded using the palette library
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            getColorFromImage(resource!!) { color ->
                                DrawableCompat.setTint(binding.recyclerViewItem.background, color.toArgb())
                            }
                            return false
                        }
                    })
                    .into(pokemonDrawable)


                pokemonName.text = pokemon.name.capitalizeUtil()
            }
        }
    }


    interface IAdapter {
        fun onItemClick(pokemon: Pokemon)
    }

    companion object {
        private val POKEMON_COMPARATOR = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) =
                oldItem.url == newItem.url

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) =
                oldItem == newItem
        }
    }
}
