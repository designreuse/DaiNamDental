<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<script type="text/javascript">
	document.getElementById("invoice").className = "active";
	document.getElementById("bill").className = "active";

	var pageNo = ${billFilter.page};
	$(function(){

		$.ajax({
	    	url : "<spring:url value='/secure/bill/loadpage'/>",
	        data: { pageNo: pageNo },
	        cache : false,
	        success: function (response) {
	            $("#_results").html(response);
	        }
	    });
	    
	    $('.input-group.date').datepicker({
	    	todayHighlight: true,
			todayBtn: "linked",
            calendarWeeks: false,
            autoclose: true,
			format: "dd/mm/yyyy"
	    });

	    $(".chosen-select").chosen({
	    	  "disable_search": true
		});
	});

</script>

<div id="body">
	<c:set var="formatPattern" value="#,##0"/>

	<form:form id="form" name="form" method="post" commandName="billFilter">
		<input type="hidden" name="action">
		
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
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-4">
					<label><spring:message code="invoice.date.from" text="!"/></label>
					<div class="input-group date">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <form:input path="dateFrom" type="text" cssClass="form-control textfield" placeholder="dd/MM/yyyy"/>
					</div>
				</div>
				<div class="col-sm-4">
					<label><spring:message code="invoice.date.to" text="!"/></label>
					<div class="input-group date">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <form:input path="dateTo" type="text" cssClass="form-control textfield" placeholder="dd/MM/yyyy"/>
					</div>
				</div>
				<sec:authorize access="hasRole('ADMIN')">
					<div class="col-sm-4">
						<label><spring:message code="user.branch" text="!"/></label>
						<form:select path="branch" class="chosen-select" cssStyle="width:100%">
							<option value="" <c:if test="${'' == billFilter.branch}">selected="selected"</c:if>>
								<spring:message code="commom.all" text="!"/>
							</option>
							<c:forEach items="${branches}" var="elm">
								<option value="${elm.id}" <c:if test="${elm.id == billFilter.branch}">selected="selected"</c:if>>${elm.name }</option>
							</c:forEach>
						</form:select>
					</div>
				</sec:authorize>
			</div>
		</div>
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-12 text-right">
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
</div>