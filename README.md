# weSay-java

weSay聊天室

[![Build Status](https://travis-ci.org/Sod-Momas/weSay-java.svg?branch=master)](https://travis-ci.org/Sod-Momas/weSay-java)

## 启动
项目使用apache maven做包管理，使用maven命令可以轻松启动应用。  
如果没有安装maven，请转到[官方网站下载](http://maven.apache.org/download.cgi)

```
 mvn clean tomcat7:run
```
 运行命令后会应用会监听系统的80端口，这个时候打开浏览器，访问`http://localhost/wesay`就可以进入应用了。  
 
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
 
## v1.3版本更新内容

1. 更换消息列表实现类(服务器端)
2. 页面bootstrap和jquery改用远程cdn

## v1.2 版本更新内容
1. 获取消息的接口改为GET请求`/wesay/msg`
2. 添加消息的接口改为POST请求`/wesay/msg`
3. 在线人数获取的接口改为GET请求`/wesay/online/count`