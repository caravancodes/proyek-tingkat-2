package org.d3ifcool.counterpulsa;

import org.d3ifcool.counterpulsa.ServiceClass;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by AKU CINTA DIA on 2/25/2018.
 */

public class ControllerService implements Serializable{

    // DECLARATION VARIABLE
    private ArrayList<ServiceClass> serviceClasses;

    // CONSTRUCTOR
    public ControllerService() {
        this.serviceClasses = new ArrayList<>();
        this.tambahService(new ServiceClass(1, 5000, 1, "Rp. 5000"));
        this.tambahService(new ServiceClass(2, 10000, 1, "Rp. 10000"));
        this.tambahService(new ServiceClass(3, 50000, 1, "Rp. 50000"));
    }

    // INSERT NEW SeRVICE
    public boolean tambahService(ServiceClass data){
        this.serviceClasses.add(data);
        return true;
    }

    // GETTER AND SETTER METHOD
    public ArrayList<ServiceClass> getServiceClasses() {
        return serviceClasses;
    }

    public void setServiceClasses(ArrayList<ServiceClass> serviceClasses) {
        this.serviceClasses = serviceClasses;
    }
}
