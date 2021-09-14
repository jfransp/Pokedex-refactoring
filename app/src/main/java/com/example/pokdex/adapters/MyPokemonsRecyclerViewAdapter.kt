import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.domain.models.pokemondetails.PokemonDetails
import com.example.domain.models.pokemondetails.Type
import com.example.pokdex.R
import com.example.pokdex.databinding.SavedPokemonItemBinding
import com.example.pokdex.util.capitalizeUtil
import com.example.pokdex.util.getColorFromImage
import com.example.pokdex.util.savedPokemonItemColorSetter

class MyPokemonsRecyclerViewAdapter(
    private val listener: IAdapter
): ListAdapter<PokemonDetails, MyPokemonsRecyclerViewAdapter.PokemonViewHolder>(
    POKEMON_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = SavedPokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class PokemonViewHolder(private val binding: SavedPokemonItemBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
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
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(pokemon: PokemonDetails) {
            val idText = "#${pokemon.id}"
            val nameText = pokemon.name.capitalizeUtil()
            val imageUrl =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemon.id}.png"

            binding.apply {

                setupTypes(pokemon.types, this)

                //Loads image
                Glide.with(itemView)
                    .load(imageUrl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_pokeball_black)
                    //Sets the background color when image is loaded using the palette library
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
                                DrawableCompat.setTint(
                                    binding.recyclerViewItem.background,
                                    color.toArgb()
                                )
                            }
                            return false
                        }
                    })
                    .into(pokemonDrawable)

                pokemonName.text = nameText

                pokemonId.text = idText
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupTypes(types: List<Type>, binding: SavedPokemonItemBinding) {
        when (types.size) {
            1 -> {
                binding.apply {
                    typeSlotOne.apply {
                        visibility = View.VISIBLE
                        savedPokemonItemColorSetter(1, context = context, types[0], binding)
                        text = types[0].type.name
                    }
                    typeSlotTwo.apply {
                        visibility = View.GONE
                    }
                }
            }
            2 -> {
                binding.apply {
                    typeSlotOne.apply {
                        visibility = View.VISIBLE
                        savedPokemonItemColorSetter(1, context = context, types[0], binding)
                        text = types[0].type.name
                    }
                    typeSlotTwo.apply {
                        visibility = View.VISIBLE
                        savedPokemonItemColorSetter(2, context = context, types[1], binding)
                        text = types[1].type.name
                    }

                }
            }
        }
    }


    interface IAdapter {
        fun onItemClick(pokemon: PokemonDetails)
    }

    companion object {
        private val POKEMON_COMPARATOR = object : DiffUtil.ItemCallback<PokemonDetails>() {
            override fun areItemsTheSame(oldItem: PokemonDetails, newItem: PokemonDetails) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PokemonDetails, newItem: PokemonDetails) =
                oldItem == newItem
        }
    }
}
