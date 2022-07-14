package br.com.cwi.tcc_android.presentation.feature.dogs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.databinding.ActivityDogHostBinding
import br.com.cwi.tcc_android.presentation.base.BaseBottomNavigation
import br.com.cwi.tcc_android.presentation.extension.visibleOrGone
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class DogHostActivity : BaseBottomNavigation() {

    private val viewModel: DogViewModel by viewModel()

    private lateinit var binding: ActivityDogHostBinding

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(binding.navHostContainer.id) as NavHostFragment)
            .findNavController()
    }

    override val currentTab: Int = R.id.dog_menu

    override fun getBottomNavigation(): BottomNavigationView = binding.contentBottomNavigation.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpViewModel()
        setUpNavController()
    }

    private fun setUpNavController() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.breedFragment) {
                binding.contentBottomNavigation.bottomNavigation.visibility = View.VISIBLE
                supportActionBar?.title = getString(R.string.txt_dogs)

            } else {
                binding.contentBottomNavigation.bottomNavigation.visibility = View.GONE
                supportActionBar?.title = getString(R.string.txt_breed_details)
            }
        }
    }

    private fun setUpViewModel() {
        viewModel.loading.observe(this@DogHostActivity) { isLoading ->
            binding.viewLoading.root.visibleOrGone(isLoading)
        }

        viewModel.error.observe(this@DogHostActivity) { hasError ->
            binding.viewError.root.visibleOrGone(hasError)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}