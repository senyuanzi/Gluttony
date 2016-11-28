package dao.yuan.sen.gluttony.realm_module

import dao.yuan.sen.gluttony.Gluttony
import dao.yuan.sen.gluttony.sqlite_module.annotation.PrimaryKey
import io.realm.RealmObject
import kotlin.reflect.declaredMemberProperties

/**
 * Created by Administrator on 2016/11/26.
 */

inline fun <reified T : RealmObject> T.realm_update() {
    this.realm_save()
}

inline fun <reified T : RealmObject> T.realm_updateByKey(primaryKey: Any, crossinline updateFunctor: (T) -> Unit) {
    val mClass = this.javaClass.kotlin
    val name = "${mClass.simpleName}"
    var propertyName: String? = null
    mClass.declaredMemberProperties.forEach {
        if (it.annotations.map { it.annotationClass }.contains(PrimaryKey::class)) {
            propertyName = it.name
        }
    }
    if (propertyName == null) {
        throw Exception("$name 类没有设置PrimaryKey")
    }

    Gluttony.realm.executeTransaction {
        updateFunctor(
                it.where(this.javaClass)
                        .apply {
                            when (primaryKey) {
                                is String -> equalTo(propertyName, primaryKey)
                                is Int -> equalTo(propertyName, primaryKey)
                                is Byte -> equalTo(propertyName, primaryKey)
                                is Short -> equalTo(propertyName, primaryKey)
                                is Long -> equalTo(propertyName, primaryKey)
                                else -> throw Exception("$name 类设置了错误的PrimaryKey的类型")
                            }
                        }
                        .findFirst()
        )
    }
}

/**
 * 更新所有符合条件的数据
 *
 * @param findFunctor 查询函数，指定更新那些数据
 * @param updateFunctor 更新函数，对数据进行更新操作
 * */
inline fun <reified T : RealmObject> T.realm_update(findFunctor: RealmFinder.() -> Unit, updateFunctor: (T) -> Unit) {
    this.realm_findAll { findFunctor() }?.forEach {
        updateFunctor(it)
    }
}