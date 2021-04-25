package com.devvailonge.flip.features.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.devvailonge.flip.R
import com.devvailonge.flip.features.categories.images.CategoryImageListAdapter

class OnboardingViewPagerAdapter(val steps: Array<OnboardingSteps>):RecyclerView.Adapter<OnboardingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_onboarding,parent,false)

     return OnboardingViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.bind(steps[position])
    }

    override fun getItemCount(): Int {
        return steps.size
    }


}

class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bind (step: OnboardingSteps){
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)

        title.text=itemView.context.getString(step.title)
        image.setImageDrawable(ContextCompat.getDrawable(itemView.context,step.image))
    }


}