package br.com.cwi.tcc_android.presentation.feature.home

import android.content.Intent
import android.os.Bundle
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.databinding.ActivityHomeBinding
import br.com.cwi.tcc_android.presentation.base.BaseBottomNavigation
import br.com.cwi.tcc_android.presentation.feature.cats.CatHostActivity
import br.com.cwi.tcc_android.presentation.feature.dogs.DogHostActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : BaseBottomNavigation() {

    private lateinit var binding: ActivityHomeBinding

    override val currentTab: Int = R.id.home_menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPetActions()
    }

    override fun getBottomNavigation(): BottomNavigationView = binding.contentBottomNavigation.root

    private fun setUpPetActions() {
        binding.contentDogs.setOnClickListener {
            val intent = Intent(this, DogHostActivity::class.java)
            startActivity(intent)
        }

        binding.contentCats.setOnClickListener {
            val intent = Intent(this, CatHostActivity::class.java)
            startActivity(intent)
        }
    }
}