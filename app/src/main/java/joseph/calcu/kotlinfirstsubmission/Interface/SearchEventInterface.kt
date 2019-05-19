package joseph.calcu.kotlinfirstsubmission.Interface

import joseph.calcu.kotlinfirstsubmission.Model.SearchEventModel

interface SearchEventInterface {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data:List<SearchEventModel>?)
}