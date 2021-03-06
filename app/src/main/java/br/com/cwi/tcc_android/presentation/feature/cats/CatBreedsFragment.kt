package br.com.cwi.tcc_android.presentation.feature.cats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.databinding.FragmentBreedBinding
import br.com.cwi.tcc_android.domain.entity.Breed
import br.com.cwi.tcc_android.presentation.constant.PetKeys
import br.com.cwi.tcc_android.presentation.constant.PetTypes
import br.com.cwi.tcc_android.presentation.feature.pets.BreedAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CatBreedsFragment : Fragment() {

    private lateinit var binding: FragmentBreedBinding

    private val viewModel: CatViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBreedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
        setUpPagination()
    }

    private fun setUpViewModel() {
        viewModel.breeds.observe(viewLifecycleOwner) { list ->
            setUpCoffeeRecyclerView(list)
        }
        viewModel.fetchBreeds()
    }

    private fun setUpPagination() {
        binding.ivNextPage.setOnClickListener {
            viewModel.nextPage()
            viewModel.fetchBreeds()
        }
        binding.ivPreviousPage.setOnClickListener {
            viewModel.previousPage()
            viewModel.fetchBreeds()
        }
    }

    private fun setUpCoffeeRecyclerView(list: List<Breed>) {
        binding.rvBreeds.apply {
            adapter = BreedAdapter(context, list, PetTypes.CAT,
                onBreedClick = { id, name ->
                    navigateToBreedDetails(id, name)
                })
        }
    }

    private fun navigateToBreedDetails(id: String, name: String) {
        findNavController().navigate(
            R.id.breedDetailsFragment,
            bundleOf(
                Pair(PetKeys.ID, id),
                Pair(PetKeys.NAME, name)
            )
        )
    }
}