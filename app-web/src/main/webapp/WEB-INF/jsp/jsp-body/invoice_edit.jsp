<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<style type="text/css">
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
	$(document).ready(function() {
	    $('.input-group.date').datepicker({
			todayBtn: "linked",
			todayHighlight: true,
            calendarWeeks: false,
            autoclose: true,
			format: "dd/mm/yyyy"
	    });
	});

	function doEdit(){
		$.ajax({
			url: "<spring:url value='/secure/invoice/errorEdit.json'/>",
	        data: $('#form_modify').serialize(), 
	        type: "POST",
	        success: function(result){
	        	if(result.errCodeEdit > 0){
	        		$('#err_contentEdit').html('');
		        	$('#err_grossEdit').html('');
		        	$('#err_paymentEdit').html('');
        			$('#err_dateExcuteEdit').html('');
        			$('#err_causeEdit').html('');
        			$('#err_amountEdit').html('');
        			
	           	 	var len = $.map(result.lstErr, function(n, i) { return i; }).length;
	           	 	for(var i=0;i<len;i++) {
               			$('#err_'+result.lstErr[i].propertyName).html(result.lstErr[i].message); 
              	 	}
	        	}else{
	        		$('#form_modify').submit();
	        	}
	        },
		 	error: function(XMLHttpRequest, textStatus, errorThrown){}
		});
	};
</script>

<form:form name="form_modify" id="form_modify" method="post" commandName="invoiceFilter" action="detail">
	<input name="action" type="hidden" value="MODIFY">
	<c:set var="formatPattern" value="#,##0"/>
	
	<form:input type="hidden" path="idInvoice" value="${invoiceDetail.id}"></form:input>
	<form:input type="hidden" path="branchEdit" value="${invoiceDetail.invoice.id.branch}"/>
   	<div class="row">
		<div class="col-sm-12">
			<h3 class="text-uppercase"><spring:message code="invoice.customer" text="!"/></h3>
			<div class="hr-line-dashed"></div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-4">
			<label><spring:message code="invoice.serial" text="!"/></label>
			<form:input path="serialEdit" value="${invoiceDetail.invoice.id.serial}" readonly="true" type="text" cssClass="form-control textfield"/>
		</div>
		<div class="col-sm-4">
			<label><spring:message code="invoice.name" text="!"/></label>
			<input value="${invoiceDetail.invoice.nameCustomer}" readonly="readonly" type="text" class="form-control textfield text-uppercase"/>
		</div>
		<div class="col-sm-4">
			<label><spring:message code="invoice.telephone" text="!"/></label><br>
			<input value="${invoiceDetail.invoice.telephone}" readonly="readonly" type="text" class="form-control textfield"/>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12">
			<label><spring:message code="invoice.address" text="!"/></label>
			<input value="${invoiceDetail.invoice.address}" readonly="readonly" type="text" class="form-control textfield text-uppercase"/>
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
			<form:input path="contentEdit" value="${invoiceDetail.content}" type="text" cssClass="form-control textfield text-uppercase"/>
			<label id="err_contentEdit" class="text-danger"></label>
		</div>
		<div class="col-sm-4">
		<fmt:formatNumber pattern="${formatPattern}" value="${invoiceDetail.gross}" var="gross"/>
			<label><spring:message code="invoice.gross" text="!"/></label> <label class="text-danger">*</label>
			<form:input path="grossEdit" value="${gross}"  type="text" cssClass="form-control textfield text-right"/>
			<label id="err_grossEdit" class="text-danger"></label>
		</div>
		<div class="col-sm-4">
			<fmt:formatNumber pattern="${formatPattern}" value="${invoiceDetail.payment}" var="payment"/>
			<label><spring:message code="invoice.payment" text="!"/></label> <label class="text-danger">*</label>
			<form:input path="paymentEdit" value="${payment}" type="text" cssClass="form-control textfield text-right"/>
			<label id="err_paymentEdit" class="text-danger"></label>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-4">
			<fmt:formatDate pattern="dd/MM/yyyy" value="${invoiceDetail.dateExcute}" var="dateExcute" />
			<label><spring:message code="invoice.date.excute" text="!"/></label> <label class="text-danger">*</label>
			<div class="input-group date">
	            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	            <form:input path="dateExcuteEdit" value="${dateExcute}" type="text" cssClass="form-control textfield" placeholder="dd/MM/yyyy"/>
			</div>
			<label id="err_dateExcuteEdit" class="text-danger"></label>
		</div>
		<div class="col-sm-4">
			<label><spring:message code="invoice.cause" text="!"/></label> <label class="text-danger">*</label>
			<form:input path="causeEdit" value="${invoiceDetail.cause}" type="text" cssClass="form-control textfield text-uppercase"/>
			<label id="err_causeEdit" class="text-danger"></label>
		</div>
		<div class="col-sm-4">
			<fmt:formatNumber pattern="${formatPattern}" value="${invoiceDetail.amount}" var="amount"/>
			<label><spring:message code="invoice.payment.new" text="!"/></label> <label class="text-danger">*</label>
			<form:input path="amountEdit" value="${amount }" type="text" cssClass="form-control textfield text-right"/>
			<label id="err_amountEdit" class="text-danger"></label>
		</div>
	</div>
		
	<div class="form-group">
		<div class="row">
			<div class="col-sm-12">
				<label><spring:message code="invoice.note" text="!"/></label>
				<form:input path="note" value="${invoiceDetail.note}" type="text" cssClass="form-control textfield text-uppercase"/>
			</div>
		</div>
	</div>
			
	<div class="form-group">
		<div class="row">
			<div class="col-sm-12 text-right">
				<button type="button" class="btn btn-w-m btn-default text-uppercase" data-dismiss="modal">
					<i class="fa fa-close"></i> <spring:message code="button.close" text="!"/>
				</button>
				<a onclick="doEdit();" class="btn btn-w-m btn-success text-uppercase">
					<i class="fa fa-save"></i> <spring:message code="button.save" text="!"/>
				</a>
			</div>
		</div>
	</div>
</form:form>