package joseph.calcu.kotlinfirstsubmission.Database

import joseph.calcu.kotlinfirstsubmission.BuildConfig

object SportDBAPI {
    fun getLeague(league:String?):String{
        return "${BuildConfig.BASE_URL}api/v1/json/1/lookupleague.php?id=$league";
    }
//    fun getLeague(league:String?):String{
//        return BuildConfig.BASE_URL +"api/v1/json/1/"+"lookupleague.php?id="+league;
//    }
    fun getNextMatch(league:String?):String{
        return BuildConfig.BASE_URL+"api/v1/json/1/eventsnextleague.php?id="+league
    }
    fun getPastMatch(league:String?):String{
        return BuildConfig.BASE_URL+"api/v1/json/1/eventspastleague.php?id="+league
    }
    fun getCurrEvent(Event:String?):String{
        return BuildConfig.BASE_URL+"api/v1/json/1/lookupevent.php?id="+Event
    }
    fun getSearchEvent(Event:String?):String{
        return BuildConfig.BASE_URL+"api/v1/json/1/searchevents.php?e="+Event
    }

    fun getTeamDetail(idTeam:String?):String{
       return BuildConfig.BASE_URL+"api/v1/json/1/lookupteam.php?id="+idTeam
    }
}