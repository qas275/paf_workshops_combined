package paf.day21.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Order {
    private Date order_date;
    private String shipping_fee;

    public Date getOrder_date() {
        return order_date;
    }
    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
    public String getShipping_fee() {
        return shipping_fee;
    }
    public void setShipping_fee(String shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public static Order create(SqlRowSet rs){
        Order order = new Order();
        String time = rs.getString("order_date");
        System.out.println(time);
        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-mm-dd");
        Date datess = new Date();
        try {
            datess = formatter2.parse((time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
        order.setOrder_date(datess);
        System.out.println(datess);
        order.setShipping_fee(rs.getString("fee"));
        return order;
    }

    public JsonObject toJSON(){
        return Json.createObjectBuilder().add("order_date", getOrder_date().toString()).add("fee", getShipping_fee()).build();
    }

}
