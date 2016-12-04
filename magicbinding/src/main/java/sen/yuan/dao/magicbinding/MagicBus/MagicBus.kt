package sen.yuan.dao.magicbinding.MagicBus

/**
 * Created by senyuyuan on 2016/3/2.
 */

import rx.Observable
import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject
import rx.subjects.Subject


object MagicBus {
    // 主题
    private val bus: Subject<Any, Any> = SerializedSubject(PublishSubject.create<Any>())
    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者


    // 提供了一个新的事件
    fun post(any: Any) {
        bus.onNext(any)
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    fun <T : Any> toObserverable(eventType: Class<T>): Observable<T> {
        return bus.filter { o -> eventType.isInstance(o) }.cast(eventType)
    }

}