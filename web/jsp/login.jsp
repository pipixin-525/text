<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<style>
    body {
        display: -ms-flexbox;
        display: flex;
        -ms-flex-align: center;
        align-items: center;
        padding-top: 40px;
        padding-bottom: 40px;
    }

    html,
    body {
        height: 100%;
    }
</style>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../assets/vendor/bootstrap/css/bootstrap.min.css">
    <link href="../assets/vendor/fonts/circular-std/style.css" rel="stylesheet">
    <link rel="stylesheet" href="../assets/libs/css/style.css">
    <link rel="stylesheet" href="../assets/vendor/fonts/fontawesome/css/fontawesome-all.css">
    <!-- Optional JavaScript -->
    <script src="../assets/vendor/jquery/jquery-3.3.1.min.js"></script>

    <script>
        $(function () {
            $("#submit").submit(function () {
                alert(1);
                var userName = $("#username").val();
                var password = $("#password").val();
                //进行表单验证
                var url = "login";
                var param = {
                    userName: userName,
                    password: password
                };
                alert(url);
                $post(url, param, function (data) {
                    alert(data);
                    alert(1);
                    if (data) {
                        window.location.href = "success.jsp"
                    } else {
                        alert("登录失败，请输入正确的用户名和密码");
                    }
                });
                return false;
            });
        });
    </script>
</head>

<body>
<!-- ============================================================== -->
<!-- login page  -->
<!-- ============================================================== -->
<div class="splash-container">
    <div class="card ">
        <div class="card-header text-center"><img class="logo-img" src="../assets/images/logo.png" alt="logo"><span
                class="splash-description">请 输 入 账 户 和 密 码 登 录</span></div>
        <div class="card-body">
            <form id="loginForm">
                <div class="form-group">
                    <input class="form-control form-control-lg" id="username" type="text" placeholder="用户名"
                           autocomplete="off">
                </div>
                <div class="form-group">
                    <input class="form-control form-control-lg" id="password" type="password" placeholder="密码">
                </div>
                <div class="form-group">
                    <label class="custom-control custom-checkbox">
                        <input class="custom-control-input" type="checkbox"><span class="custom-control-label">Remember Me</span>
                    </label>
                </div>
                <button type="submit" class="btn btn-primary btn-lg btn-block" id="submit">Sign in</button>
            </form>
        </div>
    </div>
</div>

<!-- ============================================================== -->
<!-- end login page  -->
<!-- ============================================================== -->
</body>

</html>
