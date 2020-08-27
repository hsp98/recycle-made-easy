package com.technocrats.recycle.made.easy.ui.categories

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclesimple.Adapter.CustomAdapter

import com.technocrats.recycle.made.easy.R
import com.technocrats.recycle.made.easy.model.User
import kotlinx.android.synthetic.main.category_list.*

import kotlinx.android.synthetic.main.fragment_categories.*

import org.json.JSONObject

import com.technocrats.recycle.made.easy.MainActivity as mac


class CategoriesFragment : Fragment() {

    lateinit var adapter: CustomAdapter
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val REQUEST_CODE_PERMISSIONS = 101
    private val REQUIRED_PERMISSIONS = arrayOf("android.permission.CAMERA")

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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_categories, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)


        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(activity!!, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        recyclerView.addOnItemClickListener(onClickListener = object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                val categoryName =view.findViewById(R.id.categoryname) as TextView

                val fragment = detailed_categories()
                val fragmentManager = activity!!.supportFragmentManager

                val bundle = Bundle()
                bundle.putString("category",categoryName.text.toString())
                fragment.arguments = bundle

//                val fragmentTransaction = fragmentManager.beginTransaction()
//                fragmentTransaction.add(R.id.nav_host_fragment, fragment)
//                fragmentTransaction.addToBackStack(null)
//                fragmentTransaction.commit()
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment,fragment,"TAG").commit();
                Toast.makeText(context,position.toString() +categoryName.text,Toast.LENGTH_SHORT).show()
            }
        })
        
        val categorySearch = view.findViewById<SearchView>(R.id.category_search)
        categorySearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.getFilter().filter(newText)
                adapter.notifyDataSetChanged()
                return true
            }
        })


        recyclerView.layoutManager = LinearLayoutManager(activity)
        val users = ArrayList<User>()
        users.add(User("Household",R.drawable.household))
        users.add(User("Plastics",R.drawable.plastic))
        users.add(User("Food",R.drawable.food))
        users.add(User("Electronics",R.drawable.electronics))
        users.add(User("Batteries",R.drawable.batteries))
        users.add(User("Paper and Cardboard",R.drawable.paper))
        users.add(User("Glass",R.drawable.glass))
        users.add(User("Chemical",R.drawable.chemicals))
        users.add(User("Metals",R.drawable.metals))
        users.add(User("Garden",R.drawable.garden))
        users.add(User("Automotive",R.drawable.automotive))
        users.add(User("Construction",R.drawable.construction))
        adapter = CustomAdapter(users)
        recyclerView.adapter=adapter
            return view
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(activity, "Camera Permission Denied. Some features may not work.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun allPermissionsGranted(): Boolean {
        for (permission in REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(context!!,permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }


    public fun searchClick(view: View) {
        var inputMethodManager: InputMethodManager? = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
       // inputMethodManager.showSoftInput(editT)
        inputMethodManager?.showSoftInput(category_search, 1)
    }



}
