package controller;

import Service.EmployeeService;
import model.Employee;
import utility.Validator.EmployeeValidator;
import utility.InputClearer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateAndDisplayEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeService();



    @Override
    public void init() throws ServletException {
        super.init();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean isupdating = (Boolean) request.getAttribute("isupdating");

        System.out.println("isupdating: " + isupdating);
        if (isupdating != null && isupdating) {
            System.out.println("is true");
            doGet(request, response);
            return;
        }

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

            request.getRequestDispatcher("/Employee/index.jsp").forward(request, response);

            return;
        }


        employeeService.addEmployee(employee);

        InputClearer.clearInputs(request);

        HttpSession session = request.getSession();

        // Retrieve the employees list from the session (if it exists)
        List<Employee> employees = (List<Employee>) session.getAttribute("employees");

        // If the list doesn't exist, create a new one
        if (employees == null) {
            employees = new ArrayList<>();
        }

        employees.add(0, employee);

        request.setAttribute("message", "L'employé " + name + " a été ajouté avec succès !");
        request.setAttribute("color", "#2E8B57");

        request.getRequestDispatcher("/Employee/index.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        HttpSession session = request.getSession();
        List<Employee> employees;
        employees = employeeService.getAllEmployees();
        session.setAttribute("employees", employees);
        employees = (List<Employee>) session.getAttribute("employees");


// Prepare to filter employees based on the request parameters
        String searchbar = request.getParameter("searchbar");
        String department = request.getParameter("Departement");
        String position = request.getParameter("Poste");

// Start with the complete list
        List<Employee> filteredEmployees = new ArrayList<>(employees);

// Filter based on search bar input
        if (searchbar != null && !searchbar.trim().isEmpty()) {
            String searchQuery = searchbar.toLowerCase();
            request.setAttribute("searchbar", searchbar);
            filteredEmployees = filteredEmployees.stream()
                    .filter(employee -> employee.getName().toLowerCase().contains(searchQuery) ||
                            employee.getEmail().toLowerCase().contains(searchQuery) ||
                            employee.getDepartment().equalsIgnoreCase(searchQuery) ||
                            employee.getPosition().equalsIgnoreCase(searchQuery))
                    .collect(Collectors.toList());
        }

// Filter by department if provided
        if (department != null && !department.trim().isEmpty()) {
            String dept = department.toLowerCase();
            request.setAttribute("Departement", department);
            filteredEmployees = filteredEmployees.stream()
                    .filter(employee -> employee.getDepartment().equalsIgnoreCase(dept))
                    .collect(Collectors.toList());
        }

        if (position != null && !position.trim().isEmpty()) {
            String pos = position.toLowerCase();
            request.setAttribute("Poste", position);
            filteredEmployees = filteredEmployees.stream()
                    .filter(employee -> employee.getPosition().equalsIgnoreCase(pos))
                    .collect(Collectors.toList());
        }

        session.setAttribute("employees", filteredEmployees);

        request.getRequestDispatcher("/Employee/index.jsp").forward(request, response);

    }
}
