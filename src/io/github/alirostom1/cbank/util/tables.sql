CREATE TABLE accounts(
    code varchar(36) PRIMARY KEY NOT NULL,
    balance decimal(10,2) DEFAULT 0.00,
    account_type ENUM("checkings","Savings") DEFAULT "Savings" 
);

CREATE TABLE checkingsaccounts(
    code varchar(36) PRIMARY KEY,
    overdraft_limit decimal(10,2) DEFAULT 500.00,
    FOREIGN KEY (code) REFERENCES accounts(code)
);
CREATE TABLE savingsaccounts(
    code varchar(36) PRIMARY KEY,
    interest_rate decimal(10,3) default 0.05,
    check (interest_rate <= 1 and interest_rate >= 0.01),
    FOREIGN KEY (code) REFERENCES accounts(code)
);
