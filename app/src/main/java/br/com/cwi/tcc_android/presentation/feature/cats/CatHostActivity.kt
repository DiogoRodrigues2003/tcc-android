package br.com.cwi.tcc_android.presentation.feature.cats

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.databinding.ActivityCatHostBinding
import br.com.cwi.tcc_android.presentation.base.BaseBottomNavigation
import br.com.cwi.tcc_android.presentation.extension.visibleOrGone
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatHostActivity : BaseBottomNavigation() {

    private val viewModel: CatViewModel by viewModel()

    private lateinit var binding: ActivityCatHostBinding

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(binding.navHostContainer.id) as NavHostFragment)
            .findNavController()
    }

    override val currentTab: Int = R.id.cat_menu

    override fun getBottomNavigation(): BottomNavigationView = binding.contentBottomNavigation.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpViewModel()
        setUpNavController()
    }

    private fun setUpNavController() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.breedFragment) {
                binding.contentBottomNavigation.bottomNavigation.visibility = View.VISIBLE
                supportActionBar?.title = getString(R.string.txt_cats)

            } else {
                binding.contentBottomNavigation.bottomNavigation.visibility = View.GONE
                supportActionBar?.title = getString(R.string.txt_cats)
            }
        }
    }

    private fun setUpViewModel() {
        viewModel.loading.observe(this@CatHostActivity) { isLoading ->
            binding.viewLoading.root.visibleOrGone(isLoading)
        }

        viewModel.error.observe(this@CatHostActivity) { hasError ->
            binding.viewError.root.visibleOrGone(hasError)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}