# basic-component
quickly develop basic components for monolithic applications

# architecture
`
- [ ] |-- taoq-boot-common			   -- 常用的基础公用组件
- [x] |   |-- taoq-boot-starter-common		-- 公共依赖
- [x] |   |-- taoq-boot-starter-web		    -- 对 SpringMVC 的封装
- [x] |   |-- taoq-boot-starter-knife4j		-- 对于 knife4j 的封装
- [x] |   |-- taoq-boot-starter-oss		    -- 对于本地文件上传/MinIO/第三方对象存储服务的封装
- [x] |   |-- taoq-boot-starter-system		-- 提供字典、参数配置管理等基础功能
- [x] |   |-- taoq-boot-starter-rbac		-- 提供用户、角色、权限等基础功能
- [x] |   |-- taoq-boot-starter-websocket	-- 基于 spring websocket 的封装
- [ ] |-- taoq-boot-dependencies	       -- taoq-boot项目本身各子模块的依赖管理，以及第三方模块的依赖管理
- [ ] |-- taoq-boot-orm			       -- orm 模块
- [x] |   |-- taoq-boot-starter-mp			-- 对 MyBatisPlus 的封装
- [ ] |   |-- taoq-boot-starter-mybatis		-- 对 MyBatis 的封装
- [ ] |   |-- taoq-boot-starter-jpa			-- 对 SpringDataJPA 的封装
- [ ] |-- taoq-boot-security		   -- security 模块
- [ ] |   |-- taoq-boot-starter-satoken		-- 对 Sa-Token 的封装
- [ ] |   |-- taoq-boot-starter-security	-- 对 Spring Security 的封装
- [ ] |-- taoq-boot-job			       -- 定时任务模块
- [x] |   |-- taoq-boot-starter-task		-- 基于 JDK 线程池封装的定时任务
- [ ] |   |-- taoq-boot-starter-quartz		-- 基于 Quartz 封装的定时任务
- [ ] |   |-- taoq-boot-starter-xxljob		-- 基于 xxl-job 封装的定时任务
- [ ] |   |-- taoq-boot-starter-powerjob	-- 基于 PowerJob 封装的定时任务
- [ ] |-- taoq-boot-mq			       -- 消息队列模块
- [ ] |   |-- taoq-boot-starter-rabbitmq	-- 对 RabbitMQ 的封装
- [ ] |   |-- taoq-boot-starter-kafka		-- 对 Kafka 的封装
- [ ] |   |-- taoq-boot-starter-rocketmq	-- 对 RocketMQ 的封装
- [ ] |   |-- taoq-boot-starter-pulsar	    -- 对 Pulsar 的封装
- [ ] |-- taoq-boot-push      		   -- 推送模块
- [ ] |   |-- taoq-boot-starter-getui		-- 对个推推送的封装
- [ ] |   |-- taoq-boot-starter-jpush	    -- 对极光推送的封装
- [ ] |-- taoq-boot-extends			   -- 扩展模块，大多是对于一些第三方组件的扩展处理
- [ ] |   |-- taoq-boot-starter-redis		-- 对 Redis 的封装
- [x] |   |-- taoq-boot-starter-mail		-- 对于发送邮件的封装
- [ ] |   |-- taoq-boot-starter-sms		    -- 短信发送模块
- [ ] |   |-- taoq-boot-starter-xss			-- xss 防注入相关
- [ ] |   |-- taoq-boot-starter-pay			-- 支付模块
- [ ] |   |-- taoq-boot-starter-robot		-- 对钉钉/企业微信/飞书的一些操作封装
`