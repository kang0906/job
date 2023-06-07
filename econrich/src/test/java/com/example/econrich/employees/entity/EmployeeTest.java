package com.example.econrich.employees.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    public void bigDecimal(){
        BigDecimal salary = new BigDecimal("10000");
        BigDecimal percent = new BigDecimal(20).divide(new BigDecimal(100));
        BigDecimal newSalary = salary.add(salary.multiply(percent));
        System.out.println("newSalary = " + newSalary);
    }

    @Test
    public void bigDecimal2(){
        BigDecimal salary = new BigDecimal("10000");
        BigDecimal bigDecimalPercent = new BigDecimal(20).divide(new BigDecimal(100));
        BigDecimal newSalary = salary.add(salary.multiply(bigDecimalPercent));
        System.out.println("newSalary = " + newSalary);

    }


}
