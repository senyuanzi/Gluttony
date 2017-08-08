package dao.yuan.sen.gluttonyexample

import dao.yuan.sen.gluttony.sqlite_module.annotation.Ignore
import dao.yuan.sen.gluttony.sqlite_module.annotation.PrimaryKey
import java.io.Serializable

/**
 * Created by Administrator on 2016/11/19.
 */
data class UserData(
        @PrimaryKey
        var id: Int? = null,
        var name: String = "",
        var age: Int = -1,
        var isChild: Boolean = false,
        @Ignore
        var thisIgnore: String = "thisIgnore"
) : Serializable