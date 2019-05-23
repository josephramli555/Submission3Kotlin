package joseph.calcu.kotlinfirstsubmission

import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.Interface.EventInterface
import joseph.calcu.kotlinfirstsubmission.Interface.MatchInterface
import joseph.calcu.kotlinfirstsubmission.Model.EventModel
import joseph.calcu.kotlinfirstsubmission.Model.EventResponse
import joseph.calcu.kotlinfirstsubmission.Model.MatchModel
import joseph.calcu.kotlinfirstsubmission.Model.MatchResponse
import joseph.calcu.kotlinfirstsubmission.Presenter.EventDetailPresenter
import joseph.calcu.kotlinfirstsubmission.Presenter.MatchListPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchListPresenterTest {



    @Mock
    private  lateinit var view: MatchInterface

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository : ApiRepository

    private lateinit var presenter : MatchListPresenter

    @Before
    fun init()
    {
        MockitoAnnotations.initMocks(this)
        presenter = MatchListPresenter(view, apiRepository, gson)
    }

    @Test
    fun getEventDetail()
    {
        val matchList : MutableList<MatchModel> = mutableListOf()
        val response = MatchResponse(matchList)
        val eventId = "582415"

        Mockito.`when`(
            gson.fromJson(
                "",
                MatchResponse::class.java
            )
        ).thenReturn(response)



    }
}