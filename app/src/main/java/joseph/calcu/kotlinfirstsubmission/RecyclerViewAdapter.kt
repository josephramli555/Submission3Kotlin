package joseph.calcu.kotlinfirstsubmission

import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext

class RecyclerViewAdapter(var list : MutableList<LeagueItem>, private val context:Context?,var listener : (LeagueItem) -> Unit) : RecyclerView.Adapter<RecyclerViewAdapter.LeagueViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(LayoutInflater.from(context).inflate(R.layout.leagueitem_layout,parent,false))
    }


    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(list[position],listener)
    }

    class LeagueViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.leagueitemlayout_img)
        val textView = itemView.findViewById<TextView>(R.id.leagueitemlayout_Header)

        fun bindItem(items:LeagueItem,listener:(LeagueItem)->Unit)
        {
            textView.text = items.leaguename
            items.image?.let{Picasso.get().load(it).into(imageView)}
            itemView.setOnClickListener(){
                listener(items)

            }
        }
    }



}