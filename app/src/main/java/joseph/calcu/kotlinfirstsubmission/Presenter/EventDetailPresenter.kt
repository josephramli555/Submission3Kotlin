package joseph.calcu.kotlinfirstsubmission.Presenter

import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.ApiRepository
import joseph.calcu.kotlinfirstsubmission.Database.SportDBAPI
import joseph.calcu.kotlinfirstsubmission.Interface.EventInterface
import joseph.calcu.kotlinfirstsubmission.Interface.MatchInterface
import joseph.calcu.kotlinfirstsubmission.Model.EventResponse
import joseph.calcu.kotlinfirstsubmission.coroutine.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.coroutines.experimental.bg
class EventDetailPresenter(private val view: EventInterface,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getEventDetail(eventId:String?)
    {
        view.showLoading()
        GlobalScope.launch(context.main){
            val data =
                gson.fromJson(
                    apiRepository.doRequest(SportDBAPI.getCurrEvent(eventId)).await(),
                    EventResponse::class.java)

                view.showEventList(data.events)
                view.hideLoading()
        }

    }
}