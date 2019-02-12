package com.kalum.monese.rockets.ui.view.feature.detail


import android.os.Bundle
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.kalum.monese.rockets.R
import com.kalum.monese.rockets.databinding.RocketDetailsFragmentBinding
import com.kalum.monese.rockets.ui.base.fragment.ScopedFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RocketDetailsFragment : ScopedFragment() {

    private lateinit var item: String
    private lateinit var navController: NavController
    private val ROCKET_ID: String = "id"
    private val viewModel: RocketDetailsViewModel by viewModel { parametersOf(item) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        arguments?.let {
            if (!it.isEmpty) {
                item = it.getString(ROCKET_ID) as String
            }
        }
        return DataBindingUtil.inflate<RocketDetailsFragmentBinding>(
                inflater,
                R.layout.rocket_details_fragment,
                container,
                false
        ).apply {
            model = viewModel
            setLifecycleOwner(this@RocketDetailsFragment)
            enterTransition = Fade()
            setupToolbar(toolbar)
        }.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindUI()
    }

    private fun bindUI() = launch {
        with(viewModel) {
            detailedRocket.await().observe(viewLifecycleOwner, Observer {
                loading.value = false
                setRocketDetails(it)
            })
        }
    }

    private fun setupToolbar(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

}
