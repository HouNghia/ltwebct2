<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<meta charset="UTF-8">
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<c:choose>
	<c:when test="${sessionScope.account == null}">
		<div class="col-sm-6">
			<ul class="list-inline right-topbar pull-right">
				<li><a href="${pageContext.request.contextPath }/login">Đăngnhập</a> 
				 <a href="${pageContext.request.contextPath }/register">Đăngký</a></li>
				<li><i class="search fa fa-search search-button"></i></li>
			</ul>
		</div>
	</c:when>
	<c:otherwise>
		<div class="col-sm-6">
			<ul class="list-inline right-topbar pull-right">
				<li><a
					href="${pageContext.request.contextPath}/member/myaccount">${sessionScope.account.fullName}</a>
					| <a href="${pageContext.request.contextPath }/logout">Đăng Xuất</a></li>
				<li><i class="search fa fa-search search-button"></i></li>
			</ul>
		</div>
	</c:otherwise>
</c:choose>