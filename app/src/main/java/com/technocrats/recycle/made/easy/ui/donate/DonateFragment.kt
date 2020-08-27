package com.technocrats.recycle.made.easy.ui.donate
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclesimple.Adapter.CustomAdapter

import com.technocrats.recycle.made.easy.R
import com.technocrats.recycle.made.easy.fragment_map_clothes
import com.technocrats.recycle.made.easy.model.User
import com.technocrats.recycle.made.easy.ui.categories.CategoriesFragment
import com.technocrats.recycle.made.easy.ui.categories.detailed_categories
import kotlinx.android.synthetic.main.fragment_donate.*


class DonateFragment : Fragment() {

    lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Toast.makeText(this.context,"vv",Toast.LENGTH_SHORT).show()


    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }
    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {

            override fun onChildViewDetachedFromWindow(view: View) {
                view?.setOnClickListener(null)
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view?.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view =  inflater.inflate(R.layout.fragment_donate, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView_donate)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        val users = ArrayList<User>()
        users.add(User("Clothes",R.drawable.clothes))
        users.add(User("Shoes",R.drawable.shoe))

        adapter = CustomAdapter(users)
        recyclerView.adapter=adapter


        recyclerView.addOnItemClickListener(onClickListener = object:
            DonateFragment.OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                val categoryName =view.findViewById(R.id.categoryname) as TextView

                val fragment = fragment_map_clothes()
                val fragmentManager = activity!!.supportFragmentManager

                if (categoryName.text == "Clothes")
                {
                    val bundle = Bundle()
                    bundle.putString("map","coordinates 1,2,3")
                    fragment.arguments = bundle

                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.add(R.id.nav_host_fragment, fragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }
                else{
                    val fragment = fragment_map_clothes()
                    val fragmentManager = activity!!.supportFragmentManager

                    val bundle = Bundle()
                    bundle.putString("map","coordinates 3,4,5")
                    fragment.arguments = bundle

                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.add(R.id.nav_host_fragment, fragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }

            }
        })
//        val bc = view.findViewById<Button>(R.id.btnclothes)
//        bc.setOnClickListener {
//            val fragment = fragment_map_clothes()
//            val fragmentManager = activity!!.supportFragmentManager
//
//
//            val bundle = Bundle()
//            bundle.putString("map","coordinates 1,2,3")
//            fragment.arguments = bundle
//
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.add(R.id.nav_host_fragment, fragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
//        }
//
//        val bs = view.findViewById<Button>(R.id.btnshoes)
//        bs.setOnClickListener {
//            val fragment = fragment_map_clothes()
//            val fragmentManager = activity!!.supportFragmentManager
//
//            val bundle = Bundle()
//            bundle.putString("map","coordinates 3,4,5")
//            fragment.arguments = bundle
//
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.add(R.id.nav_host_fragment, fragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
//        }

        // Inflate the layout for this fragment
        return view

    }


}
