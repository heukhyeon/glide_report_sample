package kr.heukhyeon.glidereportsample

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kr.heukhyeon.glidereportsample.databinding.FragmentABinding
import kr.heukhyeon.glidereportsample.databinding.FragmentBBinding

class AfterFragmentWithoutGlide : Fragment() {

    lateinit var binding: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.transition_example)
        postponeEnterTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageViewA.transitionName = "Test"
        binding.imageViewA.setImageResource(R.drawable.sample)
        binding.buttonA.setOnClickListener {
            (activity as SampleActivity).onClickReturnButton(this, binding.imageViewA)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            startPostponedEnterTransition()
        }, 500L)
    }
}