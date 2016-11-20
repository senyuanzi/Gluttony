*# Gluttony · 饕餮
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

## Content · 目录

* [Install · 安装](#Install--安装)
* [How to use · 如何使用](#How-to-use--如何使用)
        * [Configuration · 配置](#Configuration--配置)
        * [Entities · 实体](#Entities--实体)
 

## Install · 安装
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
        UserData(4, "su", 80, false).save()
```


### Find · 查询
* 根据 primary key 查询 实体
```kotlin
        val user1 = UserData().findOneByKey(666)
        e { user1?.name ?: "no user witch id is 666" } // "sen"
```

* 查询符合条件的 第一个 实体
```kotlin
        val user2 = UserData().findOne {
            condition {
                "age" between 7..16
                "isChild" Not false
            }
            orderBy("age", SqlOrderDirection.ASC)
        }
        e { user2?.name ?: "no user witch age is between 7..16 and isChild is not false" } // "lucy"
```

* 查询符合条件的 所有 实体，以列表的形式返回
```kotlin
        val userList = UserData().findAll {
            condition {
                "age" moreThan 11
            }
        }
        e { userList.size.toString() } // 3
```

### Update · 更新

* 实体 直接调用 update()，通过 primary key 定位
```kotlin
var user3 = UserData().findOne { condition { "name" equalsData "lucy" } }!!
        user3.age += 1
        user3.update()
        user3 = UserData().findOne { condition { "name" equalsData "lucy" } }!!
        e { user3.age.toString() } // 9
        
        //or
        
        var user4 = UserData(7, "lucy", 10, true)//user4.id == user3.id · 注意primary key相同
        user4.update()//user4 will overwrite the old data · 将会覆盖掉旧数据
        user4 = UserData().findOneByKey(7)!!
        e { user4.age.toString() } // 10
```

* updateOrSave() 在数据不存在的情况下，会新建数据
```kotlin
        var user5 = UserData(90, "white", 77, false)// 90 is a new primary key
        user5.updateOrSave()// Gluttony will save a new data
        user5 = UserData().findOneByKey(90)!!
        e { user5.name } // "white"
```

* 通过primary key来更新数据，提供两种语法
```kotlin
        UserData().updateByKey(90) { arrayOf("name" to "black", "age" to 80) }
        val user6 = UserData().findOneByKey(90)!!
        e { user6.name + ":" + user6.age } //white:77->black:80

        //or

        UserData().updateByKey(90, "name" to "green", "age" to 82)
        val user7 = UserData().findOneByKey(90)!!
        e { user7.name + ":" + user7.age } //black:80->green:82
```

* 更新符合条件的所有 数据
```kotlin
        UserData().update("name" to "red", "age" to 99) {
            condition {
                "name" equalsData "green"
            }
        }
        val user8 = UserData().findOneByKey(90)!!
        e { user8.name + ":" + user8.age } //green:82->red:99
```

### Delete · 删除

* 实体 直接调用 delete()
```kotlin
        var user9: UserData?
        user9 = UserData(90)//only need primary key
        user9.delete()
        user9 = UserData().findOneByKey(90)
        e { user9?.name ?: "no user" }//no user
```

* 根据primary key 删除指定数据
```kotlin
        UserData().deleteByKey(666)//delete sen
        val user10 = UserData().findOneByKey(666)
        e { user10?.name ?: "no user" }//no user
```

* 删除符合条件的所有 数据
```kotlin
        UserData().delete {
            condition {
                "isChild" equalsData true
            }
        }//john and lucy will be deleted
        val children = UserData().findAll { condition { "isChild" equalsData true } }
        e { children.size.toString() }//0
```

* 清空该类所有数据
```kotlin
        e { UserData().findAll { }.size.toString() }//1
        UserData().clear()
        e { UserData().findAll { }.size.toString() }//0
```
