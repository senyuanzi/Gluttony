package sen.yuan.dao.magicbinding

import android.content.Context

/**
 * Created by senyuyuan on 2016/12/4.
 */

/**
 * 容器
 * */
private val Any.reactListenerMap: MutableMap<String, MutableList<() -> Unit>> by  lazy {
    val mutableMap: MutableMap <String, MutableList<() -> Unit>> = mutableMapOf()
    mutableMap
}

/**
 *
 * 清除指定上下文的所有数据响应监听
 * */
fun Context.clearAllReactListeners() {
    this.reactListenerMap[this.javaClass.name]?.forEach {
        it.invoke()
    }
}


/**
 *记录数据响应的监听
 * */
fun Context.saveReactListener(removeListener: () -> Unit) {
    this.reactListenerMap.getOrPut(this.javaClass.name, { mutableListOf() }).add(removeListener)
}
