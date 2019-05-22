package joseph.calcu.kotlinfirstsubmission

import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.Database.SportDBAPI
import joseph.calcu.kotlinfirstsubmission.Interface.EventInterface
import joseph.calcu.kotlinfirstsubmission.Model.EventModel
import joseph.calcu.kotlinfirstsubmission.Model.EventResponse
import joseph.calcu.kotlinfirstsubmission.Presenter.EventDetailPresenter
import joseph.calcu.kotlinfirstsubmission.coroutine.TestContextProvider
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class EventDetailPresenterTest {


    @Mock
    private  lateinit var view:EventInterface

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository : ApiRepository

    private lateinit var presenter :EventDetailPresenter

    @Before
    fun init()
    {
        MockitoAnnotations.initMocks(this)
        presenter = EventDetailPresenter(view, apiRepository, gson,TestContextProvider())
    }

    @Test
    fun getEventDetail()
    {
        val eventDetail : MutableList<EventModel> = mutableListOf()
        val response = EventResponse(eventDetail)
        val eventId = "582415"

        runBlocking{
            `when`(gson.fromJson(apiRepository.doRequest(SportDBAPI.getCurrEvent(eventId)).await(),
                EventResponse::class.java)).thenReturn(response)

            presenter.getEventDetail(eventId)

            verify(view).showLoading()
            verify(view).showEventList(eventDetail)
            verify(view).hideLoading()
    }
}
}