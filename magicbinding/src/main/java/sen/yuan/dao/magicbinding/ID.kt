package sen.yuan.dao.magicbinding

/**
 * Created by senyuyuan on 2016/11/22.
 */


val ID = R.id()

fun ID(): Int {
    return R.id::class.java.getField("").getInt(R.id())
}