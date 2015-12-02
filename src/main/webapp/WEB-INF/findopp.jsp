<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="edu.stevens.ssw690.DuckSource.*"%>

<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head>
    <meta charset="utf-8">
    <title>Find an Opportunity</title>
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

     <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="<c:url value="/resources/css/style.ie7.css" />" media="screen" /><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.responsive.css" />" media="all">

    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/script.js" />"></script>
    <script src="<c:url value="/resources/js/script.responsive.js" />"></script>
    <script src="<c:url value="/resources/js/findopp.js" />"></script>
    

<style>.art-content .art-postcontent-0 .layout-item-0 {  border-collapse: separate;  }
.art-content .art-postcontent-0 .layout-item-1 { color: #212627; background: #E1E5E5 url(<c:url value='/resources/images/ecf41.png' />') scroll; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-radius: 15px;  }
.art-content .art-postcontent-0 .layout-item-2 { border-top-style:solid;border-right-style:solid;border-bottom-style:solid;border-left-style:solid;border-top-width:1px;border-right-width:1px;border-bottom-width:1px;border-left-width:1px;border-top-color:#B7C1C2;border-right-color:#B7C1C2;border-bottom-color:#B7C1C2;border-left-color:#B7C1C2; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-radius: 15px;  }
.art-content .art-postcontent-0 .layout-item-3 { color: #212627; background: #E1E5E5 url(<c:url value='/resources/images/32359.png' />') scroll; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-radius: 15px;  }
.ie7 .art-post .art-layout-cell {border:none !important; padding:0 !important; }
.ie6 .art-post .art-layout-cell {border:none !important; padding:0 !important; }
</style>
<style>
    .auto-style1 {
    	background-color: #B7E4FF;
    }
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
    <ul class="art-hmenu"><li><a href="main?userId=${userId}">Home Page</a></li><li><a href="findopp?userId=${userId}" class="active">Find an Opportunity</a></li><li><a href="createopp?userId=${userId}">Create an Opportunity</a></li><li><a href="account?userId=${userId}">Account Settings</a></li><li><a href="index">Sign Out</a></li></ul> 
    </nav>
<div class="art-sheet clearfix">
            <div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-content"><article class="art-post art-article">
                                
                                                
                <div class="art-postcontent art-postcontent-0 clearfix"><div class="art-content-layout layout-item-0">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-1" style="width: 12%" >
        <p><br /></p>
    </div><div class="art-layout-cell layout-item-2" style="width: 76%" >
                
                
                <h1><span style="font-weight: bold; color: #169CE3; "><span style="font-size: 28px; color: #000000; ">Current Open Opportunities:</span><br></span></h1><br>
                <table class="art-article"><tbody><tr></tr></tbody></table><table class="art-article">
                                                <tbody>
                                                	<tr style="border: none;">
                                                		<td colspan="3" style="text-align: left; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; "><span style="font-size: 18px; font-weight: bold; ">Filter by opportunity type : </span>
                                                		</td>
                                                		</tr>
                                                		<tr>
                                                		<td style="border: none; padding-left:10px; padding-right:10px;">
                                                			<select id="oppselect" name="OpportunityType" style="width: 300px; ">
                                                            	<option value="All types">All types</option>
                                                            	<option value="Design">Design</option>
                                                            	<option value="Development">Development</option>
                                                            	<option value="Data Science">Data Science</option>
                                                            	<option value="Engineering">Engineering</option>
                                                            </select>
                                                        </td>
                                                        <td style="border: none;">                                                                                                                                                                                                                                                                
                                                            <span style="font-size: 16px; font-weight: bold; "><a id="oppupdate" href="findupdate?userId=${userId}&" class="art-button" onclick="addOpportunityTypeToURL(this,'oppselect')">Update</a></span>
                                                        </td>
                                                    </tr>
                                                 </tbody>
                                                </table>
                                                <br>
                                                <table class="art-article" style="margin-bottom: 3px; margin-top: 3px; width: 75%; margin-right: auto; margin-left: auto; "><tbody>
                                                <tr>
							<th style="border-width: 1px; text-align: center;" class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Type</span></th>
							<th style="border-width: 1px; text-align: center;" class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Title</span></th>
							<th style="border-width: 1px; text-align: center;" class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">DuckBill$</span></th>
							<th style="border-width: 1px; text-align: center;" class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Register By</span></th>
							<th style="border-width: 1px; text-align: center;" class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Submit By</span></th>
							<th style="border-width: 1px; text-align: center;" class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; "></span>Action</th>
						</tr>
						<c:choose>
						    <c:when test="${empty opportunities}">
						    	<tr><td colspan="6" style="border-width: 1px; text-align: center;"><span style="font-weight: bold; ">No Opportunities Available</span></td></tr>
						    </c:when>    
						    <c:otherwise>
							<c:forEach var="o" items="${opportunities}">
							<tr>
								<td style="border-width: 1px; text-align: left;"><span style="font-weight: bold; ">${o.opportunityType}</span></td>
								<td style="border-width: 1px; text-align: left;"><span style="font-weight: bold; "><a href="oppdetail.html?oppId=${o.id}&userId=${userId}">${o.opportunityTitle}</a></span></td>
								<td style="border-width: 1px; text-align: right;"><span style="font-weight: bold; ">
									<fmt:setLocale value="en_US"/>
									<fmt:formatNumber value="${o.duckbills}" type="currency"/> </span>
								</td>
								<td style="border-width: 1px; text-align: left;">
									<c:set var="regDate">
      									<fmt:formatDate pattern="MM/dd/yyyy" value="${o.registerDate}" />
   									</c:set>
   									<span style="font-weight: bold; ">${regDate}</span>
								</td>
								<td style="border-width: 1px; text-align: left;">
									<c:set var="subDate">
      									<fmt:formatDate pattern="MM/dd/yyyy" value="${o.submitDate}" />
   									</c:set>
   									<span style="font-weight: bold; ">${subDate}</span>
								</td>
								<td style="border-width: 1px;">
									<a href="register?userId=${userId}&oppId=${o.id}">Register</a>
								</td>
							</tr>
						</c:forEach>
						</c:otherwise>
						</c:choose>
						</tbody>
						</table>
    </div><div class="art-layout-cell layout-item-3" style="width: 12%" >
        <p><br /></p>
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