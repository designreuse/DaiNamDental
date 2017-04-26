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

<link href="<c:url value="/static/inspinia/css/plugins/sweetalert/sweetalert.css"/>" rel="stylesheet">
<script src="<c:url value="/static/inspinia/js/plugins/sweetalert/sweetalert.min.js"/>"></script>

<script type="text/javascript">
	document.getElementById("param").className = "active";
	document.getElementById("param_dentist").className = "active";

	$(document).ready(function() {
	    $('.chosen-select').chosen({disable_search_threshold: 10});
	});

	var pageNo = ${dentistFilter.page};
	
	$(function(){
	    $.ajax({
	    	url : "<spring:url value='/secure/dentist/loadpage'/>",
	        data: { pageNo: pageNo },
	        cache : false,
	        success: function (response) {
	            $("#_results").html(response);
	        }
	    });
	});

	function doCreate(){
		$.ajax({
			url: "<spring:url value='/secure/dentist/errorCreate.json'/>",
	        data: $('#form-add').serialize(), 
	        type: "POST",
	        success: function(result){
	        	if(result.errCodeCreate > 0){
		        	$('#err_nameCreate').html('');
	           	 	var len = $.map(result.lstErr, function(n, i) { return i; }).length;
	           	 	for(var i=0;i<len;i++)
               	 	{
                		$('#err_'+result.lstErr[i].propertyName).html(result.lstErr[i].message); 
              	 	}
	        	}else{
	        		$('#form-add').submit();
	        	}
	        },
		 	error: function(XMLHttpRequest, textStatus, errorThrown){}
		});
	};

	function ConformDelete(id, name) {
	    swal({
	        title: "<spring:message code="message.conform.delete" text="!"/>" + " [" + name + "] ?",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "<spring:message code="button.delete" text="!"/>",
	        cancelButtonText: "<spring:message code="button.cancel" text="!"/>",
	        closeOnConfirm: false
	      },function () {
	   	 	 document.forms[0].elements['action'].value='DELETE';
	   	 	 document.forms[0].elements['id'].value=id;
	   	 	document.forms[0].submit();
	    });
	};

	function showForm(divId, id) {
 	    $.ajax({
 	        url: "<spring:url value='/secure/dentist/edit/'/>" + id,
 	        cache: false,
 	        success: function (reponse) {
 	            $("div#edit-result").html(reponse);
 	        }
 	    });
 	    show(divId);
 	}
</script>

<div id="body">
	<form:form name="form" id="form" method="post" commandName="dentistFilter">
		<input name="action" type="hidden">
		<input name="id" type="hidden">
		
		<div id="edit-box" class="boxcss" style="display: block;">
			<div id="edit-result"></div>
		</div>
		
		<div class="form-group">
			<div class="row">
				<div class="col-lg-4">
					<label><spring:message code="dentist.name" text="!"/></label>
					<form:input path="name" class="form-control textfield"/>
				</div>
				<div class="col-lg-4">
					<label><spring:message code="dentist.branch" text="!"/></label>
					<form:select path="branch" class="chosen-select" cssStyle="width:100%">
						<option value="" <c:if test="${'' == dentistFilter.branch}">selected="selected"</c:if>><spring:message code="commom.all" text="!"/></option>
						<c:forEach items="${branches}" var="elm">
							<option value="${elm.id }" <c:if test="${elm.id == dentistFilter.branch}">selected="selected"</c:if>>${elm.name }</option>
						</c:forEach>
					</form:select>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<div class="row">
				<div class="col-lg-4">
					<button type="button" class="btn btn-w-m btn-success text-uppercase" data-toggle="modal" data-target="#formCreate">
						<i class="fa fa-plus-square"> <spring:message code="button.add" text="!"></spring:message></i>
					</button>
				</div>
				<div class="col-lg-8 text-right">
					<a onclick="javascript:doSubmit('RESET');" class="btn btn-w-m btn-default text-uppercase">
						<i class="fa fa-undo"></i> <spring:message code="button.reset" text="!"/>
					</a>
					<a onclick="javascript:doSubmit('GO');" class="btn btn-w-m btn-success text-uppercase">
						<i class="fa fa-search"></i> <spring:message code="button.search" text="!"/>
					</a>
				</div>
			</div>
		</div>
		
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col-lg-12">
				<div id="_results"></div>
			</div>
		</div>
	</form:form>
	
	<!-- ------------------------------------------- CREATE ----------------------------------------------------- -->
	<div class="modal inmodal" id="formCreate" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width:800px">
			<div class="modal-content animated bounceInRight">
				<div class="modal-body">
					<form:form id="form-add" name="form-add" method="post" commandName="dentistFilter" action="dentist">
						<input type="hidden" name="action" value="CREATE">
						
						<div class="form-group">
							<div class="row">
								<div class="col-sm-6">
									<label><spring:message code="dentist.name" text="!"/></label>
									<form:input path="nameCreate" type="text" cssClass="form-control textfield text-left"/>
									<label id="err_nameCreate" class="text-danger"></label>
								</div>
								<div class="col-sm-6">
									<label><spring:message code="dentist.branch" text="!"/></label>
									<form:select path="branchCreate" class="chosen-select" cssStyle="width:100%">
										<c:forEach items="${branches}" var="elm">
											<option value="${elm.id }">${elm.name }</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<label><spring:message code="dentist.note" text="!"/></label>
									<form:textarea path="noteCreate" rows="3" class="form-control textfield"/>
								</div>
							</div>
						</div>
					</form:form>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-w-m btn-default text-uppercase" data-dismiss="modal">
						<i class="fa fa-close"></i> <spring:message code="button.close" text="!"/>
					</button>
					<button type="button" onclick="doCreate();" class="btn btn-w-m btn-success text-uppercase">
						<i class="fa fa-plus-square "></i> <spring:message code="button.add" text="!"/>
					</button>
				</div>
			</div>
		</div>
	</div>
</div>