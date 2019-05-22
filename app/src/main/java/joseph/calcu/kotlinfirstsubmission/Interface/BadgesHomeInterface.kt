package joseph.calcu.kotlinfirstsubmission.Interface

import joseph.calcu.kotlinfirstsubmission.Model.TeamDetailModel

interface BadgesHomeInterface {
    fun showBadgeLoading()
    fun hideBadgeLoading()
    fun showBadgeList(data:List<TeamDetailModel>,type:Int?)
}