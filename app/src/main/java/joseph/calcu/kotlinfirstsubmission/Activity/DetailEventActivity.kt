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
import joseph.calcu.kotlinfirstsubmission.Interface.BadgesHomeInterface
import joseph.calcu.kotlinfirstsubmission.Model.TeamDetailModel
import joseph.calcu.kotlinfirstsubmission.Presenter.TeamBadgesPresenter
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.colorAttr
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select

class DetailEventActivity:AppCompatActivity(),EventInterface,BadgesHomeInterface {
    override fun showBadgeLoading() {
        progbar.visible()
    }

    override fun hideBadgeLoading() {
        progbar.invisible()
    }

    override fun showBadgeList(data: List<TeamDetailModel>, type: Int?) {
        if(type==1)
        {
            Picasso.get().load(data[0].strTeamBadge).into(homeBadge)
        }
        else
        {
            Picasso.get().load(data[0].strTeamBadge).into(awayBadge)
        }
    }

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
        favoriteState()
        setButton()

        val request = ApiRepository()
        val gson = Gson()
        var presenter : TeamBadgesPresenter
        presenter = TeamBadgesPresenter(this, request, gson)
        presenter.getTeamBadges(event.idHomeTeam,1)
        presenter.getTeamBadges(event.idAwayTeam,2)
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
    lateinit var matchId:String
    lateinit var homeBadge:ImageView
    lateinit var awayBadge:ImageView
    var currEventId:String="test"
    var isAdded:Boolean = false
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
        homeBadge=findViewById<ImageView>(R.id.detailevent_badgeshome)
        awayBadge=findViewById<ImageView>(R.id.detailevent_badgesaway)

        var intent =intent
        matchId = intent.getStringExtra(EVENT_ID)
        eventType=intent.getStringExtra(EVENT_TYPE)
        favButton.setOnClickListener{
            if(!isAdded){
                addToFavorite()
                favoriteState()
                setButton()
            }

            else
            {
                removeFromFavorite()
                favoriteState()
                setButton()
            }

        }
        val request = ApiRepository()
        val gson = Gson()
        var presenter : EventDetailPresenter
        presenter = EventDetailPresenter(this, request, gson)
        presenter.getEventDetail(matchId)

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

    private fun setButton(){
        if(isAdded){
            favButton.text="Remove From Favorite"
        }
    }

    private fun favoriteState(){
        if(eventType.equals(PAST_ID))
        {
            database.use {
                val result = select(FavoritePastMatch.TABlE_NAME)
                    .whereArgs("(EVENT_ID = {id})",
                        "id" to matchId)
                val favorite = result.parseList(classParser<FavoritePastMatch>())
                if (!favorite.isEmpty())
                    isAdded = true
            }
        }else
        {
            database.use {
                val result = select(FavoriteNextMatch.TABlE_NAME)
                    .whereArgs("(EVENT_ID = {id})",
                        "id" to matchId)
                val favorite = result.parseList(classParser<FavoritePastMatch>())
                if (!favorite.isEmpty())
                    isAdded = true
            }
        }

    }

    private fun removeFromFavorite(){
        if(eventType.equals(PAST_ID))
        {
            try {
                database.use {
                    delete(FavoritePastMatch.TABlE_NAME, "(EVENT_ID = {id})",
                        "id" to matchId)
                }
                var toast = Toast.makeText(this, "Data is Deleted",Toast.LENGTH_SHORT)
                toast.show()
            } catch (e: SQLiteConstraintException){
            }
        }
        else{
            try {
                database.use {
                    delete(FavoriteNextMatch.TABlE_NAME, "(EVENT_ID = {id})",
                        "id" to matchId)
                }
                var toast = Toast.makeText(this, "Data is Deleted",Toast.LENGTH_SHORT)
                toast.show()
            } catch (e: SQLiteConstraintException){
            }
        }

    }

}