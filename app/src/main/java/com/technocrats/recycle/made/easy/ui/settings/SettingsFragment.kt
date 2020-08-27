package com.technocrats.recycle.made.easy.ui.settings

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.JobIntentService.enqueueWork
import androidx.fragment.app.Fragment
import com.technocrats.recycle.made.easy.R
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*


class SettingsFragment : Fragment() {
    var notificationSwitch : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)

        rootView.notification_switch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
            {
                Toast.makeText(context, "Recycle Day Notification ON", Toast.LENGTH_SHORT).show()
                val serviceIntent = Intent(context, NotificationJobService::class.java)
                val notificationJobService = NotificationJobService()
                notificationJobService.enqueueWork(context!!, serviceIntent)
            }
            else{
                Toast.makeText(context, "Recycle Day Notification OFF", Toast.LENGTH_SHORT).show()
            }
        }
        return rootView
    }

}
