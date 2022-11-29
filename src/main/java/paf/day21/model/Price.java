package paf.day21.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Price {
    
    private Integer order_id;
    private Integer cust_id;
    private Date order_date;
    private Integer total_price;
    private Integer cost_price;
    public Integer getOrder_id() {
        return order_id;
    }
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
    public Integer getCust_id() {
        return cust_id;
    }
    public void setCust_id(Integer cust_id) {
        this.cust_id = cust_id;
    }
    public Date getOrder_date() {
        return order_date;
    }
    public void setOrder_date(String order_date) throws ParseException{
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        this.order_date = df.parse(order_date);
    }
    public Integer getTotal_price() {
        return total_price;
    }
    public void setTotal_price(Integer total_price) {
        this.total_price = total_price;
    }
    public Integer getCost_price() {
        return cost_price;
    }
    public void setCost_price(Integer cost_price) {
        this.cost_price = cost_price;
    }

    public static Price createPrice(SqlRowSet rs) throws Exception{
        Price price = new Price();
        while(rs.next()){
            price.setCost_price((int) rs.getDouble("cost_price"));
            price.setCust_id(Integer.parseInt(rs.getString("cust_id")));
            price.setOrder_date(rs.getString("order_date"));
            price.setOrder_id(Integer.parseInt(rs.getString("order_id")));
            price.setTotal_price((int) rs.getDouble("total_price"));
        }
        return price;
    }

}
