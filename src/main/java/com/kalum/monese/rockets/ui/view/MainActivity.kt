package com.kalum.monese.rockets.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.kalum.monese.rockets.R
import com.kalum.monese.rockets.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private val model: MainActivityViewModel by lazy {
        ViewModelProviders.of(this)[MainActivityViewModel::class.java].apply {
            binding.model = this
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupNavController()
    }

    private fun setupNavController() {
        navController = findNavController(this, R.id.nav_host_fragment)

        navController.addOnNavigatedListener { _, destination ->
            when (destination.id) {
                R.id.rocketListFragment -> setupFragmentTitleAndSubtitle()
            }
        }
    }


    private fun setupFragmentTitleAndSubtitle() {
        binding.model?.run {
            with(this) {
                setTitle(R.string.app_name)
            }
        }
    }


    private fun setupBinding() {
        with(binding) {
            model = this@MainActivity.model
            setLifecycleOwner(this@MainActivity)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

}
