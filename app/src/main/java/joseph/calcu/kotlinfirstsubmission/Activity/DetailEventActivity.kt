package joseph.calcu.kotlinfirstsubmission.Activity

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import joseph.calcu.kotlinfirstsubmission.ApiRepository
import joseph.calcu.kotlinfirstsubmission.DatabaseHelper.FavoriteNextMatch
import joseph.calcu.kotlinfirstsubmission.DatabaseHelper.FavoritePastMatch
import joseph.calcu.kotlinfirstsubmission.DatabaseHelper.MyDatabaseOpenHelper
import joseph.calcu.kotlinfirstsubmission.Interface.EventInterface
import joseph.calcu.kotlinfirstsubmission.Model.EventModel
import joseph.calcu.kotlinfirstsubmission.Presenter.EventDetailPresenter
import joseph.calcu.kotlinfirstsubmission.R
import joseph.calcu.kotlinfirstsubmission.invisible
import joseph.calcu.kotlinfirstsubmission.visible
import joseph.calcu.kotlinfirstsubmission.DatabaseHelper.database
import org.jetbrains.anko.db.insert

class DetailEventActivity:AppCompatActivity(),EventInterface {
    override fun showLoading() {
        progbar.visible()
    }

    override fun hideLoading() {
        progbar.invisible()
    }


    lateinit var currEvent:EventModel
    override fun showEventList(data: List<EventModel>) {
        val event = data[0]
        currEvent=data[0]
        homeTeamName.text=event.strHomeTeam
        awayTeamName.text=event.strAwayTeam
        homeTeamScore.text=if(event.intHomeScore!=null)event.intHomeScore else "Not Played"
        awayTeamScore.text=if(event.intAwayScore!=null)event.intAwayScore else "Not Played"
        formationHome.text=if(event.strHomeFormation!=null)event.strHomeFormation else "Not Played"
        formationAway.text=if(event.strAwayFormation!=null)event.strAwayFormation else "Not Played"
        matchDate.text= event.dateEvent
        val toast = Toast.makeText(this,event.strHomeFormation,Toast.LENGTH_SHORT)
        toast.show()
        Toast.makeText(this,event.strHomeFormation,Toast.LENGTH_SHORT).show()
        if(event.strThumb!=null)
            Picasso.get().load(event.strThumb).into(matchImg)
    }
    companion object {
        var EVENT_ID="Event"
        var PAST_ID="PAST_EVENT"
        var NEXT_ID="NEXT_EVENT"
        var EVENT_TYPE="EVENT_TYPE"
    }


    lateinit var formationHome:TextView
    lateinit var formationAway:TextView
    lateinit var homeTeamName:TextView
    lateinit var awayTeamName:TextView
    lateinit var homeTeamScore:TextView
    lateinit var awayTeamScore:TextView
    lateinit var matchDate:TextView
    lateinit var matchImg:ImageView
    lateinit var progbar:ProgressBar
    lateinit var favButton:Button
    lateinit var eventType:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detailevent)
        formationHome=findViewById<TextView>(R.id.eventdetail_formationhome)
        formationAway=findViewById<TextView>(R.id.eventdetail_formationaway)

        homeTeamName=findViewById<TextView>(R.id.eventdetail_hometam)
        awayTeamName=findViewById<TextView>(R.id.eventdetail_awayteam)

        homeTeamScore=findViewById<TextView>(R.id.eventdetail_scorehome)
        awayTeamScore=findViewById<TextView>(R.id.eventdetail_scoreaway)

        matchDate=findViewById<TextView>(R.id.eventdetail_matchdate)
        matchImg=findViewById<ImageView>(R.id.eventdetail_poster)
        progbar=findViewById<ProgressBar>(R.id.eventdetail_progbar)

        favButton=findViewById<Button>(R.id.eventdetail_buttonfav)

        favButton.setOnClickListener{
            addToFavorite()
        }
        var intent =intent
        var matchid = intent.getStringExtra(EVENT_ID)
        eventType=intent.getStringExtra(EVENT_TYPE)
        val request = ApiRepository()
        val gson = Gson()
        var presenter : EventDetailPresenter
        presenter = EventDetailPresenter(this, request, gson)
        presenter.getEventDetail(matchid)
    }

    private fun addToFavorite(){
        if(eventType.equals(PAST_ID)) {
            try {
                database.use {
                    insert(
                                FavoritePastMatch.TABlE_NAME,
                        FavoritePastMatch.EVENT_ID to currEvent.idEvent,
                                FavoritePastMatch.EVENT_NAME to currEvent.strEvent,
                                FavoritePastMatch.AWAY_NAME to currEvent.strAwayTeam,
                                FavoritePastMatch.HOME_NAME to currEvent.strHomeTeam,
                                FavoritePastMatch.SCORE_AWAY to currEvent.intAwayScore,
                                FavoritePastMatch.SCORE_HOME to currEvent.intHomeScore,
                                FavoritePastMatch.DATE to currEvent.strDate
                    )
                }
            } catch (e: SQLiteConstraintException) {

                val toast= Toast.makeText(this, "Insertion Past inserted",Toast.LENGTH_SHORT)
                toast.show()
            }
            val toast= Toast.makeText(this, "Data Past inserted",Toast.LENGTH_SHORT)
            toast.show()
        }
        else if(eventType.equals(NEXT_ID)){
            try{
                database.use {
                    insert(
                        FavoriteNextMatch.TABlE_NAME,
                        FavoriteNextMatch.EVENT_ID to currEvent.idEvent,
                        FavoriteNextMatch.EVENT_NAME to currEvent.strEvent,
                        FavoriteNextMatch.AWAY_NAME to currEvent.strAwayTeam,
                        FavoriteNextMatch.HOME_NAME to currEvent.strHomeTeam,
                        FavoriteNextMatch.SCORE_AWAY to currEvent.intAwayScore,
                        FavoriteNextMatch.SCORE_HOME to currEvent.intHomeScore,
                        FavoriteNextMatch.DATE to currEvent.strDate)
                }
            }catch (e: SQLiteConstraintException){

                val toast= Toast.makeText(this, "Insertion Next Failed",Toast.LENGTH_SHORT)
                toast.show()
            }

            val toast= Toast.makeText(this, "Data Next inserted",Toast.LENGTH_SHORT)
            toast.show()
        }
    }

}