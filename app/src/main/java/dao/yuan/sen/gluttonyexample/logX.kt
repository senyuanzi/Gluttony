package dao.yuan.sen.gluttonyexample

import android.util.Log

/**
 * Created by SenYuYuan on 2016/11/17.
 */


fun Any.e(tag: String, message: String) {
//    if (Gluttony.isDebug.not()) return
    Log.e(tag, message)
}

fun Any.e(tag: String, functor: () -> String) = e(tag, functor())

fun Any.e(functor: () -> String) = e(this.javaClass.simpleName, functor)
