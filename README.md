# Gluttony · 饕餮
Gluttony, a super convenient and simple library to using Android database

饕餮，高效而简约地使用Android数据库

# Overview · 预览
```kotlin
        //save data · 数据的保存
        MyData(2, "john", true, 13).save()
        //find data · 数据的查询
        var dataList = MyData().findAll {
            condition {
                "age" between 11..27
                "isChildren" Not false
            }
            limit(3)
            orderBy("id", SqlOrderDirection.ASC)
        }
        //when MyData setting primaryKey
        var johnData = MyData().findOneByKey(2)
```

# Feature · 特性


# Install · 安装
as a gradle dependency

作为一个gradle依赖库

```groovy
    compile 'sen.yuan.dao:gluttony:1.0'
```
# How to use · 如何使用

### Configuration · 配置

```kotlin
    Gluttony.init(applicationContext, DataBaseConfig(name = "your database name",factory = null, version = 1))
```
在Application或是首个Activity中，初始化Gluttony·饕餮。

DataBaseConfig可配置 数据库名称、SQLiteDatabase.CursorFactory、数据库版本。

### Entities · 实体
实体类无需做任何处理。

Gluttony·饕餮 在数据库中自动为您打理好一切。

另外提供了 注解：@PrimaryKey 用来指定主键。
```kotlin
data class MyData(
    @PrimaryKey
    var id = -1
)
```


### Save · 保存



### Find · 查询



### Update · 更新



### Delete · 删除
