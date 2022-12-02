package paf.day21.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import paf.day21.model.Order;
import paf.day21.service.CustSvc;

@RestController
@EnableWebMvc //IDK HOW THIS RESOLVES THE POSTMAN SENDING PUT REQUEST BODY UNAVAILABLE ERROR BUT IT WORKS???!!! I GIVE UP ON ASKING WHY FOR NOW
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class WindRESTcontroller {
    
    @Autowired
    CustSvc custSvc;

    @GetMapping(path = "/customers")
    public ResponseEntity<String> getAllCustomers(){
        List<String> customers = custSvc.getAllCust();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for(String s: customers){
            arrBuilder.add(s);
        }
        JsonArray res = arrBuilder.build();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res.toString());
    }

    @GetMapping(path = "/customer/{customer_id}")
    public ResponseEntity<String> getCustomerDetails(@PathVariable String customer_id){
        System.out.println(">>>> "+customer_id);
        List<String> customers = custSvc.getCustomers(customer_id);
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for(String s: customers){
            arrBuilder.add(s);
        }
        JsonArray res = arrBuilder.build();

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res.toString());
    }

    @GetMapping(path = "/customer")
    public ResponseEntity<String> getCustomerRequestParam (@RequestParam String customer_id){
        System.out.println(customer_id);
        List<String> customers = custSvc.getCustomers(customer_id);
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for(String s: customers){
            arrBuilder.add(s);
        }
        JsonArray res = arrBuilder.build();

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res.toString());
    }


    @GetMapping(path = "/customer/{customer_id}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOrderDetails(@PathVariable String customer_id){
        List<Order> orders = custSvc.getOrders(customer_id);
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for(Order o: orders){
            arrBuilder.add(o.toJSON());
        }
        JsonArray res = arrBuilder.build();
        if(res.size()<1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body("{\"error_mesg\": \"record not found\"}");
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(res.toString());
    }

    //REQUEST PARAM METHOD WORKS WITHOUT ENABLE MVC ANNOTATION
    //might be better to use a reqbody instead
    // @PostMapping(path = "/update")
    // public ResponseEntity<String> addNewCustomer
    // (@RequestParam String company, @RequestParam String first_name, 
    // @RequestParam String last_name, @RequestParam String city, 
    // @RequestParam String state_province, @RequestParam String zip_postal_code){
    //     if(custSvc.addCustomer(company, first_name, last_name, city, state_province, zip_postal_code)){
    //         return ResponseEntity.status(HttpStatus.CREATED)
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .body("{\"STATUS 201\": \"CUSTOMER CREATED\"}");
    //     }else{
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .body("{\"error_mesg\": \"NOT CREATED\"}");
    //     }
    // }

    //can only test with POSTMAN, html form doesnt allow put methods
    // @PutMapping(path = "/putupdate")
    // public ResponseEntity<String> updateCustomer (@RequestParam String customer_id, @RequestParam String company, @RequestParam String first_name, @RequestParam String last_name){
    //     if(custSvc.updateCustomer(company, first_name, last_name, customer_id)){
    //         return ResponseEntity.status(HttpStatus.CREATED)
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .body("{\"STATUS 201\": \"CUSTOMER UPDATED\"}");
    //     }else{
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .body("{\"error_mesg\": \"NOT UPDATED\"}");
    //     }
    // }


    //REQUESTBODY METHOD
    @PutMapping(path = "/putupdate/{customer_id}", consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<String> updateCustomer (@PathVariable String customer_id ,@RequestBody MultiValueMap<String, String> form){
        String company = form.getFirst("company");
        String first_name = form.getFirst("first_name");
        String last_name = form.getFirst("last_name");
        // String customer_id = form.getFirst("customer_id");
        if(custSvc.updateCustomer(company, first_name, last_name, customer_id)){
            return ResponseEntity.status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body("{\"STATUS 201\": \"CUSTOMER UPDATED\"}");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body("{\"error_mesg\": \"NOT UPDATED\"}");
        }
    }

    @PostMapping(path = "/update")
    public ResponseEntity<String> addNewCustomer (@RequestBody MultiValueMap<String, String> form){
        String company = form.getFirst("company");
        String first_name = form.getFirst("first_name");
        String last_name = form.getFirst("last_name");
        String city = form.getFirst("city");
        String state_province = form.getFirst("state_province");
        String zip_postal_code = form.getFirst("zip_postal_code");
        if(custSvc.addCustomer(company, first_name, last_name, city, state_province, zip_postal_code)){
            return ResponseEntity.status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body("{\"STATUS 201\": \"CUSTOMER CREATED\"}");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body("{\"error_mesg\": \"NOT CREATED\"}");
        }
    }




}
