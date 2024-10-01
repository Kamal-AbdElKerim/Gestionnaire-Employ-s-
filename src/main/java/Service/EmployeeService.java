package Service;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utility.HibernateUtil;

import java.util.List;

public class EmployeeService {

    // Méthode pour ajouter un nouvel employé
    public void addEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Save the employee to the database
            session.save(employee);

            // Commit the transaction after the save is successful
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback transaction on failure
            }
            e.printStackTrace();
        }
    }



    // Méthode pour récupérer tous les employés
    public List<Employee> getAllEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee e ORDER BY e.createdAt DESC", Employee.class);
            return query.list();
        }
    }

    // Méthode pour récupérer un employé par son ID
    public Employee getEmployeeById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        }
    }

    // Méthode pour mettre à jour un employé
    public void updateEmployee(Employee employee, int id) {
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
    

    // Méthode pour supprimer un employé
    public void deleteEmployee(Long id) {  // Change int to Long
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);  
            if (employee != null) {
                session.delete(employee);
                System.out.println("Employee deleted successfully with ID: " + id);
            } else {
                System.out.println("No employee found with ID: " + id);
            }

            transaction.commit(); // Commit if no exceptions were thrown
        } catch (Exception e) {
            // Handle transaction rollback in case of errors
            if (transaction != null) {
                transaction.rollback();  // Roll back the transaction if an error occurs
                System.out.println("Transaction rolled back due to: " + e.getMessage());
            }
            handleTransactionError(transaction, e);  // Your custom error handling
        }
    }


    // Méthode pour gérer les erreurs de transaction
    public void handleTransactionError(Transaction transaction, Exception e) {
        if (transaction != null) {
            try {
                transaction.rollback();
            } catch (Exception rollbackException) {
                rollbackException.printStackTrace();
            }
        }
        e.printStackTrace();  // Log the original exception
    }

}
