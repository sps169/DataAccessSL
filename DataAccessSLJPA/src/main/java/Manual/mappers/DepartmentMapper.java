package Manual.mappers;

import Manual.daos.Department;
import Manual.dtos.DepartmentDTO;

public class DepartmentMapper extends BaseMapper<Department, DepartmentDTO>{

    @Override
    public Department fromDTO(DepartmentDTO item) {
        return new Department(item.getId(), item.getName(), item.getDepartmentBoss().getId(), item.getBudget());
    }

    @Override
    public DepartmentDTO toDTO(Department item) {
        return new DepartmentDTO(item.getId(), item.getName(), item.getBudget());
    }
}
