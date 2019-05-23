package joseph.calcu.kotlinfirstsubmission.Presenter

import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.ApiRepository
import joseph.calcu.kotlinfirstsubmission.Database.SportDBAPI
import joseph.calcu.kotlinfirstsubmission.Interface.LeagueInterface
import joseph.calcu.kotlinfirstsubmission.Model.LeagueModel
import joseph.calcu.kotlinfirstsubmission.Model.LeagueResponse
import joseph.calcu.kotlinfirstsubmission.coroutine.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeagueDetailPresenter(private val view: LeagueInterface,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,private val context: CoroutineContextProvider = CoroutineContextProvider()

) {
    fun getLeagueDetail(league: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(SportDBAPI.getLeague(league)).await(),
                LeagueResponse::class.java
            )
                view.hideLoading()
                view.showLeagueList(data.leagues)

        }
    }
}