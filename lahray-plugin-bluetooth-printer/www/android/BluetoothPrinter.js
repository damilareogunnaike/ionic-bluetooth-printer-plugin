
function BluetoothPrinter() {

}

BluetoothPrinter.prototype.ACTIONS = {
    PRINT_TEXT : 1
}

var PRINTER_ACTIONS = {
    "PRINT_TEXT": "PRINT_TEXT",
    "CONNECT": "CONNECT",
    "TEST_PRINT": "TEST_PRINT",
    "PRINT_DATA": "PRINT_DATA"
}

var serviceName = "BluetoothPrinter";

BluetoothPrinter.prototype.printData = function(printerId, data, successCallback, errorCallback){

    var action = PRINTER_ACTIONS.PRINT_DATA;
    cordova.exec(
        function(data){
            successCallback(data);
        },
        function(error){
            errorCallback(error);
        },  
        serviceName, action, [printerId, data]); 
};

BluetoothPrinter.prototype.connectToPrinter = function(printerId, successCallback, errorCallback){
    var action = PRINTER_ACTIONS.CONNECT;
    cordova.exec(
        function(data){
            successCallback(data);
        },
        function(error){
            errorCallback(error);
        },
        serviceName, PRINTER_ACTIONS.CONNECT, [printerId]);
}

BluetoothPrinter.prototype.testPrinter = function(printerId, testString, successCallback, errorCallback){
    var action = PRINTER_ACTIONS.TEST_PRINT;
     cordova.exec(
        function(data){
            successCallback(data);
        },
        function(error){
            errorCallback(error);
        },
        serviceName, PRINTER_ACTIONS.TEST_PRINT, [printerId, testString]);
}

var bluetoothPrinter = new BluetoothPrinter();
module.exports  = bluetoothPrinter;

if(!window.plugins){
    window.plugins = {};
}

if(!window.plugins.bluetoothPrinter){
    window.plugins.bluetoothPrinter = bluetoothPrinter;
}