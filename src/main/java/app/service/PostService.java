package app.service;

import app.entity.Category;
import app.entity.Post;
import app.repo.PostRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PostService {

    private final PostRepo postRepo;


    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }


    public Page<Post> listAll(int pageNumber){
        //Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 14);
        return postRepo
                .findAll(pageable);
    }

    public Page<Post> listByCategory(String category, int pageNumber){
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 2, sort);
        return
                postRepo.findAllByCategory(category, pageable);

    }

    public Post getPost(int id) {
        return
                postRepo
                .findById(id).get();
    }

    public List<Post> relatedPosts(int id){
        String category = getPost(id).getCategory();
        return postRepo.findAll()
                .stream()
                .filter(x -> x.getCategory().equals(category))
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Post> searchResult(String title) {
        return
                postRepo
                .findAll()
                .stream()
                .filter(x -> x.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());

    }

    public List<Post> latestPosts(){
        return postRepo.findAll()
                .stream().sorted(Comparator.comparingInt(Post::getId).reversed())
                .limit(4)
                .collect(Collectors.toList());

    }
}
