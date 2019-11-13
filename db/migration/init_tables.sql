INSERT INTO invoice.user (email, password, current_balance, name, phone)
    VALUES ('oleg.komissarov@gmail.com', '827e66a38bd6c1489cf7b3738e3cbe34',
        '100', 'Oleg Komissarov', '+3752956582104');

INSERT INTO invoice.user (email, password, current_balance, name, phone)
    VALUES ('user1@gmail.com', '827e66a38bd6c1489cf7b3738e3cbe34',
        '100', 'Ivan Orlov', '+3752956245121');



INSERT INTO invoice.product (title, description, price, seller_id)
    VALUES ('Socks', 'Very good socks.', '3', '7');

INSERT INTO invoice.product (title, description, price, seller_id)
    VALUES ('Web application', 'Web development.', '1000', '8');



INSERT INTO invoice.invoice (title, payment_deadline, seller_id, customer_id, is_approved)
    VALUES ('Web application for Ivan', '2020-01-01', '7', '8', '1');

INSERT INTO invoice.invoice (title, payment_deadline, seller_id, customer_id, is_approved)
    VALUES ('Socks for Oleg', '2020-02-01', '8', '7', '0');



INSERT INTO invoice.expense (invoice_id, product_id, amount)
    VALUES ('15', '7', '100');

INSERT INTO invoice.expense (invoice_id, product_id, amount)
    VALUES ('16', '10', '2');



INSERT INTO invoice.payment (payment, invoice_id)
    VALUES ('20', '15');

INSERT INTO invoice.payment (payment, invoice_id)
    VALUES ('40', '15');

INSERT INTO invoice.payment (payment, invoice_id)
    VALUES ('40', '15');

INSERT INTO invoice.payment (payment, invoice_id)
    VALUES ('2', '16');
