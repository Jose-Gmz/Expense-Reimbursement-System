package controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;
import models.Employee;
import service.ReimbursementService;

public class EmployeeController {
	
	static Employee activeEmployee = new Employee();
	static ReimbursementService employeeService = new ReimbursementService();
	
	private static Javalin application;
	
	public static void init(Javalin app) {
		application = app;
		application.get("/testEmployee", EmployeeController::testEmployeeConnection);
		application.post("/verifyEmployee", EmployeeController::logInVerification);
		application.post("logOut", EmployeeController::logOut);
		
	}
	
	
	public static void testEmployeeConnection(Context ctx) {
		ctx.status(200);
		ctx.result("Employee test Received!!");
	}
	
	private static void logInVerification(Context ctx) {
		
	
		
		String pokeEmail = ctx.req.getParameter("pokeEmail");
		String password = ctx.req.getParameter("password");
		
		activeEmployee = employeeService.findEmployee(pokeEmail,password);
		
		if(activeEmployee == null) {
			ctx.redirect("/Login.html");
			
		}else if (activeEmployee.getisManager().equals("MANAGER")){
			System.out.println("Session Granted");
			ctx.redirect("ManagerPage.html");
			
		}else{
			System.out.println("Session Granted");
			ctx.redirect("EmployeePage.html");
		}
	
	}
	
	public static void logOut(Context ctx) {
		ctx.redirect("Login.html");
	}


	public Employee getActiveEmployee() {
		return activeEmployee;
	}


	public void setActiveEmployee(Employee activeEmployee) {
		EmployeeController.activeEmployee = activeEmployee;
	}
	
	
}
