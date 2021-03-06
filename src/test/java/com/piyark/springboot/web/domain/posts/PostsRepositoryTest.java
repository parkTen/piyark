package com.piyark.springboot.web.domain.posts;

import com.piyark.springboot.domain.posts.Posts;
import com.piyark.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {

        String title = "테스트게시글";
        String content = "테스트본분";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("piy@kakao.com")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeentity_등록() {

        LocalDateTime now = LocalDateTime.of(2022,1,1,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        List<Posts> postsList = postsRepository.findAll();;

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>> createDate"+posts.getCreadtedDate()+"modifiedDate=" + posts.getModifedDate());

        assertThat(posts.getCreadtedDate()).isAfter(now);
        assertThat(posts.getModifedDate()).isAfter(now);

    }
}
