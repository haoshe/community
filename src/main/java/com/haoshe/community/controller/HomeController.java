package com.haoshe.community.controller;

import com.haoshe.community.dao.DiscussPostMapper;
import com.haoshe.community.entity.DiscussPost;
import com.haoshe.community.entity.Page;
import com.haoshe.community.entity.User;
import com.haoshe.community.service.DiscussPostService;
import com.haoshe.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// No need to specify the path for the controller, as it is a root controller.
@Controller
public class HomeController {
    // Autowire the DiscussPostService and UserService to handle business logic.
    // Spring will automatically inject instances of these services.
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    /**
     * Handles GET requests to the home page ("/index").
     * This method retrieves a list of discussion posts, enriches each post with its
     * corresponding user information, and adds the combined data to the model for
     * rendering on the front end.
     *
     * @param model A Spring Model object to store data that will be passed to the view.
     * @param page  An instance of the Page object, automatically created and injected by Spring MVC
     * from the request parameters (e.g., ?current=2).
     * @return The logical view name "index", which Spring resolves to the
     * src/main/resources/templates/index.html template.
     */
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        // Spring MVC automatically creates and populates the 'page' object based on
        // request parameters (e.g., ?current=2), and then injects it into the Model.
        // This makes the 'page' object and its properties directly accessible in the Thymeleaf template.

        // Count the total number of rows (posts) to calculate the total number of pages.
        // The userId is 0 here, which means we are counting all posts.
        page.setRows(discussPostService.findDiscussPostRows(0));

        // Set the path for the pagination links, allowing for reusable pagination links on the page.
        page.setPath("/index");

        // Retrieve a paginated list of discussion posts from the service layer.
        // The parameters are the userId (0 for all users), the offset (starting row), and the limit (page size).
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());

        // Create a list to store a combination of post and user data.
        List<Map<String, Object>> discussPosts = new ArrayList<>();

        if(list != null){
            for(DiscussPost post : list){
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }

        // Add the enriched list of posts to the model, making it accessible in the template.
        model.addAttribute("discussPosts", discussPosts);
        // Return the logical view name "index", which the view resolver will map to the template file.
        return "/index";
    }
}
