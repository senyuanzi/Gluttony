package dao.yuan.sen.gluttony.realm_module

import dao.yuan.sen.gluttony.Gluttony
import io.realm.RealmObject

/**
 * Created by Administrator on 2016/11/23.
 */


inline fun <T : RealmObject> T.realm_save() {
    Gluttony.realm.executeTransaction {
        it.copyToRealm(this@realm_save)
    }
}