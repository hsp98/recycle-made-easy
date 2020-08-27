package com.technocrats.recycle.made.easy.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.technocrats.recycle.made.easy.R
import kotlinx.android.synthetic.main.fragment_description_catagory.*
import org.json.JSONArray


/**
 * A simple [Fragment] subclass.
 */
class description_catagory : Fragment() {

    var arr:JSONArray? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val logoTypes = HashMap<String, Int>()
        logoTypes["Garbage"] = R.drawable.dustbin
        logoTypes["Drop"] = R.drawable.drop
        logoTypes["Organic"] = R.drawable.organic_bin
        logoTypes["Electronic"] = R.drawable.electronics
        logoTypes["Paper"] = R.drawable.paper_recycle
        logoTypes["Donation"] = R.drawable.donation
        val view =
            inflater.inflate(R.layout.fragment_description_catagory, container, false)

//
        if (arguments!!.getString("description") != null) {
            val v = arguments!!.getString("description")
            var arr = JSONArray(v)

            val dustbinType =
                view.findViewById(R.id.dustbinType) as TextView
            val description =
                view.findViewById(R.id.description) as TextView
           val dustbinTypeIcon = view.findViewById(R.id.dustbinTypeIcon) as ImageView

            when (arr[0]) {
                "Garbage" -> dustbinTypeIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.dustbin))
                else -> dustbinTypeIcon.setImageResource(logoTypes[arr[0].toString()]!!)
            }

            if(arr[0].toString() == "Drop") {
                dustbinType.text = "Drop It Off"
            }else if (arr[0].toString() == "Donate") {
                dustbinType.text = "Put it for donation"
            } else {
                dustbinType.text = "Dispose in "+arr[0].toString()+" bin"
            }
            description.text = arr[1].toString()
        }


        return view
    }

}
