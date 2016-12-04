package sen.yuan.dao.magicbinding

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.zchx.lb.magicbinding.annotation.*
import io.realm.RealmObject
import sen.yuan.dao.gluttony_realm.Gluttony
import sen.yuan.magic.magic_core.xFunctor.timeStamp2Date_dd
import sen.yuan.magic.magic_core.xFunctor.timeStamp2Date_mm
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.declaredMemberProperties
import kotlin.system.measureTimeMillis

/**
 * Created by senyuyuan on 2016/11/22.
 */


/**
 *
 * return 可根据id获取view的map容器
 * */
inline fun <reified T : RealmObject> Activity.render(viewModel: T): () -> Unit {
    return window.decorView.render(viewModel)
}


inline fun <reified T : RealmObject> View.render(viewModel: T): () -> Unit {
    viewModel.addChangeListener<T> { doRender(this, it) }
    val removeListener = { viewModel.removeChangeListeners() }
    this.context.saveReactListener(removeListener)
    return removeListener
}


fun <T : Any> doRender(rootView: View, viewModel: T): MutableMap<Int, View> {
    val views = mutableMapOf<Int, View>()
//    val properties: KProperty1<T, *>
    Log.e("time_find_view", measureTimeMillis {
        catchingViews(views, rootView as ViewGroup)
    }.toString())

    var view: View?

    Log.e("time_bind_data", measureTimeMillis {
        viewModel.javaClass.kotlin.declaredMemberProperties.forEach {
            val property = it
            it.annotations.forEach {
                when (it) {
                    is BindText -> {
                        view = views[it.id]
                        if (view != null) (view as TextView).text = property.get(viewModel) as CharSequence?
                    }
                    is BindDateDDFromString -> {
                        view = views[it.id]
                        if (view != null) (view as TextView).text = (property.get(viewModel) as CharSequence?).toString().timeStamp2Date_dd()
                    }
                    is BindDateDDFromString2 -> {
                        view = views[it.id1]
                        if (view != null) (view as TextView).text = (property.get(viewModel) as CharSequence?).toString().timeStamp2Date_dd()
                        view = views[it.id2]
                        if (view != null) (view as TextView).text = (property.get(viewModel) as CharSequence?).toString().timeStamp2Date_dd()
                    }
                    is BindDateMMFromString -> {
                        view = views[it.id]
                        if (view != null) (view as TextView).text = (property.get(viewModel) as CharSequence?).toString().timeStamp2Date_mm()
                    }
                    is BindTextColor -> {
                        view = views[it.id]
                        if (view != null) (view as TextView).setTextColor(property.get(viewModel) as Int)
                    }
                    is BindTextSize -> {
                        view = views[it.id]
                        if (view != null) (view as TextView).textSize = property.get(viewModel) as Float
                    }
                    is BindHint -> {
                        view = views[it.id]
                        if (view != null) (view as TextView).hint = property.get(viewModel) as CharSequence?
                    }
                    is BindHintColor -> {
                        view = views[it.id]
                        if (view != null) (view as TextView).setHintTextColor(property.get(viewModel) as Int)
                    }
                    is BindBackground -> {
                        view = views[it.id]
                        if (view != null) (view as View).background = property.get(viewModel) as Drawable?
                    }
                    is BindBackgroundColor -> {
                        view = views[it.id]
                        if (view != null) (view as View).setBackgroundColor(property.get(viewModel) as Int)
                    }
                    is BindBackgroundResource -> {
                        view = views[it.id]
                        if (view != null) (view as View).setBackgroundResource(property.get(viewModel) as Int)
                    }
                    is BindImageResource -> {
                        view = views[it.id]
                        if (view != null) (view as ImageView).setImageResource(property.get(viewModel) as Int)
                    }
                    is BindImageBitmap -> {
                        view = views[it.id]
                        if (view != null) (view as ImageView).setImageBitmap(property.get(viewModel) as Bitmap?)
                    }

                    is BindImageDrawable -> {
                        view = views[it.id]
                        if (view != null) (view as ImageView).setImageDrawable(property.get(viewModel) as Drawable?)
                    }
                }
            }
        }
    }.toString())

    return views
}

fun catchingViews(views: MutableMap<Int, View>, viewGroup: ViewGroup): Unit {
    (0..viewGroup.childCount - 1)
            .map { viewGroup.getChildAt(it) }
            .forEach {
                if (it is ViewGroup) catchingViews(views, it)
                else if (it.id != View.NO_ID) views.put(it.id, it)
            }
}


infix fun View.bind(data: Any): ViewBinder {

    return ViewBinder()
}


class ViewBinder()


infix fun ViewBinder.on(viewProperty: ViewProperty) {

}