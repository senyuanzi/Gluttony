package dao.yuan.sen.gluttonyexample

import dao.yuan.sen.gluttony.sqlite_module.annotation.PrimaryKey
import java.io.Serializable

/**
 * Created by LOVE on 2017/8/8 008.
 */
data class School(
        @PrimaryKey
        var id: Int? = null,
        var name: String = "",
        var student: List<UserData>? = null
) : Serializable