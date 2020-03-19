import { TestBed } from '@angular/core/testing';

import { MenuDataProviderService } from './menu-data-provider.service';

describe('MenuDataProviderService', () => {
  let service: MenuDataProviderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    // service = TestBed.inject(MenuDataProviderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
