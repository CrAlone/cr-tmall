<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="js/search.js"></script>

<section class="select-bar">
    <a href="?keyword=${keyword}" class="${empty param.sort?'selected':''}">
        综合
        <span class="glyphicon glyphicon-arrow-down"></span>
    </a>
    <a href="?category.id=${category.id}&keyword=${keyword}&sort=commentCount" class="${param.sort=='commentCount'?'commentCountInverse':'commentCount'}">人气<span
            class="${(param.sort=="commentCount"||param.sort=="commentCountInverse")?'selected':''}"></span></a>
    <a href="?category.id=${category.id}&keyword=${keyword}&sort=createTime" class="${param.sort=='createTime'?'createTimeInverse':'createTime'}">新品<span
            class="${(param.sort=="createTime"||param.sort=="createTimeInverse")?'selected':''}"></span></a>
    <a href="?category.id=${category.id}&keyword=${keyword}&sort=saleCount" class="${param.sort=='saleCount'?'saleCountInverse':'saleCount'}">销量<span
            class="${(param.sort=="saleCount"||param.sort=="saleCountInverse")?'selected':''}"></span></a>
    <a href="?category.id=${category.id}&keyword=${keyword}&sort=${param.sort=='nPrice'?'nPriceInverse':'nPrice'}"
       class="${(param.sort=="nPrice"||param.sort=="nPriceInverse")?'selected':''}">价格<span class="glyphicon glyphicon-resize-vertical"></span></a>
    <span class="price">
        <input type="text" placeholder="￥请输入" class="sortBarPrice beginPrice" id="low_price">
        <span>-</span>
        <input type="text" placeholder="￥请输入" class="sortBarPrice beginPrice" id="high_price">
    </span>
</section>

<%@include file="commonPage.jsp"%>