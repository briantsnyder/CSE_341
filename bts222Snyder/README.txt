Brian Snyder - bts222
ReadMe File

Welcome! To run my project, it will first ask you to login with an Orcale ID
After this there will be a menu with options from 1-8 that you can choose from
(as explained in the program currently only the Account / Deposit, Charging a card,
and the paying a loan / credit card terminals are operational).

Once you select a terminal, it will ask you to enter in a customer ID, it will display
all of the available customers in the database. Type in the customer's ID and hit enter
to login in as them.

From there depending on the interface you chose, a similar process will continue where
you will be prompted to select options such as chooosing an account by entering in an
Account ID from above, and you will continue to be prompted until you have reached the
end of an interface.

For example, for the Deposit / Withdraw screen, you will have to enter in first a
customer SSN from the options displayed, an account ID from the options displayed based
off the customer you chose, if you would like to withdraw or despoit, and then a branch.
After this point, the program will attempt to process this transaction, and if it does
you will get a message along with the updated account info, at which poitn you have reached the
end of the interface and will be returned to the main menu.

If the transaction did not get processed, it could be due to an error like the user
entering in a negative number, in which case you will be reprompted to retry. Note: the
system will take you back to the main menu if you attempt to make a deposit at a bank
that doesn't have a tellar. The logic there being perhaps the customer wants to completely
redo what they were doing if they aren't allowed to make a deposit at that specific
location, and thus returns them to the main menu.

Notably, the program will almost always offer you the option to quit by entering either
'q' or 'Q' in case you made a mistake or would like to go back.

Some Assumptions Made:
 - The Bank has a select list of items that the user can choose from to buy when
charging a card. These items have a predetermined price
 - Each purchase of an item was done with a single card
 - Nickel Bank does not offer 'Cash Back' when completing transactions
 - Nickel Bank is not checking for the use of any cards that have expired
 - Credit Cards and Loans are 0 interest, so the rates for these are simply used
to calculate the minimum payment owed each month
 - When submitting a payment for a loan or credit card, payments below the minimum
required amount will not be accepted
 - All customers are required to have an account to be considered a customer
 - Each debit card is linked with a checking account
 - Nickel Bank allows customers to go up to $100 below the minimum balance, after
which any further withdraws are declined
 - Upon each withdraw that is taken out that lowers the account balance below the
minimum amount (for checking accounts) a $25 fee is applied to the account
 - No deposits at ATM sites only (aka no Tellers)
 - Transactions will not be completed concurrently, and rather Nickel Bank processes
transactions one after another

Going Forward:
If I had more time, I would of liked to implement the following features
 - Complete all 7 terminals
 - Implement a date checker for payments so that the user can make multiple payments
over a month that will sum to the minium payment amount (and if the user does not
make enough payments to excede the minimum amount, a fee will be imposed)
 - A procedure when each unique update is done:
	A procedure when a charge entry is added
	A procedure when a loanPayment / creditPayment is added
	A prodecure when a withdraw / desposit is added
These procedure would eliminate the methods that I have all beginning with insert,
and would put more work on the database.

While testing there are some unique accounts among the 5 customers with say negative
accounts, amounts below the minimum balance, or very close to the credit limit. One
such account is the account ID '80848272', however the numbers have changed slighly due
to testing, so this is relative to when the relational data is taken.

In terms of generating code, I simply came up with names for people, and then used
Java.util.random for a lot of this or my own imagination. I included the code I used to
generate my data along with a masterFile which contains my entire schema as well as all of
the updates and inserts required to create this database

