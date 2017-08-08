package dao.yuan.sen.gluttony.sqlite_module.parser

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dao.yuan.sen.gluttony.sqlite_module.annotation.Ignore

import org.jetbrains.anko.db.MapRowParser
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.jvm.javaType

/**数据反解析
 * Created by Administrator on 2016/11/19.
 */


inline fun <reified T : Any> classParser(): MapRowParser<T> {

    val clazz = T::class.java.kotlin
    val properties = clazz.declaredMemberProperties

    val tablePairs = properties
            .filter { !it.annotations.map { it.annotationClass }.contains(Ignore::class) }
            .associate {
                it.name to it.returnType
            }.toList().toTypedArray()

    return object : MapRowParser<T> {
        override fun parseRow(columns: Map<String, Any?>): T {

            val newColumns = tablePairs
                    .associate { (key, value) ->
                        key to when (value.classifier) {
                            Boolean::class.starProjectedType.classifier -> when (columns[key]) {
                                "true" -> true
                                "false" -> false
                                else -> false
                            }

                            Long::class.starProjectedType.classifier,
                            Int::class.starProjectedType.classifier,
                            String::class.starProjectedType.classifier,
                            Float::class.starProjectedType.classifier,
                            Double::class.starProjectedType.classifier -> columns[key]

                            else -> Gson().fromJson(columns[key].toString(), value.javaType)
                        }
                    }
            val json = Gson().toJson(newColumns)
            return Gson().fromJson(json, object : TypeToken<T>() {}.type)
        }

    }
}