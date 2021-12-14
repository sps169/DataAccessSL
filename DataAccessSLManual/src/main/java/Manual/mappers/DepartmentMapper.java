package Manual.mappers;

import Manual.daos.Department;
import Manual.dtos.DepartmentDTO;
/**
 * Class that models the Mappers with the methods from and to DTO for Mapper Department class
 * @author sps169, FedericoTB
 */
public class DepartmentMapper extends BaseMapper<Department, DepartmentDTO>{
    /**
     * Method that map a DepartmentDTO object into a Department object.
     * @param item DepartmentDTO to map
     * @return Department mapped
     */
    @Override
    public Department fromDTO(DepartmentDTO item) {
        return new Department(item.getId(), item.getName(), item.getDepartmentBoss().getId(), item.getBudget());
    }
    /**
     * Method that map a Department object into a DepartmentDTO object.
     * @param item Department to map
     * @return DepartmentDTO mapped
     */
    @Override
    public DepartmentDTO toDTO(Department item) {
        return new DepartmentDTO(item.getId(), item.getName(), item.getBudget());
    }
}
