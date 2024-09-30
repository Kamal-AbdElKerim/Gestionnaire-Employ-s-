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
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            handleTransactionError(transaction, e);
        }
    }

    // Méthode pour récupérer tous les employés
    public List<Employee> getAllEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
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
    public void deleteEmployee(int id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
            }
            transaction.commit();
        } catch (Exception e) {
            handleTransactionError(transaction, e);
        }
    }

    // Méthode pour gérer les erreurs de transaction
    private void handleTransactionError(Transaction transaction, Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}
