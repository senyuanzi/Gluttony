package sen.yuan.dao.gluttony_realm

import io.realm.RealmObject
import io.realm.RealmResults
import kotlin.reflect.declaredMemberProperties

/**
 * Created by Administrator on 2016/11/26.
 */

/**
 * @return 托管状态 的 model
 * */
inline fun <reified T : RealmObject> T.update(crossinline updateFunctor: (T) -> Unit): T {


    val mClassAnnotations = this.javaClass.kotlin.annotations
    if (mClassAnnotations.firstOrNull { it is Singleton } != null) {
        var managedData: T? = null
        Gluttony.realm.executeTransaction {
            managedData = it.where(this.javaClass).findFirst()
            if (managedData == null) {
                managedData = it.copyToRealm(this)
                updateFunctor(managedData!!)
            } else updateFunctor(managedData!!)
        }
        return managedData!!
    }

    if (this.isManaged) {
        Gluttony.realm.executeTransaction {
            updateFunctor(this)
        }
        return this
    } else {
        updateFunctor(this)
        return this.updateOrSave()
    }
}

/**
 * @return 托管状态 的 model。如果为null，则表示查无此数据
 * */
inline fun <reified T : RealmObject> T.updateByKey(primaryKey: Any, crossinline updateFunctor: (T) -> Unit): T? {
    val mClass = this.javaClass.kotlin
    val name = "${mClass.simpleName}"
    var propertyName: String? = null
    mClass.declaredMemberProperties.forEach {
        if (it.annotations.map { it.annotationClass }.contains(GluttonyPrimaryKey::class)) {
            propertyName = it.name
        }
    }
    if (propertyName == null) {
        throw Exception("$name 类没有设置PrimaryKey")
    }

    var managedData: T? = null
    Gluttony.realm.executeTransaction {
        managedData = it.where(this.javaClass)
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

        if (managedData != null) updateFunctor(managedData!!)
    }
    return managedData
}

/**
 * 更新所有符合条件的数据
 *
 * @param findFunctor 查询函数，指定更新那些数据
 * @param updateFunctor 更新函数，对数据进行更新操作
 * */
inline fun <reified T : RealmObject> T.update(findFunctor: RealmFinder.() -> Unit, crossinline updateFunctor: (T) -> Unit): RealmResults<T> {
    return this.findAll { findFunctor() }.apply {
        this.forEach {
            it.update(updateFunctor)
        }
    }
}