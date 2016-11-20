package dao.yuan.sen.gluttony.operator

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log
import com.google.gson.Gson
import dao.yuan.sen.gluttony.Gluttony
import dao.yuan.sen.gluttony.annotation.PrimaryKey
import dao.yuan.sen.gluttony.e
import org.jetbrains.anko.db.*
import kotlin.reflect.declaredMemberProperties
import kotlin.reflect.defaultType
import kotlin.reflect.primaryConstructor

/**
 * Created by Administrator on 2016/11/19.
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

inline fun SQLiteDatabase.tryDo(functor: SQLiteDatabase.() -> Any?): Any? {
    return try {
        functor()
    } catch (ex: SQLiteException) {
        if (ex.message?.contains("no such table") ?: false) "no such table"
        else throw ex
    }
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

/**
 * 根据 实例 更新数据 ， 依靠实例的主键定位，如果数据库中不存在的话，将保存数据
 * 返回值：更新的数量位1 或 保存数据的id*/
inline fun <reified T : Any> T.updateOrSave(): Long {
    return if (update() == 0) save()
    else 1
}


/**
 * 根据 实例 更新数据 ， 依靠实例的主键定位
 * 返回值：更新的数量*/
inline fun <reified T : Any> T.update(): Int {
    val mClass = this.javaClass.kotlin
    val properties = mClass.declaredMemberProperties
    var propertyValue: Any? = null
    properties.forEach {
        if (it.annotations.map { it.annotationClass }.contains(PrimaryKey::class)) propertyValue = it.get(this)
    }
    if (propertyValue == null) throw Exception("${mClass.simpleName} 类型没有设置PrimaryKey， 或是  实例的PrimaryKey属性不能为null")
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
        this@update.updateByKey(propertyValue!!, *valuePairs)
    }
}


/**
 * 根据 实例 删除数据 ， 依靠实例的主键定位
 * 返回值：删除的数量*/
inline fun <reified T : Any> T.delete(): Int {
    val mClass = this.javaClass.kotlin
    val properties = mClass.declaredMemberProperties
    var propertyValue: Any? = null
    properties.forEach {
        if (it.annotations.map { it.annotationClass }.contains(PrimaryKey::class)) propertyValue = it.get(this)
    }
    if (propertyValue == null) throw Exception("${mClass.simpleName} 类型没有设置PrimaryKey， 或是  实例的PrimaryKey属性不能为null")
    return Gluttony.database.use {
        this@delete.deleteByKey(propertyValue!!)
    }
}


/**
 * 清空 该类数据
 */
inline fun <reified T : Any> T.clear() {
    val name = this.javaClass.kotlin.simpleName
    return Gluttony.database.use {
        dropTable(name!!, true)
    }
}
