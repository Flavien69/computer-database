<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="/views/include/header.jsp" />


<section id="main">
	<div class="container">
		<div class="alert alert-danger">
			<spring:message code="error.403.message"/> <br />
			<!-- stacktrace -->
		</div>
	</div>
</section>

<jsp:include page="/views/include/footer.jsp" />
