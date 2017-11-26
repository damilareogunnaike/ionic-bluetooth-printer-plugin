package raywox.com.lahray.printer;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.android.print.sdk.PrinterConstants;
import com.android.print.sdk.PrinterInstance;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * Created by Lahray on 10/13/2017.
 */
public class BluetoothPrinter extends CordovaPlugin {
    
        private static String ACTION_PRINT_TEXT = "PRINT_TEXT";
        private static String ACTION_CONNECT = "CONNECT";
        private static String ACTION_TEST_PRINTER = "TEST_PRINT";
        private static String ACTION_PRINT_DATA = "PRINT_DATA";
    
        private static String PRINTER_CONNECTED = "Printer connected successfully!";
        private static String PRINTER_CONNECT_ERROR = "Unable to connect!";
        private static String PRINT_SUCCESS = "Print Success.";
        private PrinterInstance mPrinter;
        private Context mContext;
        private boolean isConnected;
        private BluetoothOperation mOperation;
    
        @Override
        public boolean execute(String action, JSONArray data, final CallbackContext callbackContext) {
            try {
                this.mContext = this.cordova.getActivity().getApplicationContext();
                if (this.mOperation == null) {
                    this.mOperation = new BluetoothOperation(this.mContext, getHandler(callbackContext));
                }
    
                final String printerId = data.getString(0);
    
                connectToPrinter(printerId);
                if (action.equals(ACTION_CONNECT)) {
                    if (this.mPrinter != null && this.mPrinter.isConnected()) {
                        callbackContext.success(PRINTER_CONNECTED);
                    } else {
                        callbackContext.error(PRINTER_CONNECT_ERROR);
                    }
                }
    
                if (action.equals(ACTION_TEST_PRINTER)) {
                    String testString = data.getString(1);
                    PrintUtils.printText(testString, mPrinter);
                    callbackContext.success(PRINT_SUCCESS);
                }
    
                if (action.equals(ACTION_PRINT_TEXT)) {
                    String text = data.getString(1);
                    PrintUtils.printText(text, mPrinter);
                    callbackContext.success(PRINT_SUCCESS);
                }
    
                if (action.equals(ACTION_PRINT_DATA)) {
                    PrintUtils.printData(data.getJSONObject(1), mPrinter);
                    callbackContext.success(PRINT_SUCCESS);
                }
    
            } catch (JSONException ex) {
                callbackContext.error(ex.getMessage());
            }
            return false;
        }
    
        private void connectToPrinter(String printerId) {
            if (this.mPrinter == null || (this.mPrinter != null && !this.mPrinter.isConnected())) {
                final String printerMac = printerId;
                mOperation.open(printerMac, false);
                mPrinter = mOperation.getPrinter();
                //                new Thread(new Runnable(){
                //                    public void run() {
                //
                //                    }
                //                }).start();
            }
        }
    
        private Handler getHandler(CallbackContext callbackContext) {
    
            Handler mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case PrinterConstants.Connect.SUCCESS:
                            isConnected = true;
                            mPrinter = mOperation.getPrinter();
                            break;
                        case PrinterConstants.Connect.FAILED:
                            isConnected = false;
                            //Toast.makeText(mContext, "connect failed...", Toast.LENGTH_SHORT).show();
                            break;
                        case PrinterConstants.Connect.CLOSED:
                            isConnected = false;
                            //Toast.makeText(mContext, "connect close...", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
            };
            return mHandler;
        }
    }
    