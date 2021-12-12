package jpa.mappers;

import jpa.daos.Issue;
import jpa.dtos.IssueDTO;

public class IssueMapper extends BaseMapper<Issue, IssueDTO>{
    @Override
    public Issue fromDTO(IssueDTO item) {
        return new Issue(item.getId(), item.getTitle(), item.getText(), item.getDate(),
                item.getState(), item.getRepository(), item.getBoss());
    }

    @Override
    public IssueDTO toDTO(Issue item) {
        return new IssueDTO(item.getId(), item.getTitle(), item.getText(), item.getDate(),
                item.getState(), item.getRepository(), item.getBoss());
    }
}
