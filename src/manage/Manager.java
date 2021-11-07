package manage;

import entity.Report;
import entity.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.HashSet;
import validation.Validation;

public class Manager {

    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Report> reports = new ArrayList<>();
    private final static Scanner in = new Scanner(System.in);

    public void create() {
        students.add(new Student("HE151020", "Nghiem Xuan Trinh", 20, "Fall", "Java"));
        students.add(new Student("HE151020", "Nghiem Xuan Trinh", 21, "Fall", "Java"));
        students.add(new Student("HE151020", "Nghiem Xuan Trinh", 20, "Fall", "c/C++"));
        students.add(new Student("HE151020", "Nghiem Xuan Trinh", 20, "Fall", ".NET"));
        students.add(new Student("HE141020", "Le Duc Quyen", 21, "Fall", "Java"));
        students.add(new Student("HE161020", "Dam Duc Minh", 19, "Fall", ".NET"));
    }

    public void menu() {
        System.out.println(" 1.	Create");
        System.out.println(" 2.	Find and Sort");
        System.out.println(" 3.	Update/Delete");
        System.out.println(" 4.	Report");
        System.out.println(" 5.	Exit");
        System.out.print(" Enter your choice: ");
    }

    public void createStudent() {
        int count = 0;
        while (true) {
            if (count >= 5) {
                System.out.println("Do you want to continue (Y/N)? ");
                count = 0;
                if (!Validation.checkInputYN()) {
                    return;
                }
            }
            String id = Validation.inputID();
            String name = Validation.inputName();
            if (!checkIdExist(id, name)) {
                System.out.println("Id already exists names that don't match");
                System.out.println("Please inter again!");
                continue;
            }
            System.out.print("Enter Age: ");
            int age = Validation.checkInputIntLimit(1, Integer.MAX_VALUE);
            String semester = Validation.inputSemester();
            System.out.print("Enter couresName: ");
            String couresName = Validation.checkInputCourse();

            if (checkStudentExist(id, name, age, semester, couresName)) {
                students.add(new Student(id, name, age, semester, couresName));
                System.out.println("Add student success.");
                count++;
            } else {
                System.out.println("This student already exists");
                System.out.println("Duplicate!");
            }
        }
    }

    public boolean checkIdExist(String id, String name) {
        for (Student student : students) {
            if (id.equalsIgnoreCase(student.getId())
                    && !name.equalsIgnoreCase(student.getStudentName())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkStudentExist(String id, String studentName, int age, String semester, String courseName) {
        for (Student student : students) {
            if (id.equalsIgnoreCase(student.getId())
                    && studentName.equalsIgnoreCase(student.getStudentName())
                    && age == student.getAge()
                    && semester.equalsIgnoreCase(student.getSemester())
                    && courseName.equalsIgnoreCase(student.getCourseName())) {
                return false;
            }
        }
        return true;
    }

    public void findAndSort() {
        if (students.isEmpty()) {
            System.out.println("List Empty");
            return;
        }
        ArrayList<Student> listStudentFindByName = findByName();
        if (listStudentFindByName.isEmpty()) {
            System.out.println("Dont found student");
        } else {
            Collections.sort(listStudentFindByName, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    int kq = o1.getAge() < o2.getAge() ? -1 : o1.getAge() == o2.getAge() ? 0 : 1;
                    return kq;
                }
            });
            System.out.printf("%-20S%-15s%-15s%-15s\n", "Student name", "Semester", "Age", "Course Name");
            for (Student student : listStudentFindByName) {
                System.out.printf("%-20s%-15s%-15s%-15s\n", student.getStudentName(), student.getSemester(),
                        student.getAge(), student.getCourseName());
            }
        }
    }

    public ArrayList<Student> findByName() {
        ArrayList<Student> stNew = new ArrayList<>();
        String name = Validation.inputName().toUpperCase();
        for (Student student : students) {
            if (student.getStudentName().toUpperCase().contains(name)) {
                stNew.add(student);
            }
        }
        return stNew;
    }

    public void UpdateOrDelete() {
        if (students.isEmpty()) {
            System.out.println("List Empty");
            return;
        }
        System.out.print("Enter id to search: ");
        String idSearch = in.nextLine();
        ArrayList<Student> listStudentFindById = findById(idSearch);
        if (listStudentFindById.isEmpty()) {
            System.out.println("Dont Found");
        } else {
            Student student = getStudentByIdInList(idSearch);
            System.out.print("Do you want to update (U) or delete (D) student: ");
            if (Validation.checkInputUD()) {
                String id = Validation.inputID();
                String name = Validation.inputName();
                System.out.print("Enter age: ");
                int age = Validation.checkInputIntLimit(10, 100);
                String semester = Validation.inputSemester();
                System.out.print("Enter Name Coure :");
                String nameCourse = Validation.checkInputCourse();
                if (checkStudentExist(id, name, age, semester, nameCourse)) {
                    student.setId(id);
                    student.setStudentName(name);
                    student.setAge(age);
                    student.setSemester(semester);
                    student.setCourseName(nameCourse);
                    System.err.println("Update success.");
                } else {
                    System.out.println("Update fall");
                    System.out.println("Duplicate");
                }
                return;
            } else {
                students.remove(student);
                System.err.println("Delete success.");
                return;
            }
        }
    }

    public ArrayList<Student> findById(String id) {
        ArrayList<Student> stNew = new ArrayList<>();
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                stNew.add(student);
            }
        }
        return stNew;
    }

    public Student getStudentByIdInList(String id) {
        int count = 1;
        ArrayList<Student> getListStudentFindById = findById(id);
        System.out.printf("%-15s%-30s%-15s%-15s\n", "Number", "Student name",
                "semester", "Course Name");
        for (Student student : getListStudentFindById) {
            System.out.printf("%-15s%-30s%-15s%-15s\n", count,
                    student.getStudentName(), student.getSemester(),
                    student.getCourseName());
            count++;
        }
        System.out.print("Enter student: ");
        int choice = Validation.checkInputIntLimit(1, getListStudentFindById.size());
        return getListStudentFindById.get(choice - 1);
    }

    public void report() {
        ArrayList<String> listId = new ArrayList<>();
        for (Student student : students) {
            listId.add(student.getId());

        }
        HashSet<String> hset = new HashSet<String>(listId);
        for (String string : hset) {
            int count = Collections.frequency(listId, string);
            reports.add(new Report(getStudentById(string).getStudentName(),
                    getStudentById(string).getCourseName(), count));

        }
        System.out.printf("%-30s%-15s%-15s\n", "NameStudent", "NameCourse", "TotalCourse");
        for (Report report : reports) {
            System.out.printf("%-30s%-15s%-15s\n", report.getStudentName(),
                    report.getCourseName(), report.getTotalCourse());
        }
    }

    public Student getStudentById(String id) {
        for (Student student : students) {
            if (id.equalsIgnoreCase(student.getId())) {
                return student;
            }
        }
        return null;
    }

}
