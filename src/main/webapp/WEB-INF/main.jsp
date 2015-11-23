<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="edu.stevens.ssw690.DuckSource.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head>
    <meta charset="utf-8">
    <title>${user}'s Homepage</title>
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
    <ul class="art-hmenu"><li><a href="index" class="active">Home Page</a></li><li><a href="find-an-opportunity.html">Find an Opportunity</a></li><li><a href="createopp.html?creatorId=${userId}">Create an Opportunity</a>></li><li><a href="account-settings.html">Account Settings</a></li><li><a href="sign-out.html">Sign Out</a></li></ul> 
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
    <div class="art-layout-cell layout-item-3" style="width: 76%" >
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
                        
                        
                        <h1><span style="font-weight: bold; color: #169CE3; "><span style="font-size: 28px; color: #000000; ">Current Accepted Opportunities:</span><br></span></h1><br><table class="art-article"><tbody><tr><td colspan="2" style="width: 100%; text-align: left; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px; "><span style="font-size: 18px; font-weight: bold; ">Filter by opportunity type : </td></tr>
						<tr style="border: none;"><td style="border: none; padding-right:10px;"><select name="OpportunityType" style="width: 300px; ">
                                                                                                                                                                                                                                                                                                          <option value="All types" selected="">All types
                                                                                                                                                                                                                                                                                                          </option><option value="Design">Design
                                                                                                                                                                                                                                                                                                          </option><option value="Development">Development
                                                                                                                                                                                                                                                                                                          </option><option value="Data Science">Data Science
                                                                                                                                                                                                                                                                                                          </option><option value="Engineering">Engineering
                                                                                                                                                                                                                                                                                                        </option></select>
                                                                                                                                                                                                                                                                                                                </span></td>
                                                                                                                                                                                                                                                                                                                        <td style="border: none;"><span style="font-size: 16px; font-weight: bold; "><a href="">Update</a></span></td></tr></tbody></table><br><table class="art-article" style="margin-bottom: 3px; margin-top: 3px; width: 75%; margin-right: auto; margin-left: auto; "><tbody><tr>
                        	<td style="border-width: 1px; text-align: center; width: 12%; " class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Type</span></td>
                        	<td style="border-width: 1px; text-align: center; width: 18%; " class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Title&nbsp;</span></td>
                        	<td style="border-width: 1px; text-align: center; width: 10%; " class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Duck Bills</span></td>
                        	<td style="border-width: 1px; text-align: center; width: 10%; " class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Submitted 
                			On</span></td>
                        	<td style="border-width: 1px; text-align: center; width: 12%; " class="auto-style1">
                			<span class="auto-style2">Status</span><br></td></tr><tr>
                		<td style="border-width: 1px; text-align: center; " colspan="5"><span style="font-weight: bold; ">NO open opportunities to display </span><br></td></tr></tbody></table><p style="text-align: center; "><span style="border-collapse: collapse; -webkit-border-horizontal-spacing: 2px; -webkit-border-vertical-spacing: 2px; "><br></span></p><p><span style="font-weight: bold; color: #169CE3; "><br></span></p><p><span style="font-weight: bold; color: #169CE3; "><br></span></p><p><br></p><p><br></p>
    </div><div class="art-layout-cell layout-item-4" style="width: 24%" >
    	<table>
    		<tr style="border: none;">
    			<td style="border: none;" colspan="2">
    				<img width="45" height="80" alt="" src="<c:url value="/resources/images/medal.png" />"style="float: left; margin-top: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: 0px; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px;">
    			</td>
    		</tr>
    		<tr style="border: none;">
    			<td style="border: none;" colspan="2"><span style="font-size: 26px; font-variant: small-caps; font-weight: bold; line-height: 31px; color: #F4F5F6;">Reputation</span>
    			</td>
    		</tr>
    		<tr>
    			<td style="border: none;">
    				<span style="font-weight: bold;"><span style="font-family: 'Arial Black'; font-size: 16px;">#</span></span>
    			</td>	
    			<td style="border: none;">
    				<span style="font-weight: bold; font-family: 'Arial Black'; font-size: 16px;">Points :</span>
    			</td>
    		</tr>
    	</table>
    </div>
    </div>
</div>
<div class="art-content-layout layout-item-2">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-3" style="width: 76%" >
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
                       
                        <h1><span style="font-weight: bold; color: #169CE3; "><span style="font-size: 28px; color: #000000; ">Current Offered Opportunities:</span><br></span></h1><br><table class="art-article" style="margin-bottom: 3px; margin-top: 3px; width: 75%; margin-right: auto; margin-left: auto;"><tbody>
						<tr>
							<th style="border-width: 1px; text-align: center; width: 12%; " class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Type</th>
							<th style="border-width: 1px; text-align: center; width: 12%; " class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Title</th>
							<th style="border-width: 1px; text-align: center; width: 12%; " class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">DuckBill$</th>
							<th style="border-width: 1px; text-align: center; width: 12%; " class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Register By</th>
							<th style="border-width: 1px; text-align: center; width: 12%; " class="auto-style1"><span style="font-weight: bold; font-size: 16px; text-shadow: rgba(23, 23, 23, 0.496094) 0px 1px 0px; color: #101313; ">Submit By</th>
						</tr>
						<c:forEach var="o" items="${opportunties}">
							<tr>
								<td style="border-width: 1px; text-align: center;"><span style="font-weight: bold; ">${o.opportunityTiype}</span></td>
								<td style="border-width: 1px; text-align: center;"><span style="font-weight: bold; ">${o.opportunityTitle} </span></td>
								<td style="border-width: 1px; text-align: center;"><span style="font-weight: bold; ">
									<fmt:setLocale value="en_US"/>
									<fmt:formatNumber value="${o.duckbills}" type="currency"/> </span>
								</td>
								<td style="border-width: 1px; text-align: center;">
									<c:set var="regDate">
      									<fmt:formatDate pattern="MM/dd/yyyy" value="${o.registerDate}" />
   									</c:set>
   									<span style="font-weight: bold; ">${regDate}</span>
								</td>
								<td style="border-width: 1px; text-align: center;">
									<c:set var="subDate">
      									<fmt:formatDate pattern="MM/dd/yyyy" value="${o.submitDate}" />
   									</c:set>
   									<span style="font-weight: bold; ">${subDate}</span>
								</td>
							</tr>
						</c:forEach>
						</tbody>
						</table>
						<p style="text-align: center; "><span style="border-collapse: collapse; -webkit-border-horizontal-spacing: 2px; -webkit-border-vertical-spacing: 2px; "><br></span></p><p><span style="font-weight: bold; color: #169CE3; "><br></span></p><p><span style="font-weight: bold; color: #169CE3; "><br></span></p><p><br></p><p><br></p>
    </div><div class="art-layout-cell layout-item-5" style="width: 24%" >
        <p><br></p>
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
<p><a href="/new-page" style="font-size: 13px;"><span style="color: rgb(22, 156, 227);">About</span></a><a href="#"></a></p>
<p>Copyright © 2015. All Rights Reserved.</p>
</footer>

    </div>
   </div>


</body></html>
