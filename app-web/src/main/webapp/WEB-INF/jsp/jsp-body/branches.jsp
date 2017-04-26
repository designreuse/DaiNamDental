<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<link href="<c:url value="/static/inspinia/css/plugins/sweetalert/sweetalert.css"/>" rel="stylesheet">
<script src="<c:url value="/static/inspinia/js/plugins/sweetalert/sweetalert.min.js"/>"></script>

<script type="text/javascript">
	document.getElementById("param").className = "active";
	document.getElementById("param_branch").className = "active";

	function doCreate(){
		$.ajax({
			url: "<spring:url value='/secure/branches/error.json'/>",
	        data: $('#form-add').serialize(), 
	        type: "POST",
	        success: function(result){
	        	if(result.errCodeCreate > 0){
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
	   	 	 document.forms[0].elements['id_branch'].value=id;
	   	 	document.forms[0].submit();
	    });
	};

	function showFormEdit(divId, id){
		$.ajax({
			url: "<spring:url value='/secure/branches/edit/'/>" + id,
			cache: false,
			success: function(reponse){
				$("div#edit-box").html(reponse);
			}
		});
		show(divId);
	}
	
</script>

<div id="body">
	<form:form id="form" method="post">
		<input name="action" type="hidden">
		<input name="id_branch" type="hidden">
		
		<div id="edit-box" class="boxcss" style="display: block;">
			<div id="edit-result"></div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<button type="button" class="btn btn-w-m btn-success text-uppercase" data-toggle="modal" data-target="#formCreate">
					<i class="fa fa-plus-square"> <spring:message code="button.add" text="!Create Role"></spring:message></i>
				</button>
			</div>
		</div>
		&nbsp;
		<table class="table table-striped table-bordered table-hover table-result" style="width:100%;">
			<thead>
				<tr>
					<th class="text-center">#</th>
					<th class="text-center"><spring:message code="commom.action" text="!No"/></th>
					<th><spring:message code="branches.id" text="!"/></th>
					<th><spring:message code="branches.name" text="!"/></th>
					<th><spring:message code="branches.manager" text="!"/></th>
					<th><spring:message code="branches.telephone" text="!"/></th>
					<th><spring:message code="branches.address" text="!"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${branches}" var="elm" varStatus="stt">
				
					<tr id="${elm.id}">
						<td class="text-center">${stt.index + 1 }</td>
						<td class="text-center">
							<a id='a-edit-${elm.id}' onclick='bfShow("edit-box", "a-edit-${elm.id }", "${elm.id }"); showFormEdit("edit-box", "${elm.id }")'
								title="<spring:message code="message.edit" text="!Edit"/>"><i class="fa fa-2x fa-edit"></i>
							</a>
							<a onClick="ConformDelete('${elm.id}', '${elm.name }')"
								title="<spring:message code="message.delete" text="!"/>"><i class="fa fa-2x fa-trash-o"></i>
							</a>
						</td>
						<td>${elm.id }</td>
						<td>${elm.name }</td>
						<td>${elm.manager }</td>
						<td>${elm.telephone }</td>
						<td>${elm.address }</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	</form:form>
	<div class="modal inmodal" id="formCreate" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog" style="width:800px">
			<div class="modal-content animated bounceInRight">
				<div class="modal-body">
					<form:form id="form-add" name="form-add" method="post" commandName="branchesFilter" action="branches">
						<input type="hidden" name="action" value="CREATE">
						
						<div class="form-group">
							<div class="row">
								<div class="col-sm-4">
									<label><spring:message code="branches.id" text="!Role name"/></label>
									<form:input path="id" type="text" cssClass="form-control textfield text-left"/>
									<label id="err_id" class="text-danger"></label>
								</div>
								<div class="col-sm-4">
									<label><spring:message code="branches.name" text="!Detail"/></label>
									<form:input path="name" type="text" cssClass="form-control textfield"/>
									<label id="err_name" class="text-danger"></label>
								</div>
								<div class="col-sm-4">
									<label><spring:message code="branches.manager" text="!Detail"/></label>
									<form:input path="manager" type="text" cssClass="form-control textfield"/>
									<label id="err_manager" class="text-danger"></label>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12">
									<label><spring:message code="branches.full.name" text="!Role name"/></label>
									<form:input path="fullName" type="text" cssClass="form-control textfield"/>
									<label id="err_fullName" class="text-danger"></label>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<div class="row">
								<div class="col-sm-4">
									<label><spring:message code="branches.telephone" text="!Role name"/></label>
									<form:input path="telephone" type="text" cssClass="form-control textfield"/>
								</div>
								<div class="col-sm-8">
									<label><spring:message code="branches.address" text="!Detail"/></label>
									<form:input path="address" type="text" cssClass="form-control textfield"/>
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