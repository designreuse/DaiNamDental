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
	$(function () {
	    $(".chosen-select").chosen({
	    	  "disable_search": true
		});
	});

	function doEditForm(action, id){
		$.ajax({
			url: "<spring:url value='/secure/user/errorEdit.json'/>",
			type: "POST",
			cache: false,
			data: $('#form_modify').serialize(),
			success: function(result){
				if(result.errCodeEdit > 0){
	           	 	$('#err_passwordModify').html('');
	           	 	$('#err_retypePasswordModify').html('');
	           		var len = $.map(result.lstErr, function(n, i) { return i; }).length;
	           	 	for(var i=0;i<len;i++){
	                   $('#err_'+result.lstErr[i].propertyName).html(result.lstErr[i].message); 
					}
	       		} else {
	       			$('#form_modify').submit();	
	       		}
			}
		});
	}

</script>

<form:form name="form_modify" id="form_modify" method="post" modelAttribute="userFilter" action="user">
	<input name="action" type="hidden" value="MODIFY">
   		
	<div class="form-group">
		<div class="row">
			<div class="col-sm-4">
				<label><spring:message code="user.username" text="!"/></label>
				<form:input path="usernameModify" value="${sysUser.username }" readonly="true" type="text" cssClass="form-control textfield"/>
			</div>
			<div class="col-sm-4">
				<label><spring:message code="user.password" text="!"/></label>
				<form:input path="passwordModify" value="${sysUser.password }" type="password" cssClass="form-control textfield"/>
				<label id="err_passwordModify" class="text-danger"></label>
			</div>
			<div class="col-sm-4">
				<label><spring:message code="user.retype.password" text="!"/></label>
				<form:input path="retypePasswordModify" value="${sysUser.password }" type="password" cssClass="form-control textfield"/>
				<label id="err_retypePasswordModify" class="text-danger"></label>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="row">
			<div class="col-sm-4">
				<label><spring:message code="user.name" text="!"/></label>
				<form:input path="nameModify" value="${sysUser.name }" type="text" cssClass="form-control textfield"/>
			</div>
			<div class="col-sm-4">
				<label><spring:message code="user.telephone" text="!"/></label>
				<form:input path="telephoneModify" value="${sysUser.telephone }" type="text" cssClass="form-control textfield"/>
			</div>
			<div class="col-sm-4">
				<label><spring:message code="user.email" text="!"/></label>
				<form:input path="emailModify" value="${sysUser.email }" type="text" cssClass="form-control textfield"/>
			</div>
		</div>
		
	</div>
	<div class="form-group">
		<div class="row">
			<div class="col-sm-4">
				<label><spring:message code="user.role" text="!"/></label>
				<form:select path="roleModify" class="chosen-select" cssStyle="width:100%">
					<option value="ADMIN" <c:if test="${sysUser.role == 'ADMIN'}"> selected="selected"</c:if>>ADMIN</option>
					<option value="USER" <c:if test="${sysUser.role == 'USER'}"> selected="selected"</c:if>>USER</option>
				</form:select>
			</div>
			<div class="col-sm-4">
				<label><spring:message code="user.branch" text="!"/></label>
				<form:select path="branchModify" class="chosen-select" cssStyle="width:100%">
					<c:forEach items="${branches}" var="elm">
						<option value="${elm.id}" <c:if test="${sysUser.branch == elm.id}"> selected="selected"</c:if>>${elm.name }</option>
					</c:forEach>
				</form:select>
			</div>
		</div>
	</div>
		                           
	<div class="form-group">
		<div class="row">
			<div class="col-lg-12 text-right">
				<button type="button" class="btn btn-w-m btn-default text-uppercase" data-dismiss="modal">
					<i class="fa fa-close"></i> <spring:message code="button.close" text="!Close"/>
				</button>
	  			<a onclick="doEditForm('MODIFY','${user.sysUserId}')" class="btn btn-w-m btn-success text-uppercase" type="submit">
	  				<i class="fa fa-check"></i> <spring:message code="button.edit" text="!Go"/>
	  			</a>
			</div>
		</div>
	</div>	
</form:form>