package dao.yuan.sen.gluttony.sqlite_module.operator

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import com.google.gson.Gson
import dao.yuan.sen.gluttony.sqlite_module.annotation.Ignore
import dao.yuan.sen.gluttony.sqlite_module.annotation.PrimaryKey
import dao.yuan.sen.gluttony.sqlite_module.e
import java.io.Serializable
import kotlin.reflect.declaredMemberProperties

/**
 * Created by Administrator on 2016/11/26.
 */
inline fun SQLiteDatabase.tryDo(functor: SQLiteDatabase.() -> Any?): Any? {
    return try {
        functor()
    } catch (ex: SQLiteException) {
        if (ex.message?.contains("no such table") == true) "no such table"
        else throw ex
    }
}

/**
 * 反射数据解析
 * Created by LOVE on 2017/8/4 004.
 */
fun <T : Serializable> getValuePairs(data: T): Map<String, Any> {
    val mClass = data.javaClass.kotlin
    val properties = mClass.declaredMemberProperties
    var havePrimaryKey: Boolean = false

    properties.forEach {
        if (it.annotations.map { it.annotationClass }.contains(PrimaryKey::class)) havePrimaryKey = true
    }
    if (!havePrimaryKey) throw Exception("${mClass.simpleName} 类型没有设置PrimaryKey")

    return properties
            .filter { it.get(data) != null }
            .filter { !it.annotations.map { it.annotationClass }.contains(Ignore::class) }
            .associate {
                "".e("save", "${it.name}  ->  ${it.get(data)}")
                it.name to it.get(data).let {
                    when (it) {
                        true -> "true"
                        false -> "false"
                        is String, is Long, is Int, is Float, is Double -> it
                        else -> Gson().toJson(it)
                    }
                }
            }
}


