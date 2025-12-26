package com.taxcalculator.property;

import java.util.List;

public class PropertyTablePrinter {

    // Print table header
    private static void printHeader() {
        System.out.println("=============================================================");
    	System.out.printf("%6s %12s %8s %6s %8s %14s%n",
                "ID", "BaseValue", "Area", "Age", "InCity", "PropertyTax");
    	System.out.println("=============================================================");
//        System.out.printf("%-6s %-12s %-8s %-6s %-8s %-14s%n",
//                "======", "==========", "======", "====", "======", "==============");
    }
    private static void printfooter() {
    	System.out.println("=============================================================");
    }
    // Print a single row
    private static void printRow(PropertyDTO p) {
        System.out.printf("%6d %12d %8d %6d %8s %14.2f%n",
                p.getId(),
                p.getBasevalue(),
                p.getArea(),
                p.getAge(),
                p.getIncity(),
                p.getPropertytax()
        );
    }

    // Print ALL properties in table format
    public static void printAll(PropertyDAO dao) {
        try {
            List<PropertyDTO> list = dao.getallproperties();

            if (list == null || list.isEmpty()) {
                System.out.println("No properties found.");
                return;
            }

            printHeader();
            for (PropertyDTO p : list) {
                printRow(p);
            }
            printfooter();

        } catch (Exception e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }
    }

    // Print a single property by ID in table format
    public static void printById(PropertyDAO dao, int id) {
        try {
            PropertyDTO obj = new PropertyDTO();
            obj.setId(id);

            PropertyDTO result = dao.getbyid(obj);
            
            System.out.println(
            		"PROPERTY TAX FOR PROPERTY ID - "+result.getId()+" IS "+result.getPropertytax());
//            printHeader();
//            printRow(result);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}