package com.technocrats.recycle.made.easy.ui.calendar

import android.content.Context
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.technocrats.recycle.made.easy.ui.calendar.model.CollectionDays
import org.threeten.bp.DayOfWeek
import org.threeten.bp.Month
import org.threeten.bp.YearMonth
import org.threeten.bp.temporal.WeekFields
import java.util.*


fun getOrganicDayList(): List<CollectionDays> {

    val organicList = mutableListOf<CollectionDays>()
    val garbageType = "organic"
    organicList.add(CollectionDays(YearMonth.of(2020, Month.MARCH).atDay(13), garbageType))
    organicList.add(CollectionDays(YearMonth.of(2020, Month.APRIL).atDay(13), garbageType))
    organicList.add(CollectionDays(YearMonth.of(2020, Month.APRIL).atDay(27), garbageType))
    organicList.add(CollectionDays(YearMonth.of(2020, Month.MAY).atDay(11), garbageType))
    organicList.add(CollectionDays(YearMonth.of(2020, Month.MAY).atDay(25), garbageType))
    organicList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(8), garbageType))
    organicList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(22), garbageType))
    return organicList

}

fun getGarbageDayList(): List<CollectionDays> {

    val garbageList = mutableListOf<CollectionDays>()
    val garbageType = "garbage"
    garbageList.add(CollectionDays(YearMonth.of(2020, Month.APRIL).atDay(6), garbageType))
    garbageList.add(CollectionDays(YearMonth.of(2020, Month.APRIL).atDay(20), garbageType))
    garbageList.add(CollectionDays(YearMonth.of(2020, Month.MAY).atDay(4), garbageType))
    garbageList.add(CollectionDays(YearMonth.of(2020, Month.MAY).atDay(16), garbageType))
    garbageList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(1), garbageType))
    garbageList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(15), garbageType))
    garbageList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(29), garbageType))

    return garbageList
}

fun paperRecycleDayList(): List<CollectionDays> {

    val paperRecycleList = mutableListOf<CollectionDays>()
    val garbageType = "paper"
    paperRecycleList.add(CollectionDays(YearMonth.of(2020, Month.APRIL).atDay(6), garbageType))
    paperRecycleList.add(CollectionDays(YearMonth.of(2020, Month.APRIL).atDay(13), garbageType))
    paperRecycleList.add(CollectionDays(YearMonth.of(2020, Month.APRIL).atDay(20), garbageType))
    paperRecycleList.add(CollectionDays(YearMonth.of(2020, Month.APRIL).atDay(27), garbageType))
    paperRecycleList.add(CollectionDays(YearMonth.of(2020, Month.MAY).atDay(4), garbageType))
    paperRecycleList.add(CollectionDays(YearMonth.of(2020, Month.MAY).atDay(11), garbageType))
    paperRecycleList.add(CollectionDays(YearMonth.of(2020, Month.MAY).atDay(16), garbageType))
    paperRecycleList.add(CollectionDays(YearMonth.of(2020, Month.MAY).atDay(25), garbageType))
    paperRecycleList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(1), garbageType))
    paperRecycleList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(8), garbageType))
    paperRecycleList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(15), garbageType))
    paperRecycleList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(22), garbageType))
    paperRecycleList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(29), garbageType))

    return paperRecycleList
}

fun nonPaperRecycle1DayList(): List<CollectionDays> {
    val nonPaperRecycleDayList = mutableListOf<CollectionDays>()
    val garbageType = "nonpaper"
    nonPaperRecycleDayList.add(CollectionDays(YearMonth.of(2020, Month.APRIL).atDay(6), garbageType))
    nonPaperRecycleDayList.add(CollectionDays(YearMonth.of(2020, Month.APRIL).atDay(13), garbageType))
    nonPaperRecycleDayList.add(CollectionDays(YearMonth.of(2020, Month.APRIL).atDay(20), garbageType))
    nonPaperRecycleDayList.add(CollectionDays(YearMonth.of(2020, Month.APRIL).atDay(27), garbageType))
    nonPaperRecycleDayList.add(CollectionDays(YearMonth.of(2020, Month.MAY).atDay(4), garbageType))
    nonPaperRecycleDayList.add(CollectionDays(YearMonth.of(2020, Month.MAY).atDay(11), garbageType))
    nonPaperRecycleDayList.add(CollectionDays(YearMonth.of(2020, Month.MAY).atDay(16), garbageType))
    nonPaperRecycleDayList.add(CollectionDays(YearMonth.of(2020, Month.MAY).atDay(25), garbageType))
    nonPaperRecycleDayList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(1), garbageType))
    nonPaperRecycleDayList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(8), garbageType))
    nonPaperRecycleDayList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(15), garbageType))
    nonPaperRecycleDayList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(22), garbageType))
    nonPaperRecycleDayList.add(CollectionDays(YearMonth.of(2020, Month.JUNE).atDay(29), garbageType))

    return nonPaperRecycleDayList
}

fun daysOfWeekFromLocale(): Array<DayOfWeek> {
    val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
    var daysOfWeek = DayOfWeek.values()
    // Order `daysOfWeek` array so that firstDayOfWeek is at index 0.
    if (firstDayOfWeek != DayOfWeek.MONDAY) {
        val rhs = daysOfWeek.sliceArray(firstDayOfWeek.ordinal..daysOfWeek.indices.last)
        val lhs = daysOfWeek.sliceArray(0 until firstDayOfWeek.ordinal)
        daysOfWeek = rhs + lhs
    }
    return daysOfWeek
}

fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)

fun TextView.setTextColorRes(@ColorRes color: Int) = setTextColor(context.getColorCompat(color))