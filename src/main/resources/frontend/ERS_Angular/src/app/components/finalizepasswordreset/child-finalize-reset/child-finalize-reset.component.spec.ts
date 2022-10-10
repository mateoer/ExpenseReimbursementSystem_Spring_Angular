import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChildFinalizeResetComponent } from './child-finalize-reset.component';

describe('ChildFinalizeResetComponent', () => {
  let component: ChildFinalizeResetComponent;
  let fixture: ComponentFixture<ChildFinalizeResetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChildFinalizeResetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChildFinalizeResetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
