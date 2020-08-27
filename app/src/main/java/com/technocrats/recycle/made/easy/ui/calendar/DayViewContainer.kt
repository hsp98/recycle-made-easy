package com.technocrats.recycle.made.easy.ui.calendar

import android.view.View
import com.kizitonwose.calendarview.ui.ViewContainer
import kotlinx.android.synthetic.main.calendar_day_layout.view.*

class DayViewContainer(view: View): ViewContainer(view){
    val textView = view.calendarDayText
}