package dao.yuan.sen.gluttony.operator

import android.util.Log
import dao.yuan.sen.gluttony.Condition
import dao.yuan.sen.gluttony.Gluttony
import dao.yuan.sen.gluttony.e
import dao.yuan.sen.gluttony.parser.classParser
import org.jetbrains.anko.db.*

/**
 * Created by Administrator on 2016/11/19.
 */


inline fun <reified T : Any> T.columns(vararg fields: String, crossinline functor: SelectQueryBuilder.() -> Unit): List<Map<String, Any?>> {
    val name = "${this.javaClass.kotlin.simpleName}"
    return Gluttony.database.use {
        tryDo {
            select(name).columns(*fields).apply {
                functor()
            }.parseList(object : MapRowParser<Map<String, Any?>> {
                override fun parseRow(columns: Map<String, Any?>): Map<String, Any?> {
                    return columns
                }
            })
        }.let {
            when (it) {
                null, "no such table" -> emptyList<Map<String, Any?>>()
                is List<*> -> it as List<Map<String, Any?>>
                else -> emptyList<Map<String, Any?>>()
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


/**返回值：更新的数量*/
inline fun <reified T : Any> T.update(vararg pairs: Pair<String, Any>, crossinline condition: UpdateQueryBuilder.() -> Unit): Int {
    val name = "${this.javaClass.kotlin.simpleName}"
    return Gluttony.database.use {
        tryDo {
            update(name, *pairs).apply {
                condition()
            }.exec()
        }.let {
            when (it) {
                null, "no such table" -> 0
                is Int -> it
                else -> 0
            }
        }
    }
}

/**返回值：删除的数量*/
inline fun <reified T : Any> T.delete(crossinline condition: () -> Condition): Int {
    val name = "${this.javaClass.kotlin.simpleName}"
    return Gluttony.database.use {
        tryDo {
            delete(name, condition().selects.joinToString(" and "), *condition().pairs.toTypedArray())
        }.let {
            when (it) {
                null, "no such table" -> 0
                is Int -> it
                else -> 0
            }
        }

    }
}

