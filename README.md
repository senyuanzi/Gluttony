# Gluttony · 饕餮
Gluttony, a super convenient and simple library to using Android database

饕餮，高效而简约地使用Android数据库

Library is compatible with Kotlin 1.0.5-2 build.

饕餮 兼容 kotlin 1.0.5-2 版本（最新版）



## Feature · 特性

## Content · 目录

* [Install · 安装](#install--安装)
* [How to use · 如何使用](#how-to-use--如何使用)
	* [Configuration · 配置](#configuration--配置)
	* [Entities · 实体](#entities--实体)
	* [Save · 保存](#save--保存)
	* [Find · 查询](#find--查询)
	* [Update · 更新](#update--更新)
	* [Delete · 删除](#delete--删除)
	* [Condition · 条件](#condition--条件)
 

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


## Save · 保存
### Save Entity directly · 直接保存实体
```kotlin
        val user = UserData()
        user.id = 666
        user.name = "sen"
        user.age = 23
        user.isChildren = false
        user.save()

        //or
        UserData(2, "john", 12, true).save()
```


## Find · 查询
### Find Entity based on PrimaryKey · 根据primary key 查询数据
```kotlin
        val user1 = UserData().findOneByKey(666)
```

### Find the first Entity based on Condition · 根据条件 查询第一个数据
```kotlin
        val user2 = UserData().findOne {
            condition {
                "age" between 7..16
                "isChild" Not false
            }
            orderBy("age", SqlOrderDirection.ASC)
        }
```

### Find all Entities based on Condition · 根据条件 查询所有数据，返回值为一个列表
```kotlin
        val userList = UserData().findAll {
            condition {
                "age" moreThan 11
            }
        }
```

## Update · 更新

### Update Entity directly base on PrimaryKey · 根据primary key 直接更新实体
```kotlin
	var user3 = UserData().findOne { condition { "name" equalsData "lucy" } }!!
        user3.age += 1
        user3.update()
        
        //or
        
        var user4 = UserData(7, "lucy", 10, true)	//user4.id == user3.id · 注意primary key相同
        user4.update()	//user4 will overwrite the old data · 将会覆盖掉旧数据
```

### Update Entity directly or Save Entity when it doesn't exist · 直接 更新或保存实体 （如果实体是未保存过的话）
```kotlin
        var user5 = UserData(90, "white", 77, false)	// 90 is a new primary key
        user5.updateOrSave()	// Gluttony will save a new data
```

### Update Entity based on PrimaryKey · 根据primary key 更新实体
```kotlin
	//update user who id is 90 to named black,age 80
	//lambda
        UserData().updateByKey(90) { arrayOf("name" to "black", "age" to 80) }

        //or pairs
        UserData().updateByKey(90, "name" to "green", "age" to 82)
```

### Update all Entities based on Condition · 根据条件 更新所有实体
```kotlin
	//update user who name is green to name red,age 99
        UserData().update("name" to "red", "age" to 99) {
            condition {
                "name" equalsData "green"
            }
        }
```

## Delete · 删除

### Delete Entity directly · 直接删除实体
```kotlin
        var user9 = UserData(90)	//only need primary key
        user9.delete()
```


### Delete Entity based on PrimaryKey · 根据primary key 删除实体
```kotlin
        UserData().deleteByKey(666)	//delete user which id is 666
```

### Delete all Entities based on Condition · 根据条件 删除所有实体
```kotlin
	//delete users who is child
        UserData().delete {
            condition {
                "isChild" equalsData true
            }
        }
```

### Clear one Class's entities · 清空一个类的所有实体
```kotlin
        UserData().clear()
```

## Condition · 条件

* equalsData · 等于

* moreThan · 大于

* moreThanOrEquals · 大于等于

* lessThan · 小于

* lessThanOrEquals · 小于等于

* Not · 非

* In:		Determine whether in the array · 判断是否在数组中 

* notIn:	Determine whether not in the array

* between:	Determine whether in the range

* like:		fuzzy query · 模糊查询

```

two marks: % and _ · 两个通配符：% 和 _

% : indefinite amount content · 不定数量的内容

_ : one amount content · 一个位置的内容

for example:	"gluttony%" -> find values witch is starting with "gluttony"

例如，"gluttony%" -> 查询所有以"gluttony"开头的数据

```

##### Welcome to staring the project, thanks!

##### 欢迎关注(star)本项目，O(∩_∩)O谢谢！
