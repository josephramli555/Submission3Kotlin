package joseph.calcu.kotlinfirstsubmission.DatabaseHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx:Context) : ManagedSQLiteOpenHelper(ctx,"FavoriteTeam.db",null,1
)
{

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteNextMatch.TABlE_NAME,true,
            FavoriteNextMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT+UNIQUE,
            FavoriteNextMatch.EVENT_ID to TEXT,
            FavoriteNextMatch.EVENT_NAME to TEXT,
            FavoriteNextMatch.AWAY_NAME to TEXT,
            FavoriteNextMatch.HOME_NAME to TEXT,
            FavoriteNextMatch.SCORE_AWAY to TEXT,
            FavoriteNextMatch.SCORE_HOME to TEXT,
            FavoriteNextMatch.DATE to TEXT)

        db.createTable(FavoritePastMatch.TABlE_NAME,true,
            FavoritePastMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT+UNIQUE,
            FavoritePastMatch.EVENT_ID to TEXT,
            FavoritePastMatch.EVENT_NAME to TEXT,
            FavoritePastMatch.AWAY_NAME to TEXT,
            FavoritePastMatch.HOME_NAME to TEXT,
            FavoritePastMatch.SCORE_AWAY to TEXT,
            FavoritePastMatch.SCORE_HOME to TEXT,
            FavoritePastMatch.DATE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoritePastMatch.TABlE_NAME,true)
        db.dropTable(FavoriteNextMatch.TABlE_NAME,true)
    }


}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)