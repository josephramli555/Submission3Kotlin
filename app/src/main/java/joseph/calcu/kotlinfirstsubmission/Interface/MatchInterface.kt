package joseph.calcu.kotlinfirstsubmission.Interface

import joseph.calcu.kotlinfirstsubmission.Model.EventModel
import joseph.calcu.kotlinfirstsubmission.Model.MatchModel

interface MatchInterface {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data:List<MatchModel>?)
}