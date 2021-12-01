package Manual.services;

import Manual.daos.Department;
import Manual.daos.Programmer;
import Manual.daos.Project;
import Manual.dtos.DepartmentDTO;
import Manual.dtos.ProjectDTO;
import Manual.mappers.DepartmentMapper;
import Manual.repositories.DepartmentRepo;
import Manual.repositories.ProgrammerRepo;
import Manual.repositories.ProjectRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DepartmentService extends BaseService<Department,Long, DepartmentRepo> {
    private final DepartmentMapper mapper = new DepartmentMapper();
    public DepartmentService(DepartmentRepo repository) {
        super(repository);
    }
}
