package jp.trade.ins.business;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import jp.trade.ins.model.Instruction;
import jp.trade.ins.utils.Rank;

/**
 * This class used for generate daily report based on instructions.
 */
public class ReportGenerator {
    private StringBuilder stringBuilder = new StringBuilder();

    public String generateInstructionsReport(Set<Instruction> instructions) {
        // first calculate the correct settlement dates
        SettlementDateProcess.calculateSettlementDates(instructions);

        // Build the report string
        return generateDailyOutgoingRanking(instructions,
                generateDailyIncomingRanking(instructions,
                generateDailyIncomingAmount(instructions,
                generateDailyOutgoingAmount(instructions, stringBuilder))))
            .toString();
    }

    private static StringBuilder generateDailyOutgoingAmount(Set<Instruction> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
                SettlementStatsProcess.calculateDailyOutgoingAmount(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Outgoing Daily Amount(BUY)        \n")
                .append("----------------------------------------\n")
                .append("      S.Date       |    Trade Amount      \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingAmount.keySet()) {
            stringBuilder
                .append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyIncomingAmount(Set<Instruction> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
                
        SettlementStatsProcess.calculateDailyIncomingAmount(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Incoming Daily Amount(SELL)          \n")
                .append("----------------------------------------\n")
                .append("      S.Date       |    Trade Amount      \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingAmount.keySet()) {
            stringBuilder
                    .append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyOutgoingRanking(Set<Instruction> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking =
                SettlementStatsProcess.calculateDailyOutgoingRanking(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Outgoing Daily Ranking(BUY)          \n")
                .append("----------------------------------------\n")
                .append("     S.Date    |     Rank   |   Entity     \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingRanking.keySet()) {
            for (Rank rank : dailyOutgoingRanking.get(date)) {
                stringBuilder
                    .append(date).append("   |      ")
                    .append(rank.getRank()).append("     |    ")
                    .append(rank.getEntity()).append("\n");
            }
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyIncomingRanking(Set<Instruction> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking =
                SettlementStatsProcess.calculateDailyIncomingRanking(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Incoming Daily Ranking(SELL)         \n")
                .append("----------------------------------------\n")
                .append("     S.Date    |     Rank   |   Entity     \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyIncomingRanking.keySet()) {
            for (Rank rank : dailyIncomingRanking.get(date)) {
                stringBuilder
                        .append(date).append("   |      ")
                        .append(rank.getRank()).append("     |    ")
                        .append(rank.getEntity()).append("\n");
            }
        }

        return stringBuilder;
    }
}
