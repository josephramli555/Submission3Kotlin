package joseph.calcu.kotlinfirstsubmission

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import joseph.calcu.kotlinfirstsubmission.Activity.DescriptionActivity
import org.jetbrains.anko.*

class MainActivity : Fragment() {

    var leagueList : MutableList<LeagueItem> = mutableListOf()

    public val IntentTitle:String= "Title"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.activity_main,container,false)
        initData()
        val toast = Toast.makeText(this@MainActivity.context, "Kamu masuk", Toast.LENGTH_SHORT)
        val list=rootview.find<RecyclerView>(R.id.rv_leaguelist)
        activity?.title="League List"
        list.layoutManager = LinearLayoutManager(this.context)
        list.adapter=RecyclerViewAdapter(leagueList,this.context)
        {
            val intent = Intent(this@MainActivity.context,
                DescriptionActivity::class.java)
            intent.putExtra(DescriptionActivity.POSITIONEXTRA,it)
            startActivity(intent)
            val toast = Toast.makeText(this@MainActivity.context, it.id, Toast.LENGTH_SHORT)
            toast.show()
        }
        return  rootview
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            MainActivity().apply {
                arguments = Bundle().apply {

                }
            }
    }


//    class MainActivityUI(var list: MutableList<LeagueItem>): AnkoComponent<MainActivity>{
//        override fun createView(ui: AnkoContext<MainActivity>)=with(ui) {
//
//           verticalLayout{
//                lparams(matchParent, matchParent)
//                orientation = LinearLayout.VERTICAL
//
//               recyclerView(){
//                    lparams(matchParent, matchParent)
//                    layoutManager=LinearLayoutManager(context)
//                    adapter=RecyclerViewAdapter(list){
//                        startActivity<DescriptionActivity>(DescriptionActivity.POSITIONEXTRA to it)
//                        val toast = Toast.makeText(context, it.id, Toast.LENGTH_SHORT)
//                        toast.show()
//                    }
//
//               }
//           }
//        }
//    }

    private fun initData(){
        val name = resources.getStringArray(R.array.league_name);
        val img = resources.obtainTypedArray(R.array.league_image)
        val desc = resources.getStringArray(R.array.league_description)
        val leagueid = resources.getStringArray(R.array.league_id);
        leagueList.clear()
        for(i in name.indices){
            leagueList.add(LeagueItem(name[i],img.getResourceId(i,0),desc[i],leagueid[i]))
        }
        img.recycle()
    }
}
