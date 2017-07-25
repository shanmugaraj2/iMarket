
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ page isELIgnored="false"%>   
<!DOCTYPE html>
<html lang="en">
<head>
<title>iMarket</title>

 <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/css/custom.styles.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/css/dataTables.bootstrap.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/css/dataTables.bootstrap.min.css" />" rel="stylesheet">
 

</head>
<body>

	<div class="container custom-container">
		<jsp:include page="header.jsp" />

		<div class="tab-content custom-tab-content">
			<div id="dashboard" class="tab-pane fade in active">
				<table  class="table table-striped table-bordered" id="companyList">
				    <thead>
				      <tr>
				        <th>Company Name</th>
				         <th>Company Email</th>
				        <th>Contact Person1</th>
				        <th>Industry</th>
				        <th>Country</th>
				        <th>Edit</th>
				      </tr>
				    </thead>
				    <tbody>
				      <c:forEach var="prospects" items="${prospectsVOLst}">   
					   <tr>  
						   <td>${prospects.companyName}</td> 
						   <td>${prospects.companyEmail}</td> 
						   <td>${prospects.contactPerson1}</td> 
						   <td>${prospects.industry}</td> 
						   <td>${prospects.country}</td> 
						   <td>
						   		<a class = "edit-icon" href="<c:url value='/webform?id=${prospects.id}' />"><span class="glyphicon glyphicon-pencil"> </span> </a>
						   		<a  class = "edit-icon" href="<c:url value='/remove?id=${prospects.id}' />"><span class="glyphicon glyphicon-trash"> </span> </a>
						   </td>
					   </tr>  
					   </c:forEach>  
				    </tbody>
				</table>
			</div>
			
		</div>
	</div>
	<div class="modal fade" id="myModal" role="dialog">
    	<div class="modal-dialog">
    
      <!-- Modal content-->
      	<div class="modal-content">
        	<div class="modal-header">
         		 <button type="button" class="close" data-dismiss="modal">&times;</button>
          		 <h4 class="modal-title">Information</h4>
       		</div>
        	<div class="modal-body">
          		 <p>${message}</p>
        	</div>
        	<div class="modal-footer">
          		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	</div>
      </div>
      
    </div>
  </div>
	 <script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"></script>
	 <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	 <script src="<c:url value="/resources/js/notify.min.js" />"></script>
	 <script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
	 <script src="<c:url value="/resources/js/dataTables.bootstrap.js" />"></script>
	 <script src="<c:url value="/resources/js/custom.js" />"></script>
	 <script type="text/javascript" >
       $('#dashboard').addClass('active') 
        $('#webform').removeClass('active')
        if('${message}' != ''){
        	$('#myModal').modal('show')
        }
 	 </script>
	

</body>
</html>