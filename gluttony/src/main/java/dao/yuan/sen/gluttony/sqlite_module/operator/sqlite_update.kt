package dao.yuan.sen.gluttony.sqlite_module.operator

import com.google.gson.Gson
import dao.yuan.sen.gluttony.Gluttony
import dao.yuan.sen.gluttony.sqlite_module.annotation.PrimaryKey
import dao.yuan.sen.gluttony.sqlite_module.condition
import dao.yuan.sen.gluttony.sqlite_module.e
import org.jetbrains.anko.db.UpdateQueryBuilder
import org.jetbrains.anko.db.update
import kotlin.reflect.declaredMemberProperties

/**
 * Created by Administrator on 2016/11/28.
 */

/**
 * 根据 实例 更新数据 ， 依靠实例的主键定位，如果数据库中不存在的话，将保存数据
 * 返回值：更新的数量位1 或 保存数据的id*/
inline fun <reified T : Any> T.updateOrSave(): Long {
    return if (update() == 0) save()
    else 1
}

inline fun <reified T : Any> T.updateByKey(primaryKey: Any, updateFunctor: (T) -> Unit): Int {
    val data: T? = this.findOneByKey(primaryKey) ?: return 0
    updateFunctor(data!!)
    return data.update()
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