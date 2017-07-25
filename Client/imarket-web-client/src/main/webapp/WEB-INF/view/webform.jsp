
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ page isELIgnored="false"%>   
<!DOCTYPE html>
<html lang="en">
<head>
<title>iMarket</title>

<jsp:include page="cssresources.jsp" />
</head>
<body>

	<div class="container custom-container">
		<jsp:include page="header.jsp" />
		<div class="tab-content custom-tab-content">
			<div id="dashboard" class="tab-pane fade ">
				
			</div>
			<div id="web-form" class="tab-pane fade in active">
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
									<button type="button" id="upload-btn-valid"
										class="btn btn-btn-primary btn-sm" onClick ="onUploadFile()">Upload</button>
									<button type="submit" id="upload-btn-final"
										class="btn btn-primary btn-sm" style = "display:none">Upload</button>
									<button type="button" id="clear-btn"
										class="btn btn-primary btn-sm"
										onclick="reset($('#file'),$('#file_text'))">Clear</button>
								</form>
							</div>
							<div class="col-md-6">
								<a href="<c:url value="/resources/files/Template.xlsx" />" download ="Template.xlsx" class="btn btn-primary btn-sm"> Download
									Template<span class="glyphicon glyphicon-download-alt"></span>
								</a>
							</div>
						</div>
					</div>
				</div>
				<form:form  commandName="prospectsVO" action="savePropects" method ="POST">
				<form:hidden path="id" id="record_id"/>
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
									<form:select  class="form-control" multiple="true" id = "industry" path="industries" >
										 	
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
										<form:select path="employeeCount" class="form-control">
											   <form:option value="NONE" label="--- Select ---"/>
											    <form:option value="0 - 100" label="0 - 100"/>
											    <form:option value="100 - 250" label="100 - 250"/>
											    <form:option value="250 - 500" label="250 - 500"/>
											    <form:option value="500 - 1000" label="500 - 1000"/>
											    <form:option value="Above 1000" label="Above 1000"/>
										</form:select>  
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="website" class="txt-label">Website *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="website" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="country" class="txt-label">Country *</label>
									</div>
									<div class="col-md-6">
										<form:select  class="form-control" path="country" >
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
									
										<form:input class="form-control" path="companyEmail" onBlur="emailValidation(this)" />
									</div>
								</div>
							</div>
								<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="revenue" class="txt-label">Revenue *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="revenue" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="lead_source" class="">Lead Source *</label>
									</div>
									<div class="col-md-6">
									
										<form:input class="form-control" path="leadSource" />
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
										<form:input class="form-control" path="contactPerson1" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactJobTitle1" class="txt-label">Contact Job Title *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactJobTitle1" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactPhone1" class="txt-label">Contact Phone *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactPhone1" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactEmail1" class="txt-label">Email *</label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactEmail1" onBlur="emailValidation(this)" />
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
										<label for="contactPerson2" class="txt-label">Contact Person </label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactPerson2" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactJobTitle2" class="txt-label">Contact Job Title </label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactJobTitle2" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactPhone2" class="txt-label">Contact Phone </label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactPhone2" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactEmail2" class="txt-label">Email </label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactEmail2" onBlur="emailValidation(this)" />
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
										<label for="contactPerson3" class="txt-label">Contact Person </label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactPerson3" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactJobTitle3" class="txt-label">Contact Job Title </label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactJobTitle3" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactPhone3" class="txt-label">Contact Phone </label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactPhone3" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group custom-form-group">
									<div class="col-md-6 txt-label">
										<label for="contactEmail3" class="txt-label">Email </label>
									</div>
									<div class="col-md-6">
										<form:input class="form-control" path="contactEmail3" onBlur="emailValidation(this)" />
									</div>
								</div>
							</div>
						<div class="col-md-12">
							<div class="form-group custom-form-group">

								<div id="description-output"></div>
								<div class="col-md-12 custom-btn-group">
									<button type="button" id="save-btn-valid"
										class="btn btn-primary btn-sm" onClick="saveClick()">Save</button>
									<button type="SUBMIT" id="save-btn-final"
										class="btn btn-primary btn-sm" style ='display:none'>Save</button>
			
									<button type="button" id="reset-btn"
										class="btn btn-primary btn-sm" onClick="resetAll()">Reset</button>
								</div>
							</div>
						</div>
						</div>
					</div>
				</div>
			
				</form:form>
			</div>
		</div>
	</div>
	 <jsp:include page="jsresources.jsp" />
 	<script type="text/javascript" >
       $('#webform').addClass('active') 
        $('#dashboard').removeClass('active')
        
 	</script>
	

</body>
</html>