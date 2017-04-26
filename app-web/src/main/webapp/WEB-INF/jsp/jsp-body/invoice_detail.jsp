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

	$(function(){

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
			url: "<spring:url value='/secure/invoice/detail/errorCreate.json'/>",
	        data: $('#form-add').serialize(), 
	        type: "POST",
	        success: function(result){
	        	if(result.errCodeCreate > 0){
		        	$('#err_dateExcuteCreate').html('');
		        	$('#err_causeCreate').html('');
        			$('#err_amountCreate').html('');
        			$('#err_contentCreate').html('');
		        	$('#err_grossCreate').html('');
		        	$('#err_paymentCreate').html('');
		        	
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

	function ConformDelete(id, date) {
	    swal({
	        title: "<spring:message code="message.conform.delete" text="!"/>" + " [" + date + "] ?",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "<spring:message code="button.delete" text="!"/>",
	        cancelButtonText: "<spring:message code="button.cancel" text="!"/>",
	        closeOnConfirm: false
	      },function () {
	   	 	 document.forms[0].elements['action'].value='DELETE';
	   	 	 document.forms[0].elements['idInvoiceDetail'].value=id;
	   	 	document.forms[0].submit();
	    });
	};

	function doEditInvoice(id) {
 	    $.ajax({ 
 	    	url: "<spring:url value='/secure/invoice/edit/'/>" + id,
 	        cache: false,
 	        success: function (reponse) {
 	            $("div#edit-result").html(reponse);
 	        }
 	    });
 	}
 	
</script>

<div id="body">
	<c:set var="formatPattern" value="#,##0"/>

	<form:form id="form" name="form" method="post" commandName="invoiceFilter">
		<input type="hidden" name="action">
		<input type="hidden" name="idInvoiceDetail">
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-12">
					<a href="<c:url value="/secure/invoice"/>" class="btn btn-w-m btn-default text-uppercase">
						<spring:message code="button.back" text="!"/>
					</a>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<table class="table table-striped table-bordered table-hover table-result" style="width:100%;">
					<thead>
						<tr>
							<th><spring:message code="invoice.serial" text="!"/></th>
							<th><spring:message code="invoice.name" text="!"/></th>
							<th><spring:message code="invoice.telephone" text="!"/></th>
							<th><spring:message code="invoice.address" text="!"/></th>
							<th class="text-right"><spring:message code="invoice.amount" text="!"/></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${invoice.id.serial}</td>
							<td>${invoice.nameCustomer}</td>
							<td>${invoice.telephone}</td>
							<td>${invoice.address}</td>
							<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${invoice.amount}"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-12">
					<c:if test="${invoice != null }">
						<button type="button" class="btn btn-w-m btn-success text-uppercase" data-toggle="modal" data-target="#formCreate">
							<i class="fa fa-plus-square"> <spring:message code="invoice.add" text="!"/></i>
						</button>
					</c:if>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<table class="table table-striped table-bordered table-hover table-result" style="width:100%;">
					<thead>
						<tr>
							<th class="text-center">#</th>
							<th class="text-center"></th>
							<th><spring:message code="invoice.date.excute" text="!"/></th>
							<th><spring:message code="invoice.cause" text="!"/></th>
							<th><spring:message code="invoice.message" text="!"/></th>
							<th class="text-right"><spring:message code="invoice.payment.new" text="!"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${invoice.invoiceDetails}" var="elm" varStatus="stt">
							<c:url value="/secure/invoice/print" var ="print">
								<c:param name="id" value="${elm.id }"></c:param>
							</c:url>
							<tr>
								<td class="text-center">${stt.index + 1}</td>
								<td class="text-center text-nowrap">
									<a href="${print }"  target="_blank" title="<spring:message code="button.printer" text="!"/>"><i class="fa fa-2x fa-print"></i></a>
									<sec:authorize access="hasRole('ADMIN')">
									<a onclick='doEditInvoice("${elm.id}")' data-toggle="modal" data-target="#formEdit"
										title="<spring:message code="message.edit" text="!"/>"><i class="fa fa-2x fa-edit"></i>
									</a>
									<a onclick="ConformDelete('${elm.id}', '<fmt:formatDate pattern="dd/MM/yyyy" value="${elm.dateExcute}" />')" title="<spring:message code="button.delete" text="!"/>"><i class="fa fa-2x fa-trash-o"></i></a>
									</sec:authorize>
								</td>
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${elm.dateExcute}" /></td>
								<td>${elm.cause}</td>
								<td>${elm.note}</td>
								<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${elm.amount}"/></td>
							</tr>
						</c:forEach>	
					</tbody>
				</table>
			</div>
		</div>
	</form:form>
	
	<!-- ------------------------------------------- EDIT ----------------------------------------------------- -->
	<div class="modal inmodal" id="formEdit" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width: 900px;">
			<div class="modal-content animated bounceInRight">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true"><i class="fa fa-2x fa-times-circle"></i></span></button>
					<h5 class="modal-title text-uppercase"><spring:message code="invoice.edit" text="!"/></h5>
				</div>
				<div class="modal-body">
					<div id="edit-result"></div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- ------------------------------------------- CREATE ----------------------------------------------------- -->
	<div class="modal inmodal" id="formCreate" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width:850px">
			<div class="modal-content animated bounceInRight">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true"><i class="fa fa-2x fa-times-circle"></i></span></button>
					<h5 class="modal-title text-uppercase"><spring:message code="invoice.add" text="!"/></h5>
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
								<label><spring:message code="invoice.serial" text="!"/></label>
								<form:input path="serialCreate" value="${invoice.id.serial}" readonly="true" type="text" cssClass="form-control textfield"/>
								<label id="err_serialCreate" class="text-danger"></label>
							</div>
							<div class="col-sm-4">
								<label><spring:message code="invoice.name" text="!"/></label>
								<form:input path="nameCustomerCreate" value="${invoice.nameCustomer}" readonly="true" type="text" cssClass="form-control textfield text-uppercase"/>
								<label id="err_nameCustomerCreate" class="text-danger"></label>
							</div>
							<div class="col-sm-4">
								<label><spring:message code="invoice.telephone" text="!"/></label><br>
								<form:input path="telephoneCreate" value="${invoice.telephone}" readonly="true" type="text" cssClass="form-control textfield"/>
							</div>
						</div>
					
						<div class="row">
							<div class="col-sm-12">
								<label><spring:message code="invoice.address" text="!"/></label>
								<form:input path="addressCreate" value="${invoice.address}" readonly="true" type="text" cssClass="form-control textfield text-uppercase"/>
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
								<fmt:formatNumber pattern="${formatPattern}" value="${invoice.amount}" var="amount"/>
								<label><spring:message code="invoice.payment" text="!"/></label> <label class="text-danger">*</label>
								<form:input path="paymentCreate" value="${amount}" type="text" cssClass="form-control textfield text-right"/>
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
					
						<div class="row">
							<div class="col-sm-12 text-right">
								<button type="button" onclick="doCreate('CREATE');" class="btn btn-w-m btn-success text-uppercase">
									<i class="fa fa-save"></i> <spring:message code="button.save" text="!"/>
								</button>
								<button type="button" onclick="doCreate('CREATE_PRINT');" class="btn btn-w-m btn-success text-uppercase">
									<i class="fa fa-print"></i> <spring:message code="button.print" text="!"/>
								</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
		
	