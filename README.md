# 社交聊天应用 - LoginDemo

这是一个基于Android的社交聊天应用，实现了大作业要求的所有功能。

## 功能特性

### 1. 数据库集成 ✅
- 使用Room数据库存储用户和业务数据
- 包含用户表(users)、好友表(friends)、消息表(messages)
- 支持异步数据库操作

### 2. CRUD操作 ✅
- **创建(Create)**: 用户注册、添加好友、发送消息
- **读取(Read)**: 用户登录验证、获取好友列表、加载聊天记录
- **更新(Update)**: 更新用户在线状态、修改好友状态
- **删除(Delete)**: 删除好友、删除消息

### 3. 数据可视化 ✅
- 使用MPAndroidChart库显示图表
- 饼图显示好友状态分布
- 柱状图显示每日消息统计

### 4. 聊天功能 ✅ (加分项)
- 实时聊天界面
- 消息存储和显示
- 支持文本消息

### 5. 小游戏 ✅
- 数学问答游戏
- 随机生成题目
- 计分系统
- 重新开始功能

### 6. 现代化UI设计 ✅
- Material Design 3风格
- 卡片式布局
- 自定义主题色彩
- 响应式设计

## 技术栈

- **开发语言**: 纯Java (无Kotlin)
- **数据库**: Room (SQLite) - Java版本
- **图表库**: MPAndroidChart
- **UI框架**: Material Design 3
- **架构模式**: MVC
- **Java版本**: Java 11

## 项目结构

```
app/src/main/java/com/example/logindemo/
├── entity/          # 数据库实体类
│   ├── User.java
│   ├── Friend.java
│   └── Message.java
├── dao/             # 数据访问对象
│   ├── UserDao.java
│   ├── FriendDao.java
│   └── MessageDao.java
├── database/        # 数据库相关
│   ├── AppDatabase.java
│   └── DatabaseManager.java
├── MainActivity.java      # 登录界面
├── HomeActivity.java      # 主界面
├── ChatActivity.java      # 聊天界面
├── ChartsActivity.java    # 图表界面
├── GameActivity.java      # 游戏界面
└── CustomIconButton.java  # 自定义组件
```

## 使用方法

### 登录
- 用户名: `admin`, 密码: `123456`
- 用户名: `user1`, 密码: `password`
- 用户名: `user2`, 密码: `password`

### 主要功能
1. **登录**: 输入用户名和密码，选择头像
2. **好友管理**: 查看好友列表，添加新好友
3. **聊天**: 点击好友进入聊天界面
4. **数据图表**: 查看好友状态分布和消息统计
5. **小游戏**: 玩数学问答游戏

## 大作业要求完成情况

✅ **要求1**: APP基于小作业改进，具有自己的内容和主题
✅ **要求2**: 所有用户数据和业务数据存储在数据库中
✅ **要求3**: 实现CRUD操作（至少3种）
✅ **要求4**: 通过图表显示数据库内容
✅ **要求5**: 完成聊天界面（加分项）
✅ **要求6**: 完成自制小游戏

## 安装和运行

1. 使用Android Studio打开项目
2. 同步Gradle依赖
3. 连接Android设备或启动模拟器
4. 点击运行按钮

## 注意事项

- 需要Android API 24+ (Android 7.0)
- **100%纯Java项目**：无任何Kotlin文件（.kt/.kts）
- 使用传统Gradle配置（.gradle）而非Kotlin DSL（.kts）
- 首次运行会自动创建测试数据
- 图表功能需要网络权限（用于加载图表库）
- 兼容Java 8+语法，使用传统匿名内部类而非lambda表达式

## 开发者

基于原有LoginDemo项目扩展开发，实现了完整的社交聊天应用功能。
