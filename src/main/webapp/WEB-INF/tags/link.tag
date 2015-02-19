<%@ tag language="java" pageEncoding="UTF-8" description="Link template"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="target" required="false"%>
<%@ attribute name="index" required="false"%>
<%@ attribute name="search" required="false"%>
<%@ attribute name="nbEntityByPage" required="false"%>

<c:url value="${target}?nbEntityByPage=${nbEntityByPage}&index=${index}&search=${search}"/>