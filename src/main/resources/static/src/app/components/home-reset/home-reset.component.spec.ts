import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeResetComponent } from './home-reset.component';

describe('HomeResetComponent', () => {
  let component: HomeResetComponent;
  let fixture: ComponentFixture<HomeResetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeResetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeResetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
