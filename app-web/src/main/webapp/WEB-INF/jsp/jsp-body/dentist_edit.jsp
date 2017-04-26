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
	$(document).ready(function() {
	    $('.chosen-select').chosen();
	});

	function doEditForm(action, id){
		$.ajax({
			url: "<spring:url value='/secure/dentist/errorEdit.json'/>",
			type: "POST",
			data: $('#form_modify').serialize(),
			 success: function(result){
		        	if(result.errCodeEdit > 0){
			           	 $('#err_nameModify').html('');
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

<form:form name="form_modify" id="form_modify" method="post" commandName="dentistFilter" action="dentist">
	<input name="action" type="hidden" value="MODIFY">
	
	<div class="panel panel-success" style="margin-top:9px;">
         <div class="panel-heading" style="padding: 4px;">
             <div class="row">
                 <div class="col-lg-11">
                     <h3>${dentist.name }</h3>
                 </div>
                 <div class="col-lg-1 text-right">
                     <i class="fa fa-2x fa-times-circle" onclick="javascript:hide('edit-box');" style="cursor:pointer;"></i>
                 </div>
             </div>
         </div>
		
         <div class="panel-body" style="padding:5px;">
			<div class="form-group">
				<div class="row">
					<div class="col-sm-6">
						<label><spring:message code="dentist.name" text="!"/></label>
						<form:hidden path="idModify" value="${dentist.id }"/>
						<form:input path="nameModify" value="${dentist.name }" type="text" cssClass="form-control textfield text-left"/>
						<label id="err_nameModify" class="text-danger"></label>
					</div>
					<div class="col-sm-6">
						<label><spring:message code="dentist.branch" text="!"/></label>
						<form:select path="branchModify" class="chosen-select" cssStyle="width:100%">
							<c:forEach items="${branches}" var="elm">
								<option value="${elm.id }" <c:if test="${elm.id == dentist.branch}">selected="selected"</c:if>>${elm.name }</option>
							</c:forEach>
						</form:select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-12">
						<label><spring:message code="dentist.note" text="!"/></label>
						<textarea name="noteModify" rows="2" class="form-control textfield">${dentist.note }</textarea>
					</div>
				</div>
			</div>
				
			<div class="form-group"> 
				<div class="row">
					<div class="col-lg-12 text-right">
			  			<a onclick="doEditForm('${dentist.id}')" class="btn btn-w-m btn-success text-uppercase">
			  				<i class="fa fa-check"></i> <spring:message code="button.ok" text="!"/>
			  			</a>
					</div>
				</div>
			</div>
         </div>
     </div>
</form:form>