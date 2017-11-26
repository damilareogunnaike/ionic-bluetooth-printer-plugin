import { IonicNativePlugin } from '@ionic-native/core';
export declare class BluetoothPrinter extends IonicNativePlugin {
    printData(printerId: any, data: any): Promise<any>;
    connectToPrinter(printerId: any): Promise<any>;
    testPrinter(printerId: any, testString: any): Promise<any>;
}
