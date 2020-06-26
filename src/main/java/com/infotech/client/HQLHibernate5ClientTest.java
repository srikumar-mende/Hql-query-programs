package com.infotech.client;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.infotech.entities.Employee;
import com.infotech.util.HibernateUtil;

public class HQLHibernate5ClientTest {

	public static void main(String[] args) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		getEmployeeNAme(sf);
		// getEmployeeById(sf);
		//getAllEmployees(sf);
		//getEmployeeById(sf);
		
		
		getEmployeeNameAndId(sf);
	}

	private static void getEmployeeById(SessionFactory sf) {
		int empId=2;
		try(Session session = sf.openSession() ) {
			String HQL = "FROM Employee WHERE employeeId=?";
			Query<Employee> query = session.createQuery(HQL, Employee.class);
			query.setParameter(0, empId);
			
			Employee employee = query.uniqueResult();
			System.out.println(employee);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	private static void getAllEmployees(SessionFactory sf) {
		try(Session session = sf.openSession() ) {
			
			String HQL = "FROM Employee";
			//String HQL ="FROM com.infotech.entities.Employee";
			
			Query<Employee> query = session.createQuery(HQL, Employee.class);
			
			List<Employee> list = query.list();
			list.forEach(System.out::println);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void getEmployeeNAme(SessionFactory sf) {
		
		try(Session session = sf.openSession()){
			String HQL = "select employeeName from Employee";
			Query<String> query = session.createQuery(HQL);
			
			query.list().forEach(System.out::println);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		}
	
	
	private static void getEmployeeNameAndId(SessionFactory sf) {
		
		try(Session session = sf.openSession()) {
			
			String HQL = "select employeeName,employeeId from Employee";
			Query  o =   session.createQuery(HQL);
			List<Object[]> obj =  o.list();
			
			for(Object[] object :obj) {
				
			Integer EmployeeId = 	(Integer) object[1];
			String Employeename =(String) object[0];
				
			System.out.println(EmployeeId+" "+ Employeename);
			}
	
		}
	}
	
	
	
	
	
	
	
}
