package Manual.mappers;

import Manual.daos.Department;
import Manual.dtos.DepartmentDTO;

public class DepartmentMapper extends BaseMapper<Department, DepartmentDTO>{

    @Override
    public Department fromDTO(DepartmentDTO departament) {
        return new Department(departament.getId(), departament.getName(), departament.getDepartmentBoss(), departament.getBudget());
    }

    @Override
    public DepartmentDTO toDTO(Department department) {
        return new DepartmentDTO(department.getId(), department.getName(),department.getBoss(), department.getBudget());
    }
}
