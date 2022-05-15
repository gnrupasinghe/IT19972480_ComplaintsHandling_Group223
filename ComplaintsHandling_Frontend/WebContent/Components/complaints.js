$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	var status = validateComplaintForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidComplaintIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "ComplaintAPI",
		type : type,
		data : $("#frmComplaint").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onComplaintSaveComplete(response.responseText, status);
		}
	});
});

function onComplaintSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divComplaintsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidComplaintIDSave").val("");
	$("#frmComplaint")[0].reset();
}

$(document).on("click", ".btnUpdate", function(event) {
	$("#hidComplaintIDSave").val($(this).data("complaintid")); //change
	$("#accountNumber").val($(this).closest("tr").find('td:eq(0)').text()); 
	$("#complaintDate").val($(this).closest("tr").find('td:eq(1)').text()); 
	$("#nComplaint").val($(this).closest("tr").find('td:eq(2)').text());
	$("#anythingMoreToTell").val($(this).closest("tr").find('td:eq(3)').text()); 
});

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "ComplaintAPI",
		type : "DELETE",
		data : "complaintID=" + $(this).data("complaintid"),  //change
		dataType : "text",
		complete : function(response, status) {
			onComplaintDeleteComplete(response.responseText, status);
		}
	});
});

function onComplaintDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divComplaintsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENT-MODEL================================================================
function validateComplaintForm() {
	// Account Number
	if ($("#accountNumber").val().trim() == "") {
		return "Insert Account Number.";
	}
	// Complaint Date
	if ($("#complaintDate").val().trim() == "") {
		return "Insert Complaint Date.";
	}
	// New Complaint-------------------------------
	if ($("#nComplaint").val().trim() == "") {
		return "Insert New Complaint.";
	}
	// Anything More To Tell-------------------------------
	if ($("#anythingMoreToTell").val().trim() == "") {
		return "Insert Anything More To Tell.";
	}

	return true;
}
