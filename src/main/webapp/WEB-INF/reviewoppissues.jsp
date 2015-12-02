<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="edu.stevens.ssw690.DuckSource.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head>
    <meta charset="utf-8">
    <title>Review Issues</title>
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

    <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="<c:url value="/resources/css/style.ie7.css" />" media="screen" /><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.responsive.css" />" media="all">


    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/script.js" />"></script>
    <script src="<c:url value="/resources/js/script.responsive.js" />"></script>


<style>.art-content .art-postcontent-0 .layout-item-0 { margin-bottom: 0px;  }
.art-content .art-postcontent-0 .layout-item-1 { padding-right: 10px;padding-left: 10px;  }
.art-content .art-postcontent-0 .layout-item-2 { border-top-style:solid;border-right-style:solid;border-bottom-style:solid;border-left-style:solid;border-width:0px;border-top-color:#B7C1C2;border-right-color:#B7C1C2;border-bottom-color:#B7C1C2;border-left-color:#B7C1C2; color: #363E3F; background: ; border-spacing: 5px 5px; border-collapse: separate; border-radius: 15px;  }
.art-content .art-postcontent-0 .layout-item-3 { border-top-style:solid;border-right-style:solid;border-bottom-style:solid;border-left-style:solid;border-top-width:1px;border-right-width:1px;border-bottom-width:1px;border-left-width:1px;border-top-color:#B7C1C2;border-right-color:#B7C1C2;border-bottom-color:#B7C1C2;border-left-color:#B7C1C2; color: #363E3F; padding: 12px; border-radius: 10px;  }
.art-content .art-postcontent-0 .layout-item-4 { border-top-style:solid;border-right-style:solid;border-bottom-style:solid;border-left-style:solid;border-top-width:1px;border-right-width:1px;border-bottom-width:1px;border-left-width:1px;border-top-color:#B7C1C2;border-right-color:#B7C1C2;border-bottom-color:#B7C1C2;border-left-color:#B7C1C2; color: #B5BEC0; background: #3D3D3D url('<c:url value='/resources/images/67e7e.png' />') scroll; padding: 12px; border-radius: 10px;  }
.art-content .art-postcontent-0 .layout-item-5 { color: #000000; background: ; padding: 12px; border-radius: 10px;  }
.ie7 .art-post .art-layout-cell {border:none !important; padding:0 !important; }
.ie6 .art-post .art-layout-cell {border:none !important; padding:0 !important; }

</style>
<style type="text/css">
   .auto-style1 {
      background-color: #B7E4FF;
    }
    .auto-style2 {
       font-weight: bold;
       font-size: 16px;
       color: #101313;
    }
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
    <ul class="art-hmenu"><li><a href="main?userId=${userId}" class="">Home Page</a></li><li><a href="findopp?userId=${userId}">Find an Opportunity</a></li><li><a href="createopp?userId=${userId}">Create an Opportunity</a>></li><li><a href="reviewopp?userId=${userId}&oppId=${oppId}&subId=${subId}">Review</a></li><li><a href="account?userId=${userId}">Account Settings</a></li><li><a href="index">Sign Out</a></li></ul> 
    </nav>
<div class="art-sheet clearfix">
            <div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-content"><article class="art-post art-article">
                                
                                                
                <div class="art-postcontent art-postcontent-0 clearfix"><div class="art-content-layout-wrapper layout-item-0">
<div class="art-content-layout">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-1" style="width: 100%" >
        <p><br></p>
    </div>
    </div>
</div>
</div>
<div class="art-content-layout layout-item-2">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-3" style="width: 100%" >                         
                        <h1><span style="font-weight: bold; color: #169CE3; "><span style="font-size: 28px; color: #000000; ">${opportunity.opportunityTitle} by ${submission.user.userName}</span><br></span></h1><br><br>
                        <table class="art-article" style="margin-bottom: 3px; margin-top: 3px; width: 75%; margin-right: auto; margin-left: auto;"><tbody>
						<tr>
							<th style="border-width: 1px; text-align: center " class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Created</span></th>
							<th style="border-width: 1px; text-align: center;" class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Issue</span></th>
							<th style="border-width: 1px; text-align: center;" class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Resolved</span></th>
							<th style="border-width: 1px; text-align: center;" class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Comments</span></th>
							<th style="border-width: 1px; text-align: center;" class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Action</span></th>
						</tr>
						<c:choose>
						    <c:when test="${empty reviewIssues}">
						    	<tr><td colspan="5" style="border-width: 1px; text-align: center;"><span style="font-weight: bold; ">No Reviews Issues Available</span></td></tr>
						    </c:when>    
						    <c:otherwise>
							<c:forEach var="r" items="${reviewIssues}">
							<tr>
								<td style="border-width: 1px; text-align: left; vertical-align: middle;">
									<span style="font-weight: bold; ">
										<c:set var="createDate">
      										<fmt:formatDate pattern="MM/dd/yyyy" value="${r.creationDate}" />
   										</c:set>
   										${createDate}
									</span>
								</td>
								<td style="border-width: 1px; text-align: left; vertical-align: middle;">
									<span style="font-weight: bold; ">
									${r.issueTitle}
									</span>
								</td>
								<td style="border-width: 1px; text-align: left; vertical-align: middle;">
									<span style="font-weight: bold; ">
										<c:set var="resolveDate">
      										<fmt:formatDate pattern="MM/dd/yyyy" value="${r.resolutionDate}" />
   										</c:set>
   										${resolvedDate}
									</span>
								</td>
								<td style="border-width: 1px; text-align: left; vertical-align: middle;">
									${r.comment}
								</td>
								<td style="border-width: 1px; text-align: left; vertical-align: middle;">
						    		<a href="editissue?userId=${userId}&oppId=${opportunity.id}&subId=${submission.id}&reviewId=${r.id}">Edit</a> <a href="deleteissue?userId=${userId}&oppId=${opportunity.id}&subId=${submission.id}&reviewId=${r.id}">Delete</a>
								</td>
							</tr>
						</c:forEach>
						</c:otherwise>
						</c:choose>
						</tbody>
						</table>
                        <p style="text-align: center; "><span style="border-collapse: collapse; -webkit-border-horizontal-spacing: 2px; -webkit-border-vertical-spacing: 2px; "><a href="addissue?userId=${userId}&oppId=${opportunity.id}&subId=${submission.id}&loc=1">Add Review Issue</a></span></p><p><span style="font-weight: bold; color: #169CE3; "><br></span></p><p><span style="font-weight: bold; color: #169CE3; "><br></span></p><p><br></p><p><br></p>
    </div>
    </div>
    </div>
</div>
<div style="width:100%;height:100%" align="center">
		
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
