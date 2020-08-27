package com.example.recyclesimple.Adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.technocrats.recycle.made.easy.R
import com.technocrats.recycle.made.easy.model.User

class CustomAdapter (var categoryList: ArrayList<User>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(),
    Filterable {

    var categoryFilterList = ArrayList<User>()

    init {
        categoryFilterList = categoryList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById(R.id.profileImg) as ImageView
        val textViewName = itemView.findViewById(R.id.categoryname) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = categoryList[position]
        holder?.textViewName?.text = user.name
        holder?.image.setImageResource(user.logo)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                //val charSearch = constraint.toString()
                val filterResults = FilterResults()
                if (charSequence.isEmpty()) {
                    filterResults.count = categoryFilterList.size
                    filterResults.values = categoryFilterList
                } else {
                    var searchChar = charSequence.toString().toLowerCase()
                    val resultList = ArrayList<User>()
                    for (row in categoryFilterList) {
                        if (row.name.toLowerCase().contains(searchChar)) {
                            resultList.add(row)
                        }
                    }
                    filterResults.count = resultList.size
                    filterResults.values = resultList
                }
                return filterResults
            }
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                categoryList= results?.values as ArrayList<User>
                notifyDataSetChanged()
            }
        }
    }
}