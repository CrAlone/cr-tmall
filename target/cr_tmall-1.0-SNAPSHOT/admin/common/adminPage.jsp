
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>


<div class="container text-center" >
    <nav aria-label="..." >
        <ul class="pagination">
            <c:choose>
                <c:when test="${pagination.num ne 0}">
                    <li class="page-item ${pagination.hasPrevious ? '':'disabled'}">
                        <a class="page-link" href="?pagination.pageNum=${pagination.pageNum-1 }&category.id=${category.id}">«</a>
                    </li>
                    <c:forEach begin="${pagination.start}" end="${pagination.end}" varStatus="vs">
                        <%--       vs.index当前页         --%>
                        <li class="page-item ${vs.index == pagination.pageNum ? 'active':''}">
                            <a class="page-link" href="?pagination.pageNum=${vs.index}&category.id=${category.id}">
                                    ${vs.current}
                            </a>
                        </li>
                    </c:forEach>
                    <li class="page-item" ${pagination.hasNext ? '':'disabled'}>
                        <a class="page-link" href="?pagination.pageNum=${pagination.pageNum+1}&category.id=${category.id}">»</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <h3>抱歉，目前没有数据展示...</h3>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</div>
