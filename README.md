基于Spring Cloud微服务化开发平台，具有统一授权、认证后台管理系统，其中包含具备用户管理、资源权限管理、网关API 管理等多个模块，支持多业务系统并行开发，可以作为后端服务的开发脚手架。代码简洁，架构清晰，适合学习和直接项目中使用。
核心技术采用Spring Boot 2.0.7以及Spring Cloud Finchley.SR2，Amazon Corretto 8 jdk相关核心组件
使用spring cloud config完成自动更新，灰色发布
使用spring cloud zuul 2.0实现动态路由，与jwt权限校验，令牌桶算法完成限流
spring-boot-starter-data-redis，spring data mongodb，spring data elasticsearch，spring data ribbitmq完成业务开发
使用spring webflux，spring-boot-starter-data-mongodb-reactive 开发shop-order项目，完成响应式流项目的开发
完全可以基于此项目在企业中开发手脚架
