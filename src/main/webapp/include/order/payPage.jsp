<%--
  Created by IntelliJ IDEA.
  User: xen
  Date: 2017/12/9
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<main class="pay-page">
    <c:if test="${orderTable.orderPrice != 0}">
        <div class="pay-tip">扫一扫付款</div>
        <div class="pay-num">￥${orderTable.orderPrice}</div>
        <img src="img/alipay.png" class="alipay-img">
        <a href="payed?orderTable.id=${orderTable.id}"><button class="confirm-pay">免费支付</button></a>
    </c:if>
    <c:if test="${orderTable.orderPrice == 0}">
        <a href="index"><button class="confirm-pay">操作失败!点击重新操作...</button></a>
    </c:if>
</main>
