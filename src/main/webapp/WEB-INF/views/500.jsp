<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="include/header.jsp" />

<section id="main">
	<div class="container">
		<div class="alert alert-danger">
			<spring:message code="error.500.message"/> <br />
			<!-- stacktrace -->
		</div>
	</div>
</section>

<jsp:include page="include/footer.jsp" />
