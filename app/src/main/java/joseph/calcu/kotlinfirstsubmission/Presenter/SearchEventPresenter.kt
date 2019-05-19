package joseph.calcu.kotlinfirstsubmission.Presenter

import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.ApiRepository
import joseph.calcu.kotlinfirstsubmission.Database.SportDBAPI
import joseph.calcu.kotlinfirstsubmission.Interface.SearchEventInterface
import joseph.calcu.kotlinfirstsubmission.Model.SearchEventResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchEventPresenter(private val view: SearchEventInterface,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson
) {
    fun getSearchEventList(EventId: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(SportDBAPI.getSearchEvent(EventId)),
                SearchEventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showEventList(data.event)
            }
        }
    }
}