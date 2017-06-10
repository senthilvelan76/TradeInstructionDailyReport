package jp.trade.ins.test;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import jp.trade.ins.utils.AedSarWorkingDays;
import jp.trade.ins.utils.IWorkingDays;
import static org.junit.Assert.assertEquals;

public class AedSarWorkingDaysTest {
    private IWorkingDays workingDays;
    @Before
    public void setUp() throws Exception {
        workingDays = AedSarWorkingDays.getInstance();
    }

    @Test
    public void testFindFirstWorkingDate_Sunday() throws Exception {
        final LocalDate aSunday = LocalDate.of(2017, 6, 25);

        // should return the same, since Sunday is a working day in Arabu
        assertEquals(aSunday, workingDays.findFirstWorkingDate(aSunday));
    }

    @Test
    public void testFindFirstWorkingDate_Thursday() throws Exception {
        final LocalDate aThursday = LocalDate.of(2017, 6, 22);

        // should return the same, since Thursday is a working day in Arabu
        assertEquals(aThursday, workingDays.findFirstWorkingDate(aThursday));
    }

    @Test
    public void testFindFirstWorkingDate_Friday() throws Exception {
        final LocalDate aFriday = LocalDate.of(2017, 6, 23);

        // should return the first Sunday (25/6/2017), since Friday is not a working day
        assertEquals(LocalDate.of(2017, 6, 25), workingDays.findFirstWorkingDate(aFriday));
    }

    @Test
    public void testFindFirstWorkingDate_Saturday() throws Exception {
        final LocalDate aSaturday = LocalDate.of(2017, 6, 24);

        // should return the first Sunday (25/6/2017), since Saturday is not a working day
        assertEquals(LocalDate.of(2017, 6, 25), workingDays.findFirstWorkingDate(aSaturday));
    }

}