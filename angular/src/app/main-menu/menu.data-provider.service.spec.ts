import { TestBed } from '@angular/core/testing';

import { MenuService } from './menu.data-provider.service';

describe('MenuService', () => {
    beforeEach(() => TestBed.configureTestingModule({}));

    it('should be created', () => {
        const service: MenuService = TestBed.get(MenuService);
        expect(service).toBeTruthy();
    });
});
