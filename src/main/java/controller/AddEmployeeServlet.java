package controller;

import Service.EmployeeService;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import utility.EmployeeValidator;
import utility.InputClearer;
import utility.ValidationUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeService employeeService = new EmployeeService();
    private ValidationUtil validationUtil = new ValidationUtil();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String name = request.getParameter("name");
       String email = request.getParameter("email");
       String phone = request.getParameter("phone");
       String department = request.getParameter("department");
       String position = request.getParameter("position");

        Employee employee = new Employee(name, email, phone, department, position, LocalDateTime.now());

        // Validate the employee data
        EmployeeValidator validator = new EmployeeValidator();
        String validationError = validator.validate(employee);

        if (validationError != null) {
            request.setAttribute("error", validationError);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }


        // Ajouter l'employé via le service
        EmployeeService employeeService = new EmployeeService();
        employeeService.addEmployee(employee);


        InputClearer.clearInputs(request);


        // Confirmation du succès de l'ajout
        request.setAttribute("AddEmployee", "L'employé a été ajouté avec succès !");
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

