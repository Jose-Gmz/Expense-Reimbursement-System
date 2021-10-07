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

import utils.ConnectionFactory;
import utils.HibernateSessionFactory;
import models.*;


public class ReimbursementDAO implements Dao<Reimbursement>{
	Connection connection;
	List<Reimbursement> reimbursementList;
	
	public ReimbursementDAO(Connection conn) {
		reimbursementList = new LinkedList<>();
		connection = conn;
	}
	
	
	
	public ReimbursementDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Reimbursement> getEmployeeReimbursements(int pokeId){
		
		List<Reimbursement>
		reimbursementList = null;
		
		
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			
			String sql = "SELECT * FROM reimbursements rs WHERE rs.employee_poke_id = :pokeId";
			reimbursementList = s.createNativeQuery(sql, Reimbursement.class)
					.setParameter("pokeId", pokeId)
					.getResultList();
			
			tx.commit();
			
		}catch(HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			s.close();
		}
		
		return reimbursementList;
	}

	@Override
	public Reimbursement get(int claimId) throws SQLException {
		String sql = "SELECT * FROM reimbursements WHERE claim_id = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, claimId);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			Reimbursement row = new Reimbursement();
			row.setClaimId(rs.getInt(1));
			row.setDescription(rs.getString(2));
			row.setAmount(rs.getDouble(3));
			row.setReinbursementStatus(rs.getString(4));
			
			return row;
		}
		return null;
	}
	
	public List<Reimbursement> findAll(){
		List<Reimbursement> reimbursements = null;
		Session s = null;
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			reimbursements = s.createQuery("FROM Reimbursement", Reimbursement.class).getResultList();
			tx.commit();
			
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
		return reimbursements;
	}


	public List<Reimbursement> getAll(int pokeId) throws SQLException {
		String sql = "SELECT * FROM reimbursements WHERE employee_poke_id = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, pokeId);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
		Reimbursement reimbursementRow = new Reimbursement(rs.getInt("claim_id"),rs.getString(2),rs.getDouble(3),rs.getString(4));
		reimbursementList.add(reimbursementRow);
		
		}
		return reimbursementList;
	}




	@Override
	public void save(Reimbursement reim) throws SQLException{
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			session.save(reim);
			tx.commit();
		}catch(HibernateException e) {
			
		}finally {
			session.close();
		}
		
	}
	

	
	//creates a new reimbursement
	public Reimbursement createReimbursement(Reimbursement a , int pokeId) throws SQLException {
		
		
		String sql2 = "INSERT INTO reimbursements (claim_id,description,amount,status) VALUES (?,?,?,?)";
		PreparedStatement pstmt2 = connection.prepareStatement(sql2);
		pstmt2.setInt(1, a.getClaimId());
		pstmt2.setString(2, a.getDescription());
		pstmt2.setDouble(3, a.getAmount());
		pstmt2.setString(4, a.getReinbursementStatus());
		
		if(pstmt2.executeUpdate() > 0) {
			List<Reimbursement> reimbursementList = getAll();
			Reimbursement temp = reimbursementList.get(reimbursementList.size() -1);
			return temp;
		}else{
			//throw new NoSQLResultsException("Employee was not inserted");
		
		}
		return null;
	}
	


	@Override
	public void update(Reimbursement a) throws SQLException {
		String sql = "UPDATE reimbursements SET status = ? WHERE claim_id = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, a.getReinbursementStatus());
		pstmt.setInt(2, a.getClaimId());
		
		
		pstmt.executeUpdate();
		
	}

	public void deleteEmployees_ReimbursementForR(Reimbursement a) throws SQLException{
		String sql = "DELETE FROM employees_reimbursements WHERE claim_id = ? LIMIT 1";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, a.getClaimId());
		pstmt.executeUpdate();

	}
	

	@Override
	public void delete(Reimbursement a) throws SQLException{
		String sql = "DELETE FROM reimbursements WHERE claim_id = ? LIMIT 1";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, a.getClaimId());
		
		pstmt.executeUpdate();
		deleteEmployees_ReimbursementForR(a);

	}



	@Override
	public List<Reimbursement> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}