package timrapp.breweries.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import timrapp.breweries.R
import timrapp.breweries.data.Brewery

class BreweryAdapter(private val breweryList: List<Brewery>) :
    RecyclerView.Adapter<BreweryViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BreweryViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.brewery_list_row, viewGroup, false)
        return BreweryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        println("item count = ${breweryList.size}")
        return breweryList.size
    }

    override fun onBindViewHolder(viewHolder: BreweryViewHolder, position: Int) {
        val item = breweryList[position]
        val displayValue = "${item.name}, ${item.city}, ${item.breweryType}"
        viewHolder.info.text = displayValue
    }
}


class BreweryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var info: TextView = itemView.findViewById(R.id.info)
}
