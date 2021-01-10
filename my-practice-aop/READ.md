# AOP + 注解 实现接口操作日志

### 任务分解
* 工程准备-导入依赖
* 定义注解(Mylog)
* 编写日志记录对象类(MyLogDao)
  * 操作模块、操作类型、操作描述、操作用户、消耗时间、根路径、URI、URL、请求类型、IP地址、请求参数、返回结果
* 编写切面类(MyLogAspect)
  * 切面
  * 切点
  * 通知（前、后、环绕...）
 

### 入参逻辑 任务分解
* 工程准备（如上）
* 定义请求逻辑注解(RequestLogic)
    * 是否入参判空、是否入参边界检查、是否入参打印、是否出参打印、是否计算方法耗时
* 编写切面类（参数逻辑检验, RequestLogicParamValidAspect）

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
```
    * 2.添加扫描包路径
```java
@SpringBootApplication
@ComponentScan(value = {"com.ae86.mypracticeaop", "com.ae86.mypractice01"})
```
    * 3.使用注解 @MyLog()
    

