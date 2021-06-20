package com.devvailonge.flip.features.onboarding

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.devvailonge.flip.HostActivity
import com.devvailonge.flip.R
import com.devvailonge.flip.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager
        val list = OnboardingSteps.values()
        configButtons(this, list, viewPager)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) = with(binding) {
                super.onPageSelected(position)
                buttonNext.isGone = (position + 1) == list.size
                buttonFinish.isVisible = (position + 1) == list.size
                buttonBack.isVisible = position != 0
                buttonSkip.isVisible = position == 0
            }
        })
    }

    private fun configButtons(
        context: Context,
        list: Array<OnboardingSteps>,
        viewPager: ViewPager2
    ) = with(binding) {
        buttonSkip.setOnClickListener {
            finish()
            startActivity(HostActivity.start(context))
        }

        buttonFinish.setOnClickListener {
            finish()
            startActivity(HostActivity.start(context))
        }

        val adapter = OnboardingViewPagerAdapter(list)
        viewPager.adapter = adapter
        indicator.setViewPager(viewPager)

        buttonNext.setOnClickListener {
            val nextItem = viewPager.currentItem + 1
            viewPager.setCurrentItem(nextItem, true)
        }

        buttonBack.setOnClickListener {
            val nextItem = viewPager.currentItem - 1
            viewPager.setCurrentItem(nextItem, true)
        }
    }
}

enum class OnboardingSteps(val image: Int, val title: Int) {
    ONBOARDING_STEP_ONE(R.drawable.ic_onboarding_one, R.string.onboarding_step_one),
    ONBOARDING_STEP_TWO(R.drawable.ic_onboarding_two, R.string.onboarding_step_two),
    ONBOARDING_STEP_THREE(R.drawable.ic_onboarding_three, R.string.onboarding_step_three)

}