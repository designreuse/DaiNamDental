<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="row">
	<div class="col-lg-5">
		<label class="label label-success" style="font-size:14px">${customerRealityFilter.rowCount} <spring:message code="message.result" text="!Result(s)"/></label>
	</div>
	<div class="col-lg-7 text-right">
		<c:if test="${customerRealityFilter.totalPage > 1}">
			<div class="form-group">
				<label class="control-label" style="font-weight: 500; font-size: 14px;"><spring:message code="button.page" text="!page"></spring:message>&nbsp;</label>
				<input id="PageNoGo" type="text" style="width:50px; height:30px; vertical-align:middle;border-radius:3px; border: 1px solid #e5e6e7; font-size:14px;" value="${customerRealityFilter.page + 1}">
		        <label class="control-label" style="font-weight: 500; font-size:15px"> /${customerRealityFilter.totalPage}&nbsp;</label>
		        <button id="go" class="btn btn-success btn-sm text-uppercase" type="button">&nbsp;<spring:message code="button.go" text="!Go"/>&nbsp;</button>
		        <div class="btn-group">
			        <c:choose>
				        <c:when test="${customerRealityFilter.page > 0}">
				        	<a id="prev" class="btn btn-sm btn-white text-uppercase" href="--pageNo"><spring:message code="button.prev" text="!Prev."/></a>
				        </c:when>
				        <c:otherwise>
				         	<a class="btn btn-sm btn-white text-uppercase disabled"><spring:message code="button.prev" text="!Prev."/></a>
				        </c:otherwise>
			        </c:choose>
		                        
		                <c:choose>
				            <c:when test="${customerRealityFilter.page < (customerRealityFilter.totalPage - 1)}">
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

<c:set var="formatPattern" value="#,##0"/>
<div class="row">
	<div class="col-sm-12">
		<table class="table table-striped table-bordered table-hover table-result" style="width:100%;">
			<thead>
				<tr>
					<th class="text-center">#</th>
					<th><spring:message code="customer.date.start" text="!"/></th>
					<th class="text-center"><spring:message code="customer.id" text="!"/></th>
					<th><spring:message code="customer.name" text="!"/></th>
					<th><spring:message code="customer.telephone" text="!"/></th>
					<th><spring:message code="records.content" text="!"/></th>
					<th><spring:message code="customer.dentist" text="!"/></th>
					<th class="text-right"><spring:message code="customer.payment" text="!"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${records}" var="elm" varStatus="stt">
					<tr>
						<td class="text-center">${row + stt.index + 1}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${elm.dateExcute}" /></td>
						<td class="text-center">
							<a onclick="doView('VIEW', '${elm.serial}', '${elm.branch}')">${elm.serial }</a>
						</td>
						<td><a onclick="doView('VIEW', '${elm.serial}', '${elm.branch}')">${elm.customer.name}</a></td>
						<td>${elm.customer.telephone}</td>
						<td>${elm.content}</td>
						<td>${elm.dentist}</td>
						<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${elm.payment}"/></td>
					</tr>
				</c:forEach>	
			</tbody>
			<tfoot>
				<tr>
					<th colspan="7" class="text-right"><spring:message code="customer.total" text="!"/></th>
					<th class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${totalPayment}"/></th>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
		
<script type="text/javascript">

	var pageCount = ${customerRealityFilter.totalPage};
	
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
			url : "<spring:url value='/secure/customer/reality/loadpage'/>",
		    data: { pageNo: pageNo },
		    cache : false,
		    success: function (response) {
		        $("#_results").html(response);
		    }
		});
	};
</script>