package dao.yuan.sen.gluttony.realm_module

import dao.yuan.sen.gluttony.Gluttony
import dao.yuan.sen.gluttony.annotation.PrimaryKey
import dao.yuan.sen.gluttony.condition
import io.realm.*
import kotlin.reflect.declaredMemberProperties

/**
 * Created by Administrator on 2016/11/24.
 */


inline fun <reified T : RealmObject> T.findOneByKey(): T? {
    val mClass = this.javaClass.kotlin
    val name = "${mClass.simpleName}"
    var propertyName: String? = null
    var propertyValue: Any? = null
    mClass.declaredMemberProperties.forEach {
        if (it.annotations.map {
            it.annotationClass
        }.contains(PrimaryKey::class)) {
            propertyName = it.name
            propertyValue = it.get(this)
        }
    }
    if (propertyName == null) {
        throw Exception("$name 类没有设置PrimaryKey")
    }
    if (propertyValue == null) {
        throw Exception("$name 类没有设置PrimaryKey的值")
    }

    RealmData().findAll {
        dao.yuan.sen.gluttony.condition {
            "age" equalTo 8
            "time" lessThan 98
        }

    }
    return Gluttony.realm.where(this.javaClass)
            .apply {
                when (propertyValue) {
                    is String -> equalTo(propertyName, propertyValue as String)
                    is Int -> equalTo(propertyName, propertyValue as Int)
                    is Byte -> equalTo(propertyName, propertyValue as Byte)
                    is Short -> equalTo(propertyName, propertyValue as Short)
                    is Long -> equalTo(propertyName, propertyValue as Long)
                    else -> throw Exception("$name 类设置了错误的PrimaryKey的类型")
                }
            }
            .findFirst()


}


inline fun <reified T : RealmObject> T.findAll(optionFunctor: RealmCondition.() -> Unit): RealmResults<T>? {

    return Gluttony.realm.where(this@findAll.javaClass)
            .apply {
                RealmCondition().apply {
                    optionFunctor()

                }
            }.findAll()
            .let {
                it.apply {
                    orderBy()
                }
            }
}


inline fun RealmQuery<*>.condition(functor: () -> Array<RealmQuery<*>.() -> Unit>) {
    functor().forEach { it() }
}

inline fun <reified T : RealmObject> RealmResults<T>.orderBy(propertyName: String, orderMode: OrderMode): RealmResults<T>? {
    return sort(propertyName, when (orderMode) {
        OrderMode.ASC -> Sort.ASCENDING
        OrderMode.DESC -> Sort.DESCENDING
    })
}

open class RealmData : RealmObject()


class RealmFinder(){



}

enum class OrderMode {
    ASC, DESC
}