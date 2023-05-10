package com.internet.banking.util;

import com.internet.banking.exceptions.CustomerBadRequestException;
import com.internet.banking.service.vo.CustomerVO;
import com.internet.banking.service.vo.TransactionRequestVO;
import com.internet.banking.util.enums.TaxType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class MoneyUtil {

    public static BigDecimal subAmount(final BigDecimal amount, final BigDecimal subAmount) {
        return amount.subtract(subAmount);
    }

    public static BigDecimal calcAmount(final CustomerVO customer, final TransactionRequestVO transactionRequestVO) throws CustomerBadRequestException {
        final BigDecimal taxValue = customer.isExclusivePlan() ? BigDecimal.ZERO : getTaxValue(transactionRequestVO.getAmount());
        final BigDecimal amountWithTax = transactionRequestVO.getAmount().add(taxValue);

        if (customer.hasEnoughAmount(amountWithTax)) {
            throw new CustomerBadRequestException("Cliente não tem saldo suficiente para operação, saldo=R$ ".concat(customer.getAmount().toString())
                    .concat(", valor de retirada= R$").concat(amountWithTax.setScale(2).toString()));
        }

        return amountWithTax;
    }
    private static BigDecimal getTaxValue(final BigDecimal amount) {
        final TaxType taxType = TaxType.defineTax(amount);

        if (taxType.equals(TaxType.ZERO))
            return BigDecimal.ZERO;

        return taxType.getTaxPercentage().divide(BigDecimal.valueOf(100)).multiply(amount);
    }
}
