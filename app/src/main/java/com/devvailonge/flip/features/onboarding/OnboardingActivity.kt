package com.devvailonge.flip.features.onboarding

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.devvailonge.flip.R
import me.relex.circleindicator.CircleIndicator3

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var circleIndicator: CircleIndicator3
    private lateinit var skip: Button
    private lateinit var next: Button

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_onboarding)
        viewPager = findViewById(R.id.viewPager)
        circleIndicator = findViewById(R.id.indicator)
        skip = findViewById(R.id.button_back)
        next = findViewById(R.id.button_next)

        val adapter = OnboardingViewPagerAdapter(OnboardingSteps.values())
        viewPager.adapter=adapter
        circleIndicator.setViewPager(viewPager)

        viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }


}

enum class OnboardingSteps (val image: Int, val title:Int ){
    ONBOARDING_STEP_ONE(R.drawable.ic_onboarding_one, R.string.onboarding_step_one),
    ONBOARDING_STEP_TWO(R.drawable.ic_onboarding_two,R.string.onboarding_step_two),
    ONBOARDING_STEP_THREE(R.drawable.ic_onboarding_three,R.string.onboarding_step_three)

}