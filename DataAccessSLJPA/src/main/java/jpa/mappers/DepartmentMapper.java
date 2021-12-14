package jpa.mappers;

import jpa.daos.Department;
import jpa.dtos.DepartmentDTO;
/**
 * Class that models the Mappers with the methods from and to DTO for Mapper Department class
 * @author sps169, FedericoTB
 */
public class DepartmentMapper extends BaseMapper<Department, DepartmentDTO>{
    /**
     * Method that map a DepartmentDTO object into a Department object.
     * @param departament DepartmentDTO to map
     * @return Department mapped
     */
    @Override
    public Department fromDTO(DepartmentDTO departament) {
        return new Department(departament.getId(), departament.getName(), departament.getDepartmentBoss(), departament.getBudget());
    }
    /**
     * Method that map a Department object into a DepartmentDTO object.
     * @param department Department to map
     * @return DepartmentDTO mapped
     */
    @Override
    public DepartmentDTO toDTO(Department department) {
        return new DepartmentDTO(department.getId(), department.getName(),department.getBoss(), department.getBudget());
    }
}
