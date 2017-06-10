package jp.trade.ins.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import jp.trade.ins.model.Instruction;
import jp.trade.ins.model.TradeAction;

public class InstructionsGenerator {
    public static Set<Instruction> getSampleInstructions() {
        return new HashSet<>(Arrays.asList(

            new Instruction(
                "E1",
                TradeAction.BUY,
                Currency.getInstance("SGD"),
                LocalDate.of(2017, 6, 9),
                LocalDate.of(2017, 6, 19),
                BigDecimal.valueOf(0.50),
                200,
                BigDecimal.valueOf(100.25)),

            new Instruction(
                "E2",
                TradeAction.BUY,
                Currency.getInstance("AED"),
                LocalDate.of(2017, 6, 9),
                LocalDate.of(2017, 6, 18),
                BigDecimal.valueOf(0.22),
                450,
                BigDecimal.valueOf(150.5)),

            new Instruction(
                "E3",
                TradeAction.SELL,
                Currency.getInstance("SAR"),
                LocalDate.of(2017, 6, 10),
                LocalDate.of(2017, 6, 18),
                BigDecimal.valueOf(0.27),
                150,
                BigDecimal.valueOf(400.8)),

            new Instruction(
                "E4",
                TradeAction.SELL,
                Currency.getInstance("EUR"),
                LocalDate.of(2017, 6, 9),
                LocalDate.of(2017, 6, 20),
                BigDecimal.valueOf(0.34),
                50,
                BigDecimal.valueOf(500.6)),

            new Instruction(
                "E5",
                TradeAction.BUY,
                Currency.getInstance("EUR"),
                LocalDate.of(2017, 6, 9),
                LocalDate.of(2017, 6, 20),
                BigDecimal.valueOf(0.34),
                20,
                BigDecimal.valueOf(40.6)),

            new Instruction(
                "E6",
                TradeAction.BUY,
                Currency.getInstance("EUR"),
                LocalDate.of(2017, 6, 9),
                LocalDate.of(2017, 6, 20),
                BigDecimal.valueOf(0.34),
                20,
                BigDecimal.valueOf(40.6)),               
             

            new Instruction(
                "E7",
                TradeAction.SELL,
                Currency.getInstance("EUR"),
                LocalDate.of(2017, 6, 9),
                LocalDate.of(2017, 6, 20),
                BigDecimal.valueOf(0.34),
                1000,
                BigDecimal.valueOf(160.6)),

            new Instruction(
                "E8",
                TradeAction.SELL,
                Currency.getInstance("EUR"),
                LocalDate.of(2017, 6, 9),
                LocalDate.of(2017, 6, 20),
                BigDecimal.valueOf(0.34),
                120,
                BigDecimal.valueOf(500.6)),
                
             // default saturday settlement date and 
             //change to weekday correct settlement date on monday(19th)
                new Instruction(
                        "E9",
                        TradeAction.BUY,
                        Currency.getInstance("GBP"),
                        LocalDate.of(2017, 6, 9),
                        LocalDate.of(2017, 6, 17),
                        BigDecimal.valueOf(0.34),
                        20,
                        BigDecimal.valueOf(40.6)), 
                
                // default friday(23rd) settlement date and 
                //change to weekday correct settlement date on monday(25th)   
                 new Instruction(
                        "E10",
                        TradeAction.SELL,
                        Currency.getInstance("SAR"),
                        LocalDate.of(2017, 6, 10),
                        LocalDate.of(2017, 6, 23),
                        BigDecimal.valueOf(0.27),
                        150,
                        BigDecimal.valueOf(400.8))
          
           
        ));
    }
}
