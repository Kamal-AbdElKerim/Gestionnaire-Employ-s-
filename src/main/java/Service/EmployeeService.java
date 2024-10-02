package Service;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utility.HibernateUtil;

import java.util.List;

public class EmployeeService {

    public void addEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            
            session.save(employee);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); 
            }
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee e ORDER BY e.createdAt DESC", Employee.class);
            return query.list();
        }
    }

    public Employee getEmployeeById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        }
    }

    public void updateEmployee(Employee employee, long id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Employee existingEmployee = session.get(Employee.class, id);
            if (existingEmployee != null) {
                existingEmployee.setName(employee.getName());
                existingEmployee.setEmail(employee.getEmail());
                existingEmployee.setPhone(employee.getPhone());
                existingEmployee.setDepartment(employee.getDepartment());
                existingEmployee.setPosition(employee.getPosition());

                session.update(existingEmployee);
            } else {
                System.out.println("Employee not found with ID: " + id);
            }

            transaction.commit();
        } catch (Exception e) {
            handleTransactionError(transaction, e);
        }
    }

    public void deleteEmployee(Long id) { 
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
            } else {
                System.out.println("No employee found with ID: " + id);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); 
                System.out.println("Transaction rolled back due to: " + e.getMessage());
            }
            handleTransactionError(transaction, e); 
        }
    }

    public void handleTransactionError(Transaction transaction, Exception e) {
        if (transaction != null) {
            try {
                transaction.rollback();
            } catch (Exception rollbackException) {
                rollbackException.printStackTrace();
            }
        }
        e.printStackTrace(); 
    }

}
