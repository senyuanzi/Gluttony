package dao.yuan.sen.gluttony.realm_module

import dao.yuan.sen.gluttony.Gluttony
import io.realm.RealmObject

/**
 * Created by Administrator on 2016/11/23.
 */


inline fun RealmObject.save() {
    Gluttony.realm.executeTransaction {
        it.copyToRealm(this@save)
    }
}