package com.example.homework2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework2.R
import com.example.homework2.helpers.extensions.logErrorMessage
import com.example.homework2.models.AnimalModel

class AnimalListAdapter(
    private val items: List<AnimalModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        "onCreateViewHolder".logErrorMessage("AnimalListAdapter")
        val view = inflater.inflate(R.layout.animal_item, parent, false)

        return AnimalViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val animalModel = items[position]
        (holder as AnimalViewHolder).bind(animalModel)
        "onBindViewHolder; position = $position".logErrorMessage("ProductListAdapter")
    }

    inner class AnimalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val animalNameTextView: TextView
        private val animalContinentTextView: TextView

        init {
            animalNameTextView = view.findViewById(R.id.tv_animal_name)
            animalContinentTextView = view.findViewById(R.id.tv_animal_continent)
        }

        fun bind(animal: AnimalModel) {
            animalNameTextView.text = animal.name
            animalContinentTextView.text = animal.continent
        }
    }

}