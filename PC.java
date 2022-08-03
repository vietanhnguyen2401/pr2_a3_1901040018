package a2_1901040018;
import java.util.Vector;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

public class PC {
    @DomainConstraint(type= "String", mutable= true, optional =false, length = 20)
    private String model;

    @DomainConstraint (type="Integer", mutable = false, optional =false, min = 1940)
    private int year;

    @DomainConstraint (type ="String", mutable = false, optional = false, length =20)
    private String manufacturer;

    @DomainConstraint (type ="Set", mutable =true, optional = false)
    private Set<String> comps;


    /**
     * @effect <pre>
     * 	if model, year, manufacturer, components are invalid
     * 		initialise this as <model,year,manufacturer,comps>
     * 	else
     * 		throw NotPosibleException
     * 		</pre>
     */
    @DOpt (type= OptType.Constructor)
    public PC(@AttrRef("model") String model, @AttrRef("year") int year,
              @AttrRef("manufacturer") String manufacturer, @AttrRef("comps") Set<String> comps) throws NotPossibleException {
        if (!validateModel(model)) {
            // model is invalid, initialise this as <> and print error
            throw new NotPossibleException ("Invalid model" + model );
        }
        if (!validateYear(year)) {
            // year is invalid, initialise this as <> and print error
            throw new NotPossibleException ("Invalid year" + year );
        }
        if (!validateManufacturer(manufacturer)) {
            // manufacturer is invalid, initialise this as <> and print error
            throw new NotPossibleException ("Invalid manufacturer" + manufacturer );
        }
        if (!validateComps(comps)) {
            // components are invalid, initialise this as <> and print error
            throw new NotPossibleException ("Invalid component " + comps );
        }
        // if model, year, manufacturer, components are invalid: initialise this as <model,year,manufacturer,comps>
        this.model=model;
        this.year=year;
        this.manufacturer=manufacturer;
        this.comps=comps;
    }
    final static int Length_model=20;
    final static int Length_manufacturer=20;

    /**
     *
     * @return this <tt> model </model>
     */
    @DOpt (type=OptType.Observer)
    @AttrRef ("model")
    public String getModel() {
        return model;
    }
    /**
     *
     * @return this  <tt> year </tt>
     */
    @DOpt (type=OptType.Observer)
    @AttrRef ("year")
    public int getYear() {
        return year;
    }

    /**
     *
     * @return this<tt> manufacturer </tt>
     */
    @DOpt(type = OptType.Observer)
    @AttrRef ("manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }
    /**
     *
     * @return this <tt> comps </tt>
     */
    @DOpt(type =  OptType.Observer)
    @AttrRef ("comps")
    public Set<String> getComps() {
        return comps;
    }
    /**
     * @modifies this.model
     * @effects <pre>
     *  if model is valid
     *      set this.model = model
     *      return true
     *  else
     *      return false
     *          </pre>
     */
    @DOpt (type= OptType.Mutator)
    @AttrRef ("model")
    public boolean setModel(String model) {
        if (validateModel(model)) {
            this.model = model;
            return true;
        } else {
            return false;
        }
    }
    /**
     * @modifies this.comps
     * @effects <pre>
     *  if comps is valid
     *      set this.comps = comps
     *      return true
     *  else
     *      return false
     *          </pre>
     */
    @DOpt (type= OptType.Mutator)
    @AttrRef ("comps")
    public boolean setComps(Set<String> comps) {
        if (validateComps(comps)) {
            this.comps = comps;
            return true;
        } else {
            return false;
        }
    }
    /**
     * @effects     <pre>
     *  if model is valid
     *      return true
     *  else
     *      return false
     *          </pre>
     */
    private boolean validateModel(String model) {
        if (model == null || "".equals(model)) {
            return false;
        }
        if (model.length() > Length_model) {
            return false;
        }
        return true;
    }
    /**
     * @effects     <pre>
     *  if year is valid
     *      return true
     *  else
     *      return false
     *          </pre>
     */
    private boolean validateYear (int year) {
        if (year < 1940) {
            return false;
        }
        return true;
    }
    /**
     * @effects     <pre>
     *  if manufacturer is valid
     *      return true
     *  else
     *      return false
     *          </pre>
     */
    private boolean validateManufacturer (String manufacturer) {
        if (manufacturer == null || "".equals(manufacturer)) {
            return false;
        }
        if (manufacturer.length()>Length_manufacturer) {
            return false;
        }
        return true;
    }
    /**
     * @effects     <pre>
     *  if comps is valid
     *      return true
     *  else
     *      return false
     *          </pre>
     */
    private boolean validateComps (Set<String> comps) {
        if(comps.size() <= 0 ) {
            return false;
        }
        Vector <String> compo= comps.getElements();
        String[] element= new String [comps.size()];
        for (int i=0; i<comps.size(); i++) {
            element[i]= compo.get(i);
            if (element[i] == null || "".equals(element[i])) {
                return false;
            }
        }
        return true;
    }

    @DOpt (type=OptType.Helper)
    public boolean repOK () {
        return
                validateModel(model)  &&
                        validateManufacturer(manufacturer) &&
                        validateYear(year) &&
                        validateComps(comps);
    }

    @Override
    public String toString() {
        return "PC:<" + model + "," + year + "," + manufacturer + "," +    comps +">.";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comps == null) ? 0 : comps.hashCode());
        result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + year;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PC other = (PC) obj;
        if (comps == null) {
            if (other.comps != null)
                return false;
        } else if (!comps.equals(other.comps))
            return false;
        if (manufacturer == null) {
            if (other.manufacturer != null)
                return false;
        } else if (!manufacturer.equals(other.manufacturer))
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (year != other.year)
            return false;
        return true;
    }
}
