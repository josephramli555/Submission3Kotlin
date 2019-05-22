package joseph.calcu.kotlinfirstsubmission.DatabaseHelper

data class FavoritePastMatch (val id:Long?,val eventId:String?,val eventName:String?, val homeName:String?,
                              val awayName:String?, val scoreHome:String?,val scoreAway:String?,val date:String?)
{
    companion object {
        const val TABlE_NAME:String="TABLE_PAST"
        const val ID:String ="ID_"
        const val EVENT_ID:String ="EVENT_ID"
        const val EVENT_NAME:String="EVENT_NAME"
        const val HOME_NAME:String="HOME_NAME"
        const val AWAY_NAME:String="AWAY_NAME"
        const val SCORE_HOME:String="SCORE_HOME"
        const val SCORE_AWAY:String="SCORE_AWAY"
        const val DATE:String="DATE"
    }
}