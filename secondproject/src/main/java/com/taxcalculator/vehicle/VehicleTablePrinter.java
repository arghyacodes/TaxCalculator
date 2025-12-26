package com.taxcalculator.vehicle;

import java.util.List;

public class VehicleTablePrinter {

    // column widths (tweak if needed)
    private static final int W_REG   = 8;   // RC (numeric/padded)
    private static final int W_BRAND = 8;  // brand names can be longer
    private static final int W_TOP   = 12;  // max velocity (int)
    private static final int W_SEAT  = 12;  // seats (int)
    private static final int W_FUEL  = 14;  // fuel type (string like "CNG/LPG")
    private static final int W_COST  = 15;  // purchase cost (double)
    private static final int W_TAX   = 15;  // vehicle tax (double)

    /** Print header and rows with data RIGHT-ALIGNED. */
    public static void printAll(VehicleDAO dao) {
        try {
            List<VehicleDTO> list = dao.getallvehicles();
            if (list == null || list.isEmpty()) {
                System.out.println("No vehicles found.");
                return;
            }
            System.out.println(repeat('=', 98));
            String headerFormat =
                    "%" + W_REG   + "s  " +
                    "%" + W_BRAND + "s  " +
                    "%" + W_TOP   + "s  " +
                    "%" + W_SEAT  + "s  " +
                    "%" + W_FUEL  + "s  " +
                    "%" + W_COST  + "s  " +
                    "%" + W_TAX   + "s";

            String header = String.format(headerFormat,
                    "RC NUMBER",
                    "BRAND",
                    "MAX VELOCITY",
                    "NO. OF SEATS",
                    "VEHICLE TYPE",
                    "PURCHASE COST",
                    "VEHICLE TAX"
            );

            System.out.println(header);
            System.out.println(repeat('=', header.length()+1));

            // rows (all right-aligned)
            String rowFormat =
                    "%" + W_REG   + "s  " +   // RC (string, right)
                    "%" + W_BRAND + "s  " +   // Brand (string, right)
                    "%" + W_TOP   + "d  " +   // Top speed (int, right)
                    "%" + W_SEAT  + "d  " +   // Seats (int, right)
                    "%" + W_FUEL  + "s  " +   // Fuel type (string, right)
                    "%" + W_COST  + ".2f  " + // Purchase cost (double, right)
                    "%" + W_TAX   + ".2f%n";  // Vehicle tax (double, right)

            for (VehicleDTO v : list) {
                String reg = formatRegno(v.getRegno());
                String brand = safeString(v.getBrand());
                int topspeed = safeInt(v.getTopspeed());
                int seats = safeInt(v.getSeatingcapacity());
                String fuel = fuelDisplay(v.getFueltype());
                double cost = safeDouble(v.getPurchasecost());
                double tax = safeDouble(v.getVehicletax());

                System.out.printf(rowFormat,
                        reg,
                        brand,
                        topspeed,
                        seats,
                        fuel,
                        cost,
                        tax
                );
            }

            System.out.println(repeat('=', header.length()+1));

        } catch (Exception e) {
            System.err.println("Error fetching vehicles: " + e.getMessage());
        }
    }

    /** Print a single vehicle's tax line: "VEHICLE TAX FOR VEHICLE REGNO - <regno> IS <tax>" */
    public static void printByRegno(VehicleDAO dao, String regno) {
        try {
            VehicleDTO probe = new VehicleDTO();
            probe.setRegno(regno);

            VehicleDTO result = dao.getbyid(probe);

            System.out.println("VEHICLE TAX FOR VEHICLE REGNO - " + formatRegno(result.getRegno())
                    + " IS " + String.format("%.2f", safeDouble(result.getVehicletax())));

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // --- helpers ---

    /** If RC is numeric, pad with leading zeros to at least 4 digits. Otherwise return original trimmed string. */
    private static String formatRegno(String reg) {
        if (reg == null) return "";
        String trimmed = reg.trim();
        if (trimmed.matches("\\d+")) {
            // preserve original length if already >4; else pad to 4
            int width = Math.max(4, trimmed.length());
            try {
                int n = Integer.parseInt(trimmed);
                return String.format("%0" + width + "d", n);
            } catch (NumberFormatException ex) {
                return trimmed;
            }
        } else {
            return trimmed;
        }
    }

    /** Map fuel numeric codes ("1","2","3") to names; if already a text value, return UPPERCASE trimmed. */
    private static String fuelDisplay(String fuel) {
        if (fuel == null) return "";
        String t = fuel.trim();
        if (t.matches("\\d+")) {
            switch (t) {
                case "1": return "PETROL";
                case "2": return "DIESEL";
                case "3": return "CNG/LPG";
                default:  return t; // unknown numeric code -> return as-is
            }
        } else {
            return t.toUpperCase();
        }
    }

    private static String repeat(char ch, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) sb.append(ch);
        return sb.toString();
    }

    private static String safeString(String s) { return s == null ? "" : s; }
    private static int safeInt(Integer i) { return i == null ? 0 : i; }
    private static double safeDouble(Double d) { return d == null ? 0.0 : d; }
}
