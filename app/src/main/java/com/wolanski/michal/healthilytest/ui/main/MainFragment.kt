package com.wolanski.michal.healthilytest.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wolanski.michal.healthilytest.R
import com.wolanski.michal.healthilytest.adapters.VenuesAdapter
import com.wolanski.michal.healthilytest.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: MainFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.main_fragment, container, false
        )

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        val adapter = VenuesAdapter()
        binding.results.adapter = adapter
        binding.results.layoutManager = LinearLayoutManager(requireContext())

        viewModel.venues.observe(viewLifecycleOwner, Observer { venues ->
            adapter.addData(venues.filter { !it.location.address.isNullOrEmpty() && it.categories.isNotEmpty() })

            val view = requireActivity().currentFocus
            view?.let {
                val inputMethodManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            AlertDialog.Builder(requireContext()).setTitle(R.string.error_title)
                .setMessage(R.string.error_information)
                .setPositiveButton(android.R.string.ok, null)
                .show()
        })

        binding.searchEditText.addTextChangedListener {
            search_button.isEnabled = it.toString().isNotBlank()
        }

        return binding.root
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}