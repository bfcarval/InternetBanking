package com.internet.banking.web.mapper;

import com.internet.banking.service.vo.CustomerVO;
import com.internet.banking.web.dto.request.CustomerRequestDTO;
import com.internet.banking.web.dto.response.CustomerResponseDTO;

import static com.internet.banking.util.DateUtil.toLocalDate;

public class CustomerDTOMapper {

    public static CustomerVO toVO(final CustomerRequestDTO customerRequestDTO) {
        return CustomerVO.builder()
                .name(customerRequestDTO.getName())
                .exclusivePlan(customerRequestDTO.isExclusivePlan())
                .amount(customerRequestDTO.getAmount())
                .accountNumber(customerRequestDTO.getAccountNumber())
                .bornDate(toLocalDate(customerRequestDTO.getBornDate()))
                .build();
    }

    public static CustomerResponseDTO toDTO(final CustomerVO customerVO) {
        return CustomerResponseDTO.builder()
                .id(customerVO.getId())
                .name(customerVO.getName())
                .exclusivePlan(customerVO.isExclusivePlan())
                .amount(customerVO.getAmount())
                .accountNumber(customerVO.getAccountNumber())
                .bornDate(customerVO.getBornDate().toString())
                .createdDate(customerVO.getCreatedDate().toString())
                .updatedDate(customerVO.getUpdatedDate().toString())
                .build();
    }
}
