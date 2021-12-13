package jpa.mappers;

import jpa.daos.*;
import jpa.dtos.CommitDTO;

public class CommitMapper extends BaseMapper<Commit, CommitDTO>{

    @Override
    public Commit fromDTO(CommitDTO item) {
        return new Commit(item.getId(), item.getTitle(), item.getText(), item.getDate(), item.getRepository(), item.getProgrammer(), item.getIssue());
    }

    @Override
    public CommitDTO toDTO(Commit item) {

        return new CommitDTO(item.getId(), item.getTitle(), item.getText(), item.getDate(), item.getRepository(), item.getProgrammer(), item.getIssue());
    }
}