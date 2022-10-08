import { TestBed } from '@angular/core/testing';

import { GeneralRouteService } from './general-route.service';

describe('GeneralRouteService', () => {
  let service: GeneralRouteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GeneralRouteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
