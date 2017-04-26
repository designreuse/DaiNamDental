<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 
<!DOCTYPE html>
<html>
	<head>
	 <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/static/images/favicon.ico"/>"/>
		<link rel="icon" type="image/ico" href="<c:url value="/static/images/favicon.ico"/>"/>
	    <title><spring:message code="title.page" text="!"/></title>
	
	    <link href="<c:url value="/static/inspinia/css/bootstrap.min.css"/>" rel="stylesheet">
	    <link href="<c:url value="/static/inspinia/font-awesome/css/font-awesome.css"/>" rel="stylesheet">
	    <link href="<c:url value="/static/inspinia/css/animate.css"/>" rel="stylesheet">
	    <link href="<c:url value="/static/inspinia/css/style.css"/>" rel="stylesheet">
	    <link href="<c:url value="/static/css/loginstyle.css"/>" rel="stylesheet">
	  
	  	<style>
	  		.gray-bg {
				background:url("<c:url value='/static/images/bg.jpg'/>");	
				background-size: cover
			}
	
			#clear{
				clear:both
			}
			#logo{
				padding: 12px 15px;
			}
			.form-control:focus, .single-line:focus {
			    border-color: #1c84c6 !important;
			}
		</style>
	</head>

	<body class="gray-bg">
		
		
		<div class="loginColumns">
        <div class="row">
        	<div class="col-md-3"></div>
        	<div class="col-md-6">
                <div class="ibox-content">
                	<h4 align="center"><spring:message code="login.title" text="!Title" /></h4>
                	<c:choose>
						<c:when test="${loginFailed != null}">
							<p style="color: red;">
                				<spring:message code="login.error" text="! Access denied"/>
                			</p>
						</c:when>
						<c:otherwise>
							<br>
						</c:otherwise>
					</c:choose>
                	<form method="post" class="m-t" action="<c:url value="/j_spring_security_check"/>" method="post">
                        <div class="form-group">
                        	<div class="input-group m-b">
                        		<span class="input-group-addon"><i class="fa fa-user"></i></span>
                        		<input type="text" id='username' name='username' value='' placeholder="<spring:message code="login.username" text="!UserName" />" class="form-control">
                        	</div>
                        </div>
                        <div class="form-group">
                        	<div class="input-group m-b">
                            	<span class="input-group-addon"><i class="fa fa-asterisk"></i></span> 
                            	<input type='password' id='password' name='password' value="" placeholder="<spring:message code="login.password" text="!Password" />" class="form-control">
                            </div>
                             <button type="submit" class="btn btn-success block full-width m-b text-uppercase" value="Login">
                             	<spring:message code="login.login" text="!Login" />
                             </button>  
                        </div>                                                           
                    </form>                    
                </div>
            </div>
       	</div>           	   	
    </div>
	</body>
</html>