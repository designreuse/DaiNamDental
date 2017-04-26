<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/static/images/favicon.ico"/>"/>
	<link rel="icon" type="image/ico" href="<c:url value="/static/images/favicon.ico"/>"/>
    <title><spring:message code="title.page" text="!"/></title>
	
	
    <link href="<c:url value="/static/inspinia/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/inspinia/font-awesome/css/font-awesome.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/inspinia/css/animate.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/inspinia/css/style.css"/>" rel="stylesheet"> 
    <link href="<c:url value="/static/inspinia/css/plugins/toastr/toastr.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/inspinia/js/plugins/gritter/jquery.gritter.css"/>" rel="stylesheet">
    
    <link href="<c:url value="/static/inspinia/css/plugins/chosen/chosen.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/inspinia/css/plugins/dataTables/dataTables.bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/inspinia/css/plugins/dataTables/dataTables.responsive.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/inspinia/css/plugins/datapicker/datepicker3.css"/>" rel="stylesheet">
    
    
     <link href="<c:url value="/static/inspinia/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"/>" rel="stylesheet">
    
    <link href="<c:url value="/static/css/style.css"/>" rel="stylesheet">
   
    <style type="text/css">
	    body {
		  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
		  background-color: #2f4050;
		  font-size: 13px;
		  color: #333;
		  overflow-x: hidden;
		}

		.nav-header {
		  padding: 0px 25px;
		  background: #ffffff;
		  border-right: 1px solid #e7eaec;
		}
		body.mini-navbar .nav-header {
		    background-color: #ffffff;
		    height: 60px;
		}
		.small-chat-box .content > div {
		  padding-bottom: 0px;
		}
		
		.inmodal .modal-header {
	   	 	padding: 20px 15px;
	    	text-align: left;
	    	color: #1c84c6;
		}
		.inmodal .modal-title {
		    font-size: 20px;
		}
		
		.nav > li.active {
		    background: #293846 none repeat scroll 0 0;
		    border-left: 4px solid #1c84c6;
		}
		
		.pace .pace-progress {
		  background: #1c84c6;
		  position: fixed;
		  z-index: 2000;
		  top: 0;
		  right: 100%;
		  width: 100%;
		  height: 2px;
		}
		.form-control:focus, .single-line:focus {
		    border-color: #1c84c6 !important;
		}
		
		.ibox-content {
		    background-color: #ffffff;
		    border-color: #e7eaec;
		    border-image: none;
		    border-style: solid;
		    border-width: 1px 0;
		    color: inherit;
		    padding: 15px 20px 20px;
		}
		.sk-spinner-wave div {
            animation: 1.2s ease-in-out 0s normal none infinite running sk-waveStretchDelay;
            background-color: #1c84c6;
            display: inline-block;
            height: 100%;
            width: 6px;
        }
        
	 	.boxcss {
		    right: 0;
		    -webkit-transition: width 0.1s;
		    transition: width 0.1s;
		    width: 0px;
		    opacity: 0;
		    position: absolute;
		    overflow: hidden;
		    display: block;
		    z-index: 100;
		    margin-top: 0px;
		    background: #fff;
		    border-radius: 3px;
		}
	
		.closebutton {
		    float: right;
		    padding: 2px 4px;
		    margin: -40px 0px;
		}
	
		.chosen-container-single .chosen-single {
	         background: none;
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

	</style>
</head>

<body>
    <script src="<c:url value="/static/inspinia/js/jquery-2.1.1.js"/>"></script>
    <script src="<c:url value="/static/inspinia/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/static/inspinia/js/plugins/metisMenu/jquery.metisMenu.js"/>"></script>
    <script src="<c:url value="/static/inspinia/js/plugins/slimscroll/jquery.slimscroll.min.js"/>"></script>
	
    <script src="<c:url value="/static/inspinia/js/inspinia.js"/>"></script>
    <script src="<c:url value="/static/inspinia/js/plugins/pace/pace.min.js"/>"></script>
    <script src="<c:url value="/static/inspinia/js/plugins/jquery-ui/jquery-ui.min.js"/>"></script>
    <script src="<c:url value="/static/inspinia/js/plugins/gritter/jquery.gritter.min.js"/>"></script>
    <script src="<c:url value="/static/inspinia/js/plugins/toastr/toastr.min.js"/>"></script>
    <script src="<c:url value="/static/inspinia/js/plugins/chosen/chosen.jquery.js"/>"></script>
    <script src="<c:url value="/static/inspinia/js/plugins/datapicker/bootstrap-datepicker.js"/>"></script>
    
    <script src="<c:url value="/static/inspinia/js/plugins/dataTables/jquery.dataTables.js"/>"></script>
    <script src="<c:url value="/static/inspinia/js/plugins/dataTables/dataTables.bootstrap.js"/>"></script>
    <script src="<c:url value="/static/inspinia/js/plugins/dataTables/dataTables.responsive.js"/>"></script>
	<script src="<c:url value="/static/inspinia/js/plugins/dataTables/dataTables.tableTools.min.js"/>"></script>
    
   	<script type="text/javascript">
   		$(document).ready(function() {
			$("#form").keypress(function (e) {
		       if (e.which == 13) {
		    	   doSubmit('GO');
		       }
	   		});
  		});
   
	   	function doSubmit(action){
			document.forms[0].elements['action'].value=action;
			document.forms[0].submit();
		}

	   	function doEdit(action, id){	
			document.forms[0].elements['action'].value = action;	
			document.forms[0].elements['id'].value = id;
			document.forms[0].submit();
		}	

	   	function getOffset(el) {
	 	    var _x = 0;
	 	    var _y = 0;
	 	    while (el && !isNaN(el.offsetLeft) && !isNaN(el.offsetTop)) {
	 	        _x += el.offsetLeft;
	 	        _y += el.offsetTop;
	 	        el = el.offsetParent;
	 	    }
	 	    return { top: _y, left: _x };
	 	}
	   
	 	function bfShow(box_id, btn_id, elm_id) {
	        var div_elm = document.getElementById(elm_id);
	        var bodyRect = document.body.getBoundingClientRect();
	        var divRect = div_elm.getBoundingClientRect();
	        var right = bodyRect.right - divRect.right;

	        var btn_elm = document.getElementById(btn_id);
	        var elOffset = getOffset(btn_elm);

	        $('#' + box_id).css({ 'top': elOffset.top - $('#' + elm_id).height() + 'px', 'left': 25 + 'px' });

	        var navbarWidth = $(".navbar-static-side").width();
	        var offset = elOffset.left - navbarWidth - 30;

	        $('#'+ box_id).css({
	            background: "url('<c:url value="/static/images/arrowt.png"/>') no-repeat " + offset + "px top"
	        });
	    }

	 	function show(divId) {
	 	    $('#' + divId).animate({
	 	        width: $('.wrapper').width() + "px",
	 	        opacity: 1
	 	    });
	 	   
	 	}
	 	
	 	function hide(id) {
	 	    $('#' + id).animate({
	 	        width: "0px",
	 	        opacity: 0
	 	    });
	 	}
	 	
	</script>
	
	<div id="wrapper">
		<tiles:insertAttribute name="menu"></tiles:insertAttribute>
        <div id="page-wrapper" class="white-bg">
		<tiles:insertAttribute name="banner"></tiles:insertAttribute>
        <div class="row">
            <div class="col-lg-12">
                <div class="wrapper wrapper-content">
	                <tiles:insertAttribute name="body"></tiles:insertAttribute>
                </div>
			</div>
			<tiles:insertAttribute name="footer"></tiles:insertAttribute>
        </div>
        </div>  
   </div>
</body>
</html>