var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
/**
 * This is a template for new plugin wrappers
 *
 * TODO:
 * - Add/Change information below
 * - Document usage (importing, executing main functionality)
 * - Remove any imports that you are not using
 * - Add this file to /src/index.ts (follow style of other plugins)
 * - Remove all the comments included in this template, EXCEPT the @Plugin wrapper docs and any other docs you added
 * - Remove this note
 *
 */
import { Injectable } from '@angular/core';
import { Plugin, Cordova, IonicNativePlugin } from '@ionic-native/core';
var BluetoothPrinter = (function (_super) {
    __extends(BluetoothPrinter, _super);
    function BluetoothPrinter() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    BluetoothPrinter.prototype.printData = function (printerId, data) { return; };
    BluetoothPrinter.prototype.connectToPrinter = function (printerId) { return; };
    BluetoothPrinter.prototype.testPrinter = function (printerId, testString) { return; };
    return BluetoothPrinter;
}(IonicNativePlugin));
BluetoothPrinter.decorators = [
    { type: Injectable },
];
/** @nocollapse */
BluetoothPrinter.ctorParameters = function () { return []; };
__decorate([
    Cordova(),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object, Object]),
    __metadata("design:returntype", Promise)
], BluetoothPrinter.prototype, "printData", null);
__decorate([
    Cordova(),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object]),
    __metadata("design:returntype", Promise)
], BluetoothPrinter.prototype, "connectToPrinter", null);
__decorate([
    Cordova(),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object, Object]),
    __metadata("design:returntype", Promise)
], BluetoothPrinter.prototype, "testPrinter", null);
BluetoothPrinter = __decorate([
    Plugin({
        pluginName: 'BluetoothPrinter',
        plugin: '',
        pluginRef: '',
        repo: '',
        platforms: [],
        install: '',
    })
], BluetoothPrinter);
export { BluetoothPrinter };
//# sourceMappingURL=index.js.map