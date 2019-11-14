package by.bsu.invoice.controller;

import by.bsu.invoice.entity.Invoice;
import by.bsu.invoice.service.InvoiceService;
import by.bsu.invoice.util.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
        return new ResponseEntity<>(invoiceService.getByUserId(sessionUser.getId()), HttpStatus.OK);
    }

    @GetMapping("/{id}")    // 8
    public ResponseEntity<Invoice> getById(@PathVariable("id") Integer id) {
        Invoice invoice = invoiceService.getById(id);
        if (sessionUser.getId() == invoice.getUser().getId()) {
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping()
    public ResponseEntity<Invoice> create(
            @RequestParam("createdDate") Date createdDate,
            @RequestParam("title") String title,
            @RequestParam("invoiceNumber") Integer invoiceNumber,
            @RequestParam("userId") Integer userId,
            @RequestParam("isArchival") Integer isArchival
    ) {
        invoiceService.create(createdDate, title, invoiceNumber, sessionUser.getId(), isArchival);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")    // 10
    public ResponseEntity<Invoice> delete(@PathVariable("id") Integer id) {
        Invoice invoice = invoiceService.getById(id);
        if (sessionUser.getId() == invoice.getUser().getId()) {
            invoiceService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    // TODO: make something with title param to add searching
//    @GetMapping("?{title}")
//    public ResponseEntity<Invoice> getByTitle(@PathVariable("title") Integer id) {
//        Integer[] invoiceUserIds = invoiceService.getSellerAndCustomerId(id);
//        if (sessionUser.getId() == invoiceUserIds[0] || sessionUser.getId() == invoiceUserIds[1]) {
//            return new ResponseEntity<>(invoiceService.getById(id), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//    }
}
