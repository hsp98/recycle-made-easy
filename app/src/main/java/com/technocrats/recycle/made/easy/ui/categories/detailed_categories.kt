package com.technocrats.recycle.made.easy.ui.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclesimple.Adapter.CustomAdapter
import com.technocrats.recycle.made.easy.R
import com.technocrats.recycle.made.easy.model.User
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [detailed_categories.newInstance] factory method to
 * create an instance of this fragment.
 */
class detailed_categories : Fragment() {

    lateinit var adapter: CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }
    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {

            override fun onChildViewDetachedFromWindow(view: View) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view?.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =  inflater.inflate(R.layout.fragment_detailed_categories, container, false)

        var v :String? = null
        if (arguments!!.getString("category") != null) {
            v = arguments!!.getString("category")
        }
        Log.d("one",v.toString())


        val logoTypes = HashMap<String, Int>()

        logoTypes["Garbage"] = R.drawable.dustbin
        logoTypes["Drop"] = R.drawable.drop
        logoTypes["Organic"] = R.drawable.organic_bin
        logoTypes["Electronic"] = R.drawable.electronics
        logoTypes["Paper"] = R.drawable.paper_recycle
        logoTypes["Donation"] = R.drawable.donation
        var inr:JSONObject? = null

        val recyclerView = view.findViewById<RecyclerView>(R.id.detailed_recyclerView)
        recyclerView.addOnItemClickListener(onClickListener = object:
            detailed_categories.OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                val categoryName =view.findViewById(R.id.categoryname) as TextView

                val fragment = description_catagory()
                val fragmentManager = activity!!.supportFragmentManager

                val bundle = Bundle()

                val keys = inr?.keys()
                if (keys != null) {
                    while (keys.hasNext()) {
                        val key = keys.next()
                        if (key == categoryName.text.toString()){
                            bundle.putString("description",inr?.optJSONArray(key).toString())
                        }
                    }
                }
//                bundle.putString("discription",)
                fragment.arguments = bundle

//                val fragmentTransaction = fragmentManager.beginTransaction()
//                fragmentTransaction.add(R.id.nav_host_fragment, fragment)
//                fragmentTransaction.addToBackStack(null)
//                fragmentTransaction.commit()
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,fragment,"TAG").commit();
             //   Toast.makeText(context,position.toString() +categoryName.text,Toast.LENGTH_SHORT).show()
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(activity)
        val users = ArrayList<User>()


        val jsonfile: String = context?.assets?.open("vish.json")?.bufferedReader().use { it?.readText()!! }
        var jsonObject = JSONObject(jsonfile)
        var jsonArray = JSONArray(jsonObject.get("catagory").toString())
        for (x in 0..jsonArray.length()-1) {
            val jsonObject = jsonArray.getJSONObject(x)
            val keys = jsonObject.keys()
            while (keys.hasNext()) {
                val key = keys.next()
                if (key.toString() == v){
                    val value = jsonObject.optString(key)
                    inr = JSONObject(value)
                    val keys = inr.keys()
                    while (keys.hasNext()) {
                        val key = keys.next()
                        val value = inr.optJSONArray(key)
                        Log.d("val", value.toString())
                        users.add(User(key, logoTypes[value[0]]!!))

                    }
                }

            }
        }
        adapter = CustomAdapter(users)
        recyclerView.adapter=adapter
        return view
    }



}
