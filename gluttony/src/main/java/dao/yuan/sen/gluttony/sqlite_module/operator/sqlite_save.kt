package dao.yuan.sen.gluttony.sqlite_module.operator

import android.database.sqlite.SQLiteDatabase
import com.google.gson.Gson
import dao.yuan.sen.gluttony.Gluttony
import dao.yuan.sen.gluttony.sqlite_module.annotation.PrimaryKey
import dao.yuan.sen.gluttony.sqlite_module.e
import org.jetbrains.anko.db.*
import kotlin.reflect.declaredMemberProperties
import kotlin.reflect.defaultType
import kotlin.reflect.primaryConstructor

/**
 * Created by Administrator on 2016/11/28.
 */

inline fun <T : Any> SQLiteDatabase.testCreateTable(data: T) {
    val mClass = data.javaClass.kotlin
    val name = "${mClass.simpleName}"
    val parameters = mClass.primaryConstructor!!.parameters


    val tablePairs = parameters.associate {
        val pair = it.name!! to it.type.let {
            when (it) {
                Boolean::class.defaultType, String::class.defaultType -> TEXT
                Int::class.defaultType -> INTEGER
                Float::class.defaultType, Double::class.defaultType -> REAL
                else -> TEXT
            }
        }
        if (it.annotations.map { it.annotationClass }.contains(PrimaryKey::class)) pair.copy(second = pair.second + PRIMARY_KEY + UNIQUE).apply { e("reflect_columns", "$first:$second") }
        else pair.apply { e("reflect_columns", "$first:$second") }
    }.toList().toTypedArray()

    this.createTable(name, true, *tablePairs)
}




fun <T : Any> T.save(): Long {
    val mClass = this.javaClass.kotlin
    val name = "${mClass.simpleName}"
    val properties = mClass.declaredMemberProperties

    val valuePairs = properties.associate {
        e("reflect_values", "${it.name}:${it.get(this).toString()}")
        it.name to it.get(this).let {
            when (it) {
                true -> "true"
                false -> "false"
                is String, is Int, is Float, is Double -> it
                else -> Gson().toJson(it)
            }
        }
    }.toList().toTypedArray()


    return Gluttony.database.use {
        testCreateTable(this@save)
        insert(name, *valuePairs)
    }
}

