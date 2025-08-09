package com.haoshe.community.controller;

import com.haoshe.community.service.AlphaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha") // give a name to the class, so it can handle requests to /Alpha
public class AlphaController {
    /*
    Controller handles HTTP requests, the service layer handles business logic.
    Controllers call this service, and the service accesses the DAO layer.
    Therefor, we inject AlphaService here to perform service operation
    The getData() method demonstrates how the controller interacts with the service layer,
    which in turn calls the find() method to retrieve data from the DAO layer
    */
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/Hello") // give a name to the method, so it can handle requests to /Alpha/Hello
    @ResponseBody                //  we only want server to return a String. Without the annotation, it will return a webpage.
    public String sayHello(){
        return "Hello, Spring boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        // === Retrieve and print request data ===

        // Print HTTP request method (e.g., GET, POST)
        System.out.println(request.getMethod());
        // Print the servlet path (URI mapping after the context path)
        System.out.println(request.getServletPath());
        // Iterate over all request headers and print their names and values
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        // Print the value of the "code" request parameter (e.g., ?code=123)
        System.out.println(request.getParameter("code"));

        // === Send response back to client ===

        // Set the response content type and character encoding
        response.setContentType("text/html;charset=utf-8");
        // Write HTML content to the response
        try(PrintWriter writer = response.getWriter();){
            writer.write("<h1>community</h1>");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // Handle GET requests to retrieve student data
    // Example request: /students?current=1&limit=20
    // Retrieves student data for page 1, with up to 20 records per page.
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        // Print the received values to the console
        System.out.println(current);
        System.out.println(limit);
        // Return a simple text response to the client
        return "some students";
    }

    // Handle GET request for a single student by ID
    // Example: /student/101 -> Retrieves the student with ID 101
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(
            // Extract the "id" value from the URL path and bind it to the 'id' parameter
            @PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    // Handle POST request to save a student
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // Handle GET requests and return an HTML view response
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        // Create a ModelAndView object to hold both model data and view name
        ModelAndView mav = new ModelAndView();
        // Add model attributes (key-value pairs) t be passed to the view
        mav.addObject("name", "Hao She");
        mav.addObject("age", 30);
        // The template file is located in src/main/resources/templates/demo/view.html;
        // In setViewName, omit the .html extension - just use the path within the templates
        mav.setViewName("/demo/view");
        // Return the ModelAndView, which will be rendered by the view resolver
        return mav;
    }

    /*
    Difference from the previous /teacher example:
    This method uses a Model parameter instead of creating a ModelAndView object.
    In /teacher, we explicitly created ModelAndView to set both model data and the view name in one object.
    In /school, we only use Model to pass data, and the method’s return value is a String representing the view name.
    Both approaches are valid in Spring MVC — using Model is often more concise, while ModelAndView can be clearer
    when you want to handle model data and view name together in a single object.
     */
    // Handle GET requests and return an HTML view
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        // Add model attributes (key-value pairs) to be passed to the view
        model.addAttribute("name", "Trinity College Dublin");
        model.addAttribute("age", 433);
        // Return the logical view name (template path without the .html extension)
        // The template is located at src/main/resources/templates/demo/view.html
        return "/demo/view";
    }

    /*
    Asynchronous request example:
    Imagine you’re on an online shopping site. You type “headphones” into the search box, and as you type,
    the site shows live suggestions (“headphones wireless,” “headphones noise-cancelling,” etc.) without refreshing the whole page.
    Behind the scenes:
    Each keystroke triggers an asynchronous request to the server.
    The page keeps running, you can keep typing, and results appear as soon as the server responds.
    This is AJAX (Asynchronous JavaScript and XML) in action.
     */

    // Handle GET requests and respond with JSON data (for asynchronous requests)
    // Flow: Java object -> JSON string -> JavaScript object
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        // Create a map to hold employee data
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "Hao She");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        // Return the map — Spring MVC will automatically convert it to JSON
        return emp;
    }

    // Handle GET requests and return a list of employees in JSON format
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        // Create a list to hold multiple employees
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "Hao She");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "Brad Pitt");
        emp.put("age", 26);
        emp.put("salary", 10000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "Niko Kidman");
        emp.put("age", 18);
        emp.put("salary", 20000.00);
        list.add(emp);
        // Return the list — Spring will convert it to a JSON array
        return list;
    }
}
