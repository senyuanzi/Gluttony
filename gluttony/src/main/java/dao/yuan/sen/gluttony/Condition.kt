package dao.yuan.sen.gluttony

import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.UpdateQueryBuilder

/**
 * Created by Administrator on 2016/11/19.
 */


inline fun SelectQueryBuilder.condition(functor: Condition.() -> Unit) {
    val condition = Condition().apply { functor() }
    where(condition.selects.joinToString(separator = " and "),
            *condition.pairs.toTypedArray())
}

inline fun UpdateQueryBuilder.condition(functor: Condition.() -> Unit) {
    val condition = Condition().apply { functor() }
    where(condition.selects.joinToString(separator = " and "),
            *condition.pairs.toTypedArray())
}

inline fun condition(functor: Condition.() -> Unit): Condition {
    return Condition().apply { functor() }
}


class Condition {

    var selects: MutableList<String> = arrayListOf()
    var pairs: MutableList<Pair<String, Any>> = arrayListOf()

    infix fun String.equalsData(other: Any) {
        selects.add("(${this} = {${this}})")
        pairs.add(this to other.toString())
    }

    infix fun String.moreThan(other: Any) {
        selects.add("(${this} > {${this}})")
        pairs.add(this to other.toString())
    }

    infix fun String.moreThanOrEquals(other: Any) {
        selects.add("(${this} >= {${this}})")
        pairs.add(this to other.toString())
    }

    infix fun String.lessThan(other: Any) {
        selects.add("(${this} < {${this}})")
        pairs.add(this to other.toString())
    }

    infix fun String.lessThanOrEquals(other: Any) {
        selects.add("(${this} <= {${this}})")
        pairs.add(this to other.toString())
    }

    infix fun String.Not(other: Any) {
        selects.add("(${this} is NOT {Not${this}})")
        pairs.add("Not${this}" to other.toString())
    }

    infix fun String.In(array: Array<Any>) {
        var temp_string = ""
        array.forEachIndexed { i, any ->
            temp_string += " {In${this}$i}, "
            pairs.add("In${this}$i" to any.toString())
        }
        selects.add("(${this} IN ( $temp_string ))")
    }

    infix fun String.notIn(array: Array<Any>) {
        var temp_string = ""
        array.forEachIndexed { i, any ->
            temp_string += "notIn${this}$i}, "
            pairs.add("notIn${this}$i" to any.toString())
        }
        selects.add("(${this} NOT IN ( $temp_string ))")
    }


    infix fun String.between(range: IntRange) {
        selects.add("(${this} BETWEEN {between${this}1} AND {between${this}2})")
        pairs.add("between${this}1" to range.first)
        pairs.add("between${this}2" to range.last)
    }

    infix fun String.like(likeCondition: String) {
        selects.add("(${this} LIKE {like${this}})")
        pairs.add("like${this}" to likeCondition)
    }

}