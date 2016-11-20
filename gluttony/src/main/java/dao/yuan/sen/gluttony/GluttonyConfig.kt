package dao.yuan.sen.gluttony

import android.database.sqlite.SQLiteDatabase

/**
 * Created by SenYuYuan on 2016/11/17.
 */


data class GluttonyConfig(
        val name: String = "Gluttony",
        val factory: SQLiteDatabase.CursorFactory? = null,
        val version: Int = 1
)