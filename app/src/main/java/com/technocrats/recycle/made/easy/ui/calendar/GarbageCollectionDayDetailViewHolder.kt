package com.technocrats.recycle.made.easy.ui.calendar

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.technocrats.recycle.made.easy.R
import com.technocrats.recycle.made.easy.ui.calendar.model.CollectionDays
import kotlinx.android.extensions.LayoutContainer
import org.threeten.bp.format.DateTimeFormatter
import org.w3c.dom.Text

class GarbageCollectionDayDetailViewHolder3(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer  {

    private val formatter = DateTimeFormatter.ofPattern("EEE'\n'dd MMM'\n'")

    fun bind(collectionDay: CollectionDays) {
        var collectionDayTextView : TextView = containerView.findViewById(R.id.collection_day)
        collectionDayTextView.text = formatter.format(collectionDay.time)
        var garbageDetailText: TextView = containerView.findViewById(R.id.garbageDetailText)
        var garbageIcon: ImageView = containerView.findViewById(R.id.garbageIcon)

        when (collectionDay.type) {
            "organic" -> {
                collectionDayTextView.setBackgroundColor(containerView.context.getColorCompat(R.color.green))
                garbageDetailText.text = "Organics"
                garbageIcon.setImageDrawable(ContextCompat.getDrawable(containerView.context, R.drawable.organic_bin))
            }
            "garbage" -> {
                collectionDayTextView.setBackgroundColor(containerView.context.getColorCompat(R.color.light_brown))
                garbageDetailText.text = "Garbage"
                garbageIcon.setImageDrawable(ContextCompat.getDrawable(containerView.context, R.drawable.dustbin))
            }
            "paper" -> {
                collectionDayTextView.setBackgroundColor(containerView.context.getColorCompat(R.color.white))
                garbageDetailText.text = "Paper Recycling Bag 2"
                garbageIcon.setImageDrawable(ContextCompat.getDrawable(containerView.context, R.drawable.paper_recycle))
            }
            else -> {
                collectionDayTextView.setBackgroundColor(containerView.context.getColorCompat(R.color.blue))
                garbageDetailText.text = "Recycling Bag 1"
                garbageIcon.setImageDrawable(ContextCompat.getDrawable(containerView.context, R.drawable.recyclebag1))
            }
        }



        //R.id.collection_day.text = "dein"
    }
}