package dao.yuan.sen.gluttony.operator

import dao.yuan.sen.gluttony.Gluttony
import dao.yuan.sen.gluttony.annotation.PrimaryKey
import dao.yuan.sen.gluttony.condition
import dao.yuan.sen.gluttony.parser.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import kotlin.reflect.declaredMemberProperties

/**
 * Created by Administrator on 2016/11/19.
 */


/**
 * 根据 主键 查询数据
 * */
inline fun <reified T : Any> T.findOneByKey(primaryKey: Any): T? {
    val mClass = this.javaClass.kotlin
    val name = "${mClass.simpleName}"
    var propertyName: String? = null
    mClass.declaredMemberProperties.forEach {
        if (it.annotations.map {
            it.annotationClass
        }.contains(PrimaryKey::class)) propertyName = it.name
    }
    if (propertyName == null) throw Exception("$name 类型没有设置PrimaryKey")
    return Gluttony.database.use {
        tryDo {
            select(name).apply {
                limit(1)
                condition { propertyName!! equalsData primaryKey }
            }.parseOpt(classParser<T>())
        }.let {
            when (it) {
                null, "no such table" -> null
                is T -> it
                else -> null
            }
        }
    }
}


/**
 * 根据 主键 更新数据
 * 返回值：更新的数量*/
inline fun <reified T : Any> T.updateByKey(primaryKey: Any, crossinline pairs: () -> Array<out Pair<String, Any>>): Int = updateByKey(primaryKey, *pairs())


/**
 * 根据 主键 更新数据
 * 返回值：更新的数量*/
inline fun <reified T : Any> T.updateByKey(primaryKey: Any, vararg pairs: Pair<String, Any>): Int {
    val mClass = this.javaClass.kotlin
    val name = "${mClass.simpleName}"
    var propertyName: String? = null
    mClass.declaredMemberProperties.forEach {
        if (it.annotations.map { it.annotationClass }.contains(PrimaryKey::class)) propertyName = it.name
    }
    if (propertyName == null) throw Exception("$name 类型没有设置PrimaryKey")
    return Gluttony.database.use {
        tryDo {
            update(name, *pairs).apply {
                condition { propertyName!! equalsData primaryKey }
            }.exec()
        }.let {
            when (it) {
                null, "no such table" -> 0
                is Int -> it
                else -> 0
            }
        } as Int
    }
}


/**
 * 根据 主键 删除数据
 * 返回值：删除的数量*/
inline fun <reified T : Any> T.deleteByKey(primaryKey: Any): Int {
    val mClass = this.javaClass.kotlin
    val name = "${mClass.simpleName}"
    var propertyName: String? = null
    mClass.declaredMemberProperties.forEach {
        if (it.annotations.map { it.annotationClass }.contains(PrimaryKey::class)) propertyName = it.name
    }
    if (propertyName == null) throw Exception("$name 类型没有设置PrimaryKey")
    val condition = condition { propertyName!! equalsData primaryKey }
    return Gluttony.database.use {
        tryDo {
            delete(name, condition.selects.joinToString(" and "), *condition.pairs.toTypedArray())
        }.let {
            when (it) {
                null, "no such table" -> 0
                is Int -> it
                else -> 0
            }
        } as Int
    }
}
