package com.itstimx.myFirstWebBlog.repositories;

import com.itstimx.myFirstWebBlog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository  extends CrudRepository<Post, Long> {   // все нужные функции унаследование от  CrudRepository
}
