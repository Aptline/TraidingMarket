package ru.bspl.pet.tradingmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bspl.pet.tradingmarket.models.Person;
import ru.bspl.pet.tradingmarket.services.PersonService;
import ru.bspl.pet.tradingmarket.services.RegistrationService;
import ru.bspl.pet.tradingmarket.utils.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;
    private final PersonService personService;
    private final RegistrationService registrationService;


    @Autowired
    public AuthController(PersonValidator personValidator, PersonService personService, RegistrationService registrationService) {
        this.personValidator = personValidator;
        this.personService = personService;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors())
            return "/auth/registration";
        registrationService.save(person);
        return "redirect:/auth/login";
    }
}