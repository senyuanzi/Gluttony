package sen.yuan.dao.gluttony_realm

import io.realm.RealmObject

/**
 * Created by Administrator on 2016/11/23.
 */


/**
 * @return 托管状态 的 model
 * */
fun <T : RealmObject> T.save(): T {

    val mClassAnnotations = this.javaClass.kotlin.annotations

    var managedData: T = this
    Gluttony.realm.executeTransaction {

//        var person = DynamicRealm.getInstance(RealmConfiguration.Builder().build()).createObject("person")

        if (mClassAnnotations.firstOrNull { it is Singleton } != null) {
            it.delete(this.javaClass)
        }

        managedData = it.copyToRealm(this@save)
    }
    return managedData
}

//
///**
// * 对单例模式数据的支持:寻找
// *
// * 注意:必须有@Singleton注解的Int字段
// * */
//inline fun <reified T : RealmObject> T.saveSingleton(): T? {
//    var managedData: T = this
//    Gluttony.realm.executeTransaction {
//        managedData = it.copyToRealm(this@saveSingleton)
//    }
//    return managedData
//}


/**
 * @return 托管状态 的 model
 * */
inline fun <reified T : RealmObject> T.updateOrSave(): T {
    var managedData: T = this
    Gluttony.realm.executeTransaction {
        managedData = it.copyToRealmOrUpdate(this@updateOrSave)
    }
    return managedData
}