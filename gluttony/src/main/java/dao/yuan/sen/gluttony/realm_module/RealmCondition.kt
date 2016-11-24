package dao.yuan.sen.gluttony.realm_module

import io.realm.RealmObject
import io.realm.RealmQuery
import java.util.*

/**
 * Created by Administrator on 2016/11/24.
 */


class RealmCondition() {


    infix fun String.between(range: IntRange): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.between(this@between, range.first, range.last)
        }
    }

    infix fun String.between(range: LongRange): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.between(this@between, range.first, range.last)
        }
    }

    infix fun String.greaterThan(value: Int): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.greaterThan(this@greaterThan, value)
        }
    }

    infix fun String.greaterThan(value: Double): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.greaterThan(this@greaterThan, value)
        }
    }

    infix fun String.greaterThan(value: Float): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.greaterThan(this@greaterThan, value)
        }
    }

    infix fun String.greaterThan(value: Long): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.greaterThan(this@greaterThan, value)
        }
    }

    infix fun String.greaterThan(value: Date): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.greaterThan(this@greaterThan, value)
        }
    }


    infix fun String.greaterThanOrEqualTo(value: Int): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.greaterThanOrEqualTo(this@greaterThanOrEqualTo, value)
        }
    }

    infix fun String.greaterThanOrEqualTo(value: Double): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.greaterThanOrEqualTo(this@greaterThanOrEqualTo, value)
        }
    }

    infix fun String.greaterThanOrEqualTo(value: Float): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.greaterThanOrEqualTo(this@greaterThanOrEqualTo, value)
        }
    }

    infix fun String.greaterThanOrEqualTo(value: Long): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.greaterThanOrEqualTo(this@greaterThanOrEqualTo, value)
        }
    }

    infix fun String.greaterThanOrEqualTo(value: Date): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.greaterThanOrEqualTo(this@greaterThanOrEqualTo, value)
        }
    }


    infix fun String.lessThan(value: Int): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.lessThan(this@lessThan, value)
        }
    }

    infix fun String.lessThan(value: Double): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.lessThan(this@lessThan, value)
        }
    }

    infix fun String.lessThan(value: Float): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.lessThan(this@lessThan, value)
        }
    }

    infix fun String.lessThan(value: Long): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.lessThan(this@lessThan, value)
        }
    }

    infix fun String.lessThan(value: Date): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.lessThan(this@lessThan, value)
        }
    }


    infix fun String.lessThanOrEqualTo(value: Int): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.lessThanOrEqualTo(this@lessThanOrEqualTo, value)
        }
    }

    infix fun String.lessThanOrEqualTo(value: Double): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.lessThanOrEqualTo(this@lessThanOrEqualTo, value)
        }
    }

    infix fun String.lessThanOrEqualTo(value: Float): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.lessThanOrEqualTo(this@lessThanOrEqualTo, value)
        }
    }

    infix fun String.lessThanOrEqualTo(value: Long): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.lessThanOrEqualTo(this@lessThanOrEqualTo, value)
        }
    }

    infix fun String.lessThanOrEqualTo(value: Date): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.lessThanOrEqualTo(this@lessThanOrEqualTo, value)
        }
    }


    infix fun String.equalTo(value: Date): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        }
    }

    infix fun String.equalTo(value: Int): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        }
    }

    infix fun String.equalTo(value: Long): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        }
    }

    infix fun String.equalTo(value: Short): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        }
    }


    infix fun String.equalTo(value: Float): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        }
    }

    infix fun String.equalTo(value: Double): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        }
    }


    infix fun String.equalTo(value: String): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        }
    }

    infix fun String.equalTo(value: Byte): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        }
    }


    infix fun String.equalTo(value: ByteArray): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        }
    }

    infix fun String.equalTo(value: Boolean): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.equalTo(this@equalTo, value)
        }
    }


    infix fun String.notEqualTo(value: Date): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        }
    }

    infix fun String.notEqualTo(value: Int): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        }
    }

    infix fun String.notEqualTo(value: Long): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        }
    }

    infix fun String.notEqualTo(value: Short): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        }
    }


    infix fun String.notEqualTo(value: Float): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        }
    }

    infix fun String.notEqualTo(value: Double): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        }
    }


    infix fun String.notEqualTo(value: String): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        }
    }

    infix fun String.notEqualTo(value: Byte): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        }
    }


    infix fun String.notEqualTo(value: ByteArray): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        }
    }

    infix fun String.notEqualTo(value: Boolean): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.notEqualTo(this@notEqualTo, value)
        }
    }


    infix fun String.contains(value: String): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.contains(this@contains, value)
        }
    }

    infix fun String.beginsWith(value: String): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.beginsWith(this@beginsWith, value)
        }
    }

    infix fun String.endsWith(value: String): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.endsWith(this@endsWith, value)
        }
    }

    fun String.isNull(): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.isNull(this@isNull)
        }
    }

    fun String.isNotNull(): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.isNotNull(this@isNotNull)
        }
    }

    fun String.isEmpty(): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.isEmpty(this@isEmpty)
        }
    }


    fun String.isNotEmpty(): RealmQuery<*>.() -> Unit {
        return fun RealmQuery<*>.() {
            this.isNotEmpty(this@isNotEmpty)
        }
    }
}