<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="row">
	<div class="col-lg-5">
		<label class="label label-success" style="font-size:14px">${dentistFilter.rowCount} <spring:message code="message.result" text="!Result(s)"/></label>
	</div>
	<div class="col-lg-7 text-right">
		<c:if test="${dentistFilter.totalPage > 1}">
			<div class="form-group">
				<label class="control-label" style="font-weight: 500; font-size: 14px;"><spring:message code="button.page" text="!page"></spring:message>&nbsp;</label>
				<input id="PageNoGo" type="text" style="width:50px; height:30px; vertical-align:middle;border-radius:3px; border: 1px solid #e5e6e7; font-size:14px;" value="${dentistFilter.page + 1}">
		        <label class="control-label" style="font-weight: 500; font-size:15px"> /${dentistFilter.totalPage}&nbsp;</label>
		        <button id="go" class="btn btn-success btn-sm text-uppercase" type="button">&nbsp;<spring:message code="button.go" text="!Go"/>&nbsp;</button>
		        <div class="btn-group">
			        <c:choose>
				        <c:when test="${dentistFilter.page > 0}">
				        	<a id="prev" class="btn btn-sm btn-white text-uppercase" href="--pageNo"><spring:message code="button.prev" text="!Prev."/></a>
				        </c:when>
				        <c:otherwise>
				         	<a class="btn btn-sm btn-white text-uppercase disabled"><spring:message code="button.prev" text="!Prev."/></a>
				        </c:otherwise>
			        </c:choose>
		                        
		                <c:choose>
				            <c:when test="${dentistFilter.page < (dentistFilter.totalPage - 1)}">
				            	<a id="next" class="btn btn-sm btn-white text-uppercase" href="++pageNo"><spring:message code="button.next" text="!Next"/></a>
				                </c:when>
				                <c:otherwise>
				                	<a class="btn btn-sm btn-white text-uppercase disabled"><spring:message code="button.next" text="!Next"/></a>
				                </c:otherwise>
			            </c:choose>
		          </div>
			</div>
		</c:if>
	</div>
</div>

<div class="row">&nbsp;</div>
<div class="row">
	<div class="col-sm-12">
		<table class="table table-striped table-bordered table-hover table-lta" style="width:100%;">
			<thead>
				<tr>
					<th class="text-center">#</th>
					<th class="text-center"><spring:message code="commom.action" text="!Action"/></th>
					<th><spring:message code="dentist.name" text="!"/></th>
					<th><spring:message code="dentist.branch" text="!"/></th>
					<th><spring:message code="dentist.note" text="!"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dentists}" var="elm" varStatus="stt">
					<tr id="${elm.id}">
						<td class="text-center">${row + stt.index + 1}</td>
						<td class="text-center text-nowrap">
							<a id='a-edit-${elm.id}' onclick='bfShow("edit-box","a-edit-${elm.id}", "${elm.id}"); showForm("edit-box", "${elm.id}")' 
								title="<spring:message code="message.modify" text="!Edit"/>"><i class="fa fa-2x fa-edit"></i>
							</a>
							<a onclick="ConformDelete('${elm.id}', '${elm.name}')" title="<spring:message code="button.delete" text="!Delete"/>"><i class="fa fa-2x fa-trash-o"></i></a>
						</td>
						<td>${fn:trim(elm.name)}</td>
						<td>${elm.branch}</td>
						<td>${elm.note}</td>
					</tr>
				</c:forEach>	
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">

	var pageCount = ${dentistFilter.totalPage};
	
	$(function () {
		$("#prev").click(function () {
		    var href = $(this).attr("href");
		    eval("pageNo = (pageCount + " + href + " ) % pageCount");
		    fnLoadPage();
		    return false;
		});
		
		$("#next").click(function () {
		    var href = $(this).attr("href");
		    eval("pageNo = (pageCount + " + href + " ) % pageCount");
		    fnLoadPage();
		    return false;
		});
		$("#go").click(function () {
		    var href = $("#PageNoGo").val() - 1;
		
		    if (href < 1 || isNaN(href)) {
		        eval("pageNo = (pageCount + " + 0 + " ) % pageCount");
		    } else if (href > pageCount - 1) {
		        eval("pageNo = (pageCount + " + (pageCount - 1) + " ) % pageCount");
		    } else {
		        eval("pageNo = (pageCount + " + href + " ) % pageCount");
		    }
		    fnLoadPage();
		    return false;
		});
		
	});
		
	function fnLoadPage() {
		$.ajax({
			url : "<spring:url value='/secure/dentist/loadpage'/>",
		    data: { pageNo: pageNo },
		    cache : false,
		    success: function (response) {
		        $("#_results").html(response);
		    }
		});
	};
</script>
