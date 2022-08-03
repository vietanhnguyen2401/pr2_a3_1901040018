package a2_1901040018;
import java.util.Vector;

public class PCReport {
    /**
     * @effects
     *      <pre>
     *          generate and return a tabular report about PC objects in this.
     *
     *          <p>
     *          The report header is shown in the middle of the 1st line. All but
     *          the first column correspond to the PC attributes, the rows are data
     *          about the PC objects. The first column sequentially displays the row
     *          numbers.
     *
     *          <p>
     *          The column widths are calculated based on the attribute lengths such
     *          that the cell values are properly aligned with the columns and are
     *          displayed right-justified. Further, cells on same row are at least
     *          one space apart from each other.
     *
     *          <p>
     *          e.g.<br>
     *          --------------------------------------------------------------------------------
     *                                          PCPROG REPORT
     *          --------------------------------------------------------------------------------
     *          1 Vostro 3650MT 2016 DELL [Intel-Core-i3-6100 CPU,4GB-DDR3L RAM, 500GB-HDD,....]
     *          --------------------------------------------------------------------------------
     *      </pre>
     */
    public String displayReport(PC[] objs) {
        String[] progReport = new String [objs.length];
        String Progreport="";
        String line= "--------------------------------------------------------------------------------------------------";

        for(int i = 0; i< objs.length; i++) {
            progReport[i] =String.format("%3d %20s %6d %20s %50s",i+1,objs[i].getModel(),objs[i].getYear(),objs[i].getManufacturer(),objs[i].getComps().getElements()+ "\n");
            Progreport += progReport[i];
        }


        String report =line + "\n" +
                String.format("%49s","PCPROG REPORT")+"\n"+
                line+ "\n"+
                Progreport+
                line;

        return report;
    }
}
