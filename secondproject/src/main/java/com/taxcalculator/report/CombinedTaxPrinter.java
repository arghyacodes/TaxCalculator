package com.taxcalculator.report;

import com.taxcalculator.property.PropertyDAO;
import com.taxcalculator.property.PropertyDTO;
import com.taxcalculator.vehicle.VehicleDAO;
import com.taxcalculator.vehicle.VehicleDTO;

import java.util.List;

public class CombinedTaxPrinter {

    // Column widths (adjusted for better spacing)
    private static final int W_SR   = 12;
    private static final int W_PART = 12;
    private static final int W_QTY  = 16;   // widened
    private static final int W_TAX  = 16;   // widened

    // Total width inside the borders
    private static final int TOTAL_WIDTH =
            1 + W_SR + 1 +
            W_PART + 1 +
            W_QTY + 1 +
            W_TAX + 1
            -6;

    public static void printCombinedReport() {
        printCombinedReport(new PropertyDAO(), new VehicleDAO());
    }

    public static void printCombinedReport(PropertyDAO propertyDao, VehicleDAO vehicleDao) {
        try {
            List<PropertyDTO> props = propertyDao.getallproperties();
            List<VehicleDTO> vehs = vehicleDao.getallvehicles();

            int propQty = (props == null) ? 0 : props.size();
            int vehQty  = (vehs == null) ? 0 : vehs.size();

            double propTax = props.stream().mapToDouble(p -> safe(p.getPropertytax())).sum();
            double vehTax  = vehs.stream().mapToDouble(v -> safe(v.getVehicletax())).sum();

            printBorder();
            printHeader();
            printBorder();

            printDataRow(1, "PROPERTY", propQty, propTax);
            printDataRow(2, "VEHICLE",  vehQty,  vehTax);

            printBorder();
            printTotalRow(propQty + vehQty, propTax + vehTax);
            printBorder();

        } catch (Exception e) {
            System.err.println("Error generating combined tax report: " + e.getMessage());
        }
    }

    // -------- HEADER & BORDER --------

    private static void printBorder() {
        System.out.println("+" + repeat('-', TOTAL_WIDTH) + "+");
    }

    private static void printHeader() {
        // Headers: left for text columns, right for numeric columns
        String headerFormat =
                "| %-6s %-16s %" + (W_QTY - 2) + "s %" + (W_TAX - 2) + "s |";

        System.out.println(String.format(headerFormat,
                "SR NO.", "PARTICULAR", "QUANTITY", "TAX"));
    }

    // -------- ROWS --------

    private static void printDataRow(int sr, String part, int qty, double tax) {
        String rowFormat =
                "| %-6d %-16s %" + (W_QTY - 2) + "d %" + (W_TAX - 2) + ".2f |";

        System.out.println(String.format(rowFormat,
                sr, part, qty, tax));
    }

    private static void printTotalRow(int qty, double tax) {
        String rowFormat =
                "| %-6s %-16s %" + (W_QTY - 2) + "d %" + (W_TAX - 2) + ".2f |";

        System.out.println(String.format(rowFormat,
                "TOTAL", "----------", qty, tax));
    }

    // -------- UTIL --------

    private static double safe(Double d) {
        return d == null ? 0.0 : d;
    }

    private static String repeat(char c, int n) {
        return String.valueOf(c).repeat(n);
    }
}
