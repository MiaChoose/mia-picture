<img width="467" height="215" alt="image" src="https://github.com/user-attachments/assets/deacdbda-4fd0-4a51-b821-48e4f4e63f06" />
<img width="467" height="215" alt="image" src="https://github.com/user-attachments/assets/f240e9b8-f486-4c8e-b0d1-c7fbd01d02bc" />
<img width="467" height="215" alt="image" src="https://github.com/user-attachments/assets/55e91e3d-73b6-4b1e-8572-3f3587d11e21" />
<img width="467" height="215" alt="image" src="https://github.com/user-attachments/assets/23ba37fe-dbda-4731-ba42-441bc2dbd325" />

# Mia图库 — 后端服务 | Mia Picture Gallery — Backend

> 基于 Spring Boot 的图片协同社区后端服务。

---

## 📖 项目简介 | About

**Mia图库**是一个集协同编辑图片、社区互动、智能问答、AI创作于一体的综合性社交平台。后端仓库，采用 **Spring Boot 前后端分离架构**，提供 RESTful API、WebSocket 实时通信等能力。

---
## 📂 项目结构 | Project Structure

```
mia-picture-backend/
├── src/main/java/com/lumenglover/miapicturebackend/
│   ├── annotation/       # 自定义注解（权限校验、限流）
│   ├── aop/              # AOP 切面（限流、多设备登录控制）
│   ├── api/              # 第三方 API 封装（阿里云 AI、Pexels）
│   ├── config/           # Spring 配置类
│   ├── constant/         # 常量定义
│   ├── controller/       # REST 控制器
│   ├── exception/        # 全局异常处理
│   ├── init/             # 启动初始化（Bot 用户等）
│   ├── job/              # 定时任务
│   ├── manager/          # 业务管理器（COS、ShardingSphere、WebSocket）
│   ├── mapper/           # MyBatis Mapper
│   ├── model/            # 实体类、DTO、VO、枚举
│   └── service/          # 业务服务层
├── src/main/resources/
│   ├── application.yml           # 主配置
│   ├── application-dev.yml       # 开发环境配置
│   └── META-INF/spring.factories # SPI 注册
├── python-rag/                   # Python RAG 子服务
├── docker-compose.yml            # Docker 编排
├── Dockerfile                    # Java 服务镜像
└── .env.example                  # 环境变量模板
```
