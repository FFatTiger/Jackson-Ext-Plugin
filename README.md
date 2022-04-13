# Jackson-Ext-Plugin（Initializing)

> 这是一个jackson扩展插件，没有任何技术，只是把平常常用的格式化的功能整合且做了简化，一切并发与安全都依赖jackson本身

## 1.简介

该项目旨在将对象格式化从业务逻辑中解耦出来。主要是将[Jackson](https://github.com/FasterXML/jackson)
开放的自定义序列化做整合封装简化，和实现一些常用的格式化注解，如将Date对象转换为指定的格式String，将敏感信息脱敏等。对于更复杂的格式化场景，封装了比起实现JsonSerializer更简单的接口。

## 2.依赖前提

+ 项目使用了[SpringMvc](https://github.com/spring-projects/spring-framework)，且确保HttpMessageConverter为默认的Jackson实现

## 3.使用

### 3.1.注解

### 3.1.1.注解通用属性serializeType

该属性用于指定注解生效场景

+ 场景1:  不带类型的序列化，一般可见于MVC ResponseBody的json，用于前端展示，单纯传递属性数据
+ 场景2:  带类型的序列化，一般可见于Redis序列化，需要反序列化的缓存或存储，会携带类的全限定名

SerializeType枚举项

|               | 不带类型序列化 | 带类型序列化 | 默认  |
|:-------------:|---------|--------|-----|
|   ONLY_SER    | 是       | 否      | 否   |
| ONLY_TYPE_SER | 否       | 是      | 否   |
|      ALL      | 是       | 是      | 是   |

### 3.1.2.DateFormat日期格式化

序列化：将Date对象转换为json中的字符串格式

反序列化： 将String字符串

|         | Desc | Example    |
|---------|------|------------|
| pattern | 日期格式 | yyyy-MM-dd |



