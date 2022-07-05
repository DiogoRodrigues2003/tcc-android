package br.com.cwi.tcc_android.presentation.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import br.com.cwi.tcc_android.R
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseBottomNavigation : AppCompatActivity() {

    abstract val currentTab: Int

    abstract fun getBottomNavigation(): BottomNavigationView

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun onResume() {
        super.onResume()
        setUpBottomNavigationActions()
        selectCurrentTab()
    }

    private fun setUpBottomNavigationActions() {
        getBottomNavigation().setOnItemSelectedListener {
            if (it.itemId != this.currentTab) when (it.itemId) {
                R.id.home_menu -> {
                    //val intent = Intent(this, ProductsActivity::class.java)
                    //startActivity(intent)
                }
                R.id.dog_menu -> {
                    //val intent = Intent(this, FavoritesActivity::class.java)
                    //startActivity(intent)
                }
                R.id.cat_menu -> {
                    //val intent = Intent(this, BagActivity::class.java)
                    //startActivity(intent)
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun selectCurrentTab() {
        getBottomNavigation().selectedItemId = currentTab
    }
}