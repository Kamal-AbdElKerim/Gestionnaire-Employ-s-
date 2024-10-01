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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeService();

    @Override
    public void init() throws ServletException {
        super.init();
        // You can perform initialization here if needed
    }

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
            // Set the input values to be retained
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("department", department);
            request.setAttribute("position", position);

            doGet(request, response);
            return;
        }



        // Ajouter l'employé via le service
        employeeService.addEmployee(employee);


        InputClearer.clearInputs(request);


        // Confirmation du succès de l'ajout
        request.setAttribute("AddEmployee", "L'employé a été ajouté avec succès !");

        doGet(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = employeeService.getAllEmployees();

        // Set the list of employees as a request attribute
        request.setAttribute("employees", employees);
        System.out.println("employees" + employees  );
        // Forward the request to index.jsp with the employee data
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }
}

