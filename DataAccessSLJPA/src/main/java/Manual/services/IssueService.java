//package Manual.services;
//
//import Manual.daos.Issue;
//import Manual.daos.Programmer;
//import Manual.daos.Repository;
//import Manual.dtos.IssueDTO;
//import Manual.mappers.IssueMapper;
//import Manual.repositories.IssueRepo;
//import Manual.repositories.ProgrammerRepo;
//import Manual.repositories.RepositoryRepo;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class IssueService extends BaseService<Issue,Long, IssueRepo> {
//    private final IssueMapper mapper = new IssueMapper();
//    public IssueService(IssueRepo repository) {super(repository);}
//
//    public List<IssueDTO> getAllIssues() throws SQLException {
//        List<Issue> issues = this.findAll().orElseThrow(()-> new SQLException("Error al obtener todos los Issues"));
//        List<IssueDTO> result = new ArrayList<>();
//        for(Issue issue: issues){
//            IssueDTO issueDTO = mapper.toDTO(issue);
//            issueDTO.setRepository(this.getRepositoryById(issue.getRepositoryId()));
//            issueDTO.setBoss(this.getBossProgrammerById(issue.getBossId()));
//            result.add(issueDTO);
//        }
//        return result;
//    }
//
//    public IssueDTO getIssueById(Long id) throws SQLException {
//        Issue issue = this.getById(id).orElseThrow(() -> new SQLException("Error al obtener Issue por Id "+id));
//        IssueDTO issueDTO = mapper.toDTO(issue);
//        issueDTO.setRepository(this.getRepositoryById(issue.getRepositoryId()));
//        issueDTO.setBoss(this.getBossProgrammerById(issue.getBossId()));
//        return issueDTO;
//    }
//
//    public IssueDTO insertIssue (IssueDTO issueDTO) throws SQLException {
//        Issue issue = this.insert(mapper.fromDTO(issueDTO)).orElseThrow(()->new SQLException("Error al insertar Issue"));
//        IssueDTO result = mapper.toDTO(issue);
//        result.setRepository(this.getRepositoryById(issue.getRepositoryId()));
//        result.setBoss(this.getBossProgrammerById(issue.getBossId()));
//        return result;
//    }
//
//    public IssueDTO updateIssue (IssueDTO issueDTO) throws SQLException {
//        Issue issue = this.update(mapper.fromDTO(issueDTO)).orElseThrow(()->new SQLException("Error al actualizar Issue"));
//        IssueDTO result = mapper.toDTO(issue);
//        result.setRepository(this.getRepositoryById(issue.getRepositoryId()));
//        result.setBoss(this.getBossProgrammerById(issue.getBossId()));
//        return result;
//    }
//
//    public IssueDTO deleteIssue (IssueDTO issueDTO) throws SQLException {
//        Issue issue = this.delete(mapper.fromDTO(issueDTO)).orElseThrow(()->new SQLException("Error al borrar Issue"));
//        IssueDTO result = mapper.toDTO(issue);
//        result.setRepository(this.getRepositoryById(issue.getRepositoryId()));
//        result.setBoss(this.getBossProgrammerById(issue.getBossId()));
//        return result;
//    }
//
//    private Repository getRepositoryById(long repositoryId) throws SQLException {
//        RepositoryService repositoryService = new RepositoryService(new RepositoryRepo());
//        return repositoryService.getById(repositoryId).orElseThrow(()->new SQLException("Error al intentar obtener Repository por id"+ repositoryId+" para Issue"));
//    }
//    private Programmer getBossProgrammerById(long bossId) throws SQLException {
//        ProgrammerService programmerService = new ProgrammerService(new ProgrammerRepo());
//        return programmerService.getById(bossId).orElseThrow(()->new SQLException("Error al intentar obtener Boss Programmer por id"+ bossId+" para Issue"));
//    }
//}
