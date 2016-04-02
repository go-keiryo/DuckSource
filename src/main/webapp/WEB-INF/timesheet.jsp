<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="edu.stevens.ssw690.DuckSource.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html dir="ltr" lang="en-US"><head>
    <meta charset="utf-8">
    <title>Time Sheet</title>
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

   <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="<c:url value="/resources/css/style.ie7.css" />" media="screen" /><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.responsive.css" />" media="all">
    <link rel="stylesheet" href="<c:url value="/resources/css/TimeSheet.css" />" media="all">

    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/script.js" />"></script>
    <script src="<c:url value="/resources/js/script.responsive.js" />"></script>
    <script src="<c:url value="/resources/js/findopp.js" />"></script>
    <script src="<c:url value="/resources/js/TimeSheet.js" />"></script>
    <script type="text/javascript">

    var month;
    var year;
    var dimensions;
    var sheetData = [];
    var dayList = [];
    var hourList = [
        {name:"12a",title:"12am-1am"},{name:"1a",title:"1am-2am"},{name:"2a",title:"2am-3am"},{name:"3a",title:"3am-4am"},
        {name:"4a",title:"4am-5am"},{name:"5a",title:"5am-6am"},{name:"6a",title:"6am-7am"},{name:"7a",title:"7am-8am"},
        {name:"8a",title:"8am-9am"},{name:"9a",title:"9am-10am"},{name:"10a",title:"10am-11am"},{name:"11a",title:"11am-12pm"},
        {name:"12p",title:"12pm-1pm"},{name:"1p",title:"1pm-2pm"},{name:"2p",title:"2pm-3pm"},{name:"3p",title:"3pm-4pm"},
        {name:"4p",title:"4pm-5pm"},{name:"5p",title:"5pm-6pm"},{name:"6p",title:"6pm-7pm"},{name:"7p",title:"7pm-8pm"},
        {name:"8p",title:"8pm-9pm"},{name:"9p",title:"9pm-10pm"},{name:"10p",title:"10pm-11pm"},{name:"11p",title:"11pm-12am"}
    ];

    var updateRemark = function(sheet) {

        var sheetStates = sheet.getSheetStates();
        var rowsCount = dimensions[0];
        var colsCount = dimensions[1];
        var rowRemark = [];
        var rowRemarkLen = 0;
        var remarkHTML = '';

        $("#dirty").val(1);
        
        for(var row= 0, rowStates=[]; row<rowsCount; ++row){
            rowRemark = [];
            rowStates = sheetStates[row];
            for(var col=0; col<colsCount; ++col){
                if(rowStates[col]===0 && rowStates[col-1]===1){
                    rowRemark[rowRemarkLen-1] += (col<=10?'0':'')+col+':00';
                }else if(rowStates[col]===1 && (rowStates[col-1]===0 || rowStates[col-1]===undefined)){
                    rowRemarkLen = rowRemark.push((col<=10?'0':'')+col+':00-');
                }
                if(rowStates[col]===1 && col===colsCount-1){
                    rowRemark[rowRemarkLen-1] += '00:00';
                }
            }
            remarkHTML = rowRemark.join(",");
            sheet.setRemark(row,remarkHTML==='' ? sheet.getDefaultRemark() : remarkHTML);
        }
    };

    $(document).ready(function(){
    	
    	var timeData = eval('('+'${timeData}'+')'); 
    	month = "${displayMonth}";
    	year = "${displayYear}";
    	
    	var date = null
    	if (month === "" || year === "" ) {
    		date = new Date();
    		month =  date.getMonth();
    		year = date.getFullYear();
    	} else {
    		 date = new Date(year,month,1);
    	}
    	
    	$("#displayMonth").val(month);
        $("#displayYear").val(year);
        $("#newMonth").val(month);
        $("#newYear").val(year);
        $("#save").val(1);
           
    	var startDate = new Date(date.getFullYear(), date.getMonth(), 0);
    	var endDate = new Date(date.getFullYear(), date.getMonth() + 1, 0);
    	var days = endDate.getDate();
    	dimensions = [days,24];
    	
        for (var i=0; i<days; i++) {
        	startDate.setDate(startDate.getDate() + 1);
        	var dd = startDate.getDate();
        	var mm = startDate.getMonth()+1;
        	var yyyy = startDate.getFullYear(); 
        	var dispDate = mm + "/" + dd + "/" + yyyy; 
    		dayList[i] =  {name:dispDate};
    		sheetData[i] = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
    		if (timeData[i] != undefined) {
    			timeData[i] = timeData[i].replace("[","").replace("]","");
    			var currData = timeData[i].split(",");
    			for (var j=0; j<24; j++) {
    				sheetData[i][j] = +currData[j];
    			}
    		}
    	}
    	
        var sheet = $("#J_timedSheet").TimeSheet({
            data: {
                dimensions : dimensions,
                colHead : hourList,
                rowHead : dayList,
                sheetHead : {name:"Date\\Time"},
                sheetData : sheetData
            },
            remarks : {
                title : "Hours",
                default: " "
            },
            end : function(ev,selectedArea){
                updateRemark(sheet);
            }
        });

        updateRemark(sheet);
        $("#dirty").val(0);
        
        $("#prevMonth").click(function(ev){
        	month = $("#displayMonth").val();
            year = $("#displayYear").val();
            month--;
            if (month === -1) {
            	month = 11;
            	year--;
            }
            
            $("#newMonth").val(month);
            $("#newYear").val(year);
            
            var dirty = $("#dirty").val();
            if (+dirty === 1) {
            	var s = confirm("Save current Time Sheet?");
            	if (s == true) {
            		$("#save").val(1);
            	} else {
            		$("#save").val(0);
            	}
            }
            
            $("#submit").click();
        });
        
		$("#nextMonth").click(function(ev){
			month = $("#displayMonth").val();
            year = $("#displayYear").val();
            month++;
            if (month === 12) {
            	month = 0;
            	year++;
            }
            
            $("#newMonth").val(month);
            $("#newYear").val(year);
            
            var dirty =  $("#dirty").val();
            if (+dirty === 1) {
            	var s = confirm("Save current Time Sheet?");
            	if (s == true) {
            		$("#save").val(1);
            	} else {
            		$("#save").val(0);
            	}
            }
            
            $("#submit").click();
            
        });

        $("#submit").click(function(ev){

        	 var sheetStates = sheet.getSheetStates();
             var rowsCount = dimensions[0];
             var colsCount = dimensions[1];
             var rowRemark = [];
             var rowTime = [];
             var rowRemarkLen = 0;
             var remark = '';
             
             for(var row = 0, rowStates=[]; row<rowsCount; ++row){
                 rowRemark = [];
                 rowStates = sheetStates[row];
                 for(var col=0; col<colsCount; ++col){
                     if(rowStates[col]===0 && rowStates[col-1]===1){
                         rowRemark[rowRemarkLen-1] += (col<=10?'0':'')+col+':00';
                     }else if(rowStates[col]===1 && (rowStates[col-1]===0 || rowStates[col-1]===undefined)){
                         rowRemarkLen = rowRemark.push((col<=10?'0':'')+col+':00-');
                     }
                     if(rowStates[col]===1 && col===colsCount-1){
                         rowRemark[rowRemarkLen-1] += '00:00';
                     }
                 }
                 
                remark = rowRemark.join("|");
                rowTime[row] = remark==='' ? " " : remark;
             }
             $("#timeData").val(rowTime).toString();
            
        });
    });
    
</script>


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
                                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                <table class="art-article" style="width: 100%; ">
                                                                                                                                                                                                                                                                                <tbody>
                                                                                                                                                                                                                              
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
    <div class="art-layout-cell layout-item-7" style="width: 5%" >
        <p><br></p>
    </div><div class="art-layout-cell layout-item-8" style="width: 90%" >
    <h1>${title}</h1>
	<div id="J_calenderWrapper">
	    <table>
	        <thead></thead>
	        <tbody id="J_timedSheet">
	
	        </tbody>
	    </table>
	</div>
	<p id="J_dataDisplay" style="color:#aaaaaa;font-family: 'Arial';">
	
	</p>
	
    	<form:form commandName="timesheet" method="post">
    	<table class="art-article" style="width: 100%; ">
        	<tbody>
        		<tr style="border: none;">
        			<td style="border: none; width:50%; text-align:left">
        				<input class="art-button" type="button" id="prevMonth" value="Previous" />
        			</td>
        			<td style="border: none; width:50%; text-align:right">
        				<input class="art-button" type="button" id="nextMonth" value="Next" />
        			</td>
        		</tr>
        		<tr style="border: none;">
                   <td style="border: none;" colspan="2">
                   		<input type="hidden" id="timeData" name="timeData" /> 
                   		<input type="hidden" id="displayMonth" name="displayMonth" />
                   		<input type="hidden" id="displayYear" name="displayYear" /> 
                   		<input type="hidden" id="newMonth" name="newMonth" />
                   		<input type="hidden" id="newYear" name="newYear" /> 
                   		<input type="hidden" id="save" name="save" />
                   		<input type="hidden" id="dirty" name="dirty" /> 
                   </td>
                </tr>
                 <tr>
                   	<td style="border: none; text-align: center; padding-top: 10px" colspan="2">
                   		<input class="art-button" type="submit" id="submit" value="Submit" />
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