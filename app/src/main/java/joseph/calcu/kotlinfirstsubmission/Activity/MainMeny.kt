package joseph.calcu.kotlinfirstsubmission.Activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils.replace
import android.view.Menu
import android.view.MenuItem
import joseph.calcu.kotlinfirstsubmission.Fragment.FragmentFavoriteNextMatch
import joseph.calcu.kotlinfirstsubmission.Fragment.FragmentFavoritePastMatch
import joseph.calcu.kotlinfirstsubmission.Fragment.FragmentLeagueList
import joseph.calcu.kotlinfirstsubmission.Fragment.FragmentSearchEvent
import joseph.calcu.kotlinfirstsubmission.MainActivity
import joseph.calcu.kotlinfirstsubmission.R
import kotlinx.android.synthetic.main.activity_main_meny.*
import kotlinx.android.synthetic.main.app_bar_main_meny.*
import org.jetbrains.anko.startActivity

class MainMeny : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_meny)
        setSupportActionBar(toolbar)

//        var fragment = MainActivity.newInstance()
//        startFragment(fragment)


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }




    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.FragmentLeagueList-> {
                var fragment = MainActivity.newInstance()
                startFragment(fragment)
            }
            R.id.searchevent -> {
                var fragment = FragmentSearchEvent.newInstance()
                startFragment(fragment)
            }
            R.id.favoritenextmatch->{
            var fragment = FragmentFavoriteNextMatch.newInstance()
            startFragment(fragment)
            }
            R.id.favoritepastmatch->{
                var fragment = FragmentFavoritePastMatch.newInstance()
                startFragment(fragment)
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun startFragment(fragment: Fragment)
    {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainmenu,fragment,fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }
}
