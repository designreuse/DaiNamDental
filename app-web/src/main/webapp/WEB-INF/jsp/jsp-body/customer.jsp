<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<link href="<c:url value="/static/inspinia/css/plugins/sweetalert/sweetalert.css"/>" rel="stylesheet">
<script src="<c:url value="/static/inspinia/js/plugins/sweetalert/sweetalert.min.js"/>"></script>

<style type="text/css">
	.datepicker { 
		z-index: 9999 !important;
	}
	
	.chosen-container-single .chosen-single {
         background: #fff;
         border-radius: 3px;
         color: #444;
         display: block;
         height: 23px;
         line-height: 24px;
         overflow: hidden;
         padding: 0 0 0 8px;
         position: relative;
         text-decoration: none;
         white-space: nowrap;
         box-shadow: none;
         border: 1px solid #e5e6e7;
     }
     .chosen-container {
        width: 100% !important;
    }
</style>

<script type="text/javascript">
	document.getElementById("customer").className = "active";
	document.getElementById("manager").className = "active";

	var pageNo = ${customerFilter.page};
	
	$(function(){
	    $.ajax({
	    	url : "<spring:url value='/secure/customer/loadpage'/>",
	        data: { pageNo: pageNo },
	        cache : false,
	        success: function (response) {
	            $("#_results").html(response);
	        }
	    });

	    $('.input-group.date').datepicker({
			todayBtn: "linked",
			todayHighlight: true,
            calendarWeeks: false,
            autoclose: true,
			format: "dd/mm/yyyy"
	    });
	    
	    $('.chosen-select').chosen({
	    	"disable_search": true
	    });
	});

	function doCreate(){
		$.ajax({
			url: "<spring:url value='/secure/customer/errorCreate.json'/>",
	        data: $('#form-add').serialize(), 
	        type: "POST",
	        success: function(result){
	        	if(result.errCodeCreate > 0){
	        		$('#err_serialCreate').html('');
		        	$('#err_nameCreate').html('');
		        	$('#err_dateBirthCreate').html('');
        			$('#err_dateStartCreate').html('');
        			
	           	 	var len = $.map(result.lstErr, function(n, i) { return i; }).length;
	           	 	for(var i=0;i<len;i++) {
               			$('#err_'+result.lstErr[i].propertyName).html(result.lstErr[i].message); 
              	 	}
	        	}else{
	        		$('#form-add').submit();
	        	}
	        },
		 	error: function(XMLHttpRequest, textStatus, errorThrown){}
		});
	};

	function ConformDelete(id, branch) {
	    swal({
	        title: "<spring:message code="message.conform.delete" text="!"/>" + " [" + id + "] ?",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "<spring:message code="button.delete" text="!"/>",
	        cancelButtonText: "<spring:message code="button.cancel" text="!"/>",
	        closeOnConfirm: false
	      },function () {
	   	 	document.forms[0].elements['action'].value='DELETE';
	   	 	document.forms[0].elements['id'].value=id;
	   	 	document.forms[0].elements['agency'].value = branch;
			document.forms[0].submit();
	    });
	};
</script>

<div id="body">
	
	<form:form name="form" id="form" method="post" commandName="customerFilter">
		<input name="action" type="hidden">
		<input name="id" type="hidden">
		<input name="agency" type="hidden">
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-4">
					<label><spring:message code="customer.id" text="!"/></label>
					<form:input path="serial" class="form-control textfield"/>
				</div>
				<div class="col-sm-4">
					<label><spring:message code="customer.name" text="!"/></label>
					<form:input path="name" class="form-control textfield"/>
				</div>
				<div class="col-sm-4">
					<label><spring:message code="customer.telephone" text="!"/></label>
					<form:input path="telephone" class="form-control textfield"/>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-4">
					<label><spring:message code="customer.date.start" text="!"/></label>
					<div class="input-group date">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <form:input path="dateStartFrom" type="text" cssClass="form-control textfield" placeholder="dd/MM/yyyy"/>
					</div>
				</div>
				<div class="col-sm-4">
					<label>&nbsp;</label>
					<div class="input-group date">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <form:input path="dateStartTo" type="text" cssClass="form-control textfield" placeholder="dd/MM/yyyy"/>
					</div>
				</div>
				<div class="col-sm-4">
					<label><spring:message code="customer.status" text="!"/></label>
					<form:select path="status" class="chosen-select" cssStyle="width:100%">
						<option value="" <c:if test="${'' == customerFilter.status}">selected="selected"</c:if>>
							<spring:message code="commom.all" text="!"/>
						</option>
						<option value="W" <c:if test="${'W' == customerFilter.status}">selected="selected"</c:if>>
							<spring:message code="customer.status.W" text="!"/>
						</option>
						<option value="T" <c:if test="${'T' == customerFilter.status}">selected="selected"</c:if>>
							<spring:message code="customer.status.T" text="!"/>
						</option>
						<option value="F" <c:if test="${'F' == customerFilter.status}">selected="selected"</c:if>>
							<spring:message code="customer.status.F" text="!"/>
						</option>
					</form:select>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-4">
					<label><spring:message code="customer.type" text="!"/></label>
					<form:select path="type" class="chosen-select" cssStyle="width:100%">
						<option value="" <c:if test="${'' == customerFilter.type}">selected="selected"</c:if>>
							<spring:message code="commom.all" text="!"/>
						</option>
						<option value="N" <c:if test="${'N' == customerFilter.type}">selected="selected"</c:if>>
							<spring:message code="customer.type.new" text="!"/>
						</option>
						<option value="G" <c:if test="${'G' == customerFilter.type}">selected="selected"</c:if>>
							<spring:message code="customer.type.guarantee" text="!"/>
						</option>
					</form:select>
				</div>
			</div>
		</div>
		
		<sec:authorize access="hasRole('ADMIN')">
			<div class="form-group">
				<div class="row">
					<div class="col-sm-4">
						<label><spring:message code="customer.branch" text="!"/></label>
						<form:select path="branch" class="chosen-select" cssStyle="width:100%">
							<option value="" <c:if test="${'' == customerFilter.branch}">selected="selected"</c:if>>
								<spring:message code="commom.all" text="!"/>
							</option>
							<c:forEach items="${branches}" var="elm">
								<option value="${elm.id}" <c:if test="${elm.id == customerFilter.branch}">selected="selected"</c:if>>${elm.name }</option>
							</c:forEach>
						</form:select>
					</div>
				</div>
			</div>
		</sec:authorize>
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-4">
					<button type="button" class="btn btn-w-m btn-success text-uppercase" data-toggle="modal" data-target="#formCreate">
						<i class="fa fa-plus-square"> <spring:message code="customer.create" text="!"/></i>
					</button>
				</div>
				<div class="col-sm-8 text-right">
					<a onclick="javascript:doSubmit('RESET');" class="btn btn-w-m btn-default text-uppercase">
						<i class="fa fa-undo"></i> <spring:message code="button.reset" text="!"/>
					</a>
					<a onclick="javascript:doSubmit('GO');" class="btn btn-w-m btn-success text-uppercase">
						<i class="fa fa-search"></i> <spring:message code="button.search" text="!"/>
					</a>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<div id="_results"></div>
			</div>
		</div>
	</form:form>
	
	<!-- ------------------------------------------- CREATE ----------------------------------------------------- -->
	<div class="modal inmodal" id="formCreate" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width:900px">
			<div class="modal-content animated bounceInRight">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true"><i class="fa fa-2x fa-times-circle"></i></span></button>
					<h5 class="modal-title text-uppercase"><spring:message code="customer.create" text="!"/></h5>
				</div>
				<div class="modal-body">
					<form:form id="form-add" name="form-add" method="post" commandName="customerFilter" action="customer">
						<input type="hidden" name="action" value="CREATE">
						
						<div class="form-group">
							<div class="row">
								<div class="col-sm-4">
									<label><spring:message code="customer.id" text="!"/></label> <label class="text-danger">*</label>
									<form:input path="serialCreate" type="text" cssClass="form-control textfield"/>
									<label id="err_serialCreate" class="text-danger"></label>
								</div>
								<div class="col-sm-4">
									<label><spring:message code="customer.name" text="!"/></label> <label class="text-danger">*</label>
									<form:input path="nameCreate" type="text" cssClass="form-control textfield text-uppercase"/>
									<label id="err_nameCreate" class="text-danger"></label>
								</div>
								<div class="col-sm-4">
									<label><spring:message code="customer.sex" text="!"/></label><br>
									<div class="radio radio-primary radio-inline">
                                         <input type="radio" id="sexCreate1" value="1" name="sexCreate" checked="checked">
                                         <label for="sexCreate1"> <spring:message code="customer.sex.men" text="!"/> </label>
                                    </div>
                                    <div class="radio radio-primary radio-inline">
                                        <input type="radio" id="sexCreate2" value="2" name="sexCreate">
                                        <label for="sexCreate2"> <spring:message code="customer.sex.women" text="!"/> </label>
                                    </div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-sm-4">
									<label><spring:message code="customer.type" text="!"/></label><br>
									<div class="radio radio-primary radio-inline">
                                         <input type="radio" id="typeCreate1" value="N" name="typeCreate" checked="checked">
                                         <label for="typeCreate1"> <spring:message code="customer.type.new" text="!"/> </label>
                                    </div>
                                    <div class="radio radio-primary radio-inline">
                                        <input type="radio" id="typeCreate2" value="G" name="typeCreate">
                                        <label for="typeCreate2"> <spring:message code="customer.type.guarantee" text="!"/> </label>
                                    </div>
								</div>
								<div class="col-sm-4">
									<label><spring:message code="customer.date.birth" text="!"/></label> <label class="text-danger">*</label>
									<div class="input-group date">
				                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
				                        <form:input path="dateBirthCreate" type="text" cssClass="form-control textfield" placeholder="dd/MM/yyyy"/>
									</div>
									<label id="err_dateBirthCreate" class="text-danger"></label>
								</div>
								<div class="col-sm-4">
									<label><spring:message code="customer.telephone" text="!"/></label>
									<form:input path="telephoneCreate" type="text" cssClass="form-control textfield"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-sm-4">
									<label><spring:message code="customer.email" text="!"/></label>
									<form:input path="emailCreate" type="text" cssClass="form-control textfield"/>
								</div>
								<div class="col-sm-8">
									<label><spring:message code="customer.address" text="!"/></label><br>
									<form:input path="addressCreate" type="text" cssClass="form-control textfield text-uppercase"/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<div class="row">
								<div class="col-sm-4">
									<label><spring:message code="customer.date.start" text="!"/></label> <label class="text-danger">*</label>
									<div class="input-group date">
				                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
				                        <form:input path="dateStartCreate" type="text" cssClass="form-control textfield" placeholder="dd/MM/yyyy"/>
									</div>
									<label id="err_dateStartCreate" class="text-danger"></label>
								</div>
								<div class="col-sm-8">
									<label><spring:message code="customer.cause" text="!"/></label>
									<form:input path="causeCreate" type="text" cssClass="form-control textfield text-uppercase"/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<div class="row">
								<div class="col-sm-4">
									<label><spring:message code="customer.dentist" text="!"/></label>
									<form:select path="dentistCreate" class="chosen-select" cssStyle="width:100%">
										<c:forEach items="${dentists}" var="elm">
											<option value="${elm.name }">${elm.name }</option>
										</c:forEach>
									</form:select>
								</div>
								<div class="col-sm-4">
									<label><spring:message code="customer.status" text="!"/></label>
									<form:select path="statusCreate" class="chosen-select" cssStyle="width:100%">
										<option value="W" selected="selected">
											<spring:message code="customer.status.W" text="!"/>
										</option>
										<option value="T">
											<spring:message code="customer.status.T" text="!"/>
										</option>
										<option value="F">
											<spring:message code="customer.status.F" text="!"/>
										</option>
									</form:select>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12">
									<label><spring:message code="customer.note" text="!"/></label>
									<form:textarea path="noteCreate" rows="2" class="form-control textfield"/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12 text-right">
									<button type="button" class="btn btn-w-m btn-default text-uppercase" data-dismiss="modal">
										<i class="fa fa-close"></i> <spring:message code="button.close" text="!"/>
									</button>
									<button type="button" onclick="doCreate();" class="btn btn-w-m btn-success text-uppercase">
										<i class="fa fa-save"></i> <spring:message code="button.save" text="!"/>
									</button>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>