package joseph.calcu.kotlinfirstsubmission.Fragment


import android.content.Intent
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import joseph.calcu.kotlinfirstsubmission.Activity.DetailEventActivity
import joseph.calcu.kotlinfirstsubmission.Adapter.FavoriteMatchAdapter
import joseph.calcu.kotlinfirstsubmission.DatabaseHelper.FavoriteNextMatch
import joseph.calcu.kotlinfirstsubmission.DatabaseHelper.FavoritePastMatch
import joseph.calcu.kotlinfirstsubmission.R
import joseph.calcu.kotlinfirstsubmission.DatabaseHelper.database
import joseph.calcu.kotlinfirstsubmission.invisible
import org.jetbrains.anko.find


class FragmentFavoritePastMatch:Fragment() {


    private var favList: MutableList<FavoritePastMatch> = mutableListOf()
    lateinit var rv:RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_matchlist,container,false)
        rv=rootview.findViewById<RecyclerView>(R.id.rv_matchlist)
        rv.layoutManager = LinearLayoutManager(this.context)
        progressBar=rootview.find<ProgressBar>(R.id.matchlist_progbar)
        activity?.title="Favorite Past Match"
        rv.adapter = FavoriteMatchAdapter(favList,this.context){
            val intent = Intent(this.context, DetailEventActivity::class.java)
            intent.putExtra(DetailEventActivity.EVENT_ID,it.eventId)
            intent.putExtra(DetailEventActivity.EVENT_TYPE,DetailEventActivity.PAST_ID)
            startActivity(intent)
        }

        return  rootview
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
        progressBar.invisible()

    }

    private fun showFavorite(){
        favList.clear()
        context?.database?.use {
            val result = select(FavoritePastMatch.TABlE_NAME)
            val favorite = result.parseList(classParser<FavoritePastMatch>())
            favList.addAll(favorite)
            rv.adapter?.notifyDataSetChanged()
        }

}

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentFavoritePastMatch().apply {
                arguments = Bundle().apply {

                }
            }
    }
}