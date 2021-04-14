import java.util.Objects;

public class Employee {
    private int id;
    private String firstName;
    private String secondName;
    private String department;
    private int salary;

//    public EmployeeEntity(){}
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        EmployeeEntity that = (EmployeeEntity) o;
//        return getId() == that.getId() &&
//                Objects.equals(getFirstName(), that.getFirstName()) &&
//                Objects.equals(getSecondName(), that.getSecondName()) &&
//                Objects.equals(getDepartment(), that.getDepartment()) &&
//                Objects.equals(getSalary(), that.getSalary());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getFirstName(), getSecondName(), getDepartment(), getSalary());
//    }

    public Employee(int id, String firstName, String secondName,
                    String department, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.department = department;
        this.salary = salary;
    }
    public Employee(String firstName, String secondName,
                    String department, int salary) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}
