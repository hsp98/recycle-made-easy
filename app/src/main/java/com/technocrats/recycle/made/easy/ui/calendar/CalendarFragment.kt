package com.technocrats.recycle.made.easy.ui.calendar

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.kizitonwose.calendarview.utils.next
import com.kizitonwose.calendarview.utils.previous

import com.technocrats.recycle.made.easy.R
import com.technocrats.recycle.made.easy.ui.calendar.model.CollectionDays
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.calendar_day_layout.view.*
import kotlinx.android.synthetic.main.calendar_day_view.view.*
import kotlinx.android.synthetic.main.calendar_layout.*
import kotlinx.android.synthetic.main.calendar_week.view.*
import kotlinx.android.synthetic.main.collection_day_description.*
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.TextStyle
import org.threeten.bp.temporal.WeekFields
import java.util.*
/*
The below code uses kizitonwose library and the code provide by kizitonwose is modified to adapt to the requirement of application
"https://github.com/kizitonwose/CalendarView/tree/master/sample/src/main/java/com/kizitonwose/calendarviewsample"
 */
class CalendarFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.calendar_layout, container, false)
    }

    private var selectedDate: LocalDate? = null
    private val today = LocalDate.now()
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM")
    private val collectionDayAdapter = GarbageCollectionDayAdapter()
    private val organicDays = getOrganicDayList().groupBy { it.time }
    private val recycleBag1Days = nonPaperRecycle1DayList().groupBy { it.time }
    private val paperRecyleDays = paperRecycleDayList().groupBy { it.time }
    private val garbageRecyleDays = getGarbageDayList().groupBy { it.time }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("calendarfragment", "Entered onViewCreated")
        month_view.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        month_view.adapter = collectionDayAdapter
        calendarView.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        collectionDayAdapter.notifyDataSetChanged()
        val daysOfWeek = daysOfWeekFromLocale()

        val currentMonth = YearMonth.now()
        calendarView.setup(currentMonth.minusMonths(10), currentMonth.plusMonths(10), daysOfWeek.first())
        calendarView.scrollToMonth(currentMonth)

        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.
            val textView = view.calendarDayText
            val garbageDay = view.garbageRecycle
            val paperRecycle = view.paperRecycle
            val organicRecycle = view.organicRecycle
            val nonPaperRecycle = view.nonPaperRecycle
            val layout = view.day_layout
            //val selectedView = view.selected

            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH) {
                        if (selectedDate != day.date) {
                            val oldDate = selectedDate
                            selectedDate = day.date
                            calendarView.notifyDateChanged(day.date)
                            oldDate?.let { calendarView.notifyDateChanged(it) }
                            updateAdapterForDate(day.date)
                        }
                    }
                }
            }
        }

        // Bind days to views
        calendarView.dayBinder = object : DayBinder<DayViewContainer> {

            override fun create(view: View) = DayViewContainer(view)

            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.textView.text = day.date.dayOfMonth.toString()
                container.day = day

                val paperRecycle = container.paperRecycle
                val garbageRecycle = container.garbageDay
                val organicRecycle = container.organicRecycle
                val nonPaperRecycle = container.nonPaperRecycle
                paperRecycle.background = null
                garbageRecycle.background = null
                organicRecycle.background = null
                nonPaperRecycle.background = null
                if (day.owner == DayOwner.THIS_MONTH) {
                    container.layout.setBackgroundResource(if (selectedDate == day.date) R.drawable.selected_day else R.color.black)
                    val recycleDays = organicDays[day.date]
                    if (recycleDays != null) {
                        organicRecycle.setBackgroundColor(view.context.getColorCompat(R.color.green))
                    }
                    if (recycleBag1Days[day.date] != null) {
                        nonPaperRecycle.setBackgroundColor(view.context.getColorCompat(R.color.blue))
                    }
                    if (paperRecyleDays[day.date] != null) {
                        paperRecycle.setBackgroundColor(view.context.getColorCompat(R.color.white))
                    }
                    if (garbageRecyleDays[day.date] != null) {
                        garbageRecycle.setBackgroundColor(view.context.getColorCompat(R.color.light_brown))
                    }
                    when(day.date) {
                        today -> {
                            //container.textView.setTypeface(null, Typeface.BOLD)
                            container.textView.setBackgroundResource(R.drawable.circle_shape)
                        }
                    }
                } else {
                    container.textView.setTextColorRes(R.color.days_bg)
                }
            }
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val weekLayout = view.calendarWeekLayout
        }

        calendarView.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                // Setup each header day text if we have not done that already.
                if (container.weekLayout.tag == null) {
                    container.weekLayout.tag = month.yearMonth
                    container.weekLayout.children.map { it as TextView }.forEachIndexed { index, tv ->
                        tv.text = daysOfWeek[index].getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                            .toUpperCase(Locale.ENGLISH)
                        tv.setTextColorRes(R.color.white)
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
                    }
                    month.yearMonth
                }
            }
        }

        calendarView.monthScrollListener = { month ->
            val title = "${monthTitleFormatter.format(month.yearMonth)} ${month.yearMonth.year}"
            calendarMonthYearText.text = title

            selectedDate?.let {
                // Clear selection if we scroll to a new month.
                selectedDate = null
                calendarView.notifyDateChanged(it)
                //updateAdapterForDate(null)
            }
        }

        nextMonthArrow.setOnClickListener {
            calendarView.findFirstVisibleMonth()?.let {
                calendarView.smoothScrollToMonth(it.yearMonth.next)
            }
        }

        previousMonthArrow.setOnClickListener {
            calendarView.findFirstVisibleMonth()?.let {
                calendarView.smoothScrollToMonth(it.yearMonth.previous)
            }
        }


    }
    private fun updateAdapterForDate(date: LocalDate?) {
        Log.d("calendarfragment", "Entered updateAdapterForDate")
        collectionDayAdapter.collectionDayList.clear()
        val collectionList = mutableListOf<CollectionDays>()
        collectionList.addAll(organicDays[date].orEmpty())
        collectionList.addAll(garbageRecyleDays[date].orEmpty())
        collectionList.addAll(paperRecyleDays[date].orEmpty())
        collectionList.addAll(recycleBag1Days[date].orEmpty())
        collectionDayAdapter.collectionDayList.addAll(collectionList)
        collectionDayAdapter.notifyDataSetChanged()
        Log.d("calendarfragment", "Exiting updateAdapterForDate")
    }



}
