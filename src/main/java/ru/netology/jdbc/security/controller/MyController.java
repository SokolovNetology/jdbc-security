package ru.netology.jdbc.security.controller;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.netology.jdbc.security.entity.Customer;
import ru.netology.jdbc.security.entity.Order;
import ru.netology.jdbc.security.model.CustomerForRequest;
import ru.netology.jdbc.security.model.OrderForRequest;
import ru.netology.jdbc.security.service.MyService;

@RestController
public class MyController {
    private final MyService service;

    public MyController(MyService service) {
        this.service = service;
    }

    @PostMapping("add/customer")
    public Customer addCustomer(@RequestBody CustomerForRequest customer) {
        return service.addCustomer(customer);
    }

    @PostMapping("add/order")
    public Order addOrder(@RequestBody OrderForRequest order) {
        return service.addOrder(order);
    }

    @GetMapping("products/fetch-product")
    @ResponseBody
    public String getProductName(@RequestParam String name) {
        return service.getProductName(name);
    }
    @GetMapping("welcome")
    public String welcome() {return service.welcome();}

    @GetMapping("read")
    @Secured("ROLE_READ")
    public String read() {return service.read();}

    @GetMapping("write")
    @RolesAllowed("WRITE")
    public String write() {return service.write();}

    @GetMapping("delete")
    @PreAuthorize("hasAnyRole('ROLE_WRITE', 'ROLE_DELETE')")
    public String delete() {return service.delete();}

    @GetMapping("super_method")
    @PreAuthorize("#userName == authentication.principal.username")
    public String superMethod(@RequestParam String userName){
        return service.superMethod(userName);
    }

}
