<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="edu.stevens.ssw690.DuckSource.*"%>

<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head>
    <meta charset="utf-8">
    <title>Create an Opportunity</title>
    <style>
    	.error{color:#ff0000;font-weight:bold;}
    </style>
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

    <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.ie7.css" media="screen" /><![endif]-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.responsive.css" media="all">


    <script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/script.responsive.js"></script>


<style>.art-content .art-postcontent-0 .layout-item-0 { color: #232929; background: #ECEEEF url('${pageContext.request.contextPath}/resources/images/6cd8d.png') scroll;  border-collapse: separate; border-radius: 15px;  }
.art-content .art-postcontent-0 .layout-item-1 { color: #212627; background: #C0C8C9 url('${pageContext.request.contextPath}/resources/images/66838.png') scroll; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-top-left-radius: 15px;border-bottom-left-radius: 15px;  }
.art-content .art-postcontent-0 .layout-item-2 { color: #212627; background: #DEE2E3 url('${pageContext.request.contextPath}/resources/images/a8c2a.png') scroll; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px;  }
.art-content .art-postcontent-0 .layout-item-3 { color: #232929; background: ; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-top-left-radius: 15px;border-bottom-left-radius: 15px;  }
.ie7 .art-post .art-layout-cell {border:none !important; padding:0 !important; }
.ie6 .art-post .art-layout-cell {border:none !important; padding:0 !important; }

</style>
 		<spring:url value="/resources/js/jquery-1.11.3.min.js" var="jqueryJs" />
       <spring:url value="/resources/js/jquery.maskedinput.min.js" var="maskedinputJs" />
        <spring:url value="/resources/js/jquery.price_format.2.0.min.js" var="priceJs" />
		<spring:url value="/resources/js/ducksource.js" var="crowdJs" />
    	<script src="${jqueryJs}"></script>
    	<script src="${maskedinputJs}"></script>
    	<script src="${priceJs}"></script>
    	<script src="${crowdJs}"></script>
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
    <ul class="art-hmenu"><li><a href="home-page.html" class="">Home Page</a></li><li><a href="find-an-opportunity.html" class="">Find an Opportunity</a></li><li><a href="create-an-opportunity.html" class="active">Create an Opportunity</a></li><li><a href="account-settings.html">Account Settings</a></li><li><a href="sign-out.html">Sign Out</a></li></ul> 
    </nav>
<div class="art-sheet clearfix">
            <div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-content"><article class="art-post art-article">
                                
                                                
                <div class="art-postcontent art-postcontent-0 clearfix"><div class="art-content-layout layout-item-0">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-1" style="width: 13%" >
        <p><br></p>
    </div><div class="art-layout-cell layout-item-2" style="width: 74%" >                                                                              	
    </div><div class="art-layout-cell layout-item-3" style="width: 13%" >
        <p><br></p>
    </div>
    </div>
</div>
</div>

</article></div>
                    </div>
                </div>
            </div>
<form:form method="post" commandName="opportunityForm">
        <table class="art-article" style="width: 64%; margin-top: 44px; margin-bottom: 44px; margin-left: 44px; margin-right: 44px; border: none;"><tbody>
		<tr><th colspan="3" align="center">Create an Opportunity</th></tr>
		<tr>
        		<td><spring:message code="lbl.opportunitytype" text="Type" /></td>
        		<td>
	        		 <ul>  
			            <form:select path="opportunityType">  
			                <form:option value="Design">Design</form:option>  
			                <form:option value="Development">Development</form:option>  
			                <form:option value="Data Science">Data Science</form:option>  
			                <form:option value="Engineering">Engineering</form:option>  
			            </form:select>  
	        		</ul>  
                </td>
                <td><form:errors path="opportunityType" cssClass="error" /></td>
         	</tr>
         	<tr>
         		<td><spring:message code="lbl.opportunitytitle" text="Title" /></td>
                <td><form:input path="opportunityTitle" /></td>
                <td><form:errors path="opportunityTitle" cssClass="error" /></td>
         	</tr>
         	<tr>
         		<td><spring:message code="lbl.duckbills" text="Duck Bill$" /></td>
                <td><form:input class="moneymask" path="duckbills" /></td>
                <td><form:errors path="duckbills" cssClass="error" /></td>
         	</tr>
         	<tr>
         		<td><spring:message code="lbl.registerdate" text="Register By" /></td>
                <td><form:input class="datemask" path="registerDate" /></td>
                <td><form:errors path="registerDate" cssClass="error" /></td>
         	</tr>
         	<tr>
         		<td><spring:message code="lbl.submitDate" text="Submit By" /></td>
                <td><form:input class="datemask" path="submitDate" /></td>
                <td><form:errors path="submitDate" cssClass="error" /></td>
         	</tr>
         	<tr>
         		<td><spring:message code="lbl.description" text="Description" /></td>
         		<td><form:textarea path="description" rows="4" cols="50" /></td>
         		<td><form:errors path="description" cssClass="error" /></td>
         	</tr>
         	<tr>
         		<td colspan="3" align="center"> <input type="submit" value="Create" /></td>
         	</tr>         
          </tbody>
   </table>
</form:form>
<footer class="art-footer">
<p><a href="/new-page" style="font-size: 13px;"><span style="color: rgb(22, 156, 227);">About</span></a><a href="#"></a></p>
<p>Copyright © 2015. All Rights Reserved.</p>
</footer>

    </div>
  
</div>
     </body>
</html>