<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<main class="confirm-pay">
    <div class="buy-flow-img">
        <img src="img/buyflow_3.png">
    </div>
    <div class="buy-flow">
        <div class="buy-flow-time time-1">${orderTable.createTime}</div>
        <div class="buy-flow-time time-2">${orderTable.payTime}</div>
        <div class="buy-flow-time time-3">${orderTable.deliveryTime}</div>
    </div>
    <div class="agree-pay-text">我已收到货，同意支付宝付款</div>
    <div class="bill-detail">
        <div class="bill-detail-title">订单信息</div>
        <table class="bill-detail-table">
            <thead>
            <tr>
                <th colspan="2">宝贝</th>
                <th>单价</th>
                <th>数量</th>
                <th>商品总价</th>
                <th>运费</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderTable.orderItemList}" var="oi" varStatus="vs">
            <tr>
                <td><img src="${productImgDir}${oi.product.coverList[0].urlImage}"></td>
                <td class="item-title"><a href="#nowhere">${oi.product.name}</a></td>
                <td>￥${oi.product.NPrice}</td>
                <td>${oi.count}</td>
                <td class="item-sum-price">￥${oi.totalPrice}</td>
                <td>快递 0.00</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="bill-detail-sum">实付款： <span class="red-color">￥${orderTable.orderPrice}</span></div>
        <table class="pay-detail-table clear">
            <tbody>
            <tr>
                <td width="200px">订单编号：</td>
                <td>${orderTable.orderCode} <img height="14px" src="img/tmall-small.png"></td>
            </tr>
            <tr>
                <td width="200px">卖家昵称：</td>
                <td>${website_name} <span class="wangwang"></span></td>
            </tr>
            <tr>
                <td width="200px">收货信息： </td>
                <td>${orderTable.address}，${orderTable.receivers}， ${orderTable.mobile}，${orderTable.postcode}</td>
            </tr>
            <tr>
                <td width="200px">成交时间：</td>
                <td>${orderTable.createTime}</td>
            </tr>
            </tbody>
        </table>
        <div class="confirm-end">
            <div class="confirm-warning">请收到货后，再确认收货！否则您可能钱货两空！</div>
            <a href="confirmed?order.id=${orderTable.id}"><button class="confirm-button">确认支付</button></a>
        </div>
    </div>




</main>