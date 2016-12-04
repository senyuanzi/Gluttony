package sen.yuan.dao.magicbinding

import io.realm.RealmObject
import sen.yuan.dao.gluttony_realm.Gluttony
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by senyuyuan on 2016/8/4.
 */
//    * 时间戳转换成日期格式字符串
//    * @param seconds 精确到秒的字符串
//    * @param formatStr
//    * @return
//    */
fun String.timeStamp2Date_mm(): String {
    if (this.isEmpty() || this.equals("null")) {
        return "";
    }
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm");
    return sdf.format(Date((this + "000").toLong()))
}

fun String.timeStamp2Date_dd(): String {
    if (this.isEmpty() || this.equals("null")) {
        return "";
    }
    val sdf = SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(Date((this + "000").toLong()))
}


fun <T : RealmObject> T.toReactive(): T {
    return if (this.isManaged) this else {
        var reactiveData: T = this
        Gluttony.realm.executeTransaction { reactiveData = it.copyToRealm(this) }
        reactiveData
    }
}


fun <T : RealmObject> T.toNormal(): T = if (this.isManaged.not()) this else Gluttony.realm.copyFromRealm(this)


