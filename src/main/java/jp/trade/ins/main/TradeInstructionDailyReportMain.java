package jp.trade.ins.main;
import java.util.Set;

import jp.trade.ins.business.ReportGenerator;
import jp.trade.ins.model.Instruction;
import jp.trade.ins.utils.InstructionsGenerator;

/**
 * This class used for run the trade instruction daily report application and get report
 */
public class TradeInstructionDailyReportMain {

    public static void main(String[] args) {
        
    	//Load sample data and stored into set collection
    	Set<Instruction> instructions = InstructionsGenerator.getSampleInstructions();
        
    	// Create a reportgenerator object
    	ReportGenerator reportGenerator = new ReportGenerator();

    	// Call the generate instruction report method for display the daily report of trade instruction
        System.out.println(reportGenerator.generateInstructionsReport(instructions));
    }
}
