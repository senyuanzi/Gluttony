package sen.yuan.dao.gluttony_realm

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.log.LogLevel
import io.realm.log.RealmLog

/**
 * Created by SenYuYuan on 2016/3/5.
 */


object Gluttony {
    var isDebug = false


    val realm: Realm by lazy { Realm.getDefaultInstance() }

    fun init(baseApp: Application) {
        Realm.init(baseApp)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
                .name("gluttony")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build())
        RealmLog.setLevel(LogLevel.ALL)
    }
}