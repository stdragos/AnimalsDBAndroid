package com.example.homework2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework2.adapters.AnimalListAdapter
import com.example.homework2.data.models.AnimalDBModel
import com.example.homework2.data.models.ContinentDBModel
import com.example.homework2.data.repositories.AnimalRepository
import com.example.homework2.data.repositories.ContinentRepository
import com.example.homework2.helpers.extensions.logErrorMessage
import com.example.homework2.models.AnimalModel

class AnimalAddListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.btn_add_animal)
        button.setOnClickListener { doAddAnimal() }
        setupAnimalList()
    }

    private fun doAddAnimal() {
        var animalName = view?.findViewById<EditText>(R.id.et_animal_name)?.text.toString()
        var continentName = view?.findViewById<EditText>(R.id.et_continent_name)?.text.toString()

        if (animalName.isEmpty() || continentName.isEmpty() || animalName.isBlank() || continentName.isBlank()) {
            AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage("Please fill in all fields")
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .show()
            return
        }

        ContinentRepository.getContinentByName(continentName) { continent ->
            if (continent == null) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Error")
                    .setMessage("Continent does not exist")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()

            } else {
                AnimalRepository.getAnimalByName(animalName) { animal ->
                    if (animal != null) {
                        AnimalRepository.updateAnimal(animal.id, continent.id) {
                            "Updated Animal".logErrorMessage()
                            setupAnimalList()
                        }
                    } else {
                        AnimalRepository.insertAnimal(
                            AnimalDBModel(
                                name = animalName,
                                continentId = continent.id
                            )
                        ) {
                            "Inserted Animal".logErrorMessage()
                            setupAnimalList()
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupAnimalList() {
        val layoutManager = LinearLayoutManager(context)

        AnimalRepository.getAllAnimalsWithContinent { animalList ->
            view?.findViewById<RecyclerView>(R.id.rv_animal_list)?.apply {
                this.layoutManager = layoutManager

                this.adapter = AnimalListAdapter(animalList as MutableList<AnimalModel>)
                this.adapter?.notifyDataSetChanged()
            }
        }
    }
}