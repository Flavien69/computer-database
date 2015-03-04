<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="include/header.jsp" />
<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">

				<h1>
					<spring:message code="computer_form.add_computer_title" />
				</h1>
				<form:form modelAttribute="computerDTO" action="add-computer"
					method="POST">
					<fieldset>
						<div class="form-group">
							<spring:message code="computer_form.name_placeholder"
								var="name_placeholder" />
							<label for="computerName"><spring:message
									code="computer_form.name_label" /></label>
							<form:input type="text" class="form-control" id="name"
								name="name" placeholder="${name_placeholder}" path="name"></form:input>
							<form:errors path="name" cssClass="error"></form:errors>

						</div>
						<div class="form-group">
							<spring:message code="computer_form.introduced_placeholder"
								var="introduced_placeholder" />
							<label for="introduced"><spring:message
									code="computer_form.introduced_label" /></label>
							<form:input class="form-control"
								id="introduced" name="introduced"
								placeholder="${introduced_placeholder}" path="introduced"></form:input>
							<form:errors path="introduced" cssClass="error"></form:errors>

						</div>
						<div class="form-group">
							<spring:message code="computer_form.discontinued_placeholder"
								var="discontinued_placeholder" />
							<label for="discontinued"><spring:message
									code="computer_form.discontinued_label" /></label>
							<form:input class="form-control"
								id="discontinued" name="discontinued"
								placeholder="${discontinued_placeholder}" path="discontinued"></form:input>
							<form:errors path="discontinued" cssClass="error"></form:errors>

						</div>
						<div class="form-group">
							<label for="companyId"><spring:message
									code="computer_form.company_label" /></label> <form:select
								class="form-control" id="company.id" name="company.id" path="company.id">
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
										<option value="0" selected>No company</option>
									</c:when>
									<c:otherwise>
										<option value="0">No company</option>
									</c:otherwise>
								</c:choose>

							</form:select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<spring:message code="computer_form.add_button" var="add" />
						<input type="submit" value="${add}"
							class="btn btn-primary validation">
						<spring:message code="computer_form.button_separator" />
						<a href="<c:url value="dashboard"/>" class="btn btn-default"><spring:message
								code="computer_form.cancel_button" /></a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
	var strings = new Array();
	strings['date_regex'] = "<spring:message code='date.regex' javaScriptEscape='true' />";
</script>

<script src="<c:url value="/js/jquery.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/verify.js"/>"></script>
</body>
</html>