package controllers;

import java.sql.SQLException;
import java.util.List;

import daos.ReimbursementDAO;
import io.javalin.Javalin;
import io.javalin.http.Context;
import models.Employee;
import models.Reimbursement;
import service.ReimbursementService;
import utils.ConnectionFactory;

public class ReimbursementController {
	private static Javalin application;
	
	static EmployeeController employeeController = new EmployeeController();
	
	static ReimbursementService reimbursementService = new ReimbursementService();
	
	public static void init(Javalin app) {
		application = app;
		application.get("/testReimbursement", ReimbursementController::testReimbursementConnection);
		application.post("/newReimbursement", ReimbursementController::createReimbursement);
		application.get("/getAllReimbursements", ReimbursementController::getAllReimbursements);
		application.get("/getEmployeeReimbursements", ReimbursementController::EmployeeReimbursement);
		application.post("/updateReimbursement", ReimbursementController::updateReimbursementStatus);
		application.get("/testGetAll", ReimbursementController::testEmployeeReimbursements);
	}
	
	public static void testReimbursementConnection(Context ctx) {
		ctx.status(200);
		ctx.result("Reimbursement test Received!!");
	}
	
	public static void createReimbursement(Context ctx) throws SQLException {
	
		//used hibernate to save a new reimbursement in the reimbursement table. 
		ReimbursementDAO daoHibernate = new ReimbursementDAO();
		
		String tempAmount = ctx.req.getParameter("amount");
		double doubleAmount = Double.parseDouble(tempAmount);
		
		Reimbursement newReimbursement = new Reimbursement( 
				ctx.req.getParameter("description"), 
				doubleAmount, 
				"PENDING", employeeController.getActiveEmployee());
			

		
		daoHibernate.save(newReimbursement);
		ctx.redirect("EmployeePage.html");
		
		
		
	}
	
	public static void getAllReimbursements(Context ctx) throws SQLException {
		ReimbursementDAO dao = new ReimbursementDAO();
		
		List<Reimbursement> reimList = dao.findAll();
		ctx.json(reimList);
		
		
	}
	
	//hibernate
	public static void EmployeeReimbursement(Context ctx){
		List<Reimbursement> reimList;
		
		reimList = reimbursementService.getEmployeeReimbursementService(employeeController.getActiveEmployee().getPokeId());
		ctx.json(reimList);
	}
	
	//test DELETE
	public static void testEmployeeReimbursements(Context ctx) throws SQLException {
		List <Reimbursement> tempList;
		ReimbursementDAO dao = new ReimbursementDAO(ConnectionFactory.getConnection());
		tempList = dao.getAll(employeeController.getActiveEmployee().getPokeId());
		ctx.json(tempList);
	}
	
	public static void updateReimbursementStatus(Context ctx)throws SQLException {
		ReimbursementDAO jdbcDao = new ReimbursementDAO(ConnectionFactory.getConnection());
		System.out.println("test!  ");
		String tempId = ctx.req.getParameter("claimId");
		System.out.println(tempId);
		int tempClaimId = Integer.parseInt(tempId);
		
		String tempStatus = ctx.req.getParameter("status_name");
		
		
		Reimbursement updateReimbursement = new Reimbursement(
				tempClaimId,
				tempStatus
				);
		
		jdbcDao.update(updateReimbursement);
		ctx.redirect("ManagerPage.html");
	}
}
