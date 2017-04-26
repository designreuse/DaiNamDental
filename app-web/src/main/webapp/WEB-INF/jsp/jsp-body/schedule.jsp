<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

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
    .sk-spinner-wave div {
	    animation: 1.2s ease-in-out 0s normal none infinite running sk-waveStretchDelay;
	    background-color: #1c84c6;
	    display: inline-block;
	    height: 100%;
	    width: 6px;
	}
</style>

<script type="text/javascript">
	document.getElementById("customer").className = "active";
	document.getElementById("schedule").className = "active";

	var pageNo = ${scheduleFilter.page};
	
	$(function(){
	    $.ajax({
	    	url : "<spring:url value='/secure/schedule/loadpage'/>",
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

	    $('.chosen-select').chosen();
	});
</script>

<div id="body">
	<form:form name="form" id="form" method="post" commandName="scheduleFilter">
		<input name="action" type="hidden">
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-3">
					<label><spring:message code="schedule.serial" text="!"/></label>
					<form:input path="serial" class="form-control textfield"/>
				</div>
				<div class="col-sm-3">
					<label><spring:message code="schedule.name" text="!"/></label>
					<form:input path="name" class="form-control textfield"/>
				</div>
				<div class="col-sm-3">
					<label><spring:message code="schedule.date" text="!"/></label>
					<div class="input-group date">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <form:input path="dateNextFrom" type="text" cssClass="form-control textfield" placeholder="dd/MM/yyyy"/>
					</div>
				</div>
				<div class="col-sm-3">
					<label>&nbsp;</label>
					<div class="input-group date">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        <form:input path="dateNextTo" type="text" cssClass="form-control textfield" placeholder="dd/MM/yyyy"/>
					</div>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-3">
					<label><spring:message code="schedule.telephone" text="!"/></label>
					<form:input path="telephone" class="form-control textfield"/>
				</div>
				<div class="col-sm-3">
					<label><spring:message code="schedule.dentist" text="!"/></label>
					<form:select path="dentist" class="chosen-select" cssStyle="width:100%">
						<option value="" <c:if test="${scheduleFilter.dentist == ''}">selected="selected"</c:if>>
							<spring:message code="commom.all" text="!"/>
						</option>
						<c:forEach items="${dentists}" var="elm">
							<option value="${elm.name }" <c:if test="${scheduleFilter.dentist == elm.name}">selected="selected"</c:if>>${elm.name }</option>
						</c:forEach>
					</form:select>
				</div>
				
				<sec:authorize access="hasRole('ADMIN')">
					<div class="col-sm-3">
						<label><spring:message code="schedule.branch" text="!"/></label>
						<form:select path="branch" class="chosen-select" cssStyle="width:100%">
							<option value="" <c:if test="${'' == scheduleFilter.branch}">selected="selected"</c:if>>
								<spring:message code="commom.all" text="!"/>
							</option>
							<c:forEach items="${branches}" var="elm">
								<option value="${elm.id}" <c:if test="${elm.id == scheduleFilter.branch}">selected="selected"</c:if>>${elm.name }</option>
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
				<div id="_results">
					<div class="sk-spinner sk-spinner-wave" id="wait">
                        <div class="sk-rect1"></div>
                        <div class="sk-rect2"></div>
                        <div class="sk-rect3"></div>
                        <div class="sk-rect4"></div>
                        <div class="sk-rect5"></div>
                    </div>
				</div>
			</div>
		</div>
	</form:form>
</div>