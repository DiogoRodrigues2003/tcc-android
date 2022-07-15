package br.com.cwi.tcc_android.presentation.feature.addPet

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.com.cwi.tcc_android.databinding.ActivityAddPetBinding
import br.com.cwi.tcc_android.presentation.constant.PetKeys
import br.com.cwi.tcc_android.presentation.feature.userPets.UserPetsHostActivity
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPetActivity : AppCompatActivity() {

    private val viewModel: AddPetViewModel by viewModel()

    private lateinit var binding: ActivityAddPetBinding

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                onActivityResult(it)
            }

        setUpViewModel(binding.root)
        setUpChoosePhotoClick()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    private fun onActivityResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK && result.data != null) {
            val data: Intent? = result.data
            val petPhoto = data?.data.toString()

            Glide.with(this).load(petPhoto).into(binding.ivPetPhoto)
            viewModel.photoUrl = petPhoto
        }
    }

    private fun setUpChoosePhotoClick() {
        binding.mbChoosePhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            if (intent.resolveActivity(packageManager) != null) {
                activityResultLauncher.launch(intent)
            }
        }
    }

    private fun setUpViewModel(view: View) {
        val breedName = intent.getStringExtra(PetKeys.NAME)
        val breedId = intent.getStringExtra(PetKeys.ID)
        val petType = intent.getStringExtra(PetKeys.TYPE)

        AddPetViewHolder(view, onPetSubmitClick = { petName ->
            viewModel.setPet(petName, breedName, breedId, petType)
        }).bind(this, breedName)

        viewModel.submit.observe(this) {
            navigateToUserPets()
        }
    }

    private fun navigateToUserPets() {
        val intent = Intent(this, UserPetsHostActivity::class.java)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}