package uk.co.bbc.elections.ui.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import uk.co.bbc.elections.databinding.FragmentResultsBinding

class ResultsFragment : Fragment() {

    private val viewModel by viewModels<ResultsViewModel>()

    private var _binding: FragmentResultsBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding: FragmentResultsBinding get() = _binding!!

    private val resultsAdapter: ResultsAdapter = ResultsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareViews()
        observeViewModel()
    }

    private fun prepareViews() {
        with(binding.resultsRv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = resultsAdapter
        }

        binding.refreshBtn.setOnClickListener {
            viewModel.refresh()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { renderViewState(it) }
        }
    }

    private fun renderViewState(viewState: ResultsViewState) {
        if (viewState is ResultsViewState.Loaded) {
            resultsAdapter.submitList(viewState.results)
        }
    }

    companion object {
        fun newInstance() = ResultsFragment()
    }
}