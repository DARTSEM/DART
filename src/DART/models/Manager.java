package DART.models;

import java.util.ArrayList;

public class Manager {

    public Manager() {

    }

    public void addEmployee(Employee employee, ArrayList<Employee> employees) {
        employees.add(employee);
    }


    public void removeEmployee(String ID, ArrayList<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().toString().equals(ID)) {
                employees.remove(employees.get(i));
                return;
            }
        }
        System.out.println("Employee with entered ID not found");

    }

    public void printAllEmployees(ArrayList<Employee> employees) {
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i).toString());
        }
    }
}
