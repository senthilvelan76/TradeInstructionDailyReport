package jp.trade.ins.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import jp.trade.ins.business.SettlementDateProcess;
import jp.trade.ins.model.Instruction;
import jp.trade.ins.model.TradeAction;
import static org.junit.Assert.assertEquals;

public class SettlementDateProcessTest {
    @Test
    public void calculateSettlementDate_default_Friday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 6, 23); // Its a Friday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                Currency.getInstance("SGD"), // Its Default
                LocalDate.of(2017, 6, 9),
                initialSettlementDate,
                BigDecimal.valueOf(0.50),
                200,
                BigDecimal.valueOf(100.25));

        // calculate new settlement day
        SettlementDateProcess.calculateSettlementDate(fakeInstruction);

        // should be the same
        assertEquals(initialSettlementDate, fakeInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_default_Sunday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 6, 25); // Its a Sunday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                Currency.getInstance("USD"), // Its Default
                LocalDate.of(2017, 6, 10),
                initialSettlementDate,
                BigDecimal.valueOf(1),
                200,
                BigDecimal.valueOf(100.25));

        // calculate new settlement day
        SettlementDateProcess.calculateSettlementDate(fakeInstruction);

        // should be the first monday (26/6/2017)
        assertEquals(LocalDate.of(2017, 6, 26), fakeInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_arabu_Friday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 6, 23); // Its a Friday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                Currency.getInstance("AED"), // Its Arabu (AED)
                LocalDate.of(2017, 6, 9),
                initialSettlementDate,
                BigDecimal.valueOf(0.50),
                200,
                BigDecimal.valueOf(100.25));

        // calculate new settlement day
        SettlementDateProcess.calculateSettlementDate(fakeInstruction);

        // should be the first Sunday (25/6/2017)
        assertEquals(LocalDate.of(2017, 6, 25), fakeInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_arabu_Sunday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2017, 6, 25); // Its a Sunday

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                Currency.getInstance("SAR"), // Its Arabu (SAR)
                LocalDate.of(2017, 6, 9),
                initialSettlementDate,
                BigDecimal.valueOf(0.50),
                200,
                BigDecimal.valueOf(100.25));

        // calculate new settlement day
        SettlementDateProcess.calculateSettlementDate(fakeInstruction);

        // should be the same
        assertEquals(initialSettlementDate, fakeInstruction.getSettlementDate());
    }
}