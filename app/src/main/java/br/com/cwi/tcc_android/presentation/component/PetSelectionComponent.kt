package br.com.cwi.tcc_android.presentation.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.cwi.tcc_android.R
import br.com.cwi.tcc_android.databinding.ComponentPetSelectionBinding

class PetSelectionComponent : ConstraintLayout {

    private var binding: ComponentPetSelectionBinding =
        ComponentPetSelectionBinding.inflate(LayoutInflater.from(context), this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        context.obtainStyledAttributes(
            attrs, R.styleable.PetSelectionComponent, 0, 0
        ).run {
            binding.run {
                petType = getString(
                    R.styleable.PetSelectionComponent_component_pet_type
                )
                clContent.background = getDrawable(
                    R.styleable.PetSelectionComponent_component_pet_image
                )
            }
            recycle()
        }
    }

    private var petType: String? = null
        set(value) {
            field = value
            binding.tvPetSelection.text = value
        }
}