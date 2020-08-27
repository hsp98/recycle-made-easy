package com.technocrats.recycle.made.easy.ui.calendar

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.technocrats.recycle.made.easy.R
import com.technocrats.recycle.made.easy.ui.calendar.model.CollectionDays
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter



class GarbageCollectionDayAdapter : RecyclerView.Adapter<GarbageCollectionDayDetailViewHolder3>() {


    val collectionDayList = mutableListOf<CollectionDays>()

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): GarbageCollectionDayDetailViewHolder3 {
        return GarbageCollectionDayDetailViewHolder3(parent.inflate(R.layout.collection_day_description))
    }

    override fun getItemCount(): Int {
        return collectionDayList.size
    }

    override fun onBindViewHolder(holder: GarbageCollectionDayDetailViewHolder3, position: Int) {
        holder.bind(collectionDayList[position])

    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }


}