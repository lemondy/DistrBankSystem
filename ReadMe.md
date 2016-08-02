# DistriBankSystem(分布式银行系统)

##系统架构

本系统的架构设计如下

![系统架构](./doc/architecture.png)

由 Zookeeper 统一处理用户的请求调度。

##实现的特点

1. Zookeeper 协调者的功能
2. 分布式数据库的两阶段锁机制
3. 日记记录管理