<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="row border-bottom">
<nav class="navbar navbar-static-top white-bg" role="navigation" style="margin-bottom: 0">
	<div class="navbar-header">
	    <a class="navbar-minimalize minimalize-styl-2 btn btn-success" href="#"><i class="fa fa-bars"></i> </a>
	</div>
    <ul class="nav navbar-top-links navbar-right">
    	<li>
            <span class="m-r-sm font-bold" style="font-size: 15px;"><spring:message code="title.platform" text="!"/></span>
        </li>
          
        	
            <li class="dropdown">
	        	<a class="dropdown-toggle count-info" data-toggle="dropdown" href="#" style="font-weight:400;">
	            	<i class="fa fa-user"></i> ${fn:trim(sysUser.username)}
	        	</a>
	            <ul class="dropdown-menu dropdown-alerts" style="width:200px;">
	            	<li class=""><spring:message code="role.${fn:trim(sysUser.role)}" text="!"/></li>
	               	<li>
						<a style="text-align: center;" href="<c:url value="/logout"/>">
							<i class="fa fa-sign-out"></i> <spring:message code="login.logout" text="!Logout" />
						</a>
					</li>
	            </ul>
	       </li>
    </ul>
</nav>
</div>		