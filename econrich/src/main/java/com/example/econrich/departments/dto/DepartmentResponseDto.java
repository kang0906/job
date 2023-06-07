package com.example.econrich.departments.dto;

import com.example.econrich.departments.entity.Department;
import com.example.econrich.employees.dto.ManagerDto;
import com.example.econrich.employees.entity.Employee;
import lombok.Data;

@Data
public class DepartmentResponseDto {
    private Integer departmentId;
    private String departmentName;

    private Integer locationId;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private String countryId;
    private String countryName;
    private Integer regionId;
    private String regionName;
    private ManagerDto manager;

    public DepartmentResponseDto(Department department) {
        this.departmentId = department.getDepartmentId();
        this.departmentName = department.getDepartmentName();
        this.locationId = department.getLocation().getLocationId();
        this.streetAddress = department.getLocation().getStreetAddress();
        this.postalCode = department.getLocation().getPostalCode();
        this.city = department.getLocation().getCity();
        this.stateProvince = department.getLocation().getStateProvince();
        this.countryId = department.getLocation().getCountry().getCountryId();
        this.countryName = department.getLocation().getCountry().getCountryName();
        this.regionId = department.getLocation().getCountry().getRegion().getRegionId();
        this.regionName = department.getLocation().getCountry().getRegion().getRegionName();
        this.manager = new ManagerDto(department.getManager());
    }
}
