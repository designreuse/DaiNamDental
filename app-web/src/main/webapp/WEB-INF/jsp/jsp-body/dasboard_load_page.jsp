<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="row">
	<div class="col-lg-5">
		<label class="label label-success" style="font-size:14px">${dashboardFilter.rowCount} <spring:message code="message.result" text="!Result(s)"/></label>
	</div>
	<div class="col-lg-7 text-right">
		<c:if test="${dashboardFilter.totalPage > 1}">
			<div class="form-group">
				<label class="control-label" style="font-weight: 500; font-size: 14px;"><spring:message code="button.page" text="!page"></spring:message>&nbsp;</label>
				<input id="PageNoGo" type="text" style="width:50px; height:30px; vertical-align:middle;border-radius:3px; border: 1px solid #e5e6e7; font-size:14px;" value="${dashboardFilter.page + 1}">
		        <label class="control-label" style="font-weight: 500; font-size:15px"> /${dashboardFilter.totalPage}&nbsp;</label>
		        <button id="go" class="btn btn-success btn-sm text-uppercase" type="button">&nbsp;<spring:message code="button.go" text="!Go"/>&nbsp;</button>
		        <div class="btn-group">
			        <c:choose>
				        <c:when test="${dashboardFilter.page > 0}">
				        	<a id="prev" class="btn btn-sm btn-white text-uppercase" href="--pageNo"><spring:message code="button.prev" text="!Prev."/></a>
				        </c:when>
				        <c:otherwise>
				         	<a class="btn btn-sm btn-white text-uppercase disabled"><spring:message code="button.prev" text="!Prev."/></a>
				        </c:otherwise>
			        </c:choose>
		                        
		                <c:choose>
				            <c:when test="${dashboardFilter.page < (dashboardFilter.totalPage - 1)}">
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

<div class="row">
	<div class="col-lg-12">
		<table class="table table-striped table-bordered table-hover table-result" style="width:100%;">
			<thead>
				<tr>
					<th class="text-center">#</th>
					<th><spring:message code="schedule.serial" text="!"/></th>
					<th><spring:message code="schedule.name" text="!"/></th>
					<th><spring:message code="schedule.telephone" text="!"/></th>
					<th><spring:message code="schedule.content" text="!"/></th>
					<th><spring:message code="schedule.content.next" text="!"/></th>
					<th><spring:message code="schedule.dentist" text="!"/></th>
					<th class="text-right"><spring:message code="schedule.amount" text="!"/></th>
					<th class="text-right"><spring:message code="schedule.sale" text="!"/></th>
					<th class="text-right"><spring:message code="schedule.payment" text="!"/></th>
					<th class="text-right"><spring:message code="schedule.rest" text="!"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${records}" var="elm" varStatus="stt">
					<c:url value="/secure/records"  var="linkCustomer">
						<c:param name="id" value="${elm.serial}"/>
						<c:param name="agency" value="${elm.branch}"/>
					</c:url>
					
					<tr>
						<td class="text-center">${row + stt.index + 1}</td>
						<td><a href="${linkCustomer }">${elm.serial}</a></td>
						<td><a href="${linkCustomer }">${elm.customer.name}</a></td>
						<td>${elm.customer.telephone}</td>
						<td>${elm.customer.content}</td>
						<td>${elm.contentNext}</td>
						<td>${elm.dentist}</td>
						<td class="text-right"><fmt:formatNumber value="${elm.customer.gross}"/></td>
						<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${elm.customer.sale}"/></td>
						<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${elm.customer.payment}"/></td>
						<c:choose>
							<c:when test="${elm.customer.gross - elm.customer.sale - elm.customer.payment < 0 }">
								<td class="text-right text-danger"><fmt:formatNumber pattern="${formatPattern}" value="${elm.customer.gross - elm.customer.sale - elm.customer.payment}"/></td>
							</c:when>
							<c:otherwise>
								<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${elm.customer.gross - elm.customer.sale - elm.customer.payment}"/></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>	
			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript">

	var pageCount = ${dashboardFilter.totalPage};
	
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
			url : "<spring:url value='/secure/dashboard/loadpage'/>",
		    data: { pageNo: pageNo },
		    cache : false,
		    success: function (response) {
		        $("#_results").html(response);
		    }
		});
	};
</script>