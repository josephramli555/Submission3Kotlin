package joseph.calcu.kotlinfirstsubmission.Presenter

import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.ApiRepository
import joseph.calcu.kotlinfirstsubmission.Database.SportDBAPI
import joseph.calcu.kotlinfirstsubmission.Interface.LeagueInterface
import joseph.calcu.kotlinfirstsubmission.Interface.MatchInterface
import joseph.calcu.kotlinfirstsubmission.Model.LeagueResponse
import joseph.calcu.kotlinfirstsubmission.Model.MatchResponse
import joseph.calcu.kotlinfirstsubmission.coroutine.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchListPresenter(private val view: MatchInterface,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()
)  {
    fun getNextMatchList(league: String?) {
        view.showLoading()
        GlobalScope.launch (context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(SportDBAPI.getNextMatch(league)).await(),
                MatchResponse::class.java
            )


                view.hideLoading()
                view.showMatchList(data.events)

        }
    }

    fun getPrevMatchList(league: String?) {
        view.showLoading()
        GlobalScope.launch (context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(SportDBAPI.getPastMatch(league)).await(),
                MatchResponse::class.java
            )

                view.hideLoading()
                view.showMatchList(data.events)

        }
    }


}