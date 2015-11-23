<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="edu.stevens.ssw690.DuckSource.*"%>

<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head>
    <meta charset="utf-8">
    <title>Sign Up</title>
    <style>
    	.error{color:#ff0000;font-weight:bold;}
    </style>
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

    <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="<c:url value="/resources/css/style.ie7.css" />" media="screen" /><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/ccs/style.responsive.css" />" media="all">


    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/script.js" />"></script>
    <script src="<c:url value="/resources/js/script.responsive.js" />"></script>
    

<style>.art-content .art-postcontent-0 .layout-item-0 { color: #232929; background: #ECEEEF url('<c:url value='/resources/images/6cd8d.png' />') scroll;  border-collapse: separate; border-radius: 15px;  }
.art-content .art-postcontent-0 .layout-item-1 { color: #212627; background: #C0C8C9 url('<c:url value='/resources/images/66838.png' />') scroll; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-top-left-radius: 15px;border-bottom-left-radius: 15px;  }
.art-content .art-postcontent-0 .layout-item-2 { color: #212627; background: #DEE2E3 url('<c:url value='/resources/images/a8c2a.png' />') scroll; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px;  }
.art-content .art-postcontent-0 .layout-item-3 { color: #232929; background: ; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-top-left-radius: 15px;border-bottom-left-radius: 15px;  }
.ie7 .art-post .art-layout-cell {border:none !important; padding:0 !important; }
.ie6 .art-post .art-layout-cell {border:none !important; padding:0 !important; }

</style></head>
<body>
<div id="art-main">
<nav class="art-nav">
    <ul class="art-hmenu"><li><a href="index.html">Main Page</a></li><li><a href="sign-up.html">Sign Up</a></li></ul> 
    </nav>

<header class="art-header">

    <div class="art-shapes">
        <div class="art-textblock art-object624133700">
        <div class="art-object624133700-text-container">
        <div class="art-object624133700-text"><p style="color: rgb(123, 218, 249); font-weight: bold; font-style: normal; text-decoration: none; font-variant: small-caps; "><span class="Apple-style-span" style="font-family: 'Comic Sans MS'; font-size: 36px;">Duck <span style="color: rgb(226, 52, 29); ">Source</span></span></p></div>
    </div>
    
</div>
            </div>






                
                    
</header>
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
        <form:form method="POST" commandName="userForm"><table class="art-article" style="margin-right: auto; width: 64%; margin-top: 44px; margin-bottom: 44px; margin-left: 44px; border-style:hidden; border-collapse:collapse; border-width: 0px;"><tbody><tr>
        		<th colspan="3" align="center" style="border: none;"><span style="font-weight: bold; font-size: 22px;">Sign Up</span></th>
           </tr>
           <tr>
        		<td style="border: none; font-weight: bold;"><spring:message code="lbl.firstname" text="First Name" /></td>
        		<td style="border: none;"><form:input type="text" path="firstName" /></td>
        		<td style="border: none;"><form:errors path="firstName" cssClass="error" /></td>
        	</tr>
         	<tr style="border: none;">
         		<td style="border: none; font-weight: bold;"><spring:message code="lbl.lastname" text="Last Name" /></td>
         		<td style="border: none;"><form:input type="text" path="lastName" /></td>
         		<td style="border: none;"><form:errors path="lastName" cssClass="error" /></td>
         	</tr>
         	<tr style="border: none;">
         		<td style="border: none; font-weight: bold;"><spring:message  code="lbl.emailaddress" text="Email" /></td>
         		<td style="border: none;"><form:input type="text" path="emailAddress" /></td>
         		<td style="border: none;"><form:errors path="emailAddress" cssClass="error" /></td>
         	</tr>
            <tr style="border: none;">
         		<td style="border: none; font-weight: bold;"><spring:message code="lbl.username" text="Username" /></td>
         		<td style="border: none;"><form:input type="text" path="userName" /></td>
         		<td style="border: none;"><form:errors path="userName" cssClass="error" /></td>
         	</tr>
         	<tr style="border: none;"> 
         		<td style="border: none; font-weight: bold;"><spring:message code="lbl.password" text="Password" /></td>
         		<td style="border: none;"><form:input type="password" path="password" />
         		<td style="border: none;"><form:errors path="password" cssClass="error" /></td>
         	<tr style="border: none;"> 
         		<td style="border: none; font-weight: bold;"><spring:message code="lbl.confirmpassword" text="Confirm Password" /></td>
         		<td style="border: none;"><form:input type="password" path="confirmPassword" />
         		<td style="border: none;"><form:errors path="confirmPassword" cssClass="error" /></td>
         	</tr>
         	<tr style="border: none;">
         		<td colspan="3" style="border: none; text-align: center; padding-top: 10px;"> <input type="submit" value="Save" /></td>
         	</tr>         </tbody></table> </form:form><p>
                                                                                    </p>
    </div><div class="art-layout-cell layout-item-3" style="width: 13%" >
        <p><br></p>
    </div>
    </div>
</div>
</div>


</article></div>
                    </div>
                </div>
            </div><footer class="art-footer">
<p><a href="/new-page" style="font-size: 13px;"><span style="color: rgb(22, 156, 227);">About</span></a><a href="#"></a></p>
<p>Copyright © 2015. All Rights Reserved.</p>
</footer>

    </div>
   </div>

</body></html>