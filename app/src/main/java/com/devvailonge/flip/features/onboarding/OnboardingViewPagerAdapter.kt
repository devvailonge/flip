package com.devvailonge.flip.features.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.devvailonge.flip.databinding.ItemOnboardingBinding

class OnboardingViewPagerAdapter(private val steps: Array<OnboardingSteps>) :
    RecyclerView.Adapter<OnboardingViewPagerAdapter.OnboardingViewHolder>() {

    inner class OnboardingViewHolder(val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        return OnboardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = steps.size

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        val step = steps[position]
        holder.binding.apply {
            title.text = holder.itemView.context.getString(step.title)
            image.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.itemView.context,
                    step.image
                )
            )
        }
    }
}
