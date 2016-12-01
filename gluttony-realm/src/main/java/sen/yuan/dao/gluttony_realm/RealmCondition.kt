package sen.yuan.dao.gluttony_realm

import io.realm.RealmQuery
import java.util.*

/**
 * Created by Administrator on 2016/11/24.
 */


class RealmCondition() {

    var functorArray: MutableList<RealmQuery<*>.() -> Unit> = mutableListOf()

    infix fun String.between(range: IntRange): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.between(this@between, range.first, range.last)
        })
        return this@RealmCondition
    }

    infix fun String.between(range: LongRange): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.between(this@between, range.first, range.last)
        })
        return this@RealmCondition
    }

    infix fun String.greaterThan(value: Int): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.greaterThan(this@greaterThan, value)
        })
        return this@RealmCondition
    }

    infix fun String.greaterThan(value: Double): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.greaterThan(this@greaterThan, value)
        })
        return this@RealmCondition
    }

    infix fun String.greaterThan(value: Float): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.greaterThan(this@greaterThan, value)
        })
        return this@RealmCondition
    }

    infix fun String.greaterThan(value: Long): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.greaterThan(this@greaterThan, value)
        })
        return this@RealmCondition
    }

    infix fun String.greaterThan(value: Date): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.greaterThan(this@greaterThan, value)
        })
        return this@RealmCondition
    }


    infix fun String.greaterThanOrEqualTo(value: Int): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.greaterThanOrEqualTo(this@greaterThanOrEqualTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.greaterThanOrEqualTo(value: Double): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.greaterThanOrEqualTo(this@greaterThanOrEqualTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.greaterThanOrEqualTo(value: Float): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.greaterThanOrEqualTo(this@greaterThanOrEqualTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.greaterThanOrEqualTo(value: Long): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.greaterThanOrEqualTo(this@greaterThanOrEqualTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.greaterThanOrEqualTo(value: Date): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.greaterThanOrEqualTo(this@greaterThanOrEqualTo, value)
        })
        return this@RealmCondition
    }


    infix fun String.lessThan(value: Int): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.lessThan(this@lessThan, value)
        })
        return this@RealmCondition
    }

    infix fun String.lessThan(value: Double): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.lessThan(this@lessThan, value)
        })
        return this@RealmCondition
    }

    infix fun String.lessThan(value: Float): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.lessThan(this@lessThan, value)
        })
        return this@RealmCondition
    }

    infix fun String.lessThan(value: Long): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.lessThan(this@lessThan, value)
        })
        return this@RealmCondition
    }

    infix fun String.lessThan(value: Date): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.lessThan(this@lessThan, value)
        })
        return this@RealmCondition
    }


    infix fun String.lessThanOrEqualTo(value: Int): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.lessThanOrEqualTo(this@lessThanOrEqualTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.lessThanOrEqualTo(value: Double): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.lessThanOrEqualTo(this@lessThanOrEqualTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.lessThanOrEqualTo(value: Float): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.lessThanOrEqualTo(this@lessThanOrEqualTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.lessThanOrEqualTo(value: Long): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.lessThanOrEqualTo(this@lessThanOrEqualTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.lessThanOrEqualTo(value: Date): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.lessThanOrEqualTo(this@lessThanOrEqualTo, value)
        })
        return this@RealmCondition
    }


    infix fun String.equalTo(value: Date): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.equalTo(value: Int): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.equalTo(value: Long): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.equalTo(value: Short): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        })
        return this@RealmCondition
    }


    infix fun String.equalTo(value: Float): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.equalTo(value: Double): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        })
        return this@RealmCondition
    }


    infix fun String.equalTo(value: String): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.equalTo(value: Byte): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        })
        return this@RealmCondition
    }


    infix fun String.equalTo(value: ByteArray): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.equalTo(value: Boolean): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        })
        return this@RealmCondition
    }


    infix fun String.notEqualTo(value: Date): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.notEqualTo(value: Int): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.notEqualTo(value: Long): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.notEqualTo(value: Short): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        })
        return this@RealmCondition
    }


    infix fun String.notEqualTo(value: Float): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.notEqualTo(value: Double): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        })
        return this@RealmCondition
    }


    infix fun String.notEqualTo(value: String): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.notEqualTo(value: Byte): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        })
        return this@RealmCondition
    }


    infix fun String.notEqualTo(value: ByteArray): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        })
        return this@RealmCondition
    }

    infix fun String.notEqualTo(value: Boolean): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        })
        return this@RealmCondition
    }


    infix fun String.contains(value: String): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.contains(this@contains, value)
        })
        return this@RealmCondition
    }

    infix fun String.beginsWith(value: String): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.beginsWith(this@beginsWith, value)
        })
        return this@RealmCondition
    }

    infix fun String.endsWith(value: String): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.endsWith(this@endsWith, value)
        })
        return this@RealmCondition
    }

    fun String.isNull(): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.isNull(this@isNull)
        })
        return this@RealmCondition

    }

    fun String.isNotNull(): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.isNotNull(this@isNotNull)
        })
        return this@RealmCondition

    }

    fun String.isEmpty(): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.isEmpty(this@isEmpty)
        })
        return this@RealmCondition

    }


    fun String.isNotEmpty(): RealmCondition {
        functorArray.add(fun RealmQuery<*>.() {
            this.isNotEmpty(this@isNotEmpty)
        })
        return this@RealmCondition

    }
}