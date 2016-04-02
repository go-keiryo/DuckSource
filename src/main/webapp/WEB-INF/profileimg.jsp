<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="edu.stevens.ssw690.DuckSource.*"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html dir="ltr" lang="en-US"><head>
    <title>Account Settings</title>
    
    <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="<c:url value="/resources/css/style.ie7.css" />" media="screen" /><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.responsive.css" />" media="all">

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
       
                                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                <meta content="en-us" http-equiv="Content-Language">
                                                                                                                                                                                                                                                                                <style type="text/css">
                                                                                                                                                                                                                                                                                .auto-style1 {
                                                                                                                                                                                                                                                                                	background-color: #DBEFF8;
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                </style>
                                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                
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
    <form:form commandName="profileimg" method="post" enctype="multipart/form-data">
        <table class="art-article" style="width: 100%; ">
        	<tbody>
        		<tr style="border: none;">
                   <td style="width: 23%; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; text-align: right; "><span style="text-align: right; "><span style="font-weight: bold; font-size: 16px;"><span style="color: rgb(38, 43, 44); ">Image:</span></span></span>
                   </td>
                   <td style="border: none; width: 77%;">
                   		<input type="file" name="file" /> 
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