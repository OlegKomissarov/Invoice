CREATE TABLE IF NOT EXISTS invoice.user
(
  id                SERIAL PRIMARY KEY,
  email             character   varying(224)            NOT NULL UNIQUE,
  password          character   varying(32)             NOT NULL,
  current_balance   numeric                             NOT NULL,
  name              character   varying(100)            NOT NULL,
  phone             character   varying(17)             NOT NULL
);
ALTER SEQUENCE invoice.user_id_seq RESTART WITH 1;

CREATE TABLE IF NOT EXISTS invoice.service
(
    id              SERIAL PRIMARY KEY,
    title           character   varying(100)            NOT NULL,
    description     character   varying(255)            NULL DEFAULT NULL,
    price           numeric                             NOT NULL,
    seller_id       int references invoice.user (id)    NOT NULL,
    is_archival     smallint                            NOT NULL DEFAULT 0
);
ALTER SEQUENCE invoice.service_id_seq RESTART WITH 1;

CREATE TABLE IF NOT EXISTS invoice.invoice
(
    id              SERIAL PRIMARY KEY,
    title           character   varying(100)            NOT NULL,
    description     character   varying(255)            NULL DEFAULT NULL,
    created_date    date                                NOT NULL DEFAULT NOW(),
    payment_deadline    date                            NOT NULL,
    seller_id       int references invoice.user (id)    NOT NULL,
    customer_id     int references invoice.user (id)    NOT NULL,
    is_archival_for_customer smallint                   NOT NULL DEFAULT 0,
    is_archival_for_seller   smallint                   NOT NULL DEFAULT 0,
    is_approved     smallint                            NOT NULL DEFAULT 0
);
ALTER SEQUENCE invoice.invoice_id_seq RESTART WITH 1;

CREATE TABLE IF NOT EXISTS invoice.payment
(
    id              SERIAL PRIMARY KEY,
    payment         numeric                             NOT NULL,
    date            date                                NOT NULL DEFAULT NOW(),
    invoice_id      int references invoice.invoice (id) NOT NULL
);
ALTER SEQUENCE invoice.payment_id_seq RESTART WITH 1;

CREATE TABLE IF NOT EXISTS invoice.expense
(
    id              SERIAL PRIMARY KEY,
    invoice_id      int references invoice.invoice (id) NOT NULL,
    service_id      int references invoice.service (id) NOT NULL,
    amount          int                                 NOT NULL
);
ALTER SEQUENCE invoice.expense_id_seq RESTART WITH 1;
