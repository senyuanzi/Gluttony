package dao.yuan.sen.gluttony

import android.app.Application
import io.realm.Realm
import org.jetbrains.anko.runOnUiThread

/**
 * Created by SenYuYuan on 2016/3/5.
 */


object Gluttony {
    var isDebug = false

    lateinit var database: GluttonyDataBaseOpenHelper

    lateinit var realm: Realm

    fun init(baseApp: Application, config: GluttonyConfig) {
        database = GluttonyDataBaseOpenHelper.getInstance(baseApp, config)
        Realm.init(baseApp)
        baseApp.runOnUiThread {
            realm = Realm.getDefaultInstance()
        }
    }
}