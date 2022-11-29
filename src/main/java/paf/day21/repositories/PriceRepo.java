package paf.day21.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import paf.day21.model.Price;

import static paf.day21.repositories.Query.*;

@Repository
public class PriceRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Price getPrice(String order_id) throws Exception{
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_PRICES, order_id);
        Price price = Price.createPrice(rs);
        return price;
    }

}
