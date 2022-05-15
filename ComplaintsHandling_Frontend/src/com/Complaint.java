package com;
import java.sql.*;

public class Complaint {
//Database Connection
private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", "gethmi*1223");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
//Get all complaints
public String readComplaints() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table width = 101%;><tr><th>Account Number</th><th>Complaint Date</th><th>New Complaint</th>"
					+ "<th>Anything More To Tell</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from complaints";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String complaintID = Integer.toString(rs.getInt("complaintID"));
				String accountNumber = Integer.toString(rs.getInt("accountNumber"));
				String complaintDate = rs.getString("complaintDate");
				String nComplaint = rs.getString("nComplaint");
				String anythingMoreToTell = rs.getString("anythingMoreToTell");
				// Add into the html table
				output += "<tr><td><input id='hidComplaintIDUpdate' name='hidComplaintIDUpdate' type='hidden' value='" + complaintID
						+ "'>" + accountNumber + "</td>";
				output += "<td>" + complaintDate + "</td>";
				output += "<td>" + nComplaint + "</td>";
				output += "<td>" + anythingMoreToTell + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-complaintID='"
						+ complaintID + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the complaints.";
			System.err.println(e.getMessage());
		}
		return output;
	}

//Insert Complaint
public String insertComplaint(String accountnumber, String complaintdate, String ncomplaint, String anythingmoretotell) {
	String output = "";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database for inserting.";
		}
		// create a prepared statement
		String query = " insert into complaints(`complaintID`,`accountNumber`,`complaintDate`,`nComplaint`,`anythingMoreToTell`)"

				+ " values (?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setInt(2,Integer.parseInt(accountnumber));
		preparedStmt.setString(3, complaintdate);
		preparedStmt.setString(4, ncomplaint);
		preparedStmt.setString(5, anythingmoretotell);
		// execute the statement
		preparedStmt.execute();
		con.close();
		String newComplaints = readComplaints();
		output = "{\"status\":\"success\", \"data\": \"" + newComplaints + "\"}";
	} catch (Exception e) {
		output = "{\"status\":\"error\", \"data\":\"Error while inserting the complaint.\"}";
		System.err.println(e.getMessage());
	}
	return output;
}

//Update complaint
public String updateComplaint(String ID, String accountnumber, String complaintdate, String ncomplaint, String anythingmoretotell) {
	String output = "";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database for updating.";
		}
		// create a prepared statement
		String query = "UPDATE complaints SET accountNumber=?,complaintDate=?,nComplaint=?,anythingMoreToTell=? WHERE complaintID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setInt(1,Integer.parseInt(accountnumber));
		preparedStmt.setString(2, complaintdate);
		preparedStmt.setString(3, ncomplaint);
		preparedStmt.setString(4, anythingmoretotell);
		preparedStmt.setInt(5, Integer.parseInt(ID));
		// execute the statement
		preparedStmt.execute();
		con.close();
		String newComplaints = readComplaints();
		output = "{\"status\":\"success\", \"data\": \"" + newComplaints + "\"}";
	} catch (Exception e) {
		output = "{\"status\":\"error\", \"data\":\"Error while updating the complaint.\"}";
		System.err.println(e.getMessage());
	}
	return output;
}

//Delete Complaint
public String deleteComplaint(String complaintID) {
	String output = "";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database for deleting.";
		}
		// create a prepared statement
		String query = "delete from complaints where complaintID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(complaintID));
		// execute the statement
		preparedStmt.execute();
		con.close();
		String newComplaints = readComplaints();
		output = "{\"status\":\"success\", \"data\": \"" + newComplaints + "\"}";
	} catch (Exception e) {
		output = "{\"status\":\"error\", \"data\":\"Error while deleting the complaint.\"}";
		System.err.println(e.getMessage());
	}
	return output;
}
}
