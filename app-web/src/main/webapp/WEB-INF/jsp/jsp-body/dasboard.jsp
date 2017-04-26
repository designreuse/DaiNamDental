<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style type="text/css">
	.sk-spinner-wave div {
	    animation: 1.2s ease-in-out 0s normal none infinite running sk-waveStretchDelay;
	    background-color: #1c84c6;
	    display: inline-block;
	    height: 100%;
	    width: 6px;
	}
</style>

<script type="text/javascript">
	document.getElementById("dashboard").className = "active";

	$(document).ready(function() {
		$("#page-wrapper").removeClass("white-bg");
		$("#page-wrapper").addClass("gray-bg");
	 });

	var pageNo = ${dashboardFilter.page};
	
	$(function(){
	    $.ajax({
	    	url : "<spring:url value='/secure/dashboard/loadpage'/>",
	        data: { pageNo: pageNo },
	        cache : false,
	        success: function (response) {
	            $("#_results").html(response);
	        }
	    }); 
 	   	
	});
</script>

<div id="body">
	<c:set var="now" value="<%=new java.util.Date()%>" />
	<c:set var="formatPattern" value="#,##0"/>
	
	<div class="row">
		<div class="col-sm-12">
		<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5 class="text-uppercase"><spring:message code="dashboard.schedule" text="!"/></h5>
					<div class="pull-right">
						<span class="label label-success"><fmt:formatDate pattern="dd/MM/yyyy" value="${now}" /></span>
					</div>									
				</div>
				<div class="ibox-content">
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
		</div>
	</div>
</div>