package paf.day21.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import paf.day21.model.Price;
import paf.day21.service.CustSvc;

@Controller 
@RequestMapping
public class WindController {
    
    @Autowired
    CustSvc svc;

    @GetMapping(path = "/total")
    public String showTotalSearchHome (){
        return "total";
    }

    @GetMapping(path = "/update")
    public String showUpdateForm (){
        return "updates";
    }

    @GetMapping(path = "/order/total")
    public String getPrice(@RequestParam String order_id, Model model){
            System.out.println(order_id);
            try{
                Price p = svc.getPrices(order_id); 
                model.addAttribute("order_id", p.getOrder_id());
                model.addAttribute("cost_price", p.getCost_price());
                model.addAttribute("cust_id", p.getCust_id());
                model.addAttribute("total_price", p.getTotal_price());
                model.addAttribute("order_date", p.getOrder_date());

            }catch(Exception e){
                e.printStackTrace();
                return "error.html";
            }
            return "result";
    }



}
