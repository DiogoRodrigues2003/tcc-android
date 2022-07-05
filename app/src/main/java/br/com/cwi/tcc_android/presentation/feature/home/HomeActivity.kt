package br.com.cwi.tcc_android.presentation.feature.home

import android.os.Bundle
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.databinding.ActivityHomeBinding
import br.com.cwi.tcc_android.presentation.base.BaseBottomNavigation
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
        /*binding.contentCoffees.setOnClickListener {
            val intent = Intent(this, CoffeeHostActivity::class.java)
            startActivity(intent)
        }

        binding.contentMachines.setOnClickListener {
            val intent = Intent(this, MachineActivity::class.java)
            startActivity(intent)
        }

        binding.contentAccessories.setOnClickListener {
            val intent = Intent(this, AccessoryActivity::class.java)
            startActivity(intent)
        }
         */
    }
}