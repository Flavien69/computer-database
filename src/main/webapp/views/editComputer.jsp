<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="css/font-awesome.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="css/main.css"/>" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.jsp"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${computer.id}
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="editComputer" method="POST">
                        <input type="hidden" value="0"/>
                        <fieldset>
                        	<div class="form-group" >
                                <input type="hidden" class="form-control" id="id" name="id" value="${computer.id}">
                            </div>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="Computer name" value="${computer.name}">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="datetime-local" class="form-control" id="introduced" name="introduced" placeholder="Introduced date" value="${computer.introduced}">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="datetime-local" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date" value="${computer.discontinued}">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId" >
                                    <c:forEach items="${companies}" var="company">
	                                    <c:choose>
	                                    	<c:when test="${computer.company.id == 0}">
                                    			<option value="${company.id}" selected>${company.name}</option> 
										    </c:when>
										    <c:when test="${company.id == computer.company.id}">
                                    			<option value="${company.id}" selected>${company.name}</option> 
										    </c:when>
										    <c:otherwise>
                                    			<option value="${company.id}">${company.name}</option> 
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
                            <input type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="<c:url value="dashboard"/>" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>