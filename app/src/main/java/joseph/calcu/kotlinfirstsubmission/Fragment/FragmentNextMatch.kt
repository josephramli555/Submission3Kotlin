package joseph.calcu.kotlinfirstsubmission.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.Activity.DetailEventActivity
import joseph.calcu.kotlinfirstsubmission.Activity.MainMeny
import joseph.calcu.kotlinfirstsubmission.Adapter.MatchListAdapter
import joseph.calcu.kotlinfirstsubmission.ApiRepository
import joseph.calcu.kotlinfirstsubmission.Interface.MatchInterface
import joseph.calcu.kotlinfirstsubmission.Model.MatchModel
import joseph.calcu.kotlinfirstsubmission.Presenter.MatchListPresenter
import joseph.calcu.kotlinfirstsubmission.R
import joseph.calcu.kotlinfirstsubmission.invisible
import joseph.calcu.kotlinfirstsubmission.visible

class FragmentNextMatch: Fragment(),MatchInterface {
    override fun showLoading() {
        progbar.visible()
    }

    override fun hideLoading() {
        progbar.invisible()
    }

    override fun showMatchList(data: List<MatchModel>?) {
        var list : List<MatchModel>
        if(data!=null)
        {
            list=data
            rv.adapter=MatchListAdapter(list,this.context){
                val intent = Intent(this.context,DetailEventActivity::class.java)
                intent.putExtra(DetailEventActivity.EVENT_ID,it.idEvent)
                intent.putExtra(DetailEventActivity.EVENT_TYPE,DetailEventActivity.NEXT_ID)
                startActivity(intent)
            }
        }else{
            var toast = Toast.makeText(this.context, "Data kosong",Toast.LENGTH_SHORT)
            toast.show()
        }

    }

    lateinit var presenter : MatchListPresenter
    lateinit var progbar: ProgressBar
    lateinit var rv: RecyclerView
    var KEY_ID="dataid"
    var leagueList : MutableList<MatchModel> = mutableListOf()

    public val IntentTitle:String= "Title"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_matchlist,container,false)


        var args = arguments
        var matchid = args?.getString(KEY_ID,"123")
        val request = ApiRepository()
        val gson = Gson()
        progbar = rootview.findViewById<ProgressBar>(R.id.matchlist_progbar)
        rv = rootview.findViewById<RecyclerView>(R.id.rv_matchlist)
        presenter = MatchListPresenter(this, request, gson)
        presenter.getNextMatchList(matchid)
        activity?.title="Match Detail"
        rv.layoutManager = LinearLayoutManager(this.context)

        return  rootview
    }


    companion object {
        @JvmStatic
        fun newInstance(keyid:String?) =
            FragmentNextMatch().apply {
                arguments = Bundle().apply {
                    putString(KEY_ID,keyid)
                }
            }
    }


}