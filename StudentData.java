package JavaProgramming;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

// Class Student to define Student data
class Student implements Comparable<Student> {
    private String Name;
    private String Address;
    private double GPA;

    // Constructor
    public Student(String name, String address, double GPA) {
        this.Name = name;
        this.Address = address;
        this.GPA = GPA;
    }

    // Getters
    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public double getGPA() {
        return GPA;
    }

    // Compare by name for sorting
    @Override
    public int compareTo(Student other) {
        return this.Name.compareTo(other.Name);
    }

    // Format output for file writing
    @Override
    public String toString() {
        return "Name: " + Name + ", Address: " + Address + ", GPA: " + GPA;
    }
}

public class StudentData {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        LinkedList<Student> studentList = new LinkedList<>();
        String choice;

        // Loop for data entry
        do {
            System.out.print("Enter student's name: ");
            String name = scnr.nextLine();
            System.out.print("Enter student's address: ");
            String address = scnr.nextLine();
            double GPA = 0;

            // Validate GPA input
            while (true) {
                System.out.print("Enter student's GPA (0.0 - 4.0): ");
                String gpaInput = scnr.nextLine();
                try {
                    GPA = Double.parseDouble(gpaInput);
                    if (GPA < 0.0 || GPA > 4.0) {
                        throw new NumberFormatException("GPA must be between 0.0 and 4.0.");
                    }
                    break; // valid GPA
                } catch (NumberFormatException e) {
                    System.out.println("Invalid GPA. Please enter a valid number between 0.0 and 4.0.");
                }
            }

            // Add new student to the list
            studentList.add(new Student(name, address, GPA));

            // Prompt for more data
            System.out.print("Do you want to add another student? (yes/no): ");
            choice = scnr.nextLine();
        } while (choice.equalsIgnoreCase("yes"));

        // Sort list by student name
        studentList.sort(null);

        // Write sorted list to text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("StudentData.txt"))) {
            for (Student student : studentList) {
                writer.write(student.toString());
                writer.newLine();
            }
            System.out.println("Student data saved to StudentData.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        scnr.close();
    }
}
