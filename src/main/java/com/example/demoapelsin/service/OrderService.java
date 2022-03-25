package com.example.demoapelsin.service;

import com.example.demoapelsin.entity.Customer;
import com.example.demoapelsin.entity.Invoice;
import com.example.demoapelsin.entity.Orders;
import com.example.demoapelsin.payload.ApiResponse;
import com.example.demoapelsin.payload.InvoiceDto;
import com.example.demoapelsin.payload.OrderDto;
import com.example.demoapelsin.repository.CustomerRepository;
import com.example.demoapelsin.repository.InvoiceRepository;
import com.example.demoapelsin.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    final OrderRepository orderRepository;
    final CustomerRepository customerRepository;
    final InvoiceRepository invoiceRepository;


    public ApiResponse getAll() {
        List<Orders> orderList = orderRepository.findAllByActiveTrue();
        return orderList != null ? new ApiResponse(true, "Success", orderList)
                : new ApiResponse(false, "Failed");
    }


    public ApiResponse getById(Integer id) {
        Optional<Orders> orderOptional = orderRepository.findByActiveTrueAndId(id);
        return orderOptional.map(orders -> new ApiResponse(true, "Success", orders))
                .orElseGet(() -> new ApiResponse(false, "Not Found"));
    }


    public ApiResponse add(InvoiceDto invoiceDto) {
        Optional<Customer> customerOptional = customerRepository.findById(invoiceDto.getCustomerId());
        if (customerOptional.isEmpty()) return new ApiResponse(false, "Not Found");
        Customer customer = customerOptional.get();

        Orders orders = new Orders();
        orders.setDate(invoiceDto.getDate());
        orders.setCustomer(customer);
        Orders save = orderRepository.save(orders);


        Invoice invoice = new Invoice();
        invoice.setIssued(invoiceDto.getIssued());
        invoice.setDue(invoiceDto.getDue());
        invoice.setAmount(invoiceDto.getAmount());
        invoice.setOrder(save);
        invoiceRepository.save(invoice);

        return new ApiResponse(true, "Saved");
    }


    public ApiResponse update(Integer id, OrderDto updateOrder, InvoiceDto invoiceDto) {
        Optional<Orders> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) {
            return new ApiResponse(false, "Not Found");
        }
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if (invoiceOptional.isEmpty()) {
            return new ApiResponse(false, "Not found");
        }

        Invoice invoice = invoiceOptional.get();
        invoice.setIssued(invoiceDto.getIssued());
        invoice.setDue(invoiceDto.getDue());
        invoice.setAmount(invoiceDto.getAmount());

        Orders invoiceOrder = invoice.getOrder();
        invoiceOrder.setDate(updateOrder.getDate());

        invoice.setOrder(invoiceOrder);
        invoiceRepository.save(invoice);
        return new ApiResponse(true, "updated");
    }


    public ApiResponse delete(Integer id) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        if (!invoiceOptional.isPresent()) {
            return new ApiResponse(false, "Not Found");
        }
        Invoice invoice1 = invoiceOptional.get();
        Orders orders = invoice1.getOrder();
        invoice1.setActive(false);
        orders.setActive(false);
        orderRepository.save(orders);
        invoiceRepository.save(invoice1);
        return new ApiResponse(true, "deleted");
    }

    public ApiResponse expired() {
        Date date = new Date();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int nYear = gregorianCalendar.getWeekYear();
        int nMonth = date.getMonth();
        int nDay = gregorianCalendar.get(Calendar.DAY_OF_MONTH);

        int nG = nYear + nMonth + nDay;
        List<Invoice> invoicesDue = invoiceRepository.findAll();
        int dG = 0;
        List<Invoice> invoiceList = new ArrayList<>();

        for (int i = 0; i < invoicesDue.size(); i++) {
            int year = invoicesDue.get(i).getDue().getYear();
            int month = invoicesDue.get(i).getDue().getMonth();
            int day = invoicesDue.get(i).getDue().getDay();
            dG = year + month + day;
            if (nG > dG) {
                invoiceList.add(invoicesDue.get(i));
            }
        }
        if (invoiceList.isEmpty()){
            return new ApiResponse(false,"Not found");
        }
        return new ApiResponse(true,"Success",invoiceList);
    }



}
