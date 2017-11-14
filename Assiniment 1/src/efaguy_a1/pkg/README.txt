Description
The purpose of this program is to create a simulation of an
investment portfolio in which you can buy and sell share of stocks
and units of mutual funds. Both investment types had a symbol to
represent it, a name to describe it, a quantity of units or shares
owned, a price per unit/share, and a book value(the amount of 
money spent on current stock/fund). The program allows user to 
buy, sell, updates the prices, calculate the theoretical gain
if all the stocks were sold, and search the investment based
on parameters given by the users.

Assumption and Limitations
The assumption that were made were that only one investment
would have a certain symbol, that each stock purchases and sale
would have a constant fee of 9.99 and each mutual fund sale 
would have a constant fee of $45, that the user would enter 
the correct form of input(ie. an int when a number is being entered),
that the purchase and sale of stocks and funds did not have any 
affect on the prices, that no negative numbers would be 
entered, and that there will be no punctuation in the symbol
or names if the investments.

Test Plan

For main:
If a choice other than buy, sell, update, getGain, search or
quit the program ask for the user to enter a new choice.

For Portfolio:
When buying if a symbol is entered for the stock that already
exist the purchase is added onto the original stock.
When selling if the user try to sell stock/funds but does
not own any the program will tell them so and ask them to
select another option.
When selling if the user tries to sell more shares/units then
her/she owns it will them them and ask them to enter a 
smaller number.
If the user sell all of one stock/fund it will be deleted from
the list.
When searching if the user leave any field blank the program
will assume that any data in that field is valid.
When searching if the user enters only one price the program will
only return stocks/funds with that price.
If they enter a dash then a price it will assume that
all price below the price are valid.
If they enter a price then a dash it will assume all
prices above that price are valid.
If they enter two prices it will only return stock/fund with
prices in between the two numbers. 
If they enter more than one keyword it will only return a
investment with all the keywords in the name(regardless of order
or case).

Improvements:
If I had more time I could allow the program to take in invalid
(ie. a string when an int is expected) input, as well as
account for negative inputs. 




