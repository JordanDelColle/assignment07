package dmit2015.jdelcolle1.assignment07.restclient;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Bill {
    private String payeeName;
    private String dueDate;
    private BigDecimal paymentDue;
    private BigDecimal paymentBalance;
}
