# Introduction

Snaker是一个基于Java的轻量级工作流引擎，适用于企业应用中常见的业务流程。本着轻量、简单、灵巧理念设计，定位于简单集成，多环境支持。

**轻量**: snaker-core.jar大小208K，代码行数约7000行，强大的扩展支持，不依赖于具体的ORM框架

**简单**: 表设计简单，流程组件简单[start/end/task/custom/subprocess/decision/fork/join]

**灵巧**: 暴露大量可扩展接口，支持流程设计器、流程引擎的组件模型自定义

原项目中是xml文件方式配置bean，snakerflow-spring-boot-stater完全采用springboot方式配置，整合了mybatis-plus，springboot2.3.2，摒弃了所有xml配置bean的形式，可动态灵活的用yml文件配置,开箱即用。多种sql执行器、缓存类型动态切换。并扩展了redis类型的缓存模式。

## 1. 快速开始

该项目已同步至maven中央仓库

### 1.1 引入依赖

```xml
    <dependency>
            <groupId>com.github.zc-libre</groupId>
            <artifactId>snakerflow-spring-boot-stater</artifactId>
            <version>2.0.3</version>
    </dependency>
```

### 1.2 配置

```yaml
snaker:
  flow:
    auto-init-schema: false       #是否初始化数据库脚本，默认为false
    db-access-type: mybatis_plus  #sql执行器类型 可选 jdbc、spring、mybatis_plus。默认为jdbc
    expression-type: juel         #决策表达式类型 可选juel和spel。默认为juel
    cache:
      cache-type: redis           #缓存类型 可选内存、ehcache、redis
      timeout: 2                  #过期时间  (当前版本仅对redis模式生效，后续会对其他类型做扩展)
      time-unit: hours            #时间单位  (同上)
      key-prefix: "snakerflow::"  #缓存键前缀(同上)
```

注：1. DBAccess使用mybatis-plus模式需配置mybatis-plus的分页插件。

​        2.缓存使用redis模式需配置RedisTemplate<String, Objecet>  bean。

项目使用文档：https://zc-libre.github.io/snaker-doc/

有问题欢迎交流，本项目对你有用的话，欢迎star。有问题请加 QQ: 504879189
