package joseph.calcu.kotlinfirstsubmission.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.*
import joseph.calcu.kotlinfirstsubmission.Adapter.SearchEventAdapter
import joseph.calcu.kotlinfirstsubmission.Interface.SearchEventInterface
import joseph.calcu.kotlinfirstsubmission.Model.SearchEventModel
import joseph.calcu.kotlinfirstsubmission.Presenter.SearchEventPresenter

class FragmentSearchEvent: Fragment(),SearchEventInterface {
    override fun showLoading() {
        progbar.visible()
    }

    override fun hideLoading() {
        progbar.invisible()
    }

    override fun showEventList(data: List<SearchEventModel>?) {
        if (!EspressoIdlingResource.idlingresource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
        eventList.clear()
        if(data!=null)
        {
            var toast= Toast.makeText(this.context, "Data Berisi", Toast.LENGTH_SHORT)
            var List : List<SearchEventModel>
            toast.show()
            for(i in eventList.indices){
                eventList.add(data[i])

            }
            List=data
            rv.adapter=SearchEventAdapter(List,this.context){

            }
        }else
        {
            var toast= Toast.makeText(this.context, "Data Empty", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    lateinit var presenter : SearchEventPresenter
    lateinit var progbar: ProgressBar
    lateinit var rv: RecyclerView

    var eventList : MutableList<SearchEventModel> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview =inflater.inflate(R.layout.fragment_searchmatch, container, false)
        activity?.title="Search Event"
        rv=rootview.findViewById<RecyclerView>(R.id.searchmatch_rv)
        rv.layoutManager = LinearLayoutManager(this.context)
        progbar=rootview.findViewById<ProgressBar>(R.id.searchmatch_progbar)
        progbar.invisible()
        val request = ApiRepository()
        val gson = Gson()
        presenter= SearchEventPresenter(this,request,gson)
        setHasOptionsMenu(true)
        return rootview;
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu,menu)
        var searchitem = menu?.findItem(R.id.action_search)
        var searchview = searchitem?.actionView as SearchView

        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                EspressoIdlingResource.increment()
                presenter.getSearchEventList(query);
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText?.length==0)
                {
                    showEventList(eventList)

                }
                return false
            }

        })

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentSearchEvent().apply {
                arguments = Bundle().apply {

                }
            }
    }
}