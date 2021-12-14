package jpa.mappers;

import jpa.daos.Repository;
import jpa.dtos.RepositoryDTO;
/**
 * Class that models the Mappers with the methods from and to DTO for Mapper Repository class
 * @author sps169, FedericoTB
 */
public class RepositoryMapper extends BaseMapper<Repository, RepositoryDTO> {
    /**
     * Method that map a RepositoryDTO object into a Repository object.
     * @param item RepositoryDTO to map
     * @return Repository mapped
     */
    @Override
    public Repository fromDTO(RepositoryDTO item) {
        return new Repository(item.getId(), item.getName(), item.getCreationDate(), item.getProject());
    }
    /**
     * Method that map a Repository object into a RepositoryDTO object.
     * @param item Repository to map
     * @return RepositoryDTO mapped
     */
    @Override
    public RepositoryDTO toDTO(Repository item) {
        return new RepositoryDTO(item.getId(), item.getName(), item.getCreationDate(), item.getProject());
    }
}
