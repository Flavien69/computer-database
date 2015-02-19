<%@ tag language="java" pageEncoding="UTF-8" description="Page template"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags"%>

<%@ attribute name="page" required="true" type="com.flavien.models.Page"%>
<%@ attribute name="search" required="true"%>

<ul class="pagination">
	<li <c:if test="${page.index == 0}">style="display:none"</c:if>><a
		href="<mylib:link target="dashboard" nbEntityByPage="${page.entityByPage}" index="${page.index -1}" search="${search}"/>"
		aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
	</a></li>
	<c:forEach var="i" begin="${page.range[0]}" end="${page.range[1]}">
		<c:choose>
			<c:when test="${page.index == i}">
				<li class="active"><a
					href="<mylib:link target="dashboard" nbEntityByPage="${page.entityByPage}" index="${i}" search="${search}"/>">${i+1}</a></li>
			</c:when>
			<c:otherwise>
				<li><a
					href="<mylib:link target="dashboard" nbEntityByPage="${page.entityByPage}" index="${i}" search="${search}"/>">${i+1}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<li
		<c:if test="${page.index == page.nbTotalPage}"> style="display:none"</c:if>><a
		href="<mylib:link target="dashboard" nbEntityByPage="${page.entityByPage}" index="${page.index +1}" search="${search}"/>"
		aria-label="Next"> <span aria-hidden="true">&raquo;</span>
	</a></li>
</ul>

<div class="btn-group btn-group-sm pull-right" role="group">
	<c:choose>
		<c:when test="${page.entityByPage == 10}">
			<a class="btn btn-primary"
				href="<c:url value="dashboard?nbEntityByPage=10&index=0&search=${search}"/>">10</a>
		</c:when>
		<c:otherwise>
			<a class="btn btn-default"
				href="<c:url value="dashboard?nbEntityByPage=10&index=0&search=${search}"/>">10</a>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${page.entityByPage == 50}">
			<a class="btn btn-primary"
				href="<mylib:link target="dashboard" nbEntityByPage="50" index="0" search="${search}"/>">50</a>
		</c:when>
		<c:otherwise>
			<a class="btn btn-default"
				href="<mylib:link target="dashboard" nbEntityByPage="50" index="0" search="${search}"/>">50</a>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${page.entityByPage == 100}">
			<a class="btn btn-primary"
				href="<mylib:link target="dashboard" nbEntityByPage="100" index="0" search="${search}"/>">100</a>
		</c:when>
		<c:otherwise>
			<a class="btn btn-default"
				href="<mylib:link target="dashboard" nbEntityByPage="100" index="0" search="${search}"/>">100</a>
		</c:otherwise>
	</c:choose>
</div>