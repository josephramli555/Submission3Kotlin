package joseph.calcu.kotlinfirstsubmission.Interface

import joseph.calcu.kotlinfirstsubmission.Model.LeagueModel

interface LeagueInterface {
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data:List<LeagueModel>)
}