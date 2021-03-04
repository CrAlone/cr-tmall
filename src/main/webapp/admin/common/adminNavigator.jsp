
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default clearfix">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">商城后台</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="${light==1?'active':''}"><a  href="${pageContext.request.contextPath}/admin/category/backstage">分类管理</a></li>
                <li class="${light==2?'active':''}"><a  href="${pageContext.request.contextPath}/admin/user/list">用户管理</a></li>
                <li class="${light==3?'active':''}"><a href="${pageContext.request.contextPath}/admin/table/list">订单管理</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class=""><a href="${pageContext.request.contextPath}/index" target="_blank" >网站前台</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>