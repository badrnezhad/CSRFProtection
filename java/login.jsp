<% String csrfToken = (String) request.getAttribute("csrfToken"); %>
<div class="row section-row">
    <div class="col-xs-12 section-col">
        <div class="parallax-top">
            <div class="loginmodal-container">
                <h3>Login</h3><br>
                <div>
                    <i class="fa fa-user login-avatar"></i>
                </div>
                <br/>
                <form class="login-box" action="/spring/login.do" method="post" autocomplete="off">
                    <div class="input-group input-group-lg margin-center">
                        <input type="text" name="email" id="email" placeholder="Username" autocomplete="off"
                               class="form-control input-group-addon">
                    </div>
                    <br/>
                    <div class="input-group input-group-lg margin-center">
                        <input type="password" name="password" placeholder="Password" autocomplete="off"
                               class="form-control input-group-addon" id="password">
                    </div>
                    <br/>
                    <div class="input-group input-group-lg margin-center display-inline margin-15px">
                        <input type="submit" name="login"
                               class="btn btn-primary form-control min-with-100" value="Login">
                    </div>
                    <div class="input-group input-group-lg margin-center display-inline margin-15px">
                        <a href="/spring/" class="btn btn-danger form-control min-with-100">Cancle</a>
                    </div>
                    <input type="hidden" name="formCSRFToken" id="formCSRFToken" value="<%=csrfToken%>">

                </form>
                <br/>

            </div>
        </div>
    </div>
</div>
