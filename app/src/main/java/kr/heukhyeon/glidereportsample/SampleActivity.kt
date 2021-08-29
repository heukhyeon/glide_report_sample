package kr.heukhyeon.glidereportsample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kr.heukhyeon.glidereportsample.databinding.ActivityMainBinding

class SampleActivity : FragmentActivity() {

    lateinit var binding: ActivityMainBinding
    private var glideEnable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toggleGlideUsage()
        binding.glideToggle.setOnClickListener {
            toggleGlideUsage()
        }
    }

    fun onClickMoveButton(fragment: Fragment, sharedView: View) {
        val nextFragment =
            if (fragment is BeforeFragmentWithGlide) AfterFragmentWithGlide() else AfterFragmentWithoutGlide()
        supportFragmentManager.beginTransaction()
            .addSharedElement(sharedView, sharedView.transitionName)
            .hide(fragment)
            .add(R.id.fragmentContainer, nextFragment, nextFragment.javaClass.canonicalName)
            .commit()
    }

    fun onClickReturnButton(fragment: Fragment, sharedView: View) {
        val beforeFragment =
            if (fragment is AfterFragmentWithGlide) BeforeFragmentWithGlide::class.java else BeforeFragmentWithoutGlide()::class.java
        supportFragmentManager.beginTransaction()
            .addSharedElement(sharedView, sharedView.transitionName)
            .remove(fragment)
            .show(supportFragmentManager.findFragmentByTag(beforeFragment.canonicalName)!!)
            .commit()
    }

    fun toggleGlideUsage() {
        glideEnable = !glideEnable

        val fragment = if (glideEnable) BeforeFragmentWithGlide() else BeforeFragmentWithoutGlide()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment, fragment.javaClass.canonicalName)
            .commit()

        if (glideEnable) binding.glideToggle.text = "Toggle Glide - Current : Enable"
        else binding.glideToggle.text = "Toggle Glide - Current : Disable"

    }
}