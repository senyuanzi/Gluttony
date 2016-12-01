package sen.yuan.dao.gluttony_realm

import io.realm.RealmObject

/**
 * Created by Administrator on 2016/11/23.
 */


inline fun <T : RealmObject> T.save() {
    Gluttony.realm.executeTransaction {
        it.copyToRealm(this@save)
    }
}