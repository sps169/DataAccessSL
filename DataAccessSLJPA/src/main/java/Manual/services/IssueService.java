package Manual.services;

import Manual.daos.Issue;
import Manual.dtos.IssueDTO;
import Manual.mappers.IssueMapper;
import Manual.repositories.IssueRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IssueService extends BaseService<Issue,Long, IssueRepo> {
    private final IssueMapper mapper = new IssueMapper();
    public IssueService(IssueRepo repository) {super(repository);}

    public List<IssueDTO> getAllIssues() throws SQLException {
        List<Issue> issues = this.findAll().orElseThrow(()-> new SQLException("Error al obtener todos los Issues"));
        List<IssueDTO> result = new ArrayList<>();
        for(Issue issue: issues){
            IssueDTO issueDTO = mapper.toDTO(issue);
            result.add(issueDTO);
        }
        return result;
    }

    public IssueDTO getIssueById(Long id) throws SQLException {
        Issue issue = this.getById(id).orElseThrow(() -> new SQLException("Error al obtener Issue por Id "+id));
        IssueDTO issueDTO = mapper.toDTO(issue);
        return issueDTO;
    }

    public IssueDTO insertIssue (IssueDTO issueDTO) throws SQLException {
        Issue issue = this.insert(mapper.fromDTO(issueDTO)).orElseThrow(()->new SQLException("Error al insertar Issue"));
        IssueDTO result = mapper.toDTO(issue);
        return result;
    }

    public IssueDTO updateIssue (IssueDTO issueDTO) throws SQLException {
        Issue issue = this.update(mapper.fromDTO(issueDTO)).orElseThrow(()->new SQLException("Error al actualizar Issue"));
        IssueDTO result = mapper.toDTO(issue);
        return result;
    }

    public IssueDTO deleteIssue (IssueDTO issueDTO) throws SQLException {
        Issue issue = this.delete(mapper.fromDTO(issueDTO)).orElseThrow(()->new SQLException("Error al borrar Issue"));
        IssueDTO result = mapper.toDTO(issue);
        return result;
    }
}
