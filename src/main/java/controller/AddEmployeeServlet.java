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
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeService();
    private  List<Employee> filteredEmployees ;

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

            request.setAttribute("employees", filteredEmployees);

            request.getRequestDispatcher("/Employee/index.jsp").forward(request, response);

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

        String searchbar = request.getParameter("searchbar");

        String departement = request.getParameter("Departement");
        String poste = request.getParameter("Poste");

         filteredEmployees = employees;

        if (searchbar != null && !searchbar.trim().isEmpty()) {
            String Search = searchbar.toLowerCase();
            request.setAttribute("searchbar", searchbar);
            filteredEmployees = employees.stream()
                    .filter(employee -> employee.getName().toLowerCase().contains(Search) ||
                            employee.getEmail().toLowerCase().contains(Search) ||
                            employee.getDepartment().equalsIgnoreCase(Search) ||
                            employee.getPosition().equalsIgnoreCase(Search))
                    .collect(Collectors.toList());
        }

        // Filter by department if provided
        if (departement != null && !departement.trim().isEmpty()) {
            String dept = departement.toLowerCase();
            request.setAttribute("Departement", departement);
            filteredEmployees = filteredEmployees.stream()
                    .filter(employee -> employee.getDepartment().equalsIgnoreCase(dept))
                    .collect(Collectors.toList());
        }

// Filter by position if provided
        if (poste != null && !poste.trim().isEmpty()) {
            String pos = poste.toLowerCase();
            request.setAttribute("Poste", poste);
            filteredEmployees = filteredEmployees.stream()
                    .filter(employee -> employee.getPosition().equalsIgnoreCase(pos))
                    .collect(Collectors.toList());
        }





        // Set the list of employees as a request attribute
        request.setAttribute("employees", filteredEmployees);

        // Forward the request to index.jsp with the employee data
        request.getRequestDispatcher("/Employee/index.jsp").forward(request, response);

    }
}

