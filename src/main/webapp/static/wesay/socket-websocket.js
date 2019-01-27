var Chat = {};


Chat.socket = null;

Chat.connect = (function(host) {
    if ('WebSocket' in window) {
        Chat.socket = new WebSocket(host);
    } else if ('MozWebSocket' in window) {
        Chat.socket = new MozWebSocket(host);
    } else {
        Console.log('Error: 您的浏览器不支持WebSocket，请确认开启JavaScript运行权限，或更新浏览器.');
        window.open('polling.jsp')
        return;
    }

    Chat.socket.onopen = function () {
        Console.log('Info: 正在连接服务器...');
        document.getElementById('msg').onkeydown = function(event) {
            if (event.keyCode == 13) {
                Chat.sendMessage();
            }
        };
        document.getElementById('chat').onclick = function(event) {
            Chat.sendMessage();
        };
    };

    Chat.socket.onclose = function () {
        document.getElementById('chat').onkeydown = null;
        Console.log('Info: 与服务器断开连接');
    };

    Chat.socket.onmessage = function (message) {
        Console.log(message.data);
    };
});

Chat.initialize = function(locate) {
    if (window.location.protocol == 'http:') {
        Chat.connect('ws://' + window.location.host + window.location.pathname + locate);
    } else {
        Chat.connect('wss://' + window.location.host + window.location.pathname  + locate);
    }
};

Chat.sendMessage = (function() {
    var message = document.getElementById('msg').value;
    if (message != '') {
        Chat.socket.send(message);
        document.getElementById('msg').value = '';
    }
});

var Console = {};

Console.log = (function(message) {
    var console = document.getElementById('msgContent');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.innerText = message;
    console.appendChild(p);
    // 删除多余的历史消息
    while (console.childNodes.length > 25) {
        console.removeChild(console.firstChild);
    }
    console.scrollTop = console.scrollHeight;
});

Chat.initialize('wesay');

document.addEventListener("DOMContentLoaded", function() {
    // Remove elements with "noscript" class - <noscript> is not allowed in XHTML
    var noscripts = document.getElementsByClassName("noscript");
    for (var i = 0; i < noscripts.length; i++) {
        noscripts[i].parentNode.removeChild(noscripts[i]);
    }
    // 收取在线人数
    window.online = setInterval("getOnline()", 5000);
}, false);

function encodeHTML(htmlstr) {
    if (!htmlstr) {
        return "";
    }
    var s = "";
    s = htmlstr.replace(/&/g, "&amp;");
    s = s.replace(/</g, "&lt;");
    s = s.replace(/>/g, "&gt;");
    s = s.replace(/ /g, "&nbsp;");
    s = s.replace(/\'/g, '&#39;');
    s = s.replace(/\"/g, '&quot;');
    return s;
}
function getOnline() {
    $.ajax({
        url: "online/count/socket",
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