<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="include/header.jsp" />
<section id="main">
	<div class="container">
		<h1 id="homeTitle">
			<spring:message code="dashboard.n_computers_msg"
				arguments="${page.nbTotalComputer}" />
		</h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm" action="#" method="GET" class="form-inline">
					<spring:message code="dashboard.search_placeholder"
						var="search_filter" />
					<spring:message code="dashboard.search_filter_button"
						var="search_filter_btn" />
					<input type="search" id="searchbox" name="search"
						class="form-control" placeholder="${search_filter}"
						<c:if test="${search != null}">value="${page.search}"</c:if> /> <input
						type="submit" id="searchsubmit" value="${search_filter_btn}"
						class="btn btn-primary" />
				</form>
			</div>
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer" href="add-computer"><spring:message
						code="computer_form.add_computer_title" /></a> <a
					class="btn btn-default" id="editComputer" href="#"
					onclick="$.fn.toggleEditMode();"><spring:message
						code="dashboard.edit_button" /></a> <a style="display: none"
					class="btn btn-default" id="editComputerBis" href="#"
					onclick="$.fn.toggleEditMode();"><spring:message
						code="dashboard.view_button" /></a>
			</div>
		</div>
	</div>

	<form id="deleteForm" action="delete-computer" method="POST">
		<input type="hidden" name="selection" value="">
	</form>


	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->

					<th class="editMode" style="width: 60px; height: 22px;"><input
						type="checkbox" id="selectall" /> <span
						style="vertical-align: top;"> - <a href="#"
							id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
								class="fa fa-trash-o fa-lg"></i>
						</a>
					</span></th>
					<th><a href="<mylib:link target="dashboard" sortCriteria="NAME"/>"><spring:message code="dashboard.name_label" /></a></th>
					<th><a href="<mylib:link target="dashboard" sortCriteria="INTRODUCED"/>"><spring:message code="dashboard.introduced_label" /></a></th>
					<!-- Table header for Discontinued Date -->
					<th><a href="<mylib:link target="dashboard" sortCriteria="DISCONTINUED"/>"><spring:message code="dashboard.discontinued_label" /></a></th>
					<!-- Table header for Company -->
					<th><a href="<mylib:link target="dashboard" sortCriteria="COMPANY_NAME"/>"><spring:message code="dashboard.company_label" /></a></th>

				</tr>
			</thead>
			<!-- Browse attribute computers -->
			<tbody id="results">
				<c:forEach items="${page.computerList}" var="computer">

					<tr>
						<td class="editMode"><input type="checkbox" name="cb"
							class="cb" value="${computer.id}"></td>
						<td><a href='edit-computer?id=${computer.id}' onclick=""><c:out
									value="${computer.name}" /></a></td>
						<td><c:out value="${computer.introduced}" /></td>
						<td><c:out value="${computer.discontinued}" /></td>
						<td><c:out value="${computer.company.name}" /></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</section>

<footer class="navbar-fixed-bottom">
	<div class="container text-center">
		<mylib:pagination page="${page}" />
	</div>
</footer>

<script type="text/javascript">
	var strings = new Array();
	strings['delete_confirm_msg'] = "<spring:message code='dashboard.delete_confirm_msg' javaScriptEscape='true' />";
	strings['view_button'] = "<spring:message code='dashboard.view_button' javaScriptEscape='true' />";
	strings['edit_button'] = "<spring:message code='dashboard.edit_button' javaScriptEscape='true' />";
</script>

<jsp:include page="include/footer.jsp" />
