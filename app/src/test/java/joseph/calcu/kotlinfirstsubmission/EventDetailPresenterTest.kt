package joseph.calcu.kotlinfirstsubmission

import com.google.gson.Gson
import joseph.calcu.kotlinfirstsubmission.Interface.*
import joseph.calcu.kotlinfirstsubmission.Model.*
import joseph.calcu.kotlinfirstsubmission.Presenter.*
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
    private lateinit var leagueView: LeagueInterface
    @Mock
    private lateinit var badgesView: BadgesHomeInterface
    @Mock
    private lateinit var matchView: MatchInterface
    @Mock
    private lateinit var searchEventView:SearchEventInterface

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository : ApiRepository


    private lateinit var presenter: EventDetailPresenter
    private lateinit var leaguePresenter: LeagueDetailPresenter
    private lateinit var matchPresenter: MatchListPresenter
    private lateinit var searchEventPresenter: SearchEventPresenter
    private lateinit var teamBadgesPresenter: TeamBadgesPresenter

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Before
    fun init()
    {
        MockitoAnnotations.initMocks(this)
        presenter = EventDetailPresenter(view, apiRepository, gson, TestContextProvider())
        leaguePresenter = LeagueDetailPresenter(leagueView, apiRepository, gson, TestContextProvider())
        matchPresenter = MatchListPresenter(matchView, apiRepository, gson, TestContextProvider())
        searchEventPresenter= SearchEventPresenter(searchEventView, apiRepository, gson, TestContextProvider())
        teamBadgesPresenter = TeamBadgesPresenter(badgesView, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getEventDetail()
    {
        val eventDetail : List<EventModel> = mutableListOf()
        val response = EventResponse(eventDetail)
        val eventId = "582415"
        runBlocking {
            `when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            `when`(apiResponse.await()).thenReturn("")

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



    @Test
    fun getLeagueDetail()
    {
        val leagueList : MutableList<LeagueModel> = mutableListOf()
        val leagueResponse = LeagueResponse(leagueList)
        val leagueId = "4328"
        runBlocking {
            `when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            `when`(apiResponse.await()).thenReturn("")

            `when`(
                gson.fromJson(
                    "",
                    leagueResponse::class.java
                )
            ).thenReturn(leagueResponse)


            leaguePresenter.getLeagueDetail(leagueId)
            Mockito.verify(leagueView).showLoading()
            Mockito.verify(leagueView).showLeagueList(leagueList)
            Mockito.verify(leagueView).hideLoading()
        }
    }

    @Test
    fun getNextMatch()
    {
        val matchList : MutableList<MatchModel> = mutableListOf()
        val matchResponse = MatchResponse(matchList)
        val matchId= "4328"
        runBlocking {
            `when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            `when`(apiResponse.await()).thenReturn("")

            `when`(
                gson.fromJson(
                    "",
                    matchResponse::class.java
                )
            ).thenReturn(matchResponse)


            matchPresenter.getNextMatchList(matchId)
            Mockito.verify(matchView).showLoading()
            Mockito.verify(matchView).showMatchList(matchList)
            Mockito.verify(matchView).hideLoading()
        }
    }

    @Test
    fun getPrevMatch()
    {
        val matchList : MutableList<MatchModel> = mutableListOf()
        val matchResponse = MatchResponse(matchList)
        val matchId= "4328"
        runBlocking {
            `when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            `when`(apiResponse.await()).thenReturn("")

            `when`(
                gson.fromJson(
                    "",
                    matchResponse::class.java
                )
            ).thenReturn(matchResponse)


            matchPresenter.getPrevMatchList(matchId)
            Mockito.verify(matchView).showLoading()
            Mockito.verify(matchView).showMatchList(matchList)
            Mockito.verify(matchView).hideLoading()
        }
    }

    @Test
    fun getSearchEvent()
    {

        val searchEventList : MutableList<SearchEventModel> = mutableListOf()
        val searchEventResponse = SearchEventResponse(searchEventList)
        val eventString="Barcleona"
        runBlocking {
            `when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            `when`(apiResponse.await()).thenReturn("")

            `when`(
                gson.fromJson(
                    "",
                    searchEventResponse::class.java
                )
            ).thenReturn(searchEventResponse)


            searchEventPresenter.getSearchEventList(eventString)
            Mockito.verify(searchEventView).showLoading()
            Mockito.verify(searchEventView).showEventList(searchEventList)
            Mockito.verify(searchEventView).hideLoading()
        }
    }

    @Test
    fun getBadges() {
        val teamDetailList: MutableList<TeamDetailModel> = mutableListOf()
        val teamDetailResponse = TeamDetailResponse(teamDetailList)
        val teamId = "133670"
        runBlocking {
            `when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            `when`(apiResponse.await()).thenReturn("")

            `when`(
                gson.fromJson(
                    "",
                    teamDetailResponse::class.java
                )
            ).thenReturn(teamDetailResponse)


            teamBadgesPresenter.getTeamBadges(teamId, 1)
            Mockito.verify(badgesView).showBadgeLoading()
            Mockito.verify(badgesView).showBadgeList(teamDetailList, 1)
            Mockito.verify(badgesView).hideBadgeLoading()
        }


    }

}