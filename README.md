# Introduction

Snaker是一个基于Java的轻量级工作流引擎，适用于企业应用中常见的业务流程。本着轻量、简单、灵巧理念设计，定位于简单集成，多环境支持。

**轻量**: snaker-core.jar大小208K，代码行数约7000行，强大的扩展支持，不依赖于具体的ORM框架

**简单**: 表设计简单，流程组件简单[start/end/task/custom/subprocess/decision/fork/join]

**灵巧**: 暴露大量可扩展接口，支持流程设计器、流程引擎的组件模型自定义

原项目中是xml文件方式配置bean，本项目完全采用springboot方式配置，整合了mybatis-plus，springboot2.3.2
可动态灵活的用yml文件配置
