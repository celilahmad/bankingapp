package app.service;

import app.entity.Comment;
import app.entity.Post;
import app.repo.CommentRepo;
import app.repo.PostRepo;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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

    public List<Post> mostCommented(){
        return commentRepo.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Comment::getId).reversed())
                .limit(4)
                .map(x -> x.getPost())
                .collect(Collectors.toList());
    }

    public long countComments(int id){
        return
                commentRepo
                        .findAll()
                        .stream()
                        .filter(x -> x.getPost().getId() == id)
                        .collect(Collectors.counting());
    }


}
