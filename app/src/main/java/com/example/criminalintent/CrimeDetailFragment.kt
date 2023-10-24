package com.example.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.criminalintent.databinding.FragmentCrimeDetailBinding
import java.util.*

class CrimeDetailFragment : Fragment() {

    private var _binding : FragmentCrimeDetailBinding? = null
//    private lateinit var binding : ActivityCrimeDetailFragmentBinding
    private val binding
        get() = checkNotNull(_binding){
        "Cannot access binding because it is null.Is the view visible?"
    }
    private lateinit var crime: Crime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // we Do not call set Contentview
        // no inflating the view. this is the job of onCreateView
        crime = Crime(
            id = UUID.randomUUID(),
            title = "",
            date = Date(),
            isSolved = false
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCrimeDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // wire up EditText
        binding.apply {
            crimeTitle.doOnTextChanged { text, _, _, _ ->
                crime = crime.copy(title = text.toString())
            }
            // wire up the button
            crimeDate.apply{
                text = crime.date.toString()
                isEnabled = false
        }
            // wire up the checkbox
            crimeSolved.setOnCheckedChangeListener { _, isChecked ->
                crime = crime.copy(isSolved = isChecked)
            }

        }
    }

}










