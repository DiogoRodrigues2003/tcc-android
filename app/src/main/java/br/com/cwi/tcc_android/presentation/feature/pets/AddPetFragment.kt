package br.com.cwi.tcc_android.presentation.feature.pets

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import br.com.cwi.tcc_android.databinding.FragmentAddPetBinding
import br.com.cwi.tcc_android.presentation.feature.userPets.UserPetsHostActivity
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPetFragment: Fragment() {

    private val viewModel: AddPetViewModel by viewModel()

    private lateinit var binding: FragmentAddPetBinding

    private val breedId by lazy {
        arguments?.getString("BREED_ID")
    }

    private val breedName by lazy {
        arguments?.getString("BREED_NAME")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel(view)
    }

    private fun setUpViewModel(view: View) {
        AddPetViewHolder(view, onPetSubmitClick = { petName ->
            if (petName.isNotEmpty()) {
                viewModel.setPet(petName, breedName, breedId)
                navigateToUserPets()
            }
        }).bind(this, breedName)

    }

    private fun navigateToUserPets() {
        val intent = Intent(activity, UserPetsHostActivity::class.java)
        startActivity(intent)
    }
}