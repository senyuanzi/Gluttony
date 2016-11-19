# Gluttony · 饕餮
Gluttony, a super convenient and simple library to using Android database

饕餮，高效而简约地使用Android数据库

Library is compatible with Kotlin 1.0.5-2 build.

饕餮 兼容 kotlin 1.0.5-2 版本（最新版）

## Overview · 预览
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
        //find the entity with primarykey · 通过 主键 寻找指定 实体
        var johnData = MyData().findOneByKey(2)
```

##### Welcome to staring the project, thanks!

##### 欢迎关注(star)本项目，O(∩_∩)O谢谢！

## Feature · 特性


### Install · 安装
as a gradle dependency

作为一个gradle依赖库

```groovy
    compile 'sen.yuan.dao:gluttony:1.0'
```
## How to use · 如何使用

### Configuration · 配置

```kotlin
        //configure databaseName,cursorFactory,databaseVersion 
        //配置 数据库名称，cursorFactory，数据库版本
        Gluttony.init(this, GluttonyConfig("gluttony_example",null,1))
```
In Application or the first Activity, initialize Gluttony. 

在Application或是首个Activity中，初始化Gluttony·饕餮。

### Entities · 实体
Entities do not need to do any processing. 

实体类无需做任何处理。

Gluttony automatically for you to take care of everything. 

Gluttony·饕餮 在数据库中自动为您打理好一切。

Annotation: @PrimaryKey is used to specify a PrimaryKey.

注解：@PrimaryKey 用来指定主键。
```kotlin
data class UserData(
        @PrimaryKey
        var id: Int = -1,
        var name: String = "",
        var age: Int = -1,
        var isChildren: Boolean = false
)
```


### Save · 保存
* 实体 直接调用 save()
```kotlin
        val user = UserData()
        user.id = 666
        user.name = "sen"
        user.age = 23
        user.isChildren = false
        user.save()

        //or

        UserData(2, "john", 12, true).save()
        UserData(7, "lucy", 8, true).save()
```


### Find · 查询
* 根据 primary key 查询 实体
```kotlin
        val user1 = UserData().findOneByKey(666)
        e { user1?.name ?: "no user witch id is 666" } // "sen"
```

* 查询符合条件的 第一个 实体
```kotlin
var user = UserData().findOne {
        
}
```

* 查询符合条件的 所有 实体，以列表的形式返回




### Update · 更新



### Delete · 删除


### Bulk operating · 批量操作
