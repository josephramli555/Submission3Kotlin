package joseph.calcu.kotlinfirstsubmission.Presenter

import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.ApiRepository
import joseph.calcu.kotlinfirstsubmission.Database.SportDBAPI
import joseph.calcu.kotlinfirstsubmission.Interface.BadgesHomeInterface
import joseph.calcu.kotlinfirstsubmission.Model.TeamDetailResponse
import joseph.calcu.kotlinfirstsubmission.coroutine.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamBadgesPresenter (private val view: BadgesHomeInterface,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    fun getTeamBadges(teamId: String?,type:Int?) {
        view.showBadgeLoading()
        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(SportDBAPI.getTeamDetail(teamId)).await(),
                TeamDetailResponse::class.java
            )

                view.hideBadgeLoading()
                view.showBadgeList(data.teams,type)

        }
    }
}