<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<style>
	.hr-line-dashed {
	    background-color: #ffffff;
	    border-top: 1px dashed #808080;
	    color: #ffffff;
	    height: 1px;
	    margin: 0px 0px 10px 0px;
	}
</style>

<script type="text/javascript">
	document.getElementById("invoice").className = "active";
	document.getElementById("invoiceManager").className = "active";
</script>

<div id="body">
	<c:set var="now" value="<%=new java.util.Date()%>" />
	<c:set var="formatPattern" value="#,##0"/>

	<div class="row">
		<div class="col-lg-12 text-center">
			<a href="<c:url value="/secure/invoice"></c:url>" class="btn btn-w-m btn-default text-uppercase">
				<i class="fa fa-backward"></i> <spring:message code="button.back" text="!"/>
			</a>
			<c:url value="/secure/invoice/print" var="print">
				<c:param name="id" value="${invoice.id }"></c:param>
			</c:url>
			
			<a href='${print }' target="_blank" class="btn btn-w-m btn-success text-uppercase">
				<i class="fa fa-print"></i> <spring:message code="button.printer" text="!"/>
			</a>
		</div>
	</div>
	<br><br>
	<div class="row">
		<h3 class="col-lg-12 text-center font-bold">${branch.fullName }</h3>
	</div>
	<div class="row">
		<div class="col-lg-3">
		</div>
		<div class="col-lg-9">
			<div class="row">
				<span class="col-lg-12"><spring:message code="branches.address" text="!"/> : ${branch.address }</span>
				<span class="col-lg-6"><spring:message code="branches.telephone" text="!"/> : ${branch.telephone }</span>
				<span class="col-lg-6"><spring:message code="branches.hotline" text="!"/></span>
				<span class="col-lg-6"><spring:message code="branches.website" text="!"/></span>
				<span class="col-lg-6"><spring:message code="branches.email" text="!"/></span>
			</div>
		</div>
	</div>
	<br>
	<div class="row">
		<h2 class="col-lg-12 text-center font-bold text-uppercase"><spring:message code="branches.bill" text="!"/></h2>
		<span class="col-lg-12 text-center">
			<spring:message code="invoice.date" text="!"/> <fmt:formatDate pattern="dd" value="${now}" />&nbsp;
			<spring:message code="invoice.month" text="!"/> <fmt:formatDate pattern="MM" value="${now}" />&nbsp;
			<spring:message code="invoice.year" text="!"/> <fmt:formatDate pattern="yyyy" value="${now}" />
		</span>
	</div>
	
	<div class="row">
		<div class="col-sm-12">
			<h3><spring:message code="invoice.customer" text="!"/></h3>
			<div class="hr-line-dashed"></div>
		</div>
	</div>
		
	<div class="row">
		<div class="col-sm-12">
			<span><spring:message code="invoice.serial" text="!"/>: ${invoice.serial }</span>
		</div>
		<div class="col-sm-12">
			<span><spring:message code="invoice.name" text="!"/></span>: <span class="text-uppercase font-bold">${invoice.invoice.nameCustomer }</span>
		</div>
		<div class="col-sm-12">
			<span><spring:message code="invoice.telephone" text="!"/>: ${invoice.invoice.telephone }</span>
		</div>
		<div class="col-sm-12">
			<span><spring:message code="invoice.address" text="!"/>: ${invoice.invoice.address }</span>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-12">
			<table class="table table-striped table-bordered">
				<tbody>
					<tr>
						<th><spring:message code="invoice.content" text="!"/></th>
						<th class="text-right"><spring:message code="invoice.gross" text="!"/></th>
						<th class="text-right"><spring:message code="invoice.payment" text="!"/></th>
					</tr>
					<tr>
						<td class="text-uppercase">${invoice.content }</td>
						<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${invoice.gross}"/></td>
						<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${invoice.payment}"/></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-12">
			<h3><spring:message code="invoice.info" text="!"/></h3>
			<div class="hr-line-dashed"></div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-12">
			<table class="table table-striped table-bordered">
				<tbody>
					<tr>
						<th><spring:message code="invoice.date.excute" text="!"/></th>
						<th><spring:message code="invoice.cause" text="!"/></th>
						<th class="text-right"><spring:message code="invoice.payment.new" text="!"/></th>
						<th class="text-right"><spring:message code="invoice.rest" text="!"/></th>
					</tr>
					<tr>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${invoice.dateExcute }"/></td>
						<td class="text-uppercase">${invoice.cause }</td>
						<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${invoice.amount}"/></td>
						<td class="text-right"><fmt:formatNumber pattern="${formatPattern}" value="${invoice.gross - invoice.payment - invoice.amount}"/></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="col-sm-12">
			<span style="font-style: italic;"><spring:message code="invoice.note1" text="!"/></span>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-sm-6">
			<span><spring:message code="invoice.payer" text="!"/></span>
		</div>
		
		<div class="col-sm-6">
			<span><spring:message code="invoice.biller" text="!"/></span>
		</div>
		
		<div class="col-sm-6">
			<span><spring:message code="invoice.note2" text="!"/></span>
		</div>
		
		<div class="col-sm-6">
			<span><spring:message code="invoice.note2" text="!"/></span>
		</div>
	</div>
	<br><br><br><br>							
</div>