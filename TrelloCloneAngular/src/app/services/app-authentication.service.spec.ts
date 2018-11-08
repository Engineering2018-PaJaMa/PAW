import { TestBed } from '@angular/core/testing';

import { AppServiceService } from './app-authentication.service';

describe('AppServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AppServiceService = TestBed.get(AppServiceService);
    expect(service).toBeTruthy();
  });
});
