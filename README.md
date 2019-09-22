# SimBank

### Introduction
This is a CLI-driven Java app that simulates a very simplistic banking application.
The user has the ability to do the following things:
- Create an account.
- Lodge into an account.
- Withdraw from an account.
- Transfer money between two accounts.
- View a statement for an account.

### Running the app
- Checkout from GitHub.
- From the root project directory (ie the directory in which pom.xml resides), run **mvn clean install**. This will create a 'target' directory with a jar-with-dependencies file.
- From the root project directory, run start.sh or start.bat. (Not tested on Windows). If this fails to start the app, run this command: **java -jar target/simbank-0.0.1-SNAPSHOT-jar-with-dependencies.jar**.
- You'll be presented with a simple command-line UI.

### Using the app
Upon startup, you'll be presented with a command-line menu:

```
Welcome to SimBank.
Please enter an option.
[c]reate account
[l]odge money
[w]ithdraw money
[t]ransfer between accounts
[v]iew account statement
[e]xit
```
Simply enter an option, for example 'c' or 'l' and hit return. This will navigate you through to the next step.

### Design Notes
The app code is logically seperated into a few different modules. I would hesitate to call it an MVC design, but you could think about it as being broadly MVC-like. 

- Model: Account, Transaction, TransactionRecord.
- View: App, Cli.
- Controller: BankLogic.
- Backend/Service: TransactionManager.

At startup, a BankLogic object is instantiated. This object acts as the 'glue' between the UI and the backend. The BankLogic receives raw input data from the Cli UI, and transforms the input into Transaction objects which are sent to the TransactionManager for processing. All of the available banking operations are done by BankLogic sending Transactions to the TransactionManager. 

For every Transaction that is processed, a TransactionRecord object is created for each account which the Transaction affects. In other words, it somewhat satisfies the general accounting practice of double-entry bookkeeping, in that if a money transfer occurs between two accounts a TransactionRecord will be created for each account. One TransactionRecord for the debit operation and one for the corresponding credit operation.

In lieu of a data-access layer, the BankLogic object is also used to store the in-memory data. This is done via a very simple structure. The BankLogic object has a List of Account objects, each of which has a List of TransactionRecord objects. So in order to build a bank statement for a particular account, one would need to simply find the correct account from BankLogic.AccountList, and then get the List of TransactionRecords for that account. If we eventually add a real data layer to the app, this class will be refactored to use DAOs.

### Common Java practices I've avoided

- Checked Exceptions: I find that adding custom checked exceptions to a small project like this increases the testing load dramatically. Error-handling is handled as much as possible by the UI. If an error occurs, I prefer it to crash hard. An example of the error handling is when you try withdraw more money than is in the account. The app will warn you that you're about to go into negative balance and ask you if you want to continue. This is done in code by a simple if-else block as opposed to an exception.
- Logging. I've avoided adding any logging statements to the code, as this does impact readabilty and add a lot of coding time to an already time-constrained project.
