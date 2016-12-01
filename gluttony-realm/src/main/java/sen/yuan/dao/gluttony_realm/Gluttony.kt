package sen.yuan.dao.gluttony_realm

import android.app.Application
import io.realm.Realm

/**
 * Created by SenYuYuan on 2016/3/5.
 */


object Gluttony {
    var isDebug = false


    lateinit var realm: Realm

    fun init(baseApp: Application) {
        Realm.init(baseApp)
        realm = Realm.getDefaultInstance()
    }
}