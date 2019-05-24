package joseph.calcu.kotlinfirstsubmission

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import joseph.calcu.kotlinfirstsubmission.Activity.MainMeny

import joseph.calcu.kotlinfirstsubmission.Fragment.FragmentSearchEvent
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchViewTest {
    @Rule
    @JvmField
    var searchRule = ActivityTestRule(MainMeny::class.java)


    @Test
    fun testSearchView() {
//        onView(withId(R.id.searchmatch_rv)).check(matches(isDisplayed()))
        onView(withId(R.id.action_search)).check(matches(isDisplayed()))
        onView(withId(R.id.action_search)).perform(click())
    }

//    @Test
//    fun checkRecyclerView()
//    {
//        onView(withId(R.id.searchmatch_rv)).check(matches(isDisplayed()))
//    }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
        var fragment = FragmentSearchEvent.newInstance()
        searchRule.getActivity().
            supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainmenu,fragment,fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }
}