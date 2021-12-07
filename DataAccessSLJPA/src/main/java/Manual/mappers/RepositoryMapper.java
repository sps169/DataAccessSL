//package Manual.mappers;
//
//import Manual.daos.Repository;
//import Manual.dtos.RepositoryDTO;
//
//public class RepositoryMapper extends BaseMapper<Repository, RepositoryDTO> {
//    @Override
//    public Repository fromDTO(RepositoryDTO item) {
//        return new Repository(item.getId(), item.getName(), item.getCreationDate(), item.getProject().getId());
//    }
//
//    @Override
//    public RepositoryDTO toDTO(Repository item) {
//        return new RepositoryDTO(item.getId(), item.getName(), item.getCreationDate());
//    }
//}
