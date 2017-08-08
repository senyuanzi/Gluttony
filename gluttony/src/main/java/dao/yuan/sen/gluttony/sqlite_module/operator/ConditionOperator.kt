package dao.yuan.sen.gluttony.sqlite_module.operator

import dao.yuan.sen.gluttony.Gluttony
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.select
import java.io.Serializable

/**
 * Created by Administrator on 2016/11/19.
 */
inline fun <reified T : Serializable> T.columns(vararg fields: String, crossinline functor: SelectQueryBuilder.() -> Unit): List<Map<String, Any?>> {

    val name = "${this.javaClass.kotlin.simpleName}"
    return Gluttony.database.use {
        tryDo {
            select(name).columns(*fields).apply {
                functor()
            }.parseList(object : MapRowParser<Map<String, Any?>> {
                override fun parseRow(columns: Map<String, Any?>): Map<String, Any?> = columns
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


