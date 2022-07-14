package br.com.cwi.tcc_android.presentation.feature.userPets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.data.database.entity.PetEntity
import br.com.cwi.tcc_android.databinding.FragmentUserPetsListBinding
import br.com.cwi.tcc_android.presentation.constant.PetKeys
import br.com.cwi.tcc_android.presentation.constant.PetTypes
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserPetsListFragment: Fragment() {

    private lateinit var binding: FragmentUserPetsListBinding

    private val viewModel: UserPetsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserPetsListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        viewModel.pets.observe(viewLifecycleOwner) { list ->
            setUpPetsRecyclerView(list)
        }
        viewModel.fetchPets()
    }

    private fun setUpPetsRecyclerView(list: List<PetEntity>) {
        binding.rvPets.apply {
            adapter = UserPetsAdapter(context, list, onDeleteClick = { pet ->
                pet?.let { viewModel.deletePet(it) }
            })
        }
    }
}