package joseph.calcu.kotlinfirstsubmission.Presenter

import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.ApiRepository
import joseph.calcu.kotlinfirstsubmission.Database.SportDBAPI
import joseph.calcu.kotlinfirstsubmission.Interface.LeagueInterface
import joseph.calcu.kotlinfirstsubmission.Interface.MatchInterface
import joseph.calcu.kotlinfirstsubmission.Model.LeagueResponse
import joseph.calcu.kotlinfirstsubmission.Model.MatchResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchListPresenter(private val view: MatchInterface,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson
)  {
    fun getNextMatchList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(SportDBAPI.getNextMatch(league)),
                MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }

    fun getPrevMatchList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(SportDBAPI.getPastMatch(league)),
                MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }


}