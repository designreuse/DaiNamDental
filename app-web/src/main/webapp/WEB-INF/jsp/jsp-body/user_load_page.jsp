<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<table class="table table-striped table-bordered table-hover table-manifest" style="width:100%;">
	<thead>
		<tr>
			<th class="text-center">#</th>
			<th class="text-center"><spring:message code="commom.action" text="!Action"/></th>
			<th><spring:message code="user.username" text="!"/></th>
			<th><spring:message code="user.password" text="!"/></th>
			<th><spring:message code="user.name" text="!"/></th>
			<th><spring:message code="user.role" text="!"/></th>
			<th><spring:message code="user.branch" text="!"/></th>
			<th><spring:message code="user.telephone" text="!"/></th>
			<th><spring:message code="user.email" text="!"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="elm" varStatus="stt">
		<tr id=${elm.username }>
			<td class="text-center">${stt.index + 1}</td>
			<td class="text-center text-nowrap">
				<a onclick='doEditUser("${elm.username}")' data-toggle="modal" data-target="#formEdit"
					title="<spring:message code="user.table.modify" text="!Modify user"/>"><i class="fa fa-2x fa-edit"></i>
				</a>
				<a onclick="ConformDelete('${elm.username}')" title="<spring:message code="button.delete" text="!Delete"/>"><i class="fa fa-2x fa-trash-o"></i></a>
			</td>
			<td>${elm.username }</td>
			<td>${elm.password }</td>
			<td>${elm.name }</td>
			<td>${elm.role }</td>
			<td>${elm.branch }</td>
			<td>${elm.telephone }</td>
			<td>${elm.email }</td>
		</tr>
		</c:forEach>
	</tbody>
</table>