<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="edu.stevens.ssw690.DuckSource.*"%>

<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head>
    <meta charset="utf-8">
    <title>Accepted Opportunity</title>
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

   <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="<c:url value="/resources/css/style.ie7.css" />" media="screen" /><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.responsive.css" />" media="all">

    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/script.js" />"></script>
    <script src="<c:url value="/resources/js/script.responsive.js" />"></script>
    <script src="<c:url value="/resources/js/findopp.js" />"></script>


<style>.art-content .art-postcontent-0 .layout-item-0 { color: #212627; background: #E1E5E5 url('c:url value='/resources/images/45f90.png'/>') scroll;  border-collapse: separate; border-radius: 15px;  }
.art-content .art-postcontent-0 .layout-item-1 { color: #212627; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-top-left-radius: 15px;border-bottom-left-radius: 15px;  }
.art-content .art-postcontent-0 .layout-item-2 { border-top-style:solid;border-bottom-style:solid;border-top-width:1px;border-bottom-width:1px;border-top-color:#B7C1C2;border-bottom-color:#B7C1C2; color: #282E2F; background: #F9FAFA;background: rgba(249, 250, 250, 0.85); padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px;  }
.art-content .art-postcontent-0 .layout-item-3 { color: #212627; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-top-right-radius: 15px;border-bottom-right-radius: 15px;  }
.art-content .art-postcontent-0 .layout-item-4 { color: #363E3F;  }
.art-content .art-postcontent-0 .layout-item-5 { color: #363E3F; padding-right: 10px;padding-left: 10px;  }
.art-content .art-postcontent-0 .layout-item-6 { color: #212627; background: #CCEAFA url('c:url value='/resources/images/d69bd.png' >/') scroll;background: rgba(204, 234, 250, 0.2) url('c:url value='/resources/images/d69bd.png' />') scroll;  border-collapse: separate; border-radius: 10px;  }
.art-content .art-postcontent-0 .layout-item-7 { color: #212627; background: ; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-top-left-radius: 10px;border-bottom-left-radius: 10px;  }
.art-content .art-postcontent-0 .layout-item-8 { border-top-style:solid;border-bottom-style:solid;border-top-width:1px;border-bottom-width:1px;border-top-color:#BEE4F9;border-bottom-color:#BEE4F9; color: #262B2C; background: #E3F3FC; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px;  }
.art-content .art-postcontent-0 .layout-item-9 { color: #212627; background: ; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-top-right-radius: 10px;border-bottom-right-radius: 10px;  }
.ie7 .art-post .art-layout-cell {border:none !important; padding:0 !important; }
.ie6 .art-post .art-layout-cell {border:none !important; padding:0 !important; }

</style>
<style>
    	.error{color:#ff0000;font-weight:bold;}
    </style>
</head>
<body>
<div id="art-main">
<header class="art-header">

    <div class="art-shapes">
        <div class="art-textblock art-object624133700">
        <div class="art-object624133700-text-container">
        <div class="art-object624133700-text"><p style="color: rgb(123, 218, 249); font-weight: bold; font-style: normal; text-decoration: none; font-variant: small-caps; "><span class="Apple-style-span" style="font-family: 'Comic Sans MS'; font-size: 36px;">Duck <span style="color: rgb(226, 52, 29); ">Source</span></span></p></div>
    </div>
    
</div>
            </div>






                
                    
</header>
<nav class="art-nav">
    <ul class="art-hmenu"><li><a href="main?userId=${userId}">Home Page</a></li><li><a href="findopp?userId=${userId}">Find an Opportunity</a></li><li><a href="createopp?userId=${userId}">Create an Opportunity</a></li><li><a href="account?userId=${userId}">Account Settings</a></li><li><a href="index">Sign Out</a></li></ul> 
    </nav>
<div class="art-sheet clearfix">
            <div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-content"><article class="art-post art-article">
                                
                                                
                <div class="art-postcontent art-postcontent-0 clearfix"><div class="art-content-layout layout-item-0">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-1" style="width: 20%" >
        <p><br></p>
    </div><div class="art-layout-cell layout-item-2" style="width: 60%" >
        <p style="text-align: center;"><span style="font-weight: bold; font-size: 26px;">${opportunity.opportunityTitle}</span></p>
                                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                <meta content="en-us" http-equiv="Content-Language">
                                                                                                                                                                                                                                                                                <style type="text/css">
                                                                                                                                                                                                                                                                                .auto-style1 {
                                                                                                                                                                                                                                                                                	background-color: #DBEFF8;
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                </style>
                                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                <table style="width: 30%; margin-right: auto; margin-left: auto; ">
                                                                                                                                                                                                                                                                                	<tbody>
                                                                                                                                                                                                                                                                                		<tr>
                                                                                                                                                                                                                                                                                			<td class="auto-style1" style="width: 248px; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; "><span style="font-weight: bold;">Registered: ${opportunity.registeredCount}</span></td>
                                                                                                                                                                                                                                                                                			<td class="auto-style1" style="width: 242px; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; "><span style="font-weight: bold;">Submitted: ${opportunity.submittedCount}</span></td>
                                                                                                                                                                                                                                                                                		</tr>
                                                                                                                                                                                                                                                                                	</tbody>
                                                                                                                                                                                                                                                                                </table>
                                                                                                                                                                                                                                                                                <table class="art-article" style="width: 100%; ">
                                                                                                                                                                                                                                                                                <tbody>
                                                                                                                                                                                                                                                                                	<tr>
                                                                                                                                                                                                                                                                        				<td style="width: 2%; text-align: right; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; "><span style="color: rgb(28, 32, 33); font-size: 16px; font-weight: bold; ">Type:</span></td>
                                                                                                                                                                                                                                                                        				<td style="width: 50%; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; ">${opportunity.opportunityType}</td>
                                                                                                                                                                                                                                                                        			</tr>
                                                                                                                                                                             
                                                                                                                                                                                                                                                                        			<tr>
                                                                                                                                                                                                                                                                        				<td style="width: 2%; text-align: right; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; "><span style="color: rgb(28, 32, 33); font-size: 16px; font-weight: bold; ">Duck Bill$:</span></td>
                                                                                                                                                                                                                                                                        				<td style="width: 50%; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; ">
                                                                                                                                                                                                                                                                        					<fmt:setLocale value="en_US"/>
                                                                                                                                                                                                                                             											    <fmt:formatNumber value="${opportunity.duckbills}" type="currency"/>
                                                                                                                                                                                                                                                                        				</td>
                                                                                                                                                                                                                                                                        			</tr>
                                                                                                                                                                                                                                                                        			<tr>
                                                                                                                                                                                                                                                                        				<td style="width: 2%; text-align: right; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; "><span style="color: rgb(28, 32, 33); font-size: 16px; font-weight: bold; ">Register By:</span></td>
                                                                                                                                                                                                                                                                        				<td style="width: 50%; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; ">
                                                                                                                                                                                                                                                                        					<c:set var="regDate">
                                                                                                                                                                                                                                                                        					<fmt:formatDate pattern="MM/dd/yyyy" value="${opportunity.registerDate}" />
                                                                                                                                                                                                                                                                        					</c:set>
                                                                                                                                                                                                                                                                        					${regDate}
                                                                                                                                                                                                                                                                        				</td>
                                                                                                                                                                                                                                                                        			</tr>
   					                                                                                                                                                                                                       				             								<tr>
                                                                                                                                                                                                                                                                        				<td style="width: 2%; text-align: right; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; "><span style="color: rgb(28, 32, 33); font-size: 16px; font-weight: bold; ">Submit By:</span></td>
                                                                                                                                                                                                                                                                        				<td style="width: 50%; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; ">
                                                                                                                                                                                                                                                                        					<c:set var="subDate">
                                                                                                                                                                                                                                                                        					<fmt:formatDate pattern="MM/dd/yyyy" value="${opportunity.submitDate}" />
                                                                                                                                                                                                                                                                        					</c:set>
                                                                                                                                                                                                                                                                        					${subDate}
                                                                                                                                                                                                                                                                        				</td>                                                  
                                                                                                                                                                                                                                                                        			</tr>
                                                                                                                                                                                                                                                                        			<tr>
                                                                                                                                                                                                                                                                        				<td style="width: 2%; text-align: right; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; "><span style="color: rgb(28, 32, 33); font-size: 16px; font-weight: bold; ">Description:</span></td>
                                                                                                                                                                                                                                                                        				<td style="width: 50%; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; ">${opportunity.description}</td>
                                                                                                                                                                                                                                                                        			</tr>
                                                                                                                                                                                                                                                                        			<tr>
                                                                                                                                                                                                                                                                        				<td style="width: 100%; text-align: center; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; " colspan="2"></td>
                                                                                                                                                                                                                                                                        			</tr>
                                                                                                                                                                                                                                                                        		</tbody>
                                                                                                                                                                                                                                                                        	</table>
                                                                                                                                                                                                                                                                        	<p style="text-align: center;"><span style="-webkit-border-horizontal-spacing: 2px; -webkit-border-vertical-spacing: 2px; border-collapse: collapse;"><br></span><span style="color: rgb(28, 32, 33); font-size: 16px; font-weight: bold; -webkit-border-horizontal-spacing: 2px; -webkit-border-vertical-spacing: 2px; "></span></p>
    </div><div class="art-layout-cell layout-item-3" style="width: 20%" >
        <p><br></p>
    </div>
    </div>
</div>
<div class="art-content-layout layout-item-4">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-5" style="width: 100%" >
        <p><br></p>
    </div>
    </div>
</div>
<div class="art-content-layout layout-item-6">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-7" style="width: 20%" >
        <p><br></p>
    </div><div class="art-layout-cell layout-item-8" style="width: 60%" >
    <form:form commandName="submit" method="post" enctype="multipart/form-data">
        <table class="art-article" style="width: 100%; ">
        	<tbody>
        		<tr style="border: none;">
                   <td style="width: 23%; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; text-align: right; "><span style="text-align: right; "><span style="font-weight: bold; font-size: 16px;"><span style="color: rgb(38, 43, 44); ">Upload file to submit:</span></span></span>
                   </td>
                   <td style="border: none; width: 77%;">
                   		<input type="file" name="file" /> 
                   </td>
                </tr>
                <tr>
                   <td style="width: 23%; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; text-align: right; "><span style="color: rgb(38, 43, 44); font-weight: bold; text-align: right; font-size: 16px;">Comments:</span>
                   </td>
                   <td style="width: 77%; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; ">
                   		<textarea name="comments" style="font-size: 14px;" rows="4" cols="50"></textarea>
                   </td>
                 </tr>
                 <tr>
                   	<td style="border: none; text-align: center; padding-top: 10px" colspan="2">
                   		<input class="art-button" type="submit" value="Submit" />
                   	</td>
               </tr>
               <tr>
                   	<td style="border: none; text-align: center; padding-top: 10px" colspan="2" class=${messageClass}>
                   				${message}
                   	</td>
               </tr>
           </tbody>
        </table>
     </form:form>
    </div><div class="art-layout-cell layout-item-9" style="width: 20%" >
        <p><br></p>
    </div>
    </div>
</div>
</div>


</article></div>
                    </div>
                </div>
            </div><footer class="art-footer">
<p><a href="about?userId=${userId}" style="font-size: 13px;"><span style="color: rgb(22, 156, 227);">About</span></a><a href="#"></a></p>
<p>Copyright © 2015. All Rights Reserved.</p>
</footer>

    </div>
</div>


</body></html>