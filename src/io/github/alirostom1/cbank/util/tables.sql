CREATE TABLE checkingsaccounts(
    code varchar(36) PRIMARY KEY NOT NULL,
    balance decimal(10,2),
    overdraft_limit decimal(10,2),
    check (balance + overdraft_limit >= 0)
);
CREATE TABLE savingsaccounts(
    code varchar(36) PRIMARY KEY NOT NULL,
    balance decimal(10,2),
    interest_rate decimal(10,3),
    check (interest_rate <= 1 and interest_rate >= 0.01)
);
