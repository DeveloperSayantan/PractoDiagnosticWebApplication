package com.practo.webControlller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.practo.dao.repository.CustomerRepo;
import com.practo.dao.repository.FeedBackRepo;
import com.practo.dao.repository.TestRepo;
import com.practo.entity.CustomerEntity;
import com.practo.entity.FeedbackEntity;
import com.practo.entity.TestEntity;
import com.practo.serviceImpl.CustomerServiceImpl;
import com.practo.serviceImpl.FeedBackServiceImpl;
import com.practo.serviceImpl.TestServiceImpl;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("")
public class webController {

//Auto wired Repo classes.
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	TestRepo testRepo;
	@Autowired
	FeedBackRepo feedBackRepo;

//Auto wired Service classes.
	@Autowired
	CustomerServiceImpl customerServiceImpl;
	@Autowired
	TestServiceImpl testServiceImpl;
	@Autowired
	FeedBackServiceImpl feedBackServiceImpl;
	
	
	@GetMapping("/index")
	public String index() {
		
		return "index";
	}
	
//	@GetMapping("/login")
//	public String login() {
//		
//		return "login";
//	}

	
////////////////////////////////////Registration//////////////////////////////////////////////////////////////	
	@GetMapping("/register")
	public String dispalyForm(Model model) {

		model.addAttribute("customerForm", new CustomerEntity());
		return "register";
	}
	
	@PostMapping("/registerSave")
		public String registerCustomer(@ModelAttribute CustomerEntity customerEntity, Model model) {
			
				System.out.println("Name: " + customerEntity.getName());
		        System.out.println("Email: " + customerEntity.getEmail());
		        System.out.println("Mobile: " + customerEntity.getMobile());
		        System.out.println("City: " + customerEntity.getCity());
		        System.out.println("Password: " + customerEntity.getPassword());
		        
		        customerRepo.save(customerEntity);
		        model.addAttribute("customerForm",new CustomerEntity());
		       
		        
		        return "redirect:/login";
    }
	
/////////////////////////////////////////////Login & Logout///////////////////////////////////////////////////////
	
    @GetMapping("/login")
    public String yourPage(HttpSession session) {
        String logoutMessage = (String) session.getAttribute("logoutMessage");
        if (logoutMessage != null) {
        	
            session.removeAttribute("logoutMessage"); // Clear the attribute
        }
        return "login";
    }
	
	@PostMapping("/loginCustomer")
	public String login(@RequestParam String email,@RequestParam String password,HttpSession session,Model model) {
		
		//System.out.println(inputCsmailpass.getEmail()+" "+inputCsmailpass.getPassword());
		
		CustomerEntity user = customerServiceImpl.loginByEmail(email);
		
		if( user != null && user.getPassword().equals(password)) {
			
			session.setAttribute("user", user.getName());
			session.setAttribute("userId", user.getId());
			return "dashboard";
		}
			else {
	            // Failed login
	            model.addAttribute("error", "Invalid email or password");
	           
	            return "login";
		}
		
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
	    session.setAttribute("logoutMessage", "Successfully Logout");
	    session.invalidate();
	    return "redirect:/login";
	}
    
///////////////////////////////////////Creating Controllers for Test/////////////////////////////////////////////////
    @PostMapping("/booking")
	public String bookTest(@ModelAttribute("fileUpload")@RequestParam("file") MultipartFile file,
			@RequestParam("test_name") String name, @RequestParam("test_date") Date date, @RequestParam("customer") CustomerEntity customerEntity,
			Model model , HttpSession session) throws IOException {
    		
    	
    	
    		testServiceImpl.addTest(name, date, file, customerEntity);
    	
//    	TestEntity upload = new TestEntity();
//    	
//    	upload.setFile(file.getOriginalFilename());
//    	TestEntity uploadfile = testRepo.save(testEntity);
//    	if(uploadfile != null ) {
//    		
//    		try {
//    			
//    			File saveFile = new ClassPathResource("static/testImg").getFile();
//    			Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
//    			
//    			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//    			
//    			testRepo.save(testEntity);
//				
//			} catch (Exception e) {
//
//				e.printStackTrace();
//			}
//    		
//    	}
    	session.setAttribute("msg", "Booking Successful!");
    	return "dashboard";
    	
}
//    @GetMapping("/booking")
//    public String details(Model model) {
//    	model.addAttribute("testDetailsList",testRepo.findAll());
//    	return "booking";
//    }
    
    @GetMapping("/dashboard")
    public String getTestDetails(CustomerEntity user,Model model){
    	
    	String email = user.getEmail();
    	CustomerEntity customer = customerRepo.findByEmail(email);
    	
    	if(customer != null) {
    		
    	List<TestEntity> testBooking = testRepo.findByCustomer(customer);
    	
    	System.out.println(testBooking);
    	
    	model.addAttribute("user",customer);
    	model.addAttribute("testBooking", testBooking);
    	}
    	return "dashboard";
    }
    
///////////////////////////////////////Creating Controllers for FeedBack///////////////////////////////////////////////// 
    @PostMapping("/postFeedback")
    public String giveFeedback(@ModelAttribute FeedbackEntity feedbackEntity, Model model, HttpSession session) {
		
        
        feedBackRepo.save(feedbackEntity);
        model.addAttribute("customerForm",new CustomerEntity());
       
        session.setAttribute("msg", "Feedback Submited !");
        return "redirect:/dashboard";
}

}

