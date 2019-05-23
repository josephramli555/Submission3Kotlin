package joseph.calcu.kotlinfirstsubmission

import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.Interface.EventInterface
import joseph.calcu.kotlinfirstsubmission.Model.*
import joseph.calcu.kotlinfirstsubmission.Presenter.EventDetailPresenter
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class EventDetailPresenterTest {



    @Mock
    private  lateinit var view:EventInterface

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository : ApiRepository


    private lateinit var presenter: EventDetailPresenter

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Before
    fun init()
    {
        MockitoAnnotations.initMocks(this)
        presenter = EventDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getEventDetail()
    {
        val eventDetail : MutableList<EventModel> = mutableListOf()
        val response = EventResponse(eventDetail)
        val eventId = "582415"
        runBlocking {
            `when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            `when`(
                gson.fromJson(
                    "",
                    EventResponse::class.java
                )
            ).thenReturn(response)


            presenter.getEventDetail(eventId)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showEventList(eventDetail)
            Mockito.verify(view).hideLoading()
        }
    }


}