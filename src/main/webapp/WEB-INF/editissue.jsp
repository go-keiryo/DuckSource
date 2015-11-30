<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="edu.stevens.ssw690.DuckSource.*"%>

<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head>
    <meta charset="utf-8">
    <title>Edit Review Issue</title>
    <style>
    	.error{color:#ff0000;font-weight:bold;}
    </style>
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

    <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="<c:url value="/resources/css/style.ie7.css" />" media="screen" /><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.responsive.css" />" media="all">

    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/script.js" />"></script>
    <script src="<c:url value="/resources/js/script.responsive.js" />"></script>
    <script src="<c:url value="/resources/js/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.price_format.2.0.min.js" />"></script>
    <script src="<c:url value="/resources/js/createopp.js" />"></script>

<style>.art-content .art-postcontent-0 .layout-item-0 { color: #232929; background: #ECEEEF url('<c:url value='/resources/images/6cd8d.png' />') scroll;  border-collapse: separate; border-radius: 15px;  }
.art-content .art-postcontent-0 .layout-item-1 { color: #212627; background: #C0C8C9 url('<c:url value='/resources/images/66838.png' />') scroll; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-top-left-radius: 15px;border-bottom-left-radius: 15px;  }
.art-content .art-postcontent-0 .layout-item-2 { color: #212627; background: #DEE2E3 url('<c:url value='/resources/images/a8c2a.png' />') scroll; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px;  }
.art-content .art-postcontent-0 .layout-item-3 { color: #232929; background: ; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-top-left-radius: 15px;border-bottom-left-radius: 15px;  }
.ie7 .art-post .art-layout-cell {border:none !important; padding:0 !important; }
.ie6 .art-post .art-layout-cell {border:none !important; padding:0 !important; }

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
    <ul class="art-hmenu"><li><a href="main?userId=${userId}" class="">Home Page</a></li><li><a href="findopp?userId=${userId}" class="">Find an Opportunity</a></li><li><a href="createopp?userId=${userId}" class="">Create an Opportunity</a></li><li><a href="account?userId=${userId}">Account Settings</a></li><li><a href="index">Sign Out</a></li></ul> 
    </nav>
<div class="art-sheet clearfix">
            <div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-content"><article class="art-post art-article">
                                
                                                
                <div class="art-postcontent art-postcontent-0 clearfix"><div class="art-content-layout layout-item-0">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-1" style="width: 8%" >
        <p><br></p>
    </div><div class="art-layout-cell layout-item-2" style="width: 64%" >
    	<form:form method="post" commandName="reviewIssueForm">
    	<input type="hidden" name="formname" value="reviewIssue">
        <table class="art-article" style="width: 100%; margin-top: 44px; margin-bottom: 44px; margin-left: 5px; margin-right: 5px; border-style:hidden; border-collapse:collapse; border-width: 0px;"><tbody>
		<tr><th colspan="3" align="center" style="border: none;"><span style="font-weight: bold; font-size: 22px;">${opportunity.opportunityTitle} by ${opportunitySubmitted.user.userName}</span></th></tr>
		<tr style="border: none; border-width: 0px;">
        		<td style="border: none; font-weight: bold;"><spring:message code="lbl.reviewIssue" text="Issue" /></td>
        		<td style="border: none;">
	        		 <ul>  
			            <form:select path="issueId">  
							<c:forEach items="${issueList}" var="option">
						        <option value="${option.id}" ${reviewIssueForm.issueId == option.id ? 'selected' : ''}>${option.issueTitle}</option>
						    </c:forEach>
			            </form:select>  
	        		</ul>  
                </td>
                <td style="border: none;"><form:errors path="issueId" cssClass="error" /></td>
         	</tr>
         	<tr style="border: none;">
         		<td style="border: none;font-weight: bold;"><spring:message code="lbl.resolutiondate" text="Resolution Date" /></td>
                <td style="border: none;"><form:input class="datemask" path="resolutionDate" /></td>
                <td style="border: none;"><form:errors path="resolutionDate" cssClass="error" /></td>
         	</tr>
         	<tr style="border: none;">
         		<td style="border: none;font-weight: bold;"><spring:message code="lbl.comments" text="Comments" /></td>
         		<td style="border: none;"><form:textarea path="comment" rows="6" cols="50" /></td>
         		<td style="border: none;"><form:errors path="comment" cssClass="error" /></td>
         	</tr>
         	<tr style="border: none;">
         		<td style="border: none; text-align: center; padding-top: 10px" colspan="3"> <input type="submit" value="Edit Review Issue" /></td>
         	</tr>         
          </tbody>
   	</table>
	</form:form>                                                                              	
    </div>
    <div class="art-content-layout-row">
     <div>
     	<div class="art-layout-cell layout-item-4" style="width: 28%" >
       <form:form method="post" commandName="issueForm">
       <input type="hidden" name="formname" value="issue">
        <table class="art-article" style="width: 95%; margin-top: 44px; margin-bottom: 44px; margin-left: 5px; margin-right: 15px; border-style:hidden; border-collapse:collapse; border-width: 0px;"><tbody>
		<tr><th colspan="3" align="center" style="border: none;"><span style="font-weight: bold; font-size: 16px;">Add Issue to List</span></th></tr>
		<tr style="border: none; border-width: 0px;">
        		<td style="border: none; font-weight: bold;"><spring:message code="lbl.issueTitle" text="Issue Title" /></td>
        		<td style="border: none;"><form:input type="text" path="issueTitle" /></td>
                <td style="border: none;"><form:errors path="issueTitle" cssClass="error" /></td>
         	</tr>
         	<tr style="border: none;">
         		<td style="border: none;font-weight: bold;"><spring:message code="lbl.issuedescription" text="Description" /></td>
         		<td style="border: none;"><form:textarea path="description" rows="10" cols="30" /></td>
         		<td style="border: none;"><form:errors path="description" cssClass="error" /></td>
         	</tr>
         	<tr style="border: none;">
         		<td style="border: none; text-align: center; padding-top: 10px" colspan="3"> <input type="submit" value="Add Item" /></td>
         	</tr>         
          </tbody>
   </table>
</form:form> 
     </div>  
    </div>
</div>
</div>

</article></div>
                    </div>
                </div>
            </div>
</div>
    </div>
<footer class="art-footer">
<p><a href="about?userId=${userId}" style="font-size: 13px;"><span style="color: rgb(22, 156, 227);">About</span></a><a href="#"></a></p>
<p>Copyright © 2015. All Rights Reserved.</p>
</footer>

    </div>
  
</div>
     </body>
</html>