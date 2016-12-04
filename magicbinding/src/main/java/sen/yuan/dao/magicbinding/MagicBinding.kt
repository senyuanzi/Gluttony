package sen.yuan.dao.magicbinding

import android.app.Activity
import android.app.Application
import android.os.Bundle
import sen.yuan.dao.gluttony_realm.Gluttony

/**
 * Created by Administrator on 2016/12/4.
 */
object MagicBinding {

    var activityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
        }

        override fun onActivityStarted(p0: Activity?) {
        }

        override fun onActivityResumed(p0: Activity?) {
        }

        override fun onActivityPaused(p0: Activity?) {

        }

        override fun onActivityStopped(p0: Activity?) {
        }

        override fun onActivityDestroyed(p0: Activity?) {
            p0?.clearAllReactListeners()
        }

        override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
        }
    }

    fun init(baseApp: Application) {
        Gluttony.init(baseApp)

        baseApp.registerActivityLifecycleCallbacks(activityLifecycleCallbacks)


    }


    fun onTerminate(baseApp: Application) {
        baseApp.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }
}