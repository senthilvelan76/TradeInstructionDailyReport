package jp.trade.ins.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import jp.trade.ins.business.SettlementDateProcess;
import jp.trade.ins.business.SettlementStatsProcess;
import jp.trade.ins.model.Instruction;
import jp.trade.ins.model.TradeAction;
import jp.trade.ins.utils.Rank;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SettlementStatsProcessTest {

    private static final LocalDate MONDAY    = LocalDate.of(2017, 6, 19);
    private static final LocalDate TUESDAY   = LocalDate.of(2017, 6, 20);
    private static final LocalDate WEDNESDAY = LocalDate.of(2017, 6, 21);
    private static final LocalDate SATURDAY  = LocalDate.of(2017, 6, 17);
    private static final LocalDate SUNDAY    = LocalDate.of(2017, 6, 18);

    private static Set<Instruction> getFakeSetOfInstructions() {
        final Set<Instruction> instructions = new HashSet<>();

        // ===========================================================================
        // All these should be under the same settlement date (19/6/2017)
        // ===========================================================================
        instructions.add(new Instruction(
                "E1",
                TradeAction.BUY,
                Currency.getInstance("SGD"),
                LocalDate.of(2017, 6, 9),
                MONDAY,
                BigDecimal.valueOf(1),
                100,
                BigDecimal.valueOf(1)));

        instructions.add(new Instruction(
                "E2",
                TradeAction.BUY,
                Currency.getInstance("SGD"),
                LocalDate.of(2017, 6, 9),
                MONDAY,
                BigDecimal.valueOf(1),
                200,
                BigDecimal.valueOf(1)));

        instructions.add(new Instruction(
                "E3",
                TradeAction.BUY,
                Currency.getInstance("SGD"),
                LocalDate.of(2017, 6, 9),
                SATURDAY,
                BigDecimal.valueOf(1),
                300,
                BigDecimal.valueOf(1)));

        instructions.add(new Instruction(
                "E4",
                TradeAction.SELL,
                Currency.getInstance("SGD"),
                LocalDate.of(2017, 6, 9),
                SUNDAY,
                BigDecimal.valueOf(1),
                200,
                BigDecimal.valueOf(1)));

        // ===========================================================================
        // All these should be under the same settlement date (20/6/2017)
        // ===========================================================================
        instructions.add(new Instruction(
                "E5",
                TradeAction.BUY,
                Currency.getInstance("SGD"),
                LocalDate.of(2017, 6, 9),
                TUESDAY,
                BigDecimal.valueOf(1),
                400,
                BigDecimal.valueOf(1)));

        instructions.add(new Instruction(
                "E6",
                TradeAction.SELL,
                Currency.getInstance("SGD"),
                LocalDate.of(2017, 6, 9),
                TUESDAY,
                BigDecimal.valueOf(1),
                1000,
                BigDecimal.valueOf(1)));

        // ===========================================================================
        // All these should be under the same settlement date (21/6/2017)
        // ===========================================================================
        instructions.add(new Instruction(
                "E7",
                TradeAction.BUY,
                Currency.getInstance("SGD"),
                LocalDate.of(2017, 6, 10),
                WEDNESDAY,
                BigDecimal.valueOf(1),
                700,
                BigDecimal.valueOf(1)));

        SettlementDateProcess.calculateSettlementDates(instructions);

        return instructions;
    }

    @Test
    public void testDailyIncomingAmount() throws Exception {
        final Map<LocalDate, BigDecimal> dailyIncomingAmount =
                SettlementStatsProcess.calculateDailyIncomingAmount(getFakeSetOfInstructions());

        assertEquals(2, dailyIncomingAmount.size());
        assertTrue(Objects.equals(dailyIncomingAmount.get(MONDAY), BigDecimal.valueOf(200.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        assertTrue(Objects.equals(dailyIncomingAmount.get(TUESDAY), BigDecimal.valueOf(1000.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
    }

    @Test
    public void testDailyOutgoingAmount() throws Exception {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
                SettlementStatsProcess.calculateDailyOutgoingAmount(getFakeSetOfInstructions());

        assertEquals(3, dailyOutgoingAmount.size());
        assertTrue(Objects.equals(dailyOutgoingAmount.get(MONDAY), BigDecimal.valueOf(600.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        assertTrue(Objects.equals(dailyOutgoingAmount.get(TUESDAY), BigDecimal.valueOf(400.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        assertTrue(Objects.equals(dailyOutgoingAmount.get(WEDNESDAY), BigDecimal.valueOf(700.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
    }

    @Test
    public void testDailyIncomingRanking() throws Exception {
        final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking =
                SettlementStatsProcess.calculateDailyIncomingRanking(getFakeSetOfInstructions());

        assertEquals(2, dailyIncomingRanking.size());

        assertEquals(1, dailyIncomingRanking.get(MONDAY).size());
        assertEquals(1, dailyIncomingRanking.get(TUESDAY).size());

        assertTrue(dailyIncomingRanking.get(MONDAY).contains(new Rank(1, "E4", MONDAY)));
        assertTrue(dailyIncomingRanking.get(TUESDAY).contains(new Rank(1, "E6", TUESDAY)));

    }

    @Test
    public void testDailyOutgoingRanking() throws Exception {
        final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking =
                SettlementStatsProcess.calculateDailyOutgoingRanking(getFakeSetOfInstructions());

        assertEquals(3, dailyOutgoingRanking.size());

        assertEquals(3, dailyOutgoingRanking.get(MONDAY).size());
        assertEquals(1, dailyOutgoingRanking.get(TUESDAY).size());
        assertEquals(1, dailyOutgoingRanking.get(WEDNESDAY).size());

        assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new Rank(1, "E3", MONDAY)));
        assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new Rank(2, "E2", MONDAY)));
        assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new Rank(3, "E1", MONDAY)));

        assertTrue(dailyOutgoingRanking.get(TUESDAY).contains(new Rank(1, "E5", TUESDAY)));

        assertTrue(dailyOutgoingRanking.get(WEDNESDAY).contains(new Rank(1, "E7", WEDNESDAY)));
    }
}