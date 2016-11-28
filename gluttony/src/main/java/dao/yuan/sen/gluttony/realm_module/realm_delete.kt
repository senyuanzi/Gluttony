package dao.yuan.sen.gluttony.realm_module

import dao.yuan.sen.gluttony.Gluttony
import dao.yuan.sen.gluttony.sqlite_module.annotation.PrimaryKey
import io.realm.RealmObject
import kotlin.reflect.declaredMemberProperties

/**
 * Created by Administrator on 2016/11/26.
 */


/**
 * 根据 实例 删除数据 ， 依靠实例的主键定位
 * 返回值：删除的数量*/
inline fun <reified T : RealmObject> T.realm_delete() {
    Gluttony.realm.executeTransaction {

        val mClass = this.javaClass.kotlin
        val properties = mClass.declaredMemberProperties
        var propertyValue: Any? = null
        properties.forEach {
            if (it.annotations.map { it.annotationClass }.contains(PrimaryKey::class)) propertyValue = it.get(this)
        }
        if (propertyValue == null) throw Exception("${mClass.simpleName} 类型没有设置PrimaryKey， 或是  实例的PrimaryKey属性不能为null")

        this.realm_deleteByKey(propertyValue!!)
    }
}


inline fun <reified T : RealmObject> T.realm_deleteByKey(propertyValue: Any) {
    Gluttony.realm.executeTransaction {
        this.realm_findOneByKey(propertyValue)?.deleteFromRealm()
    }
}


inline fun <reified T : RealmObject> T.realm_deleteAll(crossinline optionFunctor: RealmFinder.() -> RealmFinder): Boolean {
    var boolean = false
    Gluttony.realm.executeTransaction {
        boolean = this.realm_findAll { optionFunctor() }?.deleteAllFromRealm() ?: false
    }
    return boolean
}


inline fun <reified T : RealmObject> T.realm_clear(): Boolean {
    var boolean = false
    Gluttony.realm.executeTransaction {
        boolean = this.realm_findAll { }?.deleteAllFromRealm() ?: false

    }
    return boolean
}

