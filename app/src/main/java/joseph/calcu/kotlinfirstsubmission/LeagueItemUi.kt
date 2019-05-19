package joseph.calcu.kotlinfirstsubmission

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class LeagueItemUi:AnkoComponent<ViewGroup> {
    companion object {
        val nameId =1
        val  imageId=2
        val descId= 3
    }

    override fun createView(ui: AnkoContext<ViewGroup>)= with(ui) {
        linearLayout{
            orientation=LinearLayout.HORIZONTAL
            lparams(matchParent, wrapContent)
            padding = dip(16)

            imageView(){
                id = imageId;
                imageResource=R.drawable.american_mayor_league
            }.lparams(dip(100),dip(100))

            textView(){
                id= nameId
                text="nama Leauge"
            }.lparams(matchParent, wrapContent){
                margin = dip(10)
                gravity = Gravity.CENTER_VERTICAL
            }
        }
    }
}