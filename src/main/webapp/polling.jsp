<%--
  Created by IntelliJ IDEA.
  User: sothe
  Date: 2018/5/26
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="keywords" content="chat,wesay,momas"/>
    <meta http-equiv="description" content="a simple chatroom on Internet!"/>
    <base href="${pageContext.request.contextPath}/">
    <title>莫莫砂小聊天室</title>


    <%--<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->--%>
    <%--<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">--%>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <%--<script type="text/javascript" src="static/jquery/jquery.min-1.9.1.js"></script>--%>
    <%--<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->--%>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <%--<script type="text/javascript" src="static/bootstrap3/scripts/bootstrap.min.js"></script>--%>
    <script type="text/javascript" src="static/wesay/wesay-polling.js" charset="utf-8"></script>
    <%--<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->--%>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <%--<link rel="stylesheet" href="static/bootstrap3/css/bootstrap.min.css" type="text/css"/>--%>
    <link rel="stylesheet" href="static/wesay/wesay.css" type="text/css"/>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <!--聊天窗口-->
                <div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12 box">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <strong>莫莫砂聊天室</strong>
                                <small>当前在线人数:<span id="online">0</span>人</small>
                                <button type="button" class="close" aria-label="Close" id="close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </h3>
                        </div>
                        <div class="panel-body">
                            <div class="form-control" id="msgContent"><font color="#999">没有其他消息了</font></div>
                            <hr/>
                            <div class="input-group ">
                                <span class="input-group-addon" id="username">点此改名</span>
                                <input class="form-control" id="msg" autofocus/>
                                <span class="input-group-btn">
                                    <button class="btn btn-default" id="btnsend">发送</button>
                                </span>
                            </div>
                        </div>
                        <!--<div class="panel-footer">
                                    <small>莫莫砂聊天室，在这里，真的没人知道你是谁</small>
                                </div>-->
                    </div>
                </div>
                <!--end -- 聊天窗口-->

            </div>
        </div>
    </div>
</div>
</body>
</html>