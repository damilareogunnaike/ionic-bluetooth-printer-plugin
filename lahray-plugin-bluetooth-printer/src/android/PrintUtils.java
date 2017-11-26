package raywox.com.lahray.printer;

import com.android.print.sdk.PrinterConstants;
import com.android.print.sdk.PrinterInstance;
import com.android.print.sdk.Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lahray on 11/14/2017.
 */

public class PrintUtils {
    
            public static void printText(String text, PrinterInstance mPrinter) {
                mPrinter.init();
                mPrinter.printText(text);
                mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 2);
            }
    
            public static void printData(JSONObject jsonData, PrinterInstance mPrinter) throws JSONException {
                mPrinter.init();
                TableData tableData = TableData.fromJSONData(jsonData);
                StringBuffer sb = new StringBuffer();
    
                /* Receipt Header */
                mPrinter.setPrintModel(true, false, false, false);
                mPrinter.setPrinter(PrinterConstants.Command.ALIGN, PrinterConstants.Command.ALIGN_CENTER);
                mPrinter.setCharacterMultiple(1, 1);
                mPrinter.printText(tableData.getTitle() + "\n");
                mPrinter.setCharacterMultiple(0, 0);
                sb.append(tableData.getAddress() + "\n");
                sb.append(tableData.getEmail() + "\n");
                sb.append(tableData.getMobileNo() + "\n\n");
                mPrinter.printText(sb.toString());
    
                        /* Customer and Sales Details */
                sb = new StringBuffer();
                mPrinter.setPrintModel(false, false, false, false);
                mPrinter.setPrinter(PrinterConstants.Command.ALIGN, PrinterConstants.Command.ALIGN_LEFT);
                mPrinter.setCharacterMultiple(0, 0);
                sb.append("Customer: " + tableData.customerName + "\n");
                sb.append("Time:" + tableData.getDateTime() + "\n\n");
                mPrinter.printText(sb.toString());
    
                /* Items Table*/
                mPrinter.setPrintModel(false, false, false, false);
                mPrinter.setPrinter(PrinterConstants.Command.ALIGN, PrinterConstants.Command.ALIGN_LEFT);
                Table table;
                table = new Table(tableData.getTableHeaders(), ";", new int[]{4, 14, 6, 6});
                for (String row : tableData.getTableBody()) {
                    table.addRow(row);
                }
                table.addRow(tableData.getTableFooters());
                mPrinter.printTable(table);
    
                /* Footer */
                mPrinter.setPrinter(PrinterConstants.Command.ALIGN, PrinterConstants.Command.ALIGN_CENTER);
                mPrinter.setCharacterMultiple(0, 1);
                mPrinter.printText("\n");
                mPrinter.printText(tableData.getReceiptFooter() + "\n\n\n\n");
                mPrinter.setPrinter(PrinterConstants.Command.PRINT_AND_WAKE_PAPER_BY_LINE, 2);
            }
    
            private static class TableData {
                private String title;
                private String address;
                private String customerName;
                private String dateTime;
                private String guid;
                private String tableHeaders;
                private String tableFooters;
                private List<String> tableBody;
                private String footer;
                private String email;
                private String mobileNo;
                private String receiptFooter;
    
                public String getTitle() {
                    return title;
                }
    
                public void setTitle(String title) {
                    this.title = title;
                }
    
                public String getCustomerName() {
                    return customerName;
                }
    
                public void setCustomerName(String customerName) {
                    this.customerName = customerName;
                }
    
                public String getDateTime() {
                    return dateTime;
                }
    
                public void setDateTime(String dateTime) {
                    this.dateTime = dateTime;
                }
    
                public String getGuid() {
                    return guid;
                }
    
                public void setGuid(String guid) {
                    this.guid = guid;
                }
    
                public String getAddress() {
                    return address;
                }
    
                public void setAddress(String address) {
                    this.address = address;
                }
    
                public String getTableHeaders() {
                    return tableHeaders;
                }
    
                public void setTableHeaders(String tableHeaders) {
                    this.tableHeaders = tableHeaders;
                }
    
                public String getTableFooters() {
                    return tableFooters;
                }
    
                public void setTableFooters(String tableFooters) {
                    this.tableFooters = tableFooters;
                }
    
                public List<String> getTableBody() {
                    return tableBody;
                }
    
                public void setTableBody(List<String> tableBody) {
                    this.tableBody = tableBody;
                }
    
                public String getFooter() {
                    return footer;
                }
    
                public void setFooter(String footer) {
                    this.footer = footer;
                }
    
                public String getReceiptFooter() {
                    return receiptFooter;
                }
    
                public void setReceiptFooter(String receiptFooter) {
                    this.receiptFooter = receiptFooter;
                }
    
                public String getMobileNo() {
                    return mobileNo;
                }
    
                public void setMobileNo(String mobileNo) {
                    this.mobileNo = mobileNo;
                }
    
                public String getEmail() {
                    return email;
                }
    
                public void setEmail(String email) {
                    this.email = email;
                }
    
                public static TableData fromJSONData(JSONObject jsonObject) throws JSONException {
                    TableData tableData = new TableData();
    
                    tableData.setTitle(jsonObject.getString("title"));
                    tableData.setAddress(jsonObject.getString("address"));
                    tableData.setCustomerName(jsonObject.getString("customerName"));
                    tableData.setGuid(jsonObject.getString("guid"));
                    tableData.setDateTime(jsonObject.getString("dateTime"));
                    tableData.setTableHeaders(jsonObject.getString("tableHeader"));
                    tableData.setTableFooters(jsonObject.getString("tableFooter"));
                    tableData.setEmail(jsonObject.getString("email"));
                    tableData.setMobileNo(jsonObject.getString("mobileNo"));
                    tableData.setReceiptFooter(jsonObject.getString("receiptFooter"));
    
                    JSONArray jsonTableBody = jsonObject.getJSONArray("tableBody");
                    List<String> tableBody = new ArrayList<String>();
                    for (int i = 0; i < jsonTableBody.length(); i++) {
                        tableBody.add(jsonTableBody.getString(i));
                    }
                    tableData.setTableBody(tableBody);
                    return tableData;
                }
            }
        }
    