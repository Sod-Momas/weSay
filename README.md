# wesay

wesay聊天室

[![Build Status](https://travis-ci.org/Sod-Momas/wesay.svg?branch=master)](https://travis-ci.org/Sod-Momas/wesay)

## 启动

1. 使用 maven 插件直接启动
 
    > 如果没有安装 Apache Maven, 请转到[官方网站下载](http://maven.apache.org/download.cgi)
    
    ```
     mvn clean tomcat7:run
    ```
    
    运行命令后会应用会监听系统的 80 端口，这个时候打开浏览器，访问 `http://localhost/wesay` 就可以进入应用了。  
 
2. 使用 Servlet 容器运行

    > 这里使用 Apache Tomcat 8.0.33 举例
    
    1. 先把源码下载到本地
    
    ```bash
    git clone https://github.com/Sod-Momas/wesay.git
    ```
    
    2. 进入项目根目录
    
    ```bash
    cd wesay
    ```
    
    3. 执行打包命令
    
    ```bash
    mvn clean package
    ```
    
    4. 把打包好的 war 包复制到 tomcat 的 webapps 文件夹下
    
    ```bash
    cp target/wesay.war /apache-tomcat-8.0.33/webapps/wesay.war
    ```
    
    5. 启动 tomcat ,tomcat 会自动部署(自动解压war包)
    
    ```bash
    # Windows 环境使用bat文件, *nix环境下使用sh文件
    /apache-tomcat-8.0.33/bin/startup.bat
    ```
    
    6. 访问项目
    
    用浏览器打开 `http://localhost:8080/wesay`,就可以看到聊天窗口了. 您可以使用多个浏览器打开, 不同浏览器视作不用用户,可以互相聊天

## 错误处理

 如果80端口被其他应用占用，请停止占用端口的应用或配置weSay使用其他端口：
 将项目中的`pom.xml`中的配置：
 
 ```xml
<build>
    ...
    <port>80</port>
    ...
</build>
```

改为

```xml
<build>
    ...
    <port>8080</port>
    ...
</build>
```

可以使用8080端口，访问的地址改为`localhost:8080/wesay`

 - - -
 
## v1.5版本更新

1. 重构消息列表, 底层数据结构从数组改为链表, 使用Stream API 进行json序列化,代码更简洁
2. 重构监听器, 使用原子类记录在线用户, 避免因多线程带来的问题
3. 前端优化为出错后不再重试, 直到用户刷新页面, 这样可以给用户节省流量, 也因为没有满屏幕的错误信息, 提高用户体验
4. 删除过期代码, 其他小优化