import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChildResetComponent } from './child-reset.component';

describe('ChildResetComponent', () => {
  let component: ChildResetComponent;
  let fixture: ComponentFixture<ChildResetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChildResetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChildResetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
