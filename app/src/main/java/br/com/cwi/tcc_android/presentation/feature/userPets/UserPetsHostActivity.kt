package br.com.cwi.tcc_android.presentation.feature.userPets

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.databinding.ActivityUserPetsHostBinding
import br.com.cwi.tcc_android.presentation.base.BaseBottomNavigation
import br.com.cwi.tcc_android.presentation.extension.visibleOrGone
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserPetsHostActivity: BaseBottomNavigation() {

    private val viewModel: UserPetsViewModel by viewModel()

    private lateinit var binding: ActivityUserPetsHostBinding

    override val currentTab: Int = R.id.user_pets_menu

    override fun getBottomNavigation(): BottomNavigationView = binding.contentBottomNavigation.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPetsHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        viewModel.loading.observe(this@UserPetsHostActivity) { isLoading ->
            binding.viewLoading.root.visibleOrGone(isLoading)
        }

        viewModel.error.observe(this@UserPetsHostActivity) { hasError ->
            binding.viewError.root.visibleOrGone(hasError)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}