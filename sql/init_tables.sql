INSERT INTO invoice.user (email, password, current_balance, name, phone)
    VALUES ('oleg.komissarov@gmail.com', '827e66a38bd6c1489cf7b3738e3cbe34',
        '100', 'Oleg Komissarov', '+3752956582104');
    /*password = Admin1*/

INSERT INTO invoice.user (email, password, current_balance, name, phone)
    VALUES ('user1@gmail.com', '827e66a38bd6c1489cf7b3738e3cbe34',
        '100', 'Ivan Orlov', '+3752956245121');
    /*password = Admin1*/



INSERT INTO invoice.service (title, description, price, seller_id)
    VALUES ('Socks', 'Very good socks.', '3', '1');

INSERT INTO invoice.service (title, description, price, seller_id)
    VALUES ('Web application', 'Web development.', '1000', '0');



INSERT INTO invoice.invoice (title, payment_deadline, seller_id, customer_id, is_approved)
    VALUES ('Web application for Ivan', '2020-01-01', '0', '1', '1');

INSERT INTO invoice.invoice (title, payment_deadline, seller_id, customer_id, is_approved)
    VALUES ('Socks for Oleg', '2020-02-01', '1', '0', '0');



INSERT INTO invoice.expense (invoice_id, service_id, amount)
    VALUES ('0', '1', '100');

INSERT INTO invoice.expense (invoice_id, service_id, amount)
    VALUES ('1', '0', '2');



INSERT INTO invoice.payment (payment, invoice_id)
    VALUES ('20', '0');

INSERT INTO invoice.payment (payment, invoice_id)
    VALUES ('40', '0');

INSERT INTO invoice.payment (payment, invoice_id)
    VALUES ('40', '0');

INSERT INTO invoice.payment (payment, invoice_id)
    VALUES ('2', '1');
