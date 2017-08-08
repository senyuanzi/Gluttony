package dao.yuan.sen.gluttony.sqlite_module.operator

import android.database.sqlite.SQLiteDatabase
import dao.yuan.sen.gluttony.Gluttony
import dao.yuan.sen.gluttony.sqlite_module.annotation.Ignore
import dao.yuan.sen.gluttony.sqlite_module.annotation.PrimaryKey
import dao.yuan.sen.gluttony.sqlite_module.e
import org.jetbrains.anko.db.*
import java.io.Serializable
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.starProjectedType

/**
 * Created by Administrator on 2016/11/28.
 */

fun <T : Any> SQLiteDatabase.tryCreateTable(data: T) {
    val mClass = data.javaClass.kotlin
    val name = "${mClass.simpleName}"
    val properties = mClass.declaredMemberProperties

    val tablePairs = properties
            .filter { !it.annotations.map { it.annotationClass }.contains(Ignore::class) }
            .associate {
                val pair = it.name to it.returnType.classifier.let {
                    when (it) {
                        Boolean::class.starProjectedType.classifier, String::class.starProjectedType.classifier -> TEXT
                        Long::class.starProjectedType.classifier, Int::class.starProjectedType.classifier -> INTEGER
                        Float::class.starProjectedType.classifier, Double::class.starProjectedType.classifier -> REAL
                        else -> TEXT
                    }
                }

                if (it.annotations.map { it.annotationClass }.contains(PrimaryKey::class)) {
                    pair.copy(second = pair.second + PRIMARY_KEY)
                            .apply { "".e("PrimaryKey", "$first   :${second.name}") }
                } else {
                    pair.apply { "".e("NOT PrimaryKey", "$first   :${second.name}") }
                }

            }.toList().toTypedArray()

    this.createTable(name, true, *tablePairs)
}

/**
 * 保存列表
 */
fun <T : List<Serializable>> T.saveAll(replace: Boolean = false) {

    return Gluttony.database.use {

        transaction {

            val valuePairs = this@saveAll.map { getValuePairs(it).toList().toTypedArray() }

            val mClass = this@saveAll[0].javaClass.kotlin
            val name = "${mClass.simpleName}"

            tryCreateTable(this@saveAll[0])

            valuePairs.forEach {
                if (replace) replace(name, *it) else insert(name, *it)
            }
        }
    }
}


/**
 *保存
 */
fun <T : Serializable> T.save(replace: Boolean = false): Long {
    val mClass = this.javaClass.kotlin
    val name = "${mClass.simpleName}"

    val valuePairs = getValuePairs(this).toList().toTypedArray()

    return Gluttony.database.use {
        tryCreateTable(this@save)

        if (replace) replace(name, *valuePairs) else insert(name, *valuePairs)

    }
}