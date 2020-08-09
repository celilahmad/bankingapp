package app.service;

import app.entity.Post;
import app.repo.PostRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PostService {

    private final PostRepo postRepo;


    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public List<Post> postCategory(String category){
        return
                postRepo.findAll().stream()
                        .filter(x -> x.getCategory().equals(category))
                        .collect(Collectors.toList());
    }

    public List<Post> allNews(){
        return
                postRepo.findAll();
    }

    public Page<Post> listAll(int pageNumber){
        //Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 14);
        return postRepo
                .findAll(pageable);


    }
}
