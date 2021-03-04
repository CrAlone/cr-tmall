
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<main class="register">

    <script>
        var msg = "${msg}";
        if (msg !== "") {
            alert(msg);
        }
        $(function () {
            $("#refer").val(document.referrer);
            $("#form").submit(function () {
                if ($("#repeatpassword").val() != $("#password").val()) {
                    alert("两次输入的密码不一致");
                    return false;
                }
                if ($("#password").val() == "" || $("#name").val() == "") {
                    alert("用户名或密码为空");
                    return false;
                }
                return true;
            });
        });
        function send(){
            var email = $("#email").val();
            if (email == ""){
                alert("邮箱不能为空!");
                return;
            }
            $.ajax({
                url:"sendMail",
                data:{"user.email":email},
                type:"post",
                dataType:"json",
                success:function(data){
                    var message = JSON.parse(data);
                    if (message.code = 400){
                        alert(message.msg);
                        return;
                    }else if (data.code = 200){
                        alert(message.msg);
                        return;
                    }
                },
                error:function () {
                    alert("网络异常,请联系管理员 error! 009");
                }
            });
        }
    </script>


    <form action="regAdd" method="post" id="form">
        <table class="register-table">
            <tbody>
            <tr>
                <td class="left-col">邮箱账号</td>
                <td class="right-col">
                    <input type="text" placeholder="输入您的邮箱账号" name="user.email" id="email"></td>
                <td colspan="2" class="button-td">
                    <a href="javascript:send()" class="btn btn-primary btn-xs" style="background: #FF0036;color: #FFFFFF">
                        点击发送
                    </a>
                </td>
                <td class="right-col">
                    <input type="text" placeholder="请输入验证码" name="user.verify" id="verify">
                </td>
            </tr>

            <tr>
                <td colspan="2" class="register-tip">设置会员名</td>
            </tr>

            <tr>
                <td class="left-col">登录名</td>
                <td class="right-col"><input placeholder="会员名一旦设置成功，无法修改" name="user.name" id="name"></td>
            </tr>

            <tr>
                <td colspan="2" class="register-tip">邮箱</td>
            </tr>
            <tr>
                <td colspan="2" class="register-tip">设置密码</td>
            </tr>
            <tr>
                <td class="left-col">登陆密码</td>
                <td class="right-col"><input type="password" placeholder="设置你的登陆密码" name="user.password" id="password">
                </td>
            </tr>
            <tr>
                <td class="left-col">密码确认</td>
                <td class="right-col"><input type="password" placeholder="请再次输入你的密码" id="repeatpassword"></td>
            </tr>
            <tr>

                <td colspan="2" class="button-td">
                    <input type="hidden" name="refer" id="refer">
                    <button type="submit">提 交</button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</main>