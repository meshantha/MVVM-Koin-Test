package com.kalum.monese.rockets.ui.view.feature.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kalum.monese.rockets.R
import com.kalum.monese.rockets.data.local.entity.RocketItem
import com.kalum.monese.rockets.databinding.RocketListFragmentBinding
import com.kalum.monese.rockets.ui.adapter.RocketsAdapter
import com.kalum.monese.rockets.ui.base.fragment.ScopedFragment
import kotlinx.android.synthetic.main.rocket_list_fragment.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class RocketListFragment : ScopedFragment() {

    private val viewModel by viewModel<RocketListViewModel>()
    private val ROCKET_ID: String = "id"
    private val adapter: RocketsAdapter by lazy {
        RocketsAdapter { course: Any ->
            detailItemClicked(course)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return DataBindingUtil.inflate<RocketListFragmentBinding>(
                inflater,
                R.layout.rocket_list_fragment,
                container,
                false
        ).apply {
            model = viewModel
            setLifecycleOwner(this@RocketListFragment)
        }.root
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rockets_rv.adapter = adapter
        rockets_rv.setHasFixedSize(true)
        rockets_rv.layoutManager = GridLayoutManager(context, 1)

        bindUI()
    }

    private fun bindUI() = launch {

        with(viewModel) {

            rockets.await().observe(viewLifecycleOwner, Observer {
                if (it == null) return@Observer
                loading.value = false
                adapter.rocketList = it
            })
        }
    }

    private fun detailItemClicked(details: Any) {
        val item = (details as RocketItem)
        val args = Bundle()
        args.putString(ROCKET_ID, item.rocketid);
        findNavController().navigate(R.id.rocketDetailFragment, args, null, null)
    }

}
