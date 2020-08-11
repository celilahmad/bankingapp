package app.service;

import app.entity.Comment;
import app.repo.CommentRepo;
import app.repo.PostRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepo commentRepo;

    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;

    }

    public List<Comment> postComments(int postId){
        return commentRepo
                .findAll()
                .stream()
                .filter(x -> x.getPost().getId() == postId)
                .collect(Collectors.toList());
    }

    public void saveComment(Comment comment){
        commentRepo.save(comment);
    }


}
