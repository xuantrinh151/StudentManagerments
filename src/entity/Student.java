
package entity;


public class Student {

    private String id;
    private String studentName;
    private int age;
    private String semester;
    private String courseName;

    public Student(String id, String studentName, int age, String semester, String courseName) {
        this.id = id;
        this.age = age;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}

