package joseph.calcu.kotlinfirstsubmission.Interface

import joseph.calcu.kotlinfirstsubmission.Model.EventModel

interface EventInterface {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data:List<EventModel>)
}