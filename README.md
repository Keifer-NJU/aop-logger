# aop-logger
使用AOP实现接口日志记录

### 介绍及使用
* aop组件，孵化开源组件，提供spring框架下的服务接口入参，出参的校验，执行时间的打印等功能。
* 使用：
    * 1.导入依赖
```xml
<dependency>
    <groupId>com.ae86</groupId>
    <artifactId>my-practice-aop</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
    * 2.添加扫描包路径
```java
@SpringBootApplication
@ComponentScan(value = {"com.ae86.mypracticeaop", "com.ae86.mypractice01"})
    * 3.使用注解 @MyLog()
