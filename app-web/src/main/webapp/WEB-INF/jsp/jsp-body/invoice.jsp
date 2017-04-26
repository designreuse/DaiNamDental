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
	.hr-line-dashed {
	    background-color: #ffffff;
	    border-top: 1px dashed #808080;
	    color: #ffffff;
	    height: 1px;
	    margin: 0px 0px 10px 0px;
	}
</style>

<script type="text/javascript">
	document.getElementById("invoice").className = "active";
	document.getElementById("invoiceManager").className = "active";

	var pageNo = ${invoiceFilter.page};
	
	$(function(){
	    $.ajax({
	    	url : "<spring:url value='/secure/invoice/loadpage'/>",
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

	function doCreate(action){
		$.ajax({
			url: "<spring:url value='/secure/invoice/errorCreate.json'/>",
	        data: $('#form-add').serialize(), 
	        type: "POST",
	        success: function(result){
	        	if(result.errCodeCreate > 0){
	        		$('#err_serialCreate').html('');
		        	$('#err_nameCustomerCreate').html('');
		        	$('#err_contentCreate').html('');
		        	$('#err_grossCreate').html('');
		        	$('#err_paymentCreate').html('');
		        	$('#err_dateExcuteCreate').html('');
		        	$('#err_causeCreate').html('');
		        	$('#err_amountCreate').html('');
        			
	           	 	var len = $.map(result.lstErr, function(n, i) { return i; }).length;
	           	 	for(var i=0;i<len;i++) {
               			$('#err_'+result.lstErr[i].propertyName).html(result.lstErr[i].message); 
              	 	}
	        	}else{
	        		document.forms[1].elements['action'].value = action;
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
	   	 	document.forms[0].elements['agency'].value=branch;
	   	 	document.forms[0].submit();
	    });
	};
	
</script>

<div id="body">
	<form:form name="form" id="form" method="post" commandName="invoiceFilter">
		<input type="hidden" name="action">
		<input type="hidden" name="id">
		<input type="hidden" name="agency">
	
		<div class="form-group">
			<div class="row">
				<div class="col-sm-4">
					<label><spring:message code="invoice.serial" text="!"/></label>
					<form:input path="serial" class="form-control textfield"/>
				</div>
				<div class="col-sm-4">
					<label><spring:message code="invoice.name" text="!"/></label>
					<form:input path="nameCustomer" class="form-control textfield"/>
				</div>
				<div class="col-sm-4">
					<label><spring:message code="invoice.telephone" text="!"/></label>
					<form:input path="telephone" class="form-control textfield"/>
				</div>
			</div>
		</div>
		
		<sec:authorize access="hasRole('ADMIN')">
			<div class="form-group">
				<div class="row">
					<div class="col-sm-4">
						<label><spring:message code="invoice.branch" text="!"/></label>
						<form:select path="branch" class="chosen-select" cssStyle="width:100%">
							<option value="" <c:if test="${'' == invoiceFilter.branch}">selected="selected"</c:if>>
								<spring:message code="commom.all" text="!"/>
							</option>
							<c:forEach items="${branches}" var="elm">
								<option value="${elm.id}" <c:if test="${elm.id == invoiceFilter.branch}">selected="selected"</c:if>>${elm.name }</option>
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
						<i class="fa fa-plus-square"> <spring:message code="invoice.new" text="!"/></i>
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
					<h5 class="modal-title text-uppercase"><spring:message code="invoice.new" text="!"/></h5>
				</div>
				<div class="modal-body">
					<form:form id="form-add" name="form-add" method="post" commandName="invoiceFilter">
						<input type="hidden" name="action">
						
						<div class="row">
							<div class="col-sm-12">
								<h3 class="text-uppercase"><spring:message code="invoice.customer" text="!"/></h3>
								<div class="hr-line-dashed"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4">
								<label><spring:message code="invoice.serial" text="!"/></label> <label class="text-danger">*</label>
								<form:input path="serialCreate" type="text" cssClass="form-control textfield"/>
								<label id="err_serialCreate" class="text-danger"></label>
							</div>
							<div class="col-sm-4">
								<label><spring:message code="invoice.name" text="!"/></label> <label class="text-danger">*</label>
								<form:input path="nameCustomerCreate" type="text" cssClass="form-control textfield text-uppercase"/>
								<label id="err_nameCustomerCreate" class="text-danger"></label>
							</div>
							<div class="col-sm-4">
								<label><spring:message code="invoice.telephone" text="!"/></label>
								<form:input path="telephoneCreate" type="text" cssClass="form-control textfield"/>
							</div>
						</div>
					
						<div class="row">
							<div class="col-sm-12">
								<label><spring:message code="invoice.address" text="!"/></label> <label class="text-danger">*</label>
								<form:input path="addressCreate" type="text" cssClass="form-control textfield text-uppercase"/>
								<label></label>
							</div>
						</div>
						
						<div class="row">
							<div class="col-sm-12">
								<h3 class="text-uppercase"><spring:message code="invoice.bill" text="!"/></h3>
								<div class="hr-line-dashed"></div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-sm-4">
								<label><spring:message code="invoice.content" text="!"/></label> <label class="text-danger">*</label>
								<form:input path="contentCreate" type="text" cssClass="form-control textfield text-uppercase"/>
								<label id="err_contentCreate" class="text-danger"></label>
							</div>
							<div class="col-sm-4">
								<label><spring:message code="invoice.gross" text="!"/></label> <label class="text-danger">*</label>
								<form:input path="grossCreate" type="text" cssClass="form-control textfield text-right"/>
								<label id="err_grossCreate" class="text-danger"></label>
							</div>
							<div class="col-sm-4">
								<label><spring:message code="invoice.payment" text="!"/></label> <label class="text-danger">*</label>
								<form:input path="paymentCreate" type="text" cssClass="form-control textfield text-right"/>
								<label id="err_paymentCreate" class="text-danger"></label>
							</div>
						</div>
						
						<div class="row">
							<div class="col-sm-4">
								<label><spring:message code="invoice.date.excute" text="!"/></label> <label class="text-danger">*</label>
								<div class="input-group date">
			                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
			                        <form:input path="dateExcuteCreate" type="text" cssClass="form-control textfield" placeholder="dd/MM/yyyy"/>
								</div>
								<label id="err_dateExcuteCreate" class="text-danger"></label>
							</div>
							<div class="col-sm-4">
								<label><spring:message code="invoice.cause" text="!"/></label> <label class="text-danger">*</label>
								<form:input path="causeCreate" type="text" cssClass="form-control textfield text-uppercase"/>
								<label id="err_causeCreate" class="text-danger"></label>
							</div>
							<div class="col-sm-4">
								<label><spring:message code="invoice.payment.new" text="!"/></label> <label class="text-danger">*</label>
								<form:input path="amountCreate" type="text" cssClass="form-control textfield text-right"/>
								<label id="err_amountCreate" class="text-danger"></label>
							</div>
						</div>
					</form:form>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-w-m btn-default text-uppercase" data-dismiss="modal">
						<i class="fa fa-close"></i> <spring:message code="button.close" text="!"/>
					</button>
					<button type="button" onclick="doCreate('CREATE');" class="btn btn-w-m btn-success text-uppercase">
						<i class="fa fa-save"></i> <spring:message code="button.save" text="!"/>
					</button>
					<button type="button" onclick="doCreate('CREATE_PRINT');" class="btn btn-w-m btn-success text-uppercase">
						<i class="fa fa-print"></i> <spring:message code="button.print" text="!"/>
					</button>
				</div>
			</div>
		</div>
	</div>
</div>