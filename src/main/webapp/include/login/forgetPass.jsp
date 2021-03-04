
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<main class="register">

    <script>
       $(function () {
          $("#form").submit(function () {
                var value = $("#email").val();
                if (value == ""){
                    alert("请输入您绑定的邮箱");
                    return;
                }

          })
       })

    </script>
    <form action="forget" method="post" id="form">
        <table class="register-table">
            <tbody>
            <tr>
                <td class="left-col">邮箱账号</td>
                <td class="right-col">
                    <input type="text" placeholder="输入您的邮箱账号" name="user.email" id="email">
                    <span style="color: red;">${msg == null ? "" : msg}</span>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="button-td">
                    <input type="hidden" name="refer">
                    <button type="submit">提 交</button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</main>