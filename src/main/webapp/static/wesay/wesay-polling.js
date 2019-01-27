function getMsg() {
    $.ajax({
        url: "msg",
        type: "get",
        dataType: "json",
        error: function (data, textStatus) {
            $("#msgContent").append('<span style="color: red;">拉取数据出错</span><br/>');
        },
        success: function (data, textStatus) {
            $("#msgContent").empty();

            $.each(data, function (i, obj) {
                var $pusername = $("<p>");
                var $pmsg = $("<p>");
                var $b = $("<b>");

                $b.append(obj.username + " : ");
                $pusername.append($b);
                $pmsg.append('&nbsp;&nbsp;&nbsp;&nbsp;' + encodeHTML(obj.content));

                $("#msgContent").append($pusername);
                $("#msgContent").append($pmsg);

            });
            document.getElementById('msgContent').scrollTop = document.getElementById('msgContent').scrollHeight;
        }
    });
}

function sendMsg() {
    document.getElementById("msg").focus();
    var content = $("#msg").val();
    if (content.trim() == "") {
        return;
    }
    if (content.length > 200) {
        content = content.substr(0, 200);
        return;
    }
    $(".input-group").removeClass("has-warning");
    var username = $("#username").html();
    if (username == '点此改名') {
        username = '匿名用户';
    }
    $.ajax({
        url: "msg",
        type: "post",
        data: {"code": 3, "username": username, "content": content},
        dataType: "json",
        error: function (data, textStatus) {
            alert('QAQ消息发不粗去啦,主人是不是连不上网惹?');
        },
        success: function (data, textStatus) {
            $("#msg").val("");
        }
    });
    clearInterval(self.interval);
    getMsg();
    self.interval = setInterval("getMsg()", 3000);
}

function getOnline() {
    $.ajax({
        url: "online/count",
        type: "get",
        dataType: "json",
        error: function (data, textStatus) {
            $("#online").html('<span style="color: red">?</span>');
        },
        success: function (data, textStatus) {
            $("#online").html(data.onlineCount);
        }
    });
}

function encodeHTML(htmlstr) {
    var s = "";
    if (!htmlstr) {
        return "";
    }
    s = htmlstr.replace(/&/g, "&amp;");
    s = s.replace(/</g, "&lt;");
    s = s.replace(/>/g, "&gt;");
    s = s.replace(/ /g, "&nbsp;");
    s = s.replace(/\'/g, '&#39;');
    s = s.replace(/\"/g, '&quot;');
    return s;
}

$(document).ready(function () {
    window.interval = setInterval("getMsg()", 3000);
    window.online = setInterval("getOnline()", 5000);

    getMsg();
    $("#close").click(function () {
        $("#close").blur();
        if (confirm("关闭窗口吗?")) {
            window.opener = null;
            window.close();
        }
    });
    $("#btnsend").click(function () {
        sendMsg();
        getMsg();
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
        var keycode = e.keyCode || e.which;
        if (keycode == 13) {
            sendMsg();
        }
    });
    $("#username").click(function () {
        var name = prompt("请输入您的昵称以供聊天", "您的名字");
        name = name == '您的名字' ? '匿名用户' : name;
        $("#username").html(name != null ? name : '匿名用户');
    });

})

