package dao.yuan.sen.gluttonyexample

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserRealmData(
        @PrimaryKey
        open var id: Int = -1,
        open var name: String = "",
        open var age: Int = -1
) : RealmObject() {

}
