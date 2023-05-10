package com.internet.banking.web.mapper;

import com.internet.banking.service.vo.TransactionRequestVO;
import com.internet.banking.service.vo.TransactionVO;
import com.internet.banking.web.dto.request.TransactionRequestDTO;
import com.internet.banking.web.dto.response.TransactionResponseDTO;

public class TransactionDTOMapper {

    public static TransactionRequestVO toVO(final TransactionRequestDTO transactionRequestDTO) {
        return TransactionRequestVO.builder()
                .amount(transactionRequestDTO.getAmount())
                .name(transactionRequestDTO.getName())
                .accountNumber(transactionRequestDTO.getAccountNumber())
                .transactionType(transactionRequestDTO.getTransactionType())
                .build();
    }

    public static TransactionResponseDTO toDTO(final TransactionVO transactionVO) {
        return TransactionResponseDTO.builder()
                .amount(transactionVO.getAmount())
                .name(transactionVO.getName())
                .accountNumber(transactionVO.getAccountNumber())
                .transactionDate(transactionVO.getTransactionDate().toString())
                .transactionType(transactionVO.getTransactionType().name())
                .build();
    }
}
