package service;
import java.sql.SQLException;
import java.util.List;

import daos.EmployeeDAO;
import daos.ReimbursementDAO;
import models.Employee;
import models.Reimbursement;
import utils.ConnectionFactory;

public class Driver {
	public static void main(String[] args) throws SQLException {
		
		//Testing GetALL for Reimbursement.
		
		/*
		ReimbursementDAO dao = new ReimbursementDAO(ConnectionFactory.getConnection());
		List<Reimbursement> reimbursementListTemp = dao.getAll();
		for (int i = 0; i < reimbursementListTemp.size(); i++) {
			System.out.println(reimbursementListTemp.get(i).getReinbursementStatus());
		}
		*/
		
		//Testing GetALL for Employee.
		
		/*
		EmployeeDAO daoEmployee = new EmployeeDAO(ConnectionFactory.getConnection());
		List<Employee> employeeListTemp = daoEmployee.getAll();
		for (int i = 0; i < employeeListTemp.size(); i++) {
			System.out.println(employeeListTemp.get(i).getisManager());
		}
		*/
		
		//Testing create employee.
	
		
//		EmployeeDAO daoEm = new EmployeeDAO(ConnectionFactory.getConnection());
//		Employee insertEmployee = new Employee(333,"c@pokeMail.com","12345","MANAGER");
//		//daoEm.initializeEmployeesReimbursementsForCreatingEM(insertEmployee);
//		Employee outputEmployee = daoEm.createEmployee(insertEmployee, 333);
//		System.out.println("Employee info: " + outputEmployee.getPokeId());
		
		
		//Testing create reimbursement.
		
		ReimbursementDAO daoREIM = new ReimbursementDAO(ConnectionFactory.getConnection());
//		Employee stockEmployee = new Employee(444,"d@pokeMail.com","2525","EMPLOYEE");
//		Reimbursement insertReimbursement = new Reimbursement(910,"Full Heals",200.00,"PENDING");
//		//daoREIM.initializeEmployeesReinbursementsForCreatingRE(insertReimbursement, stockEmployee.getPokeId());
//		Reimbursement outputReimbursement = daoREIM.createReimbursement(insertReimbursement, stockEmployee.getPokeId());
//		System.out.println("Reimbursement info: " + outputReimbursement.getClaimId());
		
		
		//Reimbursement insertReimbursement2 = new Reimbursement(910,"Full Heals",200.00,"APPROVED");
		//daoREIM.update(insertReimbursement2);
		
		//daoREIM.delete(insertReimbursement2);
		
		ReimbursementDAO dao = new ReimbursementDAO();
		List<Reimbursement> reimbursementListTemp = dao.findAll();
		for (int i = 0; i < reimbursementListTemp.size(); i++) {
			System.out.println(reimbursementListTemp.get(i).getReinbursementStatus());
		}
	
	}
}
