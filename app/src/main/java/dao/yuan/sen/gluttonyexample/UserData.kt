package dao.yuan.sen.gluttonyexample

import dao.yuan.sen.gluttony.annotation.PrimaryKey

/**
 * Created by Administrator on 2016/11/19.
 */


data class UserData(
        @PrimaryKey
        var id: Int = -1,
        var name: String = "",
        var age: Int = -1,
        var isChild: Boolean = false
)