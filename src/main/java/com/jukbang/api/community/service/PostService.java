package com.jukbang.api.community.service;

import com.jukbang.api.community.CommunityRole;
import com.jukbang.api.community.dto.MyPostListDto;
import com.jukbang.api.community.dto.PostListDto;
import com.jukbang.api.community.entity.Post;
import com.jukbang.api.community.exception.PostNotFoundException;
import com.jukbang.api.community.repository.PostRepository;
import com.jukbang.api.community.request.CreatePostRequest;
import com.jukbang.api.community.request.UpdatePostRequest;
import com.jukbang.api.user.entity.User;
import com.jukbang.api.user.exception.UserNotFoundException;
import com.jukbang.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.jukbang.api.community.dto.PostDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    /**
     * 게시글 목록 불러오기 기능
     *
     * @return List<PostListDto>
     */
    @Transactional
    public List<PostListDto> getPostList(CommunityRole role) {
        List<Post> postEntities = postRepository.findAllByRole(role);

        List<PostListDto> postListDto_List= new ArrayList<>();

        for (Post postEntity : postEntities) {
            PostListDto postListDTO = PostListDto.builder()
                    .postId(postEntity.getPostId())
                    .title(postEntity.getTitle())
                    .writer(postEntity.getWriter())
                    .updatedDate(postEntity.getModifiedDate())
                    .comments(postEntity.getComments())
                    .views(postEntity.getViews())
                    .build();

            postListDto_List.add(postListDTO);
        }

        return postListDto_List;
    }
/*
    커뮤니티 내 내 게시글 불러오기 기능 => 프런트 ppt 에 없음
    1. 게시글 리스트 형식으로 할지?
    2. 게시글 상세정보 형식으로 할지?
    */
    @Transactional
    public List<MyPostListDto> getMyPosts(String userid) {
        List<Post> postEntities = postRepository.findAllByWriter_UserId(userid);

        List<MyPostListDto> postDtoList = new ArrayList<>();

        for (Post postEntity : postEntities) {
            MyPostListDto mypostListDto = MyPostListDto.builder()
                    .postId(postEntity.getPostId())
                    .title(postEntity.getTitle())
                    .views(postEntity.getViews())
                    .comments(postEntity.getComments())
                    .updatedDate(postEntity.getModifiedDate())
                    .build();

            postDtoList.add(mypostListDto);
        }
        return postDtoList;
    }


    /**
     *  게시글 작성하기 기능
     *
     * @param univId
     * @param userId
     * @param createPostRequest
     *
     * @return (long) postId
     */
    @Transactional
    public Long savePost(
            int univId,
            String userId,
            CommunityRole role,
            CreatePostRequest createPostRequest
    ) {
        return postRepository.save(
                new Post(
                        createPostRequest.getTitle(),
                        createPostRequest.getBody(),
                        userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new),
                        univId,
                        role
                )
        ).getPostId();

    }

    /**
     * 단일 게시글 불러오기 기능
     *
     * @param postId
     *
     * @return postDto
     */
    @Transactional
    public PostDto getPost(Long postId) {

        Post post = postRepository.findByPostId(postId).orElseThrow(PostNotFoundException::new);


        post.addViews();

        PostDto postDto = PostDto.builder()
                .title(post.getTitle())
                .writer(post.getWriter())
                .body(post.getBody())
                .views(post.getViews())
                .updatedDate(post.getModifiedDate())
                .build();


        return postDto;
    }

    /**
     * 게시글 수정하기 기능
     *
     * @param postId
     * @param updatePostRequest
     *
     * @return (long) postId
     */
    @Transactional
    public Long rewritePost(
            Long postId,
            String userId,
            UpdatePostRequest updatePostRequest
    ) {


        // 존재하는 Id 인지 확인
        userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findByPostId(postId).orElseThrow(PostNotFoundException::new);
        post.updatePost(updatePostRequest.getTitle(), updatePostRequest.getBody());

        return post.getPostId();

    }


    /**
     * 게시글 삭제하기 기능
     *
     * @param postId
     */
    @Transactional
    public void deletePost(Long postId,String userId) {

        // 존재하는 Id 인지 확인
        userRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);

        // 존재하는 게시글 인지 확인
        postRepository.findByPostId(postId).orElseThrow(PostNotFoundException::new);

        postRepository.deleteById(postId);
    }

}
