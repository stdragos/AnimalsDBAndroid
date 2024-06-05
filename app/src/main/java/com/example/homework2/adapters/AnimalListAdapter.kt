package com.example.homework2.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework2.ApplicationController
import com.example.homework2.R
import com.example.homework2.data.repositories.AnimalRepository
import com.example.homework2.helpers.extensions.logErrorMessage
import com.example.homework2.models.AnimalModel

class AnimalListAdapter(
    private val items: MutableList<AnimalModel>
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

        holder.itemView.findViewById<Button>(R.id.btn_delete_animal).setOnClickListener { doDeleteAnimal(holder.itemView, position) }

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


    @SuppressLint("NotifyDataSetChanged")
    private fun updateItems(newItems: List<AnimalModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    private fun doDeleteAnimal(view: View, position: Int) {
        val name = view.findViewById<TextView>(R.id.tv_animal_name)?.text.toString()

        AnimalRepository.getAnimalByName(name) { animal ->
            if (animal != null) {

                AnimalRepository.deleteAnimal(animal) {
                    items.removeAt(position)
                    notifyItemRemoved(position)

                    for (i in position until items.size) {
                        notifyItemChanged(i)
                    }
                }
            }
        }
    }

}