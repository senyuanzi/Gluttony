package dao.yuan.sen.gluttony.sqlite_module.operator

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException

/**
 * Created by Administrator on 2016/11/26.
 */
inline fun SQLiteDatabase.tryDo(functor: SQLiteDatabase.() -> Any?): Any? {
    return try {
        functor()
    } catch (ex: SQLiteException) {
        if (ex.message?.contains("no such table") ?: false) "no such table"
        else throw ex
    }
}



