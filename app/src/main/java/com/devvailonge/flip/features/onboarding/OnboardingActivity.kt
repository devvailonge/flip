package com.devvailonge.flip.features.onboarding

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.devvailonge.flip.HostActivity
import com.devvailonge.flip.R
import me.relex.circleindicator.CircleIndicator3

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var circleIndicator: CircleIndicator3
    private lateinit var btnBack: Button
    private lateinit var btnNext: Button
    private lateinit var btnSkip: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        viewPager = findViewById(R.id.viewPager)
        circleIndicator = findViewById(R.id.indicator)
        btnBack = findViewById(R.id.button_back)
        btnNext = findViewById(R.id.button_next)
        btnSkip = findViewById(R.id.button_skip)

        val list = OnboardingSteps.values()

        btnSkip.setOnClickListener {
            startActivity(HostActivity.start(this))
        }

        val adapter = OnboardingViewPagerAdapter(list)
        viewPager.adapter=adapter
        circleIndicator.setViewPager(viewPager)

        btnNext.setOnClickListener {
            val nextItem = viewPager.currentItem +1
            viewPager.setCurrentItem(nextItem,true)
        }



        viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                btnNext.isGone = (position +1) == list.size
                btnBack.isVisible = position != 0
                btnSkip.isVisible = position == 0

            }
        })
    }




}

enum class OnboardingSteps (val image: Int, val title:Int ){
    ONBOARDING_STEP_ONE(R.drawable.ic_onboarding_one, R.string.onboarding_step_one),
    ONBOARDING_STEP_TWO(R.drawable.ic_onboarding_two,R.string.onboarding_step_two),
    ONBOARDING_STEP_THREE(R.drawable.ic_onboarding_three,R.string.onboarding_step_three)

}