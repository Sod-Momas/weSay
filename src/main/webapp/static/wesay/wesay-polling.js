"use strict";

var Util = {
    getMsg: function getMsg() {
        $.ajax({
            url: "msg",
            type: "get",
            dataType: "json",
            error: function () {
                $("#msgContent").append('<span style="color: red;">拉取数据出错,请刷新页面重试</span><br/>');
                clearInterval(window.interval);
                clearInterval(window.online);
            },
            success: function (data) {
                var msgContent = document.getElementById("msgContent");
                msgContent.innerHTML = '';

                for (var index = 0, len = data.length; index < len; ++index) {
                    /*
                    <div>
                        <p><b> name </b></p>
                        <p> content </p>
                    </div>
                     */
                    var item = data[index];
                    var div = document.createElement("div");
                    var nameP = document.createElement("p");
                    var nameB = document.createElement("b");
                    var name = document.createTextNode(decodeURI(item.username));
                    var contentP = document.createElement("p");
                    var content = document.createTextNode(decodeURI(item.content));

                    nameB.appendChild(name);
                    nameP.appendChild(nameB);
                    contentP.appendChild(content);
                    div.appendChild(nameB);
                    div.appendChild(contentP);
                    msgContent.appendChild(div)
                }
                // 滚动条滚到底部,否则无法看到最新消息
                msgContent.scrollTop = document.getElementById('msgContent').scrollHeight;
            }
        });
    },
    sendMsg: function sendMsg() {
        document.getElementById("msg").focus();
        var content = $("#msg").val();
        if (!content.trim()) {
            return;
        }
        if (content.length > 200) {
            content = content.substr(0, 200);
            return;
        }
        $(".input-group").removeClass("has-warning");
        var username = $("#username").html();
        if (username === '点此改名') {
            username = '匿名用户';
        }

        $.ajax({
            url: "msg",
            type: "post",
            data: {"username": encodeURI(username), "content": encodeURI(content)},
            dataType: "json",
            complete: function () {
                $("#msg").val("");
            }
        });
        clearInterval(self.interval);
        this.getMsg();
        self.interval = setInterval("Util.getMsg()", 3000);


    },

    getOnline: function getOnline() {
        $.ajax({
            url: "online/count",
            type: "get",
            dataType: "json",
            error: function () {
                $("#online").text('<span style="color: #F00">?</span>');
            },
            success: function (data) {
                $("#online").text(data.onlineCount);
            }
        });
    }
};


jQuery(document).ready(function () {

    window.interval = setInterval("Util.getMsg()", 3000);
    window.online = setInterval("Util.getOnline()", 5000);

    Util.getMsg();
    $("#btnsend").click(function () {
        Util.sendMsg();
        Util.getMsg();
    });

    $("#msg").keydown(function (event) {
        var content = $("#msg").val();
        if (content.length > 150) {
            $(".input-group").addClass("has-warning");
        } else {
            $(".input-group").removeClass("has-warning").removeClass("has-error");
        }
        if (content.length > 200) {
            $(".input-group").addClass("has-error");
            return;
        }
        var e = event || window.event;
        var keyCode = e.keyCode || e.which;
        if (keyCode === 13) {
            Util.sendMsg();
        }
    });
    $("#username").click(function () {
        var defaultPrompt = '您的名字';
        var defaultName = '匿名用户';
        var name = prompt("请输入您的昵称以供聊天", defaultPrompt);
        name = name === defaultPrompt ? defaultName : name;
        $("#username").text(name != null ? name : defaultName);
    });
});

