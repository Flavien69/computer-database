<%@ tag language="java" pageEncoding="UTF-8" description="Link template"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="target" required="false"%>
<%@ attribute name="index" required="false"%>
<%@ attribute name="search" required="false"%>
<%@ attribute name="nbEntityByPage" required="false"%>
<%@ attribute name="sortCriteria" required="false"%>

<%@ attribute name="page" required="false"
	type="com.flavien.models.Page"%>

<c:if test="${empty search}">
	<c:set var="search" value="${page.search}" />
</c:if>
<c:if test="${empty index}">
	<c:set var="index" value="${page.index}" />
</c:if>
<c:if test="${empty nbEntityByPage}">
	<c:set var="nbEntityByPage" value="${page.nbEntityByPage}" />
</c:if>
<c:set var="sortOrder" value="${page.sortOrder}" />

<c:choose>
	<c:when test="${empty sortCriteria}">
		<c:set var="sortCriteria" value="${page.sortCriteria}" />
	</c:when>
	<c:otherwise>
		<c:if test="${sortCriteria == page.sortCriteria}">
			<c:choose>
				<c:when test="${page.sortOrder == 'ASC'}">
					<c:set var="sortOrder" value="DESC" />
				</c:when>
				<c:otherwise>
					<c:set var="sortOrder" value="ASC" />
				</c:otherwise>
			</c:choose>
		</c:if>
	</c:otherwise>
</c:choose>

<c:url
	value="${target}?nbEntityByPage=${nbEntityByPage}&index=${index}&search=${search}&sortCriteria=${sortCriteria}&sortOrder=${sortOrder}" />