package a2_1901040018;

import java.util.Vector;


import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

import static utils.TextIO.*;

/**
 * @overview PCProg is a program that captures data about PC objects
 *    and displays a report about them on the console.
 * 
 * @attributes
 *  objs  Set<PC>
 *  
 * @object
 *  A typical PCProg is {c1,...,cn} where c1,...,cn are pcs
 * 
 * @abstract_properties
 *  mutable(objs)=true /\ optional(objs)=false
 *
 */
public class PCProg {
  {
    String option;
    do {
      putln("Enter model: ");
      String model = getln();

      putln("Enter year: ");
      int year = getlnInt();

      putln("Enter manufacturer: ");
      String manufacturer = getln();

      Set<String> comps = new Set<>();
      String opt;
      do {
        putln("Enter component: ");
        String cp = getln();
        if (!comps.isIn(cp)) {
          comps.insert(cp);
        } else {
          System.err.println("Overlapping component!");
        }

        putln("Enter another component? [Y/N]");
        opt = getln();
      }
      while (opt.equalsIgnoreCase("y"));
      PCFactory.getInstance();
      PC pc = PCFactory.createPC(model, year, manufacturer, comps);

      if (pc.repOK()) {
        objs.insert(pc);
      }

      putln("Create another PC? [Y/N]");
      option = getln();
    }
    while (option.equalsIgnoreCase("y"));
  }

  private Set<PC> objs;

    /**
     * @effects
     *  initialise this to have an empty set of PCs
     */
    public PCProg() {

      objs = new Set<>();
    }
  
  /**
   * @effects
   *  if objs is not empty
   *    display to the standard console a text-based tabular report on objs
   *    return this report
   *  else
   *    display nothing and return null
   */
    public String displayReport() {
      if (objs.size() > 0) {
        Vector<PC> pcs = objs.getElements();

        PCReport reportObj = new PCReport();
        return reportObj.displayReport(pcs.toArray(new PC[pcs.size()]));
      } else {
        return null;
      }
    }
  
  /**
   * @effects 
   *  save report to a file pcs.txt in the same directory 
   *  as the program's
   */
    public void saveReport(String report) {
        String fileName = "pcs.txt";
        writeFile(fileName);
        putln(report);
        writeStandardOutput();
    }

  /**
   * The run method
   * @effects
   *  initialise an instance of PCProg 
   *  create objects from data entered by the user
   *  display a report on the objects 
   *  prompt user to save report to file
   *  if user answers "Y"
   *    save report 
   *  else
   *    end 
   */
    public static void main(String[] args) {
      //
      PCProg prog = new PCProg();

      // create objects
      try {
        prog.createObjects();

        // display report
        String report = prog.displayReport();

        if (report != null) {
          // prompt user to save report
          putln("Save report to file? [Y/N]");
          String toSave = getln();
          if (toSave.equals("Y")) {
            prog.saveReport(report);
            putln("report saved");
          }
        }
      } catch (NotPossibleException e) {
        System.err.printf("%s: %s%n", e, e.getMessage());
      }

      putln("~END~");
    }
}
