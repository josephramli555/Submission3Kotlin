package joseph.calcu.kotlinfirstsubmission.Presenter

import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.ApiRepository
import joseph.calcu.kotlinfirstsubmission.Database.SportDBAPI
import joseph.calcu.kotlinfirstsubmission.Interface.SearchEventInterface
import joseph.calcu.kotlinfirstsubmission.Model.SearchEventResponse
import joseph.calcu.kotlinfirstsubmission.coroutine.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchEventPresenter(private val view: SearchEventInterface,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getSearchEventList(EventId: String?) {
        view.showLoading()
        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(SportDBAPI.getSearchEvent(EventId)).await(),
                SearchEventResponse::class.java
            )


                view.hideLoading()
                view.showEventList(data.event)

        }
    }
}