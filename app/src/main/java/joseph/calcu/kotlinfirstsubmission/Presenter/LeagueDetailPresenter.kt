package joseph.calcu.kotlinfirstsubmission.Presenter

import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.ApiRepository
import joseph.calcu.kotlinfirstsubmission.Database.SportDBAPI
import joseph.calcu.kotlinfirstsubmission.Interface.LeagueInterface
import joseph.calcu.kotlinfirstsubmission.Model.LeagueModel
import joseph.calcu.kotlinfirstsubmission.Model.LeagueResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeagueDetailPresenter(private val view: LeagueInterface,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson
) {
    fun getLeagueDetail(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(SportDBAPI.getLeague(league)),
                LeagueResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showLeagueList(data.leagues)
            }
        }
    }
}