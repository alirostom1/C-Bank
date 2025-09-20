CREATE TABLE accounts(
    code varchar(36) PRIMARY KEY NOT NULL,
    balance decimal(10,2) DEFAULT 0.00,
    account_type ENUM("checkings","Savings") DEFAULT "Savings" 
);

CREATE TABLE checkingsaccounts(
    code varchar(36) PRIMARY KEY,
    overdraft_limit decimal(10,2) DEFAULT 500.00,
    FOREIGN KEY (code) REFERENCES accounts(code) ON DELETE CASCADE
);
CREATE TABLE savingsaccounts(
    code varchar(36) PRIMARY KEY,
    interest_rate decimal(10,3) default 0.05,
    check (interest_rate <= 1 and interest_rate >= 0.01),
    FOREIGN KEY (code) REFERENCES accounts(code) ON DELETE CASCADE
);
CREATE TABLE operations(
    id varchar(36) PRIMARY KEY NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    amount decimal(10,2) NOT NULL,
    operation_type ENUM("withdrawal","deposit") NOT NULL,
    code varchar(36) NOT NULL,
    FOREIGN KEY (code) REFERENCES accounts(code) ON DELETE CASCADE
);

CREATE TABLE withdrawals(
    id varchar(36) PRIMARY KEY,
    destination ENUM("ATM","CHECK","TRANSFER_OUT") NOT NULL,
    FOREIGN KEY (id) REFERENCES operations(id) ON DELETE CASCADE
);

CREATE TABLE deposits(
    id varchar(36) PRIMARY KEY,
    source ENUM("SALARY","CASH","TRANSFER_IN"),
    FOREIGN KEY (id) REFERENCES operations(id) ON DELETE CASCADE 
);
