# SimBank

Design notes

Common Java practices I've avoided:
Checked Exceptions: I find that adding custom checked exceptions to a small project like this increases the testing load dramatically. Error-handling is handled as much as possible by the UI. If an error occurs, I prefer it to crash hard.
Logging. I've avoided adding any logging statements to the code, as this does impact readabilty. 
