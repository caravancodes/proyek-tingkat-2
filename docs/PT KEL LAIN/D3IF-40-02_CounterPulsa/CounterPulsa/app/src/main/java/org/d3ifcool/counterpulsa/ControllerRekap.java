package org.d3ifcool.counterpulsa;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by AKU CINTA DIA on 2/25/2018.
 */

public class ControllerRekap implements Serializable {

    // DECLARATION VARIABLE
    private ArrayList<RekapClass> rekapClasses;

    // CONSTRUCTOR
    public ControllerRekap() {
        this.rekapClasses = new ArrayList<>();
        this.addDataTransaksi(new RekapClass(1, 1, "082188431070", "2017/08/18", "18:00:28", new ServiceClass(1, 5000, 1, "Rp. 5000")));
        this.addDataTransaksi(new RekapClass(2, 1, "082188431070", "2017/09/18", "18:00:28", new ServiceClass(1, 5000, 1, "Rp. 5000")));
        this.addDataTransaksi(new RekapClass(3, 1, "082188431070", "2017/09/19", "18:00:28", new ServiceClass(1, 5000, 1, "Rp. 5000")));
    }

    // GETTER AND SETTER METHOD
    public ArrayList<RekapClass> getRekapHarian() {
        return rekapClasses;
    }

    public ArrayList<RekapClass> getRekapByDate(String tanggal){
        ArrayList<RekapClass> data = new ArrayList<>();

        String tahunX, tahunY, bulanX, bulanY;
        tahunX = tanggal.split("/")[0];
        bulanX = tanggal.split("/")[1];

        for (int i = 0; i < this.getRekapHarian().size(); i++){
            tahunY = this.getRekapHarian().get(i).getTanggal().split("/")[0];
            bulanY = this.getRekapHarian().get(i).getTanggal().split("/")[1];
            if(tahunX.equalsIgnoreCase(tahunY) && bulanX.equalsIgnoreCase(bulanY)){
                data.add(this.getRekapHarian().get(i));
            }
        }
        return data;
    }

    public ArrayList<RekapClass> getRekapBulanan(){
        ArrayList<RekapClass> rekapUnique = new ArrayList<>();
        rekapUnique.add(rekapClasses.get(0));
        for(int i = 0; i < this.rekapClasses.size(); i++){
            boolean valid = true;
            for(int j = 0; j < rekapUnique.size(); j++){
                String TahunX = this.rekapClasses.get(i).getTanggal().split("/")[0];
                String BulanX = this.rekapClasses.get(i).getTanggal().split("/")[1];
                String TahunY = rekapUnique.get(j).getTanggal().split("/")[0];
                String BulanY = rekapUnique.get(j).getTanggal().split("/")[1];
                if(TahunX.equalsIgnoreCase(TahunY) && BulanX.equalsIgnoreCase(BulanY)){
                    valid = false;
                }
            }
            if(valid == true){
                rekapUnique.add(rekapClasses.get(i));
            }
        }
        return rekapUnique;
    }

    // INSERT NEW TRANSAKSI
    public boolean addDataTransaksi(RekapClass data){
        this.rekapClasses.add(data);
        return true;
    }
}
