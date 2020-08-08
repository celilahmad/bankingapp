package app.service;

import app.repo.PostRepo;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepo postRepo;


    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }


}
