package joseph.calcu.kotlinfirstsubmission.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import joseph.calcu.kotlinfirstsubmission.DatabaseHelper.FavoritePastMatch
import joseph.calcu.kotlinfirstsubmission.Model.MatchModel
import joseph.calcu.kotlinfirstsubmission.R

class FavoriteMatchAdapter(var list : List<FavoritePastMatch>, private val context: Context?, var listener : (FavoritePastMatch) -> Unit) : RecyclerView.Adapter<FavoriteMatchAdapter.MatchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.favoriteitem,parent,false))
    }


    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(list[position],listener)
    }

    class MatchViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val homeTeam:TextView = itemView.findViewById(R.id.favitem_hometeam)
        val awayTeam:TextView = itemView.findViewById(R.id.favitem_awayteam)
        val dateText:TextView= itemView.findViewById(R.id.favitem_matchdate)
        val scoreHome:TextView = itemView.findViewById(R.id.favitem_scorehome)
        val scoreAway:TextView = itemView.findViewById(R.id.favitem_scoreaway)
        val title:TextView = itemView.findViewById(R.id.favitem_title)
        fun bindItem(items:FavoritePastMatch,listener:(FavoritePastMatch)->Unit)
        {
            homeTeam.text = items.homeName
            awayTeam.text = items.awayName
            dateText.text = items.date
            scoreHome.text = items.scoreHome
            scoreAway.text = items.scoreAway
            title.text = items.eventName
            itemView.setOnClickListener(){
                listener(items)
            }
        }
    }



}