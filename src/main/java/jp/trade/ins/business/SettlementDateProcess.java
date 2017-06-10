package jp.trade.ins.business;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

import jp.trade.ins.model.Instruction;
import jp.trade.ins.utils.AedSarWorkingDays;
import jp.trade.ins.utils.DefaultWorkingDays;
import jp.trade.ins.utils.IWorkingDays;

/**
 * A settlement date calculator
 */
public class SettlementDateProcess {

    /**
     * Helper function to calculate settlement date for every given instruction
     * @param instructions the instructions of which the settlement date will be calculated
     */
    public static void calculateSettlementDates(Set<Instruction> instructions) {
        instructions.forEach(SettlementDateProcess::calculateSettlementDate);
    }

    /**
     * Calculate the settlementDate Based on some logic
     * @param instruction the instruction of which the settlement date will be calculated
     */
    public static void calculateSettlementDate(Instruction instruction) {
        // Select proper strategy based on the Currency
        final IWorkingDays workingDaysMechanism = getWorkingDaysStrategy(instruction.getCurrency());

        // find the correct settlement date
        final LocalDate newSettlementDate =
                workingDaysMechanism.findFirstWorkingDate(instruction.getSettlementDate());

        if (newSettlementDate != null) {
            // set the correct settlement date
            instruction.setSettlementDate(newSettlementDate);
        }
    }

    /**
     * Select proper strategy based on the Currency
     * @param currency the currency to choose
     * @return the proper working days strategy
     */
    private static IWorkingDays getWorkingDaysStrategy(Currency currency) {
        if ((currency.getCurrencyCode().equals("AED")) ||
            (currency.getCurrencyCode().equals("SAR")))
        {
            return AedSarWorkingDays.getInstance();
        }
        return DefaultWorkingDays.getInstance();
    }
}
