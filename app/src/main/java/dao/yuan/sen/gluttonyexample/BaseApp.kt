package dao.yuan.sen.gluttonyexample

import android.app.Application
import dao.yuan.sen.gluttony.Gluttony
import dao.yuan.sen.gluttony.sqlite_module.GluttonyConfig

/**
 * Created by Administrator on 2016/11/19.
 */

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Gluttony.init(this, GluttonyConfig("gluttony_example",null,1))
//        Gluttony.init(this)
    }
}