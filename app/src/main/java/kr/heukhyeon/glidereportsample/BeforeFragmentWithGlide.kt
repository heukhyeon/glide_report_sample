package kr.heukhyeon.glidereportsample

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kr.heukhyeon.glidereportsample.databinding.FragmentABinding

class BeforeFragmentWithGlide : Fragment() {

    lateinit var binding: FragmentABinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentABinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.transition_example)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageViewA.transitionName = "Test"
        Glide.with(binding.imageViewA).load(R.drawable.sample).into(binding.imageViewA)
        binding.buttonA.setOnClickListener {
            (activity as SampleActivity).onClickMoveButton(this, binding.imageViewA)
        }
    }
}