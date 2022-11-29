package paf.day21.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import paf.day21.model.Order;

import static paf.day21.repositories.Query.*;


@Repository
public class CustRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<String> getCustomers(String Id){
        List<String> customers = new LinkedList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_BY_ID, Id);
        while(rs.next()){
            customers.add(rs.getString("name"));
        }
        return customers;
    }

    public List<Order> getOrders(String Id){
        List<Order> orders = new LinkedList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_ORDERS, Id);
        while(rs.next()){
            orders.add(Order.create(rs));
        }
        return orders;

    }

    public List<String> getAllCustomers(){
        List<String> customers = new LinkedList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_ALL_CUSTOMERS);
        while(rs.next()){
            customers.add(rs.getString("first_name"));
        }
        return customers;
    }

    //int res is number of rows affected in update
    public int addCustomer(String company, String first_name, String last_name, String city, String state_province, String zip_postal_code){
        int res = jdbcTemplate.update(SQL_ADD_CUSTOMER, company, first_name,last_name, city, state_province, zip_postal_code);
        return res;
    }

    public int updateCustomer(String company, String first_name, String last_name, String id){
        int res = jdbcTemplate.update(SQL_UPDATE_CUSTOEMER, company, first_name,last_name, id);
        return res;
    }
}
