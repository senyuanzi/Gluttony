package sen.yuan.dao.magicbinding.annotation

/**
 * Created by senyuyuan on 2016/11/22.
 */


@Target(AnnotationTarget.PROPERTY)
annotation class BindText(val id: Int)

@Target(AnnotationTarget.PROPERTY)
annotation class BindText2(val id1: Int, val id2: Int)

@Target(AnnotationTarget.PROPERTY)
annotation class BindDateDDFromString(val id: Int)

@Target(AnnotationTarget.PROPERTY)
annotation class BindDateDDFromString2(val id1: Int, val id2: Int)

@Target(AnnotationTarget.PROPERTY)
annotation class BindDateMMFromString(val id: Int)

@Target(AnnotationTarget.PROPERTY)
annotation class BindTextSize(val id: Int)

@Target(AnnotationTarget.PROPERTY)
annotation class BindTextColor(val id: Int)

@Target(AnnotationTarget.PROPERTY)
annotation class BindHint(val id: Int)

@Target(AnnotationTarget.PROPERTY)
annotation class BindHintSize(val id: Int)

@Target(AnnotationTarget.PROPERTY)
annotation class BindHintColor(val id: Int)

@Target(AnnotationTarget.PROPERTY)
annotation class BindBackground(val id: Int)

@Target(AnnotationTarget.PROPERTY)
annotation class BindBackgroundColor(val id: Int)

@Target(AnnotationTarget.PROPERTY)
annotation class BindBackgroundResource(val id: Int)


@Target(AnnotationTarget.PROPERTY)
annotation class BindImageResource(val id: Int)


@Target(AnnotationTarget.PROPERTY)
annotation class BindImageBitmap(val id: Int)

@Target(AnnotationTarget.PROPERTY)
annotation class BindImageDrawable(val id: Int)