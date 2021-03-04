<%--
  Created by IntelliJ IDEA.
  User: xen
  Date: 2017/12/9
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script>
    var deleteOid = -1;
    $(function () {
        $(".order-type a").click(function () {
            var orderStatus = $(this).attr("order-status");
            if('all'===orderStatus){
                $("table[order-status]").show();
            }else{
                $("table[order-status]").hide();
                $("table[order-status="+orderStatus+"]").show();
            }
            $(".order-type div").removeClass("selected");
            $(this).parent("div").addClass("selected");

        });
        $(".delete-button").click(function () {
            deleteOid = $(this).attr("oid");
            $("#confirmModal").modal('show');
        });
        $("#confirmDelete").click(function () {
            $("#confirmModal").modal("hide");
            var page = "deleteOrder";
            $.get(page,{"order.id":deleteOid},function (result) {
                if(result === "success"){
                    $("table[oid="+deleteOid+"]").remove();
                }else{
                    alert("服务器错误，删除失败");
                }
            });
        });
        $("#cancelDelete").click(function () {
            $("#confirmModal").modal("hide");
        });
    });
</script>

<section class="order-type">
    <div class="selected"><a href="#nowhere" order-status="all">所有订单</a></div>
    <div><a href="#nowhere" order-status="waitPay">待付款</a></div>
    <div><a href="#nowhere" order-status="waitDelivery">待发货</a></div>
    <div><a href="#nowhere" order-status="waitConfirm">待确认</a></div>
    <div><a href="#nowhere" order-status="waitComment">待评价</a></div>
</section>

<main class="order-list">
    <table class="head-table">
        <thead>
        <th >宝贝</th>
        <th width="100px">单价</th>
        <th width="100px">数量</th>
        <th width="150px">实付款</th>
        <th width="120px">交易操作</th>
        </thead>
    </table>
    <c:forEach items="${orderTables}" var="o" varStatus="vs">
    <table class="line-table" order-status="${o.status}" oid="${o.id}">
        <tr class="item-head">
            <td colspan="2" class="time-and-order">
                <b>${o.createTime}</b>
                <span>订单号: ${o.orderCode}</span>
            </td>
            <td >
                <span class="seller"><img src="img/tmall-small.png">${website_name}</span>
            </td>
            <td colspan="2">
                <span class="wangwang"></span>
            </td>
            <td class="delete">
                <c:if test="${o.status=='PENDING'|| o.status == 'PAID'}">
                <a href="#nowhere" class="delete-button" oid="${o.id}">
                    <span class="glyphicon glyphicon-trash"></span>
                </a>
                </c:if>
            </td>
        </tr>
            <c:forEach items="${o.orderItemList}" var="oi" varStatus="vs2">
        <tr class="item-body">
            <td width="100px">
                <img src="${productImgDir}${oi.product.coverList[0].urlImage}" class="cart-item-jpg">
            </td>
            <td class="item-name">
                <a class="cart-item-title" href="product?product.id=${oi.product.id}">${oi.product.name}</a>
                <div class="cart-item-title-bottom">
                    <img title="保障卡" src="img/baozhang.png">
                </div>
            </td>
            <td width="100px" class="num-center">
                <span class="cart-item-old-price">￥${oi.product.OPrice}</span>
                <span class="cart-item-now-price">￥${oi.product.NPrice}</span>
            </td>
            <td width="100px" class="num-center border-left">
                <span class="order-item-num">${oi.count}</span>
            </td>
            <c:if test="${vs2.count==1}">
                <td class="price-td" width="150px" rowspan="${o.orderItemCount}">
                    <span class="order-item-sum">￥${o.orderPrice}</span>
                    <span class="freight">(含运费：￥0.00)</span>
                </td>
            </c:if>
            <c:if test="${vs2.count==1 && o.status!='ACCOMPLISH' }">
                <td width="120px" rowspan="${o.orderItemCount}">

                    <c:if test="${o.status=='SHIPPED'}">
                        <a href="confirmPay?orderTable.id=${o.id}" class="order-button blue">确认收货</a>
                    </c:if>
                    <c:if test="${o.status=='PAID'}">
                        <a href="pay?orderTable.id=${o.id}" class="order-button blue">付款</a>
                    </c:if>

                    <c:if test="${o.status=='PENDING'}">
                        <a href="#nowhere" class="order-button white">待发货</a>
                    </c:if>
                    <c:if test="${o.status=='ACCOMPLISH' }">
                        <a href="#nowhere" class="order-button white">完成订单</a>
                    </c:if>
                </td>
            </c:if>
            <c:if test="${o.status=='ACCOMPLISH'}">
                <td width="120px" >
                    <a href="comment?orderItem.id=${oi.id}" class="order-button white">评价</a>
                </td>
            </c:if>
        </tr>
        </c:forEach>
    </table>
    </c:forEach>
    <div class="modal" tabindex="-1" role="dialog" id="confirmModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-title">删除宝贝</div>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

                <div class="confirm-content">确认要删除该宝贝吗？</div>
                <div class="button-group">
                    <button class="yes" id="confirmDelete">确定</button>
                    <button class="no" id="cancelDelete">取消</button>
                </div>
            </div>
        </div>
    </div>
</main>
