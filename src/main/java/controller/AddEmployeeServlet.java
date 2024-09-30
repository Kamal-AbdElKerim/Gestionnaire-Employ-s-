package controller;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //   String name = request.getParameter("name");
     //   String email = request.getParameter("email");
     //   String phone = request.getParameter("phone");
     //   String department = request.getParameter("department");
     //   String position = request.getParameter("position");

        // Create a new Employee object
        Employee employee = new Employee("kamal" , "islam@gmail.com" , "0669474622" , "safi" , "de");
        Configuration conf = new Configuration().configure().addAnnotatedClass(Employee.class);
        SessionFactory sessionFactory = conf.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();




        // Redirect to the employee list page
       // response.sendRedirect("employeeList.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

