package joseph.calcu.kotlinfirstsubmission.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import joseph.calcu.kotlinfirstsubmission.R

class FragmentLeagueList : Fragment() {
    val ibu:String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragmentleaguelist,container,false)
        return  rootview
    }

    companion object {
        @JvmStatic
        fun newInstance(temp:String) =
            FragmentLeagueList().apply {
                arguments = Bundle().apply {

                }
            }
    }
}