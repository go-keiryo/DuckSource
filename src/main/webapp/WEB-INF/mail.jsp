<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="edu.stevens.ssw690.DuckSource.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html dir="ltr" lang="en-US"><head>
    <title>Duck Mail</title>
    
     <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.min.css" />" media="screen">
    <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="<c:url value="/resources/css/style.ie7.css" />" media="screen" /><![endif]-->
    <link rel="stylesheet" href="<c:url value="/resources/css/style.responsive.css" />" media="all">

    <script src="<c:url value="/resources/js/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/script.js" />"></script>
    <script src="<c:url value="/resources/js/script.responsive.js" />"></script>
    <script src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
    <script src="<c:url value="/resources/js/valida.2.1.6.min.js" />"></script>
    
<style>.art-content .art-postcontent-0 .layout-item-0 { color: #232929; background: #ECEEEF url('<c:url value='/resources/images/6cd8d.png' />') scroll;  border-collapse: separate; border-radius: 15px;  }
.art-content .art-postcontent-0 .layout-item-1 { color: #212627; background: #C0C8C9 url('<c:url value='/resources/images/66838.png' />') scroll; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-top-left-radius: 15px;border-bottom-left-radius: 15px;  }
.art-content .art-postcontent-0 .layout-item-2 { color: #212627; background: #DEE2E3 url('<c:url value='/resources/images/a8c2a.png' />') scroll; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px;  }
.art-content .art-postcontent-0 .layout-item-3 { color: #232929; background: ; padding-top: 10px;padding-right: 10px;padding-bottom: 10px;padding-left: 10px; border-top-left-radius: 15px;border-bottom-left-radius: 15px;  }
.ie7 .art-post .art-layout-cell {border:none !important; padding:0 !important; }
.ie6 .art-post .art-layout-cell {border:none !important; padding:0 !important; }

</style>
<style>
   .error{color:#ff0000;font-weight:bold;}
   .bold{font-weight:bold;}
   .notbold{font-weight:normal;}
   .ui-autocomplete-input {
	  border: 1px solid #DDD !important;
	  padding-top: 0px !important;
	  z-index: 1511;
	  position: relative;
	}
	.ui-autocomplete {
	  position: absolute;
	  top: 0;
	  left: 0;
	  z-index: 1510 !important;
	  float: left;
	  display: none;
	  min-width: 160px;
	  width: 160px;
	  padding: 4px 0;
	  margin: 2px 0 0 0;
	  list-style: none;
	  background-color: #ffffff;
	  border-color: #ccc;
	  border-color: rgba(0, 0, 0, 0.2);
	  border-style: solid;
	  border-width: 1px;
	  -webkit-border-radius: 2px;
	  -moz-border-radius: 2px;
	  border-radius: 2px;
	  -webkit-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
	  -moz-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
	  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
	  -webkit-background-clip: padding-box;
	  -moz-background-clip: padding;
	  background-clip: padding-box;
	  *border-right-width: 2px;
	  *border-bottom-width: 2px;
	}
	.ui-menu-item > a.ui-corner-all {
	    display: block;
	    padding: 3px 15px;
	    clear: both;
	    font-weight: normal;
	    line-height: 18px;
	    color: #555555;
	    white-space: nowrap;
	    text-decoration: none;
	}
	.ui-state-hover, .ui-state-active {
	      color: #ffffff;
	      text-decoration: none;
	      background-color: #0088cc;
	      border-radius: 0px;
	      -webkit-border-radius: 0px;
	      -moz-border-radius: 0px;
	      background-image: none;
	}
 </style>
 <link rel="stylesheet" type="text/css" href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css"/>
    <script  src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.6/angular.min.js"></script>
    <script>
        // Full blog post at: http://www.simplygoodcode.com/2013/12/how-to-make-email-web-app-using-angular.html
        
        function EmailController($scope, $http, $filter) {
            $scope.isPopupVisible = false;
            $scope.isComposePopupVisible = false;
            $scope.composeEmail = {};
            $scope.activeTab = "inbox";
            $scope.sentEmails = [];

            $scope.forward = function () {
                $scope.isPopupVisible = false;
                $scope.composeEmail = {};
                angular.copy($scope.selectedEmail.mailMessage,$scope.composeEmail);
                
                var sent = $filter('date')(new Date($scope.composeEmail.sent), 'MM/dd/yyyy HH:mm:ss');
                
                $scope.composeEmail.body =
                    "\n-------------------------------\n"
                    + "from: " + $scope.composeEmail.user.userName + "\n"
                    + "sent: " + sent  + "\n"
                    + "to: " + $scope.composeEmail.to + "\n"
                    + "subject: " + $scope.composeEmail.subject + "\n"
                    + $scope.composeEmail.body;

                $scope.composeEmail.subject = "FW: " + $scope.composeEmail.subject;
                $scope.composeEmail.to = "";
                $scope.composeEmail.from = "${userId}";
                $scope.isComposePopupVisible = true;
            };

            $scope.reply = function () {
                // hide the view details popup
                $scope.isPopupVisible = false;

                // create an empty composeEmail object the compose 
                // email popup is bound to
                $scope.composeEmail = {};

                // copy the data from selectedEmail into composeEmail
                angular.copy($scope.selectedEmail.mailMessage,$scope.composeEmail);

                // format the date and time sent
                var sent = $filter('date')(new Date($scope.composeEmail.sent), 'MM/dd/yyyy HH:mm:ss');
                
                // edit the body to prefix it with a line and the 
                // original email information
                $scope.composeEmail.body =
                    "\n-------------------------------\n"
                    + "from: " + $scope.composeEmail.user.userName + "\n"
                    + "sent: " + sent + "\n"
                    + "to: " + $scope.composeEmail.to + "\n"
                    + "subject: " + $scope.composeEmail.subject + "\n"
                    + $scope.composeEmail.body;

                // prefix the subject with “RE:”
                $scope.composeEmail.subject = "RE: " + $scope.composeEmail.subject;

                // the email is going to the person who sent it 
                // to us so populate the `to` with `from`
                $scope.composeEmail.to = $scope.composeEmail.user.userName;

                // it’s coming from us
                $scope.composeEmail.userId = "${userId}";

                // show the compose email popup
                $scope.isComposePopupVisible = true;
            };

            $scope.sendEmail = function () {
            	if ($scope.composeEmail.to === undefined) {
            		alert("To is required");
            	} else {
            		$scope.composeEmail.to = to;
	            	$scope.composeEmail.userId = "${userId}";
	                $http.post("mailAngularJs", $scope.composeEmail).then(function (response) {
	                    $scope.isComposePopupVisible = false;
	                    $scope.composeEmail = response.data;
	                    $scope.sentEmails.splice(0, 0, $scope.composeEmail);
	                });
            	}
            };

            $scope.showComposePopup = function () {
                $scope.composeEmail = {};
                $scope.isComposePopupVisible = true;
            };

            $scope.closeComposePopup = function () {
                $scope.isComposePopupVisible = false;
            };

            $scope.showPopup = function (email) {
                $scope.selectedEmail = email;
                $scope.selectedEmail.userId = "${userId}";
                $http.post("mailReadAngularJs", $scope.selectedEmail).then(function (response) {
                	selectedEmail.isRead = true;
                });
                $scope.isPopupVisible = true;
            };

            $scope.closePopup = function () {
                $scope.isPopupVisible = false;
            };
            
            var userId = "${userId}";
            
         	$http({
         	    url: "inboxMailAngularJs", 
         	    method: "get",
         	    params: {userId: userId}
         	 }).then(function (response) {
                 $scope.emails = response.data;
             });
         	
         	$http({
         	    url: "sentMailAngularJs", 
         	    method: "get",
         	    params: {userId: userId}
         	 }).then(function (response) {
                 $scope.sentEmails = response.data;
             });
        }
    </script>
    <script>
        var to = "";
    	$(function() {
        	var users = ${users};
        	$(".userlist").autocomplete({
        	      source: users
        	});
        	$(".userlist").on('blur', function(e) {
    	    	var found = jQuery.inArray(this.value, users ) 
    	    	 if(found < 0) {
    	             this.focus();
    	             e.preventDefault();
    	             return false;
    	          } else {
    	        	 to = this.value;
    	        	  return true;
    	          }
        	});
        	 $(".form").valida();
          })
    </script>
</head>
<body>
<div id="art-main">
<nav class="art-nav">
    <ul class="art-hmenu"><li><a href="">Main Page</a></li><li><a href="signup.html">Sign Up</a></li></ul>  
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
        <h1>Duck Mail</h1>
        <div class="container" ng-app ng-controller="EmailController">
    <ul class="nav nav-tabs">
        <li ng-class="{active: activeTab == 'inbox'}">
            <a ng-click="activeTab='inbox'">Inbox</a>
        </li>
        <li ng-class="{active: activeTab == 'sent'}">
            <a ng-click="activeTab='sent'">Sent</a>
        </li>
    </ul>    
    <table ng-show="activeTab=='inbox'" width=64%>
         <thead>
			  <tr>
			     <th>From</th>
			     <th>Subject</th>
			     <th>Sent</th>
			  </tr>
		 </thead>
        <tbody>
            <tr ng-repeat="email in emails track by $index" ng-click="showPopup(email)">
                <td ng-class='{bold : email.read == false, notbold: email.read == true}'>{{ email.mailMessage.user.userName }}</td>
                <td ng-class='{bold : email.read == false, notbold: email.read == true}'>{{ email.mailMessage.subject }}</td>
                <td ng-class='{bold : email.read == false, notbold: email.read == true}'>{{ email.mailMessage.sent | date:'MM/dd/yyyy HH:mm:ss' }}</td>
            </tr>
        </tbody>
    </table>
    <table ng-show="activeTab=='sent'" width=64%>
        <thead>
			  <tr>
			     <th>To</th>
			     <th>Subject</th>
			     <th>Sent</th>
			  </tr>
		 </thead>
        <tbody>
            <tr ng-repeat="email in sentEmails track by $index" ng-click="showPopup(email)">
                <td>{{ email.mailMessage.to }}</td>
                <td>{{ email.mailMessage.subject }}</td>
                <td>{{ email.mailMessage.sent | date:'MM/dd/yyyy HH:mm:ss' }}</td>
            </tr>
        </tbody>
    </table>
    <br><br>
    <button class="btn btn-primary" ng-click="showComposePopup()">New Message</button>
    
    <div class="modal" ng-show="isPopupVisible">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true" ng-click="closePopup()">&times;</button>
            <h3>{{selectedEmail.mailMessage.subject}}</h3>
        </div>
        <div class="modal-body">
            <strong>From:</strong> {{selectedEmail.mailMessage.user.userName}}<br />
            <strong>To:</strong> {{selectedEmail.mailMessage.to}}<br />
            <strong>Date:</strong> {{selectedEmail.mailMessage.sent | date:'MM/dd/yyyy HH:mm:ss' }}<br />
            <br />
            <span style="white-space:pre">{{selectedEmail.mailMessage.body}}</span>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" ng-click="forward()">Forward</a>
            <a href="#" class="btn" ng-click="reply()">Reply</a>
            <a href="#" class="btn btn-primary" ng-click="closePopup()">Close</a>
        </div>
    </div>
    
    <div class="modal" ng-show="isComposePopupVisible">
        <div class="modal-header">
            <button type="button" class="close" ng-click="closeComposePopup()">&times;</button>
            <h3>New Message</h3>
        </div>
        <div id="dialog" class="modal-body">
            <form class="form">
               	 <input type="text" class="userlist" placeholder="To" style="width:95%;" 
                    ng-model="composeEmail.to" onEditChange required="true"><br />
                <input type="text" placeholder="Subject" style="width:95%;"
                    ng-model="composeEmail.subject"><br />
                <textarea style="width:95%;" rows="10" 
                    ng-model="composeEmail.body"></textarea>
            </form>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn"ng-click="closeComposePopup()">Close</a>
            <a href="#" class="btn btn-primary" ng-click="sendEmail()">Send</a>
        </div>
    </div>
</div>
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
<p><a href="indexabout" style="font-size: 13px;"><span style="color: rgb(22, 156, 227);">About</span></a><a href="#"></a></p>
<p>Copyright © 2015. All Rights Reserved.</p>
</footer>

    </div>
    </div>


</body></html>