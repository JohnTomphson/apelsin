package com.example.demoapelsin.service;

import com.example.demoapelsin.entity.Categorys;
import com.example.demoapelsin.entity.Invoice;
import com.example.demoapelsin.entity.Payment;
import com.example.demoapelsin.entity.Product;
import com.example.demoapelsin.payload.ApiResponse;
import com.example.demoapelsin.payload.PaymentDto;
import com.example.demoapelsin.repository.InvoiceRepository;
import com.example.demoapelsin.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    final PaymentRepository paymentRepository;
    final InvoiceRepository invoiceRepository;

    public ApiResponse getAll() {
        List<Payment> paymentList = paymentRepository.findAllByActiveTrue();
        return paymentList != null ? new ApiResponse(true, "success",paymentList)
                : new ApiResponse(false, "failed");
    }

    public ApiResponse getById(Integer id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        return paymentOptional.map(payment -> new ApiResponse(true, "Success", payment))
                .orElseGet(() -> new ApiResponse(false, "Not Found"));
    }

    public ApiResponse add(PaymentDto paymentDto) {
        try {
            Payment payment = new Payment();
            if (paymentDto.getId() != null) {
                payment = paymentRepository.getById(paymentDto.getId());
            }
            Optional<Invoice> invoiceOptional = invoiceRepository.findById(paymentDto.getInvoiceId());
            if (!invoiceOptional.isPresent()) {
                return new ApiResponse(false, "Invoice not Found!");
            }
            payment.setAmount(paymentDto.getAmount());
            payment.setInvoice(invoiceOptional.get());
            paymentRepository.save(payment);
            return new ApiResponse(true, paymentDto.getId() != null ? "Updated" : "Saved");
        } catch (Exception e) {
            return new ApiResponse(false, "There is the category with the same name");
        }
    }

    public ApiResponse update(Integer id, PaymentDto paymentDto) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isEmpty()) return new ApiResponse(false, "Not found Payment");

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isEmpty()) return new ApiResponse(false, "Not found Invoice");

        Payment payment = optionalPayment.get();
        Invoice invoice = optionalInvoice.get();

        payment.setAmount(paymentDto.getAmount());
        payment.setInvoice(invoice);

        paymentRepository.save(payment);
        return new ApiResponse(true, "Updated");
    }

    public ApiResponse delete(Integer id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isEmpty()) return new ApiResponse(false, "Not Found");
        Payment payment = optionalPayment.get();
        payment.setActive(false);
        paymentRepository.save(payment);
        return new ApiResponse(true, "Deleted");
    }
}
