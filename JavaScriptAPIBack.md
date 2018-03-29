### 服务端JavaScript API说明 ###
```
  var deptuid = douser.getValue("deptuid");

```
> 可调用所有Java API，另外还有些js专用的api

> 不常用的有：
  * docontext:全局Session,  对应JAVA类SessionContext
  * doservice:当前的服务,对应JAVA类 DOService
  * domodel:当前的模型,对应JAVA类 DOIModel
  * dovalue:当前form的值
  * doinstance:当前行的实例对象BOInstance类型
  * douser:登录用户实例BOInstance类型
  * doform:当前表单BOInstance类型
