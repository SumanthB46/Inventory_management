package com.Inventory.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Inventory.entity.RegisterEntity;
import com.Inventory.service.RegisterService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class RegisterController {

    @Autowired
    private RegisterService service;
    
    @GetMapping("/app")
    public String loadIndex() {
        return "index";
    }

    @GetMapping("/signuppage")
    public String openSignupPage() {
        return "Signup";
    }

    @GetMapping("/loginpage")
    public String openLoginPage() {
        return "login";
    }

    @GetMapping("/forgot")
    public String openForgotPage() {
        return "forgot";
    }

    @GetMapping("/attendielogin")
    public String openAttendieLogin() {
        return "AttendieLogin";
    }

    // ✅ Home Page (Only for logged-in users)
    @GetMapping("Home")
    public String openHome(HttpSession session, Model model) {
        if (session.getAttribute("uname") == null) {
            return "redirect:/loginpage"; // Redirect if not logged in
        }
        
        model.addAttribute("uname", session.getAttribute("uname"));
        model.addAttribute("umail", session.getAttribute("umail"));
        model.addAttribute("uphone", session.getAttribute("uphone"));

        return "Home";
    }

    // ✅ Registration Controller
    @PostMapping("userRegister")
    public String userRegister(@ModelAttribute RegisterEntity user, Model model) {
        boolean exist = service.checkUser(user.getUserEmail());
        String page = "";

        if (!exist) {
            Integer uid = service.saveUser(user);
            String uname = user.getUserName();

            if (uid > 0) {
                model.addAttribute("message", uname + " Registered Successfully with ID: " + uid);
                page = "login";
            } else {
                model.addAttribute("message", "Registration Unsuccessful");
                page = "Signup";
            }
        } else {
            model.addAttribute("message", "User already exists");
            page = "Signup";
        }
        return page;
    }

    // ✅ Login Controller
    @PostMapping("userLogin")
    public String userLogin(RegisterEntity entity, HttpSession session, Model model) {
        String status = service.loginUser(entity.getUserEmail(), entity.getUserPassword(), session);
        String page = "";

        if (status.equals("success")) {
            session.setAttribute("uname", entity.getUserEmail());
            model.addAttribute("uname", session.getAttribute("uname"));

            if (entity.getUserEmail().equals("admin@gmail.com") && entity.getUserPassword().equals("88888888")) {
                session.setAttribute("role", "ADMIN"); // Set role in session
                page = "Admin";
            } else {
                session.setAttribute("role", "USER");
                page = "user";
            }
        } else {
            model.addAttribute("message", "Login failed");
            page = "login";
        }
        return page;
    }

    // ✅ Logout
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginpage";
    }

    // ✅ Forgot Password
    @PostMapping("forgotPass")
    public String forgotPass(RegisterEntity entity, Model model) {
        String result = service.forgotPassword(entity.getUserEmail(), entity.getUserPassword());
        if (result.equals("success")) {
            model.addAttribute("message", "Password changed successfully");
            return "login";
        } else {
            model.addAttribute("message", "No such user email found");
            return "forgot";
        }
    }

    // ✅ Email Check for AJAX
    @GetMapping("/checkEmail")
    public ResponseEntity<Boolean> checkCode(@RequestParam String useremail) {
        boolean isCodeAvailable = service.isCodeAvailable(useremail);
        return ResponseEntity.ok(isCodeAvailable);
    }

    // ✅ Admin Only: View Users (Restrict access)
    @GetMapping("/users")
    public String data(HttpSession session, Model model, @RequestParam(required = false) String message) {
        if (!"ADMIN".equals(session.getAttribute("role"))) {
            return "redirect:/loginpage"; // Redirect if not admin
        }

        List<RegisterEntity> list = service.data();
        if (message != null) {
            model.addAttribute("message", message);
        }
        model.addAttribute("list", list);
        return "User";
    }

    // ✅ Admin Only: Add Events
    @GetMapping("/addEvents")
    public String adminAddEvents(HttpSession session) {
        if (!"ADMIN".equals(session.getAttribute("role"))) {
            return "redirect:/loginpage";
        }
        return "addEvents";
    }

    // ✅ Admin Only: Delete Events
    @GetMapping("/deleteEvents")
    public String adminDeleteEvents(HttpSession session) {
        if (!"ADMIN".equals(session.getAttribute("role"))) {
            return "redirect:/loginpage";
        }
        return "deleteEvents";
    }

    // ✅ Admin Only: Add Attendees
    @GetMapping("/addAttendies")
    public String adminAddAttendies(HttpSession session) {
        if (!"ADMIN".equals(session.getAttribute("role"))) {
            return "redirect:/loginpage";
        }
        return "AddAttendies";
    }

    // ✅ Back to Admin Page (Ensure only admins can access)
    @GetMapping("/userL")
    public String backToAdminPage(HttpSession session) {
        if (!"ADMIN".equals(session.getAttribute("role"))) {
            return "redirect:/loginpage";
        }
        return "Admin";
    }
}
