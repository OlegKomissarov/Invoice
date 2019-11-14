CREATE TABLE IF NOT EXISTS invoice.user
(
  id                SERIAL PRIMARY KEY,
  email             character   varying(224)            NOT NULL UNIQUE,
  password          character   varying(224)            NOT NULL,
  username          character   varying(100)            NOT NULL,
  phone             character   varying(17)             NOT NULL
);
ALTER SEQUENCE invoice.user_id_seq RESTART WITH 1;

CREATE TABLE IF NOT EXISTS invoice.invoice
(
    id              SERIAL PRIMARY KEY,
    created_date    date                                NOT NULL DEFAULT NOW(),
    title           character   varying(100)            NOT NULL,
    invoice_number  int                                 NULL DEFAULT NULL,
    user_id         int references invoice.user (id)    NOT NULL,
    is_archival     smallint                            NOT NULL DEFAULT 0
);
ALTER SEQUENCE invoice.invoice_id_seq RESTART WITH 1;

CREATE TABLE IF NOT EXISTS invoice.expense
(
    id              SERIAL PRIMARY KEY,
    description     character   varying(100)            NOT NULL,
    amount          int                                 NOT NULL,
    price           int                                 NOT NULL,
    invoice_id      int references invoice.invoice (id) NOT NULL
);
ALTER SEQUENCE invoice.expense_id_seq RESTART WITH 1;

CREATE TABLE IF NOT EXISTS invoice.payment
(
    id              SERIAL PRIMARY KEY,
    created_date    date                                NOT NULL DEFAULT NOW(),
    payment         numeric                             NOT NULL,
    invoice_id      int references invoice.invoice (id) NOT NULL
);
ALTER SEQUENCE invoice.payment_id_seq RESTART WITH 1;
