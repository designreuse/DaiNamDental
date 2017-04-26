<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script type="text/javascript">
	function doEditForm(id){
		$.ajax({
			url: "<spring:url value='/secure/branches/edit/error.json'/>",
			type: "POST",
			data: $('#form_modify').serialize(),
			 success: function(result){
		        	if(result.errCodeEdit > 0){
			           	 $('#err_id').html('');
			           	 $('#err_name').html('');
			           	 $('#err_manager').html('');
			           	 $('#err_fullName').html('');
			           	 
			           	 var len = $.map(result.lstErr, function(n, i) { return i; }).length;
			           	 for(var i=0;i<len;i++)
		               	 {
		                    $('#err_'+result.lstErr[i].propertyName).html(result.lstErr[i].message); 
		              	 }
		        	}else{
		        		$('#form_modify').submit();
		        	}
		        },
			 	error: function(XMLHttpRequest, textStatus, errorThrown){}
		});
	}
</script>

<form:form name="form_modify" id="form_modify" method="post" commandName="branchesFilter" action="branches">
	<input name="action" type="hidden" value="MODIFY">
	
	<div class="panel panel-success" style="margin-top:9px;">
         <div class="panel-heading" style="padding: 4px;">
             <div class="row">
                 <div class="col-lg-11">
                     <h3>${branches.name }</h3>
                 </div>
                 <div class="col-lg-1 text-right">
                     <i class="fa fa-2x fa-times-circle" onclick="javascript:hide('edit-box');" style="cursor:pointer;"></i>
                 </div>
             </div>
         </div>
		
         <div class="panel-body" style="padding:5px;">
           	<div class="form-group">
				<div class="row">
					<div class="col-lg-4">
						<label><spring:message code="branches.id" text="!"/></label>
						<form:input path="id" value="${fn:trim(branches.id)}" readonly="true" class="form-control textfield"></form:input>
						<label id="err_id" class="text-danger"></label>
					</div>
					<div class="col-lg-4">
						<label><spring:message code="branches.name" text="!"/></label>
						<form:input path="name" value="${fn:trim(branches.name)}" class="form-control textfield"></form:input>
						<label id="err_name" class="text-danger"></label>
					</div>
					<div class="col-lg-4">
						<label><spring:message code="branches.manager" text="!"/></label>
						<form:input path="manager" value="${fn:trim(branches.manager)}" class="form-control textfield"></form:input>
						<label id="err_manager" class="text-danger"></label>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<div class="row">
					<div class="col-sm-12">
						<label><spring:message code="branches.full.name" text="!Role name"/></label>
						<form:input path="fullName" value="${fn:trim(branches.fullName)}" type="text" cssClass="form-control textfield"/>
						<label id="err_fullName" class="text-danger"></label>
					</div>
				</div>
			</div>
						
			<div class="form-group">
				<div class="row">
					<div class="col-lg-4">
						<label><spring:message code="branches.telephone" text="!"/></label>
						<form:input path="telephone" value="${fn:trim(branches.telephone)}" class="form-control textfield"></form:input>
					</div>
					<div class="col-lg-8">
						<label><spring:message code="branches.address" text="!"/></label>
						<form:input path="address" value="${fn:trim(branches.address)}" class="form-control textfield"></form:input>
					</div>
				</div>
			</div>
				 
			<div class="row">
				<div class="col-lg-12 text-right">
		  			<a onclick="doEditForm('${branches.id}')" class="btn btn-w-m btn-success text-uppercase">
		  				<i class="fa fa-check"></i> <spring:message code="button.edit" text="!"/>
		  			</a>
				</div>
			</div>
         </div>
     </div>
</form:form>