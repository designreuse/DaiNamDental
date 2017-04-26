<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="row">
	<div class="col-lg-5">
		<label class="label label-success" style="font-size:14px">${invoiceFilter.rowCount} <spring:message code="message.result" text="!Result(s)"/></label>
	</div>
	<div class="col-lg-7 text-right">
		<c:if test="${invoiceFilter.totalPage > 1}">
			<div class="form-group">
				<label class="control-label" style="font-weight: 500; font-size: 14px;"><spring:message code="button.page" text="!page"></spring:message>&nbsp;</label>
				<input id="PageNoGo" type="text" style="width:50px; height:30px; vertical-align:middle;border-radius:3px; border: 1px solid #e5e6e7; font-size:14px;" value="${invoiceFilter.page + 1}">
		        <label class="control-label" style="font-weight: 500; font-size:15px"> /${invoiceFilter.totalPage}&nbsp;</label>
		        <button id="go" class="btn btn-success btn-sm text-uppercase" type="button">&nbsp;<spring:message code="button.go" text="!Go"/>&nbsp;</button>
		        <div class="btn-group">
			        <c:choose>
				        <c:when test="${invoiceFilter.page > 0}">
				        	<a id="prev" class="btn btn-sm btn-white text-uppercase" href="--pageNo"><spring:message code="button.prev" text="!Prev."/></a>
				        </c:when>
				        <c:otherwise>
				         	<a class="btn btn-sm btn-white text-uppercase disabled"><spring:message code="button.prev" text="!Prev."/></a>
				        </c:otherwise>
			        </c:choose>
		                        
		                <c:choose>
				            <c:when test="${invoiceFilter.page < (invoiceFilter.totalPage - 1)}">
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
		<table class="table table-striped table-bordered table-hover table-result" style="width:100%;">
			<thead>
				<tr>
					<th class="text-center">#</th>
					<sec:authorize access="hasRole('ADMIN')">
					<th class="text-center"></th>
					</sec:authorize>
					<th><spring:message code="invoice.serial" text="!"/></th>
					<th><spring:message code="invoice.name" text="!"/></th>
					<th><spring:message code="invoice.telephone" text="!"/></th>
					<th><spring:message code="invoice.address" text="!"/></th>
					<th class="text-right"><spring:message code="invoice.amount" text="!"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${invoices}" var="elm" varStatus="stt">
					<tr>
						<c:url value="/secure/invoice/detail"  var="linkInvoiceDetail">
							<c:param name="id" value="${elm.id.serial}"/>
							<c:param name="agency" value="${elm.id.branch}"/>
						</c:url>
						<td class="text-center">${row + stt.index + 1}</td>
						<sec:authorize access="hasRole('ADMIN')">
						<td class="text-center text-nowrap">
							<a onclick="ConformDelete('${elm.id.serial}', '${elm.id.branch}')" title="<spring:message code="button.delete" text="!Delete"/>"><i class="fa fa-2x fa-trash-o"></i></a>
						</td>
						</sec:authorize>
						<td><a href="${linkInvoiceDetail }">${elm.id.serial}</a></td>
						<td><a href="${linkInvoiceDetail }">${elm.nameCustomer}</a></td>
						<td>${elm.telephone}</td>
						<td>${elm.address}</td>
						<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${elm.amount}"/></td>
					</tr>
				</c:forEach>	
			</tbody>
		</table>

<script type="text/javascript">

	var pageCount = ${invoiceFilter.totalPage};
	
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

		$('.table-result').dataTable({
 	 		"dom": 'T<"clear">lfrtip',
 	        "sDom": "<'row'<'col-sm-6 text-left'><'col-sm-6 text-right'>r>" + "t" + "<'row'<'col-sm-6'><'col-sm-6'p>>",
 			"bSort": false,
 			"paginate": false,
 			"scrollX": true
 	   	}); 
 	   	
	});
		
	function fnLoadPage() {
		$.ajax({
			url : "<spring:url value='/secure/invoice/loadpage'/>",
		    data: { pageNo: pageNo },
		    cache : false,
		    success: function (response) {
		        $("#_results").html(response);
		    }
		});
	};
</script>