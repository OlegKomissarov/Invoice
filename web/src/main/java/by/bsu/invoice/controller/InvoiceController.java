package by.bsu.invoice.controller;

import by.bsu.invoice.entity.Invoice;
import by.bsu.invoice.service.InvoiceService;
import by.bsu.invoice.util.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(@PathVariable("id") Integer id) {
        Invoice invoice = invoiceService.getById(id);
        if (sessionUser.getId() == invoice.getUser().getId()) {
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping()
    public ResponseEntity<Invoice> create(@RequestBody Invoice invoice) {
        invoiceService.create(
                invoice.getDate(),
                invoice.getTitle(),
                invoice.getNumber(),
                sessionUser.getId(),
                invoice.getIsArchival()
        );
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> update(@PathVariable Integer id, @RequestBody Invoice invoice) {
        invoiceService.update(
                id,
                invoice.getDate(),
                invoice.getTitle(),
                invoice.getNumber()
        );
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Invoice> delete(@PathVariable("id") Integer id) {
        Invoice invoice = invoiceService.getById(id);
        if (sessionUser.getId() == invoice.getUser().getId()) {
            invoiceService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
