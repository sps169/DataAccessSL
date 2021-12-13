package jpa.mappers;

import jpa.daos.Project;
import jpa.daos.Repository;
import jpa.dtos.RepositoryDTO;

public class RepositoryMapper extends BaseMapper<Repository, RepositoryDTO> {
    @Override
    public Repository fromDTO(RepositoryDTO item) {
        return new Repository(item.getId(), item.getName(), item.getCreationDate(), item.getProject());
    }

    @Override
    public RepositoryDTO toDTO(Repository item) {
        return new RepositoryDTO(item.getId(), item.getName(), item.getCreationDate(),
                new Project(item.getProject().getId()));
                //item.getProject());
    }
}
