<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<main class="search">
    <div class="items">
        <c:forEach items="${products}" var="p" varStatus="vs">
            <a href="product?product.id=${p.id}">
                <div class="border" price="${p.NPrice}">
                    <div class="item">
                        <div class="content">
<%--                            <img class="item-img" src="${productImgDir}${p.image.path}">--%>
                                <img style="height: 180px;width: 180px" src="${p.coverList[0].urlImage}">
                            <div class="item-price">
                                ￥${p.NPrice}
                            </div>
                            <div class="item-title">
                                    ${p.name}
                            </div>

                            <div class="item-shop">
                            </div>
                            <div class="item-bottom">
                                <span>
                                    评价<a href="product?product.id=${p.id}">${p.commentCount}条</a>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </a>

        </c:forEach>
        <c:if test="${empty products}">
            <div class="no-match">没有满足条件的产品</div>
        </c:if>
    </div>
</main>
