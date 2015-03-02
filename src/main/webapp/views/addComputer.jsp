<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="/views/include/header.jsp" />
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">

				<h1>Add Computer</h1>
				<form:form modelAttribute="computerDTO" action="add-computer"
					method="POST">
					<fieldset>
						<div class="form-group">
							<label for="computerName">Computer name</label>
							<form:input type="text" class="form-control" id="name"
								name="name" placeholder="Computer name" path="name"></form:input>
							<form:errors path="name" cssClass="error"></form:errors>

						</div>
						<div class="form-group">
							<label for="introduced">Introduced date</label> <form:input
								type="datetime-local" class="form-control" id="introduced"
								name="introduced" placeholder="Introduced date" path="introduced"></form:input>
							<form:errors path="introduced" cssClass="error"></form:errors>

						</div>
						<div class="form-group">
							<label for="discontinued">Discontinued date</label> <form:input
								type="datetime-local" class="form-control" id="discontinued"
								name="discontinued" placeholder="Discontinued date" path="discontinued"></form:input>
							<form:errors path="discontinued" cssClass="error"></form:errors>

						</div>
						<div class="form-group">
							<label for="companyId">Company</label> <select
								class="form-control" id="companyId" name="companyId">
								<c:forEach items="${companies}" var="company">
									<c:choose>
										<c:when test="${computer.company.id == 0}">
											<option value="${company.id}" selected><c:out
													value="${company.name}" /></option>
										</c:when>
										<c:when test="${company.id == computer.company.id}">
											<option value="${company.id}" selected><c:out
													value="${company.name}" /></option>
										</c:when>
										<c:otherwise>
											<option value="${company.id}"><c:out
													value="${company.name}" /></option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${computer.company.id == 0}">
										<option value="${company.id}" selected>No company</option>
									</c:when>
									<c:otherwise>
										<option value="${company.id}">No company</option>
									</c:otherwise>
								</c:choose>

							</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="Add"
							class="btn btn-primary validation"> or <a
							href="<c:url value="dashboard"/>" class="btn btn-default">Cancel</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</section>

<script src="<c:url value="/js/jquery.min.js"/>"></script>
<script src="<c:url value="/js/verify.js"/>"></script>
</body>
</html>