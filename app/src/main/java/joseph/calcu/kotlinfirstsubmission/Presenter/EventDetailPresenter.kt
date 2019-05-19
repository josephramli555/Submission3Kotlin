package joseph.calcu.kotlinfirstsubmission.Presenter

import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.ApiRepository
import joseph.calcu.kotlinfirstsubmission.Database.SportDBAPI
import joseph.calcu.kotlinfirstsubmission.Interface.EventInterface
import joseph.calcu.kotlinfirstsubmission.Interface.MatchInterface
import joseph.calcu.kotlinfirstsubmission.Model.EventResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EventDetailPresenter(private val view: EventInterface,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson
) {
    fun getEventDetail(eventId:String?)
    {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(SportDBAPI.getCurrEvent(eventId)),
                EventResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }
}