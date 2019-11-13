package by.bsu.invoice.controller;

import by.bsu.invoice.entity.Invoice;
import by.bsu.invoice.service.InvoiceService;
import by.bsu.invoice.util.SessionUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;
import java.sql.Date;

import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    private InvoiceService invoiceService;
    private SessionUser sessionUser;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, SessionUser sessionUser) {
        this.invoiceService = invoiceService;
        this.sessionUser = sessionUser;
    }

    @GetMapping()
    public ResponseEntity<List<Invoice>> getAll() {
//        return new ResponseEntity<>(invoiceService.getByUserId(sessionUser.getId()), HttpStatus.OK);
        return new ResponseEntity<>(invoiceService.getByUserId(7), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(@PathVariable("id") long id) {
        long[] invoiceUserIds = invoiceService.getSellerAndCustomerId(id);
        if (sessionUser.getId() == invoiceUserIds[0] || sessionUser.getId() == invoiceUserIds[1]) {
            return new ResponseEntity<>(invoiceService.getById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping()
    public ResponseEntity<Invoice> create(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("paymentDeadline") Date paymentDeadline,
            @RequestParam("customerEmail") String customerEmail,
            @RequestParam("sellerId") long sellerId,
            @RequestParam("services") String services,
            @RequestParam("serviceAmount") String serviceAmount
    ) {
        invoiceService.create(
                title,
                description,
                paymentDeadline,
                sellerId,
                customerEmail,
                sessionUser.getId(),
                services.split(","),
                serviceAmount.split(",")
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Invoice> delete(@PathVariable("id") long id) {
        long[] identifications = invoiceService.getSellerAndCustomerId(id);
        boolean isSeller = identifications[0] == sessionUser.getId();
        if (isSeller) {
            invoiceService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping ("/approve/{id}")
    public ResponseEntity<Invoice> approve(@PathVariable("id") long id) {
        long[] identifications = invoiceService.getSellerAndCustomerId(id);
        boolean isSeller = identifications[0] == sessionUser.getId();
        if (isSeller) {
            invoiceService.approve(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    // TODO: make something with title param to add searching
    // TODO: remove?
//    @GetMapping("?{title}")
//    public ResponseEntity<Invoice> getByTitle(@PathVariable("title") long id) {
//        long[] invoiceUserIds = invoiceService.getSellerAndCustomerId(id);
//        if (sessionUser.getId() == invoiceUserIds[0] || sessionUser.getId() == invoiceUserIds[1]) {
//            return new ResponseEntity<>(invoiceService.getById(id), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//    }
}
