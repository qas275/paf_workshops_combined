package paf.day21.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paf.day21.model.Order;
import paf.day21.model.Price;
import paf.day21.repositories.CustRepo;
import paf.day21.repositories.PriceRepo;

@Service
public class CustSvc {
    
    @Autowired
    CustRepo custRepo;

    @Autowired
    PriceRepo priceRepo;

    public List<String> getCustomers (String Id){
        List<String> customers = custRepo.getCustomers(Id);
        return customers;

    }

    public List<String> getAllCust(){
        List<String> customers = custRepo.getAllCustomers();
        return customers;
    }

    public List<Order> getOrders(String Id){
        List<Order> orders = custRepo.getOrders(Id);
        return orders;
    }

    public Price getPrices(String Id) throws Exception{
        Price price = priceRepo.getPrice(Id);
        return price;
    }

    //return checks whether was updated as boolean result
    public boolean addCustomer(String company, String first_name, String last_name, String city, String state_province, String zip_postal_code){
        int res = custRepo.addCustomer(company, first_name, last_name, city, state_province, zip_postal_code);
        return res>0; 
    }

    public boolean updateCustomer(String company, String first_name, String last_name, String id){
        int res = custRepo.updateCustomer(company, first_name, last_name, id);
        return res>0; 
    }
}
