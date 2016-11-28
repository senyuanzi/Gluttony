package dao.yuan.sen.gluttony.realm_module

import dao.yuan.sen.gluttony.Gluttony
import dao.yuan.sen.gluttony.sqlite_module.annotation.PrimaryKey
import io.realm.RealmObject
import io.realm.RealmQuery
import io.realm.RealmResults
import io.realm.Sort
import kotlin.reflect.declaredMemberProperties

/**
 * Created by Administrator on 2016/11/24.
 */


inline fun <reified T : RealmObject> T.realm_findOneByKey(propertyValue: Any): T? {
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


    return Gluttony.realm.where(this.javaClass)
            .apply {
                when (propertyValue) {
                    is String -> equalTo(propertyName, propertyValue)
                    is Int -> equalTo(propertyName, propertyValue)
                    is Byte -> equalTo(propertyName, propertyValue)
                    is Short -> equalTo(propertyName, propertyValue)
                    is Long -> equalTo(propertyName, propertyValue)
                    else -> throw Exception("$name 类设置了错误的PrimaryKey的类型")
                }
            }
            .findFirst()


}


inline fun <reified T : RealmObject> T.realm_findOne(optionFunctor: RealmFinder.() -> RealmFinder): T? {


    val realmFinder = optionFunctor(RealmFinder())


    return Gluttony.realm.where(this@realm_findOne.javaClass)
            .apply {
                realmFinder.conditionFunctor?.invoke(this)
            }.findFirst()
}


inline fun <reified T : RealmObject> T.realm_findAll(optionFunctor: RealmFinder.() -> Unit): RealmResults<T>? {


    val realmFinder = RealmFinder().apply { optionFunctor() }


    return Gluttony.realm.where(this@realm_findAll.javaClass)
            .apply {
                realmFinder.conditionFunctor?.invoke(this)
            }.findAll()
            .let {
                it.apply {
                    realmFinder.orderFunctor?.invoke(this)
                }
            }
}


class RealmFinder() {

    var orderFunctor: (RealmResults<*>.() -> RealmResults<*>)? = null
    var conditionFunctor: (RealmQuery<*>.() -> Unit)? = null


    inline fun condition(crossinline functor: RealmCondition.() -> RealmCondition): RealmFinder {
        conditionFunctor = {
            RealmCondition().apply { functor().functorArray.forEach { it() } }

        }
        return this
    }

    inline fun <reified T : RealmObject> RealmResults<T>.orderBy(propertyName: String, orderMode: OrderMode): RealmResults<T> {
        orderFunctor = {
            sort(propertyName, when (orderMode) {
                OrderMode.ASC -> Sort.ASCENDING
                OrderMode.DESC -> Sort.DESCENDING
            })
        }
        return this
    }
}



