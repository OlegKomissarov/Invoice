package by.bsu.invoice.controller;

import by.bsu.invoice.entity.Expense;
import by.bsu.invoice.entity.Invoice;
import by.bsu.invoice.service.ExpenseService;
import by.bsu.invoice.util.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<Expense> create(@RequestBody Invoice invoice) {
        expenseService.create(invoice);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> update(@PathVariable Integer id, @RequestBody Expense expense) {
        expenseService.update(
                id,
                expense.getDescription(),
                expense.getCount(),
                expense.getPrice()
        );
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Expense> delete(@PathVariable Integer id) {
        expenseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
