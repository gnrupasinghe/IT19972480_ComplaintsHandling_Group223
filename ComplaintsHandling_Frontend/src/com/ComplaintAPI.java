package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ComplaintAPI")
public class ComplaintAPI extends HttpServlet {
	
	Complaint complaintObj = new Complaint();
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = complaintObj.insertComplaint(request.getParameter("accountNumber"),
		request.getParameter("complaintDate"),
		request.getParameter("nComplaint"),
		request.getParameter("anythingMoreToTell"));
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		String output = complaintObj.updateComplaint(paras.get("hidComplaintIDSave").toString(),
		paras.get("accountNumber").toString(),
		paras.get("complaintDate").toString(),
		paras.get("nComplaint").toString(),
		paras.get("anythingMoreToTell").toString());
		response.getWriter().write(output);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		String output = complaintObj.deleteComplaint(paras.get("complaintID").toString());
		response.getWriter().write(output);
	}

}
