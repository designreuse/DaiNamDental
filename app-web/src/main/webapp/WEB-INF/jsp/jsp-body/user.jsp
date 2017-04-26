<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link href="<c:url value="/static/inspinia/css/plugins/sweetalert/sweetalert.css"/>" rel="stylesheet">
<script src="<c:url value="/static/inspinia/js/plugins/sweetalert/sweetalert.min.js"/>"></script>

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
	document.getElementById("param").className = "active";
	document.getElementById("param_user").className = "active";

	$(function(){
		fnLoadPage();

	    $(".chosen-select").chosen({
	    	  "disable_search": true
		});
	});
	
	function fnLoadPage() {
		$.ajax({
			url : "<spring:url value='/secure/user/loadpage'/>",
		    cache : false,
		    success: function (response) {
		        $("#_results").html(response);
		    }
		});
	};
	
	function doCreate(){
		$.ajax({
			url: "<spring:url value='/secure/user/errorCreate.json'/>",
			type: "POST",
			cache: false,
			data: $('#form-add').serialize(),
			success: function(result){
				if(result.errCodeCreate > 0){
		           	 $('#err_usernameCreate').html('');
		           	 $('#err_passwordCreate').html('');
		           	 $('#err_retypePasswordCreate').html('');
		           	 var len = $.map(result.lstErr, function(n, i) { return i; }).length;
		           	 for(var i=0;i<len;i++) {
	                    $('#err_'+result.lstErr[i].propertyName).html(result.lstErr[i].message); 
	              	 }
	        	}else{
	        		$('#form-add').submit();
	        	}
			}
		});
	}
	
	function doEditUser(id) {
 	    $.ajax({ 
 	     	url: "<spring:url value='/secure/user/model/'/>" + id,
 	        cache: false,
 	        success: function (reponse) {
 	            $("#modify-result").html(reponse);
 	        }
 	    });
 	}
	
	function doAddButton(userId){
		$.ajax({
			url : "<spring:url value='/secure/user/add/" + userId +"'/>",
			type : "POST",
			cache: false,
			data : $('#form_add_roleuser').serialize(),
			success: function(reponse){
				$("#modify-result").html(reponse);
				fnLoadPage();
			}
		});
	}
	
	function ConformDelete(id) {
	    swal({
	        title: "<spring:message code="message.conform.delete" text="!"/>" + " [" + id +"] ?",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "<spring:message code="button.delete" text="!"/>",
	        cancelButtonText: "<spring:message code="button.cancel" text="!"/>",
	        closeOnConfirm: false
	    }, function () {
        	 document.forms[0].elements['action'].value="DELETE";
			 document.forms[0].elements['id'].value=id;
			 document.forms[0].submit();
	    });
	};
	
</script>

<div id="body">
	<form:form id="form" name="form" method="post" modelAttribute="userFilter">
		<input name="action" type="hidden">
		<input name="id" type="hidden">
		
		<div class="form-group">
			<div class="row">
				<div class="col-sm-4">
					<label><spring:message code="user.username" text="!Name"/></label>
					<form:input path="username" cssClass="form-control"/>
				</div>
				<div class="col-sm-4">
					<label><spring:message code="user.role" text="!"/></label>
					<form:select path="role" class="chosen-select" cssStyle="width:100%">
						<option value="" <c:if test="${'' == userFilter.branch}">selected="selected"</c:if>>
							<spring:message code="commom.all" text="!"/>
						</option>
						<option value="ADMIN" <c:if test="${'ADMIN' == userFilter.role}">selected="selected"</c:if>>ADMIN</option>
						<option value="USER" <c:if test="${'USER' == userFilter.role}">selected="selected"</c:if>>USER</option>
					</form:select>
				</div>
				<div class="col-sm-4">
					<label><spring:message code="user.branch" text="!"/></label>
					<form:select path="branch" class="chosen-select" cssStyle="width:100%">
						<option value="" <c:if test="${'' == userFilter.branch}">selected="selected"</c:if>>
							<spring:message code="commom.all" text="!"/>
						</option>
						<c:forEach items="${branches}" var="elm">
							<option value="${elm.id}" <c:if test="${elm.id == userFilter.branch}">selected="selected"</c:if>>${elm.name }</option>
						</c:forEach>
					</form:select>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-6">
				<button type="button" class="btn btn-w-m btn-success text-uppercase" data-toggle="modal" data-target="#formCreate">
					<i class="fa fa-plus-square"> <spring:message code="button.add" text="!"/></i>
				</button>
			</div>
			<div class="col-sm-6 text-right">
				<button type="button" onclick="javascript:doSubmit('GO');" type="button" class="btn btn-w-m btn-success text-uppercase">
					<i class="fa fa-search"></i> <spring:message code="button.search" text="!"/>
				</button>		
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">&nbsp;</div>
			<div class="col-sm-12">
				<div id="_results"></div>
			</div>
		</div>
	</form:form>

	<!-- ---------------------------------------------CREATE--------------------------------------------------- -->
	<div class="modal inmodal" id="formCreate" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width:800px">
			<div class="modal-content animated bounceInRight">
				<div class="modal-body">
					<form:form id="form-add" name="form-add" method="post" commandName="userFilter" action="user">
						<input type="hidden" name="action" value="CREATE">	
						
						<div class="form-group">
							<div class="row">
								<div class="col-sm-4">
									<label><spring:message code="user.username" text="!"/></label>
									<form:input path="usernameCreate" type="text" cssClass="form-control textfield"/>
									<label id="err_usernameCreate" class="text-danger"></label>
								</div>
								<div class="col-sm-4">
									<label><spring:message code="user.password" text="!"/></label>
									<form:input path="passwordCreate" type="password" cssClass="form-control textfield"/>
									<label id="err_passwordCreate" class="text-danger"></label>
								</div>
								<div class="col-sm-4">
									<label><spring:message code="user.retype.password" text="!"/></label>
									<form:input path="retypePasswordCreate" type="password" cssClass="form-control textfield"/>
									<label id="err_retypePasswordCreate" class="text-danger"></label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-sm-4">
									<label><spring:message code="user.name" text="!"/></label>
									<form:input path="nameCreate" type="text" cssClass="form-control textfield"/>
								</div>
								<div class="col-sm-4">
									<label><spring:message code="user.telephone" text="!"/></label>
									<form:input path="telephoneCreate" type="text" cssClass="form-control textfield"/>
								</div>
								<div class="col-sm-4">
									<label><spring:message code="user.email" text="!"/></label>
									<form:input path="emailCreate" type="text" cssClass="form-control textfield"/>
								</div>
							</div>
							
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-sm-4">
									<label><spring:message code="user.role" text="!"/></label>
									<form:select path="roleCreate" class="chosen-select" cssStyle="width:100%">
										<option value="ADMIN">ADMIN</option>
										<option selected="selected" value="USER">USER</option>
									</form:select>
								</div>
								<div class="col-sm-4">
									<label><spring:message code="user.branch" text="!"/></label>
									<form:select path="branchCreate" class="chosen-select" cssStyle="width:100%">
										<c:forEach items="${branches}" var="elm">
											<option value="${elm.id}">${elm.name }</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</form:form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-w-m btn-default text-uppercase" data-dismiss="modal">
						<i class="fa fa-close"></i> <spring:message code="button.close" text="!Close"/>
					</button>
					<button type="button" onclick="doCreate();" class="btn btn-w-m btn-success text-uppercase">
						<i class="fa fa-plus-square "></i> <spring:message code="button.add" text="!Create"/>
					</button>
				</div>
				
			</div>
		</div>
	</div>
	
	<!-- --------------------------------------------EDIT---------------------------------------------------- -->
	<div class="modal inmodal" id="formEdit" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width:800px">
			<div class="modal-content animated bounceInRight">
				<div class="modal-body">
					<div id="modify-result"></div>
				</div>
			</div>
		</div>
	</div>
</div>