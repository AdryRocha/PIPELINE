package br.com.demo.exceptions;

import br.com.demo.service.PhonebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Enumeration;
import java.util.Set;

/**
 * Class that handles the exceptions and violations from the controller.
 *
 * @author fvilarinho
 */
@ControllerAdvice
public class ExceptionHandler{
    @Autowired
    private PhonebookService service;
    
    private void populateAttributes(Model model, HttpServletRequest request){
        Enumeration<String> enumeration = request.getParameterNames();
    
        while(enumeration.hasMoreElements()){
            String parameterId = enumeration.nextElement();
        
            model.addAttribute(parameterId, request.getParameter(parameterId));
        }
    }
    
    // Handles violations of the form.
    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public String handleViolations(Model model, HttpServletRequest request, ConstraintViolationException ex){
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        
        for(ConstraintViolation<?> violation: violations){
            String messageId = violation.getPropertyPath().toString().replaceAll("save.", "").concat("Message");
            String messageValue = violation.getMessage();
            
            model.addAttribute(messageId, messageValue);
        }
        
        populateAttributes(model, request);
        
        return "form";
    }
    
    // When the data already exists.
    @org.springframework.web.bind.annotation.ExceptionHandler(ItemAlreadyExistsException.class)
    public String handleItemAlreadyExists(Model model, HttpServletRequest request, ItemAlreadyExistsException ex){
        model.addAttribute("nameMessage", "This item already exists!");
        
        populateAttributes(model, request);
        
        return "form";
    }
}
