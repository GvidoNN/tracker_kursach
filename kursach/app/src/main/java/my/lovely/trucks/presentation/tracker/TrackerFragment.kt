package my.lovely.trucks.presentation.tracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

        binding.btTrackerDone.setOnClickListener {
            binding.linearTrackerHide.isVisible = true
            binding.edTrack.setText("")
            binding.linearTrackerHideInfo.isVisible = false
        }

        binding.btTrack.setOnClickListener {
            val trackNumber = binding.edTrack.text.toString()
            trackerViewModel.postTrackerInfo(trackNumber = trackNumber)
            trackerViewModel.trackerInfoResponse.observe(viewLifecycleOwner){
                if(it.done){
                    binding.tvTrackError.isVisible = false
                    binding.linearTrackerHide.isVisible = false
                    binding.linearTrackerHideInfo.isVisible = true
                    binding.tvTrackNumber.text = it.trackNumber
                    binding.tvTrackerTruck.text = it.truck
                    binding.tvTrackerState.text = it.state
                    binding.tvTrackerDate.text = it.dateStart
                } else {
                    binding.tvTrackError.text = it.exception
                    binding.tvTrackError.isVisible = true
                    Log.d("MyLog","Ошибка ${it.exception}")
                }
            }

        }

        trackerViewModel.progressBar.observe(viewLifecycleOwner){
            if (it == true) {
                binding.progressBar.isVisible = true
            } else {
                binding.progressBar.isVisible = false
            }
        }
    }
}