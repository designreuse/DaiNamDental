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

	$(function(){
	    $('.input-group.date').datepicker({
	    	todayHighlight: true,
			todayBtn: "linked",
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
			url: "<spring:url value='/secure/records/errorCreate.json'/>",
	        data: $('#form-add').serialize(), 
	        type: "POST",
	        success: function(result){
	        	if(result.errCodeCreate > 0){
	        		$('#err_dateExcute').html('');
		        	$('#err_teeth').html('');
		        	$('#err_content').html('');
        			$('#err_dateNext').html('');
        			$('#err_gross').html('');
        			$('#err_sale').html('');
        			$('#err_payment').html('');
        			
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
	   	 	 document.forms[0].elements['idRecords'].value=id;
	   	 	document.forms[0].submit();
	    });
	};

	function doEditRecords(id) {
 	    $.ajax({ 
 	    	url: "<spring:url value='/secure/records/edit/'/>" + id,
 	        cache: false,
 	        success: function (reponse) {
 	            $("div#edit-result").html(reponse);
 	        }
 	    });
 	}
 	
</script>

<div id="body">
	<c:set var="formatPattern" value="#,##0"/>

	<form:form id="form" name="form" method="post" commandName="recordsFilter">
		<input type="hidden" name="action">
		<input type="hidden" name="idRecords">
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-8">
					<c:url value="/secure/customer/detail"  var="linkCustomer">
						<c:param name="id" value="${customer.id.serial}"/>
						<c:param name="agency" value="${customer.id.branch}"/>
					</c:url>
					<a href="${linkCustomer }" class="btn btn-w-m btn-default text-uppercase">
						<i class="fa fa-user"></i> <spring:message code="customer.general" text="!"/>
					</a>
					<a class="btn btn-w-m btn-success text-uppercase">
						<i class="fa fa-list-ol"></i> <spring:message code="customer.records" text="!"/>
					</a>
				</div>
				<div class="col-sm-4 text-right">
					<a onclick="doSubmit('BACK')"  class="btn btn-w-m btn-success text-uppercase">
						<spring:message code="button.back" text="!"/>
					</a>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<table class="table table-striped table-bordered table-hover" style="width:100%;">
					<thead>
						<tr>
							<th><spring:message code="customer.id" text="!"/></th>
							<th><spring:message code="customer.name" text="!"/></th>
							<th><spring:message code="customer.telephone" text="!"/></th>
							<th><spring:message code="customer.dentist" text="!"/></th>
							<th class="text-right"><spring:message code="customer.amount" text="!"/></th>
							<th class="text-right"><spring:message code="customer.sale" text="!"/></th>
							<th class="text-right"><spring:message code="customer.payment" text="!"/></th>
							<th class="text-right"><spring:message code="customer.rest" text="!"/></th>
							<th><spring:message code="customer.status" text="!"/></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${customer.id.serial}</td>
							<td>${customer.name}</td>
							<td>${customer.telephone}</td>
							<td>${customer.dentist}</td>
							<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${customer.gross}"/></td>
							<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${customer.sale}"/></td>
							<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${customer.payment}"/></td>
							<c:choose>
								<c:when test="${customer.gross - customer.sale - customer.payment < 0 }">
									<td class="text-right text-danger"><fmt:formatNumber pattern="${formatPattern}" value="${customer.gross - customer.sale - customer.payment}"/></td>
								</c:when>
								<c:otherwise>
									<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${customer.gross - customer.sale - customer.payment}"/></td>
								</c:otherwise>
							</c:choose>
							<td><spring:message code="customer.status.${customer.status}" text="!"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="form-group">
			<div class="row">
				<div class="col-lg-12">
					<c:choose>
						<c:when test="${customer.status eq 'F'}">
							<button type="button" class="btn btn-w-m btn-default text-uppercase">
								<i class="fa fa-plus-square"> <spring:message code="records.add" text="!"/></i>
							</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="btn btn-w-m btn-success text-uppercase" data-toggle="modal" data-target="#formCreate">
								<i class="fa fa-plus-square"> <spring:message code="records.add" text="!"/></i>
							</button>
						</c:otherwise>
					</c:choose>
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
							<th><spring:message code="records.dateExcute" text="!"/></th>
							<th><spring:message code="records.teeth" text="!"/></th>
							<th><spring:message code="records.content" text="!"/></th>
							<th><spring:message code="records.dateNext" text="!"/></th>
							<th><spring:message code="records.contentNext" text="!"/></th>
							<th class="text-right"><spring:message code="records.amount.extra" text="!"/></th>
							<th class="text-right"><spring:message code="records.sale" text="!"/></th>
							<th class="text-right"><spring:message code="records.net" text="!"/></th>
							<th class="text-right"><spring:message code="records.payment" text="!"/></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${customer.records}" var="elm" varStatus="stt">
							<tr id="${elm.recordId}">
								<td class="text-center">${stt.index + 1}</td>
								<td class="text-center text-nowrap">
									<c:choose>
										<c:when test="${customer.status eq 'F'}">
											<a style="color: #bababa"><i class="fa fa-2x fa-edit"></i></a>
											<a style="color: #bababa"><i class="fa fa-2x fa-trash-o"></i></a>
										</c:when>
										<c:otherwise>
											<a onclick='doEditRecords("${elm.recordId}")' data-toggle="modal" data-target="#formEdit"
												title="<spring:message code="message.edit" text="!"/>"><i class="fa fa-2x fa-edit"></i>
											</a>
											<a onclick="ConformDelete('${elm.recordId}', '<fmt:formatDate pattern="dd/MM/yyyy" value="${elm.dateExcute}" />')" 
												title="<spring:message code="button.delete" text="!Delete"/>"><i class="fa fa-2x fa-trash-o"></i>
											</a>
										</c:otherwise>
									</c:choose>
								</td>
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${elm.dateExcute}" /></td>
								<td>${elm.teeth}</td>
								<td>${elm.content}</td>
								<td><fmt:formatDate pattern="dd/MM/yyyy" value="${elm.dateNext}" /></td>
								<td>${elm.contentNext}</td>
								<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${elm.gross}"/></td>
								<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${elm.sale}"/></td>
								<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${elm.gross -  elm.sale}"/></td>
								<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${elm.payment}"/></td>
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
					<h5 class="modal-title text-uppercase"><spring:message code="records.edit" text="!"/></h5>
				</div>
				<div class="modal-body">
					<div id="edit-result"></div>
				</div>
			</div>
		</div>
	</div>
		
	<!-- ------------------------------------------- CREATE ----------------------------------------------------- -->
	<div class="modal inmodal" id="formCreate" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width:900px">
			<div class="modal-content animated bounceInRight">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true"><i class="fa fa-2x fa-times-circle"></i></span></button>
					<h5 class="modal-title text-uppercase"><spring:message code="records.add" text="!"/></h5>
				</div>
				<div class="modal-body">
					<form:form id="form-add" name="form-add" method="post" commandName="recordsFilter">
						<input type="hidden" name="action" value="CREATE">
						
						<div class="form-group">
							<div class="row">
								<div class="col-sm-6">
									<label><spring:message code="records.dateExcute" text="!"/></label> <label class="text-danger">*</label>
									<div class="input-group date">
				                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
				                        <form:input path="dateExcute" type="text" cssClass="form-control textfield" placeholder="dd/MM/yyyy"/>
									</div>
									<label id="err_dateExcute" class="text-danger"></label>
								</div>
								<div class="col-sm-6">
									<label><spring:message code="records.teeth" text="!"/></label> <label class="text-danger">*</label>
									<form:input path="teeth" type="text" cssClass="form-control textfield"/>
									<label id="err_teeth" class="text-danger"></label>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<div class="row">
								<div class="col-sm-6">
									<label><spring:message code="records.content" text="!"/></label> <label class="text-danger">*</label>
									<form:input path="content" type="text" cssClass="form-control textfield text-uppercase"/>
									<label id="err_content" class="text-danger"></label>
								</div>
								<div class="col-sm-6">
									<label><spring:message code="records.dentist" text="!"/></label>
									<form:select path="dentist" class="chosen-select" cssStyle="width:100%">
										<c:forEach items="${dentists}" var="elm">
											<option value="${elm.name }" <c:if test="${customer.dentist == elm.name}">selected="selected"</c:if>>${elm.name }</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<div class="row">
								<div class="col-sm-4">
									<label><spring:message code="records.amount.extra" text="!"/></label> <label class="text-danger">*</label>
									<form:input path="gross" type="text" cssClass="form-control textfield text-right"/>
									<label id="err_gross" class="text-danger"></label>
								</div>
								<div class="col-sm-4">
									<label><spring:message code="records.sale" text="!"/></label> <label class="text-danger">*</label>
									<form:input path="sale" type="text" cssClass="form-control textfield text-right"/>
									<label id="err_sale" class="text-danger"></label>
								</div>
								<div class="col-sm-4">
									<label><spring:message code="records.payment" text="!"/></label> <label class="text-danger">*</label>
									<form:input path="payment" type="text" cssClass="form-control textfield text-right"/>
									<label id="err_payment" class="text-danger"></label>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<div class="row">
								<div class="col-sm-6">
									<label><spring:message code="records.dateNext" text="!"/></label>
									<div class="input-group date">
				                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
				                        <form:input path="dateNext" type="text" cssClass="form-control textfield" placeholder="dd/MM/yyyy"/>
									</div>
									<label id="err_dateNext" class="text-danger"></label>
								</div>
								<div class="col-sm-6">
									<label><spring:message code="records.contentNext" text="!"/></label>
									<form:input path="contentNext" type="text" cssClass="form-control textfield text-uppercase"/>
								</div>
							</div>
						</div>
						
						<%-- <div class="form-group">
							<div class="row">
								<div class="col-sm-12">
									<label><spring:message code="records.note" text="!"/></label>
									<form:textarea path="note" rows="2" class="form-control textfield"/>
								</div>
							</div>
						</div> --%>
						
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