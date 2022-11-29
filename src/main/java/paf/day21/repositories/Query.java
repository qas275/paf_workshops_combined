package paf.day21.repositories;

public class Query {
    public static final String SQL_SELECT_ALL_CUSTOMERS = "select first_name from customers";

    public static final String SQL_SELECT_BY_ID = "select id, concat(first_name , ' ' , last_name) as name from customers where id = ?";

    public static final String SQL_SELECT_ORDERS = 
    "select c.id Id, order_date, shipping_fee fee from customers c join orders o on c.id=o.customer_id where c.id = ?";

    public static final String SQL_SELECT_PRICES = 
    "select o.id as order_id, o.order_date, c.id as cust_id, od.quantity*od.unit_price*(1-od.discount) as total_price, p.quantity_per_unit*p.standard_cost as cost_price from orders o join customers c on o.customer_id = c.id join order_details od on o.id = od.order_id join products p on od.product_id=p.id where order_id = ?";

    public static final String SQL_ADD_CUSTOMER = "insert into customers (company, last_name, first_name, city, state_province, zip_postal_code) values (?,?,?,?,?,?);";

    public static final String SQL_UPDATE_CUSTOEMER = "update customers set company = ?, first_name = ?, last_name = ? WHERE id = ?;";
}
