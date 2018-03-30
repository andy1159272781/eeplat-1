## 基本概念 ##

> 自定义动作是平台功能的扩展，即为了完成平台不能配置的功能。自定义动作有两种实现方式：

  1. 通过脚本在线编写
  1. 实现com.exedosoft.plat.action.Action接口或继承com.exedosoft.plat.action.DOAbstractAction抽象类

> 自定义动作的应用场景：

  1. 服务使用自定义动作：这时服务的执行委托给自定义动作，不再执行服务定义的SQL语句
  1. 参数使用自定义动作：自定义动作的返回值为参数的值
  1. 编码（CodeMain）使用自定义动作：自定义动作的返回值编码的值
  1. 编码项(CodeItem)目使用自定义动作：自定义动作的返回值为编码项目的值
  1. 通过平台提供的javascript callAction 的函数，直接调用自定义动作，完成前台和后台的直接交互

> 自定义动作一般继承DOAbstractAction抽象类，返回有以下情况

  1.返回默认字符串 	 DEFAULT_FORWARD = "success"; NO_FORWARD = "noforward";  成功为success失败返回 noforward
  1.返回对象列表  DOGlobals.getInstance().getRuleContext().setInstances(List<BOInstance> list);
  1.返回对象 DOGlobals.getInstance().getRuleContext().setInstance(BOInstance ins); 
  1.作为参数、编码、编码项的自定义动作直接返回对应的值 如果错误返回noforward
  
> 自定义动作默认属性

  1.java this.actionForm 操作当前的form对象;  js 用doform直接操作
  1.java this.setEchoValue("没有数据！");js 用 SessionContext.getInstance().getThreadContext().setEchoValue("批量删除完成！") ;
  
  

## 配置 ##

> 自定义动作管理入口位于 首页==>基础设施管理==>自定义动作管理。


> ![imgs/action_config.png](imgs/action_config.png)
