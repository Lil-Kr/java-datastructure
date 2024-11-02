package com.cy.datastructure.datastructure.hashtable.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: Lil-K
 * @Date: 2023/2/25
 * @Description:
 */
public class Student {

    private String firstName;
    private String lastName;
    private Double grade;
    private Integer cls;

    public Student() {
    }

    public Student(String firstName, String lastName, Double grade, Integer cls) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.cls = cls;
    }

    @Override
    public int hashCode() {
        int B = 31;

        int hash = 0;

        hash = (int) (hash * B + grade);
        hash = hash * B + cls;
        hash = hash * B + firstName.toLowerCase().hashCode();
        hash = hash * B + lastName.toLowerCase().hashCode();

        return hash;
    }


    public static void main(String[] args) {
        // 94924391 ->
        Student student = new Student("BOBO","a",33.4,33);
        System.out.println(student.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(student);

        HashMap<Student, Integer> score = new HashMap<>();
        score.put(student, 100);

        Map<Object, Object> map2 = new TreeMap();
    }
}
