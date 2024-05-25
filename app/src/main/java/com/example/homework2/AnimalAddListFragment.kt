package com.example.homework2

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
import com.example.homework2.models.AnimalModel

class AnimalAddListFragment : Fragment() {

    val appDao = ApplicationController.instance.appDatabase

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

        if(animalName.isEmpty() || continentName.isEmpty() || animalName.isBlank() || continentName.isBlank()){
            AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage("Please fill in all fields")
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .show()
            return
        }

        if(appDao.getContinentsDao().getContinentByName(continentName) == null) {
            AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage("Continent does not exist")
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .show()
            return
        }

        val animal = appDao.getAnimalsDao().getAnimalByName(animalName)
        var x = 0L
        if(animal != null) {
            appDao.getAnimalsDao().update(animal.id, appDao.getContinentsDao().getContinentByName(continentName).id)
            x = appDao.getContinentsDao().getContinentByName(continentName).id
        } else {
            appDao.getAnimalsDao().insertAnimal(AnimalDBModel(name=animalName, continentId = appDao.getContinentsDao().getContinentByName(continentName).id))
        }

        setupAnimalList()
    }

    private fun setupAnimalList() {
        val layoutManager = LinearLayoutManager(context)

        val animalListDB = appDao.getAnimalsDao().getAllAnimals()

        val animalList = animalListDB.map {
            AnimalModel(
                it.name,
                appDao.getContinentsDao().getContinentById(it.continentId).name
            )
        }.toMutableList()

        view?.findViewById<RecyclerView>(R.id.rv_animal_list)?.apply {
            this.layoutManager = layoutManager
            this.adapter = AnimalListAdapter(animalList)
        }
    }
}