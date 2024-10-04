package model;

import java.time.LocalDateTime;

public class Employee {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String department;
    private String position;
    private LocalDateTime createdAt;

    private static Long IDEmploye = 1L ;

    public Employee(Long id, String name, String email, String phone, String department, String position) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.position = position;
    }

    public Employee(String name, String email, String phone, String department, String position,
            LocalDateTime createdAt) {
        this.id = IDEmploye;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.position = position;
        this.createdAt = createdAt;

        IDEmploye ++ ;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
