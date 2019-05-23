package joseph.calcu.kotlinfirstsubmission.Activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.*
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import joseph.calcu.kotlinfirstsubmission.*
import joseph.calcu.kotlinfirstsubmission.Fragment.FragmentNextMatch
import joseph.calcu.kotlinfirstsubmission.Fragment.FragmentPrevMatch
import joseph.calcu.kotlinfirstsubmission.Interface.LeagueInterface
import joseph.calcu.kotlinfirstsubmission.Model.LeagueModel
import joseph.calcu.kotlinfirstsubmission.Presenter.LeagueDetailPresenter

import org.jetbrains.anko.*

class DescriptionActivity : AppCompatActivity(),LeagueInterface{


    lateinit var progbar: ProgressBar
    lateinit var descImg: ImageView
    lateinit var descHeader: TextView
    lateinit var descDescription: TextView
    lateinit var buttonPrevMatch: Button
    lateinit var buttonNextMatch: Button
    lateinit var league: LeagueItem
    override fun showLoading() {
        progbar.visible()
    }

    override fun hideLoading() {
        progbar.invisible()
    }

    override fun showLeagueList(data: List<LeagueModel>) {
        val tempdata=data[0]
        descDescription.text=tempdata.strDescriptionEN
        descHeader.text = tempdata.strLeague
        Picasso.get().load(tempdata.strBadge).into(descImg)
    }

    private lateinit var presenter: LeagueDetailPresenter
    companion object {
        val descriptionID=4
        val POSITIONEXTRA="POS_EXTRA"
        val progBarId=9999
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.leaguedesc_activity)
        descImg=findViewById<ImageView>(R.id.leaguedesc_imgdetail)
        descDescription=findViewById<TextView>(R.id.leaguedesc_desc)
        descHeader=findViewById<TextView>(R.id.leaguedesc_header)
        progbar = findViewById<ProgressBar>(R.id.leaguedesc_progbar)
        buttonPrevMatch = findViewById<Button>(R.id.leaguedesc_btnprevmatch)
        buttonNextMatch = findViewById<Button>(R.id.leaguedesc_btnnextmatch)
        val intent = intent
        val request = ApiRepository()
        val gson = Gson()
        presenter = LeagueDetailPresenter(this, request, gson)
        val data= intent.getParcelableExtra<LeagueItem>(POSITIONEXTRA)
        league = intent.getParcelableExtra<LeagueItem>(POSITIONEXTRA)
        presenter.getLeagueDetail(data.id)

        buttonPrevMatch.setOnClickListener{
            var fragment = FragmentPrevMatch.newInstance(league.id)
            startFragment(fragment)
        }

        buttonNextMatch.setOnClickListener{
            var fragment = FragmentNextMatch.newInstance(league.id)
            startFragment(fragment)
        }
    }

    fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && !str.isEmpty())
            return false
        return true
    }


    private fun startFragment(fragment: Fragment)
    {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.leaguedesc_mainview,fragment,fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }
    class DescriptionActivityUI(var data: LeagueItem, var progesBar:ProgressBar): AnkoComponent<DescriptionActivity>{
        override fun createView(ui: AnkoContext<DescriptionActivity>)=with(ui){
            scrollView() {
                verticalLayout()
                {
                    progesBar=progressBar{
                      id= progBarId
                    }.lparams(dip(100),dip(100))

                    imageView() {
                        Glide.with(this).load(data.image).into(this)
//                    id =LeagueItemUi.imageId
                        padding = dip(16)
                        this@verticalLayout.gravity = Gravity.CENTER_HORIZONTAL
                    }.lparams(dip(100), dip(100))

                    textView {
                        //                    id = LeagueItemUi.nameId
                        text = data.leaguename
                        textSize = sp(10).toFloat()
                        gravity = Gravity.CENTER_HORIZONTAL
                        padding = dip(16)
                    }.lparams(wrapContent, wrapContent)

                    textView {
                        //                    id = LeagueItemUi.nameId
                        text = data.description
                        textSize = sp(10).toFloat()
                        gravity = Gravity.CENTER_HORIZONTAL
                        padding = dip(10)
                    }
                }
            }



        }



    }
}