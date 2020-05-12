import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CusDetailsComponent } from './cus-details.component';

describe('CusDetailsComponent', () => {
  let component: CusDetailsComponent;
  let fixture: ComponentFixture<CusDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CusDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CusDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
