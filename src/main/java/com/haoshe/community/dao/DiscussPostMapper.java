package com.haoshe.community.dao;

import com.haoshe.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    /*
     * Retrieves a list of discussion posts.
     *
     * Behavior:
     * - If userId == 0, all posts are returned (community homepage).
     * - If userId > 0, only posts created by the specified user are returned (personal homepage).
     *   This requires a dynamic SQL query based on the userId value.
     *
     * Pagination:
     * - offset: the starting row of the current page
     * - limit: the maximum number of posts to return per page
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    /*
     * If userId == 0, number of all posts are returned (community homepage).
     * if userId > 0, only number of posts created by the specified user are returned (personal homepage).
     *
     * The @Param("userId") annotation is required because MyBatis uses it
     * to reference the parameter in the corresponding SQL statement.
     * Without @Param, MyBatis would not know how to map the method argument
     * to the placeholder in the SQL (especially when there is only one parameter).
     */
    int selectDiscussPostRows(@Param("userId") int userId);
}
