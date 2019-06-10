import { Directive, Host, HostListener } from '@angular/core';
import { MenuEntryDirective } from './menu-entry.directive';

@Directive({
    selector: '[appMenuSubitem]'
})
export class MenuSubitemDirective {

    constructor(@Host() private menuEntry: MenuEntryDirective) { }

    @HostListener('click') onclick() {
        this.menuEntry.toggle();
    }
}
