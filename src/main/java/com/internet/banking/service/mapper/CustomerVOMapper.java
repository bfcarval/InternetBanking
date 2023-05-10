package com.internet.banking.service.mapper;

import com.internet.banking.database.entity.Customer;
import com.internet.banking.service.vo.CustomerVO;

public class CustomerVOMapper {

    public static CustomerVO toVO(final Customer customer) {
        return CustomerVO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .exclusivePlan(customer.isExclusivePlan())
                .amount(customer.getAmount())
                .accountNumber(customer.getAccountNumber())
                .bornDate(customer.getBornDate())
                .createdDate(customer.getCreatedDate())
                .updatedDate(customer.getUpdatedDate())
                .build();
    }

    public static Customer toEntity(final CustomerVO customerVO) {
        return Customer.builder()
                .id(customerVO.getId())
                .name(customerVO.getName())
                .exclusivePlan(customerVO.isExclusivePlan())
                .amount(customerVO.getAmount())
                .accountNumber(customerVO.getAccountNumber())
                .bornDate(customerVO.getBornDate())
                .createdDate(customerVO.getCreatedDate())
                .updatedDate(customerVO.getUpdatedDate())
                .build();
    }
}
