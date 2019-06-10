import { Directive, ElementRef } from '@angular/core';
declare var $: any;

@Directive({
    selector: '[appMenuEntry]'
})
export class MenuEntryDirective {

    constructor(private elref: ElementRef) { }

    toggle() {
        const collapsable = this.elref.nativeElement.getElementsByTagName('ul')[0];
        $(collapsable).parent('li').toggleClass('active').children('ul').collapse('toggle');
        $(collapsable).parent('li').siblings().removeClass('active').children('ul.in').collapse('hide');
    }
}
