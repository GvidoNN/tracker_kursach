package my.lovely.trucks.presentation.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import my.lovely.trucks.R
import my.lovely.trucks.databinding.FragmentTrackerBinding

@AndroidEntryPoint
class TrackerFragment: Fragment(R.layout.fragment_tracker) {

    private val trackerViewModel: TrackerViewModel by viewModels()
    private lateinit var binding: FragmentTrackerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrackerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trackerViewModel.dataResponce()

        trackerViewModel.dataResponse.observe(viewLifecycleOwner) { result ->
            if (result != null) {
                binding.tvHello.text = result[0].text
            } else {

            }
        }
    }
}