package dao.yuan.sen.gluttony.parser

import dao.yuan.sen.gluttony.e
import org.jetbrains.anko.AnkoException
import org.jetbrains.anko.db.RowParser
import java.lang.reflect.Modifier
import kotlin.reflect.primaryConstructor

/**
 * Created by Administrator on 2016/11/19.
 */


inline fun <reified T : Any> classParser(): RowParser<T> {
    val clazz = T::class.java
    val constructors = clazz.declaredConstructors.filter {
        val types = it.parameterTypes
        !it.isVarArgs && Modifier.isPublic(it.modifiers) &&
                types != null && types.size > 0
    }
    if (constructors.isEmpty())
        throw AnkoException(
                "Can't initialize object parser for ${clazz.canonicalName}, no acceptable constructors found")

    val c = constructors[0]
//    val c = clazz.kotlin.primaryConstructor

    "".e("constructor", clazz.kotlin.primaryConstructor.toString())


    for (type in c.parameterTypes) {
        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
        val valid = when (type) {
            Int::class.java, Long::class.java, java.lang.Long::class.java -> true
            Double::class.java, java.lang.Double::class.java -> true
            String::class.java, ByteArray::class.java -> true
            Boolean::class.java -> true
            else -> false
        }
        if (!valid)
            throw AnkoException(
                    "Invalid argument type ${type.canonicalName} in ${clazz.canonicalName} constructor." +
                            "Supported types are: Long, Double, String, Array<Byte>.")
    }

    return object : RowParser<T> {
        override fun parseRow(columns: Array<Any?>): T {



            val intList: MutableList<Int> = mutableListOf()

            c.parameterTypes.forEachIndexed { i, clazz ->
                e("parameterType", clazz.toString())
                if (clazz == Int::class.java) intList.add(i)
            }

            val newColumns = columns.mapIndexed { i, any ->
                e("originColumns", any.toString())
                if (i in intList) any.toString().toInt()
                else when (any) {
                    "true" -> true
                    "false" -> false
                    else -> any
                }
            }.toTypedArray()

            newColumns.forEach {
                e("newColumns", it.toString())
            }


//            val constructor = clazz.kotlin.primaryConstructor!!
//            val maps = constructor.parameters.mapIndexed { i, kParameter -> kParameter to newColumns[i] }.toMap()
//            return constructor.callBy(maps) as T
            return c.newInstance(*newColumns) as T
        }
    }
}