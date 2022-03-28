package com.test.searchapp.feature_search.presentation.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.searchapp.R
import com.test.searchapp.core.adapter.MarginItemDecoration
import com.test.searchapp.core.model.ViewState
import com.test.searchapp.databinding.ActivitySearchBinding
import com.test.searchapp.feature_search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchActivity : AppCompatActivity(R.layout.activity_search) {

    private val viewModel: SearchViewModel by viewModel()

    private val binding by viewBinding(ActivitySearchBinding::bind)

    private val tracksAdapter = TracksAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        observe()
        viewModel.onCreate(this)
    }

    private fun setupViews() {
        setupSearchView()
        setupTrackRecyclerView()
    }

    private fun setupTrackRecyclerView() {
        binding.tracksRv.adapter = tracksAdapter
        binding.tracksRv.addItemDecoration(
            MarginItemDecoration(
                bottomSpaceHeight = resources?.getDimension(R.dimen.list_view_vertical_margin)
                    ?.toInt() ?: 0,
                leftRightSpaceHeight = resources?.getDimension(R.dimen.list_view_bottom_margin)
                    ?.toInt() ?: 0,
                topSpaceHeight = resources?.getDimension(R.dimen.list_view_top_margin)?.toInt()
                    ?: 0,
            )
        )
    }

    private fun setupSearchView() {
        val id: Int = binding.searchView.context
            .resources
            .getIdentifier("android:id/search_src_text", null, null)
        val textView = binding.searchView.findViewById<TextView>(id)
        textView.setTextAppearance(R.style.SearchInputTextAppearance)
    }

    private fun observe() {
        viewModel.viewStateLiveData.observe(this) { viewState ->
            when (viewState) {
                ViewState.Empty -> {
                    binding.tracksRv.isVisible = false
                    binding.progressBar.isVisible = false
                    binding.emptyLayout.isVisible = true
                }
                is ViewState.Error -> {
                    binding.tracksRv.isVisible = false
                    binding.progressBar.isVisible = false
                    binding.emptyLayout.isVisible = false
                }
                ViewState.Loading -> {
                    binding.tracksRv.isVisible = false
                    binding.progressBar.isVisible = true
                    binding.emptyLayout.isVisible = false
                }
                is ViewState.Success -> {
                    binding.tracksRv.isVisible = true
                    binding.progressBar.isVisible = false
                    binding.emptyLayout.isVisible = false
                    binding.searchView.queryHint = getString(
                        R.string.search_input_hint,
                        viewState.data.size.toString()
                    )
                    tracksAdapter.setData(viewState.data)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart(this)
    }

}