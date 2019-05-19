package joseph.calcu.kotlinfirstsubmission.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import joseph.calcu.kotlinfirstsubmission.Model.MatchModel
import joseph.calcu.kotlinfirstsubmission.Model.SearchEventModel
import joseph.calcu.kotlinfirstsubmission.R

class SearchEventAdapter(var list : List<SearchEventModel>, private val context: Context?, var listener : (SearchEventModel) -> Unit) : RecyclerView.Adapter<SearchEventAdapter.MatchListViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchListViewHolder {
        return MatchListViewHolder(LayoutInflater.from(context).inflate(R.layout.matchitem_layout,parent,false))
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: MatchListViewHolder, position: Int) {
        holder.bindItem(list[position],listener)
    }

    class MatchListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val TeamA = itemView.findViewById<TextView>(R.id.matchteamA_textview)
        val TeamB = itemView.findViewById<TextView>(R.id.matchteamB_textview)
        val DateText= itemView.findViewById<TextView>(R.id.matchdate_textview)
        fun bindItem(items: SearchEventModel, listener:(SearchEventModel)->Unit)
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