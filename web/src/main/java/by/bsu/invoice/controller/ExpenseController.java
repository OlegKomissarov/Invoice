package by.bsu.invoice.controller;

import by.bsu.invoice.entity.Expense;
import by.bsu.invoice.service.ExpenseService;
import by.bsu.invoice.util.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private ExpenseService expenseService;
    private SessionUser sessionUser;

    @Autowired
    public ExpenseController(ExpenseService expenseService, SessionUser sessionUser) {
        this.expenseService = expenseService;
        this.sessionUser = sessionUser;
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<List<Expense>> getByInvoiceId(@PathVariable("invoiceId") Integer invoiceId) {
        return new ResponseEntity<>(expenseService.getByInvoiceId(sessionUser.getId()), HttpStatus.OK);
    }
}
