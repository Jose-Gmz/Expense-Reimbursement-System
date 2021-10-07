package daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import models.*;
import utils.HibernateSessionFactory;

public class EmployeeDAO implements Dao<Employee>{
	Connection connection;
	List<Employee> employeeList = new LinkedList<>();
	
	public EmployeeDAO(Connection conn) {
		
		connection = conn;
	}
	
	

	public EmployeeDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee verifyUser(String pokeEmail, String password) {
		Session session = null;
		Transaction tx = null;
		Employee tempEmployee = null;
		
		try {
		System.out.println("Test: " + pokeEmail);
		System.out.println("Test: " + password);
		
		session = HibernateSessionFactory.getSession();
		tx = session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		
		Root<Employee> root = cq.from(Employee.class);
		cq.select(root).where(cb.equal(root.get("pokeEmail"), pokeEmail));
		
		Query<Employee> query = session.createQuery(cq);
		
		tempEmployee = query.uniqueResult();
		
		tx.commit();
		
		}catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		
		return tempEmployee;
	}

	@Override
	public Employee get(int pokeId) throws SQLException {
		String sql = "SELECT * FROM employees WHERE poke_id = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, pokeId);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			Employee row = new Employee();
			row.setPokeId(rs.getInt(1));
			row.setPokeEmail(rs.getString(2));
			row.setPassword(rs.getString(3));
			row.setManager(rs.getString(4));
			
			return row;
		}
		return null;
	}

	@Override
	public List<Employee> getAll() throws SQLException {
		String sql = "SELECT * FROM employees";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
		Employee employeeRow = new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
		employeeList.add(employeeRow);
		
		}
		return employeeList;
	}

	@Override
	public void save(Employee t) {
		// TODO Auto-generated method stub
		
	}
	
	//initializing the many to many table. Method called within CreateEmployee.
	public void initializeEmployeesReimbursementsForCreatingEM(Employee employee) throws SQLException {
		String sql = "INSERT INTO employees_reimbursements (claim_id,poke_id) VALUES (?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, 000);
		pstmt.setInt(2, employee.getPokeId());
		pstmt.executeUpdate();
	}
	
	//Creating an employee.
	public Employee createEmployee(Employee employee, int pokeId) throws SQLException {
		
		initializeEmployeesReimbursementsForCreatingEM(employee);
		
		String sql2 = "INSERT INTO employees (poke_id,poke_email,password,is_manager) VALUES (?,?,?,?)";
		PreparedStatement pstmt2 = connection.prepareStatement(sql2);
		pstmt2.setInt(1, employee.getPokeId());
		pstmt2.setString(2, employee.getPokeEmail());
		pstmt2.setString(3, employee.getPokeEmail());
		pstmt2.setString(4, employee.getisManager());
		
		if(pstmt2.executeUpdate() > 0) {
			List<Employee> employeeList = getAll();
			Employee temp = employeeList.get(employeeList.size() - 1);
			return temp;
		}else{
			//throw new NoSQLResultsException("Employee was not inserted");
		
		}
		return null;
	}

	@Override
	public void update(Employee t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Employee t) {
		// TODO Auto-generated method stub
		
	}
	
	
}
