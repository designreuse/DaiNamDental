<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="row">
	<div class="col-lg-5">
		<label class="label label-success" style="font-size:14px">${billFilter.rowCount} <spring:message code="message.result" text="!Result(s)"/></label>
	</div>
	<div class="col-lg-7 text-right">
		<c:if test="${billFilter.totalPage > 1}">
			<div class="form-group">
				<label class="control-label" style="font-weight: 500; font-size: 14px;"><spring:message code="button.page" text="!page"></spring:message>&nbsp;</label>
				<input id="PageNoGo" type="text" style="width:50px; height:30px; vertical-align:middle;border-radius:3px; border: 1px solid #e5e6e7; font-size:14px;" value="${billFilter.page + 1}">
		        <label class="control-label" style="font-weight: 500; font-size:15px"> /${billFilter.totalPage}&nbsp;</label>
		        <button id="go" class="btn btn-success btn-sm text-uppercase" type="button">&nbsp;<spring:message code="button.go" text="!Go"/>&nbsp;</button>
		        <div class="btn-group">
			        <c:choose>
				        <c:when test="${billFilter.page > 0}">
				        	<a id="prev" class="btn btn-sm btn-white text-uppercase" href="--pageNo"><spring:message code="button.prev" text="!Prev."/></a>
				        </c:when>
				        <c:otherwise>
				         	<a class="btn btn-sm btn-white text-uppercase disabled"><spring:message code="button.prev" text="!Prev."/></a>
				        </c:otherwise>
			        </c:choose>
		                        
		                <c:choose>
				            <c:when test="${billFilter.page < (billFilter.totalPage - 1)}">
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

<table class="table table-striped table-bordered table-hover table-result" style="width:100%;">
	<thead>
		<tr>
			<th class="text-center">#</th>
			<th><spring:message code="invoice.serial" text="!"/></th>
			<th><spring:message code="invoice.date.excute" text="!"/></th>
			<th><spring:message code="invoice.name" text="!"/></th>
			<th><spring:message code="invoice.telephone" text="!"/></th>
			<th><spring:message code="invoice.address" text="!"/></th>
			<th class="text-right"><spring:message code="invoice.amount" text="!"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${bills}" var="elm" varStatus="stt">
			<tr>
				<td class="text-center">${row + stt.index + 1}</td>
				<td>${elm.serial}</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${elm.dateExcute}" /></td>
				<td>${elm.invoice.nameCustomer}</td>
				<td>${elm.invoice.telephone}</td>
				<td>${elm.invoice.address}</td>
				<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${elm.amount}"/></td>
			</tr>
		</c:forEach>	
	</tbody>
	<tfoot>
		<tr>
			<th colspan="6" class="text-uppercase"><spring:message code="invoice.total" text="!"/></th>
			<th class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${totalBill}"/></th>
		</tr>
	</tfoot>
</table>

<script type="text/javascript">

	var pageCount = ${billFilter.totalPage};
	
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
			url : "<spring:url value='/secure/bill/loadpage'/>",
		    data: { pageNo: pageNo },
		    cache : false,
		    success: function (response) {
		        $("#_results").html(response);
		    }
		});
	};
</script>