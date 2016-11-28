package dao.yuan.sen.gluttony.sqlite_module.operator

import dao.yuan.sen.gluttony.Gluttony
import dao.yuan.sen.gluttony.sqlite_module.annotation.PrimaryKey
import dao.yuan.sen.gluttony.sqlite_module.condition
import dao.yuan.sen.gluttony.sqlite_module.parser.classParser
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.select
import kotlin.reflect.declaredMemberProperties

/**
 * Created by Administrator on 2016/11/28.
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
            }.parseOpt(dao.yuan.sen.gluttony.sqlite_module.parser.classParser<T>())
        }.let {
            when (it) {
                null, "no such table" -> null
                is T -> it
                else -> null
            }
        }
    }
}


inline fun <reified T : Any> T.findOne(crossinline functor: SelectQueryBuilder.() -> Unit): T? {
    val name = "${this.javaClass.kotlin.simpleName}"

    return Gluttony.database.use {
        tryDo {
            select(name).apply {
                limit(1)
                functor()
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


inline fun <reified T : Any> T.findAll(crossinline functor: SelectQueryBuilder.() -> Unit): List<T> {
    val name = "${this.javaClass.kotlin.simpleName}"

    return Gluttony.database.use {
        tryDo {
            select(name).apply {
                functor()
            }.parseList(classParser<T>())
        }.let {
            when (it) {
                null, "no such table" -> emptyList()
                is List<*> -> it as List<T>
                else -> emptyList()
            }
        }
    }
}
