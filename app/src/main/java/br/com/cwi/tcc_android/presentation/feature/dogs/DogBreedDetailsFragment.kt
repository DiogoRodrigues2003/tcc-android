package br.com.cwi.tcc_android.presentation.feature.dogs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.cwi.tcc_android.databinding.FragmentBreedDetailsBinding
import br.com.cwi.tcc_android.presentation.constant.PetKeys
import br.com.cwi.tcc_android.presentation.feature.addPet.AddPetActivity
import br.com.cwi.tcc_android.presentation.feature.pets.BreedDetailsViewHolder
import br.com.cwi.tcc_android.presentation.feature.pets.PetImageViewHolder
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DogBreedDetailsFragment: Fragment() {

    private lateinit var binding: FragmentBreedDetailsBinding

    private val breedId by lazy {
        arguments?.getString(PetKeys.ID)
    }

    private val breedName by lazy {
        arguments?.getString(PetKeys.NAME)
    }

    private val viewModel: DogViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBreedDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel(view)
    }

    private fun setUpViewModel(view: View) {
        viewModel.breedDetails.observe(viewLifecycleOwner) {
            BreedDetailsViewHolder(view, onAddPetClick = { id, name, petType ->
                navigateToAddPet(id, name, petType)
            }).bind(this, it)
       }

        viewModel.dogImage.observe(viewLifecycleOwner) {
            PetImageViewHolder(view, onNewImageClick = {
                viewModel.fetchDogImage(breedId)
            }).bind(this, it)
        }

        viewModel.fetchDogImage(breedId)
        viewModel.fetchBreedDetails(breedName)
    }

    private fun navigateToAddPet(id: String, name: String, petType: String) {
        val intent = Intent(context, AddPetActivity::class.java)
            .putExtra(PetKeys.ID, id)
            .putExtra(PetKeys.NAME, name)
            .putExtra(PetKeys.TYPE, petType)
        startActivity(intent)
    }
}