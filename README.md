# TradeInstructionDailyReport

The problem
------------

Sample data represents the instructions sent by various clients to JP Morgan to execute in the international market.

Entity	Buy/Sell	AgreedFx	Currency	InstructionDate	SettlementDate	Units	Price per unit
							
foo	B	0.50	SGP	01 Jan 2016	02 Jan 2016	200	100.25
							
bar	S	0.22	AED	05 Jan 2016	07 Jan 2016	450	150.5
							

•	A work week starts Monday and ends Friday, unless the currency of the trade is AED or SAR, where the work week starts Sunday and ends Thursday. No other holidays to be taken into account.

•	A trade can only be settled on a working day.

•	If an instructed settlement date falls on a weekend, then the settlement date should be changed to the next working day.

•	USD amount of a trade = Price per unit * Units * Agreed Fx

## Requirements
---------------

Create a report that shows

•	Amount in USD settled incoming everyday

•	Amount in USD settled outgoing everyday

•	Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest amount for a buy instruction, then foo is rank 1 for outgoing


Glossary of terms:
------------------

•	Instruction: An instruction to buy or sell

•	Entity: A financial entity whose shares are to be bought or sold

•	Instruction Date: Date on which the instruction was sent to JP Morgan by various clients

•	Settlement Date: The date on which the client wished for the instruction to be settled with respect to Instruction Date

•	Buy/Sell flag:

o	B – Buy – outgoing

o	S – Sell – incoming

•	Agreed Fx is the foreign exchange rate with respect to USD that was agreed

•	Units: Number of shares to be bought or sold

Solutions :
------------
A simple daily trade reporting process for incoming instructions. Its input is a set of instructions and its output is a (daily) report printed in console.

1. Download the TradeInstructionDailyReport project from Git
2. Import the project as maven existing project
3. Do maven update and  run the TradeInstructionDailyReportMain  class as Java application.
4. Displayed results in console.

Implementation  done using Java8(lambda ,streamAPI functionalities, functional interface, Set, Hash map, foreach and strategy design pattern,StringBuilder),  Junit and Maven.

Classes in Project
------------------

TradeInstructionDailyReportMain  - Class is used for run the main class and call the appropriate method for process the trade instruction and display the output in console.

InstructionsGenerator--- class that generates a set of sample data  instructions.

ReportGenerator ----  class is responsible of generating reports, using a StringBuilder, for the aforementioned statistics.

ReportGenerator -- class is responsible of generating reports, using a StringBuilder, for the aftre 
mentioned statistics.

InstructionSettlementStatsCalculator -- - class is responsible of generating statistics based on a set of given instructions.

Rank -- class represents the ranking, entity and date of a record.

 Working days classes:

	Depending on the currency of each instruction the settlement date may be change. More specifically, Arabhu has different working days than the rest of the world. Therefore, a work week starts Monday and ends Friday, unless the currency of the trade is AED or SAR, where the work week starts Sunday and ends Thursday. No other holidays to be taken into account. 
This is represented in this code by the Strategy pattern described in the Workingdays abstract class.
There are 2 different subclasses of this class:
-  AED and SAR - A strategy for Arabu in which work week starts Sunday and ends Thursday.
-  Default Working Days -  A strategy for the rest of the world for which a work week starts Monday and ends Friday.

Testing Classes:
	Test the different scenario like same data instruction, AED-SAR  working days, default working days and correct settlement date set process and Rank.

Algorithm:
------------
1. Create sample data instructions
2. Set the working days for Arabhu(AED-SAR) and other country currency.
3. Based on instruction, set the correct settlement date and rank.
4. Make calculation of trade amount, rank identify, grouping based on settlement date and currency
5. Generate daily trade instruction report.
6. Create test cases and check the different sc scenario.
