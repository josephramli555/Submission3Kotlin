package joseph.calcu.kotlinfirstsubmission.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import joseph.calcu.kotlinfirstsubmission.Model.MatchModel
import joseph.calcu.kotlinfirstsubmission.R
import joseph.calcu.kotlinfirstsubmission.RecyclerViewAdapter
import java.util.*
import kotlin.collections.ArrayList

class MatchListAdapter(var list : List<MatchModel>, private val context: Context?, var listener : (MatchModel) -> Unit) : RecyclerView.Adapter<MatchListAdapter.MatchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.matchitem_layout,parent,false))
    }


    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(list[position],listener)
    }

    class MatchViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val TeamA = itemView.findViewById<TextView>(R.id.matchteamA_textview)
        val TeamB = itemView.findViewById<TextView>(R.id.matchteamB_textview)
        val DateText= itemView.findViewById<TextView>(R.id.matchdate_textview)
        fun bindItem(items:MatchModel,listener:(MatchModel)->Unit)
        {
            TeamA.text = items.strHomeTeam
            TeamB.text = items.strAwayTeam
            DateText.text = items.dateEvent
            itemView.setOnClickListener(){
                listener(items)

            }
        }
    }



}