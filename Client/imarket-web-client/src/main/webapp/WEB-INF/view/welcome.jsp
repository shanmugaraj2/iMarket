
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
		<ul class="nav nav-pills">
			<li class="active"><a data-toggle="pill" href="#dashboard">Dashboard</a></li>
			<li><a data-toggle="pill" href="#web-form">Web Form</a></li>
		</ul>

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
						   <td><span id = '${prospects.id}'  class="glyphicon glyphicon-pencil"></span></td>
					   </tr>  
					   </c:forEach>  
				    </tbody>
				</table>
			</div>
			<div id="web-form" class="tab-pane fade">
				<div class="panel panel-default file-upload-area">
					<div class="panel-heading">Master Upload</div>
					<div class="panel-body">
						<div class="col-md-12 upload-btn-group">
							<div class="col-md-6">
								<form method="POST" action="fileUpload" enctype="multipart/form-data">
									<div class="input-group custom-input-group">
										<label class="input-group-btn"> <span
											class="btn btn-primary" id="span_id"> Browse...<input
												type="file" id="file" name="file" style="display: none;">
										</span>
										</label> <input type="text" name="name" class="form-control" id="file_text"
											readonly>
									</div>
									<button type="submit" id="save-btn"
										class="btn btn-default btn-sm">Upload</button>
									<button type="button" id="save-btn"
										class="btn btn-default btn-sm"
										onclick="reset($('#file'),$('#file_text'))">Clear</button>
								</form>
							</div>
							<div class="col-md-6">
								<a href="#" class="btn btn-default btn-sm"> Download
									Template<span class="glyphicon glyphicon-download-alt"></span>
								</a>
							</div>
						</div>
					</div>
				</div>
				<form:form  commandName="prospectsVO" action="savePropects" method ="POST">
				<div class="panel panel-default custom-panel">
					<div class="panel-heading">Company Information</div>
					<div class="panel-body">
						<div class="form-inline custom-form-inline">
					
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="company_name" class="txt-label">Company Name *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="companyName" id = "companyName" onBlur="onBlurCompanyName()" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="industry" class="txt-label">Industry *</label>
									</div>
									<div class="col-md-6">
									<form:select  class="form-control" path="industry" id="industry" >
										 	<form:option value="NONE" label="-select-"/>
										 	<form:options items="${industryMap}" />
									
   										</form:select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="employeeCount" class="txt-label">Employee Count *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="employeeCount" id="employeeCount" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="website" class="txt-label">Website *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="website" id="website" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="country" class="txt-label">Country *</label>
									</div>
									<div class="col-md-6">
										<form:select  class="form-control" path="country" id="country" >
										 	<form:option value="NONE" label="--- Select ---"/>
										 	<form:options items="${countryMap}" />
										 
   											
   										</form:select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="companyEmail" class="">Company Email *</label>
									</div>
									<div class="col-md-6">
									
										<form:input class="form-control" path="companyEmail" id="companyEmail" onBlur="emailValidation(this)"/>
									</div>
								</div>
							</div>
								<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="revenue" class="txt-label">Revenue *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="revenue" id="revenue"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="lead_source" class="">Lead Source *</label>
									</div>
									<div class="col-md-6">
									
										<form:input class="form-control" path="leadSource" id="leadSource" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-default custom-panel">
					<div class="panel-heading">Contact Information 1</div>
					<div class="panel-body">
						<div class="form-inline custom-form-inline">
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactPerson1" class="txt-label">Contact Person *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactPerson1" id="contactPerson1" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactJobTitle1" class="txt-label">Contact Job Title *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactJobTitle1" id="contactJobTitle1"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactPhone1" class="txt-label">Contact Phone *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactPhone1" id="contactPhone1" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactEmail1" class="txt-label">Email *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactEmail1" id="contactEmail1" onBlur="emailValidation(this)"/>
									</div>
								</div>
							</div>
						
						</div>
					</div>
				</div>
			<div class="panel panel-default custom-panel">
					<div class="panel-heading">Contact Information 2</div>
					<div class="panel-body">
						<div class="form-inline custom-form-inline">
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactPerson2" class="txt-label">Contact Person *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactPerson2" id="contactPerson2" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactJobTitle2" class="txt-label">Contact Job Title *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactJobTitle2"  id="contactJobTitle2"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactPhone2" class="txt-label">Contact Phone *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactPhone2"  id="contactPhone2"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactEmail2" class="txt-label">Email *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactEmail2" id="contactEmail2" onBlur="emailValidation(this)"/>
									</div>
								</div>
							</div>
						
						</div>
					</div>
				</div>
				<div class="panel panel-default custom-panel">
					<div class="panel-heading">Contact Information 3</div>
					<div class="panel-body">
						<div class="form-inline custom-form-inline">
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactPerson3" class="txt-label">Contact Person *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactPerson3" id="contactPerson3"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactJobTitle3" class="txt-label">Contact Job Title *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactJobTitle3" id="contactJobTitle3"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactPhone3" class="txt-label">Contact Phone *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactPhone3" id="contactPhone3"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactEmail3" class="txt-label">Email *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactEmail3" id="contactEmail3" onBlur="emailValidation(this)"/>
									</div>
								</div>
							</div>
						
						</div>
					</div>
				</div>
			<div class="col-md-12">
							<div class="form-group custom-form-group">

								<div id="description-output"></div>
								<div class="col-md-12 custom-btn-group">
								<button type="button" id="save-btn-valid"
										class="btn btn-default btn-sm" onClick="saveClick()">Save</button>
									<button type="SUBMIT" id="save-btn-final"
										class="btn btn-default btn-sm" style="display:none"></button>
			
									<button type="button" id="reset-btn"
										class="btn btn-default btn-sm">Reset</button>
								</div>
							</div>
						</div>
				</form:form>
			</div>
		</div>
	</div>
	 <script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"></script>
	 <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	 <script src="<c:url value="/resources/js/notify.min.js" />"></script>
	 <script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
	 <script src="<c:url value="/resources/js/dataTables.bootstrap.js" />"></script>
	 <script src="<c:url value="/resources/js/custom.js" />"></script>

	

</body>
</html>