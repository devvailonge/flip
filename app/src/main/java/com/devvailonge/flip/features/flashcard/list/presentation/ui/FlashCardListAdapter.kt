package com.devvailonge.flip.features.flashcard.list.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devvailonge.flip.R
import com.devvailonge.flip.features.flashcard.data.FlashCardEntity

class FlashCardListAdapter(
    private val deleteClickListener: (FlashCardEntity) -> Unit,
    private val editClickListener: (FlashCardEntity) -> Unit
) :
    ListAdapter<FlashCardEntity, FlashCardListAdapter.FlashCardViewHolder>(FlashCardListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashCardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_flashcard, parent, false)
        return FlashCardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FlashCardViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, deleteClickListener, editClickListener)
    }

    class FlashCardViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        lateinit var txtWordTitle: TextView
        lateinit var imgEdit: ImageView
        lateinit var imgDelete: ImageView

        fun bind(
            data: FlashCardEntity,
            deleteClickListener: (FlashCardEntity) -> Unit,
            editClickListener: (FlashCardEntity) -> Unit
        ) {

            txtWordTitle = view.findViewById(R.id.txtWordFlashcard)
            imgEdit = view.findViewById(R.id.imgEditItemFlashcard)
            imgDelete = view.findViewById(R.id.imgCloseItemFlashcard)

            var showFront = true
            view.setOnClickListener {
                showFront = !showFront
                if(showFront){
                    txtWordTitle.text = data.frontText
                }else{
                    txtWordTitle.text = data.backText
                }
            }

            imgEdit.setOnClickListener {
                editClickListener.invoke(data)
            }

            imgDelete.setOnClickListener {
                deleteClickListener.invoke(data)
            }

            txtWordTitle.text = data.frontText
        }
    }


    private companion object : DiffUtil.ItemCallback<FlashCardEntity>() {

        override fun areItemsTheSame(oldItem: FlashCardEntity, newItem: FlashCardEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FlashCardEntity,
            newItem: FlashCardEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
}