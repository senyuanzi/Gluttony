package dao.yuan.sen.gluttony

import android.app.Application

/**
 * Created by SenYuYuan on 2016/3/5.
 */


object Gluttony {
    var isDebug = false

    lateinit var database: GluttonyDataBaseOpenHelper

    fun init(baseApp: Application, config: GluttonyConfig) {
        database = GluttonyDataBaseOpenHelper.getInstance(baseApp, config)
    }
}