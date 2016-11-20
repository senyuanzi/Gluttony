package dao.yuan.sen.gluttony

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import dao.yuan.sen.gluttony.GluttonyConfig
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

/**
 * Created by SenYuYuan on 2016/11/17.
 */


class GluttonyDataBaseOpenHelper(ctx: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : ManagedSQLiteOpenHelper(ctx, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private var instance: GluttonyDataBaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context, databaseConfig: GluttonyConfig): GluttonyDataBaseOpenHelper {
            if (instance == null) {
                instance = GluttonyDataBaseOpenHelper(ctx.applicationContext, databaseConfig.name, databaseConfig.factory, databaseConfig.version)
            }
            return instance!!
        }
    }

}
