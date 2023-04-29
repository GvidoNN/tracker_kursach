package my.lovely.trucks.presentation.adding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import my.lovely.trucks.R
import my.lovely.trucks.databinding.FragmentAddingBinding


@AndroidEntryPoint
class AddingFragment: Fragment(R.layout.fragment_adding) {

    private val addingViewModel: AddingViewModel by viewModels()
    private lateinit var binding : FragmentAddingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addingViewModel.progressBar.observe(viewLifecycleOwner){
            binding.progressBar.isVisible = it == true
        }

        binding.btEditAddData.setOnClickListener {
            val trackNumber = binding.edEditAddTrackNumber.text.toString()
            val truck = binding.edEditAddTruck.text.toString()
            val state = binding.edEditAddState.text.toString()
            val date = binding.edEditAddDateStart.text.toString()
            addingViewModel.postAddTracker(trackNumber =  trackNumber, truck = truck, state = state, dateStart = date)
            addingViewModel.trackerAdd.observe(viewLifecycleOwner){
                if(it.done){
                    binding.edEditAddTrackNumber.setText("")
                    binding.edEditAddTruck.setText("")
                    binding.edEditAddState.setText("")
                    binding.edEditAddDateStart.setText("")

                } else {
                    binding.tvEditAddError.text = it.exception
                    binding.tvEditAddError.isVisible = true
                }
            }

        }

        binding.btEditDeleteData.setOnClickListener {
            val trackNumber = binding.edEditDeleteTrackNumber.text.toString()
            addingViewModel.postDeleteTracker(trackNumber = trackNumber)
            addingViewModel.trackerDelete.observe(viewLifecycleOwner){
                if(it.done){
                    binding.edEditDeleteTrackNumber.setText("")
                } else {
                    binding.tvEditDeleteError.text = it.exception
                    binding.tvEditDeleteError.isVisible = true
                }
            }
        }

        binding.btEditEditData.setOnClickListener {
            val trackNumber = binding.edEditEditTrackNumber.text.toString()
            val truck = binding.edEditEditTruck.text.toString()
            val state = binding.edEditEditState.text.toString()
            val date = binding.edEditEditDateStart.text.toString()
            addingViewModel.postUpdateTracker(trackNumber =  trackNumber, truck = truck, state = state, dateStart = date)
            addingViewModel.trackerUpdate.observe(viewLifecycleOwner){
                if(it.done){
                    binding.edEditEditTrackNumber.setText("")
                    binding.edEditEditTruck.setText("")
                    binding.edEditEditState.setText("")
                    binding.edEditEditDateStart.setText("")
                } else {
                    binding.tvEditEditError.text = it.exception
                    binding.tvEditEditError.isVisible = true
                }
            }
        }
    }
}