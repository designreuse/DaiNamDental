<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

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
	document.getElementById("customer").className = "active";
	document.getElementById("manager").className = "active";

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

	function doCreate(){
		$.ajax({
			url: "<spring:url value='/secure/customer/errorEdit.json'/>",
	        data: $('#form').serialize(), 
	        type: "POST",
	        success: function(result){
	        	if(result.errCodeEdit > 0){
		        	$('#err_nameCreate').html('');
		        	$('#err_dateBirthCreate').html('');
        			$('#err_dateStartCreate').html('');
        			
	           	 	var len = $.map(result.lstErr, function(n, i) { return i; }).length;
	           	 	for(var i=0;i<len;i++) {
               			$('#err_'+result.lstErr[i].propertyName).html(result.lstErr[i].message); 
              	 	}
	        	}else{
	        		document.forms[0].elements['action'].value='GO';
	        		$('#form').submit();
	        	}
	        },
		 	error: function(XMLHttpRequest, textStatus, errorThrown){}
		});
	};
	
</script>
	
<div id="body">
	<form:form id="form" name="form" method="post" commandName="customerDetailFilter">
		<input type="hidden" name="action">
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-8">
					<a class="btn btn-w-m btn-success text-uppercase">
						<i class="fa fa-user"></i> <spring:message code="customer.general" text="!"/>
					</a>
					
					<c:url value="/secure/records"  var="linkCustomer">
						<c:param name="id" value="${customer.id.serial}"/>
						<c:param name="agency" value="${customer.id.branch}"/>
					</c:url>
					<a href="${linkCustomer }" class="btn btn-w-m btn-default text-uppercase">
						<i class="fa fa-list-ol"></i> <spring:message code="customer.records" text="!"/>
					</a>
				</div>
				<div class="col-sm-4 text-right">
					<a onclick="doSubmit('BACK')" class="btn btn-w-m btn-success text-uppercase">
						<spring:message code="button.back" text="!"/>
					</a>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-4">
					<label><spring:message code="customer.id" text="!"/></label>
					<form:input path="serialCreate" value="${customer.id.serial }"  disabled = "true" type="text" cssClass="form-control textfield"/>
				</div>
				<div class="col-sm-4">
					<label><spring:message code="customer.name" text="!"/></label> <label class="text-danger">*</label>
					<form:input path="nameCreate" value="${customer.name }" type="text" cssClass="form-control textfield text-uppercase"/>
					<label id="err_nameCreate" class="text-danger"></label>
				</div>
				<div class="col-sm-4">
					<label><spring:message code="customer.sex" text="!"/></label><br>
					<div class="radio radio-primary radio-inline">
                         <input type="radio" id="sexCreate1" value="1" name="sexCreate" <c:if test="${customer.sex == '1'}">checked="checked"</c:if>>
                         <label for="sexCreate1"> <spring:message code="customer.sex.men" text="!"/> </label>
                    </div>
                    <div class="radio radio-primary radio-inline">
                        <input type="radio" id="sexCreate2" value="2" name="sexCreate" <c:if test="${customer.sex == '2'}">checked="checked"</c:if>>
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
                       	<input type="radio" id="typeCreate1" value="N" name="typeCreate" <c:if test="${customer.type == 'N'}">checked="checked"</c:if>>
                        <label for="typeCreate1"> <spring:message code="customer.type.new" text="!"/> </label>
                   	</div>
                   	<div class="radio radio-primary radio-inline">
                       	<input type="radio" id="typeCreate2" value="G" name="typeCreate" <c:if test="${customer.type == 'G'}">checked="checked"</c:if>>
                       	<label for="typeCreate2"> <spring:message code="customer.type.guarantee" text="!"/> </label>
                   	</div>
				</div>
				<div class="col-sm-4">
					<label><spring:message code="customer.date.birth" text="!"/></label> <label class="text-danger">*</label>
					<fmt:formatDate pattern="dd/MM/yyyy" value="${customer.dateBirth}" var="dateBirth"/>
					<div class="input-group date">
                    	<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                       	<form:input path="dateBirthCreate" value="${dateBirth }" type="text" cssClass="form-control textfield" placeholder="dd/MM/yyyy"/>
					</div>
					<label id="err_dateBirthCreate" class="text-danger"></label>
				</div>
				<div class="col-sm-4">
					<label><spring:message code="customer.telephone" text="!"/></label>
					<form:input path="telephoneCreate" value="${customer.telephone }" type="text" cssClass="form-control textfield"/>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="row">
				<div class="col-sm-4">
					<label><spring:message code="customer.email" text="!"/></label>
					<form:input path="emailCreate" value="${customer.email }" type="text" cssClass="form-control textfield"/>
				</div>
				<div class="col-sm-8">
					<label><spring:message code="customer.address" text="!"/></label><br>
					<form:input path="addressCreate" value="${customer.address }" type="text" cssClass="form-control textfield text-uppercase"/>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-4">
					<label><spring:message code="customer.date.start" text="!"/></label> <label class="text-danger">*</label>
					<fmt:formatDate pattern="dd/MM/yyyy" value="${customer.dateStart}" var="dateStart"/>
					<div class="input-group date">
	                       <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
	                       <form:input path="dateStartCreate" value="${dateStart }" type="text" cssClass="form-control textfield" placeholder="dd/MM/yyyy"/>
					</div>
					<label id="err_dateStartCreate" class="text-danger"></label>
				</div>
				<div class="col-sm-8">
					<label><spring:message code="customer.cause" text="!"/></label>
					<form:input path="causeCreate" value="${customer.cause }" type="text" cssClass="form-control textfield text-uppercase"/>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-4">
					<label><spring:message code="customer.dentist" text="!"/></label>
					<form:select path="dentistCreate" class="chosen-select" cssStyle="width:100%">
						<c:forEach items="${dentists}" var="elm">
							<option value="${elm.name }" <c:if test="${customer.dentist == elm.name}">selected="selected"</c:if>>${elm.name }</option>
						</c:forEach>
					</form:select>
				</div>
				<div class="col-sm-4">
					<label><spring:message code="customer.status" text="!"/></label>
					<form:select path="statusCreate" class="chosen-select" cssStyle="width:100%">
						<option value="W" <c:if test="${customer.status == 'W'}">selected="selected"</c:if>>
							<spring:message code="customer.status.W" text="!"/>
						</option>
						<option value="T" <c:if test="${customer.status == 'T'}">selected="selected"</c:if>>
							<spring:message code="customer.status.T" text="!"/>
						</option>
						<option value="F" <c:if test="${customer.status == 'F'}">selected="selected"</c:if>>
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
					<textarea name="noteCreate" rows="2" class="form-control textfield">${customer.note }</textarea>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-12 text-right">
					<a onclick="javascript:doSubmit('RESET');" class="btn btn-w-m btn-default text-uppercase">
						<i class="fa fa-undo"></i> <spring:message code="button.reset" text="!"/>
					</a>
					<a onclick="doCreate();" class="btn btn-w-m btn-success text-uppercase">
						<i class="fa fa-save"></i> <spring:message code="button.save" text="!"/>
					</a>
				</div>
			</div>
		</div>
	</form:form>
</div>