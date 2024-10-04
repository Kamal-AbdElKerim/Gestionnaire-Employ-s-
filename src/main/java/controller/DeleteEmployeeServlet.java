package controller;

import Service.EmployeeService;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DeleteEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeService();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String employeeIdParam = request.getParameter("employeeId");

        // Validate input
        if (employeeIdParam == null || employeeIdParam.isEmpty()) {
            request.setAttribute("message", "Employee ID is required!");
            request.setAttribute("color", "red");
        } else {
            try {
                Long employeeIdStr = Long.parseLong(employeeIdParam);

                // Validate the ID
                if (employeeIdStr > 0) {
                    // Attempt to delete the employee
                     employeeService.deleteEmployee(employeeIdStr);


                        // Remove from session list
                        HttpSession session = request.getSession();
                        List<Employee> employees = (List<Employee>) session.getAttribute("employees");

                        employees.removeIf(employee -> employee.getId().equals(employeeIdStr));

                        request.setAttribute("message", "Employee deleted successfully!");
                        request.setAttribute("color", "#333f27");
                    } else {
                        request.setAttribute("message", "Employee with ID " + employeeIdStr + " not found!");
                        request.setAttribute("color", "red");
                    }

            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid employee ID format!");
                request.setAttribute("color", "red");
            }
        }


        request.getRequestDispatcher("/Employee/index.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Implement doGet if needed
    }
}
