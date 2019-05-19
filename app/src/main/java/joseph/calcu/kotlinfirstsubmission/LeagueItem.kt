package joseph.calcu.kotlinfirstsubmission

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueItem(val leaguename:String?,val image:Int?, val description:String?,val id:String?):Parcelable {
}