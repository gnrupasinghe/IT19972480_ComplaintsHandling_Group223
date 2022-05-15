<%@page import="com.Complaint"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complaints Handling Management System</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/complaints.js"></script> 
<link rel="stylesheet" href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<body>
	<!-- As a heading -->
	<nav class="navbar navbar-dark" style="background: #990000;">
		<h3 style="color: white;margin-left:20px">Complaints Handling Management System</h3>
	</nav><br>

<!-- Create Form -->
	<div class="container">

		<form id="frmComplaint" name="frmComplaint"
			style="margin-top: 20px; margin-left: 25%; width: 500px;">

			<div class="form-group">
				<label>Account Number</label> <input type="text" name="accountNumber" id="accountNumber"
					style="margin-bottom:15px;" class="form-control" placeholder="Account Number" size="30px" required>
			</div>

			<div class="form-group">
				<label>Complaint Date</label> <input type="text" name="complaintDate"
					id="complaintDate" style="margin-bottom:15px;" class="form-control" placeholder="Complaint Date"
					size="30px" required>
			</div>

			<div class="form-group">
				<label>New Complaint</label> <input type="text" name="nComplaint"
					id="nComplaint" style="margin-bottom:15px;" class="form-control" placeholder="New Complaint"
					size="30px" required>
			</div>

			<div class="form-group">
				<label>Anything More To Tell</label> <input type="text" name="anythingMoreToTell" id="anythingMoreToTell"
					style="margin-bottom:15px;" class="form-control" placeholder="Anything More To Tell" size="30px" required>
			</div>
 
			<div class="form-group" align="right">
				<input id="btnSave" name="btnSave" type="button" value="Save" style="margin-bottom:15px;" class="btn btn-primary">
				<input type="hidden" id="hidComplaintIDSave" name="hidComplaintIDSave" value="">
			</div>

		</form>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		<div id="divComplaintsGrid">
		<%
			Complaint complaintObj = new Complaint();
			out.print(complaintObj.readComplaints());
		%>
		</div>
	</div>

</body>
</html>
