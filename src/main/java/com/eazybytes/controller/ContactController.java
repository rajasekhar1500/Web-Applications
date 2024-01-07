package com.eazybytes.controller;

import com.eazybytes.model.Contact;
import com.eazybytes.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Slf4j
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    /*@RequestMapping(value = "/saveMsg", method= RequestMethod.POST)
    public ModelAndView saveMessage(Contact contact) {
        *//*log.info("Name: {}", contact.getName());
        log.info("Mobile Number: {}", contact.getMobileNum());
        log.info("Email Address: {}", contact.getEmail());
        log.info("Subject: {}",contact.getSubject());
        log.info("Message: {}", contact.getMessage());*//*
     *//*log.info(contact.toString());*//*
        contactService.saveMessageDetails(contact);
        return new ModelAndView("redirect:/contact");
    }*/
    @RequestMapping(value = "/saveMsg", method = POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        if (errors.hasErrors()) {
            log.error("Contact form validation failed due to : {}", errors.toString());
            return "contact.html";
        }
        contactService.saveMessageDetails(contact);

        return "redirect:/contact";
    }
    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(Model model) {
        List<Contact> contactsMsgs = contactService.findMsgsWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMsgs", contactsMsgs);
        return modelAndView;
    }
    @RequestMapping(value = "/closeMsg",method = GET)
    public String closeMsg(@RequestParam int id) {
        contactService.updateMsgStatus(id);
        return "redirect:/displayMessages";
    }
}
