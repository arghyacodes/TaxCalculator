package com.taxcalculator.vehicle;

import java.util.List;

import com.taxcalculator.Messages;
import com.taxcalculator.Read;

public class VehicleTax {
    public void vehicle() throws Exception {
        int optionSelector = 0;
        boolean running = true;
        while (running) {
            System.out.println(Messages.vehicletaxmenu);
            optionSelector = Read.sc.nextInt();
            VehicleDAO v1 = new VehicleDAO();
            switch (optionSelector) {
                case 1:
                    VehicleDTO vehc1 = new VehicleDTO();
                    System.out.println("ADD VEHICLE DETAILS");
                    System.out.print("ENTER THE VEHICLE REGISTRATION NUMBER: ");
                    vehc1.setRegno(Read.sc.next());
                    System.out.print("ENTER BRAND OF THE VEHICLE: ");
                    vehc1.setBrand(Read.sc.next());
                    System.out.print("ENTER THE MAXIMUM VELOCITY OF THE VEHICLE (KMPH): ");
                    vehc1.setTopspeed(Read.sc.nextInt());
                    System.out.print("ENTER CAPACITY (NUMBER OF SEATS) OF THE VEHICLE: ");
                    vehc1.setSeatingcapacity(Read.sc.nextInt());
                    System.out.println("CHOOSE THE TYPE OF THE VEHICLE -\n1. PETROL DRIVEN\n2. DIESEL DRIVEN\n3. CNG/LPG DRIVEN");
                    vehc1.setFueltype(Read.sc.next());
                    System.out.print("ENTER THE PURCHASE COST OF THE VEHICLE: ");
                    vehc1.setPurchasecost(Read.sc.nextDouble());
                    v1.addvehicle(vehc1);
                    break;
                case 2:
                    System.out.println("ENTER THE REGISTRATION NUMBER OF VEHICLE: ");
                    String regno = Read.sc.next();
                    // Use the printer to show only the tax line
                    VehicleTablePrinter.printByRegno(v1, regno);
                    break;
                case 3:
                    // Print all vehicles in tabular format using the printer
                    VehicleTablePrinter.printAll(v1);
                    break;
                case 4:
                    running = false;
                    System.out.println(Messages.mainmenu);
                    break;
                default:
                    System.out.println(Messages.invalidSelection);
            }
        }
    }
}
